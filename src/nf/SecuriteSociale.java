package nf;

public class SecuriteSociale {

    private Sexe sexe;
    private int annee;
    private int mois;
    private int dpt;
    private int commune;
    private int registre;
    private int cle;

    public SecuriteSociale(Sexe sexe, int annee, int mois, int dpt, int commune, int registre, int cle) {
        this.sexe = sexe;
        this.annee = annee;
        this.mois = mois;
        this.dpt = dpt;
        this.commune = commune;
        this.registre = registre;
        this.cle = cle;
    }

    public SecuriteSociale(String numero) {
        long num = Long.parseLong(numero);
        int i = (int) (num / (1 * Math.pow(10, 14)));
        if (i == 1) {
            this.sexe = Sexe.M;
        } else {
            this.sexe = Sexe.F;
        }
        this.annee = (int) (num / (1 * Math.pow(10, 12)));
        this.annee = this.annee % 100;
        this.mois = (int) (num / (1 * Math.pow(10, 10)));
        this.mois = this.mois % 100;
        this.dpt = (int) (num / (1 * Math.pow(10, 8)));
        long b = (long) (num - dpt * Math.pow(10, 8));
        this.dpt = this.dpt % 100;
        this.commune = (int) (b / (1 * Math.pow(10, 5)));
        this.registre = (int) (b / (1 * Math.pow(10, 2)));
        this.registre = this.registre % 1000;
        this.cle = (int) (b / (1 * Math.pow(10, 0)));
        this.cle = this.cle % 100;
    }

    public String toStringSpaced() {
        String s = "" + sexe.indice + " "; // A revérifier

        if (annee < 10) {
            s += "0" + annee + " ";
        } else {
            s += annee + " ";
        }
        if (mois < 10) {
            s += "0" + mois + " ";
        } else {
            s += mois + " ";
        }
        if (dpt < 10) {
            s += "0" + dpt + " ";
        } else {
            s += dpt + " ";
        }
        if (commune < 100 && commune > 10) {
            s += "0" + commune + " ";
        } else {
            if (commune < 10) {
                s += "00" + commune + " ";
            } else {
                s += commune + " ";
            }
        }
        if (registre < 100 && registre > 10) {
            s += "0" + registre + " ";
        } else {
            if (registre < 10) {
                s += "00" + registre + " ";
            } else {
                s += registre + " ";
            }
        }
        if (cle < 10) {
            s += "0" + cle;
        } else {
            s += cle;
        }

        return s;

    }
    
    public String toString() {
        String s = "" + sexe.indice; // A revérifier

        if (annee < 10) {
            s += "0" + annee;
        } else {
            s += annee;
        }
        if (mois < 10) {
            s += "0" + mois;
        } else {
            s += mois;
        }
        if (dpt < 10) {
            s += "0" + dpt;
        } else {
            s += dpt;
        }
        if (commune < 100 && commune > 10) {
            s += "0" + commune;
        } else {
            if (commune < 10) {
                s += "00" + commune;
            } else {
                s += commune;
            }
        }
        if (registre < 100 && registre > 10) {
            s += "0" + registre;
        } else {
            if (registre < 10) {
                s += "00" + registre;
            } else {
                s += registre;
            }
        }
        if (cle < 10) {
            s += "0" + cle;
        } else {
            s += cle;
        }

        return s;

    }

    public Sexe getSexe() {
        return sexe;
    }

    public int getAnnee() {
        return annee;
    }

    public int getMois() {
        return mois;
    }

    public int getDpt() {
        return dpt;
    }

    public int getCommune() {
        return commune;
    }

    public int getRegistre() {
        return registre;
    }

    public int getCle() {
        return cle;
    }

}
