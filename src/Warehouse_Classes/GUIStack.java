package Warehouse_Classes;

import java.util.Stack;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class GUIStack {

                public static Stack<JPanel> guiStack = new Stack<JPanel>();
                private static JFrame mainframe;
                

                public static void openWindow(JPanel newWindow) {
                                
                                if (guiStack.size() != 0){
                                	guiStack.peek().setVisible(false);
                                	mainframe.remove(guiStack.peek());
                                }
                                guiStack.push(newWindow);
                                mainframe.add(newWindow);
                                mainframe.repaint();
                                mainframe.setVisible(true);
                                System.out.println("New window added to stack");
                }
                
                public static void goBack() {
                                //guiStack.pop().setVisible(false);
                                mainframe.remove(guiStack.pop());
                                mainframe.add(guiStack.peek());
                                guiStack.peek().setVisible(true);
                                System.out.println("Window removed from Stack");

                }
                
                public static void setJFrame(JFrame frame){
                                mainframe = frame;
                }

                public static JFrame getJFrame(){
                                return mainframe;
                }
                
}

