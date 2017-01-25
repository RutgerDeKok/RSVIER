package view.orders;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SubOrderPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	
	private JComboBox<String> productComboBox;
	private JTextField prijsTextField;
	private JTextField aantalTextField;
	private JTextField subtotaalTextField;
	private List<String> productOptions;
	private List<BigDecimal> productPrijzen = new ArrayList<BigDecimal>();
	private BigDecimal prijs;
	private NieuweOrderPanel nieuweOrderPanel;
	
	

	public SubOrderPanel(NieuweOrderPanel nieuweOrderPanel) {
		this.nieuweOrderPanel = nieuweOrderPanel;
		initialize();
	}
	
		
	

	private void initialize() {

		GridBagLayout gridBackLayoutSO = new GridBagLayout();
		gridBackLayoutSO.columnWidths = new int[] { 120, 150, 250, 150, 120, 20 };
		gridBackLayoutSO.rowHeights = new int[] { 40, 40, 10, 0 };
		gridBackLayoutSO.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		gridBackLayoutSO.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBackLayoutSO);

		JLabel productLabel = new JLabel(" Product:  ");
		productLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		productLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_productLabel = new GridBagConstraints();
		gbc_productLabel.fill = GridBagConstraints.BOTH;
		gbc_productLabel.insets = new Insets(10, 10, 5, 5);
		gbc_productLabel.gridx = 0;
		gbc_productLabel.gridy = 0;
		add(productLabel, gbc_productLabel);

		productComboBox = new JComboBox<String>();
		productComboBox.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Kies een product", "Product A", "Product B", "Product C" }));
		productComboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_productComboBox = new GridBagConstraints();
		gbc_productComboBox.gridwidth = 2;
		gbc_productComboBox.fill = GridBagConstraints.BOTH;
		gbc_productComboBox.insets = new Insets(10, 10, 5, 5);
		gbc_productComboBox.gridx = 1;
		gbc_productComboBox.gridy = 0;
		add(productComboBox, gbc_productComboBox);

		JLabel prijsLabel = new JLabel("Prijs:  ");
		prijsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		prijsLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_prijsLabel = new GridBagConstraints();
		gbc_prijsLabel.fill = GridBagConstraints.BOTH;
		gbc_prijsLabel.insets = new Insets(10, 10, 5, 5);
		gbc_prijsLabel.gridx = 3;
		gbc_prijsLabel.gridy = 0;
		add(prijsLabel, gbc_prijsLabel);

		prijsTextField = new JTextField();
		prijsTextField.setForeground(new Color(95, 158, 160));
		prijsTextField.setText("0");
		prijsTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		prijsTextField.setEditable(false);
		prijsTextField.setColumns(10);
		GridBagConstraints gbc_prijsTextField = new GridBagConstraints();
		gbc_prijsTextField.fill = GridBagConstraints.BOTH;
		gbc_prijsTextField.insets = new Insets(10, 10, 5, 5);
		gbc_prijsTextField.gridx = 4;
		gbc_prijsTextField.gridy = 0;
		add(prijsTextField, gbc_prijsTextField);

		JLabel aantalLabel = new JLabel("Aantal:  ");
		aantalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		aantalLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_aantalLabel = new GridBagConstraints();
		gbc_aantalLabel.fill = GridBagConstraints.BOTH;
		gbc_aantalLabel.insets = new Insets(10, 10, 0, 5);
		gbc_aantalLabel.gridx = 0;
		gbc_aantalLabel.gridy = 1;
		add(aantalLabel, gbc_aantalLabel);

		aantalTextField = new JTextField();
		aantalTextField.setToolTipText("Kies heel getal a.u.b.");
		aantalTextField.setText("0");
		aantalTextField.setEditable(false);
		aantalTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		aantalTextField.setColumns(10);
		GridBagConstraints gbc_aantalTextField = new GridBagConstraints();
		gbc_aantalTextField.fill = GridBagConstraints.BOTH;
		gbc_aantalTextField.insets = new Insets(10, 10, 0, 5);
		gbc_aantalTextField.gridx = 1;
		gbc_aantalTextField.gridy = 1;
		add(aantalTextField, gbc_aantalTextField);

		JLabel totaalLabel = new JLabel("Sub Totaal:  ");
		totaalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totaalLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_totaalLabel = new GridBagConstraints();
		gbc_totaalLabel.fill = GridBagConstraints.BOTH;
		gbc_totaalLabel.insets = new Insets(10, 10, 0, 5);
		gbc_totaalLabel.gridx = 3;
		gbc_totaalLabel.gridy = 1;
		add(totaalLabel, gbc_totaalLabel);

		subtotaalTextField = new JTextField();
		subtotaalTextField.setText("0");
		subtotaalTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		subtotaalTextField.setEditable(false);
		subtotaalTextField.setColumns(10);
		GridBagConstraints gbc_totaalTextField = new GridBagConstraints();
		gbc_totaalTextField.insets = new Insets(10, 10, 0, 5);
		gbc_totaalTextField.fill = GridBagConstraints.BOTH;
		gbc_totaalTextField.gridx = 4;
		gbc_totaalTextField.gridy = 1;
		add(subtotaalTextField, gbc_totaalTextField);
		
		
		
//		// handler 1
//		productComboBox.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent e) {
//				
//				aantalTextField.setText("0");
//				subtotaalTextField.setText("0");
//				
//				if(productComboBox.getSelectedIndex()==0) {
//					
//					setPrijs(0);
//					aantalTextField.setEditable(false);
//					
//				}else{ aantalTextField.setEditable(true);
//				
//				int keuze = (int) productComboBox.getSelectedIndex();
//				setPrijs(keuze);
//				
//				}
//				nieuweOrderPanel.SumSubtotalen();
//			}	
//		});
//		
//		// handler 2
//		aantalTextField.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				try {
//					int aantal = Integer.parseInt(aantalTextField.getText());
//			
//					BigDecimal subtotaal = new BigDecimal(aantal).multiply(prijs);
//					
//					subtotaalTextField.setText(subtotaal.toString());
//
//					
//				} catch (NumberFormatException e1) {
////					e1.printStackTrace();
//					aantalTextField.setText("0");
//				}finally{
//					nieuweOrderPanel.SumSubtotalen();
//				}			
//			}
//		});
		
		
	
	}
	


	
	//add two handlers
	public void addProductedListeners(){  
		
		// handlers cannot be added in constructor. because calling setPrijs(keuze)
		// would give a null pointer error at that time
		
		// handler 1
		productComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				aantalTextField.setText("0");
				subtotaalTextField.setText("0");
				
				if(productComboBox.getSelectedIndex()==0) {
					
					setPrijs(0);
					aantalTextField.setEditable(false);
					
				}else{ aantalTextField.setEditable(true);
				
				int keuze = (int) productComboBox.getSelectedIndex();
				setPrijs(keuze);
				
				}
				nieuweOrderPanel.SumSubtotalen();
			}	
		});
		
		// handler 2
		aantalTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int aantal = Integer.parseInt(aantalTextField.getText());
			
					BigDecimal subtotaal = new BigDecimal(aantal).multiply(prijs);
					
					subtotaalTextField.setText(subtotaal.toString());

					
				} catch (NumberFormatException e1) {
//					e1.printStackTrace();
					aantalTextField.setText("0");
				}finally{
					nieuweOrderPanel.SumSubtotalen();
				}			
			}
		});
		
	} // end addProductedListeners method
	
	
	public void setProductOptions(List<String> options) {
		productOptions = options;
		productComboBox.removeAllItems();
		
		for (String s : productOptions) {
			System.out.println(s);
			productComboBox.addItem(s.toString());
		}
	}
	
	public void setProductPrijzen(List<BigDecimal> prijzen) {
		this.productPrijzen = prijzen;
	}
	
	public void setPrijs(int keuzeIndex){
		prijs = productPrijzen.get(keuzeIndex);
		prijsTextField.setText(prijs.toString());
		
	}


	public void setTotaalText(String text) {
		subtotaalTextField.setText(text);
	}

	public void setProductOptions() {
		productComboBox.getSelectedItem();
	}

	public String getProductChoice() {
		return (String) productComboBox.getSelectedItem();
	}

	public int getAantal() {
		return Integer.parseInt(aantalTextField.getText());
	}


	public BigDecimal getSubtotaal(){
		return new BigDecimal(Double.parseDouble(subtotaalTextField.getText()));
	}
	
	public int getProductIndex(){
		return productComboBox.getSelectedIndex()-1;
	}
	

}



