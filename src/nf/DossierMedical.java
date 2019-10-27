package nf;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DossierMedical {

    private List<DossierPatientNF> dossierPatient;
    private List<Personnel> lpersonnel;
    private List<FicheDeSoins> fiches;

    public DossierMedical() {
        fiches = new ArrayList<FicheDeSoins>();  // liste vide
        dossierPatient = new ArrayList<DossierPatientNF>();
        lpersonnel = new ArrayList<Personnel>();
    }

    public List<DossierPatientNF> getDossierPatient() {
        return dossierPatient;
    }

    public List<Personnel> getLpersonnel() {
        return lpersonnel;
    }

    public List<FicheDeSoins> getFiches() {
        return fiches;
    }

    public void ajouterFiche(FicheDeSoins fiche) {
        fiches.add(fiche);
    }

    public void ajouterPatient(DossierPatientNF dpPatient) {
        dossierPatient.add(dpPatient);
    }

    public double coutPatient(Patient p) {
        double cout = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (p.equals(f.getPatient())) {
                cout += f.coutTotal();
            }
        }
        return cout;
    }

    public double coutMedecin(Medecin m) {
        double cout = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (m.equals(f.getMedecin())) {
                cout += f.coutTotal();
            }
        }
        return cout;
    }

    public double coutSpecialite(String specialite) {
        double cout = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (specialite.equals(f.getMedecin().getSpecialite())) {
                cout += f.coutTotal();
            }
        }
        return cout;
    }

    public int nombreFichesIntervalle(Date d1, Date d2) {
        int n = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            Date d = f.getDate();
            if (d.compareTo(d1) >= 0 && d.compareTo(d2) <= 0) {
                n++;
            }
        }
        return n;
    }

    public Vector<FicheDeSoins> trierDates() {
        Vector<FicheDeSoins> copieFiches = new Vector<FicheDeSoins>(fiches);
        Vector<FicheDeSoins> res = new Vector<FicheDeSoins>();
        while (!copieFiches.isEmpty()) {
            // on cherche la fiche de soins de date minimale :
            int imin = 0;
            FicheDeSoins f1 = copieFiches.get(imin);
            for (int i = 1; i < copieFiches.size(); i++) {
                FicheDeSoins f2 = copieFiches.get(i);
                if (f2.getDate().compareTo(f1.getDate()) < 0) {
                    imin = i;
                    f1 = f2;
                }
            }
            res.add(f1);
            // on affiche la fiche de soins trouvee :
            //f1.afficher();
            //System.out.println("------------------------");
            //on la supprime de la liste :
            copieFiches.remove(imin);
        }
        return res;
    }

// tri generique :
    public Vector<FicheDeSoins> trier(ComparaisonFiches c) {
        String sol = "";
        Vector<FicheDeSoins> copieFiches = new Vector<FicheDeSoins>(fiches);
        Vector<FicheDeSoins> res = new Vector<FicheDeSoins>();
        while (!copieFiches.isEmpty()) {
            // on cherche la fiche de soins minimale :
            int imin = 0;
            FicheDeSoins f1 = copieFiches.get(imin);
            for (int i = 1; i < copieFiches.size(); i++) {
                FicheDeSoins f2 = copieFiches.get(i);
                if (c.comparer(f2, f1) < 0) {
                    imin = i;
                    f1 = f2;
                }
            }
            res.add(f1);
            // on affiche la fiche de soins trouvee :
            sol += f1.toStringFS() + "\n";
            //System.out.println("------------------------");
            //on la supprime de la liste :
            copieFiches.remove(imin);
        }
        return res;
    }

    public String afficher() {
        String res = "";
        res += "Dossier medical informatise :";
        res += "-----------------------------";
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            f.toStringFS();
            // pour separer les fiches de soins :
            res += "--------------------------------------";
        }
        return res;
    }

    public void afficherListePatients(Medecin m) {
        System.out.println("> liste des patients du " + m.toString() + " :");
        Vector<Patient> liste = new Vector<Patient>();
        // 'liste' contient tous les patients deja affiches
        // --> ceci permet de ne pas reafficher un patient deja affiche
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (m.equals(f.getMedecin())) {
                Patient p = f.getPatient();
                if (!liste.contains(p)) {
                    System.out.println(" - " + p.toString());
                    liste.add(p);
                }
            }
        }
    }

    public void afficherListeMedecins(SecuriteSociale numsecu) {
        //On affiche la liste des patients selon le médecin

    }
}
