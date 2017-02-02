package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Order;

public class OrderDao {

	private Connection myConn;

	public OrderDao(Connection myConn) {
		this.myConn = myConn;

	}

	public List<Order> getAllOrders() throws Exception {
		List<Order> orderList = new ArrayList<>();

		try (
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from bestellingen")
				){
			while (myRs.next()) {
				Order tempOrder = convertRowToOrder(myRs);
				orderList.add(tempOrder);
			}

			
		} catch(Exception e){
			e.printStackTrace();
		}
		return orderList;
	}
	
	
	public void saveOrUpdate(Order order) throws Exception {
		
		ResultSet myRs = null;
		
		try (
			Statement myStmt = myConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			) {
			
			
			
			// insert new data
			if(order.getOrderId()==0){ // nieuwe order
				myRs = myStmt.executeQuery("select * from bestellingen");
				myRs.moveToInsertRow();
				
			}else{
				String query = "UPDATE bestellingen where order_id = "+order.getOrderId()+" Set product_B_id = ?";
//				String query = "UPDATE bestellingen where order_id = "+order.getOrderId();
				PreparedStatement queryStatement = (PreparedStatement)myConn.prepareStatement(query);
				queryStatement.setNull(1, java.sql.Types.INTEGER);
				

				System.out.println("order ID = "+order.getOrderId());
				myRs = myStmt.executeQuery("select * from bestellingen where bestelling_id = "+order.getOrderId());
				myRs.first();
				myRs.updateInt("product_B_id",0);
				myRs.updateInt("product_B_aantal",0);
				myRs.updateInt("product_C_id",0);
				myRs.updateInt("product_C_aantal",0);
				
			}
			
	
			
			myRs.updateInt("medewerker_id", order.get1eMedewerkerId());
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
				System.out.println("product B aantal = "+order.getProductB_aantal());
				myRs.updateInt("product_B_aantal",order.getProductB_aantal());
			}
			
			// product C if exists
			if(order.getProductC_Id()!=0){
				myRs.updateInt("product_C_id",order.getProductC_Id());
				myRs.updateInt("product_C_aantal",order.getProductC_aantal());
			}
			
			// commit data
			if (order.getOrderId()==0){ // nieuwe order
				myRs.insertRow();
			}else{
				myRs.updateRow();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	



	private Order convertRowToOrder(ResultSet myRs) throws SQLException {
		
		Order tempOrder = new Order.OrderBuilder()
				.orderId		(myRs.getInt("bestelling_id"))
				.orderDatum		(myRs.getDate("datum_bestelling"))
				.medewerkerId	(myRs.getInt("medewerker_id"))
				.klantId		(myRs.getInt("klant_id"))
				.productA_Id	(myRs.getInt("product_A_id"))
				.productA_aantal(myRs.getInt("product_A_aantal"))
				.productB_Id	(myRs.getInt("product_B_id"))
				.productB_aantal(myRs.getInt("product_B_aantal"))
				.productC_Id	(myRs.getInt("product_C_id"))
				.productC_aantal(myRs.getInt("product_C_aantal"))
				.totaalBedrag	(myRs.getBigDecimal("totaal_bedrag_best"))
				.build();

		return tempOrder;
	}

	public void deleteOrder(int orderId) {
		System.out.println("Deleting Order : "+orderId);
	

			try {
					PreparedStatement st = myConn.prepareStatement("DELETE FROM bestellingen WHERE bestelling_id = ?");
					st.setInt(1,orderId);
					st.executeUpdate(); 

			
				
			} catch(Exception e){
				e.printStackTrace();
			}
			
		
		
		
	}
	
	


}
