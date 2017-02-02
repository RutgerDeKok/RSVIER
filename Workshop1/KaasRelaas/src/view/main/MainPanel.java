package view.main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;

import model.Gebruiker;
import view.gebruikers.KlantenPanel;
import view.gebruikers.MedewerkersPanel;
import view.orders.OrdersPanel;
import view.products.ProductenPanel;


public class MainPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private OrdersPanel ordersPanel;
	private KlantenPanel klantenPanel;
	private Gebruiker medewerker;
	private JTabbedPane tabbedPane;
	private JButton logoutButton;
	

	public MainPanel() {
			
		initialize();
	}

	private void initialize() {
		setBackground(new Color(222, 184, 135));
		SpringLayout springLayoutMain = new SpringLayout();
		setLayout(springLayoutMain);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
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
		
	
		logoutButton = new JButton("Logout");
		springLayoutMain.putConstraint(SpringLayout.NORTH, logoutButton, 0, SpringLayout.NORTH, this);
		springLayoutMain.putConstraint(SpringLayout.SOUTH, logoutButton, 30, SpringLayout.NORTH, this);
		springLayoutMain.putConstraint(SpringLayout.EAST, logoutButton, -10, SpringLayout.EAST, this);
		springLayoutMain.putConstraint(SpringLayout.NORTH, tabbedPane, 0, SpringLayout.SOUTH, logoutButton);
		logoutButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(logoutButton);
		
			
		
	}
	
	public void addLogoutListener(View theView){
		logoutButton.addActionListener(e -> {
			medewerker = null;
			tabbedPane.setSelectedIndex(0);
			theView.setCard("loginPanel");
						
		});
	}
		
	
	public OrdersPanel getOrderPanel(){
		return ordersPanel;
	}
	
	public KlantenPanel getKlantenPanel(){
		return klantenPanel;
	}
	
	public void setMedewerker(Gebruiker medewerker){
		this.medewerker = medewerker;
	}
	
	public Gebruiker getMedewerker(){
		return medewerker;
	}
	

}



