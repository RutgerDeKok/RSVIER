package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class NieuweOrderPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JComboBox<String> klantComboBox;
	private List<String> klantOptions;
	private JButton orderCompleetButton;
	
	
	
	public NieuweOrderPanel(){

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel titelPanel = new JPanel();
		titelPanel.setBackground(new Color(153, 204, 51));
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
		SubOrderPanel[] subOrderPanels = new SubOrderPanel[3];
		for(int i =0; i< subOrderPanels.length;i++){

			subOrderPanels[i] = new SubOrderPanel();
			add(subOrderPanels[i]);
		}
		
		JPanel orderCompleetPanel = new JPanel();
		orderCompleetPanel.setLayout(new BorderLayout(0, 0));
		
		
		
		orderCompleetButton = new JButton("Order Compleet");
		orderCompleetButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		orderCompleetPanel.add(orderCompleetButton);
		

		
		add(orderCompleetPanel);
	}
	
	public void setKlantOptions(List<String> options) {
		klantOptions = options;
		klantComboBox.removeAllItems();
		for (String s : klantOptions) {
			klantComboBox.addItem(s.toString());
		}
	}
	
	
	public void addOrderCompleetListener(ActionListener listenForCompleetButton){  
		
		orderCompleetButton.addActionListener(listenForCompleetButton);
	}
	

}
