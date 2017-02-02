package controller;


import java.sql.SQLException;
import java.util.List;

import model.Gebruiker;
import model.Order;

public class OrdersToTableConverter {
	

	private Controller controller;
	
	public OrdersToTableConverter(Controller controller) {
		this.controller = controller;
	}

	public String[][] getData(List<Order> ordersList) throws SQLException {
	
		int l = ordersList.size();
		String[][]data = new String [l][7];
		 for(int i=0;i<l;i++){
			 data[i]= convertOrderToRow(ordersList.get(i));
			 
		 }
	
		return data;
	}
	
	private String [] convertOrderToRow(Order order) throws SQLException{
		
		String [] dataRow = new String [7];
		int totaalAantalProd = 0;
		totaalAantalProd += order.getProductA_aantal();
		totaalAantalProd += order.getProductB_aantal();
		totaalAantalProd += order.getProductC_aantal();
		
		int klantId = order.getKlantId();
		String klantString = getKlantString(klantId);
		
		dataRow[0]= Integer.toString(order.getOrderId());
		dataRow[1]= order.getOrderDatum().toString();
		dataRow[2]= Integer.toString(order.get1eMedewerkerId());
		dataRow[3]= klantString;
		dataRow[4]= Integer.toString(totaalAantalProd);
		dataRow[5]= order.getTotaalBedrag().toString();
		dataRow[6]= "-Edit-";
		
	
		return dataRow;
		
	}
	
	private String getKlantString(int klantId) throws SQLException{
		StringBuilder klantString = new StringBuilder();
		
		Gebruiker tempKlant = controller.getModel().getGebruikerDao().getGebruikerById(klantId);
		klantString.append(tempKlant.getAchterNaam()+ ", ");
		klantString.append(tempKlant.getTussenVoegsel()+ "  ");
		klantString.append(tempKlant.getVoorNaam());
		
		return klantString.toString();
	}
	

}
