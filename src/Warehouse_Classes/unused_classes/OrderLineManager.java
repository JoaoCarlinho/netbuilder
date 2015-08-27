package Warehouse_Classes.unused_classes;

import java.sql.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;


//This class is used by the Warehouse operator to alert other warehouse operators that he is handling a specific order


public class OrderLineManager {
  
	Connection conn;
	Statement stmt;
  	Scanner keyboard = new Scanner(System.in);
  
  	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/nbgardens_warehouse_database";
	static final String USER = "root";
	static final String PASS = "netbuilder";
  
   public OrderLineManager(){
	   
	   //Command line tool to accept employee ID and order number employee will handle**************************************************************
   		System.out.println("Enter employeeID of order handler");
     	int handlerID = keyboard.nextInt(); 
     	System.out.println("Enter ID of order being handled");
     	int orderID = keyboard.nextInt();

     	
     	
     	try {  //accessing database
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
 
		System.out.println("Creating statement...");
        
		
		
		//SQL customer order line table with the handlers ID for a specific order********************************************************************
		stmt = conn.createStatement();
			String sql = 	"UPDATE CUSTOMER_ORDER_LINE " 
   						+	"SET handlerID = " + String.valueOf(handlerID) 
   						+ 	" where customerOrderID = " + String.valueOf(orderID);
		 stmt.executeUpdate(sql);
			
           System.out.println("Update complete!");
         
		 } 
		 
		 catch (SQLException sqle) {
			 sqle.printStackTrace();
		 } 
		 
		 catch (Exception e) {
			 e.printStackTrace();
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
			  System.out.println("Connection Closed");     
   }
}
