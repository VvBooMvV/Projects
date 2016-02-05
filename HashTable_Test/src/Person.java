
/**
 *
 * @author Harry Kim
 * This is where all the information for each person is being held.  it will store the First Name, Last Name, Phone Number, and Address as a string
 * this will also store and generate the hashcode for the specific person. the hashcode is generated as integer.
 *
 */
public class Person implements PersonInterface{

	String 	fn,
			ln,
			num,
			addy;
	int hashcode;
	
	public Person(){
	
	}
	public Person(String string, String string2, String string3, String string4) {
		
		fn = string;
		ln = string2;
		num = string3;
		addy = string4;
		createhash();
		
	}
	/**
	 * this is where the hash code will generate for the person object.
	 */
	public void createhash(){
		int areaCode,
		exchangeCode,
		extensionCode,
		p1 = 23,
		p2 = 31;
	String[] split = new String[num.length()];
	for(int i = 0; i < num.length(); i++){
		split[i] = Character.toString(num.charAt(i));
	
	}
	String 	ac = "",
			ec ="",
			exc = "";
	for(int j = 0; j < split.length; j++){
		if(j ==0) continue;
		else if(j > 0 && j < 4){
			ac += split[j];
		}
		else if(j == 4) continue;
		else if(j > 4 && j < 8){
			ec += split[j];
		}
		else if(j == 8) continue;
		else{
			exc += split[j];
		}
//		System.out.print(split[j]);
	}
	areaCode = Integer.parseInt(ac);
	exchangeCode = Integer.parseInt(ec);
	extensionCode = Integer.parseInt(exc);
	
	hashcode = (p1 * (areaCode + p2 * exchangeCode) + extensionCode);
	
//	System.out.println(ac);
//	System.out.println(ec);
//	System.out.println(exc);
	
//	System.out.println(hashcode);
	}
	/**
	 * Return first name of person
	 * @return first name of person
	 */
	@Override
	public String getFname() {
		
		return fn;
	}
	/**
	 * Return last name of person
	 * @return last name of person
	 */
	@Override
	public String getLname() {
		
		return ln;
	}
	/**
	 * Return phone number of person
	 * @return phone number of person
	 */
	@Override
	public String getPhone() {
		
		return num;
	}
	/**
	 * Return address of person
	 * @return address of person
	 */
	@Override
	public String getAddress() {
		
		return addy;
	}
	/**
	 * compute the hash code of Person
	 * key is phone number, phone number is a string in the form: <br/>
	 *      (XXX)XXX-XXXX
	 * hashcode = (p1 * (areaCode + p2 * exchangeCode) + extensionCode)
	 * p1 is the prime 23 and p2 is the prime 31
	 * In the phone number (555)123-4567
	 * areaCode = 555
	 * exchangeCode = 123
	 * extensionCode = 4567
	 * @return hash code of phone number
	 */
	public int hashCode(){
		//created hash code in method above and stored it in this class. it will initiate during constructions.
		return hashcode;
	}
	

	/**
	 * compute the hash code of a phone number
	 * phone number, phone number is a string in the form: <br/>
	 *      (XXX)XXX-XXXX
	 * hashcode = (p1 * (areaCode + p2 * exchangeCode) + extensionCode)
	 * p1 is the prime 23 and p2 is the prime 31
	 * In the phone number (555)123-4567
	 * areaCode = 555
	 * exchangeCode = 123
	 * extensionCode = 4567
	 * @param key the phone number
	 * @return hash code of phone number
	 */
	//STUDENT create this as a static method
	
	public static int hashKey(String key){
		
		int p1 = 23,
			p2 = 31;
	String[] split1 = new String[key.length()];
	for(int i = 0; i < key.length(); i++){
		split1[i] = Character.toString(key.charAt(i));
	
	}
	String 	ac = "",
			ec ="",
			exc = "";
	for(int j = 0; j < split1.length; j++){
		if(j ==0) continue;
		else if(j > 0 && j < 4){
			ac += split1[j];
		}
		else if(j == 4) continue;
		else if(j > 4 && j < 8){
			ec += split1[j];
		}
		else if(j == 8) continue;
		else{
			exc += split1[j];
		}
	}
	int ac1 = Integer.parseInt(ac);
	int aCode = Integer.parseInt(ec);
	int ecode = Integer.parseInt(exc);
	
	int newhashcode = (p1 * (ac1 + p2 * aCode) + ecode);
	
		return newhashcode;
	}
	
	/**
	 * Checks if this String is a valid key for a Person object
	 * A valid key is in the form (XXX)XXX-XXXX, where X is a numeric
	 * @param s String being checked for validity
	 * @return true if s is in form (XXX)XXX-XXXX, where X is a numeric
	 */
	//STUDENT create this as a static method
	public static boolean isValidKey(String s){

		if(s.charAt(0) == '(' && s.charAt(4) == ')' && s.charAt(8) == '-'){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Compares the phone numbers of each of the Person objects
	 * phone number is a string in the form: (XXX)XXX-XXXX
	 * @param p Person object
	 * @return true if phone numbers are the same or else false
	 */
	@Override
	public boolean equals(PersonInterface p) {
		if (p == null) return false;
		if (getClass() != p.getClass()) return false;
		Person other = (Person) p;
		return hashcode == other.hashcode ;
		
	}
	
	
	/**
	 * Returns the key for the Person object which is in the form:
	 *      (XXX)XXX-XXXX
	 * @param p Person object
	 * @return the phone number in the form (XXX)XXX-XXXX
	 */
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return num;
	}

}
