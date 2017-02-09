package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

	private Connection myConn;

	public ProductDao(Connection myConn) {
		this.myConn = myConn;

	}

	public List<Product> getAllProducten() throws Exception {
		List<Product> productList = new ArrayList<>();

		try (Statement myStmt = myConn.createStatement();
				ResultSet myRs = myStmt.executeQuery("select * from producten");) {
			while (myRs.next()) {
				Product tempProduct = convertRowToProduct(myRs);
				productList.add(tempProduct);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;
	}

	public int getAantalProdVoorraad(int prodId) throws SQLException {
		int aantal = 0;

		try (Statement myStmt = myConn.createStatement();
				ResultSet myRs = myStmt
						.executeQuery("select product_op_voorraad from producten where product_id =" + prodId);) {
			myRs.first();
			aantal = myRs.getInt("product_op_voorraad");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return aantal;

	}

	private Product convertRowToProduct(ResultSet myRs) throws SQLException {

		Product tempProduct = new Product.ProductBuilder().productId(myRs.getInt("product_id"))
				.productNaam(myRs.getString("product_naam")).prijs(myRs.getBigDecimal("product_prijs"))
				.aantalVoorraad(myRs.getInt("product_op_voorraad")).build();

		return tempProduct;
	}

	public void updateVoorraad(int prod_id, int prod_decrease) {
		System.out.println(
				"updating prod id = " + prod_id + ", dif = " + prod_decrease );
		;
		try (PreparedStatement myStmt = myConn.prepareStatement("select * from producten where product_id =?",
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			myStmt.setInt(1, prod_id);

			try (ResultSet myRs = myStmt.executeQuery()) {
				int voorraad;

				myRs.first();
				voorraad = myRs.getInt("product_op_voorraad");
				voorraad -= prod_decrease;
				myRs.updateInt("product_op_voorraad", voorraad);
				myRs.updateRow();
				System.out.println(
						"updating prod id = " + prod_id + ", dif = " + prod_decrease + ", voorraad = " + voorraad);
				;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void saveOrUpdate(Product product) {
		
		if (product.getProductId() == 0) {
			saveNewProduct(product);
		} else {
			updateProduct(product);
		}
		
	}

	private void updateProduct(Product product) {
		
		try (PreparedStatement myStmt = myConn.prepareStatement("select * from producten where product_id =?",
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			myStmt.setInt(1, product.getProductId());

			try (ResultSet myRs = myStmt.executeQuery()) {
				
				myRs.first();
				productToResultSet(myRs, product);
				myRs.updateRow();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private void saveNewProduct(Product product) {
	
		try (Statement myStmt = myConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet myRs = myStmt.executeQuery("select * from producten")) {

			myRs.moveToInsertRow();
			productToResultSet(myRs, product);
			myRs.insertRow();


		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void productToResultSet(ResultSet myRs, Product product) throws SQLException {
//		System.out.println("Running productToResultSet method from productDao, \r Needs to be implemented");
		
		myRs.updateString("product_naam", product.getProductNaam());
		myRs.updateInt("product_op_voorraad", product.getAantalVoorraad());
		myRs.updateBigDecimal("product_prijs", product.getPrijs());
	}

}