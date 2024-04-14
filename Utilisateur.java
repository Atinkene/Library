import java.util.ArrayList;
/*
    nom: Le nom de l'utilisateur.
    numeroIdentification: Le numéro d'identification unique de l'utilisateur.
    isCotise: Un indicateur booléen qui indique si l'utilisateur est cotisé ou non.
    livresEmpruntes: Une liste (ArrayList) des livres actuellement empruntés par l'utilisateur.
    nombreLivresEmpruntes: Le nombre total de livres empruntés par l'utilisateur.
 */
public class Utilisateur {
    private String nom;
    private int numeroIdentification;
    private boolean isCotise;
    private ArrayList<Livre> livresEmpruntes;
    private int nombreLivresEmpruntes=0;

    /*
        Le constructeur Utilisateur initialise les attributs de l'utilisateur avec les valeurs passées en paramètre 
        lors de sa création, et initialise la liste livresEmpruntes pour stocker les livres empruntés par cet utilisateur.
     */
    public Utilisateur(String nom, int numeroIdentification, boolean isCotise) {
        this.nom = nom;
        this.numeroIdentification = numeroIdentification;
        this.isCotise = isCotise;
        this.livresEmpruntes = new ArrayList<>();
    }
    /*
        La fonction emprunterLivre permet à l'utilisateur d'emprunter un livre. Elle effectue plusieurs vérifications :
            Vérifie si l'utilisateur a déjà emprunté le nombre maximum de livres autorisés (limite de 3 livres).
            Vérifie si tous les exemplaires du livre sont déjà empruntés.
            Vérifie si l'utilisateur a déjà emprunté ce livre.
    Si les conditions sont remplies, le livre est ajouté à la liste livresEmpruntes, le compteur nombreEmprunt du livre est incrémenté, et le nombre total de livres empruntés par l'utilisateur est également incrémenté.
     */
    public boolean emprunterLivre(Livre livre) {
        int nombreEmprunt=livre.getnombreEmprunte();
        int nombreExemplaire=livre.getNombreExemplaire();
        if (livresEmpruntes.size() >= 3) {
            System.out.println("Limite de livres empruntés atteinte pour cet utilisateur.");
            return false;
        }
        if (nombreEmprunt==nombreExemplaire) {
            System.out.println("Désolé, tous les exemplaires de ce livre ont été empruntés");
            return false;
        }
        if (livresEmpruntes.contains(livre)) {
            System.out.println("Vous disposez déjà de ce livre");
            return false;
        }
        livresEmpruntes.add(livre);
        nombreEmprunt++;
        livre.setnombreEmprunte(nombreEmprunt);
        System.out.println("Livre emprunté avec succès: " + livre);
        nombreLivresEmpruntes++;
        return true;
        
    }
    /*
        Cette méthode retournerLivre permet à l'utilisateur de retourner un livre emprunté. 
        Le livre est retiré de la liste livresEmpruntes, le compteur nombreEmprunt du livre est décrémenté, et le nombre total de livres empruntés par l'utilisateur est également décrémenté.
     */
    public void retournerLivre(Livre livre) {
        livresEmpruntes.remove(livre);
        int nombreEmprunt=livre.getnombreEmprunte();
        nombreEmprunt--;
        livre.setnombreEmprunte(nombreEmprunt);
        nombreLivresEmpruntes--;
    }
    //La methode afficherLivresEmpruntes affiche les livres empruntés par l'utilisateur
    public void afficherLivresEmpruntes() {
        for (Livre livre : livresEmpruntes) {
            System.out.println(livre);
        }
    }
    /*
        Les méthodes getNombreLivresEmpruntes(), getNumeroIdentification(), isCotise(), et getNom() permettent
         d'accéder aux attributs de l'utilisateur depuis l'extérieur de la classe.
     */
    public int getNombreLivresEmpruntes() {
        return nombreLivresEmpruntes;
    }

    public int getNumeroIdentification() {
        return numeroIdentification;
    }
    
    public boolean isCotise() {
        return isCotise;
    }

    public String getNom() {
        return nom;
    }

    //La méthode toString retourne une représentation textuelle de l'objet Utilisateur, affichant tous ses attributs.
    @Override
    public String toString() {
        return "Utilisateur{" +
                "nom='" + nom + '\'' +
                ", numeroIdentification=" + numeroIdentification +
                ", isCotise=" + isCotise +
                ", nombreLivresEmpruntes=" + nombreLivresEmpruntes +
                '}';
    }
}