package Warehouse_Classes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Warehouse_Classes.Product;
import Warehouse_Classes.InventoryDAO;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class ReserveInventoryApp extends BasePanel {

	/*************************************************************Instantiate uneditable textfields*****************************************************************************************************/	
	private JTextField productIDTextField;
	private JTextField productNameTextField;
	private JTextField shelvedQuantityTextField;
	private JTextField reservedQuantityTextField;
	private JTextField porousAvailableTextField;
	private JTextField nonPorousAvailableTextField;
	/*************************************************************Instantiate buttons to reserve items****************************************************************************************************/		
	private JTextField porousReserveTextField;
	private JTextField nonPorousReserveTextField;

	/*************************************************************Instantiate labels uneditable textfields*****************************************************************************************************/	
	private JLabel lblProductID;
	private JLabel lblProductName;
	private JLabel lblshelvedQuantity;
	private JLabel lblreservedQuantity;
	private JLabel lblPorousAvailable;
	private JLabel lblPorousAvailablelblPorousAvailable;
	private JLabel lblNonPorousAvailable;
	
	/*************************************************************Instantiate buttons to reserve items****************************************************************************************************/			
	private JLabel lblPorousReserve;
	private JLabel lblNonPorousReserve;
	
	private JPanel buttonPane;
	private JButton reserveButton;
	private JButton pickButton;
	private JButton cancelButton;

	private InventoryDAO inventoryDAO;
	private ProductSearchApp productSearchApp;
	
	private Product previousProduct = null;

	public ReserveInventoryApp(ProductSearchApp theProductSearchApp, InventoryDAO theInventoryDAO, Product thePreviousProduct, JFrame frame) {
		super(frame);
		setVisible(true);
		
		inventoryDAO = theInventoryDAO;
		productSearchApp = theProductSearchApp;
		previousProduct = thePreviousProduct;
		populateGui(thePreviousProduct);
		
	}
	
	
	
/***********************************************************Create GUI Objects****************************************************************************************************/
		@Override
		protected void prepareGui() {
				setLayout(new BorderLayout());
				setLayout(new GridLayout(12,2));
				setBorder(new EmptyBorder(5, 5, 5, 5));
			
					lblProductID = new JLabel("Product ID");
				productIDTextField = new JTextField();
				productIDTextField.setEditable(false);
					lblProductName = new JLabel("Product Name");
				productNameTextField = new JTextField();
				productNameTextField.setEditable(false);
					lblshelvedQuantity = new JLabel("Shelved Quantity");
				shelvedQuantityTextField = new JTextField();
				shelvedQuantityTextField.setEditable(false);
					lblreservedQuantity = new JLabel("Reserved Quantity");
				reservedQuantityTextField = new JTextField();
				reservedQuantityTextField.setEditable(false);
					lblPorousAvailable = new JLabel("Porous Available");
				porousAvailableTextField = new JTextField();
				porousAvailableTextField.setEditable(false);
					lblNonPorousAvailable = new JLabel("Non Porous Available");
				nonPorousAvailableTextField = new JTextField();
				nonPorousAvailableTextField.setEditable(false);
				
					lblPorousReserve = new JLabel("Enter porous stock being reserved?");
				porousReserveTextField = new JTextField();
					lblNonPorousReserve = new JLabel("Enter Non-porous stock being reserved?");
				nonPorousReserveTextField = new JTextField();
				
				buttonPane = new JPanel();
				reserveButton = new JButton("Reserve Stock");
				cancelButton = new JButton("Cancel");
				pickButton = new JButton("Remove Stock");
				
				
				add(lblProductID, "2, 2, right, default");
				add(productIDTextField, "4, 2, fill, default");
					productIDTextField.setColumns(10);
				add(lblProductName, "2, 4, right, default");
				add(productNameTextField, "4, 4, fill, default");
					productNameTextField.setColumns(10);
				add(lblshelvedQuantity, "2, 6, right, default");
				add(shelvedQuantityTextField, "4, 6, fill, default");
					shelvedQuantityTextField.setColumns(10);
				add(lblreservedQuantity, "2, 8, right, default");
				add(reservedQuantityTextField, "4, 8, fill, default");
					reservedQuantityTextField.setColumns(10);
				add(lblPorousAvailable, "2, 10, right, default");
				add(porousAvailableTextField, "4, 10, fill, default");
					porousAvailableTextField.setColumns(10);
				add(lblNonPorousAvailable, "2, 12, right, default");
				add(nonPorousAvailableTextField, "4, 12, fill, default");
					nonPorousAvailableTextField.setColumns(10);

				add(lblPorousReserve, "2, 14, right, default");
				add(porousReserveTextField, "4, 14, fill, default");
					porousReserveTextField.setColumns(10);
				add(lblNonPorousReserve, "2, 16, right, default");
				add(nonPorousReserveTextField, "4, 16, fill, default");
					nonPorousReserveTextField.setColumns(10);
					
					
					
					
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				add(buttonPane, BorderLayout.SOUTH);
					reserveButton.setActionCommand("Reserve Products");
				buttonPane.add(reserveButton);
				
				pickButton.setActionCommand("Pick Products");
				buttonPane.add(pickButton);
				
					cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);	
		}
		/*************************************************************Attach events to GUI objects*****************************************************************************************************/
		@Override
		protected void prepareEvents() {
			reserveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					reserveProduct();
					GUIStack.goBack();
				}
			});
				
			
			pickButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pickProduct();
					GUIStack.goBack();
				}
			});
		
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIStack.goBack();
				}
			});
		}
	
		/*************************************************************Fill Objects with default text*****************************************************************************************************/	
	
	
	private void populateGui(Product theProduct) {
		productIDTextField.setText(String.valueOf(theProduct.getProductID()));
		productNameTextField.setText(theProduct.getProductName());
		shelvedQuantityTextField.setText(String.valueOf(theProduct.getShelvedQuantity()));
		reservedQuantityTextField.setText(String.valueOf(theProduct.getReservedQuantity()));
		porousAvailableTextField.setText(String.valueOf(theProduct.getPorousAvailable()));
		nonPorousAvailableTextField.setText(String.valueOf(theProduct.getNonPorousAvailable()));
	}
	
	
	/*************************************************************Call method to reserve objects*****************************************************************************************************/	
	protected void reserveProduct() {

		// get the product info from gui as text
		String productIDText = productIDTextField.getText(); 
		String productName = productNameTextField.getText();
		String shelvedQuantityText = shelvedQuantityTextField.getText();
		String reservedQuantityText = shelvedQuantityTextField.getText();
		String porousAvailableText = porousAvailableTextField.getText();
		String nonPorousAvailableText = nonPorousAvailableTextField.getText();
		
		String porousReservedText = porousReserveTextField.getText();
		String nonPorousReservedText = nonPorousReserveTextField.getText();
		
		//Convert read Strings into ints
		int productID = Integer.parseInt(productIDText);
		int shelvedQuantity = Integer.parseInt(shelvedQuantityText);
		int reservedQuantity = Integer.parseInt(reservedQuantityText);
		int porousAvailable = Integer.parseInt(porousAvailableText);
		int nonPorousAvailable = Integer.parseInt(nonPorousAvailableText);
		
		int porousAmount = Integer.parseInt(porousReservedText);
		int nonPorousAmount = Integer.parseInt(nonPorousReservedText);
		

		Product tempProduct = new Product(productID, productName, shelvedQuantity, reservedQuantity, porousAvailable, nonPorousAvailable);
		
		try {
			
				inventoryDAO.reserveInventory(tempProduct, porousAmount, nonPorousAmount);
			

			// close dialog
			setVisible(false);

			// refresh gui list
			productSearchApp.refreshProductsView();
			
			// show success message
			JOptionPane.showMessageDialog(productSearchApp,
					"Inventory updated",
					"Product Reserved",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(
					productSearchApp,
					"Error saving product: "
							+ exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	
	protected void pickProduct() {

		// get the product info from gui as text
		String productIDText = productIDTextField.getText(); 
		String productName = productNameTextField.getText();
		String shelvedQuantityText = shelvedQuantityTextField.getText();
		String reservedQuantityText = shelvedQuantityTextField.getText();
		String porousAvailableText = porousAvailableTextField.getText();
		String nonPorousAvailableText = nonPorousAvailableTextField.getText();
		
		String porousReservedText = porousReserveTextField.getText();
		String nonPorousReservedText = nonPorousReserveTextField.getText();
		
		//Convert read Strings into ints
		int productID = Integer.parseInt(productIDText);
		int shelvedQuantity = Integer.parseInt(shelvedQuantityText);
		int reservedQuantity = Integer.parseInt(reservedQuantityText);
		int porousAvailable = Integer.parseInt(porousAvailableText);
		int nonPorousAvailable = Integer.parseInt(nonPorousAvailableText);
		
		int porousAmount = Integer.parseInt(porousReservedText);
		int nonPorousAmount = Integer.parseInt(nonPorousReservedText);
		

		Product tempProduct = new Product(productID, productName, shelvedQuantity, reservedQuantity, porousAvailable, nonPorousAvailable);
		
		try {
			
				inventoryDAO.pickInventory(tempProduct, porousAmount, nonPorousAmount);
			

			// close dialog
			setVisible(false);

			// refresh gui list
			productSearchApp.refreshProductsView();
			
			// show success message
			JOptionPane.showMessageDialog(productSearchApp,
					"Inventory updated",
					"Product Reserved",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(
					productSearchApp,
					"Error saving product: "
							+ exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
