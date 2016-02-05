import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Vector;


/**
 * 
 * @author Harry Kim
 * 
 * in the data manager class will allow the user to add new friends, and edges from reading a file.  then manually allow user to add new friends or edges of friends to the friend graph class
 * it stores a list of friends so that it can easiy access a list of friends to be passed into a friend graph class.  it also can list out all the participants of this program.
 *
 */
public class DataManager implements DataManagerInterface{
	
	
	Scanner in;
	FriendGraph graph = new FriendGraph();
	ArrayList<Friend> part = new ArrayList<Friend>();
	boolean fileopened = false;
	boolean pfileopened = false;
	
	public boolean isFileOpen(){
		boolean out = false;
		if(fileopened == true && pfileopened == true){
			out = true;
		}
		return out;
	}

	/**
	 * Returns the friends of the friends of a participant
	 * @param name String of participant in the form 
	 * "<first name> <last name> of <hometown>"
	 * Example: "Ann Abbott of San Diego, CA"
	 * @return An ArrayList of all the friends of friends in alphabetical 
	 * order by last name.  The ArrayList does not include the participant or the friends 
	 * of the participant.
	 * Example: [Karen Kirkland of Honolulu, HI, Maple Myers of Cleveland, OH,
	 * Nathaniel Nestle of Hershey, PA, . . ., Yolonda Yellow of Franklin, TN]
	 */
	@Override
	public ArrayList<String> friendsOfFriends(String name) {
		
		//main guy
		Friend test = null;
		for(int i = 0; i < part.size(); i++){
			if(part.get(i).toString().equalsIgnoreCase(name)){
				test = part.get(i);
			}
		}
		
		TreeSet<Friend> pep = new TreeSet<Friend>();
		//linked list of friends
		LinkedList<Friend> get = graph.edgeSetList(test);
		
		//get main guys freinds friends
		for(int j = 0; j< get.size(); j++){
			LinkedList<Friend> other = graph.edgeSetList(get.get(j));
			
			for(int m = 0; m< other.size(); m ++){
				pep.add(other.get(m));
			}
			
			
		}
		
		LinkedList<Friend> out = new LinkedList<Friend>();
		
//		Collections.sort(pep);
		ArrayList<String> trueout = new ArrayList<String>();
		//add to list to remove friends
		for(Friend a : pep){
				out.add(a);
		}
		get.add(test);
		//take out test friend get and test
		for(int s = 0; s < get.size(); s++){
			for(int m = 0; m < out.size(); m++){
				if(out.get(m).toString().equalsIgnoreCase(get.get(s).toString())){
					out.remove(get.get(s));
				}
			}
		}
		
		Collections.sort(out);
		
		for(Friend g : out){
			trueout.add(g.toString());
		}

		
		return trueout;
	}
	/**
	 * Returns the friends of the friends of a participant
	 * @param fname participants first name
	 * @param lname participants last name
	 * @param hometown participants home town
	 * @return An ArrayList of all the friends of friends in alphabetical 
	 * order by last name.  The ArrayList does not include the participant or the friends 
	 * of the participant.
	 * Example: [Karen Kirkland of Honolulu, HI, Maple Myers of Cleveland, OH,
	 * Nathaniel Nestle of Hershey, PA, . . ., Yolonda Yellow of Franklin, TN]
	 */
	@Override
	public ArrayList<String> friendsOfFriends(String fname, String lname, String hometown) {

		//main guy
				Friend test = new Friend(fname, lname, hometown);
				for(int i = 0; i < part.size(); i++){
					if(part.get(i).toString().equalsIgnoreCase(test.toString())){
						test = part.get(i);
					}
				}
				
				TreeSet<Friend> pep = new TreeSet<Friend>();
				//linked list of friends
				LinkedList<Friend> get = graph.edgeSetList(test);
				
				//get main guys freinds friends
				for(int j = 0; j< get.size(); j++){
					LinkedList<Friend> other = graph.edgeSetList(get.get(j));
					
					for(int m = 0; m< other.size(); m ++){
						pep.add(other.get(m));
					}
					
					
				}
				
				LinkedList<Friend> out = new LinkedList<Friend>();

				ArrayList<String> trueout = new ArrayList<String>();
				//add to list to remove friends
				for(Friend a : pep){
						out.add(a);
				}
				get.add(test);
				//take out test friend get and test
				for(int s = 0; s < get.size(); s++){
					for(int m = 0; m < out.size(); m++){
						if(out.get(m).toString().equalsIgnoreCase(get.get(s).toString())){
							out.remove(get.get(s));
						}
					}
				}
				
				Collections.sort(out);
				
				for(Friend g : out){
					trueout.add(g.toString());
				}

				
				return trueout;
	}
	/**
	 * Returns the friends of a participant
	 * @param name String of participant in the form 
	 * "<first name> <last name> of <hometown>"
	 * Example: "Ann Abbott of San Diego, CA"
	 * @return  An ArrayList of all the friends of a participant in alphabetical 
	 * order.  The ArrayList does not include the participant.
	 * Example: [Eric Epstein of Alexandria, VA, Fred Flinstone of Galveston, TX,
	 * Ida Ignome of Carson City, NV, . . ., Patricia Pagan of Fargo, ND]
	 */
	@Override
	public ArrayList<String> listFriends(String name) {
		
		Friend dude = null;
		for(int i = 0; i < part.size(); i++){
			if(part.get(i).toString().equalsIgnoreCase(name)){
				dude = part.get(i);
			}
		}
		
		
		ArrayList<String> out = new ArrayList<String>();

		LinkedList<Friend> test = graph.edgeSetList(dude);
		

		Collections.sort(test);

		
		for(int i = 0; i < test.size(); i++){
			out.add(test.get(i).toString());
		}
		

		return out;
		
	}
	/**
	 * Returns the friends of a participant
	 * @param fname participants first name
	 * @param lname participants last name
	 * @param hometown participants hometown
	 * @return  An ArrayList of all the friends of a participant in alphabetical 
	 * order.  The ArrayList does not include the participant.
	 * Example: [Eric Epstein of Alexandria, VA, Fred Flinstone of Galveston, TX,
	 * Ida Ignome of Carson City, NV, . . ., Patricia Pagan of Fargo, ND]
	 */
	@Override
	public ArrayList<String> listFriends(String fname, String lname, String hometown) {
		Friend dude = new Friend(fname, lname, hometown);
		
		ArrayList<String> out = new ArrayList<String>();

		LinkedList<Friend> test = graph.edgeSetList(dude);
		

		Collections.sort(test);

		
		for(int i = 0; i < test.size(); i++){
			out.add(test.get(i).toString());
		}
		

		return out;
		
		
	}
	/**
	 * Returns the friends of a participant
	 * @param f a reference to a Friend object
	 * @return  An ArrayList of all the friends of a participant in alphabetical 
	 * order.  The ArrayList does not include the participant.
	 * Example: [Eric Epstein of Alexandria, VA, Fred Flinstone of Galveston, TX,
	 * Ida Ignome of Carson City, NV, . . ., Patricia Pagan of Fargo, ND]
	 * Used for testing
	 */
	@Override
	public ArrayList<String> listFriends(Friend f) {
		
		ArrayList<String> out = new ArrayList<String>();

		
		LinkedList<Friend> test = graph.edgeSetList(f);
		
		
		Collections.sort(test);
	
		
		for(int i = 0; i < test.size(); i++){
			out.add(test.get(i).toString());
		}
		
		
		
		return out;

	}
	/**
	 * Returns the first name, last name and hometown in an ArrayList
	 * @param name String of participant in the form 
	 * "<first name> <last name> of <hometown>"
	 * Example: "Ann Abbott of San Diego, CA"
	 * @return An ArrayList of first name, last name and hometown
	 * Example: [Ann, Abbott, San Diego, CA]
	 */
	@Override
	public ArrayList<String> getProfile(String name) {
		
		LinkedList<Friend> pep = graph.getVertices();
		
		Collections.sort(pep);
		
		
		Friend split = null;
		for(Friend a : pep){
			if(a.toString().equalsIgnoreCase(name)){
				split = a;
			}
		}
		
		ArrayList<String> out = new ArrayList<String>();
		
		out.add(split.getFname());
		out.add(split.getLname());
		out.add(split.getCity());
		
		return out;
	}
	/**
	 * Returns a vector of Participants for populating JComboBoxes
	 * in alphabetical order
	 * @return vector of Participants for populating JComboBoxes
	 * in alphabetical order
	 */
	@Override
	public Vector<String> vectorOfParticipants() {
		LinkedList<Friend> convert = graph.getVertices();
		Vector<String> out = new Vector<String>();
		Collections.sort(convert);
		for(Friend a : convert){
			
			out.add(a.toString());
			
		}
		
		return out;
	}
	/**
	 * Adds a participant (vertex) to the Friend Graph
	 * @param fName first name, Example: "Ann"
	 * @param lName lastname, Example: "Abbott"
	 * @param city Hometown, HomeState, Example: "San Diego, CA"
	 */
	@Override
	public void addParticipant(String fName, String lName, String city) {
		Friend newguy = new Friend(fName, lName, city);
		part.add(newguy);
		graph.addVertex(newguy);
		
	}
	/**
	 * Add a friend to a participant (edge)
	 * @param profile String of participant in the form 
	 * "<first name> <last name> of <hometown>"
	 * Example: "Ann Abbott of San Diego, CA"
	 * @param newFriend String of friend in the form 
	 * "<first name> <last name> of <hometown>"
	 * Example: "Larry Lobster of Knox, ME"
	 */
	@Override
	public void addFriend(String profile, String newFriend) {
		Friend part1 = null;
		Friend targ = null;
		
		for(int j = 0; j < part.size(); j++){
			if(part.get(j).toString().equalsIgnoreCase(profile)){
				part1 = part.get(j);
			}
			else if(part.get(j).toString().equalsIgnoreCase(newFriend)){
				targ = part.get(j);
			}
		}
		
		graph.addEdge(part1, targ);
	}
	/**
	 * Add a friend to a participant (edge)
	 * @param profileFname participants first name 
	 * @param profileLname participants last name
	 * @param profileHometown participants hometown
	 * @param newFriendFname first name of new friend
	 * @param newFriendLname last name  of new friend
	 * @param newFriendHometown home town of new friend
	 * 
	 */
	@Override
	public void addFriend(String profileFname, String profileLname, String profileHometown, 
							String newFriendFname, String newFriendLName, String newFriendHometown) {
		
		
		Friend part1 = new Friend(profileFname, profileLname, profileHometown);
		Friend targ = new Friend(newFriendFname, newFriendLName, newFriendHometown);
	
		graph.addEdge(part1, targ);
		
	}

	/**
	 * Used to populate the participants (verticies) in the FriendGraph
	 * for testing purposes
	 * @param participantsFile File reference
	 * @throws FileNotFoundException
	 */
	@Override
	public void populateParticipants(File participantsFile)
			throws FileNotFoundException {
		ArrayList<String> txtfile = new ArrayList<>();
		
		try {
			in = new Scanner(participantsFile);
			while(in.hasNext()){
				String line = in.nextLine();
				txtfile.add(line);

			}
			
			for(String a : txtfile){
				String[] word = a.split(":");
				String first ="", last="", city="";
				
				first = word[0];
				last = word[1];
				city = word[2];
				
				
				
				Friend dude = new Friend(first, last, city);
				
				part.add(dude);
				
				graph.addVertex(dude);
				
			}
			
		} catch (FileNotFoundException e) {
			
		}
		
		if(graph.isVertexEmpty()){
			pfileopened = true;
		}
		
	}
	/**
	 * Used to populate the friends (edges) in the FriendGraph
	 * for testing purposes
	 * @param friendsFile File reference
	 * @throws FileNotFoundException
	 */
	@Override
	public void populateFriends(File friendsFile) throws FileNotFoundException {
		ArrayList<String> txtfile = new ArrayList<>();
		
		try {
			in = new Scanner(friendsFile);
			while(in.hasNext()){
				String line = in.nextLine();
				txtfile.add(line);

			}
			
			
			for(int i = 0; i < txtfile.size(); i++){
				String[] word = txtfile.get(i).split(":");
				ArrayList<String> names =new ArrayList<String>();
				String last = "", first = "";
				int num = 0;

				
				//split first name and number of friends
				//add targets to arraylist
				
				for(int m = 0; m < word.length; m++){
					if(m == 0){
						first = word[m];
						
					}
					else if(m == 1){
						last = word[m];
						
					}
					else if(m == 2){
						num = Integer.parseInt(word[m]);
					}
					else{
						names.add(word[m]);
					}
				}
				
				
				
				//split friends
				
				ArrayList<String> tn = new ArrayList<String>();
				String temp1 ="", temp2 ="", temp3 = "";
				
				for(int h = 0; h < names.size(); h++){
					if(h%2 == 0){
						temp1 = names.get(h);
					}
					else{
						temp2 = names.get(h);
						temp3 = temp2 + " "+ temp1;
						tn.add(temp3);
					}
					
				}
				
				//get friend objects
				
				ArrayList<Friend> target = new ArrayList<Friend>();
				String ss = last + " " + first;
				Friend source = null;
				
				for(Friend a : part){
					if(a.getName().equalsIgnoreCase(ss)){
						source = a;
					}
				}
				
				for(int y = 0; y < tn.size(); y++){
					for(int r = 0; r < part.size(); r++){
						if(part.get(r).getName().equalsIgnoreCase(tn.get(y))){
							target.add(part.get(r));
							
						}
					}
				}
				
				//add edges
				
				for(int g = 0; g <num; g++){
					
				
					graph.addEdge(source, target.get(g));
				}
				
				
	

			}
			
		} catch (FileNotFoundException e) {
			
		}
		if(graph.isEdgeEmpty()){
			fileopened = true;	
		}
		
	}
	


}
