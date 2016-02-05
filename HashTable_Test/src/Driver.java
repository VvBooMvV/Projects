import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 
 * @author Harry Kim
 * 
 * This program will allow a user to add names, phone number, and address for a specific person.
 * The add button will allow a user to add information in regardless of name or address.  the Phone number is the most important information.
 * when the phone number is added it will place the information in a set of buckets and chains allow the data to be stored in a specific hashtable.
 * The reverse look up button will take the information from the phone list on the bottom left and retrieve each information for the corresponding 
 * phone number and print it out in the textarea on the right.  The read file button will allow a user to retrieve a list of information that is saved on
 * the hard drive from a txt file.  the format of the file must read [firstname lastname phone addresss] inorder for the readfile to work properly.
 * the write file will take all of the information from the hashtable and place each number into a file the user chooses.  if any information is missing 
 * the writer will input a nullF for first name, nullL for last name, and nullA for address.  (this is to insure the information can be properly read for
 * the read file button.  the exit button will shut the program down.  if a user tries to re-read the same file.  it will throw an exception for each number
 * that already exist.
 * 
 * the driver class will generate the gui for the program and run each method from the addressbookutility class.
 *
 */
public class Driver {
 
	//north panel
	JPanel addpan = new JPanel();
	JLabel lblfn = new JLabel(" First Name");
	JLabel lblln = new JLabel(" Last Name");
	JLabel lblphone = new JLabel(" Phone Number");
	JLabel lbladdy = new JLabel(" Address");
	
	JTextField txtfn = new JTextField(15);
	JTextField txtln = new JTextField(15);
	JTextField txtphone = new JTextField(15);
	JTextArea txtaddy = new JTextArea(15,5);
	
	JButton btnadd = new JButton("  Add  ");
	JPanel txtpan = new JPanel(new GridLayout(3,2));
	JPanel addypan = new JPanel(new GridLayout(1,2));
	JPanel outerpan = new JPanel();
	
	//center panel
	JPanel outpan = new JPanel();
	JPanel revpan = new JPanel();
	JPanel phonepan = new JPanel(new GridLayout(1,1));
	JPanel namepan = new JPanel(new GridLayout(1,1));
	
	JTextArea txtoutphone = new JTextArea(15,5);
	JTextArea txtoutname = new JTextArea(15,5);
	
	JButton btnreverse = new JButton("Reverse Look Up");

	//south panel
	
	JPanel btnpan = new JPanel();
	JButton btnread = new JButton(" Read Text File ");
	JButton btnwrite = new JButton(" Write Text File ");
	JButton btnexit = new JButton(" Exit ");
	AddressBookUtility abu = new AddressBookUtility();

	String 	fn = "",
			ln = "",
			ph = "",
			ad = "";
	/**
	 * constructor for the gui
	 */
	public Driver(){
		JFrame window = new JFrame();
		window.setTitle("Address Book");
		window.setSize( 700,  720);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		northpanel();
		window.add(outerpan, BorderLayout.NORTH);
		
		centerpanel();
		window.add(outpan,BorderLayout.CENTER);
		
		southpanel();
		window.add(btnpan, BorderLayout.SOUTH);
		
		//button listeners
		btnadd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			
				
				fn = txtfn.getText();
				ln = txtln.getText();
				ph = txtphone.getText();
				ad = txtaddy.getText();
				
				
				try {
					if(ph.equals("")){
						JOptionPane.showMessageDialog(null, "Please add a Phone Number (xxx)xxx-xxxx ");
					}
					else{
						abu.add(fn, ln, ph, ad);
						
					}
					
				} catch (InvalidKeyException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (KeyInUseException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
				
				txtoutphone.setText(abu.phoneout());
				txtoutname.setText("");
				
			}
		});
		
		btnreverse.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String out = "";
				String[] split;
				split = txtoutphone.getText().split("\\n");
				for(int i = 0; i< split.length; i++){
					try {
						if(abu.reverseLookup(split[i]).equals(", ") || abu.reverseLookup(split[i]).equals("nullL, nullF")){
							out += "Record Not Located\n";
						}
						else{
							
								out += abu.reverseLookup(split[i]) + '\n';
						}
					} catch (InvalidKeyException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Number Does Not Exist");
					}
					
				}
				txtoutname.setText(out);
				
				
			}
		});
		
		btnread.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFileChooser chooser = new JFileChooser();
				
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
				abu.readFile(chooser.getSelectedFile());
				}
				txtoutphone.setText(abu.phoneout());
				
			}
		});
		
		btnwrite.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				JFileChooser chooser = new JFileChooser();

				
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					abu.writeToFile(chooser.getSelectedFile());
				}
				
			}
		});
		
		btnexit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
	}
	
	/**
	 * this builds the south panel
	 */
	public void southpanel(){
		btnpan.setPreferredSize(new Dimension(500, 40));
		btnpan.add(btnread);
		btnpan.add(btnwrite);
		btnpan.add(btnexit);
	}
	/**
	 * builds the center panel for output
	 */
	public void centerpanel(){
		outpan.setPreferredSize(new Dimension(600, 350));
		revpan.setBorder(BorderFactory.createTitledBorder(null, "Reverse Phone Look Up"));
		revpan.setPreferredSize(new Dimension(590, 345));
		
		phonepan.setBorder(BorderFactory.createTitledBorder(null, "Phone Number(s) (XXX)XXX-XXXX"));
		phonepan.setPreferredSize(new Dimension(250,280));
		phonepan.add(txtoutphone);
		
		namepan.setBorder(BorderFactory.createTitledBorder(null, "Name (Last, First)"));
		namepan.setPreferredSize(new Dimension(320,280));
		namepan.add(txtoutname);
		
		revpan.add(phonepan);
		revpan.add(namepan);
		revpan.add(btnreverse);
		
		outpan.add(revpan);
	}
	/**
	 * Builds the North Panel for add information
	 */
	public void northpanel(){
		
		outerpan.setPreferredSize(new Dimension(500,290));
		addpan.setBorder(BorderFactory.createTitledBorder(null, "Add Information"));
		
		addpan.setPreferredSize(new Dimension(490,280));
		txtpan.setPreferredSize(new Dimension(480,120));
		addypan.setPreferredSize(new Dimension(480, 90));
		
		txtpan.add(lblfn);
		txtpan.add(txtfn);
		txtpan.add(lblln);
		txtpan.add(txtln);
		txtpan.add(lblphone);
		txtpan.add(txtphone);
		
		addypan.add(lbladdy);
		addypan.add(txtaddy);
		
		addpan.add(txtpan);
		addpan.add(addypan);
		addpan.add(btnadd);
		outerpan.add(addpan);
	}
	
	
	/**
	 * this is the main method that calls the sudoku GUI driver constructor.
	 * @param args
	 */
	public static void main (String[] args){
		new Driver();

//		double an = 0, 
//				x = 0, 
//				y = 7,
//				h = .001;
//			
//		for(int i = 0; i < 1000; i++){
//			an = y + .001 * ((12*x*x) - (3 * x * x * y));
//			x += h;
//			y = an;
//			System.out.println(x - .001 + "  "+ an);
//			
//		}
		
	}
}
