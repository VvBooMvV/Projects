import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
   This component draws two car shapes.
*/
public class CarPanel extends JComponent
{  
	private Car car1;
	private int x,y, delay;
	private CarQueue carQueue;
	private int direction;
	
	CarPanel(int x1, int y1, int d, CarQueue queue)
	{
		delay = d;
        x=x1;
        y=y1;
        car1 = new Car(x, y, this);
        carQueue = queue;
	}
	public void startAnimation()
	   {
	      class AnimationRunnable implements Runnable
	      {
	         public void run()
	         {
	            try
	            {
	            	
	               for(int i=0;i<100;i++)
	               {
	            	  
	            	   direction = carQueue.deleteQueue();
	            	   
	            	   if(direction == 0){
	            		   //up
	            		   if(!(y < 0 && y >300)){
	            			   y = y + 10;
	            		   }
	            		
	            		   
	            	   }
	            	   if (direction == 1){
	            		   //down
	            		  
	            		   if(!(y > 300 && y < 0)){
	            			   y = y - 10;
	            		   }
	            		  
	            	   }
	            	   if (direction == 2){
	            		   //left
	            		   
	            		   if((x >0)){
	            			   x = x - 10;
	            		   }
	            		   
	            		  
	            	   }
	            	   if (direction == 3){
	            		   //right
//	            		   System.out.println(x);
	            		   if( x <180){
	            			   x = x + 20;  
	            		   }
	            		  
	            	   }
	            	   repaint();
	            	   Thread.sleep(delay*100);
	            	   

	            	   
	               }
	            }
	            catch (InterruptedException exception)
	            {
	            	
	            	carQueue.addToQueue();
	            	
	            }
	            finally
	            {
	            	
	            }
	         }
	      }
	      
	      Runnable r = new AnimationRunnable();
	      Thread t = new Thread(r);
	      t.start();
	   }
	
   public void paintComponent(Graphics g)
   {  
      Graphics2D g2 = (Graphics2D) g;

      car1.draw(g2,x,y);    
   }
}
