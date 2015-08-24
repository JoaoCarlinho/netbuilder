package Warehouse_Classes;

//This class used for my benefit to familiarize myself with SQL*************************************************



import java.sql.*;



public class NBGardensWarehouse 
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/nbgardens_warehouse_database";
	static final String USER = "root";
	static final String PASS = "netbuilder";
	 
	 
	 
	public void accessBD() 
	{
		Connection conn = null;
		Statement stmt = null;
		 
		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 
			 
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql2 = "SELECT id, firstName, lastName FROM customer";
			ResultSet rs = stmt.executeQuery(sql2);
			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				System.out.println("ID: " + id + ", firstName: " + firstName + ", lastName: " + lastName);
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
			  System.out.println("Goodbye!");
	} 
}

