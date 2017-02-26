package gebruiker;

import main.KaasAppMain;

public class GebruikerValidator {
	
	
	public static boolean checkPostcode(String postcode) {
		//check postcode
		boolean isValid = false;
				
				if(postcode.toString().matches("^\\d{4}[A-Z]{2}")){
					KaasAppMain.logger.warn("postcode onjuist");
					isValid = true;
				}
		return isValid;
	} 

}
