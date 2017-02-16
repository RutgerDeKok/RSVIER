package product;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.GenericPanel;
import main.KaasAppMain;
import main.MainController;
import main.SimpleTablePanel;



public class ProductenPanel extends GenericPanel{

	private static final long serialVersionUID = 1L;
	
	
	private MainController controller;
	private List<Product> productList;
	private int productId;
	
	private JTextField naamTextField;
	private JTextField prijsTextField;
	private JTextField aantVoorraadtextField;
	
	
	public ProductenPanel(MainController controller) {
		this.controller = controller;
		String[][] newData = { { "-", "-", "-", "-", "-Edit-" } };
		String[] newNames = { "ID", "Naam", "Prijs", "Voorraad",  "" };
		int[] newWidths = {65,500, 100, 100, 74 }; //total 839

		data = newData;
		columnNames = newNames;
		columnWidths = newWidths;
		
		tablePanel = new SimpleTablePanel(data, columnNames, columnWidths);
		overzichtsPanel.add(tablePanel, BorderLayout.CENTER);
		tablePanel.setVisible(true);
		itemColor = new Color(173, 216, 230); //light blue
		overzTitelLabel.setText("            Producten Overzicht  ");
		nieuwButton.setBackground(itemColor);
		nieuwButton.setText("Nieuw Product");
	
	}

	@Override
	protected void createNewItemPanel() throws Exception {
		
		newItemPanel =new JPanel();		
		newItemPanel.setLayout(new BoxLayout(newItemPanel, BoxLayout.Y_AXIS));

		// title panel
		JPanel titelPanel = new JPanel();
		titelPanel.setBackground(itemColor);
		titelPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNieuwProduct = new JLabel("Nieuw Product");
		lblNieuwProduct.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNieuwProduct.setFont(new Font("Tahoma", Font.PLAIN, 24));
		titelPanel.add(lblNieuwProduct);
		newItemPanel.add(titelPanel);
		
		// nieuw Product Grid Panel
		JPanel nieuwProductGridPnl = new JPanel();
		
		GridBagLayout gbl_niewProductGridPnl = new GridBagLayout();
		gbl_niewProductGridPnl.columnWidths = new int[] { 50, 250, 130, 270, 50 };
//		gbl_niewProductGridPnl.rowHeights = new int[] { 30, 50, 30, 30, 50, 30, 50, 0};
		gbl_niewProductGridPnl.rowHeights = new int[] {30, 50, 30, 50, 30, 50, 70, 50, 30, 0};
		gbl_niewProductGridPnl.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_niewProductGridPnl.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		nieuwProductGridPnl.setLayout(gbl_niewProductGridPnl);
		
		JLabel naamLabel = new JLabel("Naam Product: ");
		naamLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		naamLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_naamLabel = new GridBagConstraints();
		gbc_naamLabel.anchor = GridBagConstraints.EAST;
		gbc_naamLabel.insets = new Insets(0, 0, 5, 5);
		gbc_naamLabel.gridx = 1;
		gbc_naamLabel.gridy = 1;
		nieuwProductGridPnl.add(naamLabel, gbc_naamLabel);

		naamTextField = new JTextField();
		naamTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		naamTextField.setColumns(10);
		GridBagConstraints gbc_naamTextField = new GridBagConstraints();
		gbc_naamTextField.gridwidth = 2;
		gbc_naamTextField.insets = new Insets(0, 0, 5, 5);
		gbc_naamTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_naamTextField.gridx = 2;
		gbc_naamTextField.gridy = 1;
		nieuwProductGridPnl.add(naamTextField, gbc_naamTextField);

		JLabel prijsProductLabel = new JLabel("Prijs Product: ");
		prijsProductLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		prijsProductLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_prijsProductLabel = new GridBagConstraints();
		gbc_prijsProductLabel.anchor = GridBagConstraints.EAST;
		gbc_prijsProductLabel.insets = new Insets(0, 0, 5, 5);
		gbc_prijsProductLabel.gridx = 1;
		gbc_prijsProductLabel.gridy = 3;
		nieuwProductGridPnl.add(prijsProductLabel, gbc_prijsProductLabel);

		prijsTextField = new JTextField();
		prijsTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_prijsTextField = new GridBagConstraints();
		gbc_prijsTextField.insets = new Insets(0, 0, 5, 5);
		gbc_prijsTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_prijsTextField.gridx = 2;
		gbc_prijsTextField.gridy = 3;
		nieuwProductGridPnl.add(prijsTextField, gbc_prijsTextField);
		prijsTextField.setColumns(10);

		JLabel aantalOpVoorraadLabel = new JLabel("Aantal op Voorraad: ");
		aantalOpVoorraadLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		aantalOpVoorraadLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_aantalOpVoorraadLabel = new GridBagConstraints();
		gbc_aantalOpVoorraadLabel.anchor = GridBagConstraints.EAST;
		gbc_aantalOpVoorraadLabel.insets = new Insets(0, 0, 5, 5);
		gbc_aantalOpVoorraadLabel.gridx = 1;
		gbc_aantalOpVoorraadLabel.gridy = 5;
		nieuwProductGridPnl.add(aantalOpVoorraadLabel, gbc_aantalOpVoorraadLabel);

		aantVoorraadtextField = new JTextField();
		aantVoorraadtextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		aantVoorraadtextField.setColumns(10);
		GridBagConstraints gbc_aantVRtextField = new GridBagConstraints();
		gbc_aantVRtextField.insets = new Insets(0, 0, 5, 5);
		gbc_aantVRtextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_aantVRtextField.gridx = 2;
		gbc_aantVRtextField.gridy = 5;
		nieuwProductGridPnl.add(aantVoorraadtextField, gbc_aantVRtextField);
		
		///////////
		
//		deleteButton = new JButton("<html>Delete<br />Product</html>");
//		deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		GridBagConstraints gbc_deleteButton = new GridBagConstraints();
//		gbc_deleteButton.insets = new Insets(30, 80, 0, 30);
//		gbc_deleteButton.fill = GridBagConstraints.BOTH;
//		gbc_deleteButton.gridheight = 2;
//		gbc_deleteButton.gridx = 3;
//		gbc_deleteButton.gridy = 3;
//		deleteButton.setVisible(true);
//		nieuwProductGridPnl.add(deleteButton, gbc_deleteButton);
		
		
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
		gbc_cancelButton.gridx = 1;
		gbc_cancelButton.gridy = 7;
		nieuwProductGridPnl.add(cancelButton, gbc_cancelButton);
		
		itemCompleteButton.setText("Bevestig Product Gegevens");
		itemCompleteButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_nieuwButton = new GridBagConstraints();
		gbc_nieuwButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_nieuwButton.gridx = 3;
		gbc_nieuwButton.gridy = 7;
		nieuwProductGridPnl.add(itemCompleteButton, gbc_nieuwButton);
		
		newItemPanel.add(nieuwProductGridPnl);
		
	}
	
	
	
	@Override
	protected void refreshNewItempPnl() {
		try {

			if (newItemPanel != null)
				remove(newItemPanel);
			
			createNewItemPanel();
				
			this.add(newItemPanel, "NewItemPanel");
			productId = 0;
			cardLayout.show(this, "NewItemPanel");


		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	


	

	

	@Override
	public void updateAction() {
		try {
			// data ophalen uit DB via DAO
			 productList = controller.getModel().getProductDao().getAll();
			
			// List converteren naar String[][] voor tabel in view
			String[][] data;
	
			data = controller.getProductController().convertProductToTableData(productList);
			
			//update view with data
			setData(data);
			addTableListener();
			setCard("overzichtsPanel");
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
	}

	

	@Override
	public void editRow(int row) {
		KaasAppMain.logger.debug("ProductPanel editRow method start");
		// get product
		Product product = productList.get(row);
		
		refreshNewItempPnl();
		
		productId = product.getId();
		
		naamTextField.setText(product.getNaam());
		prijsTextField.setText(product.getPrijs().toString());
		aantVoorraadtextField.setText(Integer.toString(product.getAantalVoorraad()));

			
		
		
	}

	public int getProductId() {
	
		return productId;
	}

	public String getProductNaam() {
		String naam = naamTextField.getText();
		if(naam.isEmpty()){
			naam = null;
		}
		return naam;
	}
	
	public BigDecimal getProductPrijs(){

		BigDecimal prijs;
		
		try {
			prijs = new BigDecimal(Double.parseDouble(prijsTextField.getText()));
		} catch (NumberFormatException e) {
			prijs = null;
		}
		
		return prijs;
	}
	
	public Integer getAantVoorraad(){

		Integer aantal;
		
		try {
			aantal = Integer.parseInt(aantVoorraadtextField.getText());
		} catch (NumberFormatException e) {
			aantal = null;
		}
		
		return aantal;
	}
	

	@Override
	protected void itemCompleteAction() {
		ProductCompleetAction.performAction(controller);
		
	}

	

	
	
	
}
