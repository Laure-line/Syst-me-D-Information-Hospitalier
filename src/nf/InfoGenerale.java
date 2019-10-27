/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JComboBox;

/**
 *
 * @author prouvosl
 */
public class InfoGenerale {

    LecturePatientXML lecturePatients;
    List<FicheDeSoins> ficheDeSoins;
    DossierMedical dm;
    LectureFicheDeSoins lecture;
    List<DossierPatientNF> patients;
    ArrayList<Medecin> lm1;
    ArrayList<Medecin> lm2;

    public InfoGenerale() {
        lm1 = new ArrayList<Medecin>();
        lm2 = new ArrayList<Medecin>();
        lecture = new LectureFicheDeSoins("FichesDeSoins.xml");
        ficheDeSoins = new ArrayList<FicheDeSoins>();
        ficheDeSoins = lecture.getFicheDeSoins();
        lecturePatients = new LecturePatientXML("Patients.xml");
        dm = lecturePatients.getDossier();
        for (int i = 0; i < ficheDeSoins.size(); i++) {
            dm.ajouterFiche(ficheDeSoins.get(i));
        }
    }

    public String getCoutSpe() {
        String res = "";
        String s = "ANES";
        res += Specialite.ANES.getSpe() + " : " + dm.coutSpecialite(s) + "\n";
        s = "CARD";
        res += Specialite.CARD.getSpe() + " : " + dm.coutSpecialite(s) + "\n";
        s = "DERM";
        res += Specialite.DERM.getSpe() + " : " + dm.coutSpecialite(s) + "\n";
        s = "GER";
        res += Specialite.GER.getSpe() + " : " + dm.coutSpecialite(s) + "\n";
        s = "GYN";
        res += Specialite.GYN.getSpe() + " : " + dm.coutSpecialite(s) + "\n";
        s = "HEM";
        res += Specialite.HEM.getSpe() + " : " + dm.coutSpecialite(s) + "\n";
        s = "NEUR";
        res += Specialite.NEUR.getSpe() + " : " + dm.coutSpecialite(s) + "\n";
        s = "PED";
        res += Specialite.PED.getSpe() + " : " + dm.coutSpecialite(s) + "\n";
        s = "RAD";
        res += Specialite.RAD.getSpe() + " : " + dm.coutSpecialite(s) + "\n";
        s = "URO";
        res += Specialite.URO.getSpe() + " : " + dm.coutSpecialite(s) + "\n";
        s = "ONCO";
        res += Specialite.ONCO.getSpe() + " : " + dm.coutSpecialite(s) + "\n";
        s = "ORL";
        res += Specialite.ORL.getSpe() + " : " + dm.coutSpecialite(s) + "\n";
        s = "AUTRE";
        res += Specialite.AUTRE.getSpe() + " : " + dm.coutSpecialite(s) + "\n";
        return res;
    }

    public String getFS() {
        String res = "";
        for (int i = 0; i < ficheDeSoins.size(); i++) {
            FicheDeSoins f = ficheDeSoins.get(i);
            res += f.toStringFS() + '\n';
        }
        return res;
    }

    public String getFSDeuxDates(Date d1, Date d2) {
        Date d;
        String res = "";
        for (int i = 0; i < ficheDeSoins.size(); i++) {
            d = new Date(ficheDeSoins.get(i).getDate().getAnnee(), ficheDeSoins.get(i).getDate().getMois(), ficheDeSoins.get(i).getDate().getJour());
            if (d.compareTo(d1) > 0 || d.compareTo(d1) == 0) {
                if (d.compareTo(d2) < 0 || d.compareTo(d2) == 0) {
                    res += ficheDeSoins.get(i).toStringFS() + '\n';
                }
            }
        }
        res += "Fin des résultats";
        return res;
    }

    public String getPatients() {
        patients = lecturePatients.getDossier().getDossierPatient();
        String res = "";
        for (int i = 0; i < patients.size(); i++) {
            Patient p = patients.get(i).getPatient();
            String patient = p.toString();
            res += patient + '\n';
        }
        return res;
    }

    public ArrayList<Medecin> avoirMedecinFSXML(String fichier) {
        lecture = new LectureFicheDeSoins(fichier);
        String res = "";
        double cout = 0.;
        for (int i = 0; i < ficheDeSoins.size(); i++) {
            lm1.add(ficheDeSoins.get(i).getMedecin());
        }
        lm2.add(lm1.get(0));
        for (int i = 0; i < lm1.size(); i++) {
            for (int j = 0; j < lm2.size(); j++) {
                if (!lm2.contains(lm1.get(i))) {
                    lm2.add(lm1.get(i));

                }
            }
        }
        return lm2;
    }

    public String getMedecins() {
        String res = "";
        double cout = 0.;
        for (int j = 0; j < lm2.size(); j++) {
            res += lm2.get(j).toString();
            cout = 0.;
            for (int i = 0; i < ficheDeSoins.size(); i++) {
                if (ficheDeSoins.get(i).getMedecin().equals(lm2.get(j))) {
                    cout += ficheDeSoins.get(i).coutTotal();
                }
            }
            res += "\n\tCout du medecin : " + cout + " €\n";
        }
        return res;
    }

    public String getFSPatient(Patient patient) {
        String res = "";
        for (int i = 0; i < ficheDeSoins.size(); i++) {
            if ((ficheDeSoins.get(i).getPatient().getSS().toString().equals(patient.getSS().toString()))) {
                FicheDeSoins f = ficheDeSoins.get(i);
                res += f.toStringFS() + '\n';
            }
        }
        res += "Fin des résultats";
        return res;
    }

    public String coutTri() {
        String res = "";
        ComparaisonFichesCouts c = new ComparaisonFichesCouts();
        Vector<FicheDeSoins> vfs = new Vector<FicheDeSoins>();
        vfs = dm.trier(c);
        for (int i = 0; i < vfs.size(); i++) {
            FicheDeSoins f = vfs.get(i);
            res += f.toStringFS() + '\n';
        }
        return res;
    }

    public String getPatientsDeMedecin(String medecin) {
        String values[] = medecin.split(" ");
        String res = "";
        for (int j = 0; j < ficheDeSoins.size(); j++) {
            if ((ficheDeSoins.get(j).getMedecin().toString().equals(medecin))) {
                for (int i = 0; i < dm.getDossierPatient().size(); i++) {
                    if (dm.getDossierPatient().get(i).getPatient().getSS().toString().equals(ficheDeSoins.get(j).getPatient().getSS().toString())) {
                        res += ficheDeSoins.get(j).toStringFS() + "\n";
                    }
                }
            } else {
                res += "";
            }
        }
        res += "Fin des résultats";
        return res;
    }

}
