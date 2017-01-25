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
			boolean productExists;
			int subOrderPnlIndex;
			int indexProd;
			
			Date sqlDate;
			int klantId;
			int idProdA;
			int idProdB;
			int idProdC;
			int aantalProdA;
			int aantalProdB;
			int aantalProdC;
			
			
			// datum (today)
			sqlDate = new java.sql.Date(new java.util.Date().getTime());
			// klant
			klantId = controller.getView().getMainpanel().getOrderPanel().
					getNieuweOrderPnl().getklantId();

			// Product A id en aantal
			subOrderPnlIndex = 0;
			indexProd = controller.getView().getMainpanel().getOrderPanel().
					getNieuweOrderPnl().getSubOrderPanel(subOrderPnlIndex).getProductIndex();
			
			productExists = (indexProd>=0);
			if (productExists){
				idProdA = controller.getView().getMainpanel().getOrderPanel().
						getNieuweOrderPnl().getProductList().get(indexProd).getProductId();
		
				aantalProdA = controller.getView().getMainpanel().getOrderPanel().
						getNieuweOrderPnl().getSubOrderPanel(subOrderPnlIndex).getAantal();
			} else {
				idProdA = 0;
				aantalProdA = 0;
			}
			
			// Product B id en aantal
			subOrderPnlIndex = 1;
			indexProd = controller.getView().getMainpanel().getOrderPanel().
					getNieuweOrderPnl().getSubOrderPanel(subOrderPnlIndex).getProductIndex();
			
			productExists = (indexProd>=0);
			if (productExists){
				idProdB = controller.getView().getMainpanel().getOrderPanel().
						getNieuweOrderPnl().getProductList().get(indexProd).getProductId();
		
				aantalProdB = controller.getView().getMainpanel().getOrderPanel().
						getNieuweOrderPnl().getSubOrderPanel(subOrderPnlIndex).getAantal();
			} else {
				idProdB = 0;
				aantalProdB = 0;
			}

			// Product C id en aantal
			subOrderPnlIndex = 2;
			indexProd = controller.getView().getMainpanel().getOrderPanel().
					getNieuweOrderPnl().getSubOrderPanel(subOrderPnlIndex).getProductIndex();
			
			productExists = (indexProd>=0);
			if (productExists){
				idProdC = controller.getView().getMainpanel().getOrderPanel().
						getNieuweOrderPnl().getProductList().get(indexProd).getProductId();
		
				aantalProdC = controller.getView().getMainpanel().getOrderPanel().
						getNieuweOrderPnl().getSubOrderPanel(subOrderPnlIndex).getAantal();
			} else {
				idProdC = 0;
				aantalProdC = 0;
			}
			
			// totaal bedrag
			BigDecimal totaalBedrag = controller.getView().getMainpanel().getOrderPanel().
					getNieuweOrderPnl().SumSubtotalen();
	
			
			Order tempOrder = new Order.OrderBuilder()
					
					.medewerkerId	(1)
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
			 controller.getView().getMainpanel().getOrderPanel().setOrderCard("overzOrdersPnl");
			 // send Order to Database  ------------
			 controller.sendOrdertoDB(tempOrder);
		
			}
			
		} catch (Exception e1) {
			System.out.println(e1);
		}

	}

} // end NieuweOrderListener inner class