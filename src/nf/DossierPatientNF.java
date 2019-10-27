package nf;

import java.util.ArrayList;
import java.util.List;
//import com.modeliosoft.modelio.javadesigner.annotations.objid;

//@objid ("b6e701c4-95f4-435f-bd96-f5d32dc2a9de")
public class DossierPatientNF {

    //@objid ("7ea04c28-6689-4fb2-baaf-505e5949d2ec")
    private Patient patient;

    //@objid ("6d0a2a4d-74cd-42f6-9399-564ceafef8a1")
    private List<FicheDeSoins> ficheDeSoins;

    //@objid ("4b2bb1c5-4a6c-4868-914e-c55243c06ce9")
    public DossierPatientNF(Patient patient) {
        this.patient = patient;
        ficheDeSoins = new ArrayList<FicheDeSoins>();
    }

    public void setFicheDeSoins(List<FicheDeSoins> ficheDeSoins) {
        this.ficheDeSoins = ficheDeSoins;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public List<FicheDeSoins> getFicheDeSoins() {
        return ficheDeSoins;
    }
    
    public void addFicheDeSoins(FicheDeSoins fiche){
        ficheDeSoins.add(fiche);
    }

    //@objid ("42d0642d-bda1-4b30-86f7-3da5cb971671")
    public void afficherFiche() {
    }

    //@objid ("d36ec854-14f8-48df-9eba-3beab258ac90")
    public void afficherCout() {
    }

}
