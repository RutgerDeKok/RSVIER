package product;

import java.math.BigDecimal;

import main.KaasAppMain;
import main.MainController;


public class ProductCompleetAction  {


	
	
	public static void performAction(MainController controller) {

		try {
			
			String naam= controller.getView().getMainpanel().getProductenPanel().getProductNaam();
			BigDecimal prijs= controller.getView().getMainpanel().getProductenPanel().getProductPrijs();
			Integer aantal = controller.getView().getMainpanel().getProductenPanel().getAantVoorraad();
			
			if (naam==null ||(prijs == null || aantal ==null)){
				return;
			}
		
			
			// productId
			int productId = controller.getView().getMainpanel().getProductenPanel().getProductId();
		
			
			Product tempProduct = new Product.ProductBuilder()
					
					.productId		(productId)
					.productNaam	(naam)
					.prijs          (prijs)
					.aantalVoorraad (aantal)
					.build();
			
			 controller.getView().getMainpanel().getProductenPanel().setCard("overzichtsPanel");
			 controller.getProductController().sendProductToDB(tempProduct);
			 controller.getView().getMainpanel().getProductenPanel().updateAction();
			
			
		} catch (Exception e1) {
			KaasAppMain.logger.error("Perform action error",e1);
		}

	}

}
