package snip_tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import gebruiker.Gebruiker;
import gebruiker.GebruikerToegang;
import gebruiker.GebruikerType;
import product.Product;

public class Snip {
	

	public static void main(String[] args) {
		List<Product> productList = new ArrayList<>();
		
	
		
		 try {
			 String login = "Piet";
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
	        Connection      con=DriverManager.getConnection("jdbc:sqlserver://localhost:64047;databaseName=boer_piets_kaashandel","sa","1234");//repalce your databse name and user name
	            Statement st=con.createStatement();
	        ResultSet rs=st.executeQuery(    "select * from gebruikers where gebruiker_login = \'" + login + "\'");
	      
//	        ResultSet rs=st.executeQuery("Select * from producten");//replace your table name
	        while(rs.next())
	        {
	        	
	        	Gebruiker gebruiker = convertRowToGebruiker(rs);
//	        	Product tempProduct 
				
	          System.out.println(gebruiker.toString());
//	            System.out.println(tempProduct.getNaam());
	        }
	        con.close();
		

	
//
//				Properties props = new Properties();
//				props.setProperty("useSSL", "false");
//				props.setProperty("autoReconnect", "true");
//				props.setProperty("user", "as");
//				props.setProperty("pass", "1234");
//			
//
//				String dburl = "jdbc:sqlserver://localhost:1433/SQLEXPRESS/boer_piets_kaashandel";
////				String user = "as";
////				String pass = "1234";
//				// connect to database
//				try {
//					myConn = DriverManager.getConnection(dburl, props);
////					myConn = DriverManager.getConnection(dburl, user, pass);
//			
////						List<Product> productList = new ArrayList<>();
//					Statement myStmt = myConn.createStatement();
//							ResultSet myRs = myStmt.executeQuery("select * from producten"); 
//					while (myRs.next()) {
//							System.out.println(myRs);
//						}
//
//					
//					myConn.close();
				

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	private static Product convertRowToProduct(ResultSet myRs) throws SQLException {

		Product tempProduct = new Product.ProductBuilder().productId(myRs.getInt("product_id"))
				.productNaam(myRs.getString("product_naam")).prijs(myRs.getBigDecimal("product_prijs"))
				.aantalVoorraad(myRs.getInt("product_op_voorraad")).build();

		return tempProduct;
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
