package persistentie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


import domein.*;

public class SpelMapper {

    private SpelerMapper spelerMapper;
    private PionMapper pionMapper;
    private muurMapper muurMapper;
    private List<String> lijstSaveGames;
    private List<Speler> Speler;
    private List<Pion> lijstPionnen;
    private List<vak> muurLijst;
    private Spel spel;
    private List<vak> spelbord;
    private int d;
    private int id;
    private final static String LEES_SPEL_SQL = "SELECT t1.SpelNaam, t1.Dimensie, t1.HuidigeSpeler FROM Spel t1";
    private final static String SAVE_SPEL_SQL = "INSERT INTO Spel"
            + "(SpelNaam, Dimensie, HuidigeSpeler,LengteMuur)"
            + "VALUES( ?, ?, ?,? )";

    //GET SAVEGAMES
    public List<String> getSaveGames() {

        Statement statement;
        lijstSaveGames = new ArrayList<String>();

        //maak instantie van PersistentieBeheerder + roep method getConnection aan
        Connection connection = PersistentieBeheerder.getInstance().getConnection();

        try {

            //maak statement voor database querying
            statement = connection.createStatement();

            //query database en wijs toe aan resultset
            ResultSet resultSet = statement.executeQuery(LEES_SPEL_SQL);

            //doorloop resultSet en wijs toe aan parameters
            while (resultSet.next()) {

                String Spel_Naam = resultSet.getString("SpelNaam");
                if (Spel_Naam != null) {
                    lijstSaveGames.add(new String(Spel_Naam));
                }

            }//end while
            statement.close();
            return lijstSaveGames;
        }//end try
        catch (SQLException e) {

            e.printStackTrace();
        }//end catch

        return null;
    }//end method getSaveGames

    //GET SPEL()
    public Spel getSpel(String SpelNaam) {

        Statement statement;

        //maak instantie van PersistentieBeheerder + roep method getConnection aan
        Connection connection = PersistentieBeheerder.getInstance().getConnection();

        try {

            //maak statement voor database querying
            statement = connection.createStatement();

            //query database en wijs toe aan resultset
            ResultSet resultSet = statement.executeQuery(LEES_SPEL_SQL);

            //doorloop resultSet en wijs toe aan parameters
            while (resultSet.next()) {

                String Spel_Naam = resultSet.getString("SpelNaam");
                int Dimensie = resultSet.getInt("Dimensie");
                int Hui_ID = resultSet.getInt("HuidigeSpeler");

                if (Spel_Naam != null && Spel_Naam.equals(SpelNaam)) {
                    d = Dimensie;
                    id = Hui_ID;
                }//end if

            }//end while
            statement.close();

            //maak nieuw spel en nieuw speelbord
            spel = new Spel(d);


            //maak instanties van mappers
            spelerMapper = new SpelerMapper();
            muurMapper = new muurMapper();
            pionMapper = new PionMapper();

            //wijs lijsten toe
            lijstPionnen = pionMapper.getLijstPionnen();
            Speler = spelerMapper.getSpelers(SpelNaam, spel);
            spel.setSpelers(Speler);
            muurLijst = muurMapper.getLijstMuren(SpelNaam);
            spel.setMuren(muurLijst);
            return spel;
        }//end try
        catch (SQLException e) {

            e.printStackTrace();
        }//end catch

        return null;
    }//end method getSpel

    //GET PIONNEN()
    public List<Pion> getPionnen() {

        return lijstPionnen;
    }//end method getPionnen

    //GET SPEELBORD()
    public List<vak> getSpelbord() {

        return spelbord;
    }//end method getSpeelbord

    //GET LIJST SPELERS
    public List<Speler> getLijstSpelers() {

        return Speler;
    }//end method getLijstSpeler

    //GET MUREN
    public List<vak> getMuren() {

        return muurLijst;
    }//end method getMuren

    //GET HUIDIGE SPELER ID()
    public int getHui_ID() {
        return id;
    }//end method getHuidigeSpeler

    //SPEL OPSLAAN()
    public void spelOpslaan(String naam, int d, List<Speler> lijstSpelers, List<vak> lijstMuren, int muurLengte) {

//        if (getSaveGames().contains(naam)) {
//            throw new IllegalArgumentException("naam_bestaat_al");
//        }

        spelerMapper = new SpelerMapper();
        muurMapper = new muurMapper();

        spelerMapper.spelersOpslaan(naam, lijstSpelers);
        muurMapper.murenOpslaan(naam, lijstMuren);


        PreparedStatement preparedStatement;

        //maak instantie van PersistentieBeheerder + roep method getConnection aan
        Connection connection = PersistentieBeheerder.getInstance().getConnection();

        try {

            //prepare statement voor database schrijven
            preparedStatement = connection.prepareStatement(SAVE_SPEL_SQL);

            preparedStatement.setString(1, naam);
            preparedStatement.setInt(2, d);
            preparedStatement.setInt(3, id);
            preparedStatement.setInt(4, muurLengte);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        }//end try
        catch (Exception e) {

            System.out.println(e);
        }//end catch

    }//end method spelOpslaan
}//end class SpelMapper

