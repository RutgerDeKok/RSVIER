package main.panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public abstract class GenericPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	protected JPanel overzichtsPanel;
	protected JLabel overzTitelLabel;
	protected JPanel newItemPanel;
	protected CardLayout cardLayout;
	protected JButton updateButton;
	protected JButton nieuwButton;
//	protected JButton itemCompleetButton;
	protected JButton cancelButton = new JButton("Cancel");
	protected JButton itemCompleteButton = new JButton("Compleet");
	protected SimpleTablePanel tablePanel;
	protected String[][] data;
	protected String[] columnNames;
	protected int[] columnWidths; // total 839
	protected Color itemColor = new Color(190, 190, 190);

	public GenericPanel() {

		cardLayout = new CardLayout(0, 0);
		setLayout(cardLayout);
		createOverzPnl();

		add(overzichtsPanel, "overzichtsPanel");
		cardLayout.show(this, "overzichtsPanel");
	}
	

	private void createOverzPnl() {
		overzichtsPanel = new JPanel();
		overzichtsPanel.setLayout(new BorderLayout(10, 10));

		JPanel topPanel = new JPanel();

		FlowLayout fl_topPanel = new FlowLayout(FlowLayout.CENTER, 5, 10);
		fl_topPanel.setAlignOnBaseline(true);
		topPanel.setLayout(fl_topPanel);

		updateButton = new JButton("    Update    ");
		updateButton.setBackground(new Color(238, 232, 170));
		updateButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		topPanel.add(updateButton);
		updateButton.addActionListener(e->{
			updateAction();
		});

		overzTitelLabel = new JLabel("            Orders Overzicht  ");
		overzTitelLabel.setHorizontalAlignment(SwingConstants.LEFT);
		overzTitelLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		topPanel.add(overzTitelLabel);

		Component rigidArea = Box.createRigidArea(new Dimension(80, 5));
		topPanel.add(rigidArea);

		nieuwButton = new JButton(" Nieuwe Order  ");
		nieuwButton.setBackground(itemColor);
		nieuwButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		nieuwButton.addActionListener(e->{
			refreshNewItempPnl();
		});
		topPanel.add(nieuwButton);

		overzichtsPanel.add(topPanel, BorderLayout.NORTH);

		cancelButton.addActionListener(e -> {
			setCard("overzichtsPanel");
		});
	
		itemCompleteButton.addActionListener(e -> {
			itemCompleteAction();
		});

	}

	protected abstract void itemCompleteAction();
		
	protected abstract void createNewItemPanel() throws Exception;
	
	public abstract void updateAction();

	protected abstract void refreshNewItempPnl();
	
	public abstract void editRow(int row) ;
	
	
	

	public void setData(String[][] data) {

		this.data = data;
		tablePanel.updateTable(data);
		this.revalidate();
	}

	
	public void setCard(String cardString) {

		cardLayout.show(this, cardString);
	}
	

	public JPanel getNieuweItemPnl() {
		return newItemPanel;
	}

	

	public void addTableListener(){
		
		tablePanel.getTable().addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e) {
				
				JTable target = (JTable) e.getSource();
				int row = target.getSelectedRow();
				int column = target.getSelectedColumn();
				
				if (tablePanel.getTable().getColumnCount() == column + 1) {
					editRow(row);
				}
			}
		});
	}
	


}
