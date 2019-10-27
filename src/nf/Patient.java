package nf;

public class Patient {

    private String nom;
    private String prenom;
    //public DossierPatient ;
    private Sexe sexe;
    private Adresse adresse;
    private SecuriteSociale SS;
    private Date naissance;

    public Patient(String nom, String prenom, Date naissance, Adresse adresse, String SS, Sexe sexe) {
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = naissance;
        this.adresse = adresse;
        this.sexe = sexe;
        this.SS = new SecuriteSociale(SS);
    }

    public Patient(SecuriteSociale SS) {
        this.SS = SS;
    }
    
    public Patient(Patient p){
        this.nom = p.getNom();
        this.prenom = p.getPrenom();
        this.naissance = p.getNaissance();
        this.adresse = p.getAdresse();
        this.sexe = p.getSexe();
        this.SS = p.getSS();
    }

    public Date getNaissance() {
        return naissance;
    }

    public String toString() {
        return prenom + " " + nom + " ";
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Sexe getSexe() {
        return this.sexe;
    }

    public Adresse getAdresse() {
        return this.adresse;
    }

    public SecuriteSociale getSS() {
        return this.SS;
    }

    public boolean equals(Object o) {
        if (o instanceof Patient) {
            Patient p = (Patient) o;
            return nom.equals(p.nom) && prenom.equals(p.prenom);
        } else {
            return false;
        }
    }
}
