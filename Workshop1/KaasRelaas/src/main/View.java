package main;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JFrame;

import main.panels.LoginPanel;
import main.panels.MainPanel;



public class View extends JFrame{

	private static final long serialVersionUID = 1L;
	private MainPanel mainPanel;
	private CardLayout mainCardLayout;


	public void initialize(MainController controller) {

		mainCardLayout = new CardLayout(0, 0);
		setTitle("- Boer Piets Kaas Handel -");
		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(mainCardLayout);
	
		mainPanel = new MainPanel(controller);
		getContentPane().add(mainPanel, "mainPanel");
				
		LoginPanel loginPanel = new LoginPanel(controller);
		getContentPane().add(loginPanel, "loginPanel");
		
		mainCardLayout.show(this.getContentPane(), "loginPanel");
		
	}

	
	public MainPanel getMainpanel(){
		return mainPanel;
	}
	

	
	public void setCard(String cardName){
		mainCardLayout.show(this.getContentPane(), cardName);
	}
	


}


