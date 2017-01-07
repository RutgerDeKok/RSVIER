package view;

import java.awt.CardLayout;
import javax.swing.JPanel;

public class OrdersPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	

	private OverzOrdersPnl overzOrdersPnl;
	private NieuweOrderPanel nieuweOrderPnl;
	private CardLayout orderCardLayout;

	public OrdersPanel(){

		initialize();
	}
	
	private void initialize(){
		
		orderCardLayout = new CardLayout(0, 0);
		setLayout(orderCardLayout);

		overzOrdersPnl = new OverzOrdersPnl();
		
		add(overzOrdersPnl, "overzOrdersPnl");

		nieuweOrderPnl = new NieuweOrderPanel();
		add(nieuweOrderPnl, "NieuweOrderPnl");
		
		orderCardLayout.show(this, "overzOrdersPnl");
	
	}
	
	public void setOrderCard(String cardString){
		orderCardLayout.show(this, cardString);
	}
	
	public OverzOrdersPnl getOverzOrdersPnl(){
		return overzOrdersPnl;
	}
	
	public NieuweOrderPanel getNieuweOrderPnl(){
		return nieuweOrderPnl;
	}
}
