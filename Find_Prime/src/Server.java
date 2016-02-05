
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

/**
 * This interface specifies public methods for the Server class.  The server is responsible for 
 * accepting socket connections from a client, reading integers to check for primality,
 * and writing results to the client.
 * @author Harry Kim
 * 
 * This is where all the information will be tested and return for prime numbers.
 */
public class Server implements ServerInterface{
	
	 ServerSocket server;
	 Socket client;

	public Server(int port) {
		// TODO Auto-generated constructor stub
		 try {
			
			  server = new ServerSocket(port);
			  
			  
			  
		      boolean b = true;
		      do{ 
		        Socket client = server.accept();
				System.out.println("Client connected.");
				       
				//while loop here
		        Scanner in = new Scanner(client.getInputStream());
		        PrintWriter out = new PrintWriter(client.getOutputStream());
		        String whichMessageString = in.nextLine();
		        int whichMessage = Integer.parseInt(whichMessageString);
		        System.out.println("whichMessage " + whichMessage);
		        //add while until user chooses quit
		        
				
				boolean	isPrime = true;
				if (whichMessage == 1)
					isPrime = false;
				else if (whichMessage == 2)
					isPrime = true;
				else if (whichMessage  % 2 == 0)
					isPrime = false;
				else if (whichMessage  < 0){
					isPrime = false;
				}
				else if(whichMessage  > 0){
					for (int denom = 3; denom <= whichMessage -1; denom+=2)
					{
					 if (whichMessage  % denom == 0)
						{
							isPrime = false;
							break;
						}
					}
				}
		        
				if(isPrime == true){
					out.println(whichMessage + " 1" );
					out.flush();
				}
				if(isPrime == false){
					out.println(whichMessage + " 0" );
					out.flush();					
				}
		        
		        out.flush();
		        out.close();
		        
		        
		      
		        
		      }while (b) ;
		    }
		    catch (IOException e) {
		      System.err.println(e.getMessage());
		    }
		
	}


	/**
	 * stopServer method exits the server instance
	 * @return true if server is set to null, false otherwise
	 */
	@Override
	public boolean stopServer() {
		// TODO Auto-generated method stub
		  try {
			client.close();
		} catch (IOException e) {
		
		}
		  
		return false;
	}
	   /**
	    * the startServer method creates a new ServerSocket instance with the port supplied
	    * to the constructor and connects to a client socket created on the same machine ("localhost")
	    */
	@Override
	public void startServer(int clientDoneSignal) {
		// TODO Auto-generated method stub
		try {
			server = new ServerSocket(clientDoneSignal);
			 System.out.println("Waiting for clients to connect . . .");

			 try {
					
				  server = new ServerSocket(clientDoneSignal);
				  
				  
				  
			      boolean b = true;
			      do{ 
			        Socket client = server.accept();
					System.out.println("Client connected.");
					       
					//while loop here
			        Scanner in = new Scanner(client.getInputStream());
			        PrintWriter out = new PrintWriter(client.getOutputStream());
			        String whichMessageString = in.nextLine();
			        int whichMessage = Integer.parseInt(whichMessageString);
			        System.out.println("whichMessage " + whichMessage);
			        //add while until user chooses quit
			        
					
					boolean	isPrime = true;
					if (whichMessage == 1)
						isPrime = false;
					else if (whichMessage == 2)
						isPrime = true;
					else if (whichMessage  % 2 == 0)
						isPrime = false;
					else if (whichMessage  < 0){
						isPrime = false;
					}
					else if(whichMessage  > 0){
						for (int denom = 3; denom <= whichMessage -1; denom+=2)
						{
						 if (whichMessage  % denom == 0)
							{
								isPrime = false;
								break;
							}
						}
					}
			        
					if(isPrime == true){
						out.println(whichMessage + " 1" );
						out.flush();
					}
					if(isPrime == false){
						out.println(whichMessage + " 0" );
						out.flush();					
					}
			        
			        out.flush();
			        out.close();
			        
			        
			      
			        
			      }while (b) ;
			    }
			    catch (IOException e) {
			      System.err.println(e.getMessage());
			    }
			
		} catch (IOException e) {
			
		}
		 
	}

}
