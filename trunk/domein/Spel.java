package domein;

import java.util.ArrayList;
import java.util.List;

public class Spel {

    private DomeinBeheerder db;
    private Spelbord bord;
    private int _aantalSpelers;
    private List<Speler> spelers;
    private vak[] lijstZetVakken = new vak[4];
    private int aantalBeurten;
    private Speler CurrentPlayer;
    private int dimensie;

    public DomeinBeheerder getDb() {
        return db;
    }

    public void setDb(DomeinBeheerder db) {
        this.db = db;
    }

    public int getDimensie() {
        return dimensie;
    }

    public void setDimensie(int dimensie) {
        this.dimensie = dimensie;
    }

    public Spel(int d) {
        db = new DomeinBeheerder();
        dimensie = d;
        bord = new Spelbord(dimensie);

    }

    public Spelbord getBord() {
        return bord;
    }

    public int getAantalSpelers() {
        return _aantalSpelers;
    }

    public vak[][] getSpeelBord() {
        return bord.getSpeelBord();
    }

    public vak getVak(int x, int y) {
        if (x >= dimensie || y >= dimensie) {
            return null;
        }
        return bord.getSpeelBord()[x][y];
    }

    //public List<String> getLijstSG(){
    //return db.getLijstSG
    //}
    public List<Speler> getSpelers() {
        return spelers;
    }

    public int CurrentPlayerID() {
        return CurrentPlayer.getNummer();
    }

    public void setCurrentPlayerID(int ID) {
        for (Speler player : spelers) {
            if (player.getNummer() == ID) {
                CurrentPlayer = player;
            }
        }
    }

    public int StartSpel() {
        if (CurrentPlayer == null) {
            aantalBeurten = 1;
            setCurrentPlayerID(aantalBeurten);
        } else {
            aantalBeurten = CurrentPlayer.getNummer();
        }
        return aantalBeurten;
    }

    public List<String[]> getPionInfo() {

        List<String[]> pionInfo = new ArrayList<String[]>();

        for (Pion p : db.getPion()) {

            String symbool = p.getSymbool(); //positie 1
            String kleur = p.getKleurString();	//positie 2

            String info[] = {kleur, symbool};
            pionInfo.add(info);
        }
        return pionInfo;
    }

    void maakSpelers(List<Speler> spelers) {

        this.spelers = spelers;

        int counter = 1;
        vak start = null;
        for (Speler speler : spelers) {
            switch (counter) {
                case 1:
                    start = bord.getSpeelBord()[dimensie / 2][0];

                    break;
                case 2:
                    start = bord.getSpeelBord()[dimensie / 2][dimensie - 1];

                    break;
                case 3:
                    start = bord.getSpeelBord()[0][dimensie / 2];
                    break;
                case 4:
                    start = bord.getSpeelBord()[dimensie - 1][dimensie / 2];
                    break;
            }
            start.setPion(speler.getPion());
            speler.getPion().setStart(start);
            counter++;
        }

    }

    boolean goToNextStep() {
        if (CurrentPlayer.getPion().getStart().getX() == 0) {
            if (CurrentPlayer.getPion().getHuidig().getX() == dimensie - 1) {
                return true;
            }

        } else {
            if (CurrentPlayer.getPion().getHuidig().getY() == dimensie - 1) {
                return true;
            }
        }
        nextPlayer();

        return false;
    }

    private void checkVak(vak vakToCheck, List<vak> vakken) {
        if (!vakToCheck.isMuur()) {
            vakken.add(vakToCheck);
        }
    }

    private void nextPlayer() {
        int nextPlayerId = CurrentPlayer.getNummer();
        nextPlayerId++;
        if (nextPlayerId >= spelers.size()) {
            nextPlayerId = 0;
        }
        setCurrentPlayerID(nextPlayerId);
    }

    String getNextGameStep() {
        return "";
    }

    public List<vak> mogelijkeZetten(Pion pion) {
        List<vak> vakken = new ArrayList<vak>();
        vak[][] vakkenBord = bord.getSpeelBord();
        int x = pion.getHuidig().getX();
        int y = pion.getHuidig().getY();
        //BOVEN
        vak vakToCheck;
        if (y != 0) {
            vakToCheck = vakkenBord[x][y - 1];
            checkVak(vakToCheck, vakken);
        }
        //ONDER
        if (y+1 < dimensie) {
            vakToCheck = vakkenBord[x][y + 1];
            checkVak(vakToCheck, vakken);
        }
        //LINKS
        if (x != 0) {
            vakToCheck = vakkenBord[x - 1][y];
            checkVak(vakToCheck, vakken);
        }
        //RECHTS
        if (x+1 < dimensie) {
            vakToCheck = vakkenBord[x + 1][y];
            checkVak(vakToCheck, vakken);
        }
        return vakken;
    }
}

    
  