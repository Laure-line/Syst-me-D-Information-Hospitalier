package nf;

public class SecretaireMed extends CorpsMed {
    
    public SecretaireMed() {
        super();
    }

    public SecretaireMed (String n, String p, String i, String m) {
        super(n, p, i, m);
     
    }

    public SecretaireMed(String nom, String prenom, String id) {
        super(nom, prenom, id);
    }

    public String toStringSecretaireMed() {
        return super.toString();
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getId() {
        return id;
    }
    
    

}
