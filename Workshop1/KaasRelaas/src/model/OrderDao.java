package model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

	private Connection myConn;

	public OrderDao(Connection myConn) {
		this.myConn = myConn;

	}

	public List<Order> getAllOrders() throws Exception {
		List<Order> orderList = new ArrayList<>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from bestellingen");

			while (myRs.next()) {
				Order tempOrder = convertRowToOrder(myRs);
				orderList.add(tempOrder);
			}

			return orderList;
		} finally {
			close(myStmt, myRs);
		}
	}
	
	
	public void UpdateOrder(Order order) throws Exception {

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			
			
			// insert new data
			if(order.getOrderId()==0){ // nieuwe order
				myRs = myStmt.executeQuery("select * from bestellingen");
				myRs.moveToInsertRow();
				
			}else{
				myRs = myStmt.executeQuery("select * from bestellingen where order_id = "+order.getOrderId());
				myRs.first();
				myRs.updateInt("product_B_id",0);
				myRs.updateInt("product_B_aantal",0);
				myRs.updateInt("product_C_id",0);
				myRs.updateInt("product_C_aantal",0);
				
			}
			
	
			
			myRs.updateInt("medewerker_id", order.getMedewerkerId());
			myRs.updateInt("klant_id", order.getKlantId());
			myRs.updateInt("product_A_id",order.getProductA_Id());
			myRs.updateInt("product_A_aantal",order.getProductA_aantal());
			myRs.updateBigDecimal("totaal_bedrag_best", order.getTotaalBedrag());
			
			// date 
			if(order.getOrderDatum()==null){
				java.util.Date javaDate = new java.util.Date();
				long javaTime = javaDate.getTime();
				java.sql.Date sqlDate = new java.sql.Date(javaTime);
				myRs.updateDate("datum_bestelling",sqlDate);
			}else{
				myRs.updateDate("datum_bestelling",order.getOrderDatum());
			}
			
			// product B if exists
			if(order.getProductB_Id()!=0){
				myRs.updateInt("product_B_id",order.getProductB_Id());
				myRs.updateInt("product_B_aantal",order.getProductB_aantal());
			}
			
			// product C if exists
			if(order.getProductC_Id()!=0){
				myRs.updateInt("product_C_id",order.getProductC_Id());
				myRs.updateInt("product_C_aantal",order.getProductC_aantal());
			}
		
			// commit data
			myRs.insertRow();
			
		} finally {
			close(myStmt, myRs);
		}
	}
	



	private Order convertRowToOrder(ResultSet myRs) throws SQLException {

		int orderId = myRs.getInt("bestelling_id");
		Date orderDatum = myRs.getDate("datum_bestelling");
		int medewerkerId = myRs.getInt("medewerker_id");
		int klantId = myRs.getInt("klant_id");
		int productA_Id = myRs.getInt("product_A_id");
		int productA_aantal = myRs.getInt("product_A_aantal");
		int productB_Id = myRs.getInt("product_B_id");
		int productB_aantal = myRs.getInt("product_B_aantal");
		int productC_Id = myRs.getInt("product_C_id");
		int productC_aantal = myRs.getInt("product_C_aantal");
		BigDecimal totaalBedrag = myRs.getBigDecimal("totaal_bedrag_best");

		Order tempOrder = new Order(orderId, orderDatum, medewerkerId, klantId, productA_Id, productA_aantal,
				productB_Id, productB_aantal, productC_Id, productC_aantal, totaalBedrag);

		return tempOrder;
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
