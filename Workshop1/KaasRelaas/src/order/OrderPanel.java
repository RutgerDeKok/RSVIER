package order;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

import gebruiker.Gebruiker;
import gebruiker.GebruikerType;
import main.panels.GenericPanel;
import main.KaasAppMain;
import main.MainController;
import main.panels.SimpleTablePanel;
import order.OrderController.ListWrapper;
import product.Product;

public class OrderPanel extends GenericPanel {

	private MainController controller;
	private JComboBox<String> klantComboBox;
	private JTextField totaalTextField;
	private JButton deleteButton;
	private SubOrderPanel[] subOrderPanels;

	private List<Gebruiker> klantList; // list hold a Gebruiker for every klant in DB
	private List<Product> productList; // list hold a Product for every product in DB
	
	private List<String> klantOptions; // List hold a String for every klant used by comboBox
	private List<String> productOptions; // List hold a String for every product used by comboBox
	private List<BigDecimal> productPrijzen; // List hold a the price of every product used by TextField
	private List<Order> ordersList; // List with all orders, used by editRow method

	private int de1eMedewerkerId = 0; // original medewerker that made the order being edited
	private int orderId = 0; // new orders are 0, edited orders used DB id
	private Date orderDate; // used by edited orders, new orders get todays date at later stage

	private static final long serialVersionUID = 1L;

	public OrderPanel(MainController controller) {
		this.controller = controller;
		String[][] newData = { { "-", "-", "-", "-", "-", "-", "-Edit-" } };
		String[] newNames = { "ID", "Datum", "Mw Id", "Klant", "Aantal", "Totaal", "" };
		int[] newWidths = { 40, 110, 65, 405, 65, 80, 74 };

		data = newData;
		columnNames = newNames;
		columnWidths = newWidths;

		tablePanel = new SimpleTablePanel(data, columnNames, columnWidths);
		overzichtsPanel.add(tablePanel, BorderLayout.CENTER);
		tablePanel.setVisible(true);
		itemColor = new Color(153, 204, 102); // geyish green
		nieuwButton.setBackground(itemColor);
		nieuwButton.setText("Nieuwe Order");

	}

	protected void createNewItemPanel() throws Exception {

		newItemPanel = new JPanel();
		newItemPanel.setLayout(new BoxLayout(newItemPanel, BoxLayout.Y_AXIS));
		Font plain20 = new Font("Tahoma", Font.PLAIN, 20);

		// title panel
		JPanel titelPanel = new JPanel();
		titelPanel.setBackground(itemColor);
		FlowLayout fl_titelPanel = new FlowLayout(FlowLayout.CENTER, 5, 5);
		titelPanel.setLayout(fl_titelPanel);

		JLabel nieuweOrderLbl = new JLabel("Nieuwe Order");
		nieuweOrderLbl.setVerticalAlignment(SwingConstants.BOTTOM);
		nieuweOrderLbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		titelPanel.add(nieuweOrderLbl);
		newItemPanel.add(titelPanel);

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
		klantComboBox.setFont(plain20);
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

		// order compleet panel
		JPanel orderCompleetPanel = new JPanel();
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 120, 180, 260, 165, 50, 25 };
		gbl_panel.rowHeights = new int[] { 40, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		orderCompleetPanel.setLayout(gbl_panel);

		cancelButton.setFont(plain20);
		GridBagConstraints gbc_CancelButton = new GridBagConstraints();
		gbc_CancelButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_CancelButton.insets = new Insets(10, 10, 0, 10);
		gbc_CancelButton.gridwidth = 1;
		gbc_CancelButton.gridx = 1;
		gbc_CancelButton.gridy = 0;
		orderCompleetPanel.add(cancelButton, gbc_CancelButton);

		itemCompleteButton.setText("Order Compleet");
		itemCompleteButton.setFont(plain20);
		GridBagConstraints gbc_CompleetButton = new GridBagConstraints();
		gbc_CompleetButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_CompleetButton.insets = new Insets(10, 20, 0, 5);
		gbc_CompleetButton.gridwidth = 1;
		gbc_CompleetButton.gridx = 2;
		gbc_CompleetButton.gridy = 0;

		orderCompleetPanel.add(itemCompleteButton, gbc_CompleetButton);

		JLabel OrderTotaalLabel = new JLabel("Order Totaal:  ");
		OrderTotaalLabel.setFont(plain20);
		OrderTotaalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(10, 10, 0, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 0;
		orderCompleetPanel.add(OrderTotaalLabel, gbc_lblNewLabel);

		totaalTextField = new JTextField("0.00");
		totaalTextField.setFont(plain20);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 0;
		orderCompleetPanel.add(totaalTextField, gbc_textField);
		totaalTextField.setColumns(10);

		newItemPanel.add(orderCompleetPanel);
		
	}  // end createNewItemPanel()
	
	
	@Override
	protected void itemCompleteAction() {
		OrderCompleetAction.performAction(controller);
	}

	@Override
	public void refreshNewItempPnl() {
		try {

			if (newItemPanel != null)
				remove(newItemPanel);

			fetchDataFromDaos();
			createNewItemPanel();

			this.add(newItemPanel, "NewItemPanel");
			de1eMedewerkerId = 0; // is != 0 bij editten bestaande order
			orderId = 0;
			orderDate = null;
			cardLayout.show(this, "NewItemPanel");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void editRow(int row) {

		KaasAppMain.logger.debug("editRow method start");
		// get order
		Order order = ordersList.get(row);

		refreshNewItempPnl();

		orderId = order.getId();
		de1eMedewerkerId = order.get1eMedewerkerId();
		orderDate = order.getOrderDatum();

		int klantIndex = 0;
		for (int i = 0; i < klantList.size(); i++) {
			if (klantList.get(i).getId() == order.getKlantId()) {
				klantIndex = i + 1; // add 1, to get the correct line in the
									// comboBox
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

		deleteButton.addActionListener(e -> {

			int confirmation = JOptionPane.showConfirmDialog(null, "Delete Order?", "Delete", JOptionPane.YES_NO_OPTION);
			if (confirmation == 0) {
				controller.getModel().getOrderDao().delete(orderId);
				setCard("overzichtsPanel");
				updateAction();
			}
		});
		deleteButton.setVisible(true);
	}
	
	
	private int getItemIndex(int id) {
		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).getId() == id) {
				return i + 1; // add 1, to get the correct line in the comboBox
			}
		}
		return 0;
	}


	protected void fetchDataFromDaos() throws Exception {
		// get klanten en product data uit DB via Dao
		klantList = controller.getModel().getGebruikerDao().getAllByType(GebruikerType.KLANT,true);
		productList = controller.getModel().getProductDao().getAll();
		
		// genereer klant combo box List
		klantOptions = controller.getGebruikerController().getKlantComboList(klantList);
	
//		Object[]  objects = controller.getOrderController().convertProductToCombo(productList);
//		productOptions = (List<String>)objects[0];
//		productPrijzen =  (List<BigDecimal>) objects[1];

		ListWrapper wrap = controller.getOrderController().convertProductToCombo(productList);
		productOptions = wrap.getComboItems();
		productPrijzen =  wrap.getPrijzen();
	}

	@Override
	public void updateAction() {
		try {
			// data ophalen uit DB via DAO
			ordersList = controller.getModel().getOrderDao().getAll();

			// List converteren naar String[][] voor tabel in view
			String[][] data;

			data = controller.getOrderController().convertOrdersToTableData(ordersList);

			// update view with data
			setData(data);
			addTableListener();
			setCard("overzichtsPanel");
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	
	

	public BigDecimal SumSubtotalen() {

		BigDecimal totaal = new BigDecimal("0.00");
		

		for (SubOrderPanel sop : subOrderPanels) {
			totaal = totaal.add(sop.getSubtotaal());
//			BigDecimal cents = totaal.multiply(new BigDecimal(100));
//			int ic= cents.ROUND_HALF_UP;
//			totaal = new BigDecimal(ic);
			totaal.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		totaalTextField.setText(totaal.toString());
		return totaal;
	}
	
	
	// GETTERS

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
		return klantList.get(index).getId();
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

}
