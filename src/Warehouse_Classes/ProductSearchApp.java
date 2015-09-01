package Warehouse_Classes;

 import Warehouse_Classes.Product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Warehouse_Classes.BasePanel;

import com.jgoodies.forms.layout.ColumnSpec;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Warehouse_Classes.BasePanel;

/***************************************************create variable for updated temp Products****************************************************/

public class ProductSearchApp extends BasePanel {
	private JPanel panel;
	private JTextField productIDTextField;
	private JTextField productNameTextField;
	private JTextField shelvedQuantityTextField;
	private JTextField porousAvailableTextField;
	private JTextField nonPorousAvailableTextField;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JTable table;
	String user;
	
	private InventoryDAO inventoryDAO;
	private JPanel panel1;
	private JButton btnAddProduct;
	private JButton btnUpdateProduct;
	private JButton btnReserveProduct;
	private JButton back;
	private JLabel lblEnterProductName;
	
	

	public ProductSearchApp(JFrame frame, String user){
		
		super(frame);
		setVisible(true);
		this.user = user;
		
		try {
			inventoryDAO = new InventoryDAO();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
		}
	
	}
	

	
	/********************************************************************************************************Build GUI******************************************************/
	
	@Override
	protected void prepareGui(){
		btnSearch = new JButton("Search");
		lblEnterProductName = new JLabel("Enter product name");
		productNameTextField = new JTextField();
		panel = new JPanel();
		table = new JTable();
		scrollPane = new JScrollPane(table);
		panel1 = new JPanel();
		
		btnAddProduct = new JButton("Add Product");
		btnUpdateProduct = new JButton("Update Product");
		btnReserveProduct = new JButton("Reserve Product");
		back = new JButton("Back");
		
		
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		add(panel, BorderLayout.NORTH);
		panel.add(productNameTextField);
		panel.add(btnSearch);
		add(lblEnterProductName);
		
		panel1.add(btnAddProduct);
		panel1.add(btnUpdateProduct);
		panel1.add(btnReserveProduct);
		panel1.add(back);
		add(scrollPane, BorderLayout.CENTER);
		add(panel1, BorderLayout.SOUTH);
		scrollPane.setViewportView(table);
		
	
	}
	
	/********************************************************************************************************Attach Events to GUI*****************************************************/
	
	@Override
	protected void prepareEvents(){
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get last name from the text field

				// Call DAO and get Products for the product name

				// If product name is empty, then get all Products

				// Print out Products				
				
				try {
					String productName = productNameTextField.getText();

					List<Product> products = null;

					if (productName != null && productName.trim().length() > 0) {
						products = inventoryDAO.searchProducts(productName);
					} else {
						products = inventoryDAO.getAllProducts();
					}
					
					// create the model and update the "table"
					ProductTableModel model = new ProductTableModel(products);
					
					table.setModel(model);
					
					/*
					for (product temp : products) {
						System.out.println(temp);
					}
					*/
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(ProductSearchApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		
		
		/************************************************************Action Description for Add products button************************************************************************************************/		
		
		btnAddProduct.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				AddInventoryApp dialog = new AddInventoryApp(ProductSearchApp.this, inventoryDAO, null, false, mainFrame);
				GUIStack.openWindow(dialog);
			}
		});
		
		
		
		
		
		
/************************************************************Action Description for reserve products button************************************************************************************************/		
		
		btnReserveProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				
				// make sure a row is selected
				if (row < 0) {
					JOptionPane.showMessageDialog(ProductSearchApp.this, "You must select a product", "Error",
							JOptionPane.ERROR_MESSAGE);				
					return;
				}
				
				
				// get the current product
				
				Object[] temp = new Object[ProductTableModel.columnNames.length];
				//tempProduct.setProductID(productID);
				for(int i = 0; i < ProductTableModel.columnNames.length;i++){
				temp[i] = table.getValueAt(row, i);
				System.out.println(temp[i]);
				}	
				Product tempProduct = new Product( ((int)temp[0]), ((String)temp[1]) , ((int)temp[2]) , ((int)temp[3]) , ((int)temp[4]), ((int)temp[5]));
				ReserveInventoryApp dialog = new ReserveInventoryApp(ProductSearchApp.this, inventoryDAO, tempProduct, mainFrame);
				GUIStack.openWindow(dialog);
				
			}
		});
		
		
		/************************************************************Action Description for Update products button************************************************************************************************/			
		btnUpdateProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = table.getSelectedRow();
				
				// make sure a row is selected
				if (row < 0) {
					JOptionPane.showMessageDialog(ProductSearchApp.this, "You must select an product", "Error",
							JOptionPane.ERROR_MESSAGE);				
					return;
				}
				
				// get the current product
			
				Object[] temp = new Object[ProductTableModel.columnNames.length];
				//tempProduct.setProductID(productID);
				for(int i = 0; i < ProductTableModel.columnNames.length;i++){
				temp[i] = table.getValueAt(row, i);
				System.out.println(temp[i]);
				}
				
				
				Product tempProduct = new Product( ((int)temp[0]), ((String)temp[1]) , ((int)temp[2]) , ((int)temp[3]) , ((int)temp[4]), ((int)temp[5]));
				
				
				System.out.println(table.getValueAt(row, ProductTableModel.OBJECT_COL).getClass());
				AddInventoryApp dialog = new AddInventoryApp(ProductSearchApp.this, inventoryDAO, tempProduct, true, mainFrame);
				GUIStack.openWindow(dialog);
				
			}
		});
		

		
		
		/************************************************************Action Description for Add back button************************************************************************************************/			
		
		
		back.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e)
			{
				GUIStack.goBack();
				
			}
		
		});
		
		
	}
	
	public void refreshProductsView() {
		try{
			List<Product> products = inventoryDAO.getAllProducts();
			
			//create the model and update the table
			
			ProductTableModel model = new ProductTableModel(products);
			
			table.setModel(model);
		} catch(Exception exc){
			JOptionPane.showMessageDialog(this,  "ERROR: " + exc, "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
	}


}
