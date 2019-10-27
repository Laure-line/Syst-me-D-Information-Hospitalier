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
public class CreerMedecinXML {

    private static Element racine = new Element("personnel");

    private static org.jdom2.Document document = new Document(racine);

    public static void main(String[] args) {
        Element medecin = new Element("medecin");
        racine.addContent(medecin);

//Medecin 1
        Element identifiant = new Element("identifiant");
        identifiant.setText("M_Bono");
        medecin.addContent(identifiant);

        Element motDePasse = new Element("motDePasse");
        motDePasse.setText("1234");
        medecin.addContent(motDePasse);

        Element nom = new Element("nom");
        nom.setText("Bono");
        medecin.addContent(nom);

        Element prenom = new Element("prenom");
        prenom.setText("Jean");
        medecin.addContent(prenom);

        Element specialite = new Element("specialite");
        specialite.setText("ORL");
        medecin.addContent(specialite);

        Element type = new Element("type");
        type.setText("Medical");
        medecin.addContent(type);

//Medecin 2
        Element medecin2 = new Element("medecin");
        racine.addContent(medecin2);

        identifiant = new Element("identifiant");
        identifiant.setText("M_Deblouze");
        medecin2.addContent(identifiant);

        motDePasse = new Element("motDePasse");
        motDePasse.setText("1234");
        medecin2.addContent(motDePasse);

        nom = new Element("nom");
        nom.setText("Deblouze");
        medecin2.addContent(nom);

        prenom = new Element("prenom");
        prenom.setText("Agathe");
        medecin2.addContent(prenom);

        specialite = new Element("specialite");
        specialite.setText("Cardiologue");
        medecin2.addContent(specialite);

        type = new Element("type");
        type.setText("Medical");
        medecin2.addContent(type);

//Medecin 3
        Element medecin3 = new Element("medecin");
        racine.addContent(medecin3);

        identifiant = new Element("identifiant");
        identifiant.setText("M_Heget");
        medecin3.addContent(identifiant);

        motDePasse = new Element("motDePasse");
        motDePasse.setText("1234");
        medecin3.addContent(motDePasse);

        nom = new Element("nom");
        nom.setText("Heget");
        medecin3.addContent(nom);

        prenom = new Element("prenom");
        prenom.setText("Yves");
        medecin3.addContent(prenom);

        specialite = new Element("specialite");
        specialite.setText("Gynécologie");
        medecin3.addContent(specialite);

        type = new Element("type");
        type.setText("Medical");
        medecin3.addContent(type);

//Medecin 4
        Element medecin4 = new Element("medecin");
        racine.addContent(medecin4);

        identifiant = new Element("identifiant");
        identifiant.setText("M_Covert");
        medecin4.addContent(identifiant);

        motDePasse = new Element("motDePasse");
        motDePasse.setText("1234");
        medecin4.addContent(motDePasse);

        nom = new Element("nom");
        nom.setText("Covert");
        medecin4.addContent(nom);

        prenom = new Element("prenom");
        prenom.setText("Harry");
        medecin4.addContent(prenom);

        specialite = new Element("specialite");
        specialite.setText("ORL");
        medecin4.addContent(specialite);

        type = new Element("type");
        type.setText("Medical");
        medecin4.addContent(type);

//Medecin 5
        Element medecin5 = new Element("medecin");
        racine.addContent(medecin5);

        identifiant = new Element("identifiant");
        identifiant.setText("M_Foupasune");
        medecin5.addContent(identifiant);

        motDePasse = new Element("motDePasse");
        motDePasse.setText("1234");
        medecin5.addContent(motDePasse);

        nom = new Element("nom");
        nom.setText("Foupasune");
        medecin5.addContent(nom);

        prenom = new Element("prenom");
        prenom.setText("Jean");
        medecin5.addContent(prenom);

        specialite = new Element("specialite");
        specialite.setText("ORL");
        medecin5.addContent(specialite);

        type = new Element("type");
        type.setText("Medical");
        medecin5.addContent(type);

//Secretaire Medicale 1
        Element secMed = new Element("secretaireMedicale");
        racine.addContent(secMed);

        identifiant = new Element("identifiant");
        identifiant.setText("SM_Blanche");
        secMed.addContent(identifiant);

        motDePasse = new Element("motDePasse");
        motDePasse.setText("1234");
        secMed.addContent(motDePasse);

        nom = new Element("nom");
        nom.setText("Blanche");
        secMed.addContent(nom);

        prenom = new Element("prenom");
        prenom.setText("Rose");
        secMed.addContent(prenom);

        type = new Element("type");
        type.setText("Medical");
        secMed.addContent(type);

//Secretaire Administrative 1
        Element secAd = new Element("secretaireAdministrative");
        racine.addContent(secAd);

        identifiant = new Element("identifiant");
        identifiant.setText("SA_Martin");
        secAd.addContent(identifiant);

        motDePasse = new Element("motDePasse");
        motDePasse.setText("1234");
        secAd.addContent(motDePasse);

        nom = new Element("nom");
        nom.setText("Martin");
        secAd.addContent(nom);

        prenom = new Element("prenom");
        prenom.setText("Eva");
        secAd.addContent(prenom);

        type = new Element("type");
        type.setText("Administratif");
        secAd.addContent(type);

        enregistre("Personnel.xml");

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
            sortie.output(document, new FileOutputStream(fichier));
        } catch (java.io.IOException e) {
        }
    }
}
