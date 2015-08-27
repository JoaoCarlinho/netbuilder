package Warehouse_Classes;

import java.awt.event.*;
import javax.swing.*;
import java.awt.GridLayout;


public class AccountCreationGUI extends BasePanel {
	
	/**********************************************************************Constructor*****************************************************/
	
	JFrame frame;
	JLabel l;
	JLabel l0;
	JLabel l1;
	JLabel l2;
	JLabel l3;
	JTextField t;
	JTextField t0;;
	JTextField t1;
	JTextField t2;
	JTextField t3;
	JButton b;
	
	public AccountCreationGUI(JFrame frame){
		super(frame);
		setVisible(true);
		 this.setLayout(new GridLayout(6,2));
		
	}
	
	@Override
	protected void prepareGui() {
		// TODO Auto-generated method stub
		
		l = new JLabel("Employee ID:");
		l0 = new JLabel("First Name:");
		l1 = new JLabel("Last Name:");
		l2= new JLabel("Password:");
		l3= new JLabel("repeat Password:");
		t = new JTextField(10);
		t0 = new JTextField(10);
		t1 = new JTextField(10);
		t2= new JTextField(10);
		t3= new JTextField(10);
		
		b = new JButton("Submit");
		
		add(l);
		add(t);
		add(l0);
		add(t0);
		add(l1);
		add(t1);
		add(l2);
		add(t2);
		add(l3);
		add(t3);
		add(b);	
	}

	@Override
	protected void prepareEvents() {
		// TODO Auto-generated method stub
		
	}
	



}
