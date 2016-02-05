/**
 * 
 * @author Harry Kim
 * This will throw an excpetion when the phone number already exist with in a list.
 * 
 *
 */
@SuppressWarnings("serial")
public class KeyInUseException extends Exception {


	public KeyInUseException(String error){
		super(error);
	}
}
