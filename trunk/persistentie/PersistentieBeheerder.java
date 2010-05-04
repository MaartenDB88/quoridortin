package persistentie;


import java.util.List;
import java.sql.Connection;

import domein.Pion;
import domein.Speler;
import domein.Spel;
import domein.vak;

public class PersistentieBeheerder {
	
	
	private static PersistentieBeheerder persistentieBeheerder;
	private PionMapper pionMapper;
	private SpelMapper spelMapper;
	private ConnectieDb connectie;

	
	
	//GETINSTANCE()
	public static PersistentieBeheerder getInstance(){
		
		if( persistentieBeheerder == null ){
			
			persistentieBeheerder = new PersistentieBeheerder();
		}//end if
		
		return persistentieBeheerder;
	}//end method getInstance()
	
	
	//PRIVATE CONSTRUCTOR
	private PersistentieBeheerder(){
		
		connectie = new ConnectieDb();
		pionMapper = new PionMapper();
		spelMapper = new SpelMapper();
		
	}//end private constructor
	
	
	//GETCONNECTION()
	public Connection getConnection(){
		
		return connectie.getConnection();
	}//end method getConnection
	
	
	
	//CLOSECONNECTION()
	public void closeConnection(){
		
		connectie.closeConnection();
	}//end method closeConnection
	
	
	
	//GETPIONNEN()
	public List<Pion> getPionnen(){
		
		return pionMapper.getLijstPionnen();
	}//end method getPionnen
	
	//GET SPEELBORD()
	public List<vak> getbord(){
		
		return spelMapper.getbord();
	}
	
	
	//GETLIJSTSG()
	public List<String> getSaveGames(){
		
		return spelMapper.getSaveGames();
	}//end method getLijstSG
	
	
	//GET SPEL()
	public Spel getSpel( String NaamSpel ){
		
		return spelMapper.getSpel( NaamSpel);
	}//end method getSpel
	
	
	//GETLIJSTSPELERS()
	public List<Speler> getLijstSpelers(){
		
		return spelMapper.getLijstSpelers();
	}//end method getLijstSpelers
	
	//GET HUIDIGE SPELER ID()
	public int getHui_ID(){
		
		return spelMapper.getHui_ID();
	}//end method getHuidigeSpeler
	
	
	//GETLIJSTPIONNENSG()
	public List<Pion> getLijstPionnenSG(){
		
		return spelMapper.getPionnen();
	}//end method getLijstPionnenSG
	
	
	//GETLIJSTMUREN()
	public List< int[] > getLijstMuren(){
		
		return spelMapper.getMuren();
	}//end method getLijstMuren
	
	//SPEL OPSLAAN()
	public void spelOpslaan( String naam, int d, int id, List<Speler> lijstSpelers, List<int[]> lijstMuren ){
		
		spelMapper.spelOpslaan( naam, d, id, lijstSpelers, lijstMuren);
	}//end method spelOpslaan

}//end class PersistentieBeheerder
