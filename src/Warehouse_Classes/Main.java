package Warehouse_Classes;

import java.sql.SQLException;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
	private static Stack<JPanel> stack = new Stack<JPanel>();

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
	

/**********************************************************************Entry Point for the Warehouse Management App*****************************************************/		
		JFrame mainFrame = new JFrame("WINDOW"); 
		GUIStack.setJFrame(mainFrame);
		Login warehouseLoginPanel = new Login(mainFrame);
		mainFrame.setSize(800, 800);
		mainFrame.setVisible(true);
		GUIStack.openWindow(warehouseLoginPanel);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/************************************************************Testing Functionality of methods**********************************************************/
		//TravelingWarehouseOperator test = new TravelingWarehouseOperator(1);
	}	
		
}
