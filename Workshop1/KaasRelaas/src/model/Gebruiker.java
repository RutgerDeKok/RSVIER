package model;

public class Gebruiker {


		private final int gebruikerId;
		private final GebruikerType gebruikerType;
		private final String voornaam;
		private final String tussenVoegsel;
		private final String achternaam;
		private final String straat;
		private final int huisNummer;
		private final String huisnrToevoeging;
		private final String postcode;
		private final String woonplaats;
		private final String phone;
		private final GebruikerToegang gebruikerToegang;
		private final String gebruikerLogin;
		private final String gebruikerPass;
		
		// constructor
		public Gebruiker(GebruikerBuilder builder){
			
		this.gebruikerId = 		builder.getGebruikerId();
		this.gebruikerType = 	builder.getGebruikerType();
		this.voornaam = 		builder.getVoorNaam();
		this.tussenVoegsel = 	builder.getTussenVoegsel();
		this.achternaam = 		builder.getAchterNaam();
		this.straat = 			builder.getStraat();
		this.huisNummer = 		builder.getHuisNummer();
		this.huisnrToevoeging = builder.getHuisnrToevoeging();
		this.postcode = 		builder.getPostcode();
		this.woonplaats = 		builder.getWoonplaats();
		this.phone = 			builder.getPhone();
		this.gebruikerToegang = builder.getGebruikerToegang();
		this.gebruikerLogin = 	builder.getGebruikerLogin();
		this.gebruikerPass = 	builder.getGebruikerPass();
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
			
			// getters 

		public int getGebruikerId() {
			return gebruikerId;
		}
		public GebruikerType getGebruikerType() {
			return gebruikerType;
		}
		public String getVoorNaam() {
			return voornaam;
		}
		public String getTussenVoegsel() {
			return tussenVoegsel;
		}
		public String getAchterNaam() {
			return achternaam;
		}
		public String getStraat() {
			return straat;
		}
		public int getHuisNummer(){
			return huisNummer;
		}
		public String getHuisnrToevoeging() {
			return huisnrToevoeging;
		}
		public String getPostcode() {
			return postcode;
		}
		public String getWoonplaats() {
			return woonplaats;
		}
		public String getPhone() {
			return phone;
		}
		public GebruikerToegang getGebruikerToegang() {
			return gebruikerToegang;
		}
		public String getGebruikerLogin() {
			return gebruikerLogin;
		}
		public String getGebruikerPass() {
			return gebruikerPass;
		}
		
		
		
		
		
		public static class GebruikerBuilder{
			
			private int gebruikerId;
			private GebruikerType gebruikerType;
			private String voornaam;
			private String tussenVoegsel;
			private String achternaam;
			private String straat;
			private int huisNummer;
			private String huisnrToevoeging;
			private String postcode;
			private String woonplaats;
			private String phone;
			private GebruikerToegang gebruikerToegang;
			private String gebruikerLogin;
			private String gebruikerPass;
			
			
			// getters

			public int getGebruikerId() {
				return gebruikerId;
			}
			public GebruikerType getGebruikerType() {
				return gebruikerType;
			}
			public String getVoorNaam() {
				return voornaam;
			}
			public String getTussenVoegsel() {
				return tussenVoegsel;
			}
			public String getAchterNaam() {
				return achternaam;
			}
			public String getStraat() {
				return straat;
			}
			public int getHuisNummer(){
				return huisNummer;
			}
			public String getHuisnrToevoeging() {
				return huisnrToevoeging;
			}
			public String getPostcode() {
				return postcode;
			}
			public String getWoonplaats() {
				return woonplaats;
			}
			public String getPhone() {
				return phone;
			}
			public GebruikerToegang getGebruikerToegang() {
				return gebruikerToegang;
			}
			public String getGebruikerLogin() {
				return gebruikerLogin;
			}
			public String getGebruikerPass() {
				return gebruikerPass;
			}
			
			
			//setters
			public GebruikerBuilder gebruikerId(int gebruikerId) {
				this.gebruikerId = gebruikerId;
				return this;
			}
			public GebruikerBuilder gebruikerType(GebruikerType gebruikerType) {
				this.gebruikerType = gebruikerType;
				return this;
			}
			public GebruikerBuilder voorNaam(String voorNaam) {
				this.voornaam = voorNaam;
				return this;
			}
			public GebruikerBuilder tussenVoegsel(String tussenVoegsel) {
				this.tussenVoegsel = tussenVoegsel;
				return this;
			}
			public GebruikerBuilder achterNaam(String achterNaam) {
				this.achternaam = achterNaam;
				return this;
			}
			public GebruikerBuilder straat(String straat) {
				this.straat = straat;
				return this;
			}
			public GebruikerBuilder huisNummer(int huisNummer) {
				this.huisNummer = huisNummer;
				return this;
			}
			public GebruikerBuilder huisnrToevoeging(String huisnrToevoeging) {
				this.huisnrToevoeging = huisnrToevoeging;
				return this;
			}
			public GebruikerBuilder postcode(String postcode) {
				this.postcode = postcode;
				return this;
			}
			public GebruikerBuilder woonplaats(String woonplaats) {
				this.woonplaats = woonplaats;
				return this;
			}
			public GebruikerBuilder phone(String phone) {
				this.phone = phone;
				return this;
			}
			public GebruikerBuilder gebruikerToegang(GebruikerToegang gebruikerToegang) {
				this.gebruikerToegang = gebruikerToegang;
				return this;
			}
			public GebruikerBuilder gebruikerLogin(String gebruikerLogin) {
				this.gebruikerLogin = gebruikerLogin;
				return this;
			}
			public GebruikerBuilder gebruikerPass(String gebruikerPass) {
				this.gebruikerPass = gebruikerPass;
				return this;
			}
			
			public Gebruiker build() {
				return new Gebruiker(this);
			}
			
		}
		
		
		
		
		
}