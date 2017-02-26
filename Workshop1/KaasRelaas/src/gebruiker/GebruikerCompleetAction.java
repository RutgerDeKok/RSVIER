package gebruiker;

import gebruiker.panels.AbstractGebruikerPanel;
import gebruiker.panels.MedewerkersPanel;
import main.KaasAppMain;
import main.MainController;

public class GebruikerCompleetAction {
	

	public static void performAction(MainController controller, boolean isKlant) {
		KaasAppMain.logger.debug("start performAction");
		
		AbstractGebruikerPanel gebrPanel;
		GebruikerType gebruikerType;
		GebruikerToegang gebruikerToegang;
		String gebruikerLogin;
		String pw;
		
		
		if(isKlant){
			gebruikerType = GebruikerType.KLANT;
			gebruikerToegang = GebruikerToegang.NONE;
			gebruikerLogin = "";
			pw = null;
			gebrPanel = controller.getView().getMainpanel().getKlantenPanel();
			
			
		} else{
			KaasAppMain.logger.debug("updating medewerker");
			
			
			gebrPanel = controller.getView().getMainpanel().getMedewerkersPanel();
			if(gebrPanel.getGebruikerId()==0 && (gebrPanel.getPassword()==null|| gebrPanel.getLogin() ==null || gebrPanel.getLogin().length()<3)){
				KaasAppMain.logger.warn("nieuwe medewerkers hebben geldige login en pw nodig");
				return;
			}

			
			MedewerkersPanel panel = (MedewerkersPanel)gebrPanel;
			gebruikerType = panel.getGebruikerType();
			gebruikerToegang = panel.getGebruikerToegang();
			gebruikerLogin = panel.getLogin();
			pw = panel.getPassword();
			
			
		}
		
		// postcode
		String postcode =gebrPanel.getPostcode().toUpperCase().replaceAll("\\s","");
		if(!GebruikerValidator.checkPostcode(postcode))return;
		
		
		
		// check vallues that may not be empty, 
		String achternaam = gebrPanel.getAchternaam();
		String straat = gebrPanel.getStraat();
		String woonplaats = gebrPanel.getPlaats();
		Integer huisNummer = gebrPanel.getHuisNummer();
		String telefoon = gebrPanel.getTelefoon();
		
		
		// check if mandatory fields are filled in
		if(achternaam==null|| straat == null || woonplaats ==null || huisNummer == null || telefoon == null ||
				achternaam.isEmpty()|| straat.isEmpty()|| woonplaats.isEmpty()|| huisNummer<1 || telefoon.isEmpty()){
			KaasAppMain.logger.warn("Nodig: achternaam, straat, nummer, postcode, plaats, telefoon");
			return;
		}
		
		
		// build Gebruiker
		Gebruiker tempGebruiker = new Gebruiker.GebruikerBuilder()
				
				.id 		(gebrPanel.getGebruikerId())
				.gebruikerType 		(gebruikerType)
				.voornaam 	 		(gebrPanel.getVoornaam())
				.tussenVoegsel  	(gebrPanel.getTussenvgs())
				.achternaam  		(achternaam)
				.straat  			(straat)
				.huisNummer 		(huisNummer)
				.huisnrToevoeging  	(gebrPanel.getToevoeg())
				.postcode 			(postcode)
				.woonplaats  		(woonplaats)
				.phone 				(telefoon)
				.gebruikerToegang 	(gebruikerToegang)
				.login  	(gebruikerLogin)
				.pass  	(pw)
				.build();

		KaasAppMain.logger.debug("gebruiker = "+tempGebruiker);
		controller.getView().getMainpanel().getMedewerkersPanel().setCard("overzichtsPanel");
		controller.getView().getMainpanel().getKlantenPanel().setCard("overzichtsPanel");
		controller.getGebruikerController().sendGebruikertoDB(tempGebruiker);
		controller.getView().getMainpanel().getMedewerkersPanel().updateAction();
		controller.getView().getMainpanel().getKlantenPanel().updateAction();
		
	}



}
