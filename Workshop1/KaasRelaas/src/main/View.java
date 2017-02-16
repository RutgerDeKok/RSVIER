package main;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JFrame;



public class View extends JFrame{

	private static final long serialVersionUID = 1L;
	private MainPanel mainPanel;
	private LoginPanel loginPanel;
	private CardLayout mainCardLayout;

//	public View() {
//		initialize();
//	}

//	private void initialize() {
	public void initialize(MainController controller) {

		mainCardLayout = new CardLayout(0, 0);
		setTitle("- Boer Piets Kaas Handel -");
		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(mainCardLayout);
		

		

		mainPanel = new MainPanel(controller);
//		mainPanel.addLogoutListener(this);
		getContentPane().add(mainPanel, "mainPanel");
		
//		mainCardLayout.show(this.getContentPane(), "mainPanel");

//	}
//	
//	public void startLoginPanel(){
		
		loginPanel = new LoginPanel(controller);
		getContentPane().add(loginPanel, "loginPanel");
		mainCardLayout.show(this.getContentPane(), "loginPanel");
		
	}

	
	public MainPanel getMainpanel(){
		return mainPanel;
	}
	
	public LoginPanel getLoginPanel(){
		return loginPanel;
		
	}
	
	public void setCard(String cardName){
		mainCardLayout.show(this.getContentPane(), cardName);
	}
	
	public CardLayout getCardLayOut(){
		
	return mainCardLayout;
	}

}


