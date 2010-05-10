package domein;

import java.util.ArrayList;
import java.util.List;
import persistentie.PersistentieBeheerder;
import domein.Spel;

public class Domeincontroller {

    private PersistentieBeheerder persistentieBeheerder;
    private Spel spel;
    private DomeinBeheerder db;

    public Spel getSpel() {
        return spel;
    }

    public Domeincontroller() {
        persistentieBeheerder = PersistentieBeheerder.getInstance();
        db = new DomeinBeheerder();
    }

    public boolean saveGame(String naam) {
        persistentieBeheerder.spelOpslaan(naam, spel);
        return true;
    }

    public boolean loadGame(String naam) {
        spel = persistentieBeheerder.getSpel(naam);
        spel.StartSpel();
        return true;

    }

    public List<String> getSavedGamesName() {
        return persistentieBeheerder.getSaveGames();
    }

    public void createBord(int dimensie) {
        spel = new Spel(dimensie);
    }

    public void beginNieuwSpel(int aantalSpelers) {
        spel.setCurrentPlayerID(0);
    }

    public String getBordPaneel() {
        return spel.getBord().toString();
    }

    public boolean controleerSpelerNaam(List<Speler> spelers, String naamKeuze) {

        for (Speler speler : spelers) {
            if (speler.getNaam().trim().equals(naamKeuze.trim())) {
                return true;
            }
        }

        return false;
    }

    public String[] geefBeschikbareKleuren() {


        List<Pion> lijst = PersistentieBeheerder.getInstance().getPionnen();
        String[] kleuren = new String[lijst.size()];
        for (int i = 0; i < lijst.size(); i++) {
            kleuren[i] = lijst.get(i).getKleurString();
        }

        return kleuren;
    }

    public void maakNieuweSpeler(List<Speler> spelers, int wallCount) {
        spel.maakSpelers(spelers, wallCount);
    }

    public List<String[]> getPionInfo() {
        return spel.getPionInfo();
    }

    public List<Pion> getPionen() {
        return db.getPion();
    }

    public boolean goToNextGameStep() {
        return spel.goToNextStep();

    }

    public String getNextGameStep() {
        return spel.getNextGameStep();

    }

    public void setMuur(vak vak) {

        spel.setMuur(vak);
    }
}


