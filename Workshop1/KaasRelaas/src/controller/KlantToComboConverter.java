package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Gebruiker;

public class KlantToComboConverter {
	
	

	
	public List<String> getComboList(List<Gebruiker> klantList) throws SQLException {
		int l = klantList.size();
		
		List<String> comboItems = new ArrayList<String>();
		comboItems.add("- Kies een klant -                                           ");
		 for(int i=0;i<l;i++){
			 comboItems.add(convertKlantToString(klantList.get(i)));
			 
		 }
	
		return comboItems;
	}
	
	

	private String convertKlantToString(Gebruiker tempKlant) throws SQLException{
		StringBuilder klantString = new StringBuilder();
		
		
		klantString.append(tempKlant.getAchterNaam()+ ", ");
		klantString.append(tempKlant.getTussenVoegsel()+ "  ");
		klantString.append(tempKlant.getVoorNaam());
		
		return klantString.toString();
	}
	
	
	
	
	
	
	

}
