package Warehouse_Classes;

import java.util.*;
import java.sql.*;
import java.io.*;


public class InventoryDAO {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/nbgardens_warehouse_database";
	static final String USER = "root";
	static final String PASS = "netbuilder";
	ResultSet rs;
	private Connection conn;
	
	public InventoryDAO() throws Exception {
		
		
	}
	/**************************************************************Resrve Inventory Method**************************************************************************************************************************/
	public void pickInventory(Product theProduct, int porousAmount, int nonPorousAmount) throws SQLException, ClassNotFoundException {
		PreparedStatement myStmt = null;
		

		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// prepare statement
			
			int totalRemoved = porousAmount + nonPorousAmount;
			myStmt = conn.prepareStatement("UPDATE INVENTORY"
					+ " SET shelvedQuantity = shelvedQuantity - " + totalRemoved 
					+ " WHERE productID =?");
			
			// set params
			
		
			myStmt.setInt(1, theProduct.getProductID());		
			
			System.out.println(totalRemoved + " " + theProduct.getProductName() +"s being removed from inventory");
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			conn.close();
		}
		
	}
	
	
	
	
	
	
	/************************************************Resrve Inventory Method**************************************************************************************************************************/
	public void reserveInventory(Product theProduct, int porousAmount, int nonPorousAmount) throws SQLException, ClassNotFoundException {
		PreparedStatement myStmt = null;
		

		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// prepare statement
			
			int totalReserved = porousAmount + nonPorousAmount;
			myStmt = conn.prepareStatement("UPDATE INVENTORY"
					+ " SET reservedQuantity = reservedQuantity + " + totalReserved + " , porousReserved = " + porousAmount + " , nonPorousReserved = " + nonPorousAmount + " , porousAvailable = porousAvailable - " + porousAmount + " , nonPorousAvailable = nonPorousAvailable - " + nonPorousAmount
					+ " WHERE productID =?");
			
			// set params
			
		
			myStmt.setInt(1, theProduct.getProductID());
			
			
			System.out.println("Product Name: " + theProduct.getProductName() + " Shelvded Quantity: " +  theProduct.getShelvedQuantity() + "Porous Available: " + theProduct.getPorousAvailable() + "NonPorous Available: " +theProduct.getNonPorousAvailable() + "Product ID: " + theProduct.getProductID());			
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			conn.close();
		}
		
	}
	
	/************************************************Update Inventory Method**************************************************************************************************************************/
	public void updateInventory(Product theProduct) throws SQLException, ClassNotFoundException {
		PreparedStatement myStmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// prepare statement
			
			System.out.println("Product Name: " + theProduct.getProductName() + " Shelvded Quantity: " +  theProduct.getShelvedQuantity() + "porous Available: " + theProduct.getPorousAvailable() + "NonPorous Available: " +theProduct.getNonPorousAvailable() + "Product ID: " + theProduct.getProductID());
			myStmt = conn.prepareStatement("UPDATE INVENTORY"
					+ " SET productName=? , shelvedQuantity=?, porousAvailable=?, nonPorousAvailable=?"
					+ " WHERE productID=?");
			
			// set params
			myStmt.setString(1, theProduct.getProductName());
			myStmt.setInt(2, theProduct.getShelvedQuantity());
			myStmt.setInt(3, theProduct.getPorousAvailable());
			myStmt.setInt(4, theProduct.getNonPorousAvailable());
			myStmt.setInt(5, theProduct.getProductID());
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			conn.close();
		}
		
	}
	
	
	
	
	/***************************************************************Add Inventory Method******************************************************************************************************************/
	public void addInventory(Product theProduct) throws Exception{
  		PreparedStatement stmt = null;
  		Statement stmt0 = null;
  	    
		
  	    try {
  	    	Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
    				 
  				System.out.println("Creating statement...");
  				stmt = conn.prepareStatement("INSERT INTO INVENTORY"
  		  	           + "(productName, shelvedQuantity, reservedQuantity, porousAvailable, minPorous, nonPorousAvailable, porousReserved, nonPorousReserved)"
  		  	           + " values(?, ?, ?, ?, ?, ?, ?, ?)");
  				
  				stmt.setString(1, theProduct.getProductName());
  				stmt.setInt(2, theProduct.getShelvedQuantity());
  				stmt.setInt(3, theProduct.getReservedQuantity());
  				stmt.setInt(4, theProduct.getPorousAvailable());
  				stmt.setInt(5, theProduct.getMinPorous());
  				stmt.setInt(6, theProduct.getNonPorousAvailable());
  				stmt.setInt(7, theProduct.getPorousReserved());
  				stmt.setInt(8, theProduct.getNonPorousReserved());
  				stmt.executeUpdate();
  				
  				int newID =0;
  				stmt0 = conn.createStatement();
  				String sql = "SELECT productID FROM INVENTORY WHERE productName = '" + theProduct.getProductName() + "'";
  				ResultSet rs = stmt0.executeQuery(sql);
  				rs.next();
  				
  				newID = rs.getInt("productID");
  				
  				System.out.println("New item's product ID: " + newID);
  				
  				System.out.println(" New item added");
  				System.out.println("Updated inventory:");  
  				
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
	
	
	
	
	/*************************************************************Retrieve Inventory Table*****************************************************************************************************/
	public List<Product> getAllProducts() throws Exception {
		Class.forName(JDBC_DRIVER);
		System.out.println("Connecting to database...");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		List<Product> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = conn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM INVENTORY");
	
			while (myRs.next()) {
				Product tempProduct = convertRowToProduct(myRs);
				list.add(tempProduct);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Product> searchProducts(String productName) throws Exception {
		Class.forName(JDBC_DRIVER);
		System.out.println("Connecting to database...");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		List<Product> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			productName += "%";
			myStmt = conn.prepareStatement("select * from inventory where productName like ?");
			
			myStmt.setString(1, productName);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Product tempProduct = convertRowToProduct(myRs);
				list.add(tempProduct);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private Product convertRowToProduct(ResultSet myRs) throws SQLException {
		
		
		int productID = myRs.getInt("productID");
		String productName = myRs.getString("productName");
     	int shelvedQuantity = myRs.getInt("shelvedQuantity");
     	int reservedQuantity = myRs.getInt("reservedQuantity");
     	int porousAvailable = myRs.getInt("porousAvailable");
     	int nonPorousAvailalbe = myRs.getInt("nonPorousAvailable");
		
		Product tempProduct= new Product(productID, productName, shelvedQuantity, reservedQuantity, porousAvailable, nonPorousAvailalbe);
		//productID, supplierID, wholesalePrice, retailPrice, 
		return tempProduct;
	}

	
	private static void close(Connection conn, Statement stmt, ResultSet rs)
			throws SQLException {

		if (rs != null) {
			rs.close();
		}

		if (stmt != null) {
			
		}
		
		if (conn != null) {
			conn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}

	public static void main(String[] args) throws Exception {
		
		InventoryDAO dao = new InventoryDAO();
	}
}