package gebruiker;

import java.util.ArrayList;
import java.util.List;

import main.Model;


public class GebruikerController {

	private Model theModel;

	public GebruikerController(Model model) throws Exception {
		theModel = model;
	}
	


	public String[][] convertGebruikersToTableData(List<Gebruiker> klantenList) {
		int l = klantenList.size();
		String[][] data = new String[l][5];
		for (int i = 0; i < l; i++) {
			data[i] = convertGebruikerToRow(klantenList.get(i));

		}

		return data;
	}
	
	
	private String[] convertGebruikerToRow(Gebruiker gebruiker) {
		// id naam prijs aantal
		String[] dataRow = new String[5];

		String gebruikerString = convertNamenToString(gebruiker);
		String postcodeNummer = convertCodeNumToString(gebruiker);
		
		dataRow[0] = Integer.toString(gebruiker.getId());
		dataRow[1] = gebruikerString;
		dataRow[2] = postcodeNummer;
		if(gebruiker.getGebruikerType()==GebruikerType.KLANT){
			dataRow[3] = gebruiker.getWoonplaats();
		}else{
		dataRow[3] = gebruiker.getGebruikerToegang().toString();
		}
		dataRow[4] = "-Edit-";

		return dataRow;
	}
	
	
	private String convertCodeNumToString(Gebruiker gebruiker) {
		StringBuilder codeString = new StringBuilder();

		codeString.append(gebruiker.getPostcode() + ", ");
		codeString.append(gebruiker.getHuisNummer() + "  ");
		codeString.append(gebruiker.getHuisnrToevoeging());
		return codeString.toString();
	}

	public List<String> getKlantComboList(List<Gebruiker> klantList) {
		int l = klantList.size();

		List<String> comboItems = new ArrayList<String>();
		comboItems.add("- Kies een klant -                                           ");
		for (int i = 0; i < l; i++) {
			comboItems.add(convertNamenToString(klantList.get(i)));

		}
		return comboItems;
	}

	private String convertNamenToString(Gebruiker tempKlant) {
		StringBuilder klantString = new StringBuilder();

		klantString.append(tempKlant.getAchternaam() + ", ");
		klantString.append(tempKlant.getTussenVoegsel() + "  ");
		klantString.append(tempKlant.getVoornaam());

		return klantString.toString();
	}



	public void sendGebruikertoDB(Gebruiker gebruiker) {
		theModel.getGebruikerDao().saveOrUpdate(gebruiker);
	}
	
	


	
	
}


