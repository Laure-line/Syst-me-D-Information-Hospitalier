/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf;

import java.io.FileNotFoundException;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author madisoim
 */
public class Projet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        String s = "297049134535195";
        SecuriteSociale ss = new SecuriteSociale(s);
        System.out.println(ss.getSexe());
        System.out.println(ss.getAnnee());
        System.out.println(ss.getMois());
        System.out.println(ss.getDpt());
        System.out.println(ss.getCommune());
        System.out.println(ss.getRegistre());
        System.out.println(ss.getCle());
        System.out.println(ss.toString());

        Adresse adresse = new Adresse("10 rue blabla", 38400, "St Martin d'Hères");
        SecuriteSociale ss1 = new SecuriteSociale(Sexe.F, 40, 12, 38, 22, 26, 23);
        Date ddn = new Date(01, 05, 1990);
        Patient ps = new Patient("il", "et moi", ddn, adresse, ss1.toString(), Sexe.F);
        Patient p = new Patient("il", "et moi", ddn, adresse, "123456789123456", Sexe.F);
        System.out.println(p.getSS().toString());
        CreerPatientXML c = new CreerPatientXML();
        c.ajouterPatient(p);
        Specialite spe = null;
        spe = spe.CARD;
        Medecin m = new Medecin("Bono", "Jean", spe);
        System.out.println("bonjour");
        FicheDeSoins fs = new FicheDeSoins(p, m, ddn);
        System.out.println(fs.getMedecin().getSpecialite());
        System.out.println("fin");
        FicheDeSoins ficheSoins;
        ficheSoins = new FicheDeSoins(p, m, ddn);
        CreerFicheSoinsXML cb = new CreerFicheSoinsXML();

        cb.ajouterFicheDeSoins(ficheSoins);
        
        c.supprPatientSS(p);
        Patient p3 = new Patient("un", "test", ddn, adresse, "213456789123456", Sexe.F);
        c.ajouterPatient(p3);
        System.out.println(p3.getSexe().type);
        System.out.println(m.getSpecialite());
    }

}
