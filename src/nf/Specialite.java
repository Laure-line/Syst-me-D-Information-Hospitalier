package nf;

public enum Specialite {

    ANES("ANESTHESIOLOGIE", 0, "ANES"),
    CARD("CARDIOLOGIE", 1, "CARD"),
    DERM("DERMATOLOGIE", 2, "DERM"),
    GER("GERONTOLOGIE", 3, "GER"),
    GYN("GYNECOLOGIE", 4, "GYN"),
    HEM("HEMATOLOGIE", 5, "HEM"),
    NEUR("NEUROLOGIE", 6, "NEUR"),
    PED("PEDIATRIE", 7, "PED"),
    RAD("RADIOLOGIE", 8, "RAD"),
    URO("UROLOGIE", 9, "URO"),
    ONCO("ONCOLOGIE", 10, "ONCO"),
    ORL("ORL", 11, "ORL"),
    AUTRE("AUTRE", 12, "AUTRE");

    private String spe;
    private int indice;
    private String nom;

    private Specialite(String specialite, int indice, String nom) {
        this.spe = specialite;
        this.indice = indice;
        this.nom = nom;
    }

    public String getSpe() {
        return spe;
    }

    public int getIndice() {
        return indice;
    }
    
    public String getNom() {
        return nom;
    }

}
