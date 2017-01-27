package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import controller.Controller;
import controller.OrdersToTableConverter;
import model.Order;

public class UpdateOrderLijstListener implements ActionListener {
	
	private Controller controller;
	private OrdersToTableConverter orderConverter;
	
	public UpdateOrderLijstListener(Controller controller){
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		try {
			// data ophalen uit DB via DAO

			 List<Order> ordersList = controller.getModel().getOrderDao().getAllOrders();
			
			// List converteren naar String[][] voor tabel in view
			String[][] data;
			System.out.println("size" +ordersList.size());
			orderConverter = new OrdersToTableConverter(controller);
			data = orderConverter.getData(ordersList);
			
		
			//data sturen naar view
			controller.getView().getMainpanel().getOrderPanel().getOverzOrdersPnl().setData(data);
				
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	} 
		
} 