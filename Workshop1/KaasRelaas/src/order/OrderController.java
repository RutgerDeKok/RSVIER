package order;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import gebruiker.Gebruiker;
import main.Model;
import product.Product;

public class OrderController {

	private Model theModel;

	public OrderController( Model model) throws Exception {
		theModel = model;
	}


	
	public ListWrapper convertProductToCombo(List<Product> productList) throws SQLException {

		List<String> comboItems;
		List<BigDecimal> prijzen;
		int listSize = productList.size();

		comboItems = new ArrayList<String>();
		prijzen = new ArrayList<BigDecimal>();
		comboItems.add("- Kies een product -    ");
		prijzen.add(new BigDecimal(0.00));
		for (int i = 0; i < listSize; i++) {
			comboItems.add(convertProductToString(productList.get(i)));
			prijzen.add(productList.get(i).getPrijs());
		}
		
		ListWrapper wrap = new ListWrapper();
		wrap.setComboItems(comboItems);
		wrap.setPrijzen(prijzen);
		return wrap;
		
//		Object[] objects = new Object[2];
//		objects[0] = comboItems;
//		objects[1] = prijzen;
//		return objects;

	}
	
	

	public String[][] convertOrdersToTableData(List<Order> ordersList) throws SQLException {

		int l = ordersList.size();
		String[][] data = new String[l][7];
		for (int i = 0; i < l; i++) {
			data[i] = convertOrderToRow(ordersList.get(i));

		}

		return data;
	}



	public void sendOrdertoDB(Order order) throws Exception {
		theModel.getOrderDao().saveOrUpdate(order);

	}
	
	
	private String getKlantString(int klantId) throws SQLException {
		StringBuilder klantString = new StringBuilder();

		Gebruiker tempKlant = theModel.getGebruikerDao().getAllById(klantId);
		klantString.append(tempKlant.getAchternaam() + ", ");
		klantString.append(tempKlant.getTussenVoegsel() + "  ");
		klantString.append(tempKlant.getVoornaam());

		return klantString.toString();
	}

	private String convertProductToString(Product tempProduct) throws SQLException {
		StringBuilder klantString = new StringBuilder();

		klantString.append(tempProduct.getNaam() + ", ");
		klantString.append(tempProduct.getPrijs() + "/stuk, ");
		klantString.append(tempProduct.getAantalVoorraad() + " beschikbaar");

		return klantString.toString();
	}
	
	private String[] convertOrderToRow(Order order) throws SQLException {

		String[] dataRow = new String[7];
		int totaalAantalProd = 0;
		totaalAantalProd += order.getProductA_aantal();
		totaalAantalProd += order.getProductB_aantal();
		totaalAantalProd += order.getProductC_aantal();

		int klantId = order.getKlantId();

		String klantString = getKlantString(klantId);

		dataRow[0] = Integer.toString(order.getId());
		dataRow[1] = order.getOrderDatum().toString();
		dataRow[2] = Integer.toString(order.get1eMedewerkerId());
		dataRow[3] = klantString;
		dataRow[4] = Integer.toString(totaalAantalProd);
		dataRow[5] = order.getTotaalBedrag().toString();
		dataRow[6] = "-Edit-";

		return dataRow;

	}
	
	public class ListWrapper{
		
		private List<String> strings = new ArrayList<String>();
		private List<BigDecimal> numbers = new ArrayList<BigDecimal>();
		
		public List<String> getComboItems() {
			return strings;
		}
		public void setComboItems(List<String> strings) {
			this.strings = strings;
		}
		public List<BigDecimal> getPrijzen() {
			return numbers;
		}
		public void setPrijzen(List<BigDecimal> numbers) {
			this.numbers = numbers;
		}
		
	}
	
	
}


