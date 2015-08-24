package Warehouse_Classes;

import java.util.*;
import java.sql.*;
import java.io.*;

/**
 * 
 * @author www.luv2code.com
 *
 */
public class InventoryDAO {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/nbgardens_warehouse_database";
	static final String USER = "root";
	static final String PASS = "netbuilder";

	private Connection conn;
	
	public InventoryDAO() throws Exception {
		
		// connect to database
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
	}
	
	public List<Product> getAllProducts() throws Exception {
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
		int supplierID = myRs.getInt("supplierID");
		Double wholesalePrice = myRs.getDouble("wholesalePrice");
     	Double retailPrice = myRs.getDouble("retailPrice");
     	int shelvedQuantity = myRs.getInt("shelvedQuantity");
     	int reservedQuantity = myRs.getInt("reservedQuantity");
     	int porousAvailable = myRs.getInt("porousAvailable");
     	int minPorous = myRs.getInt("minPorous");
     	int nonPorousAvailalbe = myRs.getInt("nonPorousAvailable");
     	int porousReserved = myRs.getInt("porousReserved");
     	int nonPorousReserved = myRs.getInt("nonPorousReserved");
		
		Product tempProduct= new Product(productID, productName, supplierID, wholesalePrice, retailPrice, shelvedQuantity, reservedQuantity, porousAvailable, minPorous, nonPorousAvailalbe, porousReserved, nonPorousReserved);
		
		return tempProduct;
	}

	
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}

	public static void main(String[] args) throws Exception {
		
		InventoryDAO dao = new InventoryDAO();
		System.out.println(dao.searchProducts("red"));

		List<Product> prettyList = dao.getAllProducts();
		for (Product line : prettyList) {
			System.out.println("");
			
			  System.out.println(line.getproductName());
			  System.out.println(line.getshelvedQuantity());
			  System.out.println(line.getreservedQuantity());
			  System.out.println(line.getporousAvailable());
			  System.out.println(line.getnonPorousAvailable());
			}
	}
}