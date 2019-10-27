package nf;

public abstract class CorpsAdmin extends Personnel {

    private static String type = "Administratif";

    public CorpsAdmin() {
        super();
    }

    public CorpsAdmin(String n, String p, String i, String m) {
        super(n, p, i, m);
    }

    public CorpsAdmin(String nom, String prenom, String id) {
        super(nom, prenom, id);
    }

    public String toStringCorpsAdmin() {
        return super.toString();
    }

    public String getType() {
        return type;
    }

}
