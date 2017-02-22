package gebruiker.panels;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;

import gebruiker.GebruikerCompleetAction;
import gebruiker.GebruikerToegang;
import gebruiker.GebruikerType;
import main.MainController;
import main.panels.SimpleTablePanel;

public class MedewerkersPanel extends AbstractGebruikerPanel {

	private static final long serialVersionUID = 1L;

	MainController controller;

	public MedewerkersPanel(MainController controller) {
		this.controller = controller;
		String[][] newData = { { "-", "-", "-", "-", "-Edit-" } };
		String[] newNames = { "ID", "Naam", "Huisnr/PC","Toegang", "" };
		int[] newWidths = { 65, 380, 200,120, 74 }; // total 839

		data = newData;
		columnNames = newNames;
		columnWidths = newWidths;

		tablePanel = new SimpleTablePanel(data, columnNames, columnWidths);
		overzichtsPanel.add(tablePanel, BorderLayout.CENTER);
		tablePanel.setVisible(true);
		itemColor = (new Color(244, 164, 96)); // faded orange
		nieuwButton.setBackground(itemColor);
		overzTitelLabel.setText("         Medewerkers Overzicht  ");
		nieuwButton.setText("Nieuwe Medewerker");
		nieuweMedewLabel = new JLabel("Nieuwe Medewerker");

	}
	
	@Override
	protected  void initialize(){
	}



	@Override
	public void updateAction() {
		try {
			// data ophalen uit DB via DAO    alle niet klanten  (baas en medewerkers)
			gebruikersList = controller.getModel().getGebruikerDao()
					.getAllByType(GebruikerType.KLANT, false);  

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

//	@Override
//	public void editRow(int row) {
//		System.out.println("editRow method start");
//	}

	@Override
	protected void itemCompleteAction() {
			GebruikerCompleetAction.performAction(controller,false);
		
		
	}
	
	public GebruikerType getGebruikerType(){
		return GebruikerType.valueOf(typeComboBox.getSelectedItem().toString());
	}
	
//	public String getLogin() {
//		return inlogTextField.getText();
//	}
	
	public GebruikerToegang getGebruikerToegang(){
		return GebruikerToegang.valueOf(toegangCombobox.getSelectedItem().toString());
	}

}
