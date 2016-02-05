import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

class MessageServer {

  public static void main(String[] args) {
	  
	  
    try {
      ServerSocket server = new ServerSocket(8800);
      System.out.println("Waiting for clients to connect . . .");
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
      
        switch(whichMessage)
        {
          case 1:out.println("You've acquired a hand full of expired coupons.");
            out.flush();
            break;
          case 2: 
        	  Random m = new Random(10);
        	  int i = m.nextInt();
        	  if(i < 5){
        		  out.println("Try Again.");
        	  }
        	  else{
        		  out.println("Ask Me Later.");  
        	  }
        	  
            out.flush();
            break;
          case 3 : 
        	  out.println("You've aquired a box full of disappointments.  Goodbye! ");
          	b = false;
            out.flush();
            break;

          default : out.println("Invalid choice");
            out.flush();
        }
       
        out.flush();

        //keep while loop until 
       
        out.close();
        
        
        client.close();
        
      }while (b) ;
    }
    catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }
}
