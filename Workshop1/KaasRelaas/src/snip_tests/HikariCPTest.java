package snip_tests;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import gebruiker.Gebruiker;
import gebruiker.GebruikerToegang;
import gebruiker.GebruikerType;

public class HikariCPTest {


	public static void main(String[] args) {

		HikariConfig config = new HikariConfig();
	
		config.setJdbcUrl("jdbc:mysql://localhost:3306/boer_piets_kaashandel");
		config.setUsername("root");
		config.setPassword("w#rt3ltje5Doo$");
		config.setMaximumPoolSize(10);
		config.setAutoCommit(false);
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		Connection connection = null;
		DataSource dataSource;

		try {

			dataSource = new HikariDataSource(config);
			
			connection = dataSource.getConnection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select * from gebruikers where gebruiker_login = \"Piet\"");

			while (rs.next()) {

				Gebruiker gebruiker = convertRowToGebruiker(rs);

				System.out.println(gebruiker.toString());

			}

			connection.close();
			System.out.println("Connection returned to the Pool");

		} catch (Exception e) {

			try {
				connection.rollback();

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		}

		// Close the Connection.

	}

	private static Gebruiker convertRowToGebruiker(ResultSet myRs) throws SQLException {

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
}