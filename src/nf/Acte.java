package nf;

public class Acte {

    private int coef;
    private Code code;
    private String commentaire;

    public Acte(Code code, int coef) {
        this.code = code;
        this.coef = coef;
        commentaire = " ";
    }

    public Acte(Code code, int coef, String commentaire) {
        this.coef = coef;
        this.code = code;
        this.commentaire = commentaire;
    }

    public String toString() {
        String res = "";
        res += code.toString() + ", Coefficient : " + coef + "\n           Cout total de l'acte = " + this.cout() + " €\n";
        res += "           Observations eventuelles : " + commentaire;
        return res;
    }

    public double cout() {
        return code.calculerCout(coef);
    }

    public Code getCode() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.code;
    }

    public int getCoef() {
        return coef;
    }

}
