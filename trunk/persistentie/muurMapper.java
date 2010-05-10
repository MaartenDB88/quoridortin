package persistentie;

import domein.vak;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class muurMapper {

    private final static String LEES_MUREN_SQL = "SELECT * FROM MuurOpgeslagen t1";
    private final static String SAVE_MUREN_SQL = "INSERT INTO MuurOpgeslagen"
            + "( spelNaam, Positie_X, Positie_Y )"
            + "VALUES( ?, ?, ? )";

    public List<vak> getLijstMuren(String SpelNaam) {

        List<vak> muurLijst = new ArrayList<vak>();
        Statement statement;
        Connection connection = PersistentieBeheerder.getInstance().getConnection();

        try {

            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(LEES_MUREN_SQL);

            while (resultSet.next()) {

                String Spel_Naam = resultSet.getString("spelNaam");
                int Positie_X = resultSet.getInt("Positie_X");
                int Positie_Y = resultSet.getInt("Positie_Y");

                if (Spel_Naam != null && Spel_Naam.equals(SpelNaam)) {

                    muurLijst.add(new vak(Positie_X, Positie_Y, true, null));
                }

            }
            statement.close();
            return muurLijst;

        }
        catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }

   
    public void murenOpslaan(String naam, List<vak> muurLijst) {

        PreparedStatement preparedStatement;
        Connection connection = PersistentieBeheerder.getInstance().getConnection();


        try {

            preparedStatement = connection.prepareStatement(SAVE_MUREN_SQL);

            int i = 1;
            for (vak muur : muurLijst) {

                preparedStatement.setString(i, naam);
                preparedStatement.setInt(i + 1, muur.getX());
                preparedStatement.setInt(i + 2, muur.getY());

                preparedStatement.executeUpdate();
            }

            preparedStatement.close();

        }
        catch (Exception e) {
            System.out.println(e);
        }

    }
}

