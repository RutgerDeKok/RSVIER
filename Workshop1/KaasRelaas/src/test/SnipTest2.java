package test;
import java.sql.*;
import java.util.Properties;

public class SnipTest2 {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		// Load the JDBC driver


	Connection sqlConnection = null;
	Statement sqlStatement = null;
	ResultSet sqlResult = null;
	
	Properties connectionProperties = new Properties();
	connectionProperties.setProperty("user", "root");
	connectionProperties.setProperty("password", "w#rt3ltje5Doo$");
	connectionProperties.setProperty("useSSL", "false");
	connectionProperties.setProperty("autoReconnect", "true");
	
	try {
		// 1. Get a connection to database
		//myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/boer_piets_kaashandel", "root", "w#rt3ltje5Doo$"?autoReconnect=true&useSSL=false);
		sqlConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/boer_piets_kaashandel",connectionProperties);
		System.out.println("database connected");
		// 2. Create a statement
		sqlStatement = sqlConnection.createStatement();
		
		// 3. Execute SQL query
		sqlResult = sqlStatement.executeQuery("select * from gebruikers");
		
		// 4. Process the result set
		while (sqlResult.next()) {
			System.out.println(sqlResult.getString("gebruiker_achternaam") + ", " + sqlResult.getString("gebruiker_voornaam"));
		}
	}
	catch (Exception exc) {
		exc.printStackTrace();
	}
	finally {
		if (sqlResult != null) {
			sqlResult.close();
		}
		
		if (sqlStatement != null) {
			sqlStatement.close();
		}
		
		if (sqlConnection != null) {
			sqlConnection.close();
		}
	}
}


}
