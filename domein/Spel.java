package domein;

import java.util.ArrayList;
import java.util.HashSet;
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
    private int wallLength = 2;
    
    public int getWallLength() {
        return wallLength;
    }

    public void setWallLength(int vakkenLength) {
        this.wallLength = vakkenLength;
    }

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

    public void setSpelers(List<Speler> spelers) {
        vak[][] bord = getSpeelBord();
        this.spelers = spelers;
        for (Speler speler : spelers) {
            bord[speler.getPion().getHuidig().getX()][speler.getPion().getHuidig().getY()].setPion(speler.getPion());
        }
    }

    public Speler CurrentPlayer() {
        return CurrentPlayer;
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

    void maakSpelers(List<Speler> spelers, int wallCount) {

        if (spelers == null) {
            spelers = new ArrayList<Speler>();
        }
        for (Speler speler : spelers) {
            speler.setMuur(wallCount);
        }
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
        if (CurrentPlayer.getPion().getStart().getX() == dimensie / 2) {
            if (CurrentPlayer.getPion().getStart().getY() == 0) {

                if (CurrentPlayer.getPion().getHuidig().getY() == dimensie - 1) {
                    return true;
                }
            } else {

                if (CurrentPlayer.getPion().getHuidig().getY() == 0) {
                    return true;
                }

            }

        } else {
            if (CurrentPlayer.getPion().getStart().getX() == 0) {

                if (CurrentPlayer.getPion().getHuidig().getX() == dimensie - 1) {

                    return true;
                }
            } else {

                if (CurrentPlayer.getPion().getHuidig().getX() == 0) {
                    return true;
                }

            }
        }


        nextPlayer();
        return false;
    }

    private boolean checkVak(vak vakToCheck, List<vak> vakken) {
        if (!vakToCheck.isMuur()) {
            if (vakToCheck.getPion() == null) {
                vakken.add(vakToCheck);
                return true;
            }

        }
        return false;
    }

    public void nextPlayer() {
        int nextPlayerId = CurrentPlayer.getNummer();
        CurrentPlayer.nextStep();
        nextPlayerId++;

        if (nextPlayerId >= spelers.size()) {
            nextPlayerId = 0;
        }
        setCurrentPlayerID(nextPlayerId);

    }

    String getNextGameStep() {
        return "";
    }

    public List<vak> pawnMoves(Pion pion) {
        List<vak> vakken = new ArrayList<vak>();
        vak[][] vakkenBord = bord.getSpeelBord();
        int x = pion.getHuidig().getX();
        int y = pion.getHuidig().getY();
        //BOVEN
        vak vakToCheck;
        if (y != 0) {
            vakToCheck = vakkenBord[x][y - 1];
            if (!checkVak(vakToCheck, vakken) && !vakToCheck.isMuur()) {
                if (y > 1) {
                    vakToCheck = vakkenBord[x][y - 2];
                    checkVak(vakToCheck, vakken);
                }
            }

        }
        //ONDER
        if (y + 1 < dimensie) {
            vakToCheck = vakkenBord[x][y + 1];
            if (!checkVak(vakToCheck, vakken) && !vakToCheck.isMuur()) {
                if (y + 2 < dimensie) {
                    vakToCheck = vakkenBord[x][y + 2];
                    checkVak(vakToCheck, vakken);
                }
            }
        }
        //LINKS
        if (x != 0) {
            vakToCheck = vakkenBord[x - 1][y];
            if (!checkVak(vakToCheck, vakken) && !vakToCheck.isMuur()) {
                if (x > 1) {
                    vakToCheck = vakkenBord[x - 2][y];
                    checkVak(vakToCheck, vakken);
                }
            }
        }
        //RECHTS
        if (x + 1 < dimensie) {
            vakToCheck = vakkenBord[x + 1][y];
            if (!checkVak(vakToCheck, vakken) && !vakToCheck.isMuur()) {
                if (x + 2 < dimensie) {
                    vakToCheck = vakkenBord[x + 2][y];
                    checkVak(vakToCheck, vakken);
                }
            }
        }
        return vakken;
    }

    void setMuur(vak vak) {
        for (vak vakC : muurVakken(vak)) {
            vakC.setMuur(true);
        }
    }

    public void setMuren(List<vak> muurLijst) {
        vak[][] bord = this.getSpeelBord();
        for (vak vak : muurLijst) {
            bord[vak.getX()][vak.getY()].setMuur(true);
        }
    }

    public enum MuurPlaatsen {

        Left,
        Right,
        Down,
        Up
    }
    MuurPlaatsen plaatsMuur = MuurPlaatsen.Right;

    public void veranderMuurPlaatsen() {
        switch (plaatsMuur) {
            case Left:
                plaatsMuur = MuurPlaatsen.Up;
                break;
            case Right:
                plaatsMuur = MuurPlaatsen.Down;
                break;
            case Down:
                plaatsMuur = MuurPlaatsen.Left;
                break;
            case Up:
                plaatsMuur = MuurPlaatsen.Right;
                break;
        }
    }

    public List<vak> muurVakken(vak vak) {
        List<vak> vakken = new ArrayList<vak>();
       // vakken.add(vak);
        vak[][] vakkenBord = bord.getSpeelBord();
        vak testVak;
        try {
            switch (plaatsMuur) {
                case Left:
                    
                    for (int i = 0; i < wallLength; i++) {
                        testVak = vakkenBord[vak.getX() - i][vak.getY()];
                        if (testVak.isMuur() || testVak.getPion() != null) {
                            return new ArrayList<vak>();
                        }
                        vakken.add(testVak);
                    }
                    break;
                case Right:
                    for (int i = 0; i < wallLength; i++) {
                        testVak = vakkenBord[vak.getX() + i][vak.getY()];
                        if (testVak.isMuur() || testVak.getPion() != null) {
                            return new ArrayList<vak>();
                        }
                        vakken.add(testVak);
                    }
                    break;
                case Down:
                    for (int i = 0; i < wallLength; i++) {
                        testVak = vakkenBord[vak.getX()][vak.getY() + i];
                        if (testVak.isMuur() || testVak.getPion() != null) {
                            return new ArrayList<vak>();
                        }
                        vakken.add(testVak);
                    }
                    break;
                case Up:
                    for (int i = 0; i < wallLength; i++) {
                        testVak = vakkenBord[vak.getX()][vak.getY() - i];
                        if (testVak.isMuur() || testVak.getPion() != null) {
                            return new ArrayList<vak>();
                        }
                        vakken.add(testVak);
                    }
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
           vakken.clear();
        }
        return vakken;
    }

    public List<vak> getMogelijkeMuurVakken() {
        List<vak> vakken = new ArrayList<vak>();
        vak[][] vakkenBord = bord.getSpeelBord();
        for (vak[] vakLijst1 : vakkenBord) {
            for (vak vak : vakLijst1) {
                if (!vak.isMuur() && vak.getPion() == null) {
                   // vakken.addAll(muurVakken(vak));
                    List<vak> tempVakken = muurVakken(vak);
                   if( tempVakken.size() >0)
                       vakken.add(vak);
                }
            }
        }

        return vakken;
    }

    public List<vak> getMuurVakken() {
        List<vak> vakken = new ArrayList<vak>();
        vak[][] vakkenBord = bord.getSpeelBord();
        for (vak[] vakLijst1 : vakkenBord) {
            for (vak vak : vakLijst1) {
                if (vak.isMuur()) {
                    vakken.add(vak);
                }
            }
        }

        return vakken;
    }

    public void moveCurrentPlayerTo(vak vak) {
        CurrentPlayer.getPion().getHuidig().setPion(null);
        CurrentPlayer.getPion().setHuidig(vak);
        vak.setPion(CurrentPlayer.getPion());
    }
}

    
  
