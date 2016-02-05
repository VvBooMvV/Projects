import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 
 * @author Harry Kim
 * 
 * <br> This program will generate a spell check like program.  It will test to see if a word exist within a program by seeing if item is contain within a treeset.  
 * a user will be allowed to type in multiple words within a line separated by spaces.  The words must only contain a-z or A-Z characters.  symbols or numbers will not be accepted.
 * ex: fly blue shoe go   a fail ex. fly blue* it's  - these will fail.  The user will then choose an item from the combobox then select add to dictionary or ignore radiobutton.
 * the user will then hit the confirm button to add to dictionary or ignore and remove from the combo box after hitting confirm.  the user has five options below.
 * they can either exit the program, read a list file of words either .txt or .bin files.  the list of txt must be separated by lines.  the binary file must be written from this program,
 * list the current or previous dictionary, write a .bin or .txt file to save the dictionary, and to clear all fields within the GUI.
 *
 */

public class Driber {
	
	//North Panel
	
	JPanel northpan = new JPanel();
	JPanel nbordpan = new JPanel();
	JButton btnspell = new JButton("Spell-check");
	JTextField txtword = new JTextField(60);
	
	//South Panel
	
	JPanel botpan = new JPanel();
	JButton btnexit = new JButton("Exit");
	JButton btnadd = new JButton("Add Dictionary");
	JButton btnlist = new JButton("List Dictionary");
	JButton btnwrite = new JButton("Write Dictionary");
	JButton btnclear = new JButton("Clear");
	
	//display panel
	JPanel dpouter = new JPanel();
	JPanel dpcombo = new JPanel();
	JPanel dpradio = new JPanel();
	JPanel dpbtn = new JPanel();
	ButtonGroup grp = new ButtonGroup();
	JRadioButton rdobtnadd = new JRadioButton("Add to Dictionary");
	JRadioButton rdobtnig = new JRadioButton("Ignore");
	JButton btnconfirm = new JButton("   Confirm   ");
	JLabel space = new JLabel("                              ");
	JLabel space2 = new JLabel("                              ");
	JLabel space1 = new JLabel("                                                                ");
	String[] listitems = {null};
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox items = new JComboBox(listitems);
	
	//data
	SpellCheckerManager scm = new SpellCheckerManager();
	String out = null;
	static int outsize = 0;
	
	
	public Driber(){
		JFrame window = new JFrame();
		window.setTitle("Spell Checker");
		window.setSize( 700,  335);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		//centers the window in the middle of screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
		
		toppanel();
		window.add(northpan, BorderLayout.NORTH);
		
		botpanel();
		window.add(botpan,BorderLayout.SOUTH);
		
		displaypanel();
		window.add(dpouter, BorderLayout.CENTER);

		
		btnconfirm.addActionListener(new ActionListener(){
		
			public void actionPerformed(ActionEvent e){
				
				if(rdobtnadd.isSelected()){
					try {
						scm.addWord((String)items.getSelectedItem());
						if(items.getSelectedItem() != null){
							JOptionPane.showMessageDialog(null, (String)items.getSelectedItem() + " was added to the Dictionary.");
						}
					
						int take = items.getSelectedIndex();
						if(take > -1)
						items.removeItemAt(take);
					} catch (DuplicateWordException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					} catch (InvalidSpellingException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
				else if(rdobtnig.isSelected()){
					int take = items.getSelectedIndex();
					if(take > -1)
					items.removeItemAt(take);
				}
				else{
					JOptionPane.showMessageDialog(null, " Please Select an Option");
				}
				
			}
		});
			
		btnspell.addActionListener(new ActionListener(){
			@SuppressWarnings({ "unchecked"})
			public void actionPerformed(ActionEvent e){
				
				try {
					
					ArrayList<String> stuff = scm.checkWords(txtword.getText());
					listitems = txtword.getText().split("\\s+");
					
					
					if(stuff == null){
						JOptionPane.showMessageDialog(null, " All word(s) found in Dictionary");
					
					}
					else{
						items.removeAllItems();
						for(int i = 0; i < stuff.size(); i++){

							items.addItem(stuff.get(i));
						}
					}
				
				} catch (InvalidSpellingException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					
				}
				
				
			}
		});
		
		btnadd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFileChooser chooser = new JFileChooser();
				
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){

					try {
						if(scm.readDictionary(chooser.getSelectedFile())){
							JOptionPane.showMessageDialog(null, "File Read Successfully.");
							
						}
					} catch (DuplicateWordException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					} catch (InvalidSpellingException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			
			}
		});
		
		btnlist.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int pane = JOptionPane.showConfirmDialog(null, "This may take a few minutes.  \n\nClick [OK] to add a NEW dictionary,\nor [Cancel] to display previous dictionary.", "Loading...", JOptionPane.OK_CANCEL_OPTION);  
				
				if(pane == JOptionPane.OK_OPTION){
					
					if(out == null){
						
						out = scm.listDictionary();
						outsize = scm.getlistsize();
					}
					
					if(outsize < scm.getlistsize()){
						
						out = scm.listDictionary();
						outsize = scm.getlistsize();
					}
					
				}
				

				
				JTextArea textArea = new JTextArea(out);
				JScrollPane scrollPane = new JScrollPane(textArea);  
				textArea.setLineWrap(true);  
				textArea.setWrapStyleWord(true); 
				textArea.setEditable(false);
				scrollPane.setPreferredSize( new Dimension( 150, 400 ) );

				JOptionPane.showMessageDialog(null, scrollPane, "List of Words", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
		btnwrite.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				JFileChooser chooser = new JFileChooser();
				
				
				if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
					try {
						scm.writeDictionary(chooser.getSelectedFile());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}

				}
			}
		});
		
		btnclear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				txtword.setText("");
				items.removeAllItems();
				
			}
		});
		
		btnexit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
	}

	

	
	public void displaypanel(){
		
		grp.add(rdobtnadd);
		grp.add(rdobtnig);
		items.setPreferredSize(new Dimension(280, 20));
		
		dpouter.setPreferredSize(new Dimension(690, 200));
		dpouter.setBorder(BorderFactory.createTitledBorder(null, "Options:"));
		
		dpcombo.setPreferredSize(new Dimension(290, 100));
		dpcombo.setBorder(BorderFactory.createTitledBorder(null, "Select Word:"));
		dpcombo.add(space);
		dpcombo.add(items);
		
		dpradio.setPreferredSize(new Dimension(220, 100));
		dpradio.setBorder(BorderFactory.createTitledBorder(null, "Select Option:"));
		dpradio.add(space1);
		dpradio.add(rdobtnadd);
		dpradio.add(rdobtnig);
		
		dpbtn.setPreferredSize(new Dimension(140, 100));
		dpbtn.setBorder(BorderFactory.createTitledBorder(null,"Finish"));
		dpbtn.add(space2);
		dpbtn.add(btnconfirm);
		
		
		dpouter.add(dpcombo);
		dpouter.add(dpradio);
		dpouter.add(dpbtn);
	}
	
	public void toppanel(){
		
		northpan.setPreferredSize(new Dimension(690, 100));
		nbordpan.setBorder(BorderFactory.createTitledBorder(null, "Enter Word / Words to Check:"));
		nbordpan.setPreferredSize(new Dimension(680, 90));
		
		nbordpan.add(txtword);
		nbordpan.add(btnspell);
		
		northpan.add(nbordpan);
		
		
	}
	
	
	
	public void botpanel(){
		botpan.setPreferredSize(new Dimension(690, 40));
		botpan.setBorder(BorderFactory.createTitledBorder(null,""));
		
		botpan.add(btnexit);
		botpan.add(btnadd);
		botpan.add(btnlist);
		botpan.add(btnwrite);
		botpan.add(btnclear);
		
	}

	
	public static void main(String[] args) {
		
		new Driber();
		
		

	}

}
