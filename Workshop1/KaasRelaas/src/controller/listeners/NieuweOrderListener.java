package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.Controller;

public class NieuweOrderListener implements ActionListener {

	private Controller controller;

	public NieuweOrderListener(Controller controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {

		try {
			
			controller.getView().getMainpanel().getOrderPanel().refreshNewItemPnl(controller);

		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

}
