import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * 
 * @author Harry Kim, CS204
 * 
 * This class will manipulate the data structures for the gui class.  it will store all the Person objects in a set of hashtable.
 * This is also where the gui will allow the information to be read and written to a file when called.
 * 
 * it will also test if the information exist within the hash table.  a user can test it by the person object, or the string for the phonenumber.
 * all data manipluationg is done here. except for the hashcode which exist in the Person class.
 *
 */

public class AddressBookUtility implements AddressBookInterface<PersonInterface> {
	
	HashTable peps; 
	PrintWriter output;
	Scanner input;
	
	public AddressBookUtility(){
		peps = new HashTable();
	
	}
	/**
	 * check if a Person is in the hashtable.  Uses getKey() to get
	 * the key (phone number) for the Person object
	 * @param p Person object
	 * @return true if Person is in the hashtable or false if not
	 */
	@Override
	public boolean contains(PersonInterface p) {
		
		if(peps.contains(p)){
			return true;
		}
		return false;
	}
	/**
	 * check if a Person is in the hashtable by key (phone number)
	 * Valid key is in the form (XXX)XXX-XXXX where X is a digit
	 * @param key the key for a Person object
	 * @return true if key for Person is in the hashtable or false if not
	 * @throws InvalidKeyException if key is invalid
	 * @throws java.security.InvalidKeyException 
	 */
	@Override
	public boolean contains(String key) throws InvalidKeyException {
		
		boolean test = false;
		
		if(key.charAt(0)=='(' && key.charAt(4)==')' && key.charAt(8)== '-'){
			
			if(peps.contains(key)){
				test = true;
			}
			
		}
		else{
			throw new InvalidKeyException("The phone number is placed inncorrectly. (X is a number) format = (XXX)XXX-XXXX");
		}
		return test;

	}
	/**
	 * Checks that the String s is a valid key for a Person object
	 * Valid key is in the form (XXX)XXX-XXXX where X is a digit
	 * @param s String to be checked if is a valid key
	 * @return true if the String s is in the form (XXX)XXX-XXXX, where X is a digit, false if not
	 * @throws InvalidKeyException if key is invalid
	 * @throws java.security.InvalidKeyException 
	 */
	@Override
	public boolean isValidKey(String s) throws InvalidKeyException {
		

		boolean test = true;
			
				if(s.length() <9){
					throw new InvalidKeyException("The  phone number is too short");
				}
		
				if(s.charAt(0)!='(' && s.charAt(4)!=')' && s.charAt(8)!= '-'){
					test = false;
					throw new InvalidKeyException("The phone number is placed inncorrectly. (X is a number) format = (XXX)XXX-XXXX");
				}
				else test = true;
		
			
		
		return test;

	}
	/**
	 * Return the Person object based on the key (phone number)
	 * Valid key is in the form (XXX)XXX-XXXX where X is a digit
	 * @param key the key for a Person object (phone number)
	 * @return the string "lastname, firstname" of the corresponding person object, null if key not in hashtable
	 * @throws InvalidKeyException if key is invalid
	 */
	@Override
	public String reverseLookup(String key) throws InvalidKeyException {
		PersonInterface dude = null;
		String out = "";
		if(!isValidKey(key)){
			throw new InvalidKeyException("The phone number is placed inncorrectly. (X is a number) format = (XXX)XXX-XXXX");
		}
		else{
			dude = peps.getValue(key);
			if(contains(key)){
			out = dude.getLname() + ", " + dude.getFname();
			}
			else{
				out = null;
			}
		}
		return out;
	}
	
	//create a method to print only the phones numbers from the hashtable 
	
//	  public String printnum(){
//	  
//	  return null;
//	  
//	  }
	 
	
	
	/**
	 * create hash table of contents in the file
	 * contents of file must be in this format:
	 * Firstname[space]lastname[space]phoneNumber[space]address\n
	 * phoneNumber must be in the form: (XXX)XXX-XXXX where X is a digit
	 * if the phoneNumber is not in the correct format - don't add to the hashtable
	 * if the phoneNumber has already been used - don't add to the hashtable
	 * @param f File
	 * @return true if File is found and Person objects added, returns false if file not found
	 */

	@Override
	public boolean readFile(File f) {
		
		ArrayList<String> txtfile = new ArrayList<>();
		
		try {
			input= new Scanner(f);
			while(input.hasNext()){
				String line = input.nextLine();
				txtfile.add(line);

			}
			String 	fn = "",
					ln = "",
					num = "",
					add = "";
			for(int i = 0; i < txtfile.size(); i++){
				String[] split = txtfile.get(i).split("\\s+");
				
				fn = split[0];
				ln = split[1];
				num = split[2];
				for(int j = 3; j < split.length; j++){
				
					add += split[j] + " ";
					
				}
				
				try {
					add(fn, ln, num, add);
				} catch (InvalidKeyException e) {
					
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (KeyInUseException e) {
					
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				
				fn = "";
				ln = "";
				num = "";
				add = "";
				
			}
			
			return true;
			
			
		} catch (FileNotFoundException e) {
			
		}
		return false;
	}
	/**
	 * add Person object to hashtable
	 * Valid key is in the form (XXX)XXX-XXXX where X is a digit
	 * @param fName first name
	 * @param lName last name
	 * @param pNumber phone number
	 * @param add address
	 * @throws InvalidKeyException if key is not in proper form
	 * @throws KeyInUseException if the key has already been used
	 * @throws java.security.InvalidKeyException 
	 */
	


	@Override
	public void add(String fName, String lName, String pNumber, String add)
			throws InvalidKeyException, KeyInUseException {
		

			
			if(isValidKey(pNumber) == false){
				throw new InvalidKeyException("The phone number is placed inncorrectly. (X is a number) format = (XXX)XXX-XXXX");
			}
			// if the hc exist then test
			
			else if (peps.contains(pNumber)){
				throw new KeyInUseException("That phone number already exist.");
			}
			else{
			PersonInterface newdude = new Person(fName, lName, pNumber, add);
			peps.add(newdude);
			
			}
			

		
	}
	/**
	 * write words in hash table to a file
	 * @param f File
	 * @return true if File can be written to and Person objects in hash table are stored in a file, 
	 *      false if file cannot be written to.
	 */

	
	@Override
	public boolean writeToFile(File f) {
		ArrayList<PersonInterface> dudes;
		
		if(peps.sort().isEmpty()){
			return false;
		}
		else{
			try {
				output = new PrintWriter(f);
				dudes = peps.sort();
				for(int i = 0; i < dudes.size(); i++){
					String 	fn= dudes.get(i).getFname(),
							ln= dudes.get(i).getLname(), 
							num =dudes.get(i).getPhone(), 
							add = dudes.get(i).getAddress();
					
					if(fn.equals("")){
						fn = "nullF";
					}
					if(ln.equals("")){
						ln = "nullL";
					}
					if(add.equals("")){
						add = "nullA";
					}
					
					output.println(fn + " " + ln + " " + num+ " " + add);
				}
				
				output.close();

			} catch (FileNotFoundException e) {
				
			}
			
			return true;
		}
		
	}
/**
 * this method will allow the phone numbers to be printed on the gui
 * @return a large set of phonenumbers with a \n fixated on the end of each number.
 */
	public String phoneout(){
		String out = "";
		ArrayList<PersonInterface> dude;
		
		dude = peps.sort();
		
		for(PersonInterface s: dude){
			out += s.getPhone() + '\n';
		}
		
		return out;
	}


}
