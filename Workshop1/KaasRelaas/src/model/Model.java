package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import daos.GebruikerDao;
import daos.OrderDao;
import daos.ProductDao;

public class Model {
	
	private OrderDao orderDao;
	private ProductDao productDao;
	private GebruikerDao gebruikerDao;
	private Connection myConn;
	
	//constructor
	public Model() {
		
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
		
//		String dburl = "jdbc:mysql://localhost:3306/boer_piets_kaashandel";
//		props.setProperty("user", "root");
//		props.setProperty("password", "pw");
		
		// connect to database
		try {
			myConn = DriverManager.getConnection(dburl, props);
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/boer_piets_kaashandel",props);
			
			orderDao = new OrderDao(myConn);
			productDao = new ProductDao(myConn);
			gebruikerDao = new GebruikerDao(myConn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	
	}
	
	
	public void close() throws SQLException{

		if (myConn != null) {
			myConn.close();
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


	
//	// main method for testing 
//	public static void main(String[] args) throws Exception {
//		
//		MasterDao dao = new MasterDao();
//		
//		dao.orderDao = new OrderDao(dao.myConn);	
//		System.out.println(dao.orderDao.getAllOrders());
//		System.out.println(dao.orderDao.getOrdersData());
//		
//		dao.gebruikerDao = new GebruikerDao(dao.myConn);	
//		System.out.println(dao.gebruikerDao.getGebruikerById(4));
//		System.out.println(dao.gebruikerDao.getGebruikerById(3));
//		System.out.println(dao.gebruikerDao.getGebruikerById(2));
//		System.out.println(dao.gebruikerDao.getGebruikerById(1));
//		
//		System.out.println(dao.gebruikerDao.getAllKlantenByType("Klant"));
//		System.out.println(dao.gebruikerDao.getAllKlantenByType("Medewerker"));	
//		
//	}		
	


}
