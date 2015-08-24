package Warehouse_Classes;

import javax.swing.*;

import java.awt.*;

public class GUI {

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
	
	
	public GUI(){
		
		frame();
		
		
		
		
	
	}
	
	public void frame(){
		
		f = new JFrame();
		f.setVisible(true);
		f.setSize(1000, 800);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		productID = new JLabel("productID");
		productName = new JLabel("productName");
		supplierID  = new JLabel("supplierID");
		wholesalePrice  = new JLabel("wholesalePrice");
		retailPrice  = new JLabel("retailPrice");
		quantity = new JLabel("quantity");
		porousQuantity = new JLabel("porousQuantity");
		
		t = new JTextField(10);
		t0 = new JTextField(10);
		t1 = new JTextField(10);
		t2 = new JTextField(10);
		t3 = new JTextField(10);
		t4 = new JTextField(10);
		t5 = new JTextField(10);
		
		
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
		
		
		
		
	}








}
