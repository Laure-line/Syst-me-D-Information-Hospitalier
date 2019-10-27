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
 * @author Maya Robichon
 */
public class ImprimerXML {

    private static Element racine = new Element("impression");

    private static org.jdom2.Document document = new Document(racine);

    public void ajouterFicheImpression(DossierPatientNF dp) {

        /*Pour sélectionner le fichier xml*/
        String fichier = "Impression.xml";

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

        Element nSS = new Element("nSS");
        nSS.setText(dp.getPatient().getSS().toString());
        patient.addContent(nSS);

        Element nom = new Element("nom");
        nom.setText(dp.getPatient().getNom());
        patient.addContent(nom);

        Element prenom = new Element("prenom");
        prenom.setText(dp.getPatient().getPrenom());
        patient.addContent(prenom);

        Element dateDeNaissance = new Element("dateDeNaissance");
        dateDeNaissance.setText(dp.getPatient().getNaissance().toString());
        patient.addContent(dateDeNaissance);

        Element adresse = new Element("adresse");
        patient.addContent(adresse);

        Element rue = new Element("rue");
        rue.setText(dp.getPatient().getAdresse().getRue());
        adresse.addContent(rue);

        Element codePostal = new Element("codePostal");
        codePostal.setText(Integer.toString(dp.getPatient().getAdresse().getCodePostal()));
        adresse.addContent(codePostal);

        Element ville = new Element("ville");
        ville.setText(dp.getPatient().getAdresse().getVille());
        adresse.addContent(ville);

        Element sexe = new Element("sexe");
        sexe.setText(dp.getPatient().getSexe().toString());
        patient.addContent(sexe);

        Element fichesDeSoins = new Element("fichesDeSoins");
        racine.addContent(fichesDeSoins);
        
        double coutToutesLesFiches = 0.0;

        for (int i = 0; i < dp.getFicheDeSoins().size(); i++) {

            Element ficheDeSoins = new Element("ficheDeSoins");
            fichesDeSoins.addContent(ficheDeSoins);

            Element medecin = new Element("medecin");
            ficheDeSoins.addContent(medecin);

            Element nomMed = new Element("nomMed");
            nomMed.setText(dp.getFicheDeSoins().get(i).getMedecin().getNom());
            medecin.addContent(nomMed);

            Element prenomMed = new Element("prenomMed");
            prenomMed.setText(dp.getFicheDeSoins().get(i).getMedecin().getPrenom());
            medecin.addContent(prenomMed);

            Element specialite = new Element("specialite");
            specialite.setText(dp.getFicheDeSoins().get(i).getMedecin().getSpecialite());
            medecin.addContent(specialite);

            Element date = new Element("date");
            date.setText(dp.getFicheDeSoins().get(i).getDate().toString());
            ficheDeSoins.addContent(date);

            Element acte = new Element("acte");
            ficheDeSoins.addContent(acte);

            double coutUneFiche = 0.0;
            
            for (int j = 0; j < dp.getFicheDeSoins().get(i).getActes().size(); j++) {

                Element libelle = new Element("libelle");
                libelle.setText(dp.getFicheDeSoins().get(i).getActes().get(j).getCode().getLibelle());
                acte.addContent(libelle);

                Element coef = new Element("coef");
                coef.setText(Integer.toString(dp.getFicheDeSoins().get(i).getActes().get(j).getCoef()));
                acte.addContent(coef);

                Element coutActe = new Element("coutActe");
                coutActe.setText(String.valueOf(dp.getFicheDeSoins().get(i).getActes().get(j).cout()));
                acte.addContent(coutActe);
                
                coutUneFiche += dp.getFicheDeSoins().get(i).getActes().get(j).cout();

            }

            Element coutFiche = new Element("coutFiche");
            coutFiche.setText(String.valueOf(coutUneFiche));
            ficheDeSoins.addContent(coutFiche);
            
            coutToutesLesFiches += coutUneFiche;

        }

        Element coutTotal = new Element("coutTotal");
        coutTotal.setText(String.valueOf(coutToutesLesFiches));
        fichesDeSoins.addContent(coutTotal);

        XMLOutputter out = new XMLOutputter();
        try {
            out.output(patient, new FileOutputStream(new File("Impression.xml")));
        } catch (IOException e) {
            //Erreur d'écriture à traiter ici
        }

        enregistre("Impression.xml");
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
    
    public void supprXMLImpression () throws FileNotFoundException, XMLStreamException{
        List<Element> listPatient = racine.getChildren("patient");
        Iterator<Element> i = listPatient.iterator();

        InputStream in = new FileInputStream("Impression.xml");
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser = factory.createXMLStreamReader(in);

        //On parcours la liste grâce à un iterator
        while (i.hasNext()) {
            Element courant = (Element) i.next();
            i.remove();
        }
        
        listPatient = racine.getChildren("fichesDeSoins");
        i = listPatient.iterator();
        while (i.hasNext()) {
            Element courant = (Element) i.next();
            i.remove();
        }
        
        enregistre("Impression.xml");
    }

}
