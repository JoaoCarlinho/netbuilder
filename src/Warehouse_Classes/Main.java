package Warehouse_Classes;

import javax.swing.JFrame;

import Warehouse_Classes.unused_classes.GuiPanel;

public class Main {

	public static void main(String[] args) {

		

		//----------------Functioning methods without GUI*********************************************************
		
		//OrderLineManager operatorTest = new OrderLineManager();
		
		//InventoryManager productManipulator = new InventoryManager();
		JFrame mainFrame = new JFrame("WINDOW");
		Login warehouseLoginPanel = new Login(mainFrame);
		mainFrame.setSize(800, 800);
		mainFrame.setVisible(true);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//---------------Non-Functioning methods 	
		//AccountCreationGUI warehouseAccountCreation = new AccountCreationGUI();

		//InventoryGUI inventoryWindow = new InventoryGUI();
	}
}
