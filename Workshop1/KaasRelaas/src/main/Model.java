package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import gebruiker.GebruikerDao;
import order.OrderDao;
import product.ProductDao;

public class Model {

	private OrderDao orderDao;
	private ProductDao productDao;
	private GebruikerDao gebruikerDao;
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
			System.out.println("Connected to DataBase");
			orderDao = new OrderDao(myConn);
			productDao = new ProductDao(myConn);
			gebruikerDao = new GebruikerDao(myConn);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() throws SQLException {

		if (myConn != null) {
			myConn.close();
			System.out.println("Connection to DataBase closed");
		}
	}

	// getter and setters

	public GebruikerDao getGebruikerDao() {
		return gebruikerDao;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	// main method for testing
	// public static void main(String[] args) throws Exception {
	//
	// Model dao = new Model();
	//
	// dao.orderDao = new OrderDao(dao.myConn);
	// System.out.println(dao.orderDao.getAllOrders());
	// System.out.println(dao.orderDao.getOrdersData());
	//
	// dao.gebruikerDao = new GebruikerDao(dao.myConn);
	// System.out.println(dao.gebruikerDao.getGebruikerById(4));
	// System.out.println(dao.gebruikerDao.getGebruikerById(3));
	// System.out.println(dao.gebruikerDao.getGebruikerById(2));
	// System.out.println(dao.gebruikerDao.getGebruikerById(1));
	//
	// System.out.println(dao.gebruikerDao.getAllKlantenByType("Klant"));
	// System.out.println(dao.gebruikerDao.getAllKlantenByType("Medewerker"));
	//
	// }

}
