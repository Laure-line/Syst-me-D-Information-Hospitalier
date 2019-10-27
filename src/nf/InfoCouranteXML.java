/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author prouvosl
 */
public class InfoCouranteXML {

    private static Element racine = new Element("infosCourante");
    private static org.jdom2.Document document = new Document(racine);

    public InfoCouranteXML() {
    }

    public void persLog(Personnel p) throws FileNotFoundException, XMLStreamException {

        //on s'assure que le fichier est bien vidé avant de rentrer les informations
        this.deLog();

        /*Pour sélectionner le fichier xml*/
        String fichier = "InfoCourante.xml";

        /*Ecrire dans le fichier*/
        Element personnel = new Element("personnel");
        racine.addContent(personnel);

        Element type = new Element("type");
        type.setText(p.getType());
        personnel.addContent(type);

        Element nom = new Element("nom");
        nom.setText(p.getNom());
        personnel.addContent(nom);

        Element prenom = new Element("prenom");
        prenom.setText(p.getPrenom());
        personnel.addContent(prenom);

        Element identifiant = new Element("identifiant");
        identifiant.setText(p.getId());
        personnel.addContent(identifiant);

        enregistre("InfoCourante.xml");
    }

    public void deLog() throws FileNotFoundException, XMLStreamException {
        List<Element> listPatient = racine.getChildren("personnel");
        Iterator<Element> i = listPatient.iterator();

        InputStream in = new FileInputStream("Impression.xml");
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser = factory.createXMLStreamReader(in);

        //On parcours la liste grâce à un iterator
        while (i.hasNext()) {
            Element courant = (Element) i.next();
            i.remove();
        }
        
        listPatient = racine.getChildren("patient");
        i = listPatient.iterator();
        while (i.hasNext()) {
            Element courant = (Element) i.next();
            i.remove();
        }

        enregistre("InfoCourante.xml");
    }

    public void patientSelected(Patient p) {

        /*Pour sélectionner le fichier xml*/
        String fichier = "InfoCourante.xml";

        /*Lire le fichier*/
        try {
            SAXBuilder sxb = new SAXBuilder();
            document = sxb.build(new File(fichier));
            racine = document.getRootElement();
        } catch (Exception e) {
        }

        /*Ecrire dans le fichier*/
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

        enregistre("InfoCourante.xml");
    }

    public void enregistre(String fichier) {
        try {
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(document, new FileOutputStream(fichier));
        } catch (java.io.IOException e) {
        }
    }

    public Medecin lireMedecin() {

        String donneesCourantes = "";
        Medecin medecinCourant = null;
        String nomCourant = "";
        String prenomCourant = "";
        String idCourant = "";

        // analyser le fichier par StAX
        try {
            // instanciation du parser
            InputStream in = new FileInputStream("InfoCourante.xml");
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader parser = factory.createXMLStreamReader(in);

            // lecture des evenements
            for (int event = parser.next(); event != XMLStreamConstants.END_DOCUMENT; event = parser.next()) {
                // traitement selon l'evenement
                switch (event) {
                    case XMLStreamConstants.END_ELEMENT:
                        if (parser.getLocalName().equals("personnel")) {
                            medecinCourant = new Medecin(nomCourant, prenomCourant, idCourant);
                        }
                        if (parser.getLocalName().equals("nom")) {
                            nomCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("prenom")) {
                            prenomCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("identifiant")) {
                            idCourant = donneesCourantes;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        donneesCourantes = parser.getText();
                        break;
                } // end switch
            } // end while
            parser.close();
        } catch (XMLStreamException ex) {
            System.out.println("Exception de type 'XMLStreamException' lors de la lecture du fichier : " + "InfoCourante.xml");
            System.out.println("Details :");
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println("Exception de type 'IOException' lors de la lecture du fichier : " + "InfoCourante.xml");
            System.out.println("Verifier le chemin.");
            System.out.println(ex.getMessage());
        }

        return medecinCourant;
    }

    public String lireType() {

        String donneesCourantes = "";
        String typeCourant = "";

        // analyser le fichier par StAX
        try {
            // instanciation du parser
            InputStream in = new FileInputStream("InfoCourante.xml");
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader parser = factory.createXMLStreamReader(in);

            // lecture des evenements
            for (int event = parser.next(); event != XMLStreamConstants.END_DOCUMENT; event = parser.next()) {
                // traitement selon l'evenement
                switch (event) {
                    case XMLStreamConstants.END_ELEMENT:
                        if (parser.getLocalName().equals("identifiant")) {
                            String values[] = donneesCourantes.split("_");
                            if (values[0].equals("M")) {
                                typeCourant = "Medecin";
                            } else if (values[0].equals("SM")) {
                                typeCourant = "Secretaire Medicale";
                            } else if (values[0].equals("SA")) {
                                typeCourant = "Secretaire Administrative";
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
            System.out.println("Exception de type 'XMLStreamException' lors de la lecture du fichier : " + "InfoCourante.xml");
            System.out.println("Details :");
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println("Exception de type 'IOException' lors de la lecture du fichier : " + "InfoCourante.xml");
            System.out.println("Verifier le chemin.");
            System.out.println(ex.getMessage());
        }
        return typeCourant;
    }

    public Patient lirePatient() {

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
            InputStream in = new FileInputStream("InfoCourante.xml");
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader parser = factory.createXMLStreamReader(in);

            // lecture des evenements
            for (int event = parser.next(); event != XMLStreamConstants.END_DOCUMENT; event = parser.next()) {
                // traitement selon l'evenement
                switch (event) {
                    case XMLStreamConstants.END_ELEMENT:
                        if (parser.getLocalName().equals("patient")) {
                            patientCourant = new Patient(nomCourant, prenomCourant, date, adresseCourante, nSSCourante, sexeCourant);
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
            System.out.println("Exception de type 'XMLStreamException' lors de la lecture du fichier : " + "InfoCourante.xml");
            System.out.println("Details :");
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println("Exception de type 'IOException' lors de la lecture du fichier : " + "InfoCourante.xml");
            System.out.println("Verifier le chemin.");
            System.out.println(ex.getMessage());
        }
        return patientCourant;
    }
}
