package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import model.Model;
import model.Order;
import view.View;

public class Controller {
	
	private View theView;
	private Model theModel;
	private OrdersToTableCoverter converter;


	public Controller(View view, Model model) {
		theView = view;
		theModel = model;

		this.theView.getMainpanel().getOrderPanel().getOverzOrdersPnl().addNieuweOrderListener(new NieuweOrderListener());
		this.theView.getMainpanel().getOrderPanel().getOverzOrdersPnl().addUpdateOrderListener(new UpdateOrderListener());
		this.theView.getMainpanel().getOrderPanel().getNieuweOrderPnl().addOrderCompleetListener(new OrderCompleetListener());
		converter = new OrdersToTableCoverter(this);
	}
	
	Model getModel(){
		return theModel;
	}
	
	

	public class NieuweOrderListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
		
			theView.getMainpanel().getOrderPanel().setOrderCard("NieuweOrderPnl");
		}
			
	} // end NieuweOrderListener inner class
	
	
	public class OrderCompleetListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			System.out.println("Order Compleet Button Pressed");
			// checks and implementations to come here
			
			//
			theView.getMainpanel().getOrderPanel().setOrderCard("overzOrdersPnl");
		}
			
	} // end NieuweOrderListener inner class
	
	
	
	
	
	public class UpdateOrderListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			try {
				// data ophalen uit DB via DAO

				 List<Order> ordersList = theModel.getOrderDao().getAllOrders();
				
				// List converteren naar String[][] voor tabel in view
				String[][] data;
				data = converter.getData(ordersList);
				
			
				//data sturen naar view
				theView.getMainpanel().getOrderPanel().getOverzOrdersPnl().setData(data);
					
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		} // end actionPerformed method
			
	} // end UpdateOrderListener inner class

	
}
