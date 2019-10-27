/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf;

import java.io.*;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.*;

/**
 *
 * @author prouvosl
 */
public class PatientXMLTEST {

    private static Element racine = new Element("patients");

    private static org.jdom2.Document document = new Document(racine);

    public static void main(String[] args) {
        Adresse adresses = new Adresse("10 Rue Lafaillette", 38400, "St Martin d'Hères");
        SecuriteSociale ss1 = new SecuriteSociale(Sexe.M, 90, 05, 38, 22, 26, 23);
        Date ddn = new Date(01, 05, 1990);
        Patient p = new Patient("Track", "Pat", ddn, adresses, ss1.toString(), Sexe.M);
        
        Element patient = new Element("patient");
        racine.addContent(patient);

        Element nom = new Element("nom");
        nom.setText(p.getNom());
        patient.addContent(nom);

        Element prenom = new Element("prenom");
        prenom.setText(p.getPrenom());
        patient.addContent(prenom);

        Element dateDeNaissance = new Element("dateDeNaissance");
        dateDeNaissance.setText(Integer.toString(p.getNaissance().getJour())
                + "-" + Integer.toString(p.getNaissance().getMois()) + "-"
                + Integer.toString(p.getNaissance().getAnnee()));
        patient.addContent(dateDeNaissance);

        Element adresse = new Element("adresse");
        patient.addContent(adresse);

        Element rue = new Element("rue");
        rue.setText(p.getAdresse().getRue());
        adresse.addContent(rue);

        Element codePostal = new Element("codePostal");
        codePostal.setText(Integer.toString(p.getAdresse().getCodePostal()));
        adresse.addContent(codePostal);

        Element ville = new Element("ville");
        ville.setText(p.getAdresse().getVille());
        adresse.addContent(ville);

        Element ss = new Element("nSecuriteSociale");
        ss.setText(p.getSS().toString());
        patient.addContent(ss);

        Element sexe = new Element("sexe");
        sexe.setText(p.getSexe().type);
        patient.addContent(sexe);

        enregistre("Patients.xml");

    }

    static void affiche() {
        try {
            //On utilise ici un affichage classique avec getPrettyFormat()
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(document, System.out);
        } catch (java.io.IOException e) {
        }
    }

    static void enregistre(String fichier) {
        try {
            //On utilise ici un affichage classique avec getPrettyFormat()
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            //Remarquez qu'il suffit simplement de créer une instance de FileOutputStream
            //avec en argument le nom du fichier pour effectuer la sérialisation.
            sortie.output(document, new FileOutputStream("src/donnees/"+fichier));
        } catch (java.io.IOException e) {
        }
    }
}
