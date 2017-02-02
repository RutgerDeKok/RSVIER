package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import java.sql.Date;

import controller.Controller;
import model.Order;

public class OrderCompleetListener implements ActionListener {

	private Controller controller;
	
	
	// constructor
	public OrderCompleetListener(Controller controller) {

		this.controller = controller;
	}
	
	
	public void actionPerformed(ActionEvent e) {

		try {
			
//			controller.getView().getMainpanel().getOrderPanel().orderCompleetAction(controller);
			
			
			boolean productExists;
			int subOrderPnlIndex;
			int indexProd;
			
			int orderId;
			java.util.Date javaDate;
			Date sqlDate;
			int medewerkerId;
			int klantId;
			int idProdA;
			int idProdB;
			int idProdC;
			int aantalProdA;
			int aantalProdB;
			int aantalProdC;
			
			// orderId
			orderId = controller.getView().getMainpanel().getOrderPanel().getOrderId();
			
			// datum 
			javaDate = controller.getView().getMainpanel().getOrderPanel().getDate();
			if(javaDate==null){
				javaDate = new java.util.Date(); // today
			}
			sqlDate = new java.sql.Date(javaDate.getTime());
			
			// medewerkerId
			int de1eMedewerkerId= controller.getView().getMainpanel().getOrderPanel().get1eMedewerkerId();
			if(de1eMedewerkerId !=0){ // editing exising order
				medewerkerId = de1eMedewerkerId; 
			} else{
			medewerkerId = controller.getView().getMainpanel().getMedewerker().getGebruikerId();
			}
			
			// klant
			klantId = controller.getView().getMainpanel().getOrderPanel().getKlantId();

			// Product A id en aantal
			subOrderPnlIndex = 0;
			indexProd = controller.getView().getMainpanel().getOrderPanel().
					getSubOrderPanel(subOrderPnlIndex).getProductIndex();
			
			productExists = (indexProd>=0);
			if (productExists){
				idProdA = controller.getView().getMainpanel().getOrderPanel().
						getProductList().get(indexProd).getProductId();
		
				aantalProdA = controller.getView().getMainpanel().getOrderPanel().
						getSubOrderPanel(subOrderPnlIndex).getAantal();
			} else {
				idProdA = 0;
				aantalProdA = 0;
			}
			
			// Product B id en aantal
			subOrderPnlIndex = 1;
			indexProd = controller.getView().getMainpanel().getOrderPanel().
					getSubOrderPanel(subOrderPnlIndex).getProductIndex();
			
			productExists = (indexProd>=0);
			if (productExists){
				idProdB = controller.getView().getMainpanel().getOrderPanel().
						getProductList().get(indexProd).getProductId();
		
				aantalProdB = controller.getView().getMainpanel().getOrderPanel().
						getSubOrderPanel(subOrderPnlIndex).getAantal();
			} else {
				idProdB = 0;
				aantalProdB = 0;
			}

			// Product C id en aantal
			subOrderPnlIndex = 2;
			indexProd = controller.getView().getMainpanel().getOrderPanel().
					getSubOrderPanel(subOrderPnlIndex).getProductIndex();
			
			productExists = (indexProd>=0);
			if (productExists){
				idProdC = controller.getView().getMainpanel().getOrderPanel().
						getProductList().get(indexProd).getProductId();
		
				aantalProdC = controller.getView().getMainpanel().getOrderPanel().
						getSubOrderPanel(subOrderPnlIndex).getAantal();
			} else {
				idProdC = 0;
				aantalProdC = 0;
			}
			
			// totaal bedrag
			BigDecimal totaalBedrag = controller.getView().getMainpanel().getOrderPanel().
					SumSubtotalen();
	
			
			Order tempOrder = new Order.OrderBuilder()
					
					.orderId		(orderId)
					.medewerkerId	(medewerkerId)
					.orderDatum		(sqlDate)
					.klantId		(klantId)
					.productA_Id	(idProdA)
					.productB_Id	(idProdB)
					.productC_Id	(idProdC)
					.productA_aantal(aantalProdA)
					.productB_aantal(aantalProdB)
					.productC_aantal(aantalProdC)
					.totaalBedrag	(totaalBedrag)
					.build();
			
			
			if(totaalBedrag.doubleValue() >0){
			 controller.getView().getMainpanel().getOrderPanel().setCard("overzichtsPanel");
			 // send Order to Database  ------------
			 controller.sendOrdertoDB(tempOrder);
			 controller.getView().getMainpanel().getOrderPanel().useUpdateBtn();
			
			}
			
		} catch (Exception e1) {
			System.out.println(e1);
		}

	}

}