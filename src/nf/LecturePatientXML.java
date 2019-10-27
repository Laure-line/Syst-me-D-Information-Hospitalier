/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author prouvosl
 */
public class LecturePatientXML {

    /// nom du document XML a analyser
    private String nomFichier;
    private final static String repBase = "src/donnees/";

    // 'nomFichier' est le nom d'un fichier XML se trouvant dans le repertoire 'repBase' a lire :
    public LecturePatientXML(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public DossierMedical getDossier() {

        String donneesCourantes = "";
        DossierMedical dossierCourant = null;
        DossierPatientNF dpCourant = null;
        Patient patientCourant = null;
        String nomCourant = "";
        String prenomCourant = "";
        Date date = null;
        Adresse adresseCourante = null;
        String rueCourante = "";
        String codePostalCourant = "";
        String villeCourante = "";
        String nSSCourante = null;
        Sexe sexeCourant = null;

        // analyser le fichier par StAX
        try {
            // instanciation du parser
            InputStream in = new FileInputStream(nomFichier);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader parser = factory.createXMLStreamReader(in);

            // lecture des evenements
            for (int event = parser.next(); event != XMLStreamConstants.END_DOCUMENT; event = parser.next()) {
                // traitement selon l'evenement
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        if (parser.getLocalName().equals("patients")) {
                            dossierCourant = new DossierMedical();
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        if (parser.getLocalName().equals("patient")) {
                            patientCourant = new Patient(nomCourant, prenomCourant, date, adresseCourante, nSSCourante, sexeCourant);
                            dpCourant = new DossierPatientNF(patientCourant);
                            dossierCourant.ajouterPatient(dpCourant);
                        }
                        if (parser.getLocalName().equals("nom")) {
                            nomCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("prenom")) {
                            prenomCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("dateDeNaissance")) {
                            int annee = Integer.parseInt(donneesCourantes.substring(0, donneesCourantes.indexOf('-')));
                            int mois = Integer.parseInt(donneesCourantes.substring(donneesCourantes.indexOf('-') + 1, donneesCourantes.lastIndexOf('-')));
                            int jour = Integer.parseInt(donneesCourantes.substring(donneesCourantes.lastIndexOf('-') + 1, donneesCourantes.length()));

                            date = new Date(jour, mois, annee);
                        }
                        if (parser.getLocalName().equals("adresse")) {
                            adresseCourante = new Adresse(rueCourante, Integer.parseInt(codePostalCourant), villeCourante);
                        }
                        if (parser.getLocalName().equals("rue")) {
                            rueCourante = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("codePostal")) {
                            codePostalCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("ville")) {
                            villeCourante = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("nSecuriteSociale")) {
                            nSSCourante = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("sexe")) {
                            if (donneesCourantes.equals("F")) {
                                sexeCourant = Sexe.F;
                            } else {
                                sexeCourant = Sexe.M;
                            }
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        donneesCourantes = parser.getText();
                        break;
                } // end switch
            } // end while
            parser.close();
        } catch (XMLStreamException ex) {
            System.out.println("Exception de type 'XMLStreamException' lors de la lecture du fichier : " + nomFichier);
            System.out.println("Details :");
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println("Exception de type 'IOException' lors de la lecture du fichier : " + nomFichier);
            System.out.println("Verifier le chemin.");
            System.out.println(ex.getMessage());
        }

        return dossierCourant;
    }
    
    public DossierMedical modifierPatient(Patient p) {

        String donneesCourantes = "";
        DossierMedical dossierCourant = null;
        DossierPatientNF dpCourant = null;
        Patient patientCourant = null;
        String nomCourant = "";
        String prenomCourant = "";
        Date date = null;
        Adresse adresseCourante = null;
        String rueCourante = "";
        String codePostalCourant = "";
        String villeCourante = "";
        String nSSCourante = null;
        Sexe sexeCourant = null;

        // analyser le fichier par StAX
        try {
            // instanciation du parser
            InputStream in = new FileInputStream(nomFichier);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader parser = factory.createXMLStreamReader(in);

            // lecture des evenements
            for (int event = parser.next(); event != XMLStreamConstants.END_DOCUMENT; event = parser.next()) {
                // traitement selon l'evenement
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        if (parser.getLocalName().equals("patients")) {
                            dossierCourant = new DossierMedical();
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        if (parser.getLocalName().equals("nSecuriteSociale")) {
                            if(donneesCourantes.equals(p.getSS().toString())){
                                
                            }
                            
                        }
                        
                    case XMLStreamConstants.CHARACTERS:
                        donneesCourantes = parser.getText();
                        break;
                } // end switch
            } // end while
            parser.close();
        } catch (XMLStreamException ex) {
            System.out.println("Exception de type 'XMLStreamException' lors de la lecture du fichier : " + nomFichier);
            System.out.println("Details :");
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println("Exception de type 'IOException' lors de la lecture du fichier : " + nomFichier);
            System.out.println("Verifier le chemin.");
            System.out.println(ex.getMessage());
        }

        return dossierCourant;
    }
}
