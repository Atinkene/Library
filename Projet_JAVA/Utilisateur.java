import java.util.ArrayList;

public class Utilisateur {
    private String nom;
    private int numeroIdentification;
    private boolean isCotise;
    private ArrayList<Livre> livresEmpruntes;
    private int nombreLivresEmpruntes;

    public Utilisateur(String nom, int numeroIdentification, boolean isCotise) {
        this.nom = nom;
        this.numeroIdentification = numeroIdentification;
        this.isCotise = isCotise;
        this.livresEmpruntes = new ArrayList<>();
    }

    public void emprunterLivre(Livre livre) {
        if (livresEmpruntes.size() >= 3) {
            System.out.println("Limite de livres empruntés atteinte pour cet utilisateur.");
            return;
        }

        livresEmpruntes.add(livre);
        System.out.println("Livre emprunté avec succès: " + livre);

        nombreLivresEmpruntes++;
    }

    public void retournerLivre(Livre livre) {
        livresEmpruntes.remove(livre);
    }

    public void afficherLivresEmpruntes() {
        for (Livre livre : livresEmpruntes) {
            System.out.println(livre);
        }
    }

    public int getNombreLivresEmpruntes() {
        return nombreLivresEmpruntes;
    }

    public boolean isCotise() {
        return isCotise;
    }

    public String getNom() {
        return nom;
    }

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
