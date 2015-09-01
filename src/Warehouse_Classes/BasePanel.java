package Warehouse_Classes;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

	/************************************************Class to keep panel on one frame through inheritance*****************************/

public abstract class BasePanel extends JPanel {
	protected JFrame mainFrame;
	
	public BasePanel(JFrame frame) {
		
		mainFrame = frame;
		prepareGui();
		prepareEvents();
	}

	
  	/***********************************Method for hiding old panels when adding new panels to the frame*****************************/

	protected void closeWindow() {
		 this.setVisible(false);
	 }
	
  	/**************************************Methods for creagin and adding events to GUI objects****************************/

	protected abstract void prepareGui();
	
	protected abstract void prepareEvents();
}