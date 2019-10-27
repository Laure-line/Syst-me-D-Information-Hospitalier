/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf;

import java.util.List;

/**
 *
 * @author prouvosl
 */
public class LiaisonFSetDP {

    LectureFicheDeSoins lectureFS;
    LecturePatientXML lectureP;
    List<FicheDeSoins> lficheDeSoins;
    DossierMedical dossiermed;

    public LiaisonFSetDP() {
        dossiermed = lectureP.getDossier();
        lficheDeSoins = lectureFS.getFicheDeSoins();
        for (int i = 0; i < lficheDeSoins.size(); i++) {
            
        }
    }

}
