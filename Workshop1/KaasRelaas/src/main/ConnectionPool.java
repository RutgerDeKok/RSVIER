package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionPool {

	private static ConnectionPool instance;
	private static Properties properties;

	private HikariDataSource dataSource;
	

	private ConnectionPool() {
			
		setupPool();

	}

	private void setupPool() {
			KaasAppMain.logger.info("setting CP config from Properties");
			HikariConfig config = new HikariConfig();
		
			config.setJdbcUrl(properties.getProperty("dburl"));
			config.setUsername(properties.getProperty("user"));
			config.setPassword(properties.getProperty("password"));
			
			config.setMaximumPoolSize(10);
			config.setAutoCommit(true);
			config.addDataSourceProperty("cachePrepStmts", "true");
			config.addDataSourceProperty("prepStmtCacheSize", "250");
			config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

			dataSource = new HikariDataSource(config);
//			dataSource.setDriverClassName(props.getProperty("driver"));

	}
	
	
	public static void setProperties(Properties properties){
		ConnectionPool.properties = properties;
		
	}

	public Connection getConnection() {

		try {
			Connection conn = dataSource.getConnection();
			KaasAppMain.logger.info("Connection obtained from Hakiri datasource");
			return conn;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void closeConnection(Connection conn) throws SQLException {

		if (conn != null) {

			conn.close();
			conn = null;

			KaasAppMain.logger.info("Connection returned to the " + "UniversalConnectionPool\n");
		}
	}

	public static ConnectionPool getInstance() {
		if(instance == null){
			instance = new ConnectionPool();
		}
		return instance;
	}

}
