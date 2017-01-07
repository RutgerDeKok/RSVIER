package controller;


import model.Model;
import view.View;

public class KaasAppMain {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		View kaasView = new View();
    	Model kaasModel = new Model();
	
		Controller kaasController = new Controller(kaasView,kaasModel);
		
        kaasView.setVisible(true);

	}

}
