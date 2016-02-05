
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 
 * @author Harry Kim
 * 
 * <b> This project will generate a list of prime numbers that the user inputs.  the data will be sent into a manager class which then will go though the thread class.  within the thread class
 * the method will generate threads to send data through a client which will then pass it to the server.  the server than test if the number is prime.  if the number is prime it will add a 1
 * to the end of the number, and a 0 if it's not prime.  each number while then be placed into an array list which will be displayed into the gui.  the write button will write to a text file.
 * the text file will consist of all the prime numbers from the user.  exit will then close the server and client.
 *
 */

public class gooey {
	
	
	JPanel topOut = new JPanel();
	JPanel toppan = new JPanel();
	JTextField txtprime = new JTextField(50);
	JButton btnprime = new JButton("Check for Primality");

	JTextArea primearea = new JTextArea(9,15);
	JScrollPane sp = new JScrollPane(primearea); 
	
	JPanel botOut = new JPanel();
	JButton btnserver = new JButton("Start Server");
	JButton btnshow = new JButton("Display Primes");
	JButton btnexit = new JButton("Exit");

	JButton btnwrite = new JButton("Write File");
	PrimeFinderManager  pfm = new PrimeFinderManager();
	Server se;
	
	public gooey(){
		JFrame window = new JFrame();
		window.setTitle("Spell Checker");
		window.setSize( 720,  335);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		//centers the window in the middle of screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
		
		toppan();
		window.add(topOut, BorderLayout.NORTH);		
		

		window.add(sp, BorderLayout.CENTER);

		botpan();
		window.add(botOut, BorderLayout.SOUTH);

		
		btnprime.setEnabled(false);
		btnshow.setEnabled(false);
		btnwrite.setEnabled(false);
		
		btnwrite.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				JFileChooser chooser = new JFileChooser();
				
				
				if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
					try {
						
						File file = chooser.getSelectedFile();
						
						pfm.writePrimes(file);


					} catch(Exception e11){
						
					}

				}
			}
		});
		btnprime.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				String primes = txtprime.getText();
				
				if(primes.contains(".")){
					
					JOptionPane.showMessageDialog(null, "Invalid input format: Enter integers each separated by a single space");
					
				}
				else if( primes.matches(".*\\d.*\\s+\\n")){
					JOptionPane.showMessageDialog(null, "Must be a number separated by white space(s).");
				}

				else{
					pfm.runPrimeFinder(primes);
				}

			}
		});

		btnserver.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				btnprime.setEnabled(true);
				btnshow.setEnabled(true);
				btnwrite.setEnabled(true);
				
				
				
				//start the server
//				se = new Server(8800);
				
				// server is false
				
				btnserver.setEnabled(false);
				
				
			}
		});
		btnshow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				primearea.setText("");
				
				
				ArrayList<Integer> show = new ArrayList<Integer>();
				
				for(int j = 0; j < show.size(); j++){
					show.remove(j);
				}
				
				show = pfm.getPrimeList();
		
				
				String onarea = "";
				for(int i = 0; i < show.size(); i++){
					
					onarea += show.get(i) + "\n";
					
				}
				
				primearea.setText(onarea);
				
			}
		});
		
		btnexit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				//close server
				
//				se.stopServer();
				
				System.exit(0);
			}
		});
		
	}
	

	
	public void toppan(){
		
		topOut.setPreferredSize(new Dimension(690, 100));
		
		toppan.setPreferredSize(new Dimension(680, 90));
		toppan.setBorder(BorderFactory.createTitledBorder(null, "Enter Numbers to Check for Primality:"));
	
		toppan.add(txtprime);
		toppan.add(btnprime);
		
		topOut.add(toppan);
	
	}
	

	
	public void botpan(){
		botOut.setPreferredSize(new Dimension(690, 40));
		
		botOut.add(btnserver);
		botOut.add(btnshow);
		botOut.add(btnwrite);
		botOut.add(btnexit);
	}
	
	
	public static void main(String[] args) {
		
		
		new gooey();


	}

}
