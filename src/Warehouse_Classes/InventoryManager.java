package Warehouse_Classes;

import java.sql.*;
import java.util.Scanner;

//This class is used by warehouse operators to keep stock levels up-to-date********************************************************************

public class InventoryManager {
  
  	Connection conn;
	Statement stmt, stmt1, stmt3, stmt2;
	ResultSet rs;
	Scanner keyboard = new Scanner(System.in);
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/nbgardens_warehouse_database";
	static final String USER = "root";
	static final String PASS = "netbuilder";
  
  public InventoryManager(){
  		int manageMore = 1;
  		do{
    	
  			
  			
  			//Command line tool to decide which operation warehouse operator will execute*******************************************************
  			System.out.println("Choose inventory management option");
  			System.out.println("type 1 and press enter to restock a product");
  			System.out.println("type 2 and press enter to reserve stock for an order");
  			System.out.println("type 3 and press enter to mark a product picked");
      
  			//System.out.println("type 4 and press enter to add a new product to the list");
  			//System.out.println("type 5 and press enter to discard a product");

  			int operation = keyboard.nextInt();   
  				switch (operation) {
            	case 1:stockShelf();
   	         				break;
            	case 2:  reserveProductForOrder();
                  	   		break;
           	    case 3:  pickProduct();
            	         	break;
           	    //case 4:  discardProduct();
                //      	break;
                //case 5:  addInventory();
           		//			break;
               	default: System.out.println("Invalid selection");
               				break;
      			}	
    			System.out.println("Would you like to make more inventory updates?");
    	    	System.out.println("type 1 for yes or 2 for no and press Enter ");
    	    	
    	    	//Command line tool to decide if more inventory management necessary**************************************************************
      	    	int ans = keyboard.nextInt();
    	    	switch(ans){
    	    		case 1: manageMore = 1;
        			System.out.println("Returning to Inventory Management menu");
        			break;
          
    	    		case 2: manageMore = 2;
        			System.out.println("Thanks for keeping inventory up-to-date");
        			break;

    	    		default: System.out.println("Invalid Selection!");
        			System.out.println("returning to Inventory Management Menu");
      				break;
    	    	}   
  		}while(manageMore==1); 
    	
	}
  
  	public void stockShelf(){
  		
  		System.out.println("Enter ID of product being re-shelved");
  	     int receivedProductID = keyboard.nextInt();
  	     System.out.println("Enter amount of product being re-shelved");
  	     int receivedAmount = keyboard.nextInt();
  	     
  	     
  	     try {
  				Class.forName(JDBC_DRIVER);
  				System.out.println("Connecting to database...");
  				conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
  			//read current porous quantity
  				stmt = conn.createStatement();
  				int porousAvailable = 0;
  				int porousNeed = 0;
  				int nonPorousAvailable = 0;
  				int porousAllocation = 0;
  				
					String sql = 	"SELECT porousAvailable, minPorous, nonPorousAvailable "
								+	"FROM INVENTORY "
								+ 	"WHERE productID = " + receivedProductID ;
					rs = stmt.executeQuery(sql);
					
					rs.next();
					
					porousAvailable = rs.getInt("porousAvailable");
					porousNeed = rs.getInt("minPorous");
					nonPorousAvailable = rs.getInt("nonPorousAvailable");
					
					System.out.println("porousAvailable: " + porousAvailable);
					System.out.println("minPorous: " + porousNeed);
					System.out.println("nonPorousAvailable: " + nonPorousAvailable);
				
				//allocate stock to porous ware
	
				int porousDeficit = porousNeed - porousAvailable;
				System.out.println(porousDeficit);
				
				System.out.println(receivedAmount + " items received, " + porousDeficit + " Items need porous treatment");
				System.out.println("");
				
				if (receivedAmount <= porousDeficit){
					porousAllocation = receivedAmount;
					}
				
				else{
					porousAllocation = receivedAmount - porousDeficit;
					}
			
					stmt1 = conn.createStatement();
						String sql0 = "UPDATE INVENTORY " 
							+	"SET nonPorousAvailable = nonPorousAvailable + " + String.valueOf(receivedAmount - porousAllocation)
							+   ", porousAvailable = porousAvailable + " + String.valueOf(porousAllocation)
							+   ", shelvedQuantity = shelvedQuantity + " + String.valueOf(receivedAmount)
							+ 	" WHERE productID = " + String.valueOf(receivedProductID);
					stmt1.executeUpdate(sql0);

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
  
  	public void reserveProductForOrder(){
     
     System.out.println("Enter ID of product being reserved");
     int reservedProductID = keyboard.nextInt();
     System.out.println("Enter amount of product being reserved");
     int reservedAmount = keyboard.nextInt();
     
     
     try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
       
       	
			 
			 //Prepare the stored procedure call
			System.out.println("Creating statement...");
         //stmt = conn.prepareCall{"{call reserve_stock_for_order(?,?)}"};
			
			
			//Update reserved quantity
			
			stmt = conn.createStatement();
				String sql = 	"UPDATE INVENTORY " 
	   						+	"SET reservedQuantity = reservedQuantity + " + String.valueOf(reservedAmount) 
	   						+ 	" where productID = " + String.valueOf(reservedProductID);
				
			 stmt.executeUpdate(sql);
			 	
			 
			 // Update quantity available
			stmt = conn.createStatement();
				String sql2 = 	"UPDATE INVENTORY " 
						+	"SET availableQuantity = availableQuantity - " + String.valueOf(reservedAmount) 
						+ 	" where productID = " + String.valueOf(reservedProductID);
       
			stmt.executeUpdate(sql2);
			
       	//set the parameters
       	//stmt.setInt(1,orderID);
      	//stmt.setInt(2,reservedProduct);
       	//stmt.setInt(3,reservedAmount);
       	
       	//Call stored procedure
       	//System.out.println("\n\nCalling stored procedure.  reserve_stock_for_order('" + reservedProduct + "', '"+reservedAmount+"')");
       
	
			
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
  
  	public void pickProduct(){
  		
  		System.out.println("Enter ID of product being removed from shelf");
  	     int pickedProductID = keyboard.nextInt();
  	     System.out.println("Enter amount of product being picked");
  	     int pickedAmount = keyboard.nextInt();
  	     
  	     
  	     try {
  				Class.forName(JDBC_DRIVER);
  				System.out.println("Connecting to database...");
  				conn = DriverManager.getConnection(DB_URL, USER, PASS);
  	       
  	       	
  				 
  				 //Prepare the stored procedure call
  				System.out.println("Creating statement...");
  	         //stmt = conn.prepareCall{"{call reserve_stock_for_order(?,?)}"};
  				
  				
  				//Update shelvedQuantity
  				stmt = conn.createStatement();
  					String sql = 	"UPDATE INVENTORY " 
  							+	"SET shelvedQuantity = shelvedQuantity - " + String.valueOf(pickedAmount) 
  							+ 	" where productID = " + String.valueOf(pickedProductID);
  	       
  				stmt.executeUpdate(sql);
  				
  			//Update reserved quantity
  				
  				stmt = conn.createStatement();
  					String sql2 = 	"UPDATE INVENTORY " 
  		   						+	"SET reservedQuantity = reservedQuantity - " + String.valueOf(pickedAmount) 
  		   						+ 	" where productID = " + String.valueOf(pickedProductID);
  					
  				 stmt.executeUpdate(sql2);
  				
  	       	//set the parameters
  	       	//stmt.setInt(1,orderID);
  	      	//stmt.setInt(2,reservedProduct);
  	       	//stmt.setInt(3,reservedAmount);
  	       	
  	       	//Call stored procedure
  	       	//System.out.println("\n\nCalling stored procedure.  reserve_stock_for_order('" + reservedProduct + "', '"+reservedAmount+"')");
  	       
  		
  				
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
  
  	public void discardProduct(){
     
   }
  	
  	public void addInventory(){
  	    
		
  	    try {
  	      Scanner keyboard = new Scanner(System.in);
  	    
  	    
  		System.out.println("Enter new product ID");
  	   int newID = keyboard.nextInt();
  	    
  	   System.out.println("Enter new product name"); 
  	   String newName = keyboard.nextLine();
  	    
  	   System.out.println("Enter new product supplier ID"); 
  	   int newSupplierID = keyboard.nextInt();
  	     
  	   System.out.println("Enter new product wholesale price");
  		Double newWholesale = keyboard.nextDouble();
  	     
  	   System.out.println("Enter new product retail price");  
  	   Double newRetail = keyboard.nextDouble();
  	     
  	  	System.out.println("Enter new product quantity");
  	   int newQuantity = keyboard.nextInt();
  	     
  	   System.out.println("Enter new product porous quantity");
  	   int newPorous = keyboard.nextInt();
  	      
  	      
  				Class.forName(JDBC_DRIVER);
  				System.out.println("Connecting to database...");
  				conn = DriverManager.getConnection(DB_URL, USER, PASS);
  				 
  				 
  				System.out.println("Creating statement...");
  				stmt = conn.createStatement();
  				String sql = "insert into inventory"
  	           + "(productID, productName, supplierID, wholesalePrice, retailPrice, quantity, porousQuantity)"
  	           + " values(newID, newName,newSupplierID,newWholesale,newRetail,newQuantity,newPorous)";
  				stmt.executeUpdate(sql);
  				
  	      	System.out.println(" New item added");
  	         System.out.println("Updated inventory:");  
  				while(rs.next()){
  	           
  	           System.out.println("productID:" + rs.getInt("productID") + "name:" + rs.getString("productName") + "supplierID:" + rs.getInt("supplierID") + "wholesalePrice:" + rs.getDouble("wholesalePrice") + "retailPrice:" + rs.getInt("retailPrice") + "quantity:" + rs.getInt("quantity") + "porousQuantity:" + rs.getInt("porousQuantity"));
  	         }
  				rs.close();
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