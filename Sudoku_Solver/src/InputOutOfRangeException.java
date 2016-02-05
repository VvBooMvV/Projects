/**
 * 
 * @author Harry Kim, CS204, MW 12PM
 * the InputOutOfRangeException will generate the error for inputoutofrange exception in the super Exception class
 *
 */
public class InputOutOfRangeException extends Exception {
	
	public InputOutOfRangeException(){
				
	}
	
	public InputOutOfRangeException(String Error){
		
		super(Error);
	}

}
