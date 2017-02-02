package view.main;

import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.Controller;
import controller.Password;
import model.Gebruiker;
import model.GebruikerType;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;


	private JButton okButton;
	private JTextField loginNaamTextField;
	private JPasswordField passwordField;

	public LoginPanel() {
	
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
		JLabel nameLabel = new JLabel("Login Naam  ");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		loginNaamPanel.add(nameLabel);
		fl_loginNaamPanel.setVgap(50);
		fl_loginNaamPanel.setAlignOnBaseline(true);

		loginNaamTextField = new JTextField();
		loginNaamTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		loginNaamPanel.add(loginNaamTextField);
		loginNaamTextField.setColumns(20);
		add(loginNaamPanel);

		JPanel passwordPanel = new JPanel();
		passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 50));
		JLabel passwordLabel = new JLabel("Password    ");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		passwordPanel.add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		passwordField.setColumns(20);
		passwordPanel.add(passwordField);
		add(passwordPanel);

		JPanel okButtonPanel = new JPanel();
		FlowLayout fl_okButtonPanel = (FlowLayout) okButtonPanel.getLayout();
		fl_okButtonPanel.setVgap(20);

		okButton = new JButton("    OK    ");
		okButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		okButtonPanel.add(okButton);
		add(okButtonPanel);
	}

	
	public void addOkListener(Controller controller) {

		okButton.addActionListener(e -> {
			String login = loginNaamTextField.getText();
			Gebruiker medewerker = controller.getModel().getGebruikerDao().getByLogin(login);
			String stored;
			
			try {
				if (medewerker != null && medewerker.getGebruikerType() != GebruikerType.KLANT){
					 stored = medewerker.getGebruikerPass().toString();
		
					if (Password.check(passwordField.getPassword(), stored)) {
						System.out.println("password ok");
						controller.getView().getMainpanel().setMedewerker(medewerker);
						loginNaamTextField.setText("");
						passwordField.setText("");
						controller.getView().setCard("mainPanel");
						controller.getView().getMainpanel().getOrderPanel().
						useUpdateBtn();
					}
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}

}
