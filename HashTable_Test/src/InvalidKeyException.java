/**
 * 
 * @author Harry Kim
 * this will be for the exception class InvalidKeyException
 * this is thrown when the phone number is not inn the specific format (XXX)XXX-XXXX
 *
 */
@SuppressWarnings("serial")
public class InvalidKeyException extends Exception {
	
	public InvalidKeyException(String Error){
		super(Error);
	}

}
