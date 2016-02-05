import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MessageClient {
   public static void main(String args[]) {
	   
	 
       try {
    	   
    	 boolean b = true;
    	do{
         Scanner scan = new Scanner(System.in);
         Socket client = new Socket("localhost", 8800);
        
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
         client.close();
    	}while(b);
    	 
       }
       catch (UnknownHostException e) {
         System.err.println("No such host");
       }
       catch (IOException e) {
         System.err.println(e.getMessage());
       }
   }
}
