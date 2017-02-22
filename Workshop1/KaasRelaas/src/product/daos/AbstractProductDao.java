package product.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import main.DaoInterface;
import product.Product;

public abstract class AbstractProductDao implements DaoInterface<Product> {

	public abstract int getAantalProdVoorraad(int prodId) throws SQLException;

	public abstract void updateVoorraad(int prod_id, int prod_decrease);

	public void saveOrUpdate(Product product) {

		if (product.getId() == 0) {
			saveNew(product);
		} else {
			update(product);
		}
	}

	protected Product convertRowToProduct(ResultSet myRs) throws SQLException {

		Product tempProduct = new Product.ProductBuilder().productId(myRs.getInt("product_id"))
				.productNaam(myRs.getString("product_naam")).prijs(myRs.getBigDecimal("product_prijs"))
				.aantalVoorraad(myRs.getInt("product_op_voorraad")).build();

		return tempProduct;
	}

	protected void productToResultSet(ResultSet myRs, Product product) throws SQLException {

		myRs.updateString("product_naam", product.getNaam());
		myRs.updateInt("product_op_voorraad", product.getAantalVoorraad());
		myRs.updateBigDecimal("product_prijs", product.getPrijs());
	}

}
