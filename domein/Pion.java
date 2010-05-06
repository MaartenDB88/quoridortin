package domein;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pion {

    private String kleurString;
    private String symbool;
    private vak huidig;
    private vak start;
    private Kleuren kleur;

    public void setStart(vak start) {
        this.start = start;
        this.huidig = start;
    }

    public vak getStart() {
        return start;
    }

    public Pion(String kl)//vak strt, vak hui)
    {

        SetKleurString(kl.toString());
        //start = strt;
        //huidig = hui;
    }

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
        this.kleur = Kleuren.valueOf(kleur.toLowerCase());
    }

    public String getSymbool() {
        return symbool;
    }

    public void setSymbool(String symbool) {
        this.symbool = symbool;
    }

    public Color getKleur() {

        switch (this.kleur) {
            case rood:
                return Color.RED;
               
            case blauw:
                return Color.blue;
                
            case geel:
                return Color.yellow;
               
            case groen:
                return Color.green;
               
            case zwart:
                return Color.black; 
        }
        return Color.cyan;
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

    enum Kleuren {

        rood, blauw, geel, groen, zwart
    }
}
