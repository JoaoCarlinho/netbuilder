package Warehouse_Classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class InventoryGUI  {
	
	JFrame f;
	JLabel productID;
	JLabel productName;
	JLabel supplierID;
	JLabel wholesalePrice;
	JLabel retailPrice;
	JLabel quantity;
	JLabel porousQuantity;
	
	JTextField t;
	JTextField t0;
	JTextField t1;
	JTextField t2;
	JTextField t3;
	JTextField t4;
	JTextField t5;
	
	ResultSet rs;
	

		
		
	

	public InventoryGUI(){
		
		frame();
		
	
	}
	
	public void frame(){
		InventoryTable.getProductsFromDatabase();
		
		rs = InventoryTable.rs;
		
		f = new JFrame();
		f.setSize(1200, 800);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		productID = new JLabel("productID");
		productName = new JLabel("productName");
		supplierID  = new JLabel("supplierID");
		wholesalePrice  = new JLabel("wholesalePrice(£)");
		retailPrice  = new JLabel("retailPrice(£)");
		quantity = new JLabel("quantity");
		porousQuantity = new JLabel("porousQuantity");
		
		t = new JTextField(7);
		t0 = new JTextField(10);
		t1 = new JTextField(7);
		t2 = new JTextField(6);
		t3 = new JTextField(7);
		t4 = new JTextField(5);
		t5 = new JTextField(4);
		
		
		JPanel p = new JPanel();
		
		p.add(productID);
		p.add(t);
		p.add(productName);
		p.add(t0);
		p.add(supplierID);
		p.add(t1);
		p.add(wholesalePrice);
		p.add(t2);
		p.add(retailPrice);
		p.add(t3);
		p.add(quantity);
		p.add(t4);
		p.add(porousQuantity);
		p.add(t5);
		
		f.add(p);
		
		
		//getProductsFromDatabase();
		try{
			
			
			t.setText(Integer.toString(InventoryTable.productID));
			t0.setText(rs.getString("productName"));
			t1.setText(String.valueOf(rs.getInt("suppliertID")));
			t2.setText(String.valueOf(rs.getDouble("wholesalePrice")));
			t3.setText(String.valueOf(rs.getDouble("retailPrice")));
			t4.setText(String.valueOf(rs.getInt("quantity")));
			t5.setText(String.valueOf(rs.getInt("porousQuantity")));
		
			rs.close();
			
		}
		
		catch(NullPointerException ex){
			
		} catch (SQLException e) {
			
			//e.printStackTrace();
		}
		
		f.setVisible(true);
	}

	

}
