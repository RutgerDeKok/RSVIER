package view.products;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;


public class ProductenPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;


	private OverzProductenPnl overzProductenPnl;
	private NieuwProductPanel nieuwProductPnl;
	private CardLayout productCardLayout;

	public ProductenPanel(){
		initialize();
	}
	
	
	private void initialize(){
		
		productCardLayout = new CardLayout(0, 0);
		setLayout(productCardLayout);

		overzProductenPnl = new OverzProductenPnl();
		
		add(overzProductenPnl, "overzProductenPnl");

		nieuwProductPnl = new NieuwProductPanel();
		add(nieuwProductPnl, "nieuwProductPnl");
	
		
//		productCardLayout.show(this, "overzProductenPnl");
		productCardLayout.show(this, "nieuwProductPnl");
	
	}
	
	public void refreshNewOrderPnl(){
		remove(nieuwProductPnl);
		nieuwProductPnl = new NieuwProductPanel();
		add(nieuwProductPnl, "NieuwProductPnl");
		nieuwProductPnl.addCancelProductListener(new CancelProductListener());
	}
	
	
	
	public void setOrderCard(String cardString){
		productCardLayout.show(this, cardString);
	}
	
	

	

	
	public class CancelProductListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			System.out.println("cancel button pressed");
			setOrderCard("overzProductenPnl");
			
		}
	}
	
	
	/////////////
}
