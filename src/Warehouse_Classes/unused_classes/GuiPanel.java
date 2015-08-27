package Warehouse_Classes.unused_classes;

import javax.swing.JButton;

import Warehouse_Classes.BasePanel;


public class GuiPanel extends BasePanel {

	JButton button;
	
	public GuiPanel() {
		
		super();
		setVisible(true);
	}

	protected void prepareGui() {
		// Add all your components here
		button = new JButton("BUTTON");
		add(button);
	}
	
	protected void prepareEvents() {
		// Add all listeners and action commands here
	}
}
