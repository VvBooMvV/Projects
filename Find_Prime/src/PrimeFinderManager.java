
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;

import javax.swing.JOptionPane;

/**
 * 
 * @author Harry Kim
 * 
 * this is the prime manager class.  this will send and store the data of numbers.  the each will send it to the thread class. to be tested for priime numbers.
 *
 */
public class PrimeFinderManager implements PrimeFinderManagerInterface {

	protected static final int CLIENT_INPUT_DONE = 0;//change

	public Object client;
	
	PrimeThread td = new PrimeThread();
	static ArrayList<Integer> primes = new ArrayList<Integer>();
	ArrayList<Integer> prime = new ArrayList<Integer>();
	/**
	 * the runPrimeFinder method parses the input string, expecting integers separated
	 * by single spaces, parses each separated string into an integer, adds them to
	 * an ArrayList of integers, and calls the Client constructor with that list
	 * @param strNumbers - a string that expects to be parsed into integers 
	 * each separated by single spaces
	 * @throws NumberFormatException if any of the character strings cannot be
	 * parsed to an integer after parsing them by " "
	 */
	@Override
	public void runPrimeFinder(String strNumbers) throws NumberFormatException {
		// TODO Auto-generated method stub
		
		if(strNumbers.contains(".")){
			throw new NumberFormatException();
		}
		else if( strNumbers.contains("[0-9]+") && strNumbers.length() > 2 ){
			throw new NumberFormatException();
		}
		
		String[] split = strNumbers.split("\\s+");
		prime.clear();
		
		for(int i = 0; i < split.length; i++){
			
			int test = Integer.parseInt(split[i]);
			
			if(td.isPrime(test)){
				prime.add(test);
			}
			
			
		}
		

		
	}
	/**
	 * the writePrimes method writes the list of primes found to a text file
	 * @param outFile an instance of File to which the list of primes will be written
	 */
	@Override
	public void writePrimes(File outFile) {
		// TODO Auto-generated method stub
		
		
        BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(outFile + ".txt"));
			
			String printit = "";
			
			for(int i = 0; i < prime.size(); i++){
				
				printit += prime.get(i) + "  \n";
				
				
			}
			
			out.write(printit);
			
			out.close();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			
		
	}

	public void runPrimeFinder(String hostname, int port, String string) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Integer> getPrimeList() {
	
		
		Collections.sort(prime);
		
		return prime;
	}

}
