package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

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
			// populate Order
			Order tempOrder = new Order();


			/* set Medewerker Id = 1, als inlog functie werkt kan 
			 * de juiste medewerker geselecteerd worden 
			 */
			tempOrder.setMedewerkerId(1);

			// datum
			
			java.util.Date javaDate = new java.util.Date();
			long javaTime = javaDate.getTime();
			java.sql.Date sqlDate = new java.sql.Date(javaTime);
			tempOrder.setOrderDatum(sqlDate);

			// klant
			int klantId = controller.getView().getMainpanel().getOrderPanel().
					getNieuweOrderPnl().getklantId();
			tempOrder.setKlantId(klantId);

			// Product A id en aantal

			int prodIindex = controller.getView().getMainpanel().getOrderPanel().
					getNieuweOrderPnl().getSubOrderPanel(0).getProductIndex();
			if (prodIindex >= 0) {
				int prodId = controller.getView().getMainpanel().getOrderPanel().
						getNieuweOrderPnl().getProductList().get(prodIindex).getProductId();
				tempOrder.setProductA_Id(prodId);

				int aantal = controller.getView().getMainpanel().getOrderPanel().
						getNieuweOrderPnl().getSubOrderPanel(0).getAantal();
				tempOrder.setProductA_aantal(aantal);
			}
			
			// Product B id en aantal
			int panelIndex = 0;
			prodIindex = controller.getView().getMainpanel().getOrderPanel().
					getNieuweOrderPnl().getSubOrderPanel(panelIndex).getProductIndex();
			if (prodIindex >= 0) {
				int prodId = controller.getView().getMainpanel().getOrderPanel().
						getNieuweOrderPnl().getProductList().get(prodIindex).getProductId();
				tempOrder.setProductB_Id(prodId);

				int aantal = controller.getView().getMainpanel().getOrderPanel().
						getNieuweOrderPnl().getSubOrderPanel(panelIndex).getAantal();
				tempOrder.setProductB_aantal(aantal);
			}
			
			// Product B id en aantal
			panelIndex = 1;
			prodIindex = controller.getView().getMainpanel().getOrderPanel().
					getNieuweOrderPnl().getSubOrderPanel(panelIndex).getProductIndex();
			if (prodIindex >= 0) {
				int prodId = controller.getView().getMainpanel().getOrderPanel().
						getNieuweOrderPnl().getProductList().get(prodIindex).getProductId();
				tempOrder.setProductB_Id(prodId);

				int aantal = controller.getView().getMainpanel().getOrderPanel().
						getNieuweOrderPnl().getSubOrderPanel(panelIndex).getAantal();
				tempOrder.setProductB_aantal(aantal);
			}
			
			// Product C id en aantal
			panelIndex = 2;
			prodIindex = controller.getView().getMainpanel().getOrderPanel().
					getNieuweOrderPnl().getSubOrderPanel(panelIndex).getProductIndex();
			if (prodIindex >= 0) {
				int prodId = controller.getView().getMainpanel().getOrderPanel().
						getNieuweOrderPnl().getProductList().get(prodIindex).getProductId();
				tempOrder.setProductB_Id(prodId);

				int aantal = controller.getView().getMainpanel().getOrderPanel().
						getNieuweOrderPnl().getSubOrderPanel(panelIndex).getAantal();
				tempOrder.setProductB_aantal(aantal);
			}
			
			// totaal bedrag
			BigDecimal totaalBedrag = controller.getView().getMainpanel().getOrderPanel().
					getNieuweOrderPnl().SumSubtotalen();
			tempOrder.setTotaalBedrag(totaalBedrag);
			//
			
			if(totaalBedrag.doubleValue() >0){
			 controller.getView().getMainpanel().getOrderPanel().setOrderCard("overzOrdersPnl");
			 // send Order to Database  ------------
			 controller.sendOrdertoDB(tempOrder);
			 //-------------------------------------
			}
			
		} catch (Exception e1) {
			System.out.println(e1);
		}

	}

} // end NieuweOrderListener inner class