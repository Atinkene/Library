import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bibliotheque bibliotheque = new Bibliotheque();

        System.out.println("Bienvenue dans la bibliothèque virtuelle!");

        while (true) {
            afficherMenu();
            int choix = scanner.nextInt();
            scanner.nextLine(); 

            switch (choix) {
                case 1:
                    ajouterLivre(bibliotheque, scanner);
                    break;
                case 2:
                    rechercherLivre(bibliotheque, scanner);
                    break;
                case 3:
                    emprunterLivre(bibliotheque, scanner);
                    break;
                case 4:
                    retournerLivre(bibliotheque, scanner);
                    break;
                case 5:
                    creerUtilisateur(bibliotheque, scanner);
                    break;
                case 6:
                    modifierLivre(bibliotheque, scanner);
                    break;
                case 7:
                    supprimerLivre(bibliotheque, scanner);
                    break;
                case 8:
                    afficherLivresEmpruntes(bibliotheque, scanner);
                    break;
                case 9:
                    System.out.println("Merci d'avoir utilisé notre bibliothèque virtuelle. Au revoir!");
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez sélectionner une option valide.");
            }
        }
    }

    private static void afficherMenu() {
        System.out.println("\nQue souhaitez-vous faire ?");
        System.out.println("1. Ajouter un livre");
        System.out.println("2. Rechercher un livre");
        System.out.println("3. Emprunter un livre");
        System.out.println("4. Retourner un livre");
        System.out.println("5. Créer un utilisateur");
        System.out.println("6. Modifier un livre");
        System.out.println("7. Supprimer un livre");
        System.out.println("8. Afficher les livres empruntés par un utilisateur");
        System.out.println("9. Quitter");
    }

    private static void ajouterLivre(Bibliotheque bibliotheque, Scanner scanner) {
        System.out.println("Entrez le titre du livre :");
        String titre = scanner.nextLine();
        System.out.println("Entrez le nom de l'auteur :");
        String auteur = scanner.nextLine();
        System.out.println("Entrez l'année de publication :");
        int annee = scanner.nextInt();
        scanner.nextLine(); 
        System.out.println("Entrez l'ISBN :");
        String isbn = scanner.nextLine();

        Livre livre = new Livre(titre, auteur, annee, isbn);
        bibliotheque.ajouterLivre(livre);
        System.out.println("Livre ajouté avec succès : " + livre);
    }

    private static void rechercherLivre(Bibliotheque bibliotheque, Scanner scanner) {
        System.out.println("Entrez le titre, l'auteur ou l'ISBN du livre recherché :");
        String recherche = scanner.nextLine();
        Livre livre = bibliotheque.rechercherLivre(recherche);
        if (livre != null) {
            System.out.println("Livre trouvé : " + livre);
        } else {
            System.out.println("Aucun livre trouvé avec les critères de recherche spécifiés.");
        }
    }

    private static void emprunterLivre(Bibliotheque bibliotheque, Scanner scanner) {
        System.out.println("Entrez le nom de l'utilisateur :");
        String nomUtilisateur = scanner.nextLine();
        Utilisateur utilisateur = trouverUtilisateur(bibliotheque, nomUtilisateur);
        if (utilisateur == null) {
            System.out.println("Utilisateur non trouvé. Veuillez d'abord créer un utilisateur.");
            return;
        }

        System.out.println("Entrez le titre, l'auteur ou l'ISBN du livre à emprunter :");
        String recherche = scanner.nextLine();
        Livre livre = bibliotheque.rechercherLivre(recherche);
        if (livre != null) {
            if (bibliotheque.verifierEligibilite(utilisateur)) {
                bibliotheque.enregistrerEmprunt(utilisateur, livre);
                utilisateur.emprunterLivre(livre);
            } else {
                System.out.println("L'utilisateur n'est pas éligible à emprunter des livres.");
            }
        } else {
            System.out.println("Aucun livre trouvé avec les critères de recherche spécifiés.");
        }
    }

    private static void retournerLivre(Bibliotheque bibliotheque, Scanner scanner) {
        System.out.println("Entrez le nom de l'utilisateur :");
        String nomUtilisateur = scanner.nextLine();
        Utilisateur utilisateur = trouverUtilisateur(bibliotheque, nomUtilisateur);
        if (utilisateur == null) {
            System.out.println("Utilisateur non trouvé. Veuillez d'abord créer un utilisateur.");
            return;
        }

        System.out.println("Entrez le titre, l'auteur ou l'ISBN du livre à retourner :");
        String recherche = scanner.nextLine();
        Livre livre = bibliotheque.rechercherLivre(recherche);
        if (livre != null) {
            bibliotheque.enregistrerRetour(utilisateur, livre);
            utilisateur.retournerLivre(livre);
            System.out.println("Livre retourné avec succès.");
        } else {
            System.out.println("Aucun livre trouvé avec les critères de recherche spécifiés.");
        }
    }

    private static Utilisateur trouverUtilisateur(Bibliotheque bibliotheque, String nomUtilisateur) {
        for (Utilisateur utilisateur : bibliotheque.getListeUtilisateurs()) {
            if (utilisateur.getNom().equalsIgnoreCase(nomUtilisateur)) {
                return utilisateur;
            }
        }
        return null;
    }

    private static void creerUtilisateur(Bibliotheque bibliotheque, Scanner scanner) {
        System.out.println("Entrez le nom de l'utilisateur :");
        String nom = scanner.nextLine();
        System.out.println("Entrez le numéro d'identification de l'utilisateur :");
        int numeroIdentification = scanner.nextInt();
        scanner.nextLine(); 
        System.out.println("L'utilisateur est-il cotisé ? (true/false) :");
        boolean isCotise = scanner.nextBoolean();
        scanner.nextLine(); 

        bibliotheque.creerUtilisateur(nom, numeroIdentification, isCotise);
    }

    private static void modifierLivre(Bibliotheque bibliotheque, Scanner scanner) {
        System.out.println("Entrez le titre, l'auteur ou l'ISBN du livre à modifier :");
        String recherche = scanner.nextLine();
        Livre livre = bibliotheque.rechercherLivre(recherche);
        if (livre != null) {
            System.out.println("Entrez le nouveau titre :");
            String titre = scanner.nextLine();
            System.out.println("Entrez le nouveau nom de l'auteur :");
            String auteur = scanner.nextLine();
            System.out.println("Entrez la nouvelle année de publication :");
            int annee = scanner.nextInt();
            scanner.nextLine(); 
            System.out.println("Entrez le nouveau ISBN :");
            String isbn = scanner.nextLine();

            bibliotheque.modifierLivre(livre, titre, auteur, annee, isbn);
        } else {
            System.out.println("Aucun livre trouvé avec les critères de recherche spécifiés.");
        }
    }

    private static void supprimerLivre(Bibliotheque bibliotheque, Scanner scanner) {
        System.out.println("Entrez le titre, l'auteur ou l'ISBN du livre à supprimer :");
        String recherche = scanner.nextLine();
        Livre livre = bibliotheque.rechercherLivre(recherche);
        if (livre != null) {
            bibliotheque.supprimerLivre(livre);
            System.out.println("Livre supprimé avec succès.");
        } else {
            System.out.println("Aucun livre trouvé avec les critères de recherche spécifiés.");
        }
    }

    private static void afficherLivresEmpruntes(Bibliotheque bibliotheque, Scanner scanner) {
        System.out.println("Entrez le nom de l'utilisateur :");
        String nomUtilisateur = scanner.nextLine();
        Utilisateur utilisateur = trouverUtilisateur(bibliotheque, nomUtilisateur);
        if (utilisateur == null) {
            System.out.println("Utilisateur non trouvé. Veuillez d'abord créer un utilisateur.");
            return;
        }

        bibliotheque.afficherLivresEmpruntes(utilisateur);
    }
}
