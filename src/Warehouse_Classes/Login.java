package Warehouse_Classes;
import javax.swing.*;

import Warehouse_Classes.BasePanel;

import java.awt.event.*;
import java.sql.*;

/**************Class to display login pages, access database and authenticate user info****************/

public class Login extends BasePanel{
	 
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	 static final String DB_URL = "jdbc:mysql://localhost/nbgardens_warehouse_database";
	 static final String USER = "root";
	 static final String PASS = "netbuilder";
	 
	 JFrame f; 
	 JLabel l;
	 JLabel l1;
	 JTextField t;;
	 JTextField t1;
	 JButton b;
	 JButton b0;
	 
	 
	 
	 /*******************************************Constructor Method **************************************************/
		public Login(JFrame frame){
			
			super(frame);
			setVisible(true);
				
		}
	 
	 
	 protected void prepareGui() {
			// Add all your components here
		 
		  f = new 	JFrame("Employee Login");
		  l = new 	JLabel("Employee ID:");
		  l1= new 	JLabel("Password:");
		  t = new 	JTextField(10);
		  t1= new 	JTextField(10);
		  b = new 	JButton("Login");
		  //b0 = new 	JButton("New User");

		 add(l);
		 add(t);
		 add(l1);
		 add(t1);
		 add(b);
		 //add(b0);
		}
		
		protected void prepareEvents() {
			// Add all listeners and action commands here
			//b0.addActionListener(new ActionListener(){
			//	public void actionPerformed (ActionEvent c){
			//		closeWindow();
			//		AccountCreationGUI createAccount = new AccountCreationGUI(mainFrame);
			//	}
			//});
			
			
			
			b.addActionListener(new ActionListener(){
				public void actionPerformed (ActionEvent e)
				{
					
					Connection conn =null;
					Statement stmt = null;
				
					try
					{
						  Class.forName( JDBC_DRIVER);
						  System.out.println("Validating User Info");
						  conn = DriverManager.getConnection(DB_URL, USER, PASS);

					String user = t.getText().trim();
					String pass = t1.getText().trim();
					
					stmt = conn.createStatement();
					String sql = "select employeeID, authenticationCode from warehouse_operator where employeeID = '" + user + "'and authenticationCode = '" + pass + "'";
					ResultSet rs = stmt.executeQuery(sql);
					rs = stmt.executeQuery(sql);

                 
/*******************************************authenticate user info*********************************************/
					int count = 0;
						
						while (rs.next()) {
						  
						count = count + 1;	
							
						}
						
						if (count ==1)
						{
							JOptionPane.showMessageDialog(null,"User found, Access Granted!");
							WarehouseHome mainMenu = new WarehouseHome(user, mainFrame);
							GUIStack.openWindow(mainMenu);
						}
						
						else if(count > 1)
						{
							JOptionPane.showMessageDialog(null, "Duplicate User, Access Denied");
						}
						
						else{
							JOptionPane.showMessageDialog(null, "Credentials do not match user on file!");
						}
						
					rs.close();
					}
					
					catch (SQLException sqle) {
						 sqle.printStackTrace();
					 } 
					 
					 catch (Exception e1) {
						 e1.printStackTrace();
					}

					 
					 finally {
						 	try {
						 		if (stmt != null)
						 		conn.close();
						 	} 
						 	
						 	catch (SQLException se) { }
						 	
						 	try {
						 		if (conn != null)
						 		conn.close();
						 	} 
						 	
						 	catch (SQLException se) {
						    	se.printStackTrace();
						   	}
						}
						  System.out.println("User Authentication Complete");
				}
					
		});
		}

}

