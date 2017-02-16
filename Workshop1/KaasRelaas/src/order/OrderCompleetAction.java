package order;

import java.math.BigDecimal;

import java.sql.Date;

import main.KaasAppMain;
import main.MainController;

public class OrderCompleetAction  {


	public static void performAction(MainController controller) {

		try {
			// totaal bedrag
			BigDecimal totaalBedrag = controller.getView().getMainpanel().getOrderPanel().SumSubtotalen();

			if (totaalBedrag.doubleValue() <= 0) {
				return;
			} //end if

			boolean productExists;
			int medewerkerId;
			int idProdA;
			int idProdB;
			int idProdC;
			int aantalProdA;
			int aantalProdB;
			int aantalProdC;

			// orderId
			int orderId = controller.getView().getMainpanel().getOrderPanel().getOrderId();

			// datum
			java.util.Date javaDate = controller.getView().getMainpanel().getOrderPanel().getDate();
			if (javaDate == null) {
				javaDate = new java.util.Date(); // today
			}
			Date sqlDate = new java.sql.Date(javaDate.getTime());

			// medewerkerId
			int de1eMedewerkerId = controller.getView().getMainpanel().getOrderPanel().get1eMedewerkerId();
			if (de1eMedewerkerId != 0) { // editing existing order
				medewerkerId = de1eMedewerkerId;
			} else {
				medewerkerId = controller.getView().getMainpanel().getMedewerker().getId();
			}

			// klant
			int klantId = controller.getView().getMainpanel().getOrderPanel().getKlantId();

			// Product A id en aantal
			int subOrderPnlIndex = 0;
			int indexProd = controller.getView().getMainpanel().getOrderPanel().getSubOrderPanel(subOrderPnlIndex)
					.getProductIndex();

			productExists = (indexProd >= 0);
			if (productExists) {
				idProdA = controller.getView().getMainpanel().getOrderPanel().getProductList().get(indexProd)
						.getId();

				aantalProdA = controller.getView().getMainpanel().getOrderPanel().getSubOrderPanel(subOrderPnlIndex)
						.getAantal();
			} else {
				idProdA = 0;
				aantalProdA = 0;
			}

			// Product B id en aantal
			subOrderPnlIndex = 1;
			indexProd = controller.getView().getMainpanel().getOrderPanel().getSubOrderPanel(subOrderPnlIndex)
					.getProductIndex();

			productExists = (indexProd >= 0);
			if (productExists) {
				idProdB = controller.getView().getMainpanel().getOrderPanel().getProductList().get(indexProd)
						.getId();

				aantalProdB = controller.getView().getMainpanel().getOrderPanel().getSubOrderPanel(subOrderPnlIndex)
						.getAantal();
			} else {
				idProdB = 0;
				aantalProdB = 0;
			}

			// Product C id en aantal
			subOrderPnlIndex = 2;
			indexProd = controller.getView().getMainpanel().getOrderPanel().getSubOrderPanel(subOrderPnlIndex)
					.getProductIndex();

			productExists = (indexProd >= 0);
			if (productExists) {
				idProdC = controller.getView().getMainpanel().getOrderPanel().getProductList().get(indexProd)
						.getId();

				aantalProdC = controller.getView().getMainpanel().getOrderPanel().getSubOrderPanel(subOrderPnlIndex)
						.getAantal();
			} else {
				idProdC = 0;
				aantalProdC = 0;
			}

			Order tempOrder = new Order.OrderBuilder()

					.orderId			(orderId)
					.medewerkerId		(medewerkerId)
					.orderDatum			(sqlDate)
					.klantId			(klantId)
					.productA_Id		(idProdA)
					.productB_Id		(idProdB)
					.productC_Id		(idProdC)
					.productA_aantal	(aantalProdA)
					.productB_aantal	(aantalProdB)
					.productC_aantal	(aantalProdC)
					.totaalBedrag		(totaalBedrag)
					.build();

			controller.getView().getMainpanel().getOrderPanel().setCard("overzichtsPanel");
			controller.getOrderController().sendOrdertoDB(tempOrder);
			controller.getView().getMainpanel().getOrderPanel().updateAction();

		} catch (Exception e1) {
			KaasAppMain.logger.error("Perform Action error",e1);
		}

	}

}