package Warehouse_Classes;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

//This is a class I made for my benefit to practice GUI*****************************************************************************

public class Login {
	 
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	 static final String DB_URL = "jdbc:mysql://localhost/nbgardens_warehouse_database";
	 static final String USER = "root";
	 static final String PASS = "netbuilder";
	 
	 
	
	JFrame f;
	JLabel l; 
	JLabel l1; 
	JTextField t;
	JTextField t1;
	JButton b;
	JButton b0;
	
	


	public Login(){
		

		f = new 	JFrame("Employee Login");
		l = new 	JLabel("Employee ID:");
		l1= new 	JLabel("Password:");
		t = new 	JTextField(10);
		t1= new 	JTextField(10);
		b = new 	JButton("Login");
		b0 = new 	JButton("New User");

		frame();	
	}
	
	public void frame(){
		f.setSize(600,400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		JPanel p = new JPanel();
		p.add(l);
		p.add(t);
		p.add(l1);
		p.add(t1);
		p.add(b);
		p.add(b0);
		
		f.add(p);
		
		b0.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent c){
				
			}
		
		});
		
		b.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e)
			{
				
				Connection conn =null;
				Statement stmt = null;
			
				try
				{
					  Class.forName( JDBC_DRIVER);
					  System.out.println("Connecting to database...");
					  conn = DriverManager.getConnection(DB_URL, USER, PASS);

					
				String user = t.getText().trim();
				String pass = t1.getText().trim();
				
				stmt = conn.createStatement();
				String sql = "select employeeID, authenticationCode from warehouse_operator where employeeID = '" + user + "'and authenticationCode = '" + pass + "'";
				ResultSet rs = stmt.executeQuery(sql);
				rs = stmt.executeQuery(sql);
				
				int count = 0;
					
					while (rs.next()) {
					  
					count = count + 1;	
						
					}
					
					if (count ==1)
					{
						JOptionPane.showMessageDialog(null,"User found, Access Granted!");
						
					}
					
					else if(count > 1)
					{
						JOptionPane.showMessageDialog(null, "Duplicate User, Access Denied");
					}
					
					else{
						JOptionPane.showMessageDialog(null, "User no found!, Create Account");
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
					  System.out.println("Goodbye!");
			}
				
	});
		
		
	}


	 

}
