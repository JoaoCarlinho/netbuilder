package Warehouse_Classes.unused_classes;
	import javax.swing.JFrame;


	public class SingleWindowGUIMain {

		public static void main(String[] args) {
				
			JFrame mainFrame = new JFrame("WINDOW");
			GuiPanel mainPanel = new GuiPanel();
			
			mainFrame.setSize(400, 400);
			mainFrame.add(mainPanel);
			mainFrame.setVisible(true);
		}
	}
