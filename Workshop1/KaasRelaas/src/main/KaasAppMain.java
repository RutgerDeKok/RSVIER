package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KaasAppMain {
	
	public static final Logger logger = LoggerFactory.getLogger(MainController.class);	

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		View theView = new View();
    	Model theModel = new Model();
	
		try {
			MainController orderController = new MainController(theView,theModel);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        theView.setVisible(true);

	}

}
