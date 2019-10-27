package nf;

import java.util.ArrayList;
import java.util.List;

public abstract class Personnel {

    protected String nom;
    protected String prenom;
    protected String id;
    protected String mp;
    protected String type;
    private List<DossierMedical> dossiermedical;

    public Personnel() {
        nom = "";
        prenom = "";
        id = "";
        mp = "";
        type = "";
    }

    public Personnel(String nom, String prenom, String id, String mp) {
        this.nom = nom;
        this.prenom = prenom;
        this.id = id;
        this.mp = mp;
        dossiermedical = new ArrayList<DossierMedical>();
    }
// getters

    public Personnel(String nom, String prenom, String id) {
        this.nom = nom;
        this.prenom = prenom;
        this.id = id;
    }

    public abstract String getNom();

    public abstract String getPrenom();

    public abstract String getId();

    public abstract String getType();

    public List<DossierMedical> getDossiermedical() {
        return dossiermedical;
    }

    public String getMp() {
        return mp;
    }

// setters
    public void setMp(String value) {
        this.mp = value;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }
    
// Autres méthodes 
    public String toString(){
       return nom.toUpperCase() + " " + prenom + " " + id + " " + mp + " " + type ;
    }

}
