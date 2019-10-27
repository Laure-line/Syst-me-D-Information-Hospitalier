package nf;

public enum Sexe {

    M(1,"M"),
    F(2,"F");
    int indice;
    String type;

    Sexe(int indice, String type) {
        this.indice = indice;
        this.type=type;
    }

}
