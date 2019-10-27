package nf;

public class ComparaisonFichesDates implements ComparaisonFiches {
// la fonction 'comparer' doit retourner :
//    <0  si fiche1 < fiche2
//     0  si fiche1 == fiche2
//    >0  si fiche1 > fiche2
    
    public int comparer(FicheDeSoins fiche1, FicheDeSoins fiche2) {
        return fiche1.getDate().compareTo(fiche2.getDate());
    }

}
