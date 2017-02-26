package main;

import java.sql.Connection;
import java.sql.SQLException;

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

	public void startConnection() {
			
			myConn = ConnectionPool.getInstance().getConnection();
			KaasAppMain.logger.info("ConnectionPool Instance = "+ConnectionPool.getInstance());
			
			switch(DB_TYPE){
			default:
				break;
			case MY_SQL:
				KaasAppMain.logger.info("Database type = MySql");
				orderDao = new OrderDaoMySql(this);
				productDao = new ProductDaoMySql(myConn);
				gebruikerDao = new GebruikerDaoMySql(myConn);
				break;
			case SQL_SERVER:
				KaasAppMain.logger.info("Database type = SqlServer");
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
