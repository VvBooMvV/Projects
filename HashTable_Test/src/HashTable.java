import java.util.ArrayList;
import java.util.LinkedList;



/**

 * @author Harry Kim
 * @param <plist>
 * 
 * this information is where the buckets of information is being stored.  it will take the hashcode and mod it by 10.  each of these information will
 * will be stored according to the hashcode provided with in the person class.  this class also sorts the information going through each linked list within the array
 * the order will change if the array list has a different size and the hashcode is modded by that specific array size.  this class also handles if the
 * phone number (based on the hashcode) will be tested to see if the item exist.  the hashcode position will narrow the searching method.
 *
 */

public class HashTable implements HashTableInterface<PersonInterface>{
	
	ArrayList<LinkedList<PersonInterface>> peop;
	int numpep;
	
	public HashTable(){
		

	
		
		peop = new ArrayList<LinkedList<PersonInterface>>(10);
		
		for(int i = 0; i< 10;i++){
			peop.add(new LinkedList<PersonInterface>());
		}
		
	}
	/**
	 * Adds an element to this set.  Adds to the appropriate LinkedList by taking the Person HashCode()
	 * and mod (%) by the table size.
	 * @param p a Person to add to the HashTable
	 * @return the number of People currently in HashTable
	 */
	@Override
	public int add(PersonInterface p) {
		
		int place = p.hashCode() % 10;
		
		peop.get(place).add(p);
		
		return numpep++;
		
	}
	/**
	 * Tests to see if the Person is in the HashTable.
	 * @param p a Person Object
	 * @return true if Person is in the HashTable, false if not
	 */
	@Override
	public boolean contains(PersonInterface p) {
		
		int num = p.hashCode() % 10;
		
		for(int i =0; i<peop.get(num).size(); i++){
			if(peop.get(num).get(i).hashCode() == p.hashCode()){
				return true;
			}
		}
		
		return false;
		
	}
	/**
	 * Returns the Person object that corresponds to the key
	 * @param key the key (phone number) for the Person object
	 * @return the Person object if the key is in the hash table, null if not
	 */
	@Override
	public PersonInterface getValue(String key) {
		PersonInterface out = null ;
		
		if(Person.isValidKey(key)){
		int pos = Person.hashKey(key) % 10;
		
		for(int i =0; i<peop.get(pos).size(); i++){
			if(peop.get(pos).get(i).getPhone().equals(key)){
				out = peop.get(pos).get(i);
			}
		}
		return out;
		}
		else{
		return out;
		}
	}
	/**
	 * Tests to see if a Person is in the HashTable based on the key
	 * @param key a Person Object key (phone number)
	 * @return true if Person is in the HashTable, false if not
	 */
	@Override
	public boolean contains(String key) {

		int num = Person.hashKey(key) % 10;
		
		for(int i =0; i<peop.get(num).size(); i++){
			if(peop.get(num).get(i).getPhone().equals(key)){
				return true;
			}
		}
		
		
		return false;
	}
	/**
	 * Puts the People in the hashtable in a sorted ArrayList, sorted by key (phone number)
	 * Use Collections.sort with a Comparator that sorts by phone number
	 * @return ArrayList of People in hashtable in sorted order by key (phone number)
	 */
	@Override
	public ArrayList<PersonInterface> sort() {
		
		ArrayList<PersonInterface> out = new ArrayList<PersonInterface>();
		for(int i = 0; i< peop.size(); i++){
			if(peop.get(i).isEmpty()){
				continue;
			}
			else{
				for(int j = 0; j < peop.get(i).size(); j++){
					out.add(peop.get(i).get(j));
				}
			}
		}
		return out;
	}

}
