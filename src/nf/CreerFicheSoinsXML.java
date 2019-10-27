/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Maya Robichon
 */
public class CreerFicheSoinsXML {

    private static Element racine = new Element("fichesDeSoins");

    private static org.jdom2.Document document = new Document(racine);

    public void ajouterFicheDeSoins(FicheDeSoins fs) {

        /*Pour sélectionner le fichier xml*/
        String fichier = "FichesDeSoins.xml";

        /*Lire le fichier*/
        try {
            SAXBuilder sxb = new SAXBuilder();
            document = sxb.build(new File(fichier));
            racine = document.getRootElement();
        } catch (Exception e) {
        }

        /*Ecrire dans le fichier*/
        Element ficheDeSoins = new Element("ficheDeSoins");
        racine.addContent(ficheDeSoins);

        Element nSS = new Element("nSS");
        nSS.setText(fs.getPatient().getSS().toString());
        ficheDeSoins.addContent(nSS);

        Element medecin = new Element("medecin");
        ficheDeSoins.addContent(medecin);

        Element nom = new Element("nom");
        nom.setText(fs.getMedecin().getNom());
        medecin.addContent(nom);

        Element prenom = new Element("prenom");
        prenom.setText(fs.getMedecin().getPrenom());
        medecin.addContent(prenom);

        Element specialite = new Element("specialite");
        specialite.setText(fs.getMedecin().getSpecialite());
        medecin.addContent(specialite);

        Element date = new Element("date");
        String dateFDS = fs.getDate().toString();
        date.setText(dateFDS);
        ficheDeSoins.addContent(date);

        for (int i = 0; i < fs.getActes().size(); i++) {

            Element acte = new Element("acte");
            ficheDeSoins.addContent(acte);

            Element code = new Element("code");
            code.setText(fs.getActes().get(i).getCode().getCode());
            acte.addContent(code);

            Element coef = new Element("coef");
            coef.setText(Integer.toString(fs.getActes().get(i).getCoef()));
            acte.addContent(coef);

            Element cout = new Element("cout");
            cout.setText(String.valueOf(fs.getActes().get(i).cout()));
            ficheDeSoins.addContent(cout);
        }

        Element coutTot = new Element("coutTot");
        coutTot.setText(String.valueOf(fs.coutTotal()));
        ficheDeSoins.addContent(coutTot);

        XMLOutputter out = new XMLOutputter();
        try {
            out.output(ficheDeSoins, new FileOutputStream(new File("FichesDeSoins.xml")));
        } catch (IOException e) {
            //Erreur d'écriture à traiter ici
        }

        enregistre("FichesDeSoins.xml");
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
