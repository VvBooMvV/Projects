
import java.util.ArrayList;


/**
 * the PrimeThread class is a thread (also implements Runnable) that determines if an integer 
 * is a prime number.   Since computing whether a number is a prime can be an expensive 
 * operation for large numbers, each candidate integer is tested for primality in its
 * own thread on a server.
 * <b>
 * @author Harry Kim
 * <b>  this class will generate a thread which will then test each thread of numbers to be generated into the client class. which will send it to the server.
 *
 */
public class PrimeThread implements PrimeThreadInterface, Runnable{
	/**
	 * the isPrime method checks an integer for primality.  Its results are valid
	 * only for integers greater than or equal to 2. Non-integers, 1, 0, and negative numbers 
	 * are not eligible to be prime numbers, by definition of primality. 
	 * There are numerous algorithms to determine if a number is a prime; see the
	 * site http://en.wikipedia.org/wiki/Primality_test. 
	 * @param number an integer greater than 1 that is to be checked for primality
	 * @return true if number is a prime number, false if it is a composite number
	 */
	
	int num = 0;
	ArrayList<Integer> test = new ArrayList<Integer>();
	@Override
	public boolean isPrime(int number) {
		// TODO Auto-generated method stub
		
			test.add(number);
			num = number;
			
			boolean	isPrime = true;
			if (number == 1)
				isPrime = false;
			else if (number == 2)
				isPrime = true;
			else if (number % 2 == 0)
				isPrime = false;
			else if (number < 0){
				isPrime = false;
			}
			else if(number > 0){
				for (int denom = 3; denom <= number-1; denom+=2)
				{
				 if (number % denom == 0)
					{
						isPrime = false;
						break;
					}
				}
			}
				
			
			if (isPrime == true)
			{
				return true;
			}
			else{
				return false;
			}
	}
	
	public void run(){

		Client a = new Client();
		
	            try
	            {
	            	
	            	a.checkCandidates(test);
	            	
	            	
	            }
	            finally
	            {
	            	
	            }
	         
	      
		 Runnable r = new PrimeThread();
		 Thread t = new Thread(r);
	     
	      t.start();	
	
		
	}
	public String toString(){
		return "";
	}

}
