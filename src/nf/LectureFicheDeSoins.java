/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author Maya Robichon
 */
public class LectureFicheDeSoins {

    private String nomFichier;

    public LectureFicheDeSoins(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public List<FicheDeSoins> getFicheDeSoins() {
        String donneesCourantes = "";

        List<FicheDeSoins> ficheDeSoins = null;
        FicheDeSoins fsCourant = null;
        Medecin medecinCourant = null;
        Patient patientCourant = null;
        String nomCourant = "";
        String prenomCourant = "";
        Specialite specialiteCourante = null;
        List<Acte> actes = new Vector<Acte>();
        Date date = null;
        SecuriteSociale ssCourante = null;
        Code codeCourant = null;
        int coefCourant = 0;
        boolean temp = false;

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
                        if (parser.getLocalName().equals("fichesDeSoins")) {
                            ficheDeSoins = new ArrayList<FicheDeSoins>();
                        }
                        if (parser.getLocalName().equals("fichesDeSoins")) {
                            temp=true;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        if (parser.getLocalName().equals("ficheDeSoins")) {
                            patientCourant = new Patient(ssCourante);
                            fsCourant = new FicheDeSoins(patientCourant, medecinCourant, date);
                            for (int i = 0; i < actes.size(); i++) {
                                fsCourant.ajouterActe(actes.get(i));
                            }
                            ficheDeSoins.add(fsCourant);
                            temp = false;
                            actes = new Vector<Acte>();
                        }
                        if (parser.getLocalName().equals("nSS")) {
                            ssCourante = new SecuriteSociale(donneesCourantes.toString());
                            temp=true;
                        }
                        if (parser.getLocalName().equals("medecin")) {
                            medecinCourant = new Medecin(nomCourant, prenomCourant, specialiteCourante);
                        }
                        if (parser.getLocalName().equals("nom")) {
                            nomCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("prenom")) {
                            prenomCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("specialite")) {
                            specialiteCourante = Specialite.valueOf(donneesCourantes);
                        }
                        if (parser.getLocalName().equals("date")) {
                            int annee = Integer.parseInt(donneesCourantes.substring(0, donneesCourantes.indexOf('-')));
                            int mois = Integer.parseInt(donneesCourantes.substring(donneesCourantes.indexOf('-') + 1, donneesCourantes.lastIndexOf('-')));
                            int jour = Integer.parseInt(donneesCourantes.substring(donneesCourantes.lastIndexOf('-') + 1, donneesCourantes.length()));

                            date = new Date(jour, mois, annee);
                        }
                        if (parser.getLocalName().equals("acte") && temp == true) {
                            actes.add(new Acte(codeCourant, coefCourant));
                        }
                        if (parser.getLocalName().equals("code") && temp == true) {
                            codeCourant = getCode(donneesCourantes);
                        }
                        if (parser.getLocalName().equals("coef") && temp == true) {
                            coefCourant = Integer.parseInt(donneesCourantes);
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
        return ficheDeSoins;

    }

    private static Code getCode(String code) {
        if (code.equals("CS")) {
            return Code.CS;
        }
        if (code.equals("CSC")) {
            return Code.CSC;
        }
        if (code.equals("FP")) {
            return Code.FP;
        }
        if (code.equals("KC")) {
            return Code.KC;
        }
        if (code.equals("KE")) {
            return Code.KE;
        }
        if (code.equals("K")) {
            return Code.K;
        }
        if (code.equals("KFA")) {
            return Code.KFA;
        }
        if (code.equals("KFB")) {
            return Code.KFB;
        }
        if (code.equals("ORT")) {
            return Code.ORT;
        }
        if (code.equals("PRO")) {
            return Code.PRO;
        }
        // probleme : code inconnu
        return null;
    }

}
