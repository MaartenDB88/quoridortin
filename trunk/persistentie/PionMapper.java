package persistentie;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domein.Pion;

public class PionMapper {
	
	//private final static String LEES_PIONNEN_SQL = "SELECT t1.Symbool, t1.Kleur FROM Pionnen t1";
	private final static String LEES_PIONNEN_SQL = "SELECT Symbool, Kleur FROM PIONNEN";
	
	public List<Pion> getLijstPionnen(){
		
		List<Pion> lijstPionnen = new ArrayList<Pion>();
		Statement statement;
		
		
		//maak instantie van PersistentieBeheerder + roep method getConnection aan
		Connection connection = PersistentieBeheerder.getInstance().getConnection();
		
		try{
			
			//maak statement voor database querying
			statement = connection.createStatement();
			
			//query database en wijs toe aan resultset
			ResultSet resultSet = statement.executeQuery( LEES_PIONNEN_SQL );
			
			//doorloop resultSet en wijs toe aan parameters
			while( resultSet.next() ){
				
				String symbool = 	resultSet.getString( "Symbool" );
				String kleur =		resultSet.getString( "Kleur" );
				
				
				//voeg opgehaalde data toe aan lijstPionnen
				lijstPionnen.add( new Pion( symbool, kleur ));
				
			}//end while
			statement.close();
			
			
		}//end try
		catch( SQLException e ){
			
			e.printStackTrace();
		}//end catch
		
		return lijstPionnen;
		
	}//end method getLijstPionnen

}//end class PionMapper

