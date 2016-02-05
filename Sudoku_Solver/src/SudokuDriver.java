import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author Harry Kim
 * 
 * This program is for Sudoku Game 9x9 Board.  The user will be allowed to add values to the row and column based on the rules of Sudoku.
 * 
 * starting a new game.  the user will open a sudokufile.txt and it will generate a random assorted value for each box.  (it will start a new game.)
 * 
 * Enter Value Box will allow the user to place a value between 1-9.  if the number is not correct it will generate an error message.
 * 
 * the display possible option will show you a possible answer to place in the box.
 * 
 * it will generate all of these in the Sudoku Board Manager class.
 * 
 * 
 *
 */
public class SudokuDriver {
	
	SudokuBoardManager sbm = new SudokuBoardManager();
	/**
	 * this is the driver that generates the game GUI
	 */
	public SudokuDriver(){
		JFrame window = new JFrame();
		window.setTitle("Sudoku");
		window.setSize( 375,  450);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		rowpanel();
		window.add(rowpan,  BorderLayout.WEST);
		
		columnpanel();
		window.add(colpan, BorderLayout.NORTH);
		
		gridpanel();
		window.add(centerpan, BorderLayout.CENTER);
		
		bottompanel();
		window.add(southpanel, BorderLayout.SOUTH);
		
		btnenter.setMnemonic(KeyEvent.VK_E);
		btndisplay.setMnemonic(KeyEvent.VK_D);
		btnnewgame.setMnemonic(KeyEvent.VK_N);
		btnexit.setMnemonic(KeyEvent.VK_X);
		
		/**
		 * Enter Button Action Listener will send the value to the Sudoku Board Manager Class to test the value added.  then print the value in to box if correct value.
		 */
		btnenter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//enters the value into the data array to be printed out
			
				try {
					String sr, sc, sv;
					int row, col, value;
					
					sr = txtenterrow.getText();
					sc = txtentercol.getText();
					sv = txtenterval.getText();
					
					if(txtenterrow.getText().equals("")){
						throw new ValueNotValidException("Please Add a Value to the Row Box");
					}
					else if(txtentercol.getText().equals("")){
						throw new ValueNotValidException("Please Add a Value to the Column Box");
					}
					else if(txtenterval.getText().equals("")){
						throw new ValueNotValidException("Please Add a Value to the Value Box");
					}
					else{
					
					row = Integer.parseInt(sr);
					col = Integer.parseInt(sc);
					value = Integer.parseInt(sv);
					
					sbm.setValueAt(row, col, value);
					}
				} 
				catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} 
				
				
				String[][] output = new String[9][9];
				try {
					for(int r = 1; r < 10; r++){
						for(int c = 1; c <10; c++){
							if (sbm.getValueAt(r, c) == 0){
								output[r-1][c-1] = null;
							}
							else{
							output[r-1][c-1] = Integer.toString(sbm.getValueAt(r, c));
							}
						}
					}
					
				} catch (InputOutOfRangeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txt11.setText(output[0][0]);
				txt12.setText(output[0][1]);
				txt13.setText(output[0][2]);
				txt14.setText(output[0][3]);
				txt15.setText(output[0][4]);
				txt16.setText(output[0][5]);
				txt17.setText(output[0][6]);
				txt18.setText(output[0][7]);
				txt19.setText(output[0][8]);
				
				txt21.setText(output[1][0]);
				txt22.setText(output[1][1]);
				txt23.setText(output[1][2]);
				txt24.setText(output[1][3]);
				txt25.setText(output[1][4]);
				txt26.setText(output[1][5]);
				txt27.setText(output[1][6]);
				txt28.setText(output[1][7]);
				txt29.setText(output[1][8]);
				
				txt31.setText(output[2][0]);
				txt32.setText(output[2][1]);
				txt33.setText(output[2][2]);
				txt34.setText(output[2][3]);
				txt35.setText(output[2][4]);
				txt36.setText(output[2][5]);
				txt37.setText(output[2][6]);
				txt38.setText(output[2][7]);
				txt39.setText(output[2][8]);
				
				txt41.setText(output[3][0]);
				txt42.setText(output[3][1]);
				txt43.setText(output[3][2]);
				txt44.setText(output[3][3]);
				txt45.setText(output[3][4]);
				txt46.setText(output[3][5]);
				txt47.setText(output[3][6]);
				txt48.setText(output[3][7]);
				txt49.setText(output[3][8]);
				
				txt51.setText(output[4][0]);
				txt52.setText(output[4][1]);
				txt53.setText(output[4][2]);
				txt54.setText(output[4][3]);
				txt55.setText(output[4][4]);
				txt56.setText(output[4][5]);
				txt57.setText(output[4][6]);
				txt58.setText(output[4][7]);
				txt59.setText(output[4][8]);
				
				txt61.setText(output[5][0]);
				txt62.setText(output[5][1]);
				txt63.setText(output[5][2]);
				txt64.setText(output[5][3]);
				txt65.setText(output[5][4]);
				txt66.setText(output[5][5]);
				txt67.setText(output[5][6]);
				txt68.setText(output[5][7]);
				txt69.setText(output[5][8]);
				
				txt71.setText(output[6][0]);
				txt72.setText(output[6][1]);
				txt73.setText(output[6][2]);
				txt74.setText(output[6][3]);
				txt75.setText(output[6][4]);
				txt76.setText(output[6][5]);
				txt77.setText(output[6][6]);
				txt78.setText(output[6][7]);
				txt79.setText(output[6][8]);
				
				txt81.setText(output[7][0]);
				txt82.setText(output[7][1]);
				txt83.setText(output[7][2]);
				txt84.setText(output[7][3]);
				txt85.setText(output[7][4]);
				txt86.setText(output[7][5]);
				txt87.setText(output[7][6]);
				txt88.setText(output[7][7]);
				txt89.setText(output[7][8]);
				
				txt91.setText(output[8][0]);
				txt92.setText(output[8][1]);
				txt93.setText(output[8][2]);
				txt94.setText(output[8][3]);
				txt95.setText(output[8][4]);
				txt96.setText(output[8][5]);
				txt97.setText(output[8][6]);
				txt98.setText(output[8][7]);
				txt99.setText(output[8][8]);
				
		}
		});
		
		/**
		 * this will display a possible value in the grey box below.  testing the displayPossibleValue() method in the SudokuBoardManager class.
		 */
		btndisplay.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//shows possible outputs for each row and column
				String row, col;
				int r, c;
				
				row = txtdisplayrow.getText();
				col = txtdisplaycol.getText();
				

				
				
				
				
				try {
					if(txtdisplayrow.getText().equals("")){
						throw new InputOutOfRangeException("Please Add a Value to the Row Box");
					}
					else if(txtdisplaycol.getText().equals("")){
						throw new InputOutOfRangeException("Please Add a Value to the Column Box");
					}
					else{
						r = Integer.parseInt(row);
						c = Integer.parseInt(col);
					
						String disout = "";
						int[] value = sbm.displayPossibleValues(r, c);
						
						
						for (int m = 0; m< value.length; m++){
							disout += value[m] + "  ";
						}
				
					
						txtdisplayvalue.setText(disout);
						
					}
				} catch (InputOutOfRangeException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		/**
		 * this listener will generate a new game file.
		 */
		btnnewgame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					readFile();
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String[][] output = new String[9][9];
					try {
						for(int r = 1; r < 10; r++){
							for(int c = 1; c <10; c++){
								if (sbm.getValueAt(r, c) == 0){
									output[r-1][c-1] = null;
								}
								else{
								output[r-1][c-1] = Integer.toString(sbm.getValueAt(r, c));
								}
							}
						}
						
					} catch (InputOutOfRangeException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				txt11.setText(output[0][0]);
				txt12.setText(output[0][1]);
				txt13.setText(output[0][2]);
				txt14.setText(output[0][3]);
				txt15.setText(output[0][4]);
				txt16.setText(output[0][5]);
				txt17.setText(output[0][6]);
				txt18.setText(output[0][7]);
				txt19.setText(output[0][8]);
				
				txt21.setText(output[1][0]);
				txt22.setText(output[1][1]);
				txt23.setText(output[1][2]);
				txt24.setText(output[1][3]);
				txt25.setText(output[1][4]);
				txt26.setText(output[1][5]);
				txt27.setText(output[1][6]);
				txt28.setText(output[1][7]);
				txt29.setText(output[1][8]);
				
				txt31.setText(output[2][0]);
				txt32.setText(output[2][1]);
				txt33.setText(output[2][2]);
				txt34.setText(output[2][3]);
				txt35.setText(output[2][4]);
				txt36.setText(output[2][5]);
				txt37.setText(output[2][6]);
				txt38.setText(output[2][7]);
				txt39.setText(output[2][8]);
				
				txt41.setText(output[3][0]);
				txt42.setText(output[3][1]);
				txt43.setText(output[3][2]);
				txt44.setText(output[3][3]);
				txt45.setText(output[3][4]);
				txt46.setText(output[3][5]);
				txt47.setText(output[3][6]);
				txt48.setText(output[3][7]);
				txt49.setText(output[3][8]);
				
				txt51.setText(output[4][0]);
				txt52.setText(output[4][1]);
				txt53.setText(output[4][2]);
				txt54.setText(output[4][3]);
				txt55.setText(output[4][4]);
				txt56.setText(output[4][5]);
				txt57.setText(output[4][6]);
				txt58.setText(output[4][7]);
				txt59.setText(output[4][8]);
				
				txt61.setText(output[5][0]);
				txt62.setText(output[5][1]);
				txt63.setText(output[5][2]);
				txt64.setText(output[5][3]);
				txt65.setText(output[5][4]);
				txt66.setText(output[5][5]);
				txt67.setText(output[5][6]);
				txt68.setText(output[5][7]);
				txt69.setText(output[5][8]);
				
				txt71.setText(output[6][0]);
				txt72.setText(output[6][1]);
				txt73.setText(output[6][2]);
				txt74.setText(output[6][3]);
				txt75.setText(output[6][4]);
				txt76.setText(output[6][5]);
				txt77.setText(output[6][6]);
				txt78.setText(output[6][7]);
				txt79.setText(output[6][8]);
				
				txt81.setText(output[7][0]);
				txt82.setText(output[7][1]);
				txt83.setText(output[7][2]);
				txt84.setText(output[7][3]);
				txt85.setText(output[7][4]);
				txt86.setText(output[7][5]);
				txt87.setText(output[7][6]);
				txt88.setText(output[7][7]);
				txt89.setText(output[7][8]);
				
				txt91.setText(output[8][0]);
				txt92.setText(output[8][1]);
				txt93.setText(output[8][2]);
				txt94.setText(output[8][3]);
				txt95.setText(output[8][4]);
				txt96.setText(output[8][5]);
				txt97.setText(output[8][6]);
				txt98.setText(output[8][7]);
				txt99.setText(output[8][8]);
			}
		});
		/**
		 * this will close the program.
		 */
		btnexit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
	}
	
	/**
	 * this method will read the file with a JFileChooser
	 * @throws FileNotFoundException
	 */
	public void readFile() throws FileNotFoundException{
		JFileChooser chooser = new JFileChooser();
		
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			File selectedFile = chooser.getSelectedFile();
		
			sbm.newGame(selectedFile);
		}
		
	}
	
	JPanel southpanel = new JPanel();
	//TOP LEFT Enter Value PANEL
	JPanel enterpan = new JPanel();
	JPanel topleftpan = new JPanel();	
	JLabel lblenterrow = new JLabel("Row: ");
	JLabel lblentercol = new JLabel("Column: ");
	JLabel lblenterval = new JLabel("Value: ");	
	JTextField txtenterrow = new JTextField(5);
	JTextField txtentercol = new JTextField(5);
	JTextField txtenterval = new JTextField(5);	
	JButton btnenter = new JButton("Enter");
	
	//TOP RIGHT Display Value PANEL
	JPanel displaypan = new JPanel();
	JPanel toprightpan = new JPanel();
	JLabel lbldisplayrow = new JLabel("  Row: ");
	JLabel lbldisplaycol = new JLabel("    Column: ");
	JTextField txtdisplayrow = new JTextField(5);
	JTextField txtdisplaycol = new JTextField(5);
	JTextField txtdisplayvalue = new JTextField(12);
	JButton btndisplay = new JButton("Display Possible Value");
	
	//bottom buttons
	JPanel buttonpan = new JPanel();
	JButton btnnewgame = new JButton("New Game");
	JButton btnexit = new JButton("Exit");
	
	/**
	 * this method will generate the bottom panel.  each node is declared in the field above
	 */
	public void bottompanel(){
		
		southpanel.setPreferredSize(new Dimension(370, 190));
		//Enter Value Panel
		enterpan.setBorder(BorderFactory.createTitledBorder(null, "Enter Value"));
		topleftpan.setPreferredSize(new Dimension(120,110));
		topleftpan.add(lblenterrow);
		topleftpan.add(txtenterrow);
		topleftpan.add(lblentercol);
		topleftpan.add(txtentercol);
		topleftpan.add(lblenterval);
		topleftpan.add(txtenterval);
		topleftpan.add(btnenter);
		enterpan.add(topleftpan);
		southpanel.add(enterpan);
		
		//Display Value Panel
		displaypan.setBorder(BorderFactory.createTitledBorder(null, "Display Possible Values"));
		toprightpan.setPreferredSize(new Dimension(170,110));
		toprightpan.add(lbldisplayrow);
		toprightpan.add(txtdisplayrow);
		toprightpan.add(lbldisplaycol);
		toprightpan.add(txtdisplaycol);
		toprightpan.add(txtdisplayvalue);
		toprightpan.add(btndisplay);
		displaypan.add(toprightpan);
		southpanel.add(displaypan);
		
		txtdisplayvalue.setEditable(false);
		
		buttonpan.setPreferredSize(new Dimension(300, 40));
		buttonpan.add(btnnewgame);
		buttonpan.add(btnexit);
		southpanel.add(buttonpan);
		
	}
	
	JTextField txt11 = new JTextField(2);
	JTextField txt12 = new JTextField(2);
	JTextField txt13 = new JTextField(2);
	JTextField txt14 = new JTextField(2);
	JTextField txt15 = new JTextField(2);
	JTextField txt16 = new JTextField(2);
	JTextField txt17 = new JTextField(2);
	JTextField txt18 = new JTextField(2);
	JTextField txt19 = new JTextField(2);
	
	JTextField txt21 = new JTextField(2);
	JTextField txt22 = new JTextField(2);
	JTextField txt23 = new JTextField(2);
	JTextField txt24 = new JTextField(2);
	JTextField txt25 = new JTextField(2);
	JTextField txt26 = new JTextField(2);
	JTextField txt27 = new JTextField(2);
	JTextField txt28 = new JTextField(2);
	JTextField txt29 = new JTextField(2);
	
	JTextField txt31 = new JTextField(2);
	JTextField txt32 = new JTextField(2);
	JTextField txt33 = new JTextField(2);
	JTextField txt34 = new JTextField(2);
	JTextField txt35 = new JTextField(2);
	JTextField txt36 = new JTextField(2);
	JTextField txt37 = new JTextField(2);
	JTextField txt38 = new JTextField(2);
	JTextField txt39 = new JTextField(2);
	
	JTextField txt41 = new JTextField(2);
	JTextField txt42 = new JTextField(2);
	JTextField txt43 = new JTextField(2);
	JTextField txt44 = new JTextField(2);
	JTextField txt45 = new JTextField(2);
	JTextField txt46 = new JTextField(2);
	JTextField txt47 = new JTextField(2);
	JTextField txt48 = new JTextField(2);
	JTextField txt49 = new JTextField(2);
	
	JTextField txt51 = new JTextField(2);
	JTextField txt52 = new JTextField(2);
	JTextField txt53 = new JTextField(2);
	JTextField txt54 = new JTextField(2);
	JTextField txt55 = new JTextField(2);
	JTextField txt56 = new JTextField(2);
	JTextField txt57 = new JTextField(2);
	JTextField txt58 = new JTextField(2);
	JTextField txt59 = new JTextField(2);
	
	JTextField txt61 = new JTextField(2);
	JTextField txt62 = new JTextField(2);
	JTextField txt63 = new JTextField(2);
	JTextField txt64 = new JTextField(2);
	JTextField txt65 = new JTextField(2);
	JTextField txt66 = new JTextField(2);
	JTextField txt67 = new JTextField(2);
	JTextField txt68 = new JTextField(2);
	JTextField txt69 = new JTextField(2);
	
	JTextField txt71 = new JTextField(2);
	JTextField txt72 = new JTextField(2);
	JTextField txt73 = new JTextField(2);
	JTextField txt74 = new JTextField(2);
	JTextField txt75 = new JTextField(2);
	JTextField txt76 = new JTextField(2);
	JTextField txt77 = new JTextField(2);
	JTextField txt78 = new JTextField(2);
	JTextField txt79 = new JTextField(2);
	
	JTextField txt81 = new JTextField(2);
	JTextField txt82 = new JTextField(2);
	JTextField txt83 = new JTextField(2);
	JTextField txt84 = new JTextField(2);
	JTextField txt85 = new JTextField(2);
	JTextField txt86 = new JTextField(2);
	JTextField txt87 = new JTextField(2);
	JTextField txt88 = new JTextField(2);
	JTextField txt89 = new JTextField(2);
	
	JTextField txt91 = new JTextField(2);
	JTextField txt92 = new JTextField(2);
	JTextField txt93 = new JTextField(2);
	JTextField txt94 = new JTextField(2);
	JTextField txt95 = new JTextField(2);
	JTextField txt96 = new JTextField(2);
	JTextField txt97 = new JTextField(2);
	JTextField txt98 = new JTextField(2);
	JTextField txt99 = new JTextField(2);
	
	JPanel gridpan = new JPanel();
	JPanel centerpan = new JPanel();

	/**
	 * this method will generate the grid for the Sudoku box.  each node is declared above.
	 */
	public void gridpanel(){
		
		gridpan.setLayout(new GridLayout(9,9, 1, 1));
		
		txt11.setBackground(Color.cyan);
		txt12.setBackground(Color.cyan);
		txt13.setBackground(Color.cyan);
		txt21.setBackground(Color.cyan);
		txt22.setBackground(Color.cyan);
		txt23.setBackground(Color.cyan);
		txt31.setBackground(Color.cyan);
		txt32.setBackground(Color.cyan);
		txt33.setBackground(Color.cyan);
		
		txt17.setBackground(Color.cyan);
		txt18.setBackground(Color.cyan);
		txt19.setBackground(Color.cyan);
		txt27.setBackground(Color.cyan);
		txt28.setBackground(Color.cyan);
		txt29.setBackground(Color.cyan);
		txt37.setBackground(Color.cyan);
		txt38.setBackground(Color.cyan);
		txt39.setBackground(Color.cyan);
		
		txt44.setBackground(Color.cyan);
		txt45.setBackground(Color.cyan);
		txt46.setBackground(Color.cyan);
		txt54.setBackground(Color.cyan);
		txt55.setBackground(Color.cyan);
		txt56.setBackground(Color.cyan);
		txt64.setBackground(Color.cyan);
		txt65.setBackground(Color.cyan);
		txt66.setBackground(Color.cyan);
		
		txt71.setBackground(Color.cyan);
		txt72.setBackground(Color.cyan);
		txt73.setBackground(Color.cyan);
		txt81.setBackground(Color.cyan);
		txt82.setBackground(Color.cyan);
		txt83.setBackground(Color.cyan);
		txt91.setBackground(Color.cyan);
		txt92.setBackground(Color.cyan);
		txt93.setBackground(Color.cyan);
		
		txt77.setBackground(Color.cyan);
		txt78.setBackground(Color.cyan);
		txt79.setBackground(Color.cyan);
		txt87.setBackground(Color.cyan);
		txt88.setBackground(Color.cyan);
		txt89.setBackground(Color.cyan);
		txt97.setBackground(Color.cyan);
		txt98.setBackground(Color.cyan);
		txt99.setBackground(Color.cyan);
		
		txt11.setEditable(false);
		txt12.setEditable(false);
		txt13.setEditable(false);
		txt14.setEditable(false);
		txt15.setEditable(false);
		txt16.setEditable(false);
		txt17.setEditable(false);
		txt18.setEditable(false);
		txt19.setEditable(false);
		
		txt21.setEditable(false);
		txt22.setEditable(false);
		txt23.setEditable(false);
		txt24.setEditable(false);
		txt25.setEditable(false);
		txt26.setEditable(false);
		txt27.setEditable(false);
		txt28.setEditable(false);
		txt29.setEditable(false);
		
		txt31.setEditable(false);
		txt32.setEditable(false);
		txt33.setEditable(false);
		txt34.setEditable(false);
		txt35.setEditable(false);
		txt36.setEditable(false);
		txt37.setEditable(false);
		txt38.setEditable(false);
		txt39.setEditable(false);
		
		txt41.setEditable(false);
		txt42.setEditable(false);
		txt43.setEditable(false);
		txt44.setEditable(false);
		txt45.setEditable(false);
		txt46.setEditable(false);
		txt47.setEditable(false);
		txt48.setEditable(false);
		txt49.setEditable(false);
		
		txt51.setEditable(false);
		txt52.setEditable(false);
		txt53.setEditable(false);
		txt54.setEditable(false);
		txt55.setEditable(false);
		txt56.setEditable(false);
		txt57.setEditable(false);
		txt58.setEditable(false);
		txt59.setEditable(false);
		
		txt61.setEditable(false);
		txt62.setEditable(false);
		txt63.setEditable(false);
		txt64.setEditable(false);
		txt65.setEditable(false);
		txt66.setEditable(false);
		txt67.setEditable(false);
		txt68.setEditable(false);
		txt69.setEditable(false);
		
		txt71.setEditable(false);
		txt72.setEditable(false);
		txt73.setEditable(false);
		txt74.setEditable(false);
		txt75.setEditable(false);
		txt76.setEditable(false);
		txt77.setEditable(false);
		txt78.setEditable(false);
		txt79.setEditable(false);
		
		txt81.setEditable(false);
		txt82.setEditable(false);
		txt83.setEditable(false);
		txt84.setEditable(false);
		txt85.setEditable(false);
		txt86.setEditable(false);
		txt87.setEditable(false);
		txt88.setEditable(false);
		txt89.setEditable(false);
		
		txt91.setEditable(false);
		txt92.setEditable(false);
		txt93.setEditable(false);
		txt94.setEditable(false);
		txt95.setEditable(false);
		txt96.setEditable(false);
		txt97.setEditable(false);
		txt98.setEditable(false);
		txt99.setEditable(false);
		
		gridpan.add(txt11);
		gridpan.add(txt12);
		gridpan.add(txt13);
		gridpan.add(txt14);
		gridpan.add(txt15);
		gridpan.add(txt16);
		gridpan.add(txt17);
		gridpan.add(txt18);
		gridpan.add(txt19);
		
		gridpan.add(txt21);
		gridpan.add(txt22);
		gridpan.add(txt23);
		gridpan.add(txt24);
		gridpan.add(txt25);
		gridpan.add(txt26);
		gridpan.add(txt27);
		gridpan.add(txt28);
		gridpan.add(txt29);
		
		gridpan.add(txt31);
		gridpan.add(txt32);
		gridpan.add(txt33);
		gridpan.add(txt34);
		gridpan.add(txt35);
		gridpan.add(txt36);
		gridpan.add(txt37);
		gridpan.add(txt38);
		gridpan.add(txt39);
		
		gridpan.add(txt41);
		gridpan.add(txt42);
		gridpan.add(txt43);
		gridpan.add(txt44);
		gridpan.add(txt45);
		gridpan.add(txt46);
		gridpan.add(txt47);
		gridpan.add(txt48);
		gridpan.add(txt49);
		
		gridpan.add(txt51);
		gridpan.add(txt52);
		gridpan.add(txt53);
		gridpan.add(txt54);
		gridpan.add(txt55);
		gridpan.add(txt56);
		gridpan.add(txt57);
		gridpan.add(txt58);
		gridpan.add(txt59);
		
		gridpan.add(txt61);
		gridpan.add(txt62);
		gridpan.add(txt63);
		gridpan.add(txt64);
		gridpan.add(txt65);
		gridpan.add(txt66);
		gridpan.add(txt67);
		gridpan.add(txt68);
		gridpan.add(txt69);
		
		gridpan.add(txt71);
		gridpan.add(txt72);
		gridpan.add(txt73);
		gridpan.add(txt74);
		gridpan.add(txt75);
		gridpan.add(txt76);
		gridpan.add(txt77);
		gridpan.add(txt78);
		gridpan.add(txt79);
		
		gridpan.add(txt81);
		gridpan.add(txt82);
		gridpan.add(txt83);
		gridpan.add(txt84);
		gridpan.add(txt85);
		gridpan.add(txt86);
		gridpan.add(txt87);
		gridpan.add(txt88);
		gridpan.add(txt89);
		
		gridpan.add(txt91);
		gridpan.add(txt92);
		gridpan.add(txt93);
		gridpan.add(txt94);
		gridpan.add(txt95);
		gridpan.add(txt96);
		gridpan.add(txt97);
		gridpan.add(txt98);
		gridpan.add(txt99);
		
		centerpan.setPreferredSize(new Dimension(300, 200));
		centerpan.setLayout(new FlowLayout(FlowLayout.LEFT));
		centerpan.add(gridpan);
		
	}
	
	
	JPanel colpan = new JPanel();
	JLabel lblcol = new JLabel("                     1       2       3       4       5       6       7       8       9      ");
	
	/**
	 * this will generate the column label above the grid.
	 */
	public void columnpanel(){
		
		colpan.add(lblcol);
		
	}
	
	JPanel rowpan = new JPanel();
	JLabel lblrow1 = new JLabel("    Row 1");
	JLabel lblrow2 = new JLabel("    Row 2");
	JLabel lblrow3 = new JLabel("    Row 3");
	JLabel lblrow4 = new JLabel("    Row 4");
	JLabel lblrow5 = new JLabel("    Row 5");
	JLabel lblrow6 = new JLabel("    Row 6");
	JLabel lblrow7 = new JLabel("    Row 7");
	JLabel lblrow8 = new JLabel("    Row 8");
	JLabel lblrow9 = new JLabel("    Row 9");
	
	/**
	 * this will generate the row panel on the side of the grid.
	 */
	public void rowpanel(){
		
		rowpan.setPreferredSize(new Dimension (75, 200));
		rowpan.setLayout(new FlowLayout(FlowLayout.RIGHT));
		rowpan.add(lblrow1);
		rowpan.add(lblrow2);
		rowpan.add(lblrow3);
		rowpan.add(lblrow4);
		rowpan.add(lblrow5);
		rowpan.add(lblrow6);
		rowpan.add(lblrow7);
		rowpan.add(lblrow8);
		rowpan.add(lblrow9);
		
	}
	/**
	 * this is the main method that calls the sudoku GUI driver constructor.
	 * @param args
	 */
	public static void main (String[] args){
		new SudokuDriver();
	}

}
