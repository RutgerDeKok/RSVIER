package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import gebruiker.daos.AbstractGebruikerDao;
import gebruiker.daos.GebruikerDaoMock;
import gebruiker.daos.GebruikerDaoMySql;
import gebruiker.daos.GebruikerDaoSqlSrv;
import order.daos.OrderDaoMySql;
import order.daos.OrderDaoSqlSrv;
import order.daos.AbstractOrderDao;
import order.daos.OrderDaoMock;
import product.daos.AbstractProductDao;
import product.daos.ProductDaoMock;
import product.daos.ProductDaoMySql;
import product.daos.ProductDaoSqlSrv;

public class Model {
	 
	public final static DatabaseType DB_TYPE = DatabaseType.MY_SQL;
//	public final static DatabaseType DB_TYPE = DatabaseType.SQL_SERVER;
//	public final static DatabaseType DB_TYPE = DatabaseType.TEST;
	
	

	private AbstractOrderDao orderDao;
	private AbstractProductDao productDao;
	private AbstractGebruikerDao gebruikerDao;
	private Connection myConn;
	private Properties properties = new Properties();

	public void startConnection() {
						
			try {
				switch(DB_TYPE){
				default:
					break;
				case MY_SQL:
					KaasAppMain.logger.info("Database type = MySql");
					properties.load(new FileInputStream("src/resources/MySql.access"));
					initiateConnection();
					orderDao = new OrderDaoMySql(this);
					productDao = new ProductDaoMySql(myConn);
					gebruikerDao = new GebruikerDaoMySql(myConn);
					break;
				case SQL_SERVER:
					KaasAppMain.logger.info("Database type = SqlServer");
					properties.load(new FileInputStream("src/resources/SqlServer.access"));
					initiateConnection();
					orderDao = new OrderDaoSqlSrv(this);
					productDao = new ProductDaoSqlSrv(myConn);
					gebruikerDao = new GebruikerDaoSqlSrv(myConn);
					break;
					
				case TEST:
					KaasAppMain.logger.info("Database type = Test");
					orderDao = new OrderDaoMock();
					productDao = new ProductDaoMock();
					gebruikerDao = new GebruikerDaoMock();
					break;
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
	private void initiateConnection(){
		ConnectionPool.setProperties(properties);
		myConn = ConnectionPool.getInstance().getConnection();
		
	}

	public void closeConnection() throws SQLException {

		if (myConn != null) {
			myConn.close();
			KaasAppMain.logger.info("Connection to DataBase closed");
		}
	}

	// getter and setters

	public AbstractGebruikerDao getGebruikerDao() {
		return gebruikerDao;
	}

	public AbstractProductDao getProductDao() {
		return  productDao;
	}

	public AbstractOrderDao getOrderDao() {
		return  orderDao;
	}
	
	public Connection getConnection() {
		return myConn;
	}


}
