package main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import gebruiker.Gebruiker;
import gebruiker.GebruikerToegang;
import gebruiker.KlantenPanel;
import gebruiker.MedewerkersPanel;
import order.OrdersPanel;
import product.ProductenPanel;


public class MainPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private MainController controller;
	private OrdersPanel ordersPanel;
	private KlantenPanel klantenPanel;
	private ProductenPanel productenPanel;
	private MedewerkersPanel medewerkersPanel;
	private Gebruiker medewerker;
	private JTabbedPane tabbedPane;
	private JButton logoutButton;

	public MainPanel(MainController orderController) {
		this.controller = orderController;
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
		
	
		ordersPanel = new OrdersPanel(controller);
		tabbedPane.addTab(" Orders         ", null, ordersPanel, null);
		
		
		klantenPanel = new KlantenPanel(controller);
		tabbedPane.addTab(" Klanten        ", null, klantenPanel, null);

		
		productenPanel = new ProductenPanel(controller);
		tabbedPane.addTab(" Producten    ", null, productenPanel, null);
		
		
		medewerkersPanel = new MedewerkersPanel(controller);
		tabbedPane.addTab(" Medewerkers  ", null, medewerkersPanel, null);
		
		tabbedPane.addChangeListener(new ChangeListener() {

              public void stateChanged(ChangeEvent e) {
                  if (e.getSource() instanceof JTabbedPane) {
                      JTabbedPane pane = (JTabbedPane) e.getSource();
                      int panelIndex= pane.getSelectedIndex();
                      updatePanel(panelIndex);
                  }
              }
          });
		
	
		logoutButton = new JButton("Logout");
		springLayoutMain.putConstraint(SpringLayout.NORTH, logoutButton, 0, SpringLayout.NORTH, this);
		springLayoutMain.putConstraint(SpringLayout.SOUTH, logoutButton, 30, SpringLayout.NORTH, this);
		springLayoutMain.putConstraint(SpringLayout.EAST, logoutButton, -10, SpringLayout.EAST, this);
		springLayoutMain.putConstraint(SpringLayout.NORTH, tabbedPane, 0, SpringLayout.SOUTH, logoutButton);
		logoutButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(logoutButton);
		
		logoutButton.addActionListener(e -> {
			medewerker = null;
			tabbedPane.setSelectedIndex(0);
			controller.getView().setCard("loginPanel");
			controller.closeConnection();
						
		});
	}
		
	
	public OrdersPanel getOrderPanel(){
		return ordersPanel;
	}
	public ProductenPanel getProductenPanel(){
		return productenPanel;
	}
	
	public KlantenPanel getKlantenPanel(){
		return klantenPanel;
	}
	
	public MedewerkersPanel getMedewerkersPanel(){
		return medewerkersPanel;
	}
	
	public void setMedewerker(Gebruiker medewerker){
		this.medewerker = medewerker;
		if(medewerker.getGebruikerToegang()==GebruikerToegang.ADMIN){
			productenPanel.nieuwButton.setEnabled(true);
			tabbedPane.setEnabledAt(3, true);
		}else{
			productenPanel.nieuwButton.setEnabled(false);
			tabbedPane.setEnabledAt(3, false);
		}
	}
	
	public Gebruiker getMedewerker(){
		return medewerker;
	}
	
	private void updatePanel(int panelIndex) {

		switch (panelIndex){
		default:
			break;
		case 0:
			ordersPanel.updateAction();
			break;
		case 1:
			klantenPanel.updateAction();
			break;
		case 2:
			productenPanel.updateAction();
			break;
		case 3:
			medewerkersPanel.updateAction();
			break;
		}
			
		
	}

	
	

}



