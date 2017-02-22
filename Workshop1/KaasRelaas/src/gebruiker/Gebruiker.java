package gebruiker;


public class Gebruiker {


		private final int id;
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
		private final String login;
		private final String pass;
		
		// constructor
		public Gebruiker(GebruikerBuilder builder){
			
		this.id = 				builder.getGebruikerId();
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
		this.login = 			builder.getGebruikerLogin();
		this.pass = 			builder.getGebruikerPass();
		}
		
		
		@Override
		public String toString() {
			return String.format("id=%s, type=%s, vn=%s, tv=%s, an=%s "+"\n"+
			" straat=%s, nr=%s, tvg=%s, pc=%s, plaats=%s"+"\n"+" " +
			 "phone=%s , toegang=%s, login=%s, pass=%s"+"\n",
			id, gebruikerType, voornaam, tussenVoegsel, achternaam, straat, huisNummer,
			huisnrToevoeging, postcode, woonplaats, phone, gebruikerToegang,
			login, pass);
		}
			
			// getters 

		public int getId() {
			return id;
		}
		public GebruikerType getGebruikerType() {
			return gebruikerType;
		}
		public String getVoornaam() {
			return voornaam;
		}
		public String getTussenVoegsel() {
			return tussenVoegsel;
		}
		public String getAchternaam() {
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
		public String getTelefoon() {
			return phone;
		}
		public GebruikerToegang getGebruikerToegang() {
			return gebruikerToegang;
		}
		public String getLogin() {
			return login;
		}
		public String getPass() {
			return pass;
		}
		
		
		
		
		
		public static class GebruikerBuilder{
			
			private int gebruikerId;
			private GebruikerType gebruikerType;
			protected String voornaam;
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
			public GebruikerBuilder id(int id) {
				this.gebruikerId = id;
				return this;
			}
			public GebruikerBuilder gebruikerType(GebruikerType gebruikerType) {
				this.gebruikerType = gebruikerType;
				return this;
			}
			public GebruikerBuilder voornaam(String voornaam) {
				this.voornaam = voornaam;
				return this;
			}
			public GebruikerBuilder tussenVoegsel(String tussenVoegsel) {
				this.tussenVoegsel = tussenVoegsel;
				return this;
			}
			public GebruikerBuilder achternaam(String achternaam) {
				this.achternaam = achternaam;
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
			public GebruikerBuilder login(String login) {
				this.gebruikerLogin = login;
				return this;
			}
			public GebruikerBuilder pass(String pass) {
				this.gebruikerPass = pass;
				return this;
			}
			
			public Gebruiker build() {
				return new Gebruiker(this);
			}
			
		}
		
		
		
		
		
}