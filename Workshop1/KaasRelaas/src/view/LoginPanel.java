package view;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private View theView;
	
	public LoginPanel(View theView){
		this.theView = theView;
		Initialaize();
	}
		
	private void Initialaize(){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		
		JPanel titelPanel = new JPanel();
		FlowLayout fl_titelCardPanel = (FlowLayout) titelPanel.getLayout();
		fl_titelCardPanel.setVgap(40);
		
		JLabel titelLabel = new JLabel("Boer Piets Kaas Handel");
		titelLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		titelPanel.add(titelLabel);
		add(titelPanel);
		
		
		
		JPanel loginNaamPanel = new JPanel();
		FlowLayout fl_loginNaamPanel = (FlowLayout) loginNaamPanel.getLayout();
		fl_loginNaamPanel.setVgap(50);
		fl_loginNaamPanel.setAlignOnBaseline(true);
		
		JTextField loginNaamTextField = new JTextField();
		loginNaamTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		loginNaamPanel.add(loginNaamTextField);
		loginNaamTextField.setColumns(20);
		add(loginNaamPanel);
		
	
		
		JPanel passwordPanel = new JPanel();
		passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 50));
		JLabel passwordLabel = new JLabel("Password    ");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		passwordPanel.add(passwordLabel);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		passwordField.setColumns(20);
		passwordPanel.add(passwordField);
		add(passwordPanel);
		
		
	
		JPanel okButtonPanel = new JPanel();
		FlowLayout fl_okButtonPanel = (FlowLayout) okButtonPanel.getLayout();
		fl_okButtonPanel.setVgap(20);
		
		JButton okButton = new JButton("    OK    ");
		okButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		okButtonPanel.add(okButton);
		add(okButtonPanel);
	}
	
}

