package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Gebruiker;
import model.GebruikerToegang;
import model.GebruikerType;

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

		
		Gebruiker tempGebruiker = new Gebruiker.GebruikerBuilder()
				.gebruikerId		(myRs.getInt("gebruiker_id"))
				.gebruikerType		(GebruikerType.valueOf(myRs.getString("gebruiker_type")))
				.voorNaam			(myRs.getString("gebruiker_voornaam"))
				.tussenVoegsel		(myRs.getString("gebruiker_tussenvoegsel"))
				.achterNaam			(myRs.getString("gebruiker_achternaam"))
				.straat				(myRs.getString("gebruiker_straat"))
				.huisNummer			(myRs.getInt("gebruiker_huisnummer"))
				.huisnrToevoeging	(myRs.getString("gebruiker_hn_toevoeging"))
				.postcode			(myRs.getString("gebruiker_postcode"))
				.woonplaats			(myRs.getString("gebruiker_woonplaats"))
				.phone				(myRs.getString("gebruiker_telefoonnr"))
				.gebruikerToegang	(GebruikerToegang.valueOf(myRs.getString("gebruiker_toegang")))
				.gebruikerLogin		(myRs.getString("gebruiker_login"))
				.gebruikerPass		(myRs.getString("gebruiker_pass"))
				.build();

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
