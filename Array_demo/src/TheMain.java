import java.util.Scanner;

/**
 * This is for a lecture I presented
 * @author Harry Kim
 *
 */
public class TheMain {
	
	public static void main(String[] args) {
		//answer 
		
//		if(Car.FERRARI.compareTo(Car.TOYOTA) > 0){
//			System.out.println(Car.FERRARI.compareTo(Car.TOYOTA) );
//		}
//		if(Car.FERRARI.compareTo(Car.PORSCHE) < 0){
//			System.out.println("Car: " + Car.TOYOTA);
//		}
//		else{
//			System.out.println("Car: " + Car.PORSCHE);
//		}
//		
		//which looks less time consuming to type?
		
//		int a01 = 0;
//		int a02 = 0;
//		int a03 = 0;
//		int a04 = 0;
//		int a05 = 0;
//		int a06 = 0;
//		int a07 = 0;
//		int a08 = 0;
//		int a09 = 0;
//		int a10 = 0;
		
//
//		int[] Num = new int[10];  
		
		//array size must be a positive number, and the array will be a fixed size	
		//explain elements, index, and how to class each element (on board)
		//accessing the array element ArrayObjectName[index]  <== this returns the element
		//the element position can be called subscript or "Num sub 1"
		
	
//		System.out.println(Num[4]);  //index is the position of the element starting at 0.
//		System.out.println(a05);    //same way in the list above;
		
//		Num[4] = 1;  //same
//		a05 = 1;     //same
//		System.out.println(Num[4]);
//		System.out.println(a05);
		
		
		
//		Scanner key = new Scanner(System.in);
//		
//		System.out.print("Enter a number: ");
//		Num[0] = key.nextInt();
//		System.out.println("Enter Another Number: ");
//		a01 = key.nextInt();
//		
//		System.out.println("First Element: " + Num[0]);
//		System.out.println("Number in a01: " + a01);
	
		
		
		
		
		//alternate ways of declaring arrays
		
//		int num1[] = new int[5];
//		int[] 	num2 = new int[5];
	
		
//				num3 = new int[5]; //2nd way is better IMO
		
		
		
		
		
		
		//Initializing arrays, array.length, and treating arrays as primitives (if statments)
		
//		int[] DeclaredNums1 = {1,2,3,4,5};
//		int[] DeclaredNums2 = new int[5];

//
//		System.out.println(DeclaredNums1);
//		System.out.println(DeclaredNums2);
		

		
//		for(int j = 0; j < 6; j++){    //will this work?
//			System.out.println(DeclaredNums2[j]);
//		}
		
		
//		for(int index =0; index < DeclaredNums2.length; index++){  
//		//what values will be placed into DN2?
//			DeclaredNums2[index] = index+1;
//		}
		
//		
//		for(int i =0; i< DeclaredNums2.length; i++){  //show results
//			System.out.print("dn1: " + DeclaredNums1[i] + "    dn2: ");
//			System.out.println(DeclaredNums2[i]);
//		}
		
		
	
		//will these if statements work?
		
//		if(DeclaredNums1[2] == DeclaredNums2[2]){
//			System.out.println("HELL YEAH!!");
//		}
//
//		if(DeclaredNums2 == DeclaredNums1){
//			System.out.println("HELL YEAH!!");
//		}
//		
//		if(DeclaredNums2[4] < DeclaredNums1[1]){
//			System.out.println("HELL YEAH!!");
//		}

//		if(DeclaredNums1[2] == DeclaredNums2[2]){
//		System.out.println("HELL YEAH!!");
//	}
//	System.out.println("This Statement is: " + (DeclaredNums1[2] == DeclaredNums2[2]));
//
//	if(DeclaredNums2 == DeclaredNums1){
//		System.out.println("HELL YEAH!!");
//	}
//	
//	System.out.println("This Statement is: " + (DeclaredNums2 == DeclaredNums1));
//	if(DeclaredNums2[4] < DeclaredNums1[1]){
//		System.out.println("HELL YEAH!!");
//	}
//	System.out.println("This Statement is: " + (DeclaredNums2[4] < DeclaredNums1[1]));
		
		
		//explain arrays of different primitive data types 
		//(classes can be arrays, but they are not automatically initialized like primitive datatypes)
		
//		float[] ArrayF = new float[5];  //whats the index of this last array?
//		long[] ArrayL = new long[8];	//whats the size of this array?
//		String[] ArrayS = new String[4];  //whats the data type?
//		double[] ArrayD = new double[3];  //what number is initialed in the first element?
//		boolean[] ArrayB = new boolean[4];  //what is initialzed for all elements in the array?
//		char[] ArrayC = new char[2];  //are these values initialized?
		

//		//Q9 Answer:
//		System.out.println(ArrayD[0]);
//		//Q10 Answer:
//		for(int i = 0; i < ArrayB.length; i++){
//			System.out.println(ArrayB[i]);}
//		for(int i = 0; i < ArrayC.length; i++){
//		System.out.println(ArrayC[i]);}

		//array of classes

//		int playerOneScore = 0;
//		ClassA[] classy = new ClassA[6];
////		
//		for(int i = 0; i<classy.length; i++){
//			classy[i] = new ClassA();
//		}
//		
//		System.out.println(classy[5]);

//		for(int j =0; j < classy.length; j++){
//			classy[1].SetString("What is the capital of Ethopia? \n 1. Addisabba \n 2. Washington \n 3. iPhone");
//			classy[1].SetInt(1);
//			
//			Scanner a = new Scanner(System.in);
//			int answer;
//			
//			System.out.println(classy[1].GetString());
//			answer = a.nextInt();
//			
//			if(answer == classy[1].GetInt()){
//				playerOneScore++;
//			}
//			else{
//				//whatever
//			}
			
//			classy[0].SetString("What is 2 + 2?");
//		}
//		for(ClassA a : classy){ //enhance for loops are READ ONLY, and will step trough all elements
//			System.out.println(a.GetString());
//		}
//		
//		
		
		
		
		
		//passing arrays to a different class
//		
//		ClassB TheClass = new ClassB();
//		int mainArray[] = {2,3,4,5,6};
//		int test[] = new int[5];


		
		
//		test = mainArray;  // will this work?
//		//now change the length for test.  What will happen print? (for loop below)
		
//		
//		TheClass.SetArray(mainArray);
//		
//		
		
//		int test2[];
//		test2 = TheClass.GetArray();
		
		
//		
//		for(int i = 0; i < test.length; i++){  //use to print stuff
			
//			System.out.println(test[i]);
			
//			System.out.println(TheClass.GetArray()[i]);
//			System.out.println(test2[i]);
//		}
		
//		TheClass.PrintArray();
		
		
		
		//Strings and Array of Strings

//		String[] sa = new String[3];
//		
//		sa[0] = "blue";
//		sa[1] = "red";
//		sa[2] = "green";
//		
//		String a1 = "Blue";
//
//		
//		int size = sa.length;
//		int wordsize = sa[2].length();
//		char C = sa[2].charAt(1);
//		
////		//what will print out?	
//		System.out.println(size);  
//		System.out.println(wordsize);
//		System.out.println(C);
//		System.out.println(sa[2]);
////		
////		
//		if(a1.compareToIgnoreCase(sa[0]) == 0){  //is this statement true?
//			System.out.println(a1 == sa[0]);  //what will print out?
//		}
		
//		//go to page 39, 40, 43, 45 on the slides
//		int[] b = {2, 4, 6};
//		int[] d = b;
//		for(int a : d){
//			System.out.println(a);
//		}
//		
	}

}
