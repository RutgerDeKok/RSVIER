package test;

import java.sql.*;

public class SnippetTest {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		// Load the JDBC driver

		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");

		// Connect to a database
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/boer_piets_kaas_relaas", "root", "w#rt3ltje5Doo$");
		System.out.println("Database connected");
		
		

//		// Create a statement
//		Statement statement = connection.createStatement();
//
//		// Execute a statement
//		ResultSet resultSet = statement
//				.executeQuery("select firstName, mi, lastName from Student where lastName " + " = 'Smith'");
//
//		// Iterate through the result and print the student names
//		while (resultSet.next())
//			System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3));
//
//		// Close the connection
		connection.close();

	}

}
