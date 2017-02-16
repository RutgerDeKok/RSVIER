package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import gebruiker.Gebruiker;
import gebruiker.GebruikerDao;
import order.OrderDao;
import order.Order;
import product.Product;
import product.ProductDao;

public class Model {

	private DaoInterface<Order> orderDao;
	private DaoInterface<Product> productDao;
	private DaoInterface<Gebruiker> gebruikerDao;
	private Connection myConn;

	public void startConnection() {

		Properties props = new Properties();
		props.setProperty("useSSL", "false");
		props.setProperty("autoReconnect", "true");
		try {
			props.load(new FileInputStream("src/resources/database.access"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String dburl = props.getProperty("dburl");
		props.remove("dburl");

		// connect to database
		try {
			myConn = DriverManager.getConnection(dburl, props);
			orderDao = new OrderDao(this);
			productDao = new ProductDao(myConn);
			gebruikerDao = new GebruikerDao(myConn);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() throws SQLException {

		if (myConn != null) {
			myConn.close();
			KaasAppMain.logger.info("Connection to DataBase closed");
		}
	}

	// getter and setters

	public GebruikerDao getGebruikerDao() {
		return (GebruikerDao)gebruikerDao;
	}

	public ProductDao getProductDao() {
		return (ProductDao) productDao;
	}

	public OrderDao getOrderDao() {
		return (OrderDao) orderDao;
	}
	
	public Connection getConnection() {
		return myConn;
	}


}
