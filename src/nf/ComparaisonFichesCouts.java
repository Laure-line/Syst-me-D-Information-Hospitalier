package nf;

public class ComparaisonFichesCouts implements ComparaisonFiches {
// la fonction 'comparer' doit retourner :
//    <0  si fiche1 < fiche2
//     0  si fiche1 == fiche2
//    >0  si fiche1 > fiche2
    public int comparer(FicheDeSoins fiche1, FicheDeSoins fiche2) {
        Double c1 = new Double(fiche1.coutTotal());
        Double c2 = new Double(fiche2.coutTotal());
        return c1.compareTo(c2);
    }

    public ComparaisonFichesCouts() {
    }

}
