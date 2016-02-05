import java.util.PriorityQueue;
import java.util.Random;


public class CarQueue {

	
	Random ran = new Random();
	PriorityQueue<Integer> q = new PriorityQueue<Integer>();
	private int out;
	
	public CarQueue(){
	
		q.add(ran.nextInt(4));
		q.add(ran.nextInt(4));
		q.add(ran.nextInt(4));
		q.add(ran.nextInt(4));
		q.add(ran.nextInt(4));
		q.add(ran.nextInt(4));
		q.add(ran.nextInt(4));
		q.add(ran.nextInt(4));
		q.add(ran.nextInt(4));
		q.add(ran.nextInt(4));
		q.add(ran.nextInt(4));
		q.add(ran.nextInt(4));
		q.add(ran.nextInt(4));
		
		
	
	}
	
	public void addToQueue() {
	
		 class ARunnable implements Runnable
	      { 
			
	         public void run()
	         {
	        	
	            try
	            {
	            	q.add(ran.nextInt(4));
	            	q.add(ran.nextInt(4));
	            	q.add(ran.nextInt(4));
	            	q.add(ran.nextInt(4));
	            	q.add(ran.nextInt(4));
	            	q.add(ran.nextInt(4));
	            	q.add(ran.nextInt(4));
	            	
	            	
	            }
	            finally
	            {
	            	
	            }
	         }
	      }
		 Runnable r = new ARunnable();
		 Thread t = new Thread(r);
	     
	      t.start();	
	}

	public int deleteQueue() {
		
		 class ARunnable implements Runnable
	      { 
			 
			
	         public void run()
	         {

	        	 try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						
					}
		        	 
	        	 
	            try
	            {
	            	if(q.size() < 20){
	        			q.add(ran.nextInt(4));
	        			q.add(ran.nextInt(4));
	        			q.add(ran.nextInt(4));
	        			q.add(ran.nextInt(4));
	        			q.add(ran.nextInt(4));
	        			q.add(ran.nextInt(4));
	        			q.add(ran.nextInt(4));
	        			q.add(ran.nextInt(4));
	        			q.add(ran.nextInt(4));
	        			q.add(ran.nextInt(4));
	        			q.add(ran.nextInt(4));
	        			q.add(ran.nextInt(4));
	        			q.add(ran.nextInt(4));
	        			q.add(ran.nextInt(4));
	        		
	        		}
	            	
	            	else if (!q.isEmpty()){
	            	out = q.remove();
	            	}
	            	
	        		
	            }
	            finally
	            {
	            	
	            }
	         }
	         
	      }
		 Runnable r = new ARunnable();
		 Thread t = new Thread(r);
	     
	      t.start();
	      
	
	      return out;
		
	}

}
