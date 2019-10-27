package nf;

// Cette enumeration fait intervenir des valeurs qui possedent des
// attributs ('libelle' et 'cout') qui sont initialises par un appel du
// constructeur (arguments entre parentheses apres le nom de chaque valeur).
// Par exemple, la valeur Code.FP a un attribut 'libelle' contenant la chaine
// de caracteres "forfait pediatrique" et un attribut 'cout' ayant la valeur 5.0
public enum Code {
// valeurs de l'�num :

    CS("Consultation au cabinet", 23.0, "CS", "1"),
    CSC("Consultation cardiologie", 45.73, "CSC", "2"),
    FP("Forfait pediatrique", 5.0, "FP", "3"),
    KC("Actes de chirurgie et de specialite", 2.09, "KC", "4"),
    KE("Actes d'echographie, de doppler", 1.89, "KE", "5"),
    K("Autres actes de specialite", 1.92, "K", "6"),
    KFA("Forfait A", 30.49, "KFA", "7"),
    KFB("Forfait B", 60.98, "KFB", "8"),
    ORT("Orthodontie", 2.15, "ORT", "9"),
    PRO("Prothese dentaire", 2.15, "PRO", "10");

// attributs de l'�num :
    private String libelle;
    private String code;
    private double cout;
    private String indice;

// constructeur :
    private Code(String libelle, double cout, String code, String indice) {
        this.libelle = libelle;
        this.cout = cout;
        this.code = code;
        this.indice = indice;
    }

// m�thodes :
    public String toString() {
        return super.toString() + " : " + libelle + ", Cout = " + cout + " €";
    }

// calcule le prix pour un coefficient donne :
    public double calculerCout(int coefficient) {
        return coefficient * cout;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getCode() {
        return code;
    }

    public double getCout() {
        return cout;
    }
    
    public Code getCode(String code) {
        if (code.equals("1"))
            return Code.CS;
        if (code.equals("2"))
            return Code.CSC;
        if (code.equals("3"))
            return Code.FP;
        if (code.equals("4"))
            return Code.KC;
        if (code.equals("5"))
            return Code.KE;
        if (code.equals("6"))
            return Code.K;
        if (code.equals("7"))
            return Code.KFA;
        if (code.equals("8"))
            return Code.KFB;
        if (code.equals("9"))
            return Code.ORT;
        if (code.equals("10"))
            return Code.PRO;
        // probleme : code inconnu
        return Code.PRO;            
    }

}
