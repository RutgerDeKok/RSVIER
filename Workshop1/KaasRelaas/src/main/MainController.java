package main;

import order.OrderController;

import java.sql.SQLException;



import gebruiker.GebruikerController;
import product.ProductController;

public class MainController {

	private View theView;
	private Model theModel;
	private OrderController orderController;
	private GebruikerController gebruikerController;
	private ProductController productController;


	public MainController(View view, Model model) throws Exception {
		theModel = model;
		theView = view;

		orderController = new OrderController(theModel);
		gebruikerController = new GebruikerController(theModel);
		productController = new ProductController(theView, theModel);

		theView.initialize(this);
	}

	
	public void startConnection(){
		theModel.startConnection();
	}

	
	public void closeConnection(){
		try {
			theModel.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// getters
	
	public Model getModel() {
		return theModel;
	}

	public View getView() {
		return theView;
	}

	public OrderController getOrderController() {
		return orderController;
	}

	public GebruikerController getGebruikerController() {
		return gebruikerController;
	}

	public ProductController getProductController() {
		return productController;
	}

}
