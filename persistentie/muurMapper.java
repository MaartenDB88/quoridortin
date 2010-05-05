package persistentie;



import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


public class muurMapper {
	
	private final static String LEES_MUREN_SQL = "SELECT t1.spelNaam, t1.Positie_X, t1.Positie_Y FROM MuurOpgeslagen t1";
	private final static String SAVE_MUREN_SQL = "INSERT INTO MuurOpgeslagen"
												+ "( spelNaam, Positie_X, Positie_Y )"
												+ "VALUES( ?, ?, ? )";
	
	public List< int[] > getLijstMuren( String SpelNaam ){
		
		List< int[] > muurLijst = new ArrayList< int[] >();
		Statement statement;
		Connection connection = PersistentieBeheerder.getInstance().getConnection();
		
		try{
			
			statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery( LEES_MUREN_SQL );
			
			while( resultSet.next() ){
				
				String Spel_Naam =		resultSet.getString( "Spel_Naam" );
				int Positie_X =			resultSet.getInt( "Positie_X" );
				int Positie_Y =			resultSet.getInt( "Positie_Y" );
				
				if( Spel_Naam.equals( SpelNaam )){
					
					int[] muur = { Positie_X, Positie_Y };
					muurLijst.add( muur  ); 
				}//end if
				
			}//end while
			statement.close();
			return muurLijst;
			
		}//end try
		catch( SQLException e ){
			
			e.printStackTrace();
		}//end catch
		
		return null;
	}//end method getMuren
	
	//MUREN OPSLAAN()
	public void murenOpslaan( String naam, List<int[]> muurLijst ){

		PreparedStatement preparedStatement;
		Connection connection = PersistentieBeheerder.getInstance().getConnection();
		
		
		try{
			
			preparedStatement = connection.prepareStatement( SAVE_MUREN_SQL );
			
			int i = 1;
			for( int[] muur : muurLijst ){
				
				preparedStatement.setString	( i, 	naam );
				preparedStatement.setInt	( i+1, 	muur[ i-1 ] );
				preparedStatement.setInt	( i+2, 	muur[ i ] );
				
				preparedStatement.executeUpdate();
			}//end for

			preparedStatement.close();

		}//end try
		catch( Exception e){
			System.out.println( e );
		}//end catch
		
	}//end method murenOpslaan

}//end class MuurMapper
