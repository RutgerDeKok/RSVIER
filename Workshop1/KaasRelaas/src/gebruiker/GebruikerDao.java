package gebruiker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.DaoInterface;


public class GebruikerDao implements DaoInterface<Gebruiker>{

	private Connection myConn;

	public GebruikerDao(Connection myConn) {
		this.myConn = myConn;

	}

	public List<Gebruiker> getAllByType(GebruikerType type, boolean include) throws Exception {

		List<Gebruiker> gebruikerList = new ArrayList<>();
		String queryPart;
		if (include) {
			queryPart = "";
		} else {
			queryPart = "not";
		}

		try (PreparedStatement myStmt = myConn
				.prepareStatement("select * from gebruikers where " + queryPart + " gebruiker_type = ?")) {

			myStmt.setString(1, type.name());

			try (ResultSet myRs = myStmt.executeQuery()) {

				while (myRs.next()) {
					Gebruiker tempGebruiker = convertRowToGebruiker(myRs);
					gebruikerList.add(tempGebruiker);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return gebruikerList;

	}

	public Gebruiker getAllById(int Id) throws SQLException {

		Gebruiker gebruiker = null;

		try (Statement myStmt = myConn.createStatement();
				ResultSet myRs = myStmt.executeQuery("select * from gebruikers where gebruiker_id =" + Id)) {

			myRs.first();
			gebruiker = convertRowToGebruiker(myRs);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return gebruiker;

	}

	private Gebruiker convertRowToGebruiker(ResultSet myRs) throws SQLException {

		Gebruiker tempGebruiker = new Gebruiker.GebruikerBuilder().id(myRs.getInt("gebruiker_id"))
				.gebruikerType(GebruikerType.valueOf(myRs.getString("gebruiker_type")))
				.voornaam(myRs.getString("gebruiker_voornaam")).tussenVoegsel(myRs.getString("gebruiker_tussenvoegsel"))
				.achternaam(myRs.getString("gebruiker_achternaam")).straat(myRs.getString("gebruiker_straat"))
				.huisNummer(myRs.getInt("gebruiker_huisnummer"))
				.huisnrToevoeging(myRs.getString("gebruiker_hn_toevoeging"))
				.postcode(myRs.getString("gebruiker_postcode")).woonplaats(myRs.getString("gebruiker_woonplaats"))
				.phone(myRs.getString("gebruiker_telefoonnr"))
				.gebruikerToegang(GebruikerToegang.valueOf(myRs.getString("gebruiker_toegang")))
				.login(myRs.getString("gebruiker_login")).pass(myRs.getString("gebruiker_pass"))
				.build();

		return tempGebruiker;
	}

	public Gebruiker getByLogin(String login) {
		Gebruiker gebruiker = null;

		try (Statement myStmt = myConn.createStatement();
				ResultSet myRs = myStmt
						.executeQuery("select * from gebruikers where gebruiker_login = \"" + login + "\"");) {

			if (myRs.isBeforeFirst()) { // if set is not empty

				myRs.first();
				gebruiker = convertRowToGebruiker(myRs);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return gebruiker;

	}

//	public void saveOrUpdate(Gebruiker gebruiker) {
//		if (gebruiker.getId() == 0) {
//			saveNew(gebruiker);
//		} else {
//			update(gebruiker);
//		}
//	}

	public void update(Gebruiker gebruiker) {
		try (PreparedStatement myStmt = myConn.prepareStatement("select * from gebruikers where gebruiker_id =?",
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			myStmt.setInt(1, gebruiker.getId());

			try (ResultSet myRs = myStmt.executeQuery()) {

				myRs.first();
				gebruikerToResultSet(myRs, gebruiker);
				myRs.updateRow();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void saveNew(Gebruiker gebruiker) {
		try (Statement myStmt = myConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet myRs = myStmt.executeQuery("select * from gebruikers")) {

			myRs.moveToInsertRow();
			gebruikerToResultSet(myRs, gebruiker);
			myRs.insertRow();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void gebruikerToResultSet(ResultSet myRs, Gebruiker gebruiker) throws SQLException {

		// "gebruiker_id"
		myRs.updateString("gebruiker_type", gebruiker.getGebruikerType().toString());
		myRs.updateString("gebruiker_voornaam", gebruiker.getVoornaam());
		myRs.updateString("gebruiker_tussenvoegsel", gebruiker.getTussenVoegsel());
		myRs.updateString("gebruiker_achternaam", gebruiker.getAchternaam());
		myRs.updateString("gebruiker_straat", gebruiker.getStraat());
		myRs.updateInt("gebruiker_huisnummer", gebruiker.getHuisNummer());
		myRs.updateString("gebruiker_hn_toevoeging", gebruiker.getHuisnrToevoeging());
		myRs.updateString("gebruiker_postcode", gebruiker.getPostcode());
		myRs.updateString("gebruiker_woonplaats", gebruiker.getWoonplaats());
		myRs.updateString("gebruiker_telefoonnr", gebruiker.getTelefoon());
		myRs.updateString("gebruiker_toegang", gebruiker.getGebruikerToegang().toString());
		myRs.updateString("gebruiker_login", gebruiker.getLogin());
		if (!(gebruiker.getPass() == null ||gebruiker.getPass().isEmpty())) {
			myRs.updateString("gebruiker_pass", gebruiker.getPass());
		}

	}

	@Override
	public List<Gebruiker> getAll() {
		List<Gebruiker> gebruikerList = new ArrayList<>();
		
		try (Statement myStmt = myConn.createStatement();
				ResultSet myRs = myStmt.executeQuery("select * from gebruikers")) {

			while (myRs.next()) {
				Gebruiker tempGebruiker = convertRowToGebruiker(myRs);
				gebruikerList.add(tempGebruiker);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return gebruikerList;
	
	}

//	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
