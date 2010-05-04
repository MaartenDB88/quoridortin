package domein;

import java.awt.Color;

public class Pion {

    private String kleurString;
    private String symbool;
    private vak huidig;
    private vak start;

    public void setStart(vak start) {
        this.start = start;
        this.huidig = start;
    }

    public vak getStart() {
        return start;
    }
    private Color kleur;

    public Pion(String sym, String kl)//vak strt, vak hui)
    {
        symbool = sym;
        SetKleurString(kl);
        //start = strt;
        //huidig = hui;
    }

    public String getKleurString() {
        return kleurString;
    }

    public void SetKleurString(String kleur) {
        this.kleurString = kleur;
        this.kleur = Color.getColor(kleur);
    }

    public String getSymbool() {
        return symbool;
    }

    public void setSymbool(String symbool) {
        this.symbool = symbool;
    }

    public Color getKleur() {
        return kleur;
    }

    public void setKleur(Color kleur) {
        this.kleur = kleur;
    }

    public vak getHuidig() {
        return huidig;
    }

    public void setHuidig(vak huidig) {
        this.huidig = huidig;
    }

    public String toString() {
        return "[" + getSymbool() + "]";
    }
}
