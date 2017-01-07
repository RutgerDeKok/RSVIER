package model;

public class Gebruiker {


		private int gebruikerId;
		private String gebruikerType;
		private String voornaam;
		private String tussenVoegsel;
		private String achternaam;
//		private Adres adres;
		private String straat;
		private int huisNummer;
		private String huisnrToevoeging;
		private String postcode;
		private String woonplaats;
		private String phone;
		private String gebruikerToegang;
		private String gebruikerLogin;
		private String gebruikerPass;
		
		// constructor
		public Gebruiker(){}

		public Gebruiker(int gebruikerId, String gebruikerType, String voorNaam, String tussenVoegsel, 
		String achterNaam, String straat, int huisNummer, String huisnrToevoeging, String postcode,
		String woonplaats, String phone, String gebruikerToegang, String gebruikerLogin, String gebruikerPass){
			
		this.gebruikerId = gebruikerId;
		this.gebruikerType = gebruikerType;
		this.voornaam = voorNaam;
		this.tussenVoegsel = tussenVoegsel;
		this.achternaam = achterNaam;
//		this.adres = adres;
		this.straat = straat;
		this.huisNummer = huisNummer;
		this.huisnrToevoeging = huisnrToevoeging;
		this.postcode = postcode;
		this.woonplaats = woonplaats;
		this.phone = phone;
		this.gebruikerToegang = gebruikerToegang;
		this.gebruikerLogin = gebruikerLogin;
		this.gebruikerPass = gebruikerPass;
		}
		
		
		@Override
		public String toString() {
			return String.format("id=%s, type=%s, vn=%s, tv=%s, an=%s "+"\n"+
			" straat=%s, nr=%s, tvg=%s, pc=%s, plaats=%s"+"\n"+" " +
			 "phone=%s , toegang=%s, login=%s, pass=%s"+"\n",
							gebruikerId, gebruikerType, voornaam, tussenVoegsel, achternaam, straat, huisNummer,
							huisnrToevoeging, postcode, woonplaats, phone, gebruikerToegang,
							gebruikerLogin, gebruikerPass);
		}
			
			// getters and setters

		public int getGebruikerId() {
			return gebruikerId;
		}

		public void setGebruikerId(int gebruikerId) {
			this.gebruikerId = gebruikerId;
		}
		
		public String getGebruikerType() {
			return gebruikerType;
		}

		public void setGebruikerType(String gebruikerType) {
			this.gebruikerType = gebruikerType;
		}

		public String getVoorNaam() {
			return voornaam;
		}

		public void setVoorNaam(String voorNaam) {
			this.voornaam = voorNaam;
		}

		public String getTussenVoegsel() {
			return tussenVoegsel;
		}

		public void setTussenVoegsel(String tussenVoegsel) {
			this.tussenVoegsel = tussenVoegsel;
		}

		public String getAchterNaam() {
			return achternaam;
		}

		public void setAchterNaam(String achterNaam) {
			this.achternaam = achterNaam;
		}
		
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

//		public Adres getAdres() {
//			return adres;
//		}
//
//		public void setAdres(Adres adres) {
//			this.adres = adres;
//		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getGebruikerToegang() {
			return gebruikerToegang;
		}

		public void setGebruikerToegang(String gebruikerToegang) {
			this.gebruikerToegang = gebruikerToegang;
		}

		public String getGebruikerLogin() {
			return gebruikerLogin;
		}

		public void setGebruikerLogin(String gebruikerLogin) {
			this.gebruikerLogin = gebruikerLogin;
		}

		public String getGebruikerPass() {
			return gebruikerPass;
		}

		public void setGebruikerPass(String gebruikerPass) {
			this.gebruikerPass = gebruikerPass;
		}
		
		
		
}