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
		System.out.println("updateOrdersList called");
		try {
			// data ophalen uit DB via DAO

			 List<Order> ordersList = controller.getModel().getOrderDao().getAllOrders();
			
			// List converteren naar String[][] voor tabel in view
			String[][] data;
	
			orderConverter = new OrdersToTableConverter(controller);
			data = orderConverter.getData(ordersList);
			
		
			//data sturen naar view
			controller.getView().getMainpanel().getOrderPanel().setOrderList(ordersList);
			controller.getView().getMainpanel().getOrderPanel().setData(data);
			controller.getView().getMainpanel().getOrderPanel().addTableListener(controller);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	} 
		
} 