package domein;
import java.util.List;

import persistentie.PersistentieBeheerder;

public class DomeinBeheerder {
	private PersistentieBeheerder persistentieBeheerder;
	
	private List <Pion> pionnen;
	private List<Speler> spelers;
	 public DomeinBeheerder() {
		 persistentieBeheerder = PersistentieBeheerder.getInstance();
	 }
	 public List <Pion> getPion(){
		 if (pionnen == null){
			 pionnen = persistentieBeheerder.getPionnen();}
		 return pionnen;
		 
	 }
	 public List <Speler> getLijstSpelers(){
		 if ( spelers == null){
			 spelers = persistentieBeheerder.getLijstSpelers();}
		 return spelers;
	 }
	 
		 public List <vak> getbord(){
			 return persistentieBeheerder.getbord();}
		 
		public Spel getSpel (String NaamSpel) {
			return persistentieBeheerder.getSpel(NaamSpel);}
		
		 
	 
	 }
	
	
	


