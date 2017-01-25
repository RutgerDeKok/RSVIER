package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

import controller.Controller;
import controller.KlantToComboConverter;
import controller.ProductToComboConverter;
import model.Gebruiker;
import model.Product;

public class NieuweOrderListener implements ActionListener {
	
	private Controller controller;
	private KlantToComboConverter klantConverter;
	private ProductToComboConverter productConverter;
	private List<String> klantOptions; 
	private List<String> productOptions;
	private List<BigDecimal> productPrijzen;
	private List<Gebruiker> klantList;
	private List<Product> productList;
	
	
	public NieuweOrderListener(Controller controller){
		this.controller = controller;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		
		try {
			// refresh the panel otherwise the second time you go there there is an error
			controller.getView().getMainpanel().getOrderPanel().refreshNewOrderPnl();
			controller.getView().getMainpanel().getOrderPanel().getNieuweOrderPnl().addOrderCompleetListener(new OrderCompleetListener(controller));
			
			// get klanten en product data uit DB via Dao
			klantList = controller.getModel().getGebruikerDao().getAllKlantenByType("Klant");
			productList = controller.getModel().getProductDao().getAllProducten();
			
			// update klantList en productList in nieuweOrderPanel,  needed when Order is complete button is pressed
			controller.getView().getMainpanel().getOrderPanel().getNieuweOrderPnl().setKlantList(klantList);
			controller.getView().getMainpanel().getOrderPanel().getNieuweOrderPnl().setProductList(productList);
			
			
			// convert data from objects to lists for combobox and textfield
			klantConverter = new KlantToComboConverter();
			productConverter = new ProductToComboConverter(productList);
			
			
			klantOptions = klantConverter.getComboList(klantList);
			productOptions = productConverter.getComboList();
			productPrijzen = productConverter.getPrijzenList();
			
			
			// populate comboBox met klanten en producten
			controller.getView().getMainpanel().getOrderPanel().getNieuweOrderPnl().setKlantOptions(klantOptions);
			controller.getView().getMainpanel().getOrderPanel().getNieuweOrderPnl().setProductOptions(productOptions);
			controller.getView().getMainpanel().getOrderPanel().getNieuweOrderPnl().setProductPrijzen(productPrijzen);
			
			// add ItemListenere 
			controller.getView().getMainpanel().getOrderPanel().getNieuweOrderPnl().addProductSelectListeners();
			
			
			
			// switch the panel
			controller.getView().getMainpanel().getOrderPanel().setOrderCard("NieuweOrderPnl");
			
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}		
	
	}
	
		
} 
