package order.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.Model;
import order.Order;

public class OrderDaoMySql extends AbstractOrderDao{
	// fields to update stock numbers in product table in DB
	private int[] prod_ids = new int[3];
	private int[] prod_decreases = new int[3];

	private Model model;
	private Connection myConn;

	public OrderDaoMySql(Model model) {
		this.model = model;
		myConn = model.getConnection();
	}

	public List<Order> getAll() {
		List<Order> orderList = new ArrayList<>();

		try (Statement myStmt = myConn.createStatement();
				ResultSet myRs = myStmt.executeQuery("select * from bestellingen")) {

			while (myRs.next()) {
				Order tempOrder = convertRowToOrder(myRs);
				orderList.add(tempOrder);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderList;
	}


	public void saveNew(Order order) {

		try (Statement myStmt = myConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet myRs = myStmt.executeQuery("select * from bestellingen")) {

			myRs.moveToInsertRow();
			objectToResultSet(myRs, order);
			myRs.insertRow();
			updateVoorraad();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Order order) {

		try (PreparedStatement myStmt = myConn.prepareStatement("select * from bestellingen where bestelling_id =?",
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			myStmt.setInt(1, order.getId());

			try (ResultSet myRs = myStmt.executeQuery()) {
				
				restoreOldAmounts(myRs);

				myRs.first();
				myRs.updateInt("product_B_id", 0);
				myRs.updateInt("product_B_aantal", 0);
				myRs.updateInt("product_C_id", 0);
				myRs.updateInt("product_C_aantal", 0);

				objectToResultSet(myRs, order);
				myRs.updateRow();
				updateVoorraad();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void restoreOldAmounts(ResultSet myRs) throws SQLException {
		int[] prod_old_ids = new int[3];
		int[] prod_old_count = new int[3];
		
		myRs.first();
		prod_old_count[0] = myRs.getInt("product_A_aantal");
		prod_old_count[1] = myRs.getInt("product_B_aantal");
		prod_old_count[2] = myRs.getInt("product_C_aantal");
		prod_old_ids[0] = myRs.getInt("product_A_id");
		prod_old_ids[1] = myRs.getInt("product_B_id");
		prod_old_ids[2] = myRs.getInt("product_C_id");
		
		for (int i = 0; i < prod_old_ids.length; i++) {
			if (prod_old_ids[i] > 0) {
				model.getProductDao().updateVoorraad(prod_old_ids[i], -prod_old_count[i]);
			}
		}
	}



	public void delete(int orderId) {
	
		try (PreparedStatement myStmt = myConn.prepareStatement("select * from bestellingen where bestelling_id =?",
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			myStmt.setInt(1, orderId);

			try (ResultSet myRs = myStmt.executeQuery()) {
				
				myRs.first();
				restoreOldAmounts(myRs);
				myRs.deleteRow();
			}
	

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void updateVoorraad() {
		for (int i = 0; i < prod_ids.length; i++) {
			if (prod_ids[i] > 0) {
				model.getProductDao().updateVoorraad(prod_ids[i], prod_decreases[i]);
			}
		}
	}


//	private void objectToResultSet(ResultSet myRs, Order order) throws SQLException {
//	
//		prod_ids[0] = order.getProductA_Id();
//		prod_ids[1] = order.getProductB_Id();
//		prod_ids[2] = order.getProductC_Id();
//		prod_decreases[0] = order.getProductA_aantal();
//		prod_decreases[1] = order.getProductB_aantal();
//		prod_decreases[2] = order.getProductC_aantal();
//
//		myRs.updateInt("medewerker_id", order.get1eMedewerkerId());
//		myRs.updateInt("klant_id", order.getKlantId());
//		myRs.updateInt("product_A_id", prod_ids[0]);
//		myRs.updateInt("product_A_aantal", prod_decreases[0]);
//		myRs.updateBigDecimal("totaal_bedrag_best", order.getTotaalBedrag());
//
//		// date
//		if (order.getOrderDatum() == null) {
//			java.util.Date javaDate = new java.util.Date();
//			long javaTime = javaDate.getTime();
//			java.sql.Date sqlDate = new java.sql.Date(javaTime);
//			myRs.updateDate("datum_bestelling", sqlDate);
//		} else {
//			myRs.updateDate("datum_bestelling", order.getOrderDatum());
//		}
//
//		// product B if exists
//		if (prod_ids[1] != 0) {
//			myRs.updateInt("product_B_id", prod_ids[1]);
//			myRs.updateInt("product_B_aantal", prod_decreases[1]);
//		}
//
//		// product C if exists
//		if (prod_ids[2] != 0) {
//			myRs.updateInt("product_C_id", prod_ids[2]);
//			myRs.updateInt("product_C_aantal", prod_decreases[2]);
//		}
//
//	}

//	private Order convertRowToOrder(ResultSet myRs) throws SQLException {
//
//		Order tempOrder = new Order.OrderBuilder().orderId(myRs.getInt("bestelling_id"))
//				.orderDatum(myRs.getDate("datum_bestelling")).medewerkerId(myRs.getInt("medewerker_id"))
//				.klantId(myRs.getInt("klant_id")).productA_Id(myRs.getInt("product_A_id"))
//				.productA_aantal(myRs.getInt("product_A_aantal")).productB_Id(myRs.getInt("product_B_id"))
//				.productB_aantal(myRs.getInt("product_B_aantal")).productC_Id(myRs.getInt("product_C_id"))
//				.productC_aantal(myRs.getInt("product_C_aantal")).totaalBedrag(myRs.getBigDecimal("totaal_bedrag_best"))
//				.build();
//
//		return tempOrder;
//	}

	


}
