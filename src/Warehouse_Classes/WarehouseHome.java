package Warehouse_Classes;
import javax.swing.*;
import Warehouse_Classes.BasePanel;
import java.awt.event.*;
import java.sql.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Warehouse_Classes.CustomerOrderLine;
import Warehouse_Classes.CustomerOrderLineDAO;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


//This is a class I made for my benefit to practice GUI*****************************************************************************

public class WarehouseHome extends BasePanel{
	 
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	 static final String DB_URL = "jdbc:mysql://localhost/nbgardens_warehouse_database";
	 static final String USER = "root";
	 static final String PASS = "netbuilder";
	 String user;
	 
	JFrame f;
	JLabel operator;
	JButton b;
	JButton b0;
	
	
	public WarehouseHome(String user, JFrame frame){
		
		super(frame);
		setVisible(true);
		this.user = user;
		setUserName();
			
	}
	
	@Override
	protected void prepareGui() {
	
		operator = new JLabel();
		b = new 	JButton("Inventory Manager");
		b0 = new 	JButton("Orderline Manager");
		
		add(operator);
		add(b);
		add(b0);

	}
	
	@Override
	protected void prepareEvents() {
		b0.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent c){
				closeWindow();
				CustomerOrderLineList orderLineList = new CustomerOrderLineList(user, mainFrame);
			}
		
		});
		
		b.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e)
			{
				closeWindow();
				ProductSearchApp productManager = new ProductSearchApp(mainFrame, user);
				
			}
		
		});
	}
		
		
	
	private void setUserName(){
		
		Connection conn0 =null;
		Statement stmt0 = null;
		String username = null;
		
		
		try
		{	Class.forName( JDBC_DRIVER);
		  	System.out.println("Connecting to database...");
		  	conn0 = DriverManager.getConnection(DB_URL, USER, PASS);
		  	
		  	stmt0 = conn0.createStatement();
		  	System.out.println(user);
			String sql = "select lastName from warehouse_operator where employeeID = '" + user + "'";
			ResultSet rs = stmt0.executeQuery(sql);
			System.out.println("assigning username");
			while(rs.next()){
				username = rs.getString("lastName");
			}
		
		}
		
		catch (SQLException sqle) {
			 sqle.printStackTrace();
		 } 
		 
		 catch (Exception e1) {
			 e1.printStackTrace();
		}

		 finally {
			 	try {
			 		if (stmt0 != null)
			 		conn0.close();
			 	} 
			 	
			 	catch (SQLException se) { }
			 	
			 	try {
			 		if (conn0 != null)
			 		conn0.close();
			 	} 
			 	
			 	catch (SQLException se) {
			    	se.printStackTrace();
			   	}
			}
		
		
		String welcomeString = "Welcome Mr. " + username;
		operator.setText(welcomeString);
	}
	
	
}


	
