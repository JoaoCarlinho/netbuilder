package Warehouse_Classes;

import javax.swing.*;
import java.awt.*;

public class ProductSearchApp extends JFrame {
	private JPanel contentPane;
	
	
	public static void main(String[] args)	{
		EventQueue.invokeLater(new Runnable()	{
			public void run() {
				try {
					ProductSearchApp frame = new ProductSearchApp();
					frame.setVisible(true);
				}	catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
