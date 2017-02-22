package main;

import java.sql.Connection;
import java.sql.SQLException;
import connection_pool.ConnectionPool;
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
	
//	public static final String DB_TYPE = "MySql";
	public static final String DB_TYPE = "SqlServer";
//	public static final String DB_TYPE = "Test";

	private AbstractOrderDao orderDao;
	private AbstractProductDao productDao;
	private AbstractGebruikerDao gebruikerDao;
	private Connection myConn;

	public void startConnection() {
			
			myConn = ConnectionPool.getInstance().getConnection();
			
			switch(DB_TYPE){
			default:
				break;
			case "MySql":
				KaasAppMain.logger.info("Database type = MySql");
				orderDao = new OrderDaoMySql(this);
				productDao = new ProductDaoMySql(myConn);
				gebruikerDao = new GebruikerDaoMySql(myConn);
				break;
			case "SqlServer":
				KaasAppMain.logger.info("Database type = SqlServer");
				orderDao = new OrderDaoSqlSrv(this);
				productDao = new ProductDaoSqlSrv(myConn);
				gebruikerDao = new GebruikerDaoSqlSrv(myConn);
				break;
				
			case "Test":
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
