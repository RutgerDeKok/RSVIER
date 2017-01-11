package test;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Properties;

public class SnipTest2 {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		// Load the JDBC driver
		
		java.util.Date javaDate = new java.util.Date();
		long javaTime = javaDate.getTime();
		java.sql.Date sqlDate = new java.sql.Date(javaTime);



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
		//sqlConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/boer_piets_kaashandel", "root", "w#rt3ltje5Doo$"?autoReconnect=true&useSSL=false);
		sqlConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/boer_piets_kaashandel",connectionProperties);
		System.out.println("database connected");
		// 2. Create a statement
		sqlStatement = sqlConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		
	
		
		// 3. Execute SQL query
		
//		sqlResult = sqlStatement.executeQuery("insert into bestellingen (datum_bestelling,"
//				+ " medewerker_id, klant_id, product_A_id, product_A_aantal,totaal_bedrag_best)"
//				+ "values ('"+sqlDate+"', '2', '3', '4', '2', 30.00)");
		
		
		
		sqlResult = sqlStatement.executeQuery("select * from bestellingen");

		// 4. Process the result set
		while (sqlResult.next()) {
			System.out.println(sqlResult.getString("bestelling_id") + ", " + sqlResult.getString("totaal_bedrag_best"));
		}
		System.out.println("");
		// insert data in resultSet
		
		sqlResult.moveToInsertRow();
		sqlResult.updateDate("datum_bestelling",sqlDate);
		sqlResult.updateInt("medewerker_id", 2);
		sqlResult.updateInt("klant_id", 4);
		sqlResult.updateInt("product_A_id",3);
		sqlResult.updateInt("product_A_aantal",2);
		sqlResult.updateBigDecimal("totaal_bedrag_best", new BigDecimal(30.00));
		sqlResult.insertRow();
		
		sqlResult.beforeFirst();
		
		while (sqlResult.next()) {
			System.out.println(sqlResult.getString("bestelling_id") + ", " + sqlResult.getString("totaal_bedrag_best"));
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
