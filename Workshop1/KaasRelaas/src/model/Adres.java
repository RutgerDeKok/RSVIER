package model;

public class Adres {

	private String straat;
	private int huisNummer;
	private String huisnrToevoeging;
	private String postcode;
	private String woonplaats;
	
	//constructor
	public Adres(String straat, int huisNummer, String huisnrToevoeging, 
			String postcode, String woonplaats) {

		this.straat = straat;
		this.huisNummer =huisNummer;
		this.huisnrToevoeging = huisnrToevoeging;
		this.postcode = postcode;
		this.woonplaats = woonplaats;
	}
	
// getters and setters
	public String getStraat() {
		return straat;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	public int getHuisNummer() {
		return huisNummer;
	}

	public void setHuisNummer(int huisNummer) {
		this.huisNummer = huisNummer;
	}

	public String getHuisnrToevoeging() {
		return huisnrToevoeging;
	}

	public void setHuisnrToevoeging(String huisnrToevoeging) {
		this.huisnrToevoeging = huisnrToevoeging;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getWoonplaats() {
		return woonplaats;
	}

	public void setWoonplaats(String woonplaats) {
		this.woonplaats = woonplaats;
	}

}
