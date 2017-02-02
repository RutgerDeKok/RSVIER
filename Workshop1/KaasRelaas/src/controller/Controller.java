package controller;




import controller.listeners.NieuweOrderListener;

import controller.listeners.UpdateOrderLijstListener;
import model.Model;
import model.Order;
import view.main.View;

public class Controller {
	
	private View theView;
	private Model theModel;


	public Controller(View view, Model model) throws Exception {
		theView = view;
		theModel = model;
		
		// adding Handlers that need access to Controller 
		
		// NieuweOrderListener
		theView.getMainpanel().getOrderPanel().
			addNieuweOrderListener(new NieuweOrderListener(this));
		
		// UpdateOrderLijstListener
		theView.getMainpanel().getOrderPanel().
			addUpdateOrderListener(new UpdateOrderLijstListener(this));
		
//		List<Gebruiker> medewerkers = theModel.getGebruikerDao().getAllGebruikersByType(GebruikerType.MEDERWERKER);
		theView.startLoginPanel();
		theView.getLoginPanel().addOkListener(this);
		
	}
	
	
	//getters 
	
	public Model getModel(){
		return theModel;
	}
	
	public View getView(){
		return theView;
	}
	


	public void sendOrdertoDB(Order order) throws Exception {
		theModel.getOrderDao().saveOrUpdate(order);
		
	}
	



	
}
