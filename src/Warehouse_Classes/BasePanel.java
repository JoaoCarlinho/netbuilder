package Warehouse_Classes;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public abstract class BasePanel extends JPanel {
	protected JFrame mainFrame;
	
	public BasePanel(JFrame frame) {
		
		mainFrame = frame;
		prepareGui();
		prepareEvents();
		mainFrame.add(this);
		this.setVisible(true);
	}
	
	protected void closeWindow() {
		 this.setVisible(false);
	 }
	
	protected abstract void prepareGui();
	
	protected abstract void prepareEvents();
}
