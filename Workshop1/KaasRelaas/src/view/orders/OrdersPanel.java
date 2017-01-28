package view.orders;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import controller.Controller;

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
	
		orderCardLayout.show(this, "overzOrdersPnl");
	
	}
	
	public void refreshNewOrderPnl(Controller controller){
		try {
			
			if (nieuweOrderPnl != null)
				remove(nieuweOrderPnl);
		
			nieuweOrderPnl = new NieuweOrderPanel(controller);
			add(nieuweOrderPnl, "NieuweOrderPnl");
			orderCardLayout.show(this, "NieuweOrderPnl");
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		nieuweOrderPnl.addCancelOrderListener(new CancelOrderListener());
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
	

	
	public class CancelOrderListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			System.out.println("cancel button pressed");
			setOrderCard("overzOrdersPnl");
			
		}
	}
	
	
}
