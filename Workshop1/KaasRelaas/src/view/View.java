package view;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JFrame;


public class View extends JFrame{

	private static final long serialVersionUID = 1L;
	private MainPanel mainPanel;

	public View() {
		initialize();
	}

	private void initialize() {

		CardLayout mainCardLayout = new CardLayout(0, 0);
		setTitle("- Boer Piets Kaas Handel -");
		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(mainCardLayout);
		

		LoginPanel loginPanel = new LoginPanel(this);
		getContentPane().add(loginPanel, "loginPanel");

		mainPanel = new MainPanel();
		getContentPane().add(mainPanel, "mainPanel");
		
		mainCardLayout.show(this.getContentPane(), "mainPanel");

	}



	
	public MainPanel getMainpanel(){
		return mainPanel;
	}

}


