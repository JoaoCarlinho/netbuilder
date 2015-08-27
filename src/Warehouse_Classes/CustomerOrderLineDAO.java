package Warehouse_Classes;


import Warehouse_Classes.CustomerOrderLine;
import java.util.*;
import java.sql.*;
import java.io.*;

/**
 * 
 * @author www.luv2code.com
 *
 */
public class CustomerOrderLineDAO {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/nbgardens_warehouse_database";
	static final String USER = "root";
	static final String PASS = "netbuilder";
	ResultSet rs;

	private Connection conn;
	
	public CustomerOrderLineDAO() throws Exception {
		
		
	}
	
	public void updateCustomerOrderLine(CustomerOrderLine theCustomerOrderLine) throws SQLException, ClassNotFoundException {
		PreparedStatement myStmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// prepare statement
			myStmt = conn.prepareStatement("UPDATE customer_order_line"
					+ " SET handlerID=? "
					+ " WHERE customerOrderID= '" + theCustomerOrderLine.getCustomerOrderID() + "'");
			
			myStmt.setInt(1, theCustomerOrderLine.getHandlerID());
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			conn.close();
		}
		
	}
	
	
	public void addCustomerOrderLine(CustomerOrderLine theCustomerOrderLine) throws Exception{
  		PreparedStatement stmt = null;
  		Statement stmt0 = null;
  	    
		
  	    try {
  	    	Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
    				 
  				System.out.println("Creating statement...");
  				stmt = conn.prepareStatement("INSERT INTO customer_order_line"
  		  	           + "(customerOrderID, productID, quantity, productStatusCode, porousCount, handlerID, orderStatusCode)"
  		  	           + " values(?, ?, ?, ?, ?, ?, ?)");
  				
  				stmt.setInt(1, theCustomerOrderLine.getCustomerOrderID());
  				stmt.executeUpdate();
  				

  				int newID =0;
  				stmt0 = conn.createStatement();
  				String sql = "SELECT customerOrderID FROM customer_order_line WHERE customerOrderID = '" + theCustomerOrderLine.getCustomerOrderID() + "'";
  				ResultSet rs = stmt0.executeQuery(sql);
  				rs.next();
  				
  				newID = rs.getInt("customerOrderID");
  				
  				System.out.println("New Customer Order ID: " + newID);
  				
  				System.out.println(" New order line added");
  				System.out.println("Updated customer order line:");  
  				
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
	
	public List<CustomerOrderLine> getAllCustomerOrderLines() throws Exception {
		Class.forName(JDBC_DRIVER);
		System.out.println("Connecting to database...");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		List<CustomerOrderLine> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = conn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM customer_order_line");
			
			while (myRs.next()) {
				CustomerOrderLine tempCustomerOrderLine = convertRowToCustomerOrderLine(myRs);
				list.add(tempCustomerOrderLine);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<CustomerOrderLine> listCustomerOrderLines(int customerOrderID) throws Exception {
		Class.forName(JDBC_DRIVER);
		System.out.println("Connecting to database...");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		List<CustomerOrderLine> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			System.out.println("Grabbing Customer Order ID");
			myStmt = conn.prepareStatement("select * from customer_order_line where customerOrderID like " + customerOrderID);
		
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				CustomerOrderLine tempCustomerOrderLine = convertRowToCustomerOrderLine(myRs);
				list.add(tempCustomerOrderLine);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private CustomerOrderLine convertRowToCustomerOrderLine(ResultSet myRs) throws SQLException {
		
		int customerOrderID = myRs.getInt("customerOrderID");
		int productID = myRs.getInt("productID");
     	int quantity = myRs.getInt("quantity");
     	int productStatusCode = myRs.getInt("productStatusCode");
     	int porousCount = myRs.getInt("porousCount");
     	int handlerID = myRs.getInt("handlerID");
     	int orderStatusCode = myRs.getInt("orderStatusCode");
		
		CustomerOrderLine tempCustomerOrderLine= new CustomerOrderLine(customerOrderID, productID, quantity, productStatusCode, porousCount, handlerID, orderStatusCode);
		return tempCustomerOrderLine;
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
		
		CustomerOrderLineDAO dao = new CustomerOrderLineDAO();
	}
}