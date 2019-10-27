/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.jdom2.Element;

/**
 *
 * @author prouvosl
 */
public class GererPersonnelXML {

    static org.jdom2.Document document;
    static Element racine;

    public static boolean gererConnexion(String identifiant, String mdp) {
        boolean res = false;
        boolean temp = false;
        String donneesCourantes = "";        

        // analyser le fichier par StAX
        try {
            // instanciation du parser
            InputStream in = new FileInputStream("Personnel.xml");
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader parser = factory.createXMLStreamReader(in);

            // lecture des evenements
            for (int event = parser.next(); event != XMLStreamConstants.END_DOCUMENT; event = parser.next()) {

// traitement selon l'evenement
                switch (event) {
                    case XMLStreamConstants.END_ELEMENT:
                        if (temp == false) {
                            if (parser.getLocalName().equals("identifiant")) {
                                if (donneesCourantes.equals(identifiant)) {
                                    temp = true;
                                }
                            }
                        } else {
                            if (parser.getLocalName().equals("motDePasse")) {
                                if (donneesCourantes.equals(mdp)) {
                                    res = true;
                                }
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
            System.out.println("Exception de type 'XMLStreamException' lors de la lecture du fichier : " + "Personnel");
            System.out.println("Details :");
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println("Exception de type 'IOException' lors de la lecture du fichier : " + "Personnel");
            System.out.println("Verifier le chemin.");
            System.out.println(ex.getMessage());
        }
        return res;
    }

    public static Personnel persConnectee(String identifiant, String mdp) {
        boolean res = false;
        boolean temp = false;
        Personnel p = null;
        
        String idCourant = "";
        String nomCourant = "";
        String prenomCourant = "";
        Specialite specialiteCourante = null;
        String type = "";
        String donneesCourantes = "";

        // analyser le fichier par StAX
        try {
            // instanciation du parser
            InputStream in = new FileInputStream("Personnel.xml");
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader parser = factory.createXMLStreamReader(in);

            // lecture des evenements
            for (int event = parser.next(); event != XMLStreamConstants.END_DOCUMENT; event = parser.next()) {

// traitement selon l'evenement
                switch (event) {
                    case XMLStreamConstants.END_ELEMENT:
                        if (temp == false) {
                            if (parser.getLocalName().equals("identifiant")) {
                                if (donneesCourantes.equals(identifiant)) {
                                    idCourant = donneesCourantes;
                                    temp = true;
                                }
                            }
                        } else {
                            if (parser.getLocalName().equals("motDePasse")) {
                                if (donneesCourantes.equals(mdp)) {
                                    res = true;
                                }
                            }
                        }
                        if (res == true && temp == true) {
                            if (parser.getLocalName().equals("nom")) {
                                nomCourant = donneesCourantes;
                            }
                            if (parser.getLocalName().equals("prenom")) {
                                prenomCourant = donneesCourantes;
                            }
                            if (parser.getLocalName().equals("specialite")) {
                                specialiteCourante = Specialite.valueOf(donneesCourantes);
                            }
                            if (parser.getLocalName().equals("type")) {
                                type = donneesCourantes;
                                if (type.equals("Medical")){
                                    if (specialiteCourante != null){
                                        Medecin m = new Medecin(nomCourant, prenomCourant, idCourant, specialiteCourante);
                                        return m;
                                    } else {
                                        SecretaireMed sm = new SecretaireMed (nomCourant, prenomCourant, idCourant);
                                        return sm;
                                    }
                                }
                                else if (type.equals("Administratif")){
                                    SecretaireAdmin sa = new SecretaireAdmin (nomCourant, prenomCourant, idCourant);
                                    return sa;
                                }
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
            System.out.println("Exception de type 'XMLStreamException' lors de la lecture du fichier : " + "Personnel");
            System.out.println("Details :");
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println("Exception de type 'IOException' lors de la lecture du fichier : " + "Personnel");
            System.out.println("Verifier le chemin.");
            System.out.println(ex.getMessage());
        }
        return p;
    }

}
