package gebruiker.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import gebruiker.Gebruiker;
import gebruiker.GebruikerToegang;
import gebruiker.GebruikerType;
import main.DaoInterface;

public abstract class AbstractGebruikerDao implements DaoInterface<Gebruiker> {

	public abstract List<Gebruiker> getAllByType(GebruikerType type, boolean include) throws Exception;

	public abstract Gebruiker getAllById(int Id) throws SQLException;

	public abstract Gebruiker getByLogin(String login);

	public void saveOrUpdate(Gebruiker gebruiker) {
		if (gebruiker.getId() == 0) {
			saveNew(gebruiker);
		} else {
			update(gebruiker);
		}
	}

	protected Gebruiker convertRowToGebruiker(ResultSet myRs) throws SQLException {

		Gebruiker tempGebruiker = new Gebruiker.GebruikerBuilder().id(myRs.getInt("gebruiker_id"))
				.gebruikerType(GebruikerType.valueOf(myRs.getString("gebruiker_type")))
				.voornaam(myRs.getString("gebruiker_voornaam")).tussenVoegsel(myRs.getString("gebruiker_tussenvoegsel"))
				.achternaam(myRs.getString("gebruiker_achternaam")).straat(myRs.getString("gebruiker_straat"))
				.huisNummer(myRs.getInt("gebruiker_huisnummer"))
				.huisnrToevoeging(myRs.getString("gebruiker_hn_toevoeging"))
				.postcode(myRs.getString("gebruiker_postcode")).woonplaats(myRs.getString("gebruiker_woonplaats"))
				.phone(myRs.getString("gebruiker_telefoonnr"))
				.gebruikerToegang(GebruikerToegang.valueOf(myRs.getString("gebruiker_toegang")))
				.login(myRs.getString("gebruiker_login")).pass(myRs.getString("gebruiker_pass")).build();

		return tempGebruiker;
	}

	protected void gebruikerToResultSet(ResultSet myRs, Gebruiker gebruiker) throws SQLException {

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
		if (!(gebruiker.getPass() == null || gebruiker.getPass().isEmpty())) {
			myRs.updateString("gebruiker_pass", gebruiker.getPass());
		}

	}

}
