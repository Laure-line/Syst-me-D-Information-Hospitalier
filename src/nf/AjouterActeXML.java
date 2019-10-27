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
import java.util.ArrayList;
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
public class AjouterActeXML {

    private static Element racine = new Element("lactes");

    private static org.jdom2.Document document = new Document(racine);

    public void addActe(String s1, String s2, String s3) {

        /*Pour sélectionner le fichier xml*/
        String fichier = "Acte.xml";

        /*Lire le fichier*/
        try {
            SAXBuilder sxb = new SAXBuilder();
            document = sxb.build(new File(fichier));
            racine = document.getRootElement();
        } catch (Exception e) {
        }

        /*Ecrire dans le fichier*/
        Element actes = new Element("actes");
        racine.addContent(actes);

        Element acte = new Element("acte");
        acte.setText(s1);
        actes.addContent(acte);

        Element coef = new Element("coef");
        coef.setText(s2);
        actes.addContent(coef);

        if (!s3.isEmpty()) {
            Element commentaire = new Element("commentaire");
            commentaire.setText(s3);
            actes.addContent(commentaire);
        }

        XMLOutputter out = new XMLOutputter();
        try {
            out.output(actes, new FileOutputStream(new File("Acte.xml")));
        } catch (IOException e) {
            //Erreur d'écriture à traiter ici
        }

        enregistre("Acte.xml");
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

    public ArrayList<Acte> lireActe() {

        String donneesCourantes = "";
        Acte acteCourant = null;
        String acteCour = "";
        String coefCourant = "";
        String commCourant = " ";
        Code codeCourant = null;
        ArrayList<Acte> actes = null;

        // analyser le fichier par StAX
        try {
            // instanciation du parser
            InputStream in = new FileInputStream("Acte.xml");
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader parser = factory.createXMLStreamReader(in);

            // lecture des evenements
            for (int event = parser.next(); event != XMLStreamConstants.END_DOCUMENT; event = parser.next()) {
                // traitement selon l'evenement
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        if (parser.getLocalName().equals("lactes")) {
                            actes = new ArrayList<Acte>();
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        if (parser.getLocalName().equals("acte")) {
                            acteCour = donneesCourantes;
                            if (acteCour.equals("1")) {
                                codeCourant = Code.CS;
                            }
                            if (acteCour.equals("2")) {
                                codeCourant = Code.CSC;
                            }
                            if (acteCour.equals("3")) {
                                codeCourant = Code.FP;
                            }
                            if (acteCour.equals("4")) {
                                codeCourant = Code.KC;
                            }
                            if (acteCour.equals("5")) {
                                codeCourant = Code.KE;
                            }
                            if (acteCour.equals("6")) {
                                codeCourant = Code.K;
                            }
                            if (acteCour.equals("7")) {
                                codeCourant = Code.KFA;
                            }
                            if (acteCour.equals("8")) {
                                codeCourant = Code.KFB;
                            }
                            if (acteCour.equals("9")) {
                                codeCourant = Code.ORT;
                            }
                            if (acteCour.equals("10")) {
                                codeCourant = Code.PRO;
                            }
                        }
                        if (parser.getLocalName().equals("coef")) {
                            coefCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("commentaire")) {
                            commCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("actes")) {
                            if (codeCourant != null) {
                                acteCourant = new Acte(codeCourant, Integer.valueOf(coefCourant), commCourant);
                                actes.add(acteCourant);
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
            System.out.println("Exception de type 'XMLStreamException' lors de la lecture du fichier : " + "Acte.xml");
            System.out.println("Details :");
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println("Exception de type 'IOException' lors de la lecture du fichier : " + "Acte.xml");
            System.out.println("Verifier le chemin.");
            System.out.println(ex.getMessage());
        }
        return actes;
    }

    public void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void clearActes() throws FileNotFoundException, XMLStreamException {

        List<Element> listPatient = racine.getChildren("actes");
        Iterator<Element> i = listPatient.iterator();

        InputStream in = new FileInputStream("Acte.xml");
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser = factory.createXMLStreamReader(in);

        //On parcours la liste grâce à un iterator
        while (i.hasNext()) {
            Element courant = (Element) i.next();
            i.remove();

        }
        enregistre("Acte.xml");
    }

}
