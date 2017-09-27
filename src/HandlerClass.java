import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;



public class HandlerClass implements ActionListener{

	public int getnum(String textsize) {
		textsize = JOptionPane.showInputDialog("Enter Size Of Text");
		 int sizeof =Integer.parseInt(textsize);
		 return sizeof;
	}
	
	public int getresize() {
		
		 return 1;
	}
	
	//if(e.getSource() == save){


	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(){
	
		
	}


	
	
 
}

