package controller;


import controller.listeners.NieuweOrderListener;
import controller.listeners.OrderCompleetListener;
import controller.listeners.UpdateOrderLijstListener;
import model.Model;
import model.Order;
import view.main.View;

public class Controller {
	
	private View theView;
	private Model theModel;


	public Controller(View view, Model model) {
		theView = view;
		theModel = model;
		
		// adding Handlers that need access to Controller 
		
		// NieuweOrderListener
		this.theView.getMainpanel().getOrderPanel().getOverzOrdersPnl().
			addNieuweOrderListener(new NieuweOrderListener(this));
		// UpdateOrderLijstListener
		this.theView.getMainpanel().getOrderPanel().getOverzOrdersPnl().
			addUpdateOrderListener(new UpdateOrderLijstListener(this));
		//OrderCompleetListener
		this.theView.getMainpanel().getOrderPanel().getNieuweOrderPnl().
			addOrderCompleetListener(new OrderCompleetListener(this));
		
	}
	
	
	//getters 
	
	public Model getModel(){
		return theModel;
	}
	
	public View getView(){
		return theView;
	}
	


	public void sendOrdertoDB(Order order) throws Exception {
		theModel.getOrderDao().UpdateOrder(order);
		
	}
	



	
}
