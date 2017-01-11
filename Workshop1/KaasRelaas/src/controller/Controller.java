package controller;


import controller.listeners.NieuweOrderListener;
import controller.listeners.OrderCompleetListener;
import controller.listeners.UpdateOrderLijstListener;
import model.Model;
import model.Order;
import view.View;

public class Controller {
	
	private View theView;
	private Model theModel;
//	private List<String> klantOptions;
//	private List<String> productOptions;


	public Controller(View view, Model model) {
		theView = view;
		theModel = model;

		this.theView.getMainpanel().getOrderPanel().getOverzOrdersPnl().
			addNieuweOrderListener(new NieuweOrderListener(this));
		this.theView.getMainpanel().getOrderPanel().getOverzOrdersPnl().
			addUpdateOrderListener(new UpdateOrderLijstListener(this));
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
	
//	public void setKlantOptions(List<String> klantOptions) {
//		this.klantOptions = klantOptions;
//	}
//
//	public void setProductOptions(List<String> productOptions) {
//		this.productOptions = productOptions;
//	}


	public void sendOrdertoDB(Order order) throws Exception {
		theModel.getOrderDao().UpdateOrder(order);
		
	}
	



	
}
