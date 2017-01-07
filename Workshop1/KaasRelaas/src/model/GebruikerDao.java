package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GebruikerDao {

	private Connection myConn;

	public GebruikerDao(Connection myConn) {
		this.myConn = myConn;

	}
	
	public List<Gebruiker> getAllKlantenByType(String type) throws Exception {
		List<Gebruiker> gebruikerList = new ArrayList<>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from gebruikers where gebruiker_type = \"" + type +"\"");

			while (myRs.next()) {
				Gebruiker tempGebruiker = convertRowToGebruiker(myRs);
				gebruikerList.add(tempGebruiker);
			}

			return gebruikerList;
		} finally {
			close(myStmt, myRs);
		}
	}

	public Gebruiker getGebruikerById(int Id) throws SQLException {

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from gebruikers where gebruiker_id =" + Id);

			myRs.first();
			Gebruiker gebruiker = convertRowToGebruiker(myRs);

			return gebruiker;

		} finally {
			close(myStmt, myRs);
		}

	}

	private Gebruiker convertRowToGebruiker(ResultSet myRs) throws SQLException {

		int gebruikerId = myRs.getInt("gebruiker_id");
		String gebruikerType = myRs.getString("gebruiker_type");
		String voornaam = myRs.getString("gebruiker_voornaam");
		String tussenVoegsel = myRs.getString("gebruiker_tussenvoegsel");
		String achternaam = myRs.getString("gebruiker_achternaam");
		String straat = myRs.getString("gebruiker_straat");
		int huisNummer = myRs.getInt("gebruiker_huisnummer");
		String huisnrToevoeging = myRs.getString("gebruiker_hn_toevoeging");
		String postcode = myRs.getString("gebruiker_postcode");
		String woonplaats = myRs.getString("gebruiker_woonplaats");
		String phone = myRs.getString("gebruiker_telefoonnr");
		String gebruikerToegang = myRs.getString("gebruiker_toegang");
		String gebruikerLogin = myRs.getString("gebruiker_login");
		String gebruikerPass = myRs.getString("gebruiker_pass");

		Gebruiker tempGebruiker = new Gebruiker(gebruikerId, gebruikerType, voornaam, tussenVoegsel, achternaam, straat,
				huisNummer, huisnrToevoeging, postcode, woonplaats, phone, gebruikerToegang, gebruikerLogin,
				gebruikerPass);

		return tempGebruiker;
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			myStmt.close();
		}
	}

}
