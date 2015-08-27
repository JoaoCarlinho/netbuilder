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

import Warehouse_Classes.CustomerOrderLine;
import Warehouse_Classes.CustomerOrderLineDAO;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddCustomerOrderLineApp extends BasePanel {

	private JTextField orderIDTextField;
	private JTextField productIDTextField;
	private JTextField quantityTextField;
	private JTextField productStatusCodeTextField;
	private JTextField porousCountTextField;
	private JTextField handlerIDTextField;
	private JTextField orderStatusCodeTextField;
	
	private JLabel lblCustomerOrderLineID; 
	private JLabel lblQuantityLabel;
	private JLabel lblproductID;
	private JLabel lblProductStatusCode;
	private JLabel lblPorousCount;
	private JLabel lblHandlerID;
	private JLabel lblOrderStatusCode;
	
	private JPanel buttonPane;
	private JButton okButton;
	private JButton cancelButton;
	
	private CustomerOrderLineDAO customerOrderLineDAO;
	private CustomerOrderLineList customerOrderLineList;
	
	private CustomerOrderLine previousCustomerOrderLine = null;
	private boolean updateMode = false;

	public AddCustomerOrderLineApp(CustomerOrderLineList theCustomerOrderLineList, CustomerOrderLineDAO theCustomerOrderLineDAO, CustomerOrderLine thePreviousCustomerOrderLine, boolean theUpdateMode, JFrame frame) {
		
		super(frame);
		setVisible(true);
		customerOrderLineDAO = theCustomerOrderLineDAO;
		customerOrderLineList = theCustomerOrderLineList;
		previousCustomerOrderLine = thePreviousCustomerOrderLine;
		updateMode = theUpdateMode;
		
		if(updateMode)	{
			
			populateGui(thePreviousCustomerOrderLine);
		}
	}
	
	
	public AddCustomerOrderLineApp(CustomerOrderLineList theCustomerOrderLineList, CustomerOrderLineDAO theCustomerOrderLineDAO, JFrame frame){
		super(frame);
		setVisible(true);
		customerOrderLineDAO = theCustomerOrderLineDAO;
		customerOrderLineList = theCustomerOrderLineList;
	}
	
	
	@Override
	protected void prepareGui(){
		setLayout(new BorderLayout());
		setLayout(new GridLayout(8,2));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		lblCustomerOrderLineID = new JLabel("Customer Order Line ID");
		orderIDTextField = new JTextField();
		lblproductID = new JLabel("Product ID");
		productIDTextField = new JTextField();
		lblQuantityLabel = new JLabel("Quantity");
		quantityTextField = new JTextField();
		lblProductStatusCode = new JLabel("Product Status Code");
		productStatusCodeTextField = new JTextField();
		lblPorousCount = new JLabel("Porous Count");
		porousCountTextField = new JTextField();
		lblHandlerID = new JLabel("Handler ID");
		handlerIDTextField = new JTextField();
		lblOrderStatusCode = new JLabel("Order Status Code");
		orderStatusCodeTextField = new JTextField();
		
		buttonPane = new JPanel();
		okButton = new JButton("Save");
		cancelButton = new JButton("Cancel");
		
		add(lblCustomerOrderLineID, "2, 2, right, default");
		add(orderIDTextField, "4, 2, fill, default");
			orderIDTextField.setColumns(10);
		add(lblproductID, "4, 4, right, default");
		add(productIDTextField, "6, 4, fill, default");
			productIDTextField.setColumns(10);
		add(lblQuantityLabel, "6, 6, right, default");
		add(quantityTextField, "4, 6, fill, default");
			quantityTextField.setColumns(10);
		add(lblProductStatusCode, "8, 8, right, default");
		add(productStatusCodeTextField, "4, 8, fill, default");
			productStatusCodeTextField.setColumns(10);
		add(lblPorousCount, "10, 10, right, default");
		add(porousCountTextField, "4, 10, fill, default");
			porousCountTextField.setColumns(10);
		add(lblHandlerID, "12, 12, right, default");
		add(handlerIDTextField, "4, 12, fill, default");
			handlerIDTextField.setColumns(10);
		add(lblOrderStatusCode, "14, 14, right, default");
		add(orderStatusCodeTextField, "4, 14, fill, default");
			orderStatusCodeTextField.setColumns(10);
			
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(buttonPane, BorderLayout.SOUTH);
			okButton.setActionCommand("OK");
		buttonPane.add(okButton);
			cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
	}
	
	protected void prepareEvents(){
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveCustomerOrderLine();
					}
				});
				
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						
					}
				});
				
				
	} 
	
	
	private void populateGui(CustomerOrderLine theCustomerOrderLine) {

		orderIDTextField.setText(String.valueOf(theCustomerOrderLine.getCustomerOrderID()));
		productIDTextField.setText(String.valueOf(theCustomerOrderLine.getProductID()));
		quantityTextField.setText(String.valueOf(theCustomerOrderLine.getQuantity()));
		productStatusCodeTextField.setText(String.valueOf(theCustomerOrderLine.getProductStatusCode()));
		porousCountTextField.setText(String.valueOf(theCustomerOrderLine.getPorousCount()));
		handlerIDTextField.setText(String.valueOf(theCustomerOrderLine.getHandlerID()));
		orderStatusCodeTextField.setText(String.valueOf(theCustomerOrderLine.getOrderStatusCode()));		
	}
			
		
	protected void saveCustomerOrderLine() {

		// get the customerOrderLine info from gui as text
		String orderIDText = orderIDTextField.getText();
		String productIDText = productIDTextField.getText();
		String quantityText = quantityTextField.getText();
		String productStatusCodeText = productStatusCodeTextField.getText();
		String porousCountText = porousCountTextField.getText();
		String handlerIDText = handlerIDTextField.getText();
		String orderStatusCodeText = orderStatusCodeTextField.getText();
		
		
		//Convert read Strings into ints
		int customerOrderID = Integer.parseInt(orderIDText);
		int productID = Integer.parseInt(productIDText);
		int quantity = Integer.parseInt(quantityText);
		int productStatusCode = Integer.parseInt(productStatusCodeText);
		int porousCount = Integer.parseInt(porousCountText);
		int handlerID = Integer.parseInt(handlerIDText);
		int orderStatusCode = Integer.parseInt(orderStatusCodeText);

		CustomerOrderLine tempCustomerOrderLine = new CustomerOrderLine(customerOrderID, productID, quantity, productStatusCode, porousCount, handlerID, orderStatusCode);
		
		
		
		if (updateMode){
			tempCustomerOrderLine = previousCustomerOrderLine;
			tempCustomerOrderLine.setCustomerOrderID(customerOrderID);
			tempCustomerOrderLine.setProductID(productID);
			tempCustomerOrderLine.setQuantity(quantity);
			tempCustomerOrderLine.setProductStatusCode(productStatusCode);
			tempCustomerOrderLine.setPorousCount(porousCount);
			tempCustomerOrderLine.setHandlerID(handlerID);
			tempCustomerOrderLine.setOrderStatusCode(orderStatusCode);
			
		}
		try {
			if (updateMode)
			{
				customerOrderLineDAO.updateCustomerOrderLine(tempCustomerOrderLine);
			}
			else{
				customerOrderLineDAO.addCustomerOrderLine(tempCustomerOrderLine);
			}

			// close dialog
			setVisible(false);

			// refresh gui list
			customerOrderLineList.refreshCustomerOrderLinesView();
			
			// show success message
			JOptionPane.showMessageDialog(customerOrderLineList,
					"CustomerOrderLine added succesfully.",
					"CustomerOrderLine Added",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(
					customerOrderLineList,
					"Error saving customerOrderLine: "
							+ exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
