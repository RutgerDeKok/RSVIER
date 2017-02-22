package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import java.util.Properties;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSourceFactory;

import gebruiker.Gebruiker;
import gebruiker.GebruikerToegang;
import gebruiker.GebruikerType;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import oracle.ucp.jdbc.PoolDataSource;

public class udpTest {
	public static void main(String args[]) throws SQLException {
		try {
			// Create pool-enabled data source instance.
			
			Object obj = PoolDataSourceFactory.getPoolDataSource();
			PoolDataSource pds = (PoolDataSource) obj;
//			PoolDataSource pds = PoolDataSourceFactory.getPoolDataSource();

			// set the connection properties on the data source.

//			pds.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
//			MysqlDataSourceFactory mdsf = new MysqlDataSourceFactory();
//			pds.setConnectionFactoryClassName(mdsf.getClass().getTypeName());
//			pds.setConnectionFactoryClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSourceFactory");
//			pds.setConnectionFactoryClassName();
			
//			pds.setConnectionFactoryClassName("org.mariadb.jdbc.MySQLDataSource");
			
//			Properties props = new Properties();
//			props.setProperty("url", "jdbc:mysql://localhost:3306");
//			props.setProperty("database", "boer_piets_kaashandel");
//			props.setProperty("user", "root");
//			props.setProperty("factoryClass", "com.mysql.jdbc.jdbc2.optional.MysqlDataSourceFactory");
			
//			try {
//				props.load(new FileInputStream("src/resources/SqlServer.access"));
//			} catch (FileNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
	//
//			String dburl = props.getProperty("dburl");
//			props.remove("dburl");
			
			
//			pds.setConnectionProperties(props);
			pds.setURL("jdbc:mysql://localhost:3306/boer_piets_kaashandel");
//			pds.setURL("jdbc:mysql://localhost:3306");
			pds.setUser("root"); 
			pds.setPassword("w#rt3ltje5Doo$");
			pds.setConnectionFactoryClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSourceFactory");

			// Override any pool properties.

			pds.setInitialPoolSize(5);
			System.out.println("factory class = "+pds.getConnectionFactoryClassName());
			// Get a database connection from the datasource.
			
			DataSource ds = pds;

			Connection conn = pds.getConnection();

			System.out.println("\nConnection obtained from " + "UniversalConnectionPool\n");

			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from gebruikers where gebruiker_login = Piet");

			while (rs.next()) {

				Gebruiker gebruiker = convertRowToGebruiker(rs);

				System.out.println(gebruiker.toString());

			}

			// do some work with the connection.
			// Statement stmt = conn.createStatement ();
			// stmt.execute("select * from foo");

			// Close the Connection.

			conn.close();
			conn = null;

			System.out.println("Connection returned to the " + "UniversalConnectionPool\n");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("BasicConnectionExample - " + "main()-SQLException occurred : " + e.getMessage());
		}
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
				.login(myRs.getString("gebruiker_login")).pass(myRs.getString("gebruiker_pass"))
				.build();

		return tempGebruiker;
	}
	
	
	
	
}