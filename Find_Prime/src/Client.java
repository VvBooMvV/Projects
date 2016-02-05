
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Interface to the Client class; specifies the methods required to be implemented
 * @author Harry Kim
 * 
 * this is where the client thread will start and send each item to the server and retrieve all prime numbers.
 *
 */

public class Client implements ClientInterface{

	 Socket client;
	public Client() {
		// TODO Auto-generated constructor stub
		
		 try {
			client = new Socket("localhost", 8800);
		} catch (UnknownHostException e) {
		
		} catch (IOException e) {
		
		}
	}
	public Client(String testHostname, int testPort) {
		// TODO Auto-generated constructor stub
		
		try {
			client = new Socket(testHostname, testPort);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * checkCandidates method uses the Client socket's i/o stream
	 *        to send candidate prime integers to the server and to receive responses
	 *        from the server as to whether or not each integer is a prime number
	 *        For each candidate sent to the server, the server responds with a string
	 *        of the form "n 1" if n is a prime, or "n 0" if it is not.  The method adds 
	 *        the primes to an ArrayList<Integer> that can be accessed for display
	 *        or writing to a file.
	 * @param candidatePrimes - a list of integers that may or may not be prime numbers.  
	 *        The list should end with a sentinal value that signals the end of the list 
	 *        for the server. By default this sentinal will be -999. 
	 * @return a boolean that indicates that the server has responded to all 
	 *         the candidates sent to it 
	 */
	@Override
	public boolean checkCandidates(ArrayList<Integer> candidatePrimes) {
		// TODO Auto-generated method stub
		 
	       try {
	    	   
	    	 boolean b = true;
	    	do{
	         Scanner scan = new Scanner(System.in);
	        
	        
	         InputStream instream = client.getInputStream();
	         OutputStream outstream = client.getOutputStream();
	         Scanner in = new Scanner(instream);
	         PrintWriter out = new PrintWriter(outstream);
	        
	         System.out.println("1.  Unlimited Wealth\n2.  Mystery Box\n3.  Secret to Life\nWhich option? ");
	        
	         int option = scan.nextInt();
	         out.print("" + option+ "\n");
	         out.flush();
	         //add something for user to quit
	         String response = in.nextLine();
	         System.out.println(response);
	         if(option == 3){
	        	 b = false;
	        	 break;
	       
	        
	         }
	         in.close();
	       
	    	}while(b);
	    	 
	       }      
	       catch (UnknownHostException e) {
	           System.err.println("No such host");
	       }
	       catch (IOException e) {
	         System.err.println(e.getMessage());
	       }
		return false;
	}
	
	public boolean isSocketClosed(){
		return false;
	}
	/**
	 * sendMessage method uses the client's output stream to send an integer in the form 
	 *        of a string to the server.
	 * @param msg an integer represented as a String
	 */
	@Override
	public void sendMessage(String msg) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * receiveString method uses the client's input stream connected to the server to
	 *        read responses from the server.  The the string of the form "n 1" 
	 *        if n is a prime, or "n 0" if it is not.
	 * @return the String read from the server
	 */
	@Override
	public String receiveString() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * closeClient method closes the input and output streams of the client's socket and
	 *        then closes the client socket itself.  It catches a SocketException if the
	 *        socket is blocked in an I/O operation.
	 * @return true if the socket and i/o streams are successfully closed, false otherwise.
	 */
	@Override
	public boolean closeClient() {
		// TODO Auto-generated method stub
		  try {
			client.close();
			
			return true;
		} catch (IOException e) {
			
		}
		return false;
	}

}
