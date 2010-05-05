import domein.Speler;
package persistentie;

public class SpelerMapper {


package persistentie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;



		private final static String LEES_SPELERS_SQL = "SELECT spelNaam, spelerID, SpelerVoornaam, aantalMuren, StartX, StartY, HUIX, HUIY FROM spelerOpgeslagen";
		private final static String SAVE_SPELERS_SQL = "INSERT INTO SpelerOpgeslagen"
			+ "( spelNaam, SpelerID, spelerNaam, aantalMuren, SymboolPion, Start_X, Start_Y, Hui_X, Hui_Y )"
			+ "VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ? )";

		public List<spelers> geefSpeler()
		{
			List<spelers> Lijst = new ArrayList<speler>();

			// create Statement for querying database
                        Statement statement;
			Connection connection = PersistentieBeheerder.getInstance().getConnection();
			try {
				statement = connection.createStatement();

				// query database
				ResultSet resultSet = statement.executeQuery(LEES_KLANTEN_SQL);

				while (resultSet.next()) {

					String spelNaam = resultSet.getString("spelNaam");
					String spelerID= resultSet.getString("SpelerID");
					String SpelerVoornaam= resultSet.getString("spelerVoornaam");
					int aantalMuren= resultSet.getInt("aantalMuren");
					int StartX= resultSet.getInt("Start_X");
					int StartY= resultSet.getInt("Start_Y");
					int HUIX= resultSet.getInt("HUI_X");
					int HUIY= resultSet.getInt("HUI_Y");

					Speler spelers = new Speler(spelNaam, spelerID, SpelerVoornaam, aantalMuren, StartX, StartY, HUIX, HUIY);
					Lijst.add(spelers);
				}
				statement.close();
				return Lijst;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

		private PreparedStatement initWijzigKlant(Connection connection) {
			try {
				PreparedStatement pStatement = connection
						.prepareStatement(UPDATE_SQL);
				return pStatement;
			}
			// detect problems interacting with the database
			catch (SQLException sqlException) {
				JOptionPane.showMessageDialog(null, sqlException.getMessage(),
						"Database Error", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
			return null;
		}
}

