package Warehouse_Classes;

import Warehouse_Classes.CustomerOrderLine;
import Warehouse_Classes.CustomerOrderLineTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/***************************************************create variable for updated temp Customer Order Lines**********************************************************/
			
public class CustomerOrderLineList extends BasePanel {
  	private JTextField orderIDTextField;
	private JTextField productIDTextField;
	private JTextField quantityTextField;
	private JTextField productStatusCodeTextField;
	private JTextField porousCountTextField;
	private JTextField handlerIDTextField;
	private JTextField orderStatusCodeTextField;
	private JButton btnList;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblEnterCustomerOrderLine;
	private JButton btnAddCustomerOrderLine;
	private JButton btnUpdateCustomerOrderLine;
	
	private CustomerOrderLineDAO customerOrderLineDAO;
	private JPanel panel1;
	private JButton btnAddProduct;
	private JButton btnUpdateProduct;
	private JButton back;
	String user;
	
	/*************************************************************************************************Constructor****************************************************/
	public CustomerOrderLineList(String user, JFrame frame){
		
		super(frame);
		setVisible(true);
		this.user = user;
		
		// create the DAO
		try {
			customerOrderLineDAO = new CustomerOrderLineDAO();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
		}
	}
	
	/*******************************************************************************************Build GUI***************************************************************/
	@Override
	protected void prepareGui() {
		
		JPanel panel = new JPanel();
		btnList = new JButton("List");
		lblEnterCustomerOrderLine = new JLabel("Enter Customer Order");
		btnAddCustomerOrderLine = new JButton("Add Customer Order Line");
		orderIDTextField = new JTextField();
		table = new JTable();
		scrollPane = new JScrollPane(table);
		panel1 = new JPanel();
		btnUpdateCustomerOrderLine = new JButton("Update Order Status");
		back = new JButton("Back");
		
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel, BorderLayout.NORTH);
		panel.add(lblEnterCustomerOrderLine);
		panel.add(orderIDTextField);
		orderIDTextField.setColumns(10);
		panel.add(btnList);
		add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(table);
		add(panel1, BorderLayout.SOUTH);
		
		panel1.add(btnUpdateCustomerOrderLine);
		panel1.add(btnAddCustomerOrderLine);
		panel1.add(back);
	
		
		
	}


	/**************************************************************attach events to GUI**************************************************************************************/	
	
	@Override
	protected void prepareEvents() {
		// TODO Auto-generated method stub
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get last name from the text field

				// Call DAO and get Customer Order Lines for the customer orderline ID

				// If customer order line ID is empty, then get all customer order lines

				// Print out Customer Order Lines				
				
				try {
					String orderIDText = orderIDTextField.getText();

					List<CustomerOrderLine> customerOrderLines = null;

					if (orderIDText != null && orderIDText.trim().length() > 0) {
						customerOrderLines = customerOrderLineDAO.listCustomerOrderLines(Integer.parseInt(orderIDText));
					} else {
						
						customerOrderLines = customerOrderLineDAO.getAllCustomerOrderLines();
					}
					
					// create the model and update the "table"
					CustomerOrderLineTableModel model = new CustomerOrderLineTableModel(customerOrderLines);
					
					
					table.setModel(model);
					
					/*
					for (customerOrderLine temp : customerOrderLines) {
						System.out.println(temp);
					}
					*/
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(CustomerOrderLineList.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		
		
		
		
		/**************************************************************update customer order line button**************************************************************************************/		
		
		btnUpdateCustomerOrderLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = table.getSelectedRow();
				
				// make sure a row is selected
				if (row < 0) {
					JOptionPane.showMessageDialog(CustomerOrderLineList.this, "You must select an order line", "Error",
							JOptionPane.ERROR_MESSAGE);				
					return;
				}
				
				// get the current Customer Order Line
				Object[] temp = new Object[CustomerOrderLineTableModel.columnNames.length];
				//tempProduct.setCustomerOrderID(CustomerOrderID);
				for(int i = 0; i < CustomerOrderLineTableModel.columnNames.length;i++){
				temp[i] = table.getValueAt(row, i);
				System.out.println(temp[i]);
				}
				
				CustomerOrderLine tempCustomerOrderLine = new CustomerOrderLine( ((int)temp[0]), ((int)temp[1]) , ((int)temp[2]) , ((int)temp[3]) , ((int)temp[4]), ((int)temp[5]), ((int)temp[6]));
				
				System.out.println(table.getValueAt(row, ProductTableModel.OBJECT_COL).getClass());
				// create dialog
				AddCustomerOrderLineApp dialog = new AddCustomerOrderLineApp(CustomerOrderLineList.this, customerOrderLineDAO, tempCustomerOrderLine, true, mainFrame);
				GUIStack.openWindow(dialog);
				
			}
		});
		
		
		/**************************************************************button for adding customer order lines**************************************************************************************/		
		
		btnAddCustomerOrderLine.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {	
				// create dialog
		
				AddCustomerOrderLineApp dialog = new AddCustomerOrderLineApp(CustomerOrderLineList.this, customerOrderLineDAO, mainFrame);
				GUIStack.openWindow(dialog);

			}
		});
		
		back.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e)
			{
				GUIStack.goBack();
				
			}
		
		});
	}
		

	
	public void refreshCustomerOrderLinesView() {
		try{
			List<CustomerOrderLine> customerOrderLines = customerOrderLineDAO.getAllCustomerOrderLines();
			
			//create the model and update the table
			
			CustomerOrderLineTableModel model = new CustomerOrderLineTableModel(customerOrderLines);
			
			table.setModel(model);
		} catch(Exception exc){
			JOptionPane.showMessageDialog(this,  "ERROR: " + exc, "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
	}


	

}