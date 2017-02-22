package order.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import main.DaoInterface;
import order.Order;

public abstract class AbstractOrderDao implements DaoInterface<Order> {
	
	protected int[] prod_ids = new int[3];
	protected int[] prod_decreases = new int[3];

	public abstract List<Order> getAll();
	
	protected abstract void restoreOldAmounts(ResultSet myRs) throws SQLException;
	
	public abstract void delete(int orderId);

	protected abstract void updateVoorraad();
	
	
	public void saveOrUpdate(Order order) {

		if (order.getId() == 0) {
			saveNew(order);
		} else {
			update(order);
		}
	}

	protected void objectToResultSet(ResultSet myRs, Order order) throws SQLException {
	
		prod_ids[0] = order.getProductA_Id();
		prod_ids[1] = order.getProductB_Id();
		prod_ids[2] = order.getProductC_Id();
		prod_decreases[0] = order.getProductA_aantal();
		prod_decreases[1] = order.getProductB_aantal();
		prod_decreases[2] = order.getProductC_aantal();

		myRs.updateInt("medewerker_id", order.get1eMedewerkerId());
		myRs.updateInt("klant_id", order.getKlantId());
		myRs.updateInt("product_A_id", prod_ids[0]);
		myRs.updateInt("product_A_aantal", prod_decreases[0]);
		myRs.updateBigDecimal("totaal_bedrag_best", order.getTotaalBedrag());

		// date
		if (order.getOrderDatum() == null) {
			java.util.Date javaDate = new java.util.Date();
			long javaTime = javaDate.getTime();
			java.sql.Date sqlDate = new java.sql.Date(javaTime);
			myRs.updateDate("datum_bestelling", sqlDate);
		} else {
			myRs.updateDate("datum_bestelling", order.getOrderDatum());
		}

		// product B if exists
		if (prod_ids[1] != 0) {
			myRs.updateInt("product_B_id", prod_ids[1]);
			myRs.updateInt("product_B_aantal", prod_decreases[1]);
		}

		// product C if exists
		if (prod_ids[2] != 0) {
			myRs.updateInt("product_C_id", prod_ids[2]);
			myRs.updateInt("product_C_aantal", prod_decreases[2]);
		}

	}

	protected Order convertRowToOrder(ResultSet myRs) throws SQLException {

		Order tempOrder = new Order.OrderBuilder().orderId(myRs.getInt("bestelling_id"))
				.orderDatum(myRs.getDate("datum_bestelling")).medewerkerId(myRs.getInt("medewerker_id"))
				.klantId(myRs.getInt("klant_id")).productA_Id(myRs.getInt("product_A_id"))
				.productA_aantal(myRs.getInt("product_A_aantal")).productB_Id(myRs.getInt("product_B_id"))
				.productB_aantal(myRs.getInt("product_B_aantal")).productC_Id(myRs.getInt("product_C_id"))
				.productC_aantal(myRs.getInt("product_C_aantal")).totaalBedrag(myRs.getBigDecimal("totaal_bedrag_best"))
				.build();

		return tempOrder;
	}



}
