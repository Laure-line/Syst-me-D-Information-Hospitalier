package nf;

public abstract class CorpsMed extends Personnel {

    private static String type = "Medical";

    public CorpsMed() {
        super();
    }

    public CorpsMed(String n, String p, String i, String m) {
        super(n, p, i, m);
    }

    public CorpsMed(String nom, String prenom, String id) {
        super(nom, prenom, id);
    }

    public String toStringCorpsMed() {
        return super.toString();
    }

    public String getType() {
        return type;
    }
    
    
}
