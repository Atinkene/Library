public class Livre {
    private String titre;
    private String auteur;
    private int anneePublication;
    private String ISBN;
    private int nombreExemplaire;
    private int nombreEmprunte;

    /*
        Le constructeur Livre initialise les attributs du livre lors de sa création en prenant en paramètre le titre, 
        l'auteur, l'année de publication, l'ISBN et le nombre d'exemplaires disponibles. L'attribut nombreEmprunte est initialisé à 0 
        car aucun exemplaire n'est emprunté initialement.
     */

    public Livre(String titre, String auteur, int anneePublication, String ISBN, int nombreExemplaire) {
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.ISBN = ISBN;
        this.nombreExemplaire = nombreExemplaire;
        this.nombreEmprunte = 0;
    }
    // La  méthode modifierLivre permet de modifier les détails d'un livre existant en mettant à jour ses attributs avec de nouvelles valeurs.
    public void modifierLivre(String titre, String auteur, int anneePublication, String ISBN, int nombreExemplaire, int nombreEmprunte) {
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.ISBN = ISBN;
        this.nombreExemplaire = nombreExemplaire;
        this.nombreEmprunte = nombreEmprunte;
    }

    //Les Accesseur(getters) permettent d'accéder aux valeurs des attributs du livre depuis l'extérieur de la classe.
    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getISBN() {
        return ISBN;
    }
    
    public int getNombreExemplaire() {
        return nombreExemplaire;
    }

    public int getnombreEmprunte() {
        return nombreEmprunte;
    }

    //Les mutateur(setters) permettent  de modifier les valeurs des attributs nombreExemplaire et nombreEmprunte depuis l'extérieur de la classe.
    public void setNombreExemplaire(int nombreExemplaire) {
        this.nombreExemplaire = nombreExemplaire;
    }

    
    public void setnombreEmprunte(int nombreEmprunte) {
        this.nombreEmprunte = nombreEmprunte;
    }

    //La méthode toString est utilisée pour fournir une représentation sous forme de chaîne de caractères de l'objet Livre, affichant tous ses attributs.
    @Override
    public String toString() {
        return "Livre{" +
                "titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", anneePublication=" + anneePublication +
                ", ISBN='" + ISBN + '\'' +
                ", nombreExemplaire=" + nombreExemplaire +
                ", nombreEmprunte=" + nombreEmprunte +
                '}';
    }
}
