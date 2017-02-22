package product.daos;

import java.sql.SQLException;
import java.util.List;

import product.Product;

public class ProductDaoMock extends AbstractProductDao {

	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveNew(Product t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Product t) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getAantalProdVoorraad(int prodId) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateVoorraad(int prod_id, int prod_decrease) {
		// TODO Auto-generated method stub
		
	}

}
