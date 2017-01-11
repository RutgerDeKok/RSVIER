package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;


public class MainPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private OrdersPanel ordersPanel;
	private KlantenPanel klantenPanel;
	

	public MainPanel() {

		initialize();
	}

	private void initialize() {
		setBackground(new Color(222, 184, 135));
		SpringLayout springLayoutMain = new SpringLayout();
		setLayout(springLayoutMain);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		springLayoutMain.putConstraint(SpringLayout.WEST, tabbedPane, 10, SpringLayout.WEST, this);
		springLayoutMain.putConstraint(SpringLayout.SOUTH, tabbedPane, -10, SpringLayout.SOUTH, this);
		springLayoutMain.putConstraint(SpringLayout.EAST, tabbedPane, -10, SpringLayout.EAST, this);
		tabbedPane.setFont( new Font( "Tahoma", Font.PLAIN, 24 ) );
		add(tabbedPane);
		
	
		ordersPanel = new OrdersPanel();
		tabbedPane.addTab(" Orders         ", null, ordersPanel, null);
		
		
		klantenPanel = new KlantenPanel();
		tabbedPane.addTab(" Klanten        ", null, klantenPanel, null);

		
		ProductenPanel productenPanel = new ProductenPanel();
		tabbedPane.addTab(" Producten    ", null, productenPanel, null);
		
		
		MedewerkersPanel medewerkersPanel = new MedewerkersPanel();
		tabbedPane.addTab(" Medewerkers  ", null, medewerkersPanel, null);
		
	
		JButton logoutButton = new JButton("Logout");
		springLayoutMain.putConstraint(SpringLayout.NORTH, logoutButton, 0, SpringLayout.NORTH, this);
		springLayoutMain.putConstraint(SpringLayout.SOUTH, logoutButton, 30, SpringLayout.NORTH, this);
		springLayoutMain.putConstraint(SpringLayout.EAST, logoutButton, -10, SpringLayout.EAST, this);
		springLayoutMain.putConstraint(SpringLayout.NORTH, tabbedPane, 0, SpringLayout.SOUTH, logoutButton);
		logoutButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(logoutButton);
		
	}
		
	
	public OrdersPanel getOrderPanel(){
		return ordersPanel;
	}
	
	public KlantenPanel getKlantenPanel(){
		return klantenPanel;
	}
	

}



