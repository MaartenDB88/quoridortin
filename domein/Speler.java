package domein;

public class Speler {

    private String naam;
    private Pion pion;
    private int nummer;
    private int muren;

    public Speler(int nr, String Nm) {
        this.nummer = nr;
        naam = Nm;
        pion = null;
        muren = 10;
    }

    public Speler(int nr, String Nm, Pion p, int muur) {
        this.nummer = nr;
        naam = Nm;
        pion = p;
        muren = muur;

    }

    public void setNaam(String n) {
        this.naam = n;
    }

    public String getNaam() {
        return naam;
    }

    public void setPion(Pion P) {
        pion = P;
    }

    public Pion getPion() {
        return pion;
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nr) {
        this.nummer = nr;
    }

    public void setMuur(int muur) {
        this.muren = muur;
    }

    public int getMuur() {
        return muren;
    }
}
