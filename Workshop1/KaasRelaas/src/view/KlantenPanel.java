package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class KlantenPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public KlantenPanel(){
		Initialize();
	}

	private void Initialize() {
	
		setBackground(new Color(173, 216, 230));
		setLayout(new BorderLayout(0, 0));
		
	}

}
