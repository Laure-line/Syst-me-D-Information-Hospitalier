package nf;

public class SecretaireAdmin extends CorpsAdmin {

    public SecretaireAdmin() {
        super();
    }

    public SecretaireAdmin (String n, String p, String i, String m) {
        super(n, p, i, m);
     
    }

    public SecretaireAdmin(String nom, String prenom, String id) {
        super(nom, prenom, id);
    }

    public String toStringSecretaireAdmin() {
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
