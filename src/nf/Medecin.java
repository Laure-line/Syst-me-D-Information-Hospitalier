package nf;

public class Medecin extends CorpsMed {

    private String nom;
    private String prenom;
    private String idMed;
    private String mp;
    private Specialite specialite;

    public Medecin(String nom, String prenom, Specialite specialite, String idMed, String mp) {
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        this.idMed = idMed;
        this.mp = mp;
    }

    public Medecin(String nom, String prenom, Specialite specialite) {
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
    }

    public Medecin(String nom, String prenom, String idMed) {
        this.nom = nom;
        this.prenom = prenom;
        this.idMed = idMed;
    }

    public Medecin(String nom, String prenom, String idMed, Specialite specialite) {
        this.nom = nom;
        this.prenom = prenom;
        this.idMed = idMed;
        this.specialite = specialite;
    }
    
    public Medecin (Medecin m){
        this.nom = m.getNom();
        this.prenom = m.getPrenom();
        //this.specialite = m.getSpecialite();
        this.idMed = m.getId();
        this.mp = m.getMp();
    }

    public String getSpecialite() {
        return specialite.getNom();
    }

    public String toString() {
        return "Dr " + prenom + " " + nom + ", " + specialite;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Medecin) {
            Medecin p = (Medecin) o;
            return nom.equals(p.nom) && prenom.equals(p.prenom);
        } else {
            return false;
        }
    }

    public String getId() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.idMed;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

}
