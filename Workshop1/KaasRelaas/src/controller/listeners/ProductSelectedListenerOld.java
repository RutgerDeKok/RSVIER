package controller.listeners;


import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import controller.Controller;

//import view.SubOrderPanel;



public class ProductSelectedListenerOld implements ItemListener {
	
	Controller controller;
	
	public ProductSelectedListenerOld(Controller controller){
		
		this.controller = controller;
	}
	
	
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		System.out.println("itemstatechanged event");
		
//		System.out.println(((indexedComboBox)e.getSource()).getComboId());
//		controller.getView().getMainpanel().getOrderPanel().getNieuweOrderPnl().
//		
//		int panelIndex = ((SubOrderPanel) e.getSource()).getPanelIndex();
//		System.out.println(" panelIndex = "+ panelIndex));
	

		
	}
		
} 

