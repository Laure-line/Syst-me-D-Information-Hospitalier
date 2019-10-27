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
public class CreerPatientXML {

    private static Element racine = new Element("patients");

    private static org.jdom2.Document document = new Document(racine);

    public void ajouterPatient(Patient p) {

        /*Pour sélectionner le fichier xml*/
        String fichier = "Patients.xml";

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

        Element ss = new Element("nSecuriteSociale");
        ss.setText(p.getSS().toString());
        patient.addContent(ss);

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

        Element sexe = new Element("sexe");
        sexe.setText(p.getSexe().type);
        patient.addContent(sexe);

        XMLOutputter out = new XMLOutputter();
        try {
            out.output(patient, new FileOutputStream(new File("Patients.xml")));
        } catch (IOException e) {
            //Erreur d'écriture à traiter ici
        }

        enregistre("Patients.xml");
    }

    public void supprPatientSS(Patient p) throws FileNotFoundException, XMLStreamException {
        //Dans un premier temps on liste tous les étudiants
        List<Element> listPatient = racine.getChildren("patient");
        Iterator<Element> i = listPatient.iterator();

        InputStream in = new FileInputStream("Patients.xml");
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser = factory.createXMLStreamReader(in);

        //On parcours la liste grâce à un iterator
        while (i.hasNext()) {
            Element courant = (Element) i.next();
            if (courant.getChild("nSecuriteSociale").getValue().equals(p.getSS().toString())) {
                i.remove();
            }
        }
        enregistre("Patients.xml");
    }

    public void enregistre(String fichier) {
        try {
            //On utilise ici un affichage classique avec getPrettyFormat()
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            //Remarquez qu'il suffit simplement de créer une instance de FileOutputStream
            //avec en argument le nom du fichier pour effectuer la sérialisation.
            sortie.output(document, new FileOutputStream(fichier));
        } catch (java.io.IOException e) {
        }
    }

}
