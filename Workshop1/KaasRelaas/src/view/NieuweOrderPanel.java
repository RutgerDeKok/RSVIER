package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Gebruiker;
import model.Product;


public class NieuweOrderPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JComboBox<String> klantComboBox;
	private List<String> klantOptions;
	private JTextField totaalTextField;
	private JButton orderCompleetButton;
	private JButton cancelButton;
	private SubOrderPanel[] subOrderPanels;
	private List<Gebruiker> klantList;
	private List<Product> productList;
	


	public NieuweOrderPanel(){

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel titelPanel = new JPanel();
		titelPanel.setBackground(new Color(153,204,102));  //greyish green
		add(titelPanel);
		FlowLayout fl_titelPanel = new FlowLayout(FlowLayout.CENTER, 5, 5);
		titelPanel.setLayout(fl_titelPanel);
		
		JLabel nieuweOrderLbl = new JLabel("Nieuwe Order");
		nieuweOrderLbl.setVerticalAlignment(SwingConstants.BOTTOM);
		nieuweOrderLbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		titelPanel.add(nieuweOrderLbl);
		
		JPanel klantKiesPanel = new JPanel();
		add(klantKiesPanel);
		FlowLayout fl_klantKiesPanel = new FlowLayout(FlowLayout.CENTER, 5, 15);
		fl_klantKiesPanel.setAlignOnBaseline(true);
		klantKiesPanel.setLayout(fl_klantKiesPanel);
		
		JLabel klantLabel = new JLabel("Klant: ");
		klantLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		klantKiesPanel.add(klantLabel);
		
		klantComboBox = new JComboBox<String>();
		klantComboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		klantComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {
				" Kies een klant                                              ", "Klant A", "Klant B", "Klant C"}));
		klantKiesPanel.add(klantComboBox);
		
		
		
		// add three sub order panels
		subOrderPanels = new SubOrderPanel[3];
		for(int i = 0; i< subOrderPanels.length;i++){
			subOrderPanels[i] = new SubOrderPanel(this);
			add(subOrderPanels[i]);
		}
		
		
		
		JPanel orderCompleetPanel = new JPanel();
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 120, 180, 260, 165, 50, 25};
		gbl_panel.rowHeights = new int[] {40, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		orderCompleetPanel.setLayout(gbl_panel);
		
		cancelButton = new JButton("Cancel");
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
		
		add(orderCompleetPanel);
	}
	
	
	public BigDecimal SumSubtotalen(){
		
		BigDecimal totaal = new BigDecimal(0.00);
		
		for(SubOrderPanel sop : subOrderPanels){
			totaal = totaal.add(sop.getSubtotaal());
		}
		totaalTextField.setText(totaal.toString());
		return totaal;
	}
	
	
	public void addCancelOrderListener(ActionListener listenForCancelButton){  
		System.out.println("adding cancel listener");
		cancelButton.addActionListener(listenForCancelButton);
	}
	
	
	
	public void addOrderCompleetListener(ActionListener listenForCompleetButton){  
		
		orderCompleetButton.addActionListener(listenForCompleetButton);
	}
	
	
	public void addProductSelectListener(){
		
		for(SubOrderPanel sop :subOrderPanels){
			sop.addProductedListeners();
		}
	}
	
	
	
	
	// getters and setters
	
	public void setKlantList(List<Gebruiker> klantList) {
		this.klantList = klantList;
	}
	
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	public void setProductOptions(List<String> options){
		System.out.println(options);
		for(SubOrderPanel sop :subOrderPanels){
			System.out.println(sop);
		sop.setProductOptions(options);
		}
	}
	
	public void setProductPrijzen(List<BigDecimal> productPrijzen) {
		
		for(SubOrderPanel sop :subOrderPanels){
			sop.setProductPrijzen(productPrijzen);
		}
	}
	
	
	
	public List<Gebruiker> getKlantList() {
		return klantList;
	}





	public List<Product> getProductList() {
		return productList;
	}



	
	
	public void setKlantOptions(List<String> options) {
		klantOptions = options;
		klantComboBox.removeAllItems();
		for (String s : klantOptions) {
			klantComboBox.addItem(s.toString());
		}
	}
	
	public JButton getCancelButton(){
		return cancelButton;
	}
	

	
	public SubOrderPanel getSubOrderPanel(int index){
		
		return subOrderPanels[index];
	}
	
		
	
	public int getklantId(){
		int index =  klantComboBox.getSelectedIndex()-1;
		System.out.println("Klant index = "+index);
		System.out.println("Klant = "+klantList.get(index));
		return klantList.get(index).getGebruikerId();
	}

	
}
