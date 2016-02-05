
/**
 * 
 * @author Harry Kim
 * this will generate the message for the value not valid exception called from the super Exception class
 *
 */
public class ValueNotValidException extends Exception {

	public ValueNotValidException(){
		//if the value is not valid for row or column or number
	}
	
	public ValueNotValidException(String Error){
		super(Error);
	
	}
	

}
