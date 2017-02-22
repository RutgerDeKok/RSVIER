package main.panels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import connection_pool.Password;
import gebruiker.Gebruiker;
import gebruiker.GebruikerType;
import main.KaasAppMain;
import main.MainController;


public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;


	private JButton okButton;
	private JTextField loginNaamTextField;
	private JPasswordField passwordField;

	public LoginPanel(MainController controller) {
	
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel titelPanel = new JPanel();
		titelPanel.setBackground(new Color(255, 204, 102));
		FlowLayout fl_titelCardPanel = (FlowLayout) titelPanel.getLayout();
		fl_titelCardPanel.setVgap(55);

		JLabel titelLabel = new JLabel("Boer Piets Kaas Handel");
		titelLabel.setFont(new Font("Tahoma", Font.PLAIN, 38));
		titelPanel.add(titelLabel);
		add(titelPanel);

		JPanel loginNaamPanel = new JPanel();
		loginNaamPanel.setBackground(new Color(238, 232, 170));
		loginNaamPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 45));
		JLabel nameLabel = new JLabel("Login Naam  ");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		loginNaamPanel.add(nameLabel);

		loginNaamTextField = new JTextField();
		loginNaamTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		loginNaamPanel.add(loginNaamTextField);
		loginNaamTextField.setColumns(20);
		add(loginNaamPanel);

		JPanel passwordPanel = new JPanel();
		passwordPanel.setBackground(new Color(238, 232, 170));
		passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 45));
		JLabel passwordLabel = new JLabel("Password    ");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		passwordPanel.add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		passwordField.setColumns(20);
		passwordPanel.add(passwordField);
		add(passwordPanel);

		JPanel okButtonPanel = new JPanel();
		okButtonPanel.setBackground(new Color(255, 204, 102));
		FlowLayout fl_okButtonPanel = (FlowLayout) okButtonPanel.getLayout();
		fl_okButtonPanel.setVgap(50);

		okButton = new JButton("    OK    ");
		okButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		okButtonPanel.add(okButton);
		add(okButtonPanel);
		addOkListener(controller);
	}

	
	private void addOkListener(MainController controller) {

		okButton.addActionListener(e -> {
			controller.startConnection();
			
			String login = loginNaamTextField.getText();
			Gebruiker medewerker = controller.getModel().getGebruikerDao().getByLogin(login);
			
			String stored;
			
			
			
			try {
				if (medewerker != null && medewerker.getGebruikerType() != GebruikerType.KLANT){
					 stored = medewerker.getPass().toString();
		
					if (Password.check(passwordField.getPassword(), stored)) {

						KaasAppMain.logger.info("logged in by : "+medewerker.getId()+" " +medewerker.getVoornaam()+" "+medewerker.getTussenVoegsel()+" " +medewerker.getAchternaam());
						controller.getView().getMainpanel().setMedewerker(medewerker);
						loginNaamTextField.setText("");
						passwordField.setText("");
					
						controller.getView().getMainpanel().getKlantenPanel().
						updateAction();
						controller.getView().getMainpanel().getProductenPanel().
						updateAction();
						controller.getView().getMainpanel().getMedewerkersPanel().
						updateAction();
						controller.getView().getMainpanel().getOrderPanel().
						updateAction();
						controller.getView().setCard("mainPanel");
					}else{
						controller.closeConnection();
					}
				}else{
					controller.closeConnection();
				}
			} catch (Exception e1) {
				KaasAppMain.logger.info("failed login, no stored pw, attempt by: "+loginNaamTextField.getText());
				// TODO Auto-generated catch block
				e1.printStackTrace();
				controller.closeConnection();
			}
		});
	}

}
