import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 
 * @author Harry Kim
 * 
 * <br> This program should manage all of the data being used by the program.  each method is described below.  the data is placed within a set tree.
 * the methods will either check words, add the word to the dictionary, or write to a file.
 *
 */
public class SpellCheckerManager implements SpellCheckerManagerInterface{
	
	TreeSet<String> word = new TreeSet<String>();
	Scanner in;
	
	/**
	 * The checkWord method attempts to find its argument in a dictionary of words.
	 * @param wordToCheck a String
	 * @return true if the argument is found, false otherwise
	 * @throws InvalidSpellingException if any of the words contain invalid characters
	 */

	public boolean checkWord(String wordToCheck)
			throws InvalidSpellingException {
		
		
		if(!wordToCheck.matches("[a-zA-Z]+")){
			throw new InvalidSpellingException("One of the word(s) is inputed incorrectly.  \nWords must contain a-z or A-Z, and a space to seperate each word");
		}
		return word.contains(wordToCheck);			
				
		
	}
	/**
	 * The checkWords method attempts to find all the words in the string in a dictionary of words
	 * @param wordsToCheck a String of words to be checked
	 * @return an ArrayList of Strings of words not found in the dictionary of words, null if all words in dictionary
	 * @throws InvalidSpellingException if any of the words contain invalid characters
	 */
	public ArrayList<String> checkWords(String wordsToCheck)
			throws InvalidSpellingException {
		
		
		String[] split = wordsToCheck.split("\\s+");

		
		ArrayList<String> lout = new ArrayList<String>();
		for(String a : split){
			
			if(!checkWord(a)){
				lout.add(a);
			}

		}
		if(lout.size() == 0){
			lout = null;
		}

		
		return lout;
	}
	/**
	 * 
	 * @return the number of words in the dictionary.
	 */
	public int getlistsize(){
		return word.size();
	}
	/**
	 * The addWord method adds its argument to a dictionary of words.  Presumably the user 
	 * has spell-checked the argument and found it missing from the dictionary, and wants
	 * to include it in the dictionary.
	 * @param wordToAdd the word to add to the dictionary
	 * @throws InvalidSpellingException if the word contain invalid characters
	 * @throws DuplicateWordException if the word is already in the dictionary
	 */
	
	
	public void addWord(String wordToAdd) throws DuplicateWordException,
			InvalidSpellingException {
		String[] split;
		if(wordToAdd != null){
			split = wordToAdd.split("\\s+");
		}
		else{
			split = new String[0];
		}
		
		
		for(int i = 0; i< split.length; i++){
			if(!split[i].matches("[a-zA-Z]+")){
				throw new InvalidSpellingException("One of the word(s) is inputed incorrectly.  \nWords must contain a-z or A-Z, and a space to seperate each word");
			}
			if(checkWord(split[i])){
				throw new DuplicateWordException("The word [" + split[i] + "] already exist.  It was not added");
			}
			else{
				word.add(split[i]);
			}
		}
		
		
		
	}
	/**
	 * Reads from a file and populates the binary search tree
	 * Must determine if it is a text file or binary file.  We will use a very trivial
	 * determination - if the file ends in .bin it is a binary file, if it ends
	 * in .txt it is a text file.
	 * Use the private getExtension method
	 * @param input the file to read from
	 * @return true if successful read, false if file could not be read from
	 * @throws IOException if the file is not found or accessible
	 * @throws InvalidSpellingException if any of the words contain invalid characters
	 * @throws DuplicateWordException if of the words is already in the dictionary
	 */
	public boolean readDictionary(File input) throws DuplicateWordException,
			InvalidSpellingException {
		
		String ext = getExtension(input);
		
		if(ext.equals("bin") && input.canRead()){
			
			readBinaryFile(input);
			
			return true;
		}
		if(ext.equals("txt") && input.canRead()){
		
			readTextFile(input);
			
			return true;
		}
		
		
		return false;
	}
	/**
	 * Writes binary search tree to a file
	 * Must determine if it is a text file or binary file.  We will use a very trivial
	 * determination - if the file ends in .bin it is a binary file, if it ends
	 * in .txt it is a text file.
	 * Use the private getExtension method
	 * @param output the file to write to
	 * @return true if successful write, false if file could not be written to
	 * @throws IOException if the file is not found or accessible
	 */
	public boolean writeDictionary(File input) throws IOException {

	String ext = getExtension(input);
		
		if(ext.equals("bin") && input.canRead()){
			
			writeBinaryFile(input);
			
			return true;
		}
		if(ext.equals("txt") && input.canRead()){
		
			writeTextFile(input);
			
			return true;
		}
		

		return false;
	}
	/**
	 * Returns the extension of a file in the form FILENAME.xxx
	 * In this case the extention is the xxx
	 * @param file the name of the file
	 * @return the extention of the file
	 * 
	 * NOTE: This should be a private method, but private methods aren't allowed in
	 * interfaces.  I have given you the code for this method in comments.
	 */	
	
	public String getExtension(File file) {
	    String ext = null;
	    String s = file.getName();
	    int i = s.lastIndexOf('.');

	    if (i > 0 &&  i < s.length() - 1) {
	        ext = s.substring(i+1).toLowerCase();
	    }
	    return ext;
	}
	    
	
	/**
	 * the listDictionary method creates a String with the words in the current dictionary to the console 
	 * in alphabetic order, one word per line
	 * @return a String that lists all the words in the dictionary in alphabetic order
	 */
	public String listDictionary() {
		String out = "";
		for(String a : word){
			out += a + "\n";
		}
		return out;
	}
	/**
	 * Stores the binary search tree in text format, alphabetically
	 * @param output the file to write to
	 * @return true if successful write, false if file could not be written to
	 */
	PrintWriter out;
	public boolean writeTextFile(File output) {

		try {
			out = new PrintWriter(output);
			
			for(String a : word){

				out.println(a);
			}
			
			out.close();

		} catch (FileNotFoundException e) {
			
		}
		
		
		return false;
	}
	/**
	 * Reads from a text file and populates the binary search tree
	 * @param input the file to read from
	 * @return true if successful read, false if file could not be read from
	 * @throws InvalidSpellingException if any of the words contain invalid characters
	 * @throws DuplicateWordException if of the words is already in the dictionary
	 */
	
	public boolean readTextFile(File input) throws DuplicateWordException,
			InvalidSpellingException {
		
		
		ArrayList<String> txtfile = new ArrayList<>();
			
			try {
				in = new Scanner(input);
				while(in.hasNext()){
					String line = in.nextLine();
					txtfile.add(line);

				}
				
				for(String a : txtfile){
					addWord(a);
				}
				
			} catch (FileNotFoundException e) {
				
			}
		
		return false;
	}
	/**
	 * Stores the dictionary in a binary format
	 * @param output the location on the hard drive to store binary file
	 * @return true if successful write, false if file could not be written to
	 */
	public boolean writeBinaryFile(File output) {
		boolean test = false;
		try {
			ObjectOutputStream	out = new ObjectOutputStream(new FileOutputStream(output));
			out.writeObject(word);
			test = true;
			out.close();

		} catch (FileNotFoundException e) {
		
		} catch (IOException e) {
		
		
		}
		
		return test;
	}
	/**
	 * Reads from a binary file and populates the binary search tree
	 * Must use the writeBinaryFile for this file before calling readBinaryFile for this file
	 * @param input the File to be read from
	 * @return true if successful read, false if file could not be read from
	 * @throws InvalidSpellingException if any of the words contain invalid characters
	 * @throws DuplicateWordException if of the words is already in the dictionary
	 */
	
	@SuppressWarnings("unchecked")
	public boolean readBinaryFile(File input) throws DuplicateWordException,
			InvalidSpellingException {
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(input));
			
			
				word = ((TreeSet<String>) in.readObject());
				in.close();
				return true;
			
		} catch (FileNotFoundException e) {
			
			
		} catch (IOException e) {


		} catch (ClassNotFoundException e) {
		
		}
		
		return false;
	}

}
