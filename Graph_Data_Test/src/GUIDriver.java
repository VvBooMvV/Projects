import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * 
 * @author Harry Kim
 * This program will generate a friend program.  it will then use a graph with vertices and edges to organize all list.  the graph will be stored as Edge<Friend, Friend>
 * and the Friend class will store all the information about that friend.  all the organization will happen in the FriendGraph class.  the Data manager will print out all information
 * from the friend graph when invoked.  The program must load participant and friend file.  the participant fill with store information per line by firstname;lastname;city.  the 
 * friend file with store information of firstname;lastname;numberoffriends;friendfirst;friendlast;.......  after the files have been open the gui will generate and fill the boxes accordingly
 * the user then will be allowed to choose a profile.  if the user wants to see the list of frineds of friends hit the show friends of friends button on the top right.  the user can
 * add new friends from the combo box selection on the bottom right.  when adding a friend it will add a new friend to their list in the FriendGraph class, and print out
 * all the friends in the proper boxes and removing the new friend from the combobox.  adding a new profile will genereate a new list of profiles to choose from on the top left and allow user
 * to add new friends accordingly.
 *
 */

public class GUIDriver {
	
	DataManager dm = new DataManager();
	//top left
	JPanel leftouter = new JPanel();
	JPanel leftpan = new JPanel();
	JPanel lefttpan = new JPanel();
	JPanel leftlblpan = new JPanel();
	JPanel leftlblpan2 = new JPanel();
	JPanel leftareapan = new JPanel();
	
	JLabel lblpfname = new JLabel("First Name  ");
	JLabel lblplname = new JLabel("Last Name   ");
	JLabel lblphome= new JLabel("Hometown   ");
	JTextField txtpfname = new JTextField(18);
	JTextField txtplname = new JTextField(18);
	JTextField txtphome = new JTextField(18);
	
	JTextArea friendarea = new JTextArea(14,27);
	JScrollPane sp = new JScrollPane(friendarea); 
	String[] prolist = {null};
	JComboBox<String> cboprofile = new JComboBox<String>(prolist);
	
	//top right
	JPanel rightouter = new JPanel();
	JPanel rightfofpan = new JPanel();
	JPanel rightaddpan = new JPanel();
	JPanel rightfofouter = new JPanel();
	JPanel rightaddouter = new JPanel();
	
	JButton btnshowfriend = new JButton("Show Friends of Friends");
	JButton btnaddfriend = new JButton("Add Friend");
	JTextArea fofarea = new JTextArea(14,26);
	JScrollPane fofsp = new JScrollPane(fofarea);
	String[] addfriendlist = {null};
	JComboBox<String> cboaddfriend = new JComboBox<String>(addfriendlist);

	//bottom panel
	JPanel bottomouter = new JPanel();
	JPanel bottompan = new JPanel();
	JPanel blblpan = new JPanel();
	JPanel bbtnpan = new JPanel();
	JLabel lblfname = new JLabel("First Name  ");
	JLabel lbllname = new JLabel("Last Name  ");
	JLabel lblhome = new JLabel("Hometown  ");
	JTextField txtfname = new JTextField(20);
	JTextField txtlname = new JTextField(20);
	JTextField txthome = new JTextField(20);
	JButton btnaddpro = new JButton("Add New Profile");
	JButton btnexit = new JButton("     Exit    ");
	JPanel btnpan = new JPanel();
	JPanel fixpan = new JPanel();
	JPanel fix2pan = new JPanel();
	
	//add all three pans
	JPanel outpan = new JPanel();
	JPanel titlepan = new JPanel();
	JLabel title = new JLabel("Connections");
	
	private static File participantsFile, friendsFile, filename;
	Vector<String> peoples = new Vector<String>();
	int pepsize = 0;
	
	
	public GUIDriver(){
		
		JFrame window = new JFrame();
		window.setTitle("FakeBook");
		window.setSize( 860,  720);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
		
		outpan.setPreferredSize(new Dimension(850, 700));
		title.setFont(new Font("Serif", Font.BOLD, 22));
		titlepan.setPreferredSize(new Dimension(800, 30));
		titlepan.add(title);
		
		leftpanel();
		rightpanel();
		bottompanel();

		outpan.add(titlepan);
		outpan.add(leftouter);
		outpan.add(rightouter);
		outpan.add(bottomouter);
		window.add(outpan, BorderLayout.CENTER);
		
		try {
			getFileNames("Participants.txt");
			participantsFile = filename;
			dm.populateParticipants(participantsFile);
			peoples = dm.vectorOfParticipants();
			pepsize = peoples.size();
			
			getFileNames("Friends.txt");
			friendsFile = filename;
			dm.populateFriends(friendsFile);
				
		
			
		} catch (Exception e) {
			
		}
		if(!dm.isFileOpen()){
			JOptionPane.showMessageDialog(null, "Reboot Program.  Both Participant and Friend File Must be Opened.");
		}
		if(dm.isFileOpen()){
			cboprofile.removeAllItems();
			for(String a : peoples){
				cboprofile.addItem(a);
			}
			
			printgui();
			
		}
		
		
		cboprofile.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange() == ItemEvent.SELECTED){
					
					printgui();

					
				}
			}
		});
	
		btnaddfriend.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dm.addFriend(cboprofile.getItemAt(cboprofile.getSelectedIndex()), cboaddfriend.getItemAt(cboaddfriend.getSelectedIndex()));
				printgui();
				//show friends of friends
				ArrayList<String> allother = dm.friendsOfFriends(txtpfname.getText(), txtplname.getText(), txtphome.getText());
				fofarea.setText("");
				String fofa = "";
				for(String c: allother){
					fofa += c + "\n";
				}
				fofarea.setText(fofa);
			}
		});
		
		btnaddpro.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(txtfname.getText().trim().equals("")){
					txtfname.setText("");
					JOptionPane.showMessageDialog(null, "Please Add a Profile First Name");
				}
				else if(txtlname.getText().trim().equals("")){
					txtlname.setText("");
					JOptionPane.showMessageDialog(null, "Please Add a Profile Last Name");
				}
				else if(txthome.getText().trim().equals("")){
					txthome.setText("");
					JOptionPane.showMessageDialog(null, "Please Add a Profile Hometown");
				}
				else{
					dm.addParticipant(txtfname.getText(), txtlname.getText(), txthome.getText());
					peoples = dm.vectorOfParticipants();
					pepsize = peoples.size();
					cboprofile.removeAllItems();
					for(String a : peoples){
						cboprofile.addItem(a);
					}
					printgui();
					txtfname.setText("");
					txtlname.setText("");
					txthome.setText("");
				}
			}
		});
		
		btnshowfriend.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//show friends of friends
				ArrayList<String> allother = dm.friendsOfFriends(txtpfname.getText(), txtplname.getText(), txtphome.getText());
				fofarea.setText("");
				String fofa = "";
				for(String c: allother){
					fofa += c + "\n";
				}
				fofarea.setText(fofa);
			}
		});
		btnexit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
	}
	/**
	 * this will print out all the combo boxes and fill and text area that needs to be filled.  invokes when selecting a friend, adding a friend, or adding profile.
	 */
	public void printgui(){
		String dudeget = cboprofile.getItemAt(cboprofile.getSelectedIndex());
		ArrayList<String> pro = dm.getProfile(dudeget);
		//profile info
		txtpfname.setText(pro.get(0)); 
		txtplname.setText(pro.get(1));
		txtphome.setText(pro.get(2));
		//populate friend list
		ArrayList<String> faadd = dm.listFriends(dudeget);
		String fas = "";
		for(String b : faadd){
			fas += (b + "\n");
		}
		friendarea.setText(fas);
		fofarea.setText("");
		
		//add friend combo box
		Vector<String> allparticipant = dm.vectorOfParticipants();
	
		if(allparticipant.containsAll(peoples)){
			
			allparticipant.remove(cboprofile.getItemAt(cboprofile.getSelectedIndex()));
			
			for(int j = 0; j < faadd.size(); j++){
				
				allparticipant.remove(faadd.get(j));
				
			}
		
		}
		cboaddfriend.removeAllItems();
		for(String a : allparticipant){
			cboaddfriend.addItem(a);
		}
		

	}
	/**
	 * this will read the files when program starts
	 * @param in the string of the file location
	 * @throws Exception if file is not selected this will be thrown.
	 */
	public static void getFileNames(String in) throws Exception 
	{
		JFileChooser chooser = new JFileChooser();
		int status;

		chooser.setDialogTitle("Select Input File - " + in);
		status = chooser.showOpenDialog(null);

		if(status == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				filename = chooser.getSelectedFile();
				// readFile();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				JOptionPane.showMessageDialog(null, "There is a problem with this file", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}


	}
	
	public void leftpanel(){
		leftouter.setPreferredSize(new Dimension(430,420));
		//main
		leftpan.setPreferredSize(new Dimension(400,410));
		leftpan.setBorder(BorderFactory.createTitledBorder(null, "Choose Profile"));
		
		
		//top choices
		lefttpan.setPreferredSize(new Dimension(390, 115));
		cboprofile.setPreferredSize(new Dimension(300, 20));
		lefttpan.add(cboprofile);

		//labels left
		
		leftlblpan.setPreferredSize(new Dimension(130,100));
		lblpfname.setFont(new Font("Serif", Font.BOLD, 14));
		lblpfname.setFont(new Font("Serif", Font.BOLD, 14));
		lblpfname.setFont(new Font("Serif", Font.BOLD, 14));
		leftlblpan.add(lblpfname);
		leftlblpan.add(lblplname);
		leftlblpan.add(lblphome);
		//labels right
		leftlblpan2.setPreferredSize(new Dimension(245, 110));
		txtpfname.setEditable(false);
		txtplname.setEditable(false);
		txtphome.setEditable(false);
		
		leftlblpan2.add(txtpfname);
		leftlblpan2.add(txtplname);
		leftlblpan2.add(txtphome);
		lefttpan.add(leftlblpan);
		lefttpan.add(leftlblpan2);
		
		//area
		leftareapan.setPreferredSize(new Dimension(340, 240));
		leftareapan.add(sp);
		
		leftpan.add(lefttpan);
		leftpan.add(leftareapan);
		leftouter.add(leftpan);
	
	}
	
	public void rightpanel(){
	
		rightouter.setPreferredSize(new Dimension(360, 420));
		rightfofouter.setPreferredSize(new Dimension(340, 300));
		rightfofpan.setPreferredSize(new Dimension(330, 290));
		rightfofpan.setBorder(BorderFactory.createTitledBorder(null, "Friends of Friends"));
		rightfofpan.add(btnshowfriend);
		rightfofpan.add(fofsp);
		rightfofouter.add(rightfofpan);
		
		rightaddouter.setPreferredSize(new Dimension(340, 90));
		rightaddpan.setPreferredSize(new Dimension(330, 80));
		rightaddpan.setBorder(BorderFactory.createTitledBorder(null, "Add A Friend"));
		cboaddfriend.setPreferredSize(new Dimension(200,20));
		rightaddpan.add(cboaddfriend);
		rightaddpan.add(btnaddfriend);
		rightaddouter.add(rightaddpan);
		
		
		rightouter.add(rightfofouter);
		rightouter.add(rightaddouter);

	}
	

	
	
	public void bottompanel(){
		lblfname.setFont(new Font("Serif", Font.BOLD, 14));
		lbllname.setFont(new Font("Serif", Font.BOLD, 14));
		lblhome.setFont(new Font("Serif", Font.BOLD, 14));
		
		//outer stuff
		bottomouter.setPreferredSize(new Dimension(740, 210));
		bottompan.setPreferredSize(new Dimension(730, 160));
		bottompan.setBorder(BorderFactory.createTitledBorder(null, "Create New Profile"));
		//add profile button
		bbtnpan.setPreferredSize(new Dimension(150,90));
		btnpan.setPreferredSize(new Dimension(140, 30));
		
		bbtnpan.add(btnpan);
		bbtnpan.add(btnaddpro);
		//labels and textboxes
		blblpan.setPreferredSize(new Dimension(340, 90));
		fixpan.setPreferredSize(new Dimension(300, 10));
		
		blblpan.add(fixpan);
		blblpan.add(lblfname);
		blblpan.add(txtfname);
		blblpan.add(lbllname);
		blblpan.add(txtlname);
		blblpan.add(lblhome);
		blblpan.add(txthome);
		
		//add panels to and button to larger panel
		fix2pan.setPreferredSize(new Dimension(300, 40));
		fix2pan.add(btnexit);
	
		bottompan.add(blblpan);
		bottompan.add(bbtnpan);

		
		bottomouter.add(bottompan);
		bottomouter.add(fix2pan);

		
		
	}

	public static void main(String[] args) {
		
		new GUIDriver();

	}

}
