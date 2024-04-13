import java.util.ArrayList;
import java.util.HashMap;

public class Bibliotheque {
    private ArrayList<Livre> listeLivres;
    private ArrayList<Utilisateur> listeUtilisateurs;
    private HashMap<Utilisateur, ArrayList<Livre>> empruntsUtilisateurs;

    public Bibliotheque() {
        this.listeLivres = new ArrayList<>();
        this.listeUtilisateurs = new ArrayList<>();
        this.empruntsUtilisateurs = new HashMap<>();
    }

    public void ajouterLivre(Livre livre) {
        listeLivres.add(livre);
    }

    public void supprimerLivre(Livre livre) {
        listeLivres.remove(livre);
    }

    public Livre rechercherLivre(String recherche) {
        for (Livre livre : listeLivres) {
            if (livre.getTitre().equalsIgnoreCase(recherche) || livre.getAuteur().equalsIgnoreCase(recherche) || livre.getISBN().equalsIgnoreCase(recherche)) {
                return livre;
            }
        }
        return null;
    }

    public void enregistrerEmprunt(Utilisateur utilisateur, Livre livre) {
        if (!empruntsUtilisateurs.containsKey(utilisateur)) {
            empruntsUtilisateurs.put(utilisateur, new ArrayList<>());
        }
        empruntsUtilisateurs.get(utilisateur).add(livre);
    }

    public void enregistrerRetour(Utilisateur utilisateur, Livre livre) {
        if (empruntsUtilisateurs.containsKey(utilisateur)) {
            empruntsUtilisateurs.get(utilisateur).remove(livre);
        }
    }

    public boolean verifierEligibilite(Utilisateur utilisateur) {
        return utilisateur.isCotise();
    }

    public ArrayList<Utilisateur> getListeUtilisateurs() {
        return listeUtilisateurs;
    }

    public void creerUtilisateur(String nom, int numeroIdentification, boolean isCotise) {
        Utilisateur utilisateur = new Utilisateur(nom, numeroIdentification, isCotise);
        listeUtilisateurs.add(utilisateur);
        System.out.println("Utilisateur créé avec succès: " + utilisateur);
    }

    // Méthode pour modifier un livre existant
    public void modifierLivre(Livre livre, String titre, String auteur, int anneePublication, String ISBN) {
        if (listeLivres.contains(livre)) {
            livre.modifierLivre(titre, auteur, anneePublication, ISBN);
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
        } else {
            System.out.println("Cet utilisateur n'a emprunté aucun livre.");
        }
    }
}
