package controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductToComboConverter {

	private List<String> comboItems;
	private List<BigDecimal> prijzen;

	public ProductToComboConverter(List<Product> productList) throws SQLException {
		int l = productList.size();

		comboItems = new ArrayList<String>();
		prijzen = new ArrayList<BigDecimal>();
		comboItems.add("- Kies een product -    ");
		prijzen.add(new BigDecimal(0.00));
		for (int i = 0; i < l; i++) {
			comboItems.add(convertProductToString(productList.get(i)));
			prijzen.add(productList.get(i).getPrijs());
		}
		
	}

	

	private String convertProductToString(Product tempProduct) throws SQLException {
		StringBuilder klantString = new StringBuilder();

		klantString.append(tempProduct.getProductNaam() + ", ");
		klantString.append(tempProduct.getPrijs() + "/stuk, ");
		klantString.append(tempProduct.getAantalVoorraad() + " beschikbaar");

		return klantString.toString();
	}
	
	
	public List<String> getComboList() {

		return comboItems;
	}
	

	public List<BigDecimal> getPrijzenList() {
	
		return prijzen;
	}

}
