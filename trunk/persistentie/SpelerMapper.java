package persistentie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import domein.Speler;
import domein.Pion;
import domein.vak;
import domein.Spel;

public class SpelerMapper {

    private final static String LEES_SPELER_SQL = "SELECT * FROM SpelerOpgeslagen t1";
    private final static String SAVE_SPELER_SQL = "INSERT INTO SpelerOpgeslagen"
            + "( spelNaam, SpelerID, spelerNaam, aantalMuren, SymboolPion, Start_X, Start_Y, Hui_X, Hui_Y )"
            + "VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ? )";
    private PionMapper pionMapper;
    private List<Pion> lijstPionnen;

    //GETLIJSTSPELERS()
    public List<Speler> getSpelers(String SpelNaam, Spel spel) {

        List<Speler> Speler = new ArrayList<Speler>();
        pionMapper = new PionMapper();
        lijstPionnen = pionMapper.getLijstPionnen();

        Statement statement;
        Connection connection = PersistentieBeheerder.getInstance().getConnection();



        try {

            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(LEES_SPELER_SQL);

            while (resultSet.next()) {

                String Spel_Naam = resultSet.getString("spelNaam");
                int Speler_ID = resultSet.getInt("SpelerID");
                String Speler_Naam = resultSet.getString("spelerNaam");
                int aantalMuren = resultSet.getInt("aantalMuren");
                String Pion_Symbool = resultSet.getString("SymboolPion");
                int StartX = resultSet.getInt("Start_X");
                int StartY = resultSet.getInt("Start_Y");
                int HuiX = resultSet.getInt("Hui_X");
                int HuiY = resultSet.getInt("Hui_Y");

                if (Spel_Naam.equals(SpelNaam)) {
                    Pion correctPion = null;
                    for (Pion pion : lijstPionnen) {

                        if (pion.getSymbool().equals(Pion_Symbool)) {
                            vak[][] vakken = spel.getBord().getSpeelBord();
                            pion.setStart(vakken[StartX][StartY]);
                            pion.setHuidig(vakken[HuiX][HuiY]);
                            vakken[HuiX][HuiY].setPion(pion);
                            correctPion = pion;
                        }//end if

                    }//end for
                    Speler.add(new Speler(Speler_ID, Speler_Naam, correctPion, aantalMuren));
                }//end if
            }//end while
            statement.close();
            return Speler;

        }//end try
        catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }//end catch

        return null;
    }//end method getLijstSpelers

    //SPELERS OPSLAAN()
    public void spelersOpslaan(String naam, List<Speler> lijstSpelers) {


        PreparedStatement preparedStatement;
        Connection connection = PersistentieBeheerder.getInstance().getConnection();

        try {

            preparedStatement = connection.prepareStatement(SAVE_SPELER_SQL);

            preparedStatement.setString(1, naam);


            //( Spel_Naam, Speler_ID, Speler_Naam, Speler_Muren, Pion_Symbool, Start_X, Start_Y, Hui_X, Hui_Y )
            int i = 1;
            for (Speler s : lijstSpelers) {

                preparedStatement.setString(i, naam);
                preparedStatement.setInt(i + 1, s.getNummer());
                preparedStatement.setString(i + 2, s.getNaam());
                preparedStatement.setInt(i + 3, s.getMuur());
                preparedStatement.setString(i + 4, s.getPion().getSymbool());
                preparedStatement.setInt(i + 5, s.getPion().getStart().getX());
                preparedStatement.setInt(i + 6, s.getPion().getStart().getY());
                preparedStatement.setInt(i + 7, s.getPion().getHuidig().getX());
                preparedStatement.setInt(i + 8, s.getPion().getHuidig().getY());

                preparedStatement.executeUpdate();
            }//end for
            preparedStatement.close();

        }//end try
        catch (Exception e) {

            System.out.println(e);
        }//end catch

    }//end method spelersOpslaan
}//end class SpelerMapper

