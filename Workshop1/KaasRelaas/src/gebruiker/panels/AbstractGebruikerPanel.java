package gebruiker.panels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import gebruiker.Gebruiker;
import gebruiker.GebruikerToegang;
import gebruiker.GebruikerType;
import main.panels.GenericPanel;
import main.KaasAppMain;
import main.Password;


public abstract class AbstractGebruikerPanel extends GenericPanel {

	private static final long serialVersionUID = 1L;

	protected int gebruikerId;
	protected List<Gebruiker> gebruikersList;

	protected JTextField voorNaamTextField;
	protected JTextField tussenvgsTextField;
	protected JTextField achternaamTextField;
	protected JTextField straatTextField;
	protected JTextField nummerTextField;
	protected JTextField toevoegTextField;
	protected JTextField postcodeTextField;
	protected JTextField telefoonTextField;
	protected JTextField plaatsTextField;
	protected JTextField inlogTextField;
	protected JPasswordField passwordField;
	protected JComboBox<GebruikerToegang> toegangCombobox;
	protected JComboBox<GebruikerType> typeComboBox;
	protected JLabel nieuweMedewLabel;
	protected JLabel toegangLabel;
	protected JLabel inlogLabel;
	protected JLabel typeLabel;
	protected JCheckBox passwCheck;

	@Override
	protected void createNewItemPanel() throws Exception {

		Font standaardFont = new Font("Tahoma", Font.PLAIN, 20);

		newItemPanel = new JPanel();
		newItemPanel.setLayout(new BoxLayout(newItemPanel, BoxLayout.Y_AXIS));

		// title panel
		JPanel titelPanel2 = new JPanel();
		titelPanel2.setBackground(itemColor);
		titelPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// nieuweMedewLabel = new JLabel();
		nieuweMedewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		nieuweMedewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		titelPanel2.add(nieuweMedewLabel);
		newItemPanel.add(titelPanel2);

		// nieuw Gebruiker Grid Panel

		JPanel nieuwGridPnl = new JPanel();
		newItemPanel.add(nieuwGridPnl);
		GridBagLayout gbl_nieuwGridPnl = new GridBagLayout();
		gbl_nieuwGridPnl.columnWidths = new int[] { 15, 50, 50, 40, 40, 110, 15 };
		gbl_nieuwGridPnl.rowHeights = new int[] { 50, 30, 55, 30, 30, 60, 60, 30, 60 }; // 50
																						// 30
																						// 55
																						// 30
																						// 30
																						// 60
																						// 60
																						// 30
																						// 60
		gbl_nieuwGridPnl.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0 };
		gbl_nieuwGridPnl.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0 };
		nieuwGridPnl.setLayout(gbl_nieuwGridPnl);

		JLabel voornaamLabel = new JLabel("Voornaam: ");
		voornaamLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		voornaamLabel.setFont(standaardFont);
		GridBagConstraints gbc_voornaamLabel = new GridBagConstraints();
		gbc_voornaamLabel.anchor = GridBagConstraints.EAST;
		gbc_voornaamLabel.insets = new Insets(0, 0, 5, 5);
		gbc_voornaamLabel.gridx = 1;
		gbc_voornaamLabel.gridy = 0;
		nieuwGridPnl.add(voornaamLabel, gbc_voornaamLabel);

		voorNaamTextField = new JTextField();
		voorNaamTextField.setFont(standaardFont);
		GridBagConstraints gbc_voorNaamTextField = new GridBagConstraints();
		gbc_voorNaamTextField.anchor = GridBagConstraints.SOUTH;
		gbc_voorNaamTextField.gridwidth = 2;
		gbc_voorNaamTextField.insets = new Insets(0, 0, 5, 5);
		gbc_voorNaamTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_voorNaamTextField.gridx = 2;
		gbc_voorNaamTextField.gridy = 0;
		nieuwGridPnl.add(voorNaamTextField, gbc_voorNaamTextField);
		voorNaamTextField.setColumns(16);

		JLabel tussenvgsLabel = new JLabel("Tussenvoegsel: ");
		tussenvgsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		tussenvgsLabel.setFont(standaardFont);
		GridBagConstraints gbc_tussenvgsLabel = new GridBagConstraints();
		gbc_tussenvgsLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_tussenvgsLabel.insets = new Insets(0, 0, 5, 5);
		gbc_tussenvgsLabel.gridx = 4;
		gbc_tussenvgsLabel.gridy = 0;
		nieuwGridPnl.add(tussenvgsLabel, gbc_tussenvgsLabel);

		tussenvgsTextField = new JTextField();
		tussenvgsTextField.setFont(standaardFont);
		tussenvgsTextField.setColumns(8);
		GridBagConstraints gbc_tussenvgsTextField = new GridBagConstraints();
		gbc_tussenvgsTextField.anchor = GridBagConstraints.SOUTH;
		gbc_tussenvgsTextField.insets = new Insets(0, 0, 5, 0);
		gbc_tussenvgsTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_tussenvgsTextField.gridx = 5;
		gbc_tussenvgsTextField.gridy = 0;
		nieuwGridPnl.add(tussenvgsTextField, gbc_tussenvgsTextField);

		JLabel achternaamLabel = new JLabel("Achternaam: ");
		achternaamLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		achternaamLabel.setFont(standaardFont);
		GridBagConstraints gbc_achternaamLabel = new GridBagConstraints();
		gbc_achternaamLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_achternaamLabel.insets = new Insets(0, 0, 5, 5);
		gbc_achternaamLabel.gridx = 1;
		gbc_achternaamLabel.gridy = 1;
		nieuwGridPnl.add(achternaamLabel, gbc_achternaamLabel);

		achternaamTextField = new JTextField();
		achternaamTextField.setFont(standaardFont);
		achternaamTextField.setColumns(10);
		GridBagConstraints gbc_achternaamTextField = new GridBagConstraints();
		gbc_achternaamTextField.anchor = GridBagConstraints.SOUTH;
		gbc_achternaamTextField.gridwidth = 4;
		gbc_achternaamTextField.insets = new Insets(0, 0, 5, 0);
		gbc_achternaamTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_achternaamTextField.gridx = 2;
		gbc_achternaamTextField.gridy = 1;
		nieuwGridPnl.add(achternaamTextField, gbc_achternaamTextField);

		JLabel straatLabel = new JLabel("Straat: ");
		straatLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		straatLabel.setFont(standaardFont);
		GridBagConstraints gbc_straatLabel = new GridBagConstraints();
		gbc_straatLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_straatLabel.insets = new Insets(0, 0, 5, 5);
		gbc_straatLabel.gridx = 1;
		gbc_straatLabel.gridy = 2;
		nieuwGridPnl.add(straatLabel, gbc_straatLabel);

		straatTextField = new JTextField();
		straatTextField.setFont(standaardFont);
		straatTextField.setColumns(10);
		GridBagConstraints gbc_straatTextField = new GridBagConstraints();
		gbc_straatTextField.anchor = GridBagConstraints.SOUTH;
		gbc_straatTextField.gridwidth = 4;
		gbc_straatTextField.insets = new Insets(0, 0, 5, 0);
		gbc_straatTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_straatTextField.gridx = 2;
		gbc_straatTextField.gridy = 2;
		nieuwGridPnl.add(straatTextField, gbc_straatTextField);

		JLabel nummerLabel = new JLabel("Nummer: ");
		nummerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nummerLabel.setFont(standaardFont);
		GridBagConstraints gbc_nummerLabel = new GridBagConstraints();
		gbc_nummerLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_nummerLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nummerLabel.gridx = 1;
		gbc_nummerLabel.gridy = 3;
		nieuwGridPnl.add(nummerLabel, gbc_nummerLabel);

		nummerTextField = new JTextField();
		nummerTextField.setFont(standaardFont);
		nummerTextField.setColumns(6);
		GridBagConstraints gbc_nummerTextField = new GridBagConstraints();
		gbc_nummerTextField.anchor = GridBagConstraints.SOUTH;
		gbc_nummerTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nummerTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nummerTextField.gridx = 2;
		gbc_nummerTextField.gridy = 3;
		nieuwGridPnl.add(nummerTextField, gbc_nummerTextField);

		JLabel toevoegLabel = new JLabel("Toevoeging: ");
		toevoegLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		toevoegLabel.setFont(standaardFont);
		GridBagConstraints gbc_toevoegLabel = new GridBagConstraints();
		gbc_toevoegLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_toevoegLabel.insets = new Insets(0, 0, 5, 5);
		gbc_toevoegLabel.gridx = 3;
		gbc_toevoegLabel.gridy = 3;
		nieuwGridPnl.add(toevoegLabel, gbc_toevoegLabel);

		toevoegTextField = new JTextField();
		toevoegTextField.setFont(standaardFont);
		toevoegTextField.setColumns(8);
		GridBagConstraints gbc_toevoegTextField = new GridBagConstraints();
		gbc_toevoegTextField.anchor = GridBagConstraints.SOUTH;
		gbc_toevoegTextField.insets = new Insets(0, 0, 5, 5);
		gbc_toevoegTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_toevoegTextField.gridx = 4;
		gbc_toevoegTextField.gridy = 3;
		nieuwGridPnl.add(toevoegTextField, gbc_toevoegTextField);

		JLabel postcodeLabel = new JLabel("Postcode: ");
		postcodeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		postcodeLabel.setFont(standaardFont);
		GridBagConstraints gbc_postcodeLabel = new GridBagConstraints();
		gbc_postcodeLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_postcodeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_postcodeLabel.gridx = 1;
		gbc_postcodeLabel.gridy = 4;
		nieuwGridPnl.add(postcodeLabel, gbc_postcodeLabel);

		postcodeTextField = new JTextField();
		postcodeTextField.setFont(standaardFont);
		postcodeTextField.setColumns(6);
		GridBagConstraints gbc_postcodeTextField = new GridBagConstraints();
		gbc_postcodeTextField.anchor = GridBagConstraints.SOUTH;
		gbc_postcodeTextField.insets = new Insets(0, 0, 5, 5);
		gbc_postcodeTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_postcodeTextField.gridx = 2;
		gbc_postcodeTextField.gridy = 4;
		nieuwGridPnl.add(postcodeTextField, gbc_postcodeTextField);

		JLabel plaatsLabel = new JLabel("Plaats: ");
		plaatsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		plaatsLabel.setFont(standaardFont);
		GridBagConstraints gbc_plaatsLabel = new GridBagConstraints();
		gbc_plaatsLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_plaatsLabel.insets = new Insets(0, 0, 5, 5);
		gbc_plaatsLabel.gridx = 3;
		gbc_plaatsLabel.gridy = 4;
		nieuwGridPnl.add(plaatsLabel, gbc_plaatsLabel);

		plaatsTextField = new JTextField();
		plaatsTextField.setFont(standaardFont);
		plaatsTextField.setColumns(8);
		GridBagConstraints gbc_plaatsTextField = new GridBagConstraints();
		gbc_plaatsTextField.anchor = GridBagConstraints.SOUTH;
		gbc_plaatsTextField.gridwidth = 2;
		gbc_plaatsTextField.insets = new Insets(0, 0, 5, 0);
		gbc_plaatsTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_plaatsTextField.gridx = 4;
		gbc_plaatsTextField.gridy = 4;
		nieuwGridPnl.add(plaatsTextField, gbc_plaatsTextField);

		JLabel telefoonLabel = new JLabel("Telefoon nr: ");
		telefoonLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		telefoonLabel.setFont(standaardFont);
		GridBagConstraints gbc_telefoonLabel = new GridBagConstraints();
		gbc_telefoonLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_telefoonLabel.insets = new Insets(0, 0, 5, 5);
		gbc_telefoonLabel.gridx = 1;
		gbc_telefoonLabel.gridy = 5;
		nieuwGridPnl.add(telefoonLabel, gbc_telefoonLabel);

		telefoonTextField = new JTextField();
		telefoonTextField.setFont(standaardFont);
		telefoonTextField.setColumns(11);
		GridBagConstraints gbc_telefoonTextField = new GridBagConstraints();
		gbc_telefoonTextField.anchor = GridBagConstraints.SOUTH;
		gbc_telefoonTextField.gridwidth = 2;
		gbc_telefoonTextField.insets = new Insets(0, 0, 5, 5);
		gbc_telefoonTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_telefoonTextField.gridx = 2;
		gbc_telefoonTextField.gridy = 5;
		nieuwGridPnl.add(telefoonTextField, gbc_telefoonTextField);

		typeLabel = new JLabel("Type:");
		typeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		typeLabel.setFont(standaardFont);
		GridBagConstraints gbc_typeLabel = new GridBagConstraints();
		gbc_typeLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_typeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_typeLabel.gridx = 1;
		gbc_typeLabel.gridy = 6;
		nieuwGridPnl.add(typeLabel, gbc_typeLabel);

		typeComboBox = new JComboBox<GebruikerType>();
		typeComboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		typeComboBox.setModel(new DefaultComboBoxModel<GebruikerType>(
				new GebruikerType[] {GebruikerType.MEDEWERKER, GebruikerType.BAAS}));
		GridBagConstraints gbc_typeComboBox = new GridBagConstraints();
		gbc_typeComboBox.anchor = GridBagConstraints.SOUTH;
		gbc_typeComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_typeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_typeComboBox.gridx = 2;
		gbc_typeComboBox.gridy = 6;
		nieuwGridPnl.add(typeComboBox, gbc_typeComboBox);

		inlogLabel = new JLabel("Inlog: ");
		inlogLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		inlogLabel.setFont(standaardFont);
		GridBagConstraints gbc_inlogLabel = new GridBagConstraints();
		gbc_inlogLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_inlogLabel.insets = new Insets(0, 0, 5, 5);
		gbc_inlogLabel.gridx = 3;
		gbc_inlogLabel.gridy = 6;
		nieuwGridPnl.add(inlogLabel, gbc_inlogLabel);

		inlogTextField = new JTextField();
		inlogTextField.setFont(standaardFont);
		inlogTextField.setColumns(8);
		GridBagConstraints gbc_inlogTextField = new GridBagConstraints();
		gbc_inlogTextField.gridwidth = 2;
		gbc_inlogTextField.anchor = GridBagConstraints.SOUTH;
		gbc_inlogTextField.insets = new Insets(0, 0, 5, 0);
		gbc_inlogTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_inlogTextField.gridx = 4;
		gbc_inlogTextField.gridy = 6;
		nieuwGridPnl.add(inlogTextField, gbc_inlogTextField);

		toegangLabel = new JLabel("Toegang: ");
		toegangLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		toegangLabel.setFont(standaardFont);
		GridBagConstraints gbc_toegangLabel = new GridBagConstraints();
		gbc_toegangLabel.insets = new Insets(0, 0, 5, 5);
		gbc_toegangLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_toegangLabel.gridx = 1;
		gbc_toegangLabel.gridy = 7;
		nieuwGridPnl.add(toegangLabel, gbc_toegangLabel);

		toegangCombobox = new JComboBox<GebruikerToegang>();
		toegangCombobox.setModel(new DefaultComboBoxModel<GebruikerToegang>(
				new GebruikerToegang[] { GebruikerToegang.NONE,GebruikerToegang.BASIC,GebruikerToegang.ADMIN }));
		toegangCombobox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_toegangCombobox = new GridBagConstraints();
		gbc_toegangCombobox.anchor = GridBagConstraints.SOUTH;
		gbc_toegangCombobox.insets = new Insets(0, 0, 5, 5);
		gbc_toegangCombobox.fill = GridBagConstraints.HORIZONTAL;
		gbc_toegangCombobox.gridx = 2;
		gbc_toegangCombobox.gridy = 7;
		nieuwGridPnl.add(toegangCombobox, gbc_toegangCombobox);
		
		passwordField = new JPasswordField();
		passwordField.setFont(standaardFont);
		GridBagConstraints gbc_passwordField1 = new GridBagConstraints();
		gbc_passwordField1.anchor = GridBagConstraints.SOUTH;
		gbc_passwordField1.gridwidth = 2;
		gbc_passwordField1.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField1.gridx = 4;
		gbc_passwordField1.gridy = 7;
		passwordField.setBackground(new Color(240,240,240));
		passwordField.setEnabled(false);
		nieuwGridPnl.add(passwordField, gbc_passwordField1);

//		passwLabel = new JLabel("Nieuw PW: ");
		passwCheck = new JCheckBox("Nieuw PW");
		passwCheck.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwCheck.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_passCheck = new GridBagConstraints();
		gbc_passCheck.anchor = GridBagConstraints.SOUTHEAST;
		gbc_passCheck.insets = new Insets(0, 0, 5, 5);
		gbc_passCheck.gridx = 3;
		gbc_passCheck.gridy = 7;
		nieuwGridPnl.add(passwCheck, gbc_passCheck);
		passwCheck.addActionListener(e->{
			passwordField.setEnabled(passwCheck.isSelected());
			if(passwordField.isEnabled()){
				passwordField.setBackground(new Color(255,255,255));
			}else{
				passwordField.setBackground(new Color(240,240,240));
			}
		});


	

		cancelButton.setFont(standaardFont);
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.gridwidth = 2;
		gbc_cancelButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_cancelButton.insets = new Insets(0, 0, 5, 5);
		gbc_cancelButton.gridx = 1;
		gbc_cancelButton.gridy = 8;
		nieuwGridPnl.add(cancelButton, gbc_cancelButton);

		itemCompleteButton.setText("Voeg Toe");
		itemCompleteButton.setFont(standaardFont);
		GridBagConstraints gbc_compleetButton = new GridBagConstraints();
		gbc_compleetButton.insets = new Insets(0, 0, 5, 0);
		gbc_compleetButton.gridwidth = 2;
		gbc_compleetButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_compleetButton.gridx = 4;
		gbc_compleetButton.gridy = 8;
		nieuwGridPnl.add(itemCompleteButton, gbc_compleetButton);

		initialize();
	}

	protected void initialize() {
	}

	@Override
	protected void refreshNewItempPnl() {
		try {

			if (newItemPanel != null)
				remove(newItemPanel);
			
			
			
			createNewItemPanel();
			this.add(newItemPanel, "NewItemPanel");

			gebruikerId=0;
			cardLayout.show(this, "NewItemPanel");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void editRow(int row) {
		KaasAppMain.logger.debug("edditing row = "+ row);
		// get gebruiker
		KaasAppMain.logger.debug("length list = "+gebruikersList.size());
		Gebruiker gebruiker = gebruikersList.get(row);
		

		refreshNewItempPnl();

		gebruikerId = gebruiker.getId();
		KaasAppMain.logger.debug("editmethod from AbstractGebrPnl- gebruiker ID = "+gebruikerId);

		voorNaamTextField.setText(gebruiker.getVoornaam());
		tussenvgsTextField.setText(gebruiker.getTussenVoegsel());
		achternaamTextField.setText(gebruiker.getAchternaam());
		straatTextField.setText(gebruiker.getStraat());
		nummerTextField.setText(Integer.toString(gebruiker.getHuisNummer()));
		toevoegTextField.setText(gebruiker.getHuisnrToevoeging());
		postcodeTextField.setText(gebruiker.getPostcode());
		telefoonTextField.setText(gebruiker.getTelefoon());
		plaatsTextField.setText(gebruiker.getWoonplaats());
		inlogTextField.setText(gebruiker.getLogin());
		typeComboBox.setSelectedItem(gebruiker.getGebruikerType());
		toegangCombobox.setSelectedItem(gebruiker.getGebruikerToegang());
		// passwordField;
	}

	public int getGebruikerId() {
		return gebruikerId;
	}

	public GebruikerType getGebruikerType;

	public String getLogin(){
		return inlogTextField.getText();
		
	}

	public String getVoornaam() {
		return voorNaamTextField.getText();
	}

	public String getAchternaam() {
		return achternaamTextField.getText();
	}

	public String getTussenvgs() {
		return tussenvgsTextField.getText();
	}

	public String getStraat() {
		return straatTextField.getText();
	}

	public Integer getHuisNummer() {
		try {
			return Integer.parseInt(nummerTextField.getText());
		} catch (Exception e) {
			return null;
		}

	}

	public String getToevoeg() {
		return toevoegTextField.getText();
	}

	public String getPostcode() {
		return postcodeTextField.getText();
	}

	public String getPlaats() {
		return plaatsTextField.getText();
	}

	public String getTelefoon() {
		return telefoonTextField.getText();
	}
	
	public String getPassword(){
		if(passwCheck.isSelected()){
			char[] pw = passwordField.getPassword();
			if (pw.length<4){
				return null;
			}
			try {
				return Password.getSaltedHash(pw);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	


	// @Override
	// protected void fetchDataFromDao() throws Exception {
	//
	// }
	//
	// @Override
	// public void updateAction() {
	//
	// }
	//
	//
	//

}
