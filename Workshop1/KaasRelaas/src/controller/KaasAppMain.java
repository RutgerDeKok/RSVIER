package controller;


import model.Model;
import view.main.View;

public class KaasAppMain {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		View kaasView = new View();
    	Model kaasModel = new Model();
	
		try {
			Controller controller = new Controller(kaasView,kaasModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        kaasView.setVisible(true);

	}

}
