package product;

import java.sql.SQLException;
import java.util.List;

import main.Model;

public class ProductController {
	
	private Model theModel;
	
	public ProductController(Model model) throws Exception {
		theModel = model;
	}
	
	public String[][] convertProductToTableData(List<Product> productList) throws SQLException {

		int l = productList.size();
		String[][] data = new String[l][7];
		for (int i = 0; i < l; i++) {
			data[i] = convertProductToRow(productList.get(i));

		}

		return data;
	}

	private String[] convertProductToRow(Product product) {
		// id naam prijs aantal
		String[] dataRow = new String[5];
		
		dataRow[0] = Integer.toString(product.getId());
		dataRow[1] = product.getNaam();
		dataRow[2] = product.getPrijs().toString();
		dataRow[3] = Integer.toString(product.getAantalVoorraad());
		dataRow[4] = "-Edit-";

		return dataRow;
	}

	public void sendProductToDB(Product product) {
		theModel.getProductDao().saveOrUpdate(product);
		
	}
	
	

}
