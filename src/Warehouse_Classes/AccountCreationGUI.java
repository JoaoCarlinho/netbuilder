package Warehouse_Classes;

import java.awt.event.*;
import javax.swing.*;

//This class was used to practice creating GUI******************************************************************

public class AccountCreationGUI {
				
	
	JFrame f = new JFrame("Employee Account Creation");
	JLabel l = new JLabel("Employee ID:");
	JLabel l0 = new JLabel("First Name:");
	JLabel l1 = new JLabel("Last Name:");
	JLabel l2= new JLabel("Password:");
	JLabel l3= new JLabel("repeat Password:");
	JTextField t = new JTextField(10);
	JTextField t0 = new JTextField(10);
	JTextField t1 = new JTextField(10);
	JTextField t2= new JTextField(10);
	JTextField t3= new JTextField(10);
	JButton b = new JButton("Submit");


	public AccountCreationGUI(){
		frame();	
	}
	
	public void frame(){
		f.setSize(600,400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		JPanel p = new JPanel();
		p.add(l);
		p.add(t);
		p.add(l0);
		p.add(t0);
		p.add(l1);
		p.add(t1);
		p.add(l2);
		p.add(t2);
		p.add(l3);
		p.add(t3);
		p.add(b);
		
		f.add(p);
	}


}
