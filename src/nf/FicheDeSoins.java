package nf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class FicheDeSoins {

    private Medecin medecin;
    private Patient patient;
    private Vector<Acte> actes; // contient des objets
    private Date date;
    private DossierPatientNF dossier;
    

    public FicheDeSoins(Patient patient, Medecin medecin, Date date) {
        this.patient = patient;
        this.medecin = medecin;
        this.date = date;
        actes = new Vector<Acte>();   // liste vide
    }

    public Patient getPatient() {
        return patient;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public Date getDate() {
        return date;
    }

    public Vector<Acte> getActes() {
        return actes;
    }

    public void ajouterActe(Acte acte) {
        actes.add(acte);
    }

    public void ajouterActe(Code code, int coefficient) {
        Acte acte = new Acte(code, coefficient);
        actes.add(acte);
    }

    public double coutTotal() {
        double total = 0;
        for (int i = 0; i < actes.size(); i++) {
            Acte a = actes.get(i);
            total += a.cout();
        }
        return total;
    }
    
    public String toStringFS(){
        String s ="";
        s += "Fiche de soins du " + date.toString() + '\n';
        s += "- Medecin : " + medecin.toString() + '\n';
        s += "- Numéro de Sécurité Sociale : " + patient.getSS().toString() + '\n';
        s += "- Actes medicaux :" + '\n';
        for (int i = 0; i < actes.size(); i++) {
            Acte a = actes.get(i);
            s+= "    > " + a.toString() + '\n';
        }
        s+= "- Cout de la fiche de soins : " + this.coutTotal() + " €\n";
        return s;
    }
    
    public String toStringFSSansSS(){
        String s ="";
        s += "Fiche de soins du " + date.toString() + '\n';
        s += "- Medecin : " + medecin.toString() + '\n';
        s += "- Actes medicaux :" + '\n';
        for (int i = 0; i < actes.size(); i++) {
            Acte a = actes.get(i);
            s+= "    > " + a.toString() + '\n';
        }
        s+= "- Cout de la fiche de soins : " + this.coutTotal() + " €\n";
        return s;
    }

    public String toString() {
        String s ="";
        s += "Fiche de soins du " + date.toString() + '\n';
        s += "- Medecin : " + medecin.toString() + '\n';
        s += "- Patient : " + patient.toString() + '\n';
        s += "- Actes medicaux :" + '\n';
        for (int i = 0; i < actes.size(); i++) {
            Acte a = actes.get(i);
            s+= "    > " + a.toString() + '\n';
        }
        s+= "- Cout de la fiche de soins : " + this.coutTotal() + " €\n";
        return s;
    }
   
    
}
