package view.orders;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.Controller;
import controller.KlantToComboConverter;
import controller.ProductToComboConverter;
import controller.listeners.OrderCompleetListener;
import model.Gebruiker;
import model.GebruikerType;
import model.Order;
import model.Product;
import view.main.GenericPanel;
import view.main.SimpleTablePanel;

public class OrdersPanel extends GenericPanel {

	private JComboBox<String> klantComboBox;
	private JTextField totaalTextField;
	private JButton orderCompleetButton;
	private SubOrderPanel[] subOrderPanels;
	private List<Gebruiker> klantList;
	private List<Product> productList;
	private List<String> klantOptions;
	private List<String> productOptions;
	private List<BigDecimal> productPrijzen;
	private List<Order> ordersList;
	private int de1eMedewerkerId =0;
	private int orderId = 0;
	private Date orderDate;
	private JButton deleteButton;

	private static final long serialVersionUID = 1L;

	public OrdersPanel() {
		String[][] newData = { { "-", "-", "-", "-", "-", "-", "-Edit-" } };
		String[] newNames = { "ID", "Datum", "Mw Id", "Klant", "Aantal", "Totaal", "." };
		int[] newWidths = { 40, 110, 65, 405, 65, 80, 74 };

		data = newData;
		columnNames = newNames;
		columnWidths = newWidths;
		
		tablePanel = new SimpleTablePanel(data, columnNames, columnWidths);
		overzichtsPanel.add(tablePanel, BorderLayout.CENTER);
		tablePanel.setVisible(true);
	
	}
	

	protected void createNewItemPanel(Controller controller) throws Exception {
		
		newItemPanel = new JPanel();
		newItemPanel.setLayout(new BoxLayout(newItemPanel, BoxLayout.Y_AXIS));
		
		
		// title panel
		JPanel titelPanel = new JPanel();
		titelPanel.setBackground(new Color(153, 204, 102)); // greyish green
		newItemPanel.add(titelPanel);
		FlowLayout fl_titelPanel = new FlowLayout(FlowLayout.CENTER, 5, 5);
		titelPanel.setLayout(fl_titelPanel);

		JLabel nieuweOrderLbl = new JLabel("Nieuwe Order");
		nieuweOrderLbl.setVerticalAlignment(SwingConstants.BOTTOM);
		nieuweOrderLbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		titelPanel.add(nieuweOrderLbl);

		// klantKies Panel
		JPanel klantKiesPanel = new JPanel();
		newItemPanel.add(klantKiesPanel);
		FlowLayout fl_klantKiesPanel = new FlowLayout(FlowLayout.CENTER, 5, 15);
		fl_klantKiesPanel.setAlignOnBaseline(true);
		klantKiesPanel.setLayout(fl_klantKiesPanel);

		JLabel klantLabel = new JLabel("Klant: ");
		klantLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		klantKiesPanel.add(klantLabel);

		klantComboBox = new JComboBox<String>();
		klantComboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		klantComboBox.setModel(new DefaultComboBoxModel<String>());
		for (String s : klantOptions) {
			klantComboBox.addItem(s.toString());
		}
		klantKiesPanel.add(klantComboBox);
		
		deleteButton = new JButton("Delete Order");
		deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		deleteButton.setVisible(false);
		klantKiesPanel.add(deleteButton);
		
		

		// add three sub order panels
		subOrderPanels = new SubOrderPanel[3];
		for (int i = 0; i < subOrderPanels.length; i++) {
			subOrderPanels[i] = new SubOrderPanel(this, productOptions, productPrijzen);
			newItemPanel.add(subOrderPanels[i]);
		}

		JPanel orderCompleetPanel = new JPanel();
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 120, 180, 260, 165, 50, 25 };
		gbl_panel.rowHeights = new int[] { 40, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		orderCompleetPanel.setLayout(gbl_panel);


		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_CancelButton = new GridBagConstraints();
		gbc_CancelButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_CancelButton.insets = new Insets(10, 10, 0, 10);
		gbc_CancelButton.gridwidth = 1;
		gbc_CancelButton.gridx = 1;
		gbc_CancelButton.gridy = 0;
		orderCompleetPanel.add(cancelButton, gbc_CancelButton);

		orderCompleetButton = new JButton("Order Compleet");
		orderCompleetButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_CompleetButton = new GridBagConstraints();
		gbc_CompleetButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_CompleetButton.insets = new Insets(10, 20, 0, 5);
		gbc_CompleetButton.gridwidth = 1;
		gbc_CompleetButton.gridx = 2;
		gbc_CompleetButton.gridy = 0;

		orderCompleetPanel.add(orderCompleetButton, gbc_CompleetButton);

		JLabel OrderTotaalLabel = new JLabel("Order Totaal:  ");
		OrderTotaalLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		OrderTotaalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(10, 10, 0, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 0;
		orderCompleetPanel.add(OrderTotaalLabel, gbc_lblNewLabel);

		totaalTextField = new JTextField("0.00");
		totaalTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 0;
		orderCompleetPanel.add(totaalTextField, gbc_textField);
		totaalTextField.setColumns(10);

		newItemPanel.add(orderCompleetPanel);
		orderCompleetButton.addActionListener(new OrderCompleetListener(controller));
	
	}
	
	
	public void addNieuweOrderListener(ActionListener listenForNieuwButton) {

		nieuwButton.addActionListener(listenForNieuwButton);
	}

	public void addUpdateOrderListener(ActionListener listenForUpdateButton) {

		updateButton.addActionListener(listenForUpdateButton);
		
	}
	

	public void refreshNewItemPnl(Controller controller) {
		try {

			if (newItemPanel != null)
				remove(newItemPanel);
			
			fetchDataFromDao(controller);
			createNewItemPanel(controller);
				
			this.add(newItemPanel, "NewItemPanel");
			de1eMedewerkerId = 0; // is != 0 bij editten bestaande order
			orderId = 0;
			orderDate = null;
			cardLayout.show(this, "NewItemPanel");


		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	
	public void editRow(int row, Controller controller) {
		
		System.out.println("editRow method start");
		// get order
		Order order = ordersList.get(row);
		
		
		refreshNewItemPnl(controller);
		
		orderId = order.getOrderId();
		de1eMedewerkerId = order.get1eMedewerkerId();
		orderDate = order.getOrderDatum();
		
		int klantIndex =0;
		for(int i=0; i<klantList.size();i++){
			if(klantList.get(i).getGebruikerId()==order.getKlantId()){
				klantIndex = i+1; // add 1, to get the correct line in the comboBox
			}
		}
		
		klantComboBox.setSelectedIndex(klantIndex);
		subOrderPanels[0].setProductChoice(getItemIndex(order.getProductA_Id()));
		subOrderPanels[1].setProductChoice(getItemIndex(order.getProductB_Id()));
		subOrderPanels[2].setProductChoice(getItemIndex(order.getProductC_Id()));
		subOrderPanels[0].setAantal(order.getProductA_aantal());
		subOrderPanels[1].setAantal(order.getProductB_aantal());
		subOrderPanels[2].setAantal(order.getProductC_aantal());
		SumSubtotalen();
		
		deleteButton.addActionListener(e ->{
			
			int p = JOptionPane.showConfirmDialog(null,"Delete Order?","Delete",JOptionPane.YES_NO_OPTION);
			if(p==0){
				controller.getModel().getOrderDao().deleteOrder(orderId);
				setCard("overzichtsPanel");
				useUpdateBtn();
			}
		});
		deleteButton.setVisible(true);
		
	}
	

	private void fetchDataFromDao(Controller controller) throws Exception {
		// get klanten en product data uit DB via Dao
				klantList = controller.getModel().getGebruikerDao().getAllGebruikersByType(GebruikerType.KLANT);
				productList = controller.getModel().getProductDao().getAllProducten();

				// convert data from objects to lists for combobox and textfield
				KlantToComboConverter klantConverter = new KlantToComboConverter();
				ProductToComboConverter productConverter = new ProductToComboConverter(productList);

				klantOptions = klantConverter.getComboList(klantList);
				productOptions = productConverter.getComboList();
				productPrijzen = productConverter.getPrijzenList();
		
	}

	
	
	private int getItemIndex(int id){
		for(int i=0; i<productList.size();i++){
			if(productList.get(i).getProductId()==id){
				return  i+1; // add 1, to get the correct line in the comboBox
			}
		}
		return 0;
	}
	

	public BigDecimal SumSubtotalen() {

		BigDecimal totaal = new BigDecimal(0.00);

		for (SubOrderPanel sop : subOrderPanels) {
			totaal = totaal.add(sop.getSubtotaal());
		}
		totaalTextField.setText(totaal.toString());
		return totaal;
	}

	
	public List<Gebruiker> getKlantList() {
		return klantList;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public SubOrderPanel getSubOrderPanel(int index) {

		return subOrderPanels[index];
	}

	public int getKlantId() {
		int index = klantComboBox.getSelectedIndex() - 1;
		return klantList.get(index).getGebruikerId();
		
	}

	public void setOrderList(List<Order> ordersList) {
		this.ordersList = ordersList;
		
	}

	public Date getDate() {
		return orderDate;
		
	}

	public int get1eMedewerkerId() {
		return de1eMedewerkerId;
	}

	public int getOrderId() {
		return orderId;
	}
	
	
	public void useUpdateBtn(){
		//update the table
		 updateButton.getActionListeners()[0].actionPerformed(null);
	 }
	
	
	
//	public void orderCompleetAction(Controller controller) throws Exception{
//		boolean productExists;
//		int subOrderPnlIndex;
//		int indexProd;
//		
//		java.util.Date javaDate;
//		java.sql.Date sqlDate;
//		int medewerkerId;
//		int idProdA;
//		int idProdB;
//		int idProdC;
//		int aantalProdA;
//		int aantalProdB;
//		int aantalProdC;
//		
//		
//		// datum 
//		javaDate = orderDate;
//		if(javaDate==null){
//			javaDate = new java.util.Date(); // today
//		}
//		sqlDate = new java.sql.Date(javaDate.getTime());
//		
//		// medewerkerId
//		int de1eMedewerkerId= get1eMedewerkerId();
//		if(de1eMedewerkerId !=0){ // editing exising order
//			medewerkerId = de1eMedewerkerId; 
//		} else{
//		medewerkerId = controller.getView().getMainpanel().getMedewerker().getGebruikerId();
//		}
//		
//
//		// Product A id en aantal
//		subOrderPnlIndex = 0;
//		indexProd = getSubOrderPanel(subOrderPnlIndex).getProductIndex();
//		
//		productExists = (indexProd>=0);
//		if (productExists){
//			idProdA = getProductList().get(indexProd).getProductId();
//	
//			aantalProdA = getSubOrderPanel(subOrderPnlIndex).getAantal();
//		} else {
//			idProdA = 0;
//			aantalProdA = 0;
//		}
//		
//		// Product B id en aantal
//		subOrderPnlIndex = 1;
//		indexProd = getSubOrderPanel(subOrderPnlIndex).getProductIndex();
//		
//		productExists = (indexProd>=0);
//		if (productExists){
//			idProdB = getProductList().get(indexProd).getProductId();
//	
//			aantalProdB = getSubOrderPanel(subOrderPnlIndex).getAantal();
//		} else {
//			idProdB = 0;
//			aantalProdB = 0;
//		}
//
//		// Product C id en aantal
//		subOrderPnlIndex = 2;
//		indexProd = getSubOrderPanel(subOrderPnlIndex).getProductIndex();
//		
//		productExists = (indexProd>=0);
//		if (productExists){
//			idProdC = controller.getView().getMainpanel().getOrderPanel().
//					getProductList().get(indexProd).getProductId();
//	
//			aantalProdC = getSubOrderPanel(subOrderPnlIndex).getAantal();
//		} else {
//			idProdC = 0;
//			aantalProdC = 0;
//		}
//		
//		// totaal bedrag
//		BigDecimal totaalBedrag = SumSubtotalen();
//
//		
//		Order tempOrder = new Order.OrderBuilder()
//				
//				.orderId		(orderId)
//				.medewerkerId	(medewerkerId)
//				.orderDatum		(sqlDate)
//				.klantId		(getKlantId())
//				.productA_Id	(idProdA)
//				.productB_Id	(idProdB)
//				.productC_Id	(idProdC)
//				.productA_aantal(aantalProdA)
//				.productB_aantal(aantalProdB)
//				.productC_aantal(aantalProdC)
//				.totaalBedrag	(totaalBedrag)
//				.build();
//		
//		
//		if(totaalBedrag.doubleValue() >0){
//		 setCard("overzichtsPanel");
//		 // send Order to Database  ------------
//		 controller.sendOrdertoDB(tempOrder);
//		
//		 useUpdateBtn();
//		 
//		}
//	}
	
	

}
