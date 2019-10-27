package nf;


public class Adresse {
    private String rue;

   public void setRue(String rue) {
      this.rue = rue;
   }

   public void setCodePostal(int codePostal) {
      this.codePostal = codePostal;
   }

   public void setVille(String ville) {
      this.ville = ville;
   }

    private int codePostal;

    private String ville;

    public String getRue() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.rue;
    }
    public int getCodePostal() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.codePostal;
    }

    public String getVille() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.ville;
    }
    public Adresse(String rue, int codePostal, String ville) {
       this.rue=rue;
       this.ville=ville;
       if (codePostal<99000 && codePostal >= 01000){
          this.codePostal= codePostal;
       }
       else {
          this.codePostal= 00000;
       }
    }

    public String getDepartement() {
       return String.valueOf(codePostal/1000);
    }

    public String toString() {
      return (this.getRue() + " " + this.getCodePostal() + " " + this.getVille()); 
    }

}
