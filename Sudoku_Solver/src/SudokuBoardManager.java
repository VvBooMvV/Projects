import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;
/**
 *
 * @author Harry Kim
 * 
 *  This class will manipulate, access, update, and store data accordingly. 
 *  the setValueAt(r,c,v) will set the value at array that will be sent
 *  the getValueAt(r,c) will return the value at the corresponding spot when called by the GUI
 *  the displayPossibleValues(r,c) will return all possible values of the spot that is being called.
 *  	the display possible will add each value into an array and subtract each value in a fixed array.  it will return an int[] of numbers greater than 0
 *  the toString() will return all the values read from the sudoku file and concatenated into one string
 *  the newgame() will generate and add the value to the array read from the sudoku file.
 *  
 */

public class SudokuBoardManager implements SudokuBoardManagerInterface{
	
	Scanner input;
	ArrayList<String> txtfile = new ArrayList<>();
	String[][] stringvalue = new String[9][9];
	int[][] value = new int[9][9];
	
	
	/** Set a value (v) at the row (r) and column (c).  If the value is valid for this row and column, 
	 * the value will be placed at that location.  If the value is invalid for this row and column,
	 * the value will not be placed at that location.
	 * @param r row in the sudoku puzzle
	 * @param c column in the sudoku puzzle
	 * @param v value to place in the row,column
	 * @throws InputOutOfRangeException if the values for the row or column are invalid (> 9 or < 1)
	 * @throws ValueNotValidException if the value is invalid for this (row,column) entry in the puzzle
	 */
	@Override
	public void setValueAt(int r, int c, int v)
			throws InputOutOfRangeException, ValueNotValidException {
		// TODO Auto-generated method stub
		try{
			//this will generate an error if the input is wrong
			if(r <= 0 || r > 9){
				throw new InputOutOfRangeException("The Row is Not Between 1 - 9. Please Double Check the Value.");
			}
			else if (c > 9 || c <= 0 ){
				throw new InputOutOfRangeException("The Column is Not Between 1 - 9. Please Double Check the Value.");
			}
			else if (v > 9 || v < 0){
				throw new ValueNotValidException("The Value is Not Valid.  Pick a Number Between 1 - 9.");	
			}
			else if (v != 0 && value[r-1][c-1] > 0){
				throw new ValueNotValidException("This spot has been taken.  Please Try Again.");
			}//this will store the correct value into the array
			else{
				value[r-1][c-1] = v;
			}
			
		}
		//errors when exception is thrown
		catch(InputOutOfRangeException except){
			JOptionPane.showMessageDialog(null, except.getMessage());
			
		}
		catch(ValueNotValidException except){
			JOptionPane.showMessageDialog(null, except.getMessage());
			
		}
	
		
	}

	/** Get the value at the row (r) and column (c).  
	 * @param r row in the sudoku puzzle
	 * @param c column in the sudoku puzzle
	 * @throws InputOutOfRangeException if the values for the row or column are invalid (> 9 or < 1)
	 * @return the value for this row,column in the puzzle, returns 0 if value was not previously set
	 */	
	@Override
	public int getValueAt(int r, int c) throws InputOutOfRangeException {
		// TODO Auto-generated method stub
		int number = 0;

		number = value[r-1][c-1];

		return number;
		
	}
	/**
	 * Determines all possible values for this (row, column) entry in the puzzle, based on the
	 * rules of Sudoku
	 * @param r row in the sudoku puzzle
	 * @param c column in the sudoku puzzle
	 * @return an array of integers representing all possible values for this (row,column) entry 
	 * in the puzzle in numeric order, i.e. 3 6 9
	 * @throws InputOutOfRangeException if the values for the row or column are invalid (> 9 or < 1)
	 */
	@Override
	public int[] displayPossibleValues(int r, int c)
			throws InputOutOfRangeException {
		// TODO Auto-generated method stub
		
		ArrayList<Integer> begin = new ArrayList<Integer>();
		
		
		
		try{
			//this will check if the value is correct and remove anything to be returned.
			if(r < 0 || r > 9 || r == 0){
				begin.add(1);
				begin.add(2);
				begin.add(3);
				begin.add(4);
				begin.add(5);
				begin.add(6);
				begin.add(7);
				begin.add(8);
				begin.add(9);
				throw new InputOutOfRangeException("The Row is Not Between 1 - 9. Please Double Check the Value.");
			}
			else if (c > 9 || c < 0 || c == 0){
				begin.add(1);
				begin.add(2);
				begin.add(3);
				begin.add(4);
				begin.add(5);
				begin.add(6);
				begin.add(7);
				begin.add(8);
				begin.add(9);
				throw new InputOutOfRangeException("The Column is Not Between 1 - 9. Please Double Check the Value.");
			}
			else if (value[r-1][c-1] > 0){
				begin.add(1);
				begin.add(2);
				begin.add(3);
				begin.add(4);
				begin.add(5);
				begin.add(6);
				begin.add(7);
				begin.add(8);
				begin.add(9);
				throw new InputOutOfRangeException("That Spot Has Been Taken.  Please Try Again");
			}
			//this will check the rows, column, and each box for possible values
			//first it will check if in row 1,2,3 then column 1,2,3
			//when quadrant is found it will add the numbers it stores in that quadrant
			else{
				
				//this will check for the row values
				for(int j = 0; j < 9; j++){
					if(value[r-1][j] > 0){
						begin.add(value[r-1][j]);
					}
				}
				
				//this checks for the column values
				
				for(int i = 0; i< 9; i++){
					if (value[i][c-1] >  0){
						begin.add(value[i][c-1]);
					}
				}
				
				//this will check quadrants 1,2,3
				if(r >0 && r < 4){
					if (c >0 && c< 4){
						//quad = 1;
						
						for(int q1r = 0; q1r < 3; q1r++){
							for (int q1c = 0; q1c < 3; q1c++){
								if(value[q1r][q1c] > 0){
									begin.add(value[q1r][q1c]);
								}
							}
						}
						
					}
					else if (c > 3 && c < 7){
						//quad = 2;
						for(int q2r = 0; q2r < 3; q2r++){
							for (int q2c = 3; q2c < 6; q2c++){
								if(value[q2r][q2c] > 0){
									begin.add(value[q2r][q2c]);
								}
							}
						}
						
					}
					else{
						//quad = 3;
						for(int q3r = 0; q3r < 3; q3r++){
							for (int q3c = 6; q3c < 9; q3c++){
								if(value[q3r][q3c] > 0){
									begin.add(value[q3r][q3c]);
								}
							}
						}
					}
				}
				
				//this will check quadrants 4,5,6
				else if(r> 3 && r < 7){
					if (c> 0 && c < 4){
						//quad = 4;
						for(int q4r = 3; q4r < 6; q4r++){
							for (int q4c = 0; q4c < 3; q4c++){
								if(value[q4r][q4c] > 0){
									begin.add(value[q4r][q4c]);
								}
							}
						}
					}
					else if (c > 3 && c < 7){
						//quad = 5;
						for(int q5r = 3; q5r < 6; q5r++){
							for (int q5c = 3; q5c < 6; q5c++){
								if(value[q5r][q5c] > 0){
									begin.add(value[q5r][q5c]);
								}
							}
						}
					}
					else{
						//quad = 6;
						for(int q6r = 3; q6r < 6; q6r++){
							for (int q6c = 6; q6c < 9; q6c++){
								if(value[q6r][q6c] > 0){
									begin.add(value[q6r][q6c]);
								}
							}
						}
					}
				
				}
				
				//this will check for 7,8,9
				else if(r > 6 && r < 10){
					if (c > 0 && c < 4){
						//quad = 7;
						for(int q7r = 6; q7r < 9; q7r++){
							for (int q7c = 0; q7c < 3; q7c++){
								if(value[q7r][q7c] > 0){
									begin.add(value[q7r][q7c]);
								}
							}
						}
					}
					else if (c > 3 && c < 7){
						//quad = 8;
						for(int q8r = 6; q8r < 9; q8r++){
							for (int q8c = 3; q8c < 7; q8c++){
								if(value[q8r][q8c] > 0){
									begin.add(value[q8r][q8c]);
								}
							}
						}
					}
					else{
						//quad = 9;
						for(int q9r = 6; q9r < 9; q9r++){
							for (int q9c = 7; q9c < 9; q9c++){
								if(value[q9r][q9c] > 0){
									begin.add(value[q9r][q9c]);
								}
							}
						}
					} //end of else
					
				} //end of quadrant values
				

				//end of correct value
			}
			
			//end of try
		}
		catch(InputOutOfRangeException e1){
			JOptionPane.showMessageDialog(null, e1.getMessage());
			
		}
		
		//this will generate the array that is to be returned.
		int[] test = {1,2,3,4,5,6,7,8,9};
		ArrayList<Integer> subtract = new ArrayList<Integer>();
		int[] end;
		
		//this will subtract the test array with the number found in each row, column, and quadrant
		for(int e = 0; e< begin.size(); e++){
			test[begin.get(e) - 1] -= begin.get(e); 
		}
		
		//this will remove all numbers less than 0 in the test array
		for(int e = 0; e< test.length; e++){
			if (test[e] > 0){
				subtract.add(test[e]);
			}
		}
		//this will generate the proper array with only possible answers.
		end = new int[subtract.size()];
		
		for(int e = 0; e < subtract.size(); e++){
			end[e] = subtract.get(e);
		}
		
		return end;
	}
	/** Return string representation of the sudoku board in the following format:
	 *   0,0,2,0,8,0,0,0,1 (new line)
	 *   1,0,0,4,0,2,0,0,6 (new line)
	 *   . . .
	 *   
	 * @return string representation of the sudoku board
	 */
	
	public String toString(){
		String output ="";
		
		for(int i = 0; i < txtfile.size(); i++){
			output += txtfile.get(i) + " ";
		}
		System.out.println(output);
		return output;
		
	}
	/**
	 * 
	 * @param gameFile the File with the representation of a new sudoku game.
	 * File will contain representation of the sudoku board in the following format:
	 *   0,0,2,0,8,0,0,0,1 (new line)
	 *   1,0,0,4,0,2,0,0,6 (new line)
	 *   . . .
	 * 9 rows separated with a new line.  Each row contains 9 values separated by a comma.
	 * A 0 represents an empty value
	 * 
	 */

	@Override
	public void newGame(File gameFile) {
		
		try {
			input = new Scanner(gameFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		

		while(input.hasNext()){
			String line = input.next();
			txtfile.add(line);

		}
		
		for(int i=0; i < txtfile.size(); i++){

			String[] split = txtfile.get(i).split(",");
			
			for(int j = 0; j<9; j++){
				stringvalue[i][j] = split[j];
			}
			
		}

		for(int a = 0; a < 9; a++){
			for(int b = 0; b <9; b++){
				value[a][b] = Integer.parseInt(stringvalue[a][b]);
			}
		}

		
	}

}
