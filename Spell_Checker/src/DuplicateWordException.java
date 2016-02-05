/**
 * 
 * @author Harry kim
 * this exception will throw if a word already exist within the dictionary.
 *
 */
@SuppressWarnings("serial")
public class DuplicateWordException extends Exception{
	
	public DuplicateWordException(String Error){
		super(Error);
	}

}
