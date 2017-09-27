import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;


public class SettingClass implements ActionListener {
	
	
	public JMenuBar settingMenuBar;
	JFrame settingframe = new JFrame();
	public int resizenum = 0;
	public JButton resizebutton;
	public JButton fullbutton;
	public JButton decorbutton;
	private DocumentModel referenceToDocumentModel;
	
	
	public SettingClass(DocumentModel x){
		referenceToDocumentModel = x;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		
		settingmenu();
		setDefaultOpen();
		toolbar();
		settingframe.setResizable(false);
		settingframe.setVisible(true);
		settingframe.setSize(143, 300);
		settingframe.setLocationRelativeTo(null);
        ImageIcon ImageIcon = new ImageIcon(getClass().getResource("window.png"));
	    Image Image = ImageIcon.getImage();
	    settingframe.setIconImage(Image);
		
	}
	
	public void settingmenu(){
		// TODO Auto-generated method stub
		this.settingMenuBar = new JMenuBar();
		
		//file icons
		ImageIcon exiticon = new ImageIcon(getClass().getResource("exit.png"));
		
		//menu bar items
		JMenu docprop = new JMenu("Setting");
		docprop.setFont(new Font("Arial", Font.BOLD, 15 ));		
	
		//menu bar items files for doc properties
		JMenuItem exit = new JMenuItem("Exit Settings", exiticon);
		
		//menu items for window prop
	
				
		//add to documents
		docprop.add(exit);
	
		settingMenuBar.add(docprop);
		
						
		//exit file action
		exit.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent event) {
		        settingframe.dispose();
		     }  
		});
		
		//resize
		
		
		settingframe.setJMenuBar(settingMenuBar);
	}
	public void toolbar(){
		
		  JToolBar toolbar = new JToolBar("Toolbar", JToolBar.HORIZONTAL);
		  toolbar.setFloatable(false);
		  
		  ImageIcon resizeicon = new ImageIcon(getClass().getResource("resize.png"));
		  resizebutton = new JButton(resizeicon);
		  resizebutton.setToolTipText("Make Application Resizeable");
		  
		  ImageIcon fullscreenicon = new ImageIcon(getClass().getResource("fullscreen.png"));
		  fullbutton = new JButton(fullscreenicon);
		  fullbutton.setToolTipText("Make Application Full Screen");
		  
		  ImageIcon decoricon = new ImageIcon(getClass().getResource("decor.png"));
		  decorbutton = new JButton(decoricon);
		  decorbutton.setToolTipText("Take off Decorations New Window");
		  
		  //setting action listener implements setting class
		  toolbar.add(resizebutton);
		  toolbar.add(fullbutton);
		  toolbar.add(decorbutton);
		  
		  final HandlerClass handlerObject = new HandlerClass();
		  resizebutton.addActionListener(new ActionListener() {
			      public void actionPerformed(ActionEvent event) {
			    	  resizenum = 1;
			    	  if(resizenum == 1){
			    	  referenceToDocumentModel.setResizable(true);
			    	  }
			    	  
				     }  
				});
		  
		  fullbutton.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent event) {
		    	Toolkit tk = Toolkit.getDefaultToolkit();  
		      	int xSize = ((int) tk.getScreenSize().getWidth());  
		      	int ySize = ((int) tk.getScreenSize().getHeight());  
		      	
		    	  resizenum = 1;
		    	  if(resizenum == 1){
		    	  referenceToDocumentModel.setSize(xSize, ySize);
		    	  }
		    	  
			     }  
			});
		  
		  decorbutton.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent event) {
		    	  resizenum = 1;
		    	  if(resizenum == 1){
		    	  referenceToDocumentModel.decorated=true;
		    	  }
		    	  
			     }  
			});
		  
		  settingframe.getContentPane().add(toolbar,BorderLayout.SOUTH);  
		  
		  }
	
	
	public void setDefaultOpen(){
		JLabel howopen = new JLabel("Set Default Open Opperation");
		JPanel panel = new JPanel();
		panel.setVisible(true);
	
		
		
		settingframe.getContentPane().add(panel,BorderLayout.WEST);
		
	}
	

}
