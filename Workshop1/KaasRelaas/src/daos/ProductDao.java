package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductDao {

	private Connection myConn;

	public ProductDao(Connection myConn) {
		this.myConn = myConn;

	}
	
	public List<Product> getAllProducten() throws Exception {
		List<Product> productList = new ArrayList<>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from producten");

			while (myRs.next()) {
				Product tempProduct = convertRowToProduct(myRs);
				productList.add(tempProduct);
			}

			return productList;
		} finally {
			close(myStmt, myRs);
		}
	}

	public int getAantalProdVoorraad(int prodId) throws SQLException {

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select product_op_voorraad from producten where product_id =" + prodId);

			myRs.first();
			int aantal = myRs.getInt("product_op_voorraad");

			return aantal;

		} finally {
			close(myStmt, myRs);
		}

	}

	private Product convertRowToProduct(ResultSet myRs) throws SQLException {

		Product tempProduct = new Product.ProductBuilder()
				.productId		(myRs.getInt("product_id"))
				.productNaam	(myRs.getString("product_naam"))
				.prijs			(myRs.getBigDecimal("product_prijs"))
				.aantalVoorraad	(myRs.getInt("product_op_voorraad"))
				.build();
		
		return tempProduct;
	}
	
	

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			myStmt.close();
		}
	}

}