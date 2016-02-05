
import java.util.LinkedList;


/**
 * 
 * @author Harry Kim
 * this class will store all of the data of friends in the class and also hold a backup list of friends.  it will store the first name, last name, and hometown of the participants.
 * and also provide a string to be used in the gui.
 *
 */


public class Friend implements Comparable<Friend>{
	
	FriendGraph other = new FriendGraph();
	
	String 	first,
			last,
			city;
	
	LinkedList<Friend> list = new LinkedList<Friend>();
	
	public Friend(String fname, String lname, String place) {
		first = fname;
		last = lname;
		city = place;
	}
	
	public String getName(){
		String out, tempf, templ;
		
		tempf = first;
		templ = last;
		out = templ + " " + tempf;
				
		return out;
	}
	public void addFriendtoList(Friend a){
		if(list.contains(a)){
			
		}else
		list.add(a);
	}
	public boolean contains(Friend f){
		if(list.contains(f)){
			return true;
		}
		else return false;
	}
	public LinkedList<Friend> getListofFriends(){
		
		return list;
	}
	
	public void setFirstName(String fname){
		first = fname;
	}
	public void setLastName(String lname){
		last = lname;
	}	
	public void setCity(String place){
		city = place;
	}
	public String getFname(){
		return first;
	}
	public String getLname(){
		return last;
	}
	public String getCity(){
		return city;
	}
	public boolean removefriend(Friend f){
		if(list.contains(f)){
			
			list.remove(f);
			return true;
		}
		return false;
	}
	public Friend getTargetFriend(int i){
		
		return list.get(i);
	}
	public int getContainsReferenceNum(Friend test){
		int a = -1;
		
		if(list.contains(test)){
			a = list.indexOf(test);
		}
		
		return a;
	}
	
	public String toString(){
		
		
		String out = first + " " + last+ " of " + city;
		
		return out;
	}
	
	@Override
	public int compareTo(Friend f) {
		
		if(f.getName().compareToIgnoreCase(this.getName()) < 0){
			return 1;
		}
		else if(f.getName().compareToIgnoreCase(this.getName()) > 0){
			return -1;
		}
		else{
			return 0;
		}
		

	}
}
