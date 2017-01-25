package view.orders;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.main.SimpleTablePanel;

public class OverzOrdersPnl extends JPanel {

	private static final long serialVersionUID = 1L;
	

	private JButton nieuwButton;
	private JButton updateButton;
	private SimpleTablePanel tablePanel;
	
	private String[][] data = {{"-","-","-","-","-","-","-Edit-"}};
	private String[] columnNames = {"ID","Datum","Mw Id", "Klant","Aantal","Totaal","."};
	private int[] columnWidths = {40,110, 65, 420,65,80,74};
	

public OverzOrdersPnl(){
	
	initialize();
}


	private void initialize(){
		setLayout(new BorderLayout(10, 10));
		
		
		JPanel topPanel = new JPanel();
		
		FlowLayout fl_topPanel = new FlowLayout(FlowLayout.CENTER, 5, 10);
		fl_topPanel.setAlignOnBaseline(true);
		topPanel.setLayout(fl_topPanel);
		
		updateButton = new JButton("    Update    ");
		updateButton .setBackground(new Color(238,232,170));
		updateButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		topPanel.add(updateButton);
		
	
		JLabel lblNewLabel = new JLabel("            Orders Overzicht  ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		topPanel.add(lblNewLabel);
		
		Component rigidArea = Box.createRigidArea(new Dimension(80, 5));
		topPanel.add(rigidArea);
		
		nieuwButton = new JButton(" Nieuwe Order  ");
		nieuwButton .setBackground(new Color(153,204,102));
		nieuwButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		topPanel.add(nieuwButton);
		
		add(topPanel, BorderLayout.NORTH);
		
		
		tablePanel = new SimpleTablePanel(data, columnNames, columnWidths);
		add(tablePanel, BorderLayout.CENTER);
		tablePanel.setVisible(true);		
		
	} // end constructor
	
	


	public void addNieuweOrderListener(ActionListener listenForNieuwButton){  
		
		nieuwButton.addActionListener(listenForNieuwButton);
	}
	
	
	public void addUpdateOrderListener(ActionListener listenForUpdateButton){  
		
		updateButton.addActionListener(listenForUpdateButton);
	}
	
	
	
	public void setData(String[][] data){
		
		this.data = data;
		tablePanel.updateTable(data);
		this.revalidate();

	}
	
}
