/**
 * 
 * @author Harry Kim
 * this will throw if the word has an incorrect character.
 *
 */
@SuppressWarnings("serial")
public class InvalidSpellingException extends Exception {
	


	public InvalidSpellingException(String Error){
		
		super(Error);
		
	}

}
