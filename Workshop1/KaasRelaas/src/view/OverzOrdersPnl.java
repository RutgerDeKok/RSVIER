package view;

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

public class OverzOrdersPnl extends JPanel {

	private static final long serialVersionUID = 1L;
	

	private JButton nieuwButton;
	private JButton updateButton;
	private SimpleTablePanel tablePanel;
	
	private String[][] data = {{"-","-","-","-","-","-","-Edit-"}};
	private String[] columnNames = {"ID","Datum","Mw Id", "Klant","Aantal","Totaal","."};
	private int[] columnWidths = {40,110, 65, 423,65,80,80};
	

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
		
//		createEditTable();
		
		
	} // end constructor
	
	
//	// table of one column on side that has values "edit" (will be clickable in future)
//	private void createEditTable() {
//		String[] editHdr = {"."};
//		String[][]editData = new String[data.length][1];
//		int[] editWidth = {55};
//		
//		for(int i = 0; i<data.length;i++){
//			editData[i][0] = " Edit";
//		}
//	
//		SimpleTablePanel tableEditPanel = new SimpleTablePanel(editData,editHdr, editWidth);
//		tableEditPanel.setPaneWidth(60);
//		add(tableEditPanel, BorderLayout.EAST);
//		tableEditPanel.setVisible(true);
//		
//	}


	public void addNieuweOrderListener(ActionListener listenForNieuwButton){  
		
		nieuwButton.addActionListener(listenForNieuwButton);
	}
	
	
	public void addUpdateOrderListener(ActionListener listenForUpdateButton){  
		
		updateButton.addActionListener(listenForUpdateButton);
	}
	
	
	
	public void setData(String[][] data){
		
		this.data = data;
		tablePanel.updateTable(data);
//		createEditTable();
		this.revalidate();

	}
	
}
