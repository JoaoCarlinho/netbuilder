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



public class AddInventoryApp extends BasePanel {

	/*************************************************************Instantiate class variables*****************************************************************************************************/	
	private JTextField productIDTextField;
	private JTextField productNameTextField;
	private JTextField shelvedQuantityTextField;
	private JTextField reservedQuantityTextField;
	private JTextField porousAvailableTextField;
	private JTextField nonPorousAvailableTextField;
	
	
	private JLabel lblProductID;
	private JLabel lblProductName;
	private JLabel lblshelvedQuantity;
	private JLabel lblreservedQuantity;
	private JLabel lblPorousAvailable;
	private JLabel lblPorousAvailablelblPorousAvailable;
	private JLabel lblNonPorousAvailable;
	
	
	private JPanel buttonPane;
	private JButton okButton;
	private JButton cancelButton;

	private InventoryDAO inventoryDAO;
	private ProductSearchApp productSearchApp;
	
	private Product previousProduct = null;
	private boolean updateMode = false;

	public AddInventoryApp(ProductSearchApp theProductSearchApp, InventoryDAO theInventoryDAO, Product thePreviousProduct, boolean theUpdateMode, JFrame frame) {
		super(frame);
		setVisible(true);
		
		inventoryDAO = theInventoryDAO;
		productSearchApp = theProductSearchApp;
		previousProduct = thePreviousProduct;
		updateMode = theUpdateMode;
		
		if(updateMode)	{
		populateGui(thePreviousProduct);
		}
		
		
	}
	
	
	
/***********************************************************Create GUI Objects****************************************************************************************************/
		@Override
		protected void prepareGui() {
				setLayout(new BorderLayout());
				setLayout(new GridLayout(8,2));
				setBorder(new EmptyBorder(5, 5, 5, 5));
			
					lblProductID = new JLabel("Product ID");
				productIDTextField = new JTextField();
					lblProductName = new JLabel("Product Name");
				productNameTextField = new JTextField();
					lblshelvedQuantity = new JLabel("Shelved Quantity");
				shelvedQuantityTextField = new JTextField();
					lblreservedQuantity = new JLabel("Reserved Quantity");
				reservedQuantityTextField = new JTextField();
				reservedQuantityTextField.setEditable(false);
					lblPorousAvailable = new JLabel("Porous Available");
				porousAvailableTextField = new JTextField();
					lblNonPorousAvailable = new JLabel("Non Porous Available");
				nonPorousAvailableTextField = new JTextField();
				
				buttonPane = new JPanel();
				okButton = new JButton("Save");
				cancelButton = new JButton("Cancel");
				
				
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
				
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				add(buttonPane, BorderLayout.SOUTH);
					okButton.setActionCommand("OK");
				buttonPane.add(okButton);
					cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);	
		}
		/*************************************************************Attach events to GUI objects*****************************************************************************************************/
		@Override
		protected void prepareEvents() {
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					saveProduct();
				}
			});
		
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					
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
	
	protected void saveProduct() {

		// get the product info from gui as text
		String productIDText = productIDTextField.getText(); 
		String productName = productNameTextField.getText();
		String shelvedQuantityText = shelvedQuantityTextField.getText();
		String reservedQuantityText = shelvedQuantityTextField.getText();
		String porousAvailableText = porousAvailableTextField.getText();
		String nonPorousAvailableText = nonPorousAvailableTextField.getText();
		
		
		//Convert read Strings into ints
		int productID = Integer.parseInt(productIDText);
		int shelvedQuantity = Integer.parseInt(shelvedQuantityText);
		int reservedQuantity = Integer.parseInt(reservedQuantityText);
		int porousAvailable = Integer.parseInt(porousAvailableText);
		int nonPorousAvailable = Integer.parseInt(nonPorousAvailableText);
		

		Product tempProduct = new Product(productID, productName, shelvedQuantity, reservedQuantity, porousAvailable, nonPorousAvailable);
		
		
		
		if (updateMode){
			System.out.println("updating Product");
			tempProduct = previousProduct;
			tempProduct.setProductName(productName);
			tempProduct.setShelvedQuantity(shelvedQuantity);
			tempProduct.setPorousAvailable(porousAvailable);
			tempProduct.setNonPorousAvailable(nonPorousAvailable);
		}
		try {
			if (updateMode)
			{
				inventoryDAO.updateInventory(tempProduct);
			}
			else{
				inventoryDAO.addInventory(tempProduct);
			}

			// close dialog
			setVisible(false);

			// refresh gui list
			productSearchApp.refreshProductsView();
			
			// show success message
			JOptionPane.showMessageDialog(productSearchApp,
					"Product added succesfully.",
					"Product Added",
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
