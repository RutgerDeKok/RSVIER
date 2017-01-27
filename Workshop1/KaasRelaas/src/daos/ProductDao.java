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


		try (
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from producten");
			){
			while (myRs.next()) {
				Product tempProduct = convertRowToProduct(myRs);
				productList.add(tempProduct);
			}

			
		} catch (Exception e){
			e.printStackTrace();
		}
		return productList;
	}

	public int getAantalProdVoorraad(int prodId) throws SQLException {
		int aantal = 0;

		try (
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("select product_op_voorraad from producten where product_id =" + prodId);
			){
			myRs.first();
			aantal = myRs.getInt("product_op_voorraad");

		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aantal;

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
	
}
