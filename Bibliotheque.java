import java.util.ArrayList;
import java.util.HashMap;

public class Bibliotheque {
    /* 
        listeLivres: Une liste d'objets Livre pour stocker les livres de la bibliothèque.
        listeUtilisateurs: Une liste d'objets Utilisateur pour stocker les utilisateurs de la bibliothèque.
        empruntsUtilisateurs: Une HashMap où les clés sont des objets Utilisateur et les valeurs sont des listes de livres (ArrayList<Livre>) empruntés par chaque utilisateur.
    */
    private ArrayList<Livre> listeLivres;
    private ArrayList<Utilisateur> listeUtilisateurs;
    private HashMap<Utilisateur, ArrayList<Livre>> empruntsUtilisateurs;

    /*
        Initialise les listes listeLivres et listeUtilisateurs, ainsi que la HashMap empruntsUtilisateurs dans le constructeur de la classe.
    */
    public Bibliotheque() {
        this.listeLivres = new ArrayList<>();
        this.listeUtilisateurs = new ArrayList<>();
        this.empruntsUtilisateurs = new HashMap<>();
    }
    
    //Dans la methode ajouterLivre on ajoute un objet livre de la classe Livre à la liste des livres de la bibliotheque avec la méthode add()
    public void ajouterLivre(Livre livre) {
        listeLivres.add(livre);
    }

    //Dans la methode supprimerLivre on supprime un objet livre de la classe Livre de la liste des livres de la bibliotheque avec la méthode remove()
    public void supprimerLivre(Livre livre) {
        listeLivres.remove(livre);
    }
    //La fonction rechercherListeLivre Recherche et retourne une liste de livres correspondant à un critère de recherche: auteur) puisqu'un auteur peut avoir plusieurs livres dans le bibliothèque
    public ArrayList<Livre> rechercherListeLivre(String recherche) {
        ArrayList<Livre> livreTrouve= new ArrayList<>();
        for (Livre livre : listeLivres) {
            if (livre.getTitre().equalsIgnoreCase(recherche) || livre.getAuteur().equalsIgnoreCase(recherche) || livre.getISBN().equalsIgnoreCase(recherche)) {
                livreTrouve.add(livre);//A chaque itération, si la condition est satisfaite, on ajoute l'objet livre de la classe Livre à la liste des livres retrouve
            }
        }
        //On verifie par la suite que la liste des livres trouvés n'est pas vide et si cette condition est satisfaite, on retourne la liste 
        if (!livreTrouve.isEmpty()) {
                return livreTrouve;
        } else {
            return null; //Sinon, on retourne null
        }
    }
    // La fonction rechercherLivre est simulaire a la fonction rechercherListeLivre mais elle est plus spécifique à la recherche d un objet livre de la classe Livre selon un critère unique à l'objet: ISBN, nom
    public Livre rechercherLivre(String recherche) {
        for (Livre livre : listeLivres) {
            if (livre.getTitre().equalsIgnoreCase(recherche) || livre.getAuteur().equalsIgnoreCase(recherche) || livre.getISBN().equalsIgnoreCase(recherche)) {
                return livre;
            }
        }
        return null;
    }
    //La methode enregistrerEmprunt permet d'enregistrer un emprunt associant un utilisateur à un livre dans empruntsUtilisateurs par l'utilisation de la méthode put()
    public void enregistrerEmprunt(Utilisateur utilisateur, Livre livre) {
        if (!empruntsUtilisateurs.containsKey(utilisateur)) {
            empruntsUtilisateurs.put(utilisateur, new ArrayList<>());
        }
        empruntsUtilisateurs.get(utilisateur).add(livre);
    }
    //La methode Enregistre le retour d'un livre par un utilisateur en le retirant de empruntsUtilisateurs.
    public void enregistrerRetour(Utilisateur utilisateur, Livre livre) {
        if (empruntsUtilisateurs.containsKey(utilisateur)) {
            empruntsUtilisateurs.get(utilisateur).remove(livre);
        }
    }
    //La fonction verifierEligibilite permet de vérifier si un utilisateur est éligible à emprunter des livres (en fonction de sa cotisation).
    public boolean verifierEligibilite(Utilisateur utilisateur) {
        return utilisateur.isCotise();
    }
    //La fonction getListeUtilisateurs retourne la liste des utilisateurs de la bibliothèque
    public ArrayList<Utilisateur> getListeUtilisateurs() {
        return listeUtilisateurs;
    }

    //La methode creerUtilisateur crée un nouvel utilisateur et l'ajoute à listeUtilisateurs si un utilisateur avec le même identifiant n'existe pas déjà.
    public void creerUtilisateur(String nom, int numeroIdentification, boolean isCotise) {
        boolean utilisateurExistant = false;
        for (Utilisateur utilisateur : listeUtilisateurs) {
            if (numeroIdentification == utilisateur.getNumeroIdentification()) {
                utilisateurExistant = true;
                System.out.printf("Un utilisateur avec l'ID %d existe déjà.%n", numeroIdentification);
                break; // Sortir de la boucle dès qu'un utilisateur est trouvé
            }
        }

        // Créer un nouvel utilisateur seulement si aucun utilisateur avec le même numéro d'identification n'a été trouvé
        if (!utilisateurExistant) {
            Utilisateur newUtilisateur = new Utilisateur(nom, numeroIdentification, isCotise);
            listeUtilisateurs.add(newUtilisateur);
            System.out.println("Utilisateur créé avec succès: " + newUtilisateur);
        }
    }

    // Méthode pour modifier un livre existant
    public void modifierLivre(Livre livre, String titre, String auteur, int anneePublication, String ISBN, int nombreExemplaire) {
        if (listeLivres.contains(livre)) {
            int nombreEmprunt=livre.getnombreEmprunte();
            livre.modifierLivre(titre, auteur, anneePublication, ISBN,nombreExemplaire,nombreEmprunt);
            System.out.println("Livre modifié avec succès: " + livre);
        } else {
            System.out.println("Le livre spécifié n'existe pas dans la bibliothèque.");
        }
    }

    // Méthode pour afficher les livres empruntés par un utilisateur donné
    public void afficherLivresEmpruntes(Utilisateur utilisateur) {
        if (empruntsUtilisateurs.containsKey(utilisateur)) {
            ArrayList<Livre> livresEmpruntes = empruntsUtilisateurs.get(utilisateur);
            System.out.println("Livres empruntés par " + utilisateur.getNom() + ": ");
            for (Livre livre : livresEmpruntes) {
                System.out.println(livre);
            }
        } 
    }
}
