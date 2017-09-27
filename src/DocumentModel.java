import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.colorchooser.AbstractColorChooserPanel;

public class DocumentModel extends JFrame implements ActionListener {

	public JTextArea area;
	public HandlerClass handler;
	public int size;
	public String textsize = "50";
	private JMenuBar menuBar;
	public JMenu text;
	public JMenuItem bold;
	public JMenuItem italic;
	public JMenuItem plain;
	public JMenuItem bai;
	public JMenuItem textthing;
	public JMenuItem printsection;
	public JMenuItem pcolor;
	public String content;
	public JMenuItem save;
	public int kavun;
	public int resizenum;
	public static boolean decorated;
	public static JMenuItem newfile;
    public DocumentModel() {
    
    	
    	
	    
    	setTitle("Kavun Sketch");
    	menu();
    	page();
    	toptoolbar();

    	toolbartwo();
    	setResizable(false);
    	
    	setUndecorated(decorated);
    	Toolkit tk = Toolkit.getDefaultToolkit();  
    	int xSize = ((int) tk.getScreenSize().getWidth());  
    	int ySize = ((int) tk.getScreenSize().getHeight());  
    	
    	//setSize(xSize, ySize);
    	setSize(600, 700);
    	
        
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	SettingClass settingObject = new SettingClass(this);
    	kavun = settingObject.resizenum;
    	System.out.println(kavun);
   
    	
    	
        setLocationRelativeTo(null);
        
        ImageIcon imageicon = new ImageIcon(getClass().getResource("window.png"));
        Image image = imageicon.getImage();
        this.setIconImage(image);
	 
	
	    
    }
    
	public void menu(){
		// menu bar
		this.menuBar = new JMenuBar();
		
		//file icons
		ImageIcon newfileicon = new ImageIcon(getClass().getResource("newfile.png"));
		ImageIcon exiticon = new ImageIcon(getClass().getResource("exit.png"));
		ImageIcon openicon = new ImageIcon(getClass().getResource("open.png"));
		ImageIcon saveicon = new ImageIcon(getClass().getResource("save.png"));
		ImageIcon saveasicon = new ImageIcon(getClass().getResource("saveas.png"));
		
		//edit icons
		ImageIcon copyicon = new ImageIcon(getClass().getResource("copy.png"));
		ImageIcon pasteicon = new ImageIcon(getClass().getResource("paste.png"));
		ImageIcon texticon = new ImageIcon(getClass().getResource("edit.png"));
		
		//page layout icons
		ImageIcon coloricon = new ImageIcon(getClass().getResource("color.png"));
		ImageIcon printericon = new ImageIcon(getClass().getResource("printer.png"));
		
		//menu bar items
		JMenu file = new JMenu("File");
		file.setFont(new Font("Arial", Font.BOLD, 20 ));
		JMenu pagelayout = new JMenu("Page"); 	
		pagelayout.setFont(new Font("Arial", Font.BOLD, 20 ));
		JMenu edit = new JMenu("Edit"); 
		edit.setFont(new Font("Arial", Font.BOLD, 20 ));
		
		//menu bar items files for file
		JMenuItem open = new JMenuItem("Open Sketch", openicon);
		newfile = new JMenuItem("New Sketch", newfileicon);
		newfile.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N,
                java.awt.Event.CTRL_MASK));
		
		save = new JMenuItem("Save Sketch", saveicon);
		save.addActionListener(this);
		JMenuItem saveas = new JMenuItem("Save As Sketch", saveasicon);
		JMenuItem exit = new JMenuItem("Exit Sketch", exiticon);
		
		
		open.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
		    	
		    } 
		});
		
		//menu bar items files for edit
		JMenuItem copy = new JMenuItem("Copy Selection", copyicon);
		JMenuItem paste = new JMenuItem("Paste Selection", pasteicon);
		this.text = new JMenu("Edit Text");
		this.textthing = new JMenuItem("Edit Size");
		
		//menu stuff for page  layout
		this.pcolor = new JMenuItem("Page color", coloricon);
		this.printsection = new JMenuItem("Print Page", printericon);
		
		
		//tool tips
		exit.setToolTipText("Exit Application");
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
	            ActionEvent.CTRL_MASK));
		
		newfile.setToolTipText("Open New File");
		open.setToolTipText("Open Another File");
		save.setToolTipText("Save Current File");
		saveas.setToolTipText("Save New File");
		copy.setToolTipText("Copy Selection");
		paste.setToolTipText("Paste Selection");
		text.setToolTipText("Edit Text Selection");
		
		//text application items
		this.bold = new JMenuItem("Bold");
		this.italic = new JMenuItem("Italic");
		this.plain = new JMenuItem("Regular Text");
		this.bai = new JMenuItem("Bold And Italic");
		
		//add text application items to text
		text.add(bold);
		text.add(italic);
		text.add(bai);
		text.add(plain);
		text.add(textthing);
		
		
		//action listeners
		open.addActionListener(this);
		save.addActionListener(this);
		
		
		
		//exit file action
		exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
            
		});
		
		copy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	
            	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        		TransferHandler transferHandler = area.getTransferHandler();
        		transferHandler.exportToClipboard(area, clipboard, TransferHandler.COPY);
            }
            
		});
		
		paste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	
            	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        		TransferHandler transferHandler = area.getTransferHandler();
        		transferHandler.importData(area, clipboard.getContents(null));
            }
            
		});
		

		//add items to menu
		//add to file
		file.add(newfile);
		file.add(open);
		file.add(exit);
		file.addSeparator();
		file.add(save);
		file.add(saveas);
		
		//add items to edit
		edit.add(copy);
		edit.add(paste);
		edit.addSeparator();
		edit.add(text);
		 file.addSeparator();
		 
		//add items to page layout
		 pagelayout.add(pcolor);
		 pagelayout.add(printsection);
		
		//add to menu bar
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(pagelayout);
        
        //set menu bar
        setJMenuBar(menuBar);
	}
	
    public final void page() {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane pane = new JScrollPane();
        area = new JTextArea();

      
        area.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        pane.getViewport().add(area);
        panel.add(pane);
        
        menu();
		this.handler = new HandlerClass();  
		size = 20;
		
		textthing.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
		    	size = handler.getnum(textsize);
		    	Font temp1 = new Font("Serif", Font.PLAIN, size );
		    	//temp1.setColor(Color.RED);
		    	area.setFont(temp1);
		    }
		    
		});
		
		bold.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
		    	area.setFont(new Font("Serif", Font.BOLD, size ));
		    }
		    
		});
		
		printsection.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
		    	area.setFont(new Font("Serif", Font.BOLD, size ));
		    	PrinterJob printJob = PrinterJob.getPrinterJob();
		    	printJob.setJobName(" TESSCO CUSTOMER CARD ");
		        //printJob.setPrintable(this);
		        if (printJob.printDialog())
		          try { 
		            printJob.print();
		          } catch(PrinterException pe) {
		            System.out.println("Error printing: " + pe);
		          }
		    	
		    }
		    
		});
		
		pcolor.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
		    	
		    	Color newColor = JColorChooser.showDialog(
	                     DocumentModel.this,
	                     "Choose Background Color",
	                     area.getBackground());
		    	
		    	
		    	if (newColor != null) {
		    	    area.setBackground(newColor);
		    	}
		    }
		    
		});
		
		italic.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
		    	area.setFont(new Font("Serif", Font.ITALIC, size ));
		    }
		    
		});
		
		plain.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
		    	area.setFont(new Font("Serif", Font.PLAIN, size ));
		    }
		    
		});
		
		bai.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
		    	area.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, size )); 
		    }
		    
		});
      
        this.add(panel);

        
    }
	public void toptoolbar(){
		
		  JToolBar toolbar = new JToolBar(JToolBar.HORIZONTAL);
		  toolbar.setFloatable(true);
		  
		  ImageIcon textcolorpic = new ImageIcon(getClass().getResource("textcolor.png"));
		 
		  ImageIcon boldicon = new ImageIcon(getClass().getResource("bold.png"));
		  ImageIcon italicicon = new ImageIcon(getClass().getResource("italic.png"));
		  ImageIcon bolditalicicon = new ImageIcon(getClass().getResource("bolditalic.png"));
		  ImageIcon normalicon = new ImageIcon(getClass().getResource("normal.png"));
		  ImageIcon wrapicon = new ImageIcon(getClass().getResource("wrap.png"));
		  ImageIcon textcoloricon = new ImageIcon(getClass().getResource("textcolor.png"));
		  
		  JButton boldbutton = new JButton(boldicon);
		  JButton textcolorbutton = new JButton(textcoloricon);
		  JButton wrapbutton = new JButton(wrapicon);
		  JButton baibutton = new JButton(bolditalicicon);
		  JButton italicbutton = new JButton(italicicon);
		  JButton textcolor = new JButton(textcolorpic);
		  JButton normalbutton = new JButton(normalicon);
		  
		  
		  //setting action listener implements setting class
		  
		  
		  
		  
		  
		  toolbar.add(boldbutton);
		  toolbar.add(italicbutton);
		  toolbar.add(baibutton);
		  toolbar.add(normalbutton);
		  toolbar.add(textcolorbutton);
		  toolbar.add(wrapbutton);
		
		 
		  textcolorbutton.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent event) {
			    	Color newColor = JColorChooser.showDialog(
		                     DocumentModel.this,
		                     "Choose Text Color",
		                     area.getBackground());
			    	
			    	
			    	if (newColor != null) {
			    	    area.setForeground(newColor);
			    	}
			    }
			    
			});
		  
		  wrapbutton.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent event) {
			    	area.setLineWrap(true);
			        area.setWrapStyleWord(true); 
			    }
			    
			});
		  
		  normalbutton.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent event) {
			    	area.setFont(new Font("Serif", Font.PLAIN, size )); 
			    }
			    
			});
		  
		  baibutton.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent event) {
			    	area.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, size )); 
			    }
			    
			});
	      
		  
		  boldbutton.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent event) {
			    	area.setFont(new Font("Serif", Font.BOLD, size ));
			    }
			    
			});
		  
		  italicbutton.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent event) {
			    	area.setFont(new Font("Serif", Font.ITALIC, size ));
			    }
			    
			});
		 
		  
		  textcolor.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent event) {
			    	Color newTextColor = JColorChooser.showDialog(
		                     DocumentModel.this,
		                     "Choose Background Color",
		                     area.getBackground());
			    	
			    	
			    	if (newTextColor != null) {
			    		//pagecreate.setColor(Color.RED) ;
			    	}
			    }
			    
			});
		  
		  this.getContentPane().add(toolbar,BorderLayout.NORTH);
		  
		  }
	public void toolbar(){
		
		  JToolBar toolbar = new JToolBar("Toolbar", JToolBar.VERTICAL);
		  toolbar.setFloatable(false);
		  this.getContentPane().add(toolbar,BorderLayout.WEST);
		  
		  }
	
	public void toolbartwo(){
		
		  JToolBar toolbartwo = new JToolBar("Toolbar", JToolBar.HORIZONTAL);
		  toolbartwo.setFloatable(true);
		  
		  ImageIcon setting = new ImageIcon(getClass().getResource("setting.png"));
		  SettingClass seting = new SettingClass(this);
		  JButton settings = new JButton(setting);
		  settings.addActionListener(seting);
		  
		  toolbartwo.add(settings);
		 
		  this.getContentPane().add(toolbartwo,BorderLayout.SOUTH);
		  
		  }
	
	
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
            	
            	
            	DocumentModel frame = new DocumentModel();
                frame.setVisible(true);
                
                
                
                newfile.addActionListener(new ActionListener() {
            	    public void actionPerformed(ActionEvent event) {
            	    	
            	    	DocumentModel frame = new DocumentModel();
                        frame.setVisible(true);
                        
            	    	
            	    }
            	    
            	});
                
                
                
            }
        });
    }
    
    public void openFile(){
    	JFileChooser fileChooser = new JFileChooser();
    	fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    	
    	int results = fileChooser.showOpenDialog(this);
    	
    	if(results == JFileChooser.CANCEL_OPTION)
    		return;
    	
    	File filename = fileChooser.getSelectedFile();
    	
    	if(filename == null || filename.getName().equals(" ")){
    		
    		JOptionPane.showMessageDialog(this, "invalid file", "this could"+
    	    		"now be resolved",	JOptionPane.ERROR_MESSAGE);
    	}else{
    		try{
    			BufferedReader opener = new BufferedReader(new FileReader(filename));
    			String text;
					while((text = (opener.readLine())) != null){
						area.append(text + "\n");
				}
			} catch(Exception e){
    			JOptionPane.showMessageDialog(this, "Error", e.toString(), JOptionPane.ERROR_MESSAGE);
    		}
    	}
    	
    }
    		
    	
    
    
    public void saveFile(){
    	JFileChooser fileChooser = new JFileChooser();
    	fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    	
    	int results = fileChooser.showSaveDialog(this);
    	
    	if(results == JFileChooser.CANCEL_OPTION)
    		return;
    	
    	File filename = fileChooser.getSelectedFile();
    	
    	if(filename == null || filename.getName().equals(" ")){
    		
    		JOptionPane.showMessageDialog(this, "invalid file", "this could"+
    	    		"now be resolved",	JOptionPane.ERROR_MESSAGE);
    	}else{
    		BufferedWriter output;
    		
    		try{
    			output = new BufferedWriter(new FileWriter(filename.getAbsolutePath()));
    			output.write(area.getText(), 0, area.getText().length());
    			output.close();
    		}catch(Exception e){
    			
    			JOptionPane.showMessageDialog(this, "Error", e.toString(), JOptionPane.ERROR_MESSAGE);
    		}
    	
    	}
    		
    	
    }
    

    


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
		
		if(e.getSource() == save){
			this.saveFile();
		}
		else{
			
			this.openFile();
		}
		
	}
}