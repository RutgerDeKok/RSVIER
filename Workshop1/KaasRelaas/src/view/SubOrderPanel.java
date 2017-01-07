package view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
	private JTextField totaalTextField;
	private List<String> productOptions;

	public SubOrderPanel(List<String> productOptions) {
//		this.productOptions = productOptions;
		initialize();
		setProductOptions(productOptions);
	}
	
	public SubOrderPanel() {
		initialize();
	}
	
	
	

	private void initialize() {

		GridBagLayout gridBackLayoutSO = new GridBagLayout();
		gridBackLayoutSO.columnWidths = new int[] { 150, 150, 250, 150, 150, 0 };
		gridBackLayoutSO.rowHeights = new int[] { 55, 55, 0 };
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
		aantalTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		aantalTextField.setColumns(10);
		GridBagConstraints gbc_aantalTextField = new GridBagConstraints();
		gbc_aantalTextField.fill = GridBagConstraints.BOTH;
		gbc_aantalTextField.insets = new Insets(10, 10, 0, 5);
		gbc_aantalTextField.gridx = 1;
		gbc_aantalTextField.gridy = 1;
		add(aantalTextField, gbc_aantalTextField);

		JLabel totaalLabel = new JLabel("Totaal Prijs:  ");
		totaalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totaalLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_totaalLabel = new GridBagConstraints();
		gbc_totaalLabel.fill = GridBagConstraints.BOTH;
		gbc_totaalLabel.insets = new Insets(10, 10, 0, 5);
		gbc_totaalLabel.gridx = 3;
		gbc_totaalLabel.gridy = 1;
		add(totaalLabel, gbc_totaalLabel);

		totaalTextField = new JTextField();
		totaalTextField.setText("0");
		totaalTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		totaalTextField.setEditable(false);
		totaalTextField.setColumns(10);
		GridBagConstraints gbc_totaalTextField = new GridBagConstraints();
		gbc_totaalTextField.insets = new Insets(10, 10, 0, 5);
		gbc_totaalTextField.fill = GridBagConstraints.BOTH;
		gbc_totaalTextField.gridx = 4;
		gbc_totaalTextField.gridy = 1;
		add(totaalTextField, gbc_totaalTextField);

	}

	public void setProductOptions(List<String> options) {
		productOptions = options;
		productComboBox.removeAllItems();
		for (String s : productOptions) {
			productComboBox.addItem(s.toString());
		}
	}

	public void setPrijsText(String text) {
		prijsTextField.setText(text);
	}

	public void setTotaalText(String text) {
		totaalTextField.setText(text);
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

}
