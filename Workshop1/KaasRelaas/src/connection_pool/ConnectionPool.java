package connection_pool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import main.KaasAppMain;
import main.Model;

public class ConnectionPool {

	private static final ConnectionPool instance = new ConnectionPool();

	private HikariDataSource dataSource;


	private ConnectionPool() {
		Properties props = new Properties();
		try {
			switch (Model.DB_TYPE) {

				case "MySql":
					KaasAppMain.logger.info("Loading MySql config");
					props.load(new FileInputStream("src/resources/MySql.access"));
					setupPool(props);
					break;
				case "SqlServer":
					KaasAppMain.logger.info("Loading SqlServer config");
					props.load(new FileInputStream("src/resources/SqlServer.access"));
					setupPool(props);
					break;
				default:
					break;
				}

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void setupPool(Properties props) {
		
			HikariConfig config = new HikariConfig();
		
			config.setJdbcUrl(props.getProperty("dburl"));
			config.setUsername(props.getProperty("user"));
			config.setPassword(props.getProperty("password"));
			
			config.setMaximumPoolSize(10);
			config.setAutoCommit(true);
			config.addDataSourceProperty("cachePrepStmts", "true");
			config.addDataSourceProperty("prepStmtCacheSize", "250");
			config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

			dataSource = new HikariDataSource(config);
//			dataSource.setDriverClassName(props.getProperty("driver"));

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
		return instance;
	}

}
