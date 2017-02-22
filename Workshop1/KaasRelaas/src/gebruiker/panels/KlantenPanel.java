package gebruiker.panels;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;

import gebruiker.GebruikerCompleetAction;
import gebruiker.GebruikerType;
import main.MainController;
import main.panels.SimpleTablePanel;


public class KlantenPanel extends AbstractGebruikerPanel {

	private static final long serialVersionUID = 1L;

	MainController controller;

	public KlantenPanel(MainController controller) {
		this.controller = controller;
		String[][] newData = { { "-", "-", "-", "-", "-Edit-" } };
		String[] newNames = { "ID", "Naam", "Postcode/ Huisnr", "Plaats", "" };
		int[] newWidths = { 65, 330, 170, 200, 74 }; // total 839

		data = newData;
		columnNames = newNames;
		columnWidths = newWidths;

		tablePanel = new SimpleTablePanel(data, columnNames, columnWidths);
		overzichtsPanel.add(tablePanel, BorderLayout.CENTER);
		tablePanel.setVisible(true);
		itemColor = (new Color(242, 123, 123)); // salmon
		nieuwButton.setBackground(itemColor);
		overzTitelLabel.setText("            Klanten Overzicht  ");
		nieuwButton.setText("Nieuwe Klant");
		nieuweMedewLabel = new JLabel("Nieuwe Klant");
		

	}

	@Override
	protected void initialize() {
		inlogTextField.setVisible(false);
		passwordField.setVisible(false);
		toegangCombobox.setVisible(false);
		typeComboBox.setVisible(false);
		toegangLabel.setVisible(false);
		inlogLabel.setVisible(false);
		typeLabel.setVisible(false);
		passwCheck.setVisible(false);

	}


	@Override
	public void updateAction() {
		try {
			// data ophalen uit DB via DAO
			gebruikersList = controller.getModel().getGebruikerDao()
					.getAllByType(GebruikerType.KLANT, true);

			// List converteren naar String[][] voor tabel in view
			String[][] data;

			data = controller.getGebruikerController().convertGebruikersToTableData(gebruikersList);

			// update view with data
			setData(data);
			addTableListener();
			setCard("overzichtsPanel");

		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
	
	public GebruikerType getGebruikerTypr(){
		return  GebruikerType.KLANT;
	}



	@Override
	protected void itemCompleteAction() {
		// true = gebruiker is a KLANT
		GebruikerCompleetAction.performAction(controller,true);

	}

}
