package order.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import order.Order;

public class OrderDaoMock extends AbstractOrderDao{

	@Override
	public void saveNew(Order t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Order t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void restoreOldAmounts(ResultSet myRs) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int orderId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void updateVoorraad() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void objectToResultSet(ResultSet myRs, Order order) throws SQLException {	
	}
	
	
	@Override
	protected Order convertRowToOrder(ResultSet myRs) throws SQLException {
		return null;
		
	}
	
}

