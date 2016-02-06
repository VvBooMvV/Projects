import java.util.Set;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		String out = "";
		for (int i = 0; i < 5; i++){
			for(int j = 5 - i; j >0; j--){
				out += "x";
			}
			out += "\n";
		}
		int[] test = {5,4,3,2,8,6,9,1,7};

		
		bubbleSort(test);
//		insertSort(test);
//		quickSort(test, 0, test.length-1);
		//TODO build merge sort
		//TODO build Heap Sort
		//TODO modify for list versions
		
		for (int i = 0; i < test.length; i++){
			System.out.println(test[i]);
		}
		
		System.out.println("index position " + binary_search(test, 7, 0, 9));
		
		System.out.println(fibonacci(10));
		
	}
	

	
	public static int fibonacci(int i){
		
		if(i <= 0){
			
			return 0;
		}
		if(i == 1){
			
			return 1;
		}
		
		return fibonacci(i-1) + fibonacci(i-2);
	}
	

	

	//TODO only ascending order, fix so that it can also do decending order.
	public static int binary_search(int A[], int key, int imin, int imax)
	{
	  // test if array is empty
	  if (imax < imin)
	    // set is empty, so return value showing not found
	    return 0;
	  else
	    {
	      // calculate midpoint to cut set in half
	      int imid = (imin + imax)/2;
	     
	      // three-way comparison
	      if (A[imid] < key)   //toggle conditional for ascending/descending order
	        // key is in lower subset
	        return binary_search(A, key, imin, imid - 1);
	      else if (A[imid] < key)
	        // key is in upper subset
	        return binary_search(A, key, imid + 1, imax);
	      else
	        // key has been found
	        return imid;
	    }
	}
	
	
	
	public static void insertSort(int[] array){
		
		//start the pivot pos
		for(int i = 1; i < array.length; i++){
			
			int point = array[i];
			int a = i-1;
			while(a >=0 && point < array[a]){  //swap conditional between point and array[]
				
				int temp = array[a];
				array[a] = array[a+1];
				array[a+1] = temp;
				a--;
				
			}
			
		}

	}
	
	
	
	public static void bubbleSort(int[] a){
		
		int itemSorted = 0;
		boolean flip;
		int n = a.length;
		do{
			flip = false;
			for(int b = n-1, t = n -2; t >= itemSorted; b--, t--){
				if(a[b] > a[t]){  //swap this conditional for ascending or descending order
					int temp = a[t];
					a[t] = a[b];
					a[b] = temp;
					flip = true;
				}
			}
			itemSorted++;
			
		}while(flip == true && itemSorted != n-1);

	}
	
	
	/**
	 * quickSort algorithm to sort in ascending order
	 * @param items: the array which passed by reference
	 * @param left: lowest index value in the array = 0
	 * @param right: highest index value in the array = items.length-1
	 */
	public static void quickSort(int[] items, int left, int right){

		int i, j, temp, pivot, wall;
		wall = right - left + 1; //wall position
		if(wall <= 1){  //base case
			return;
		}
		
		pivot = items[(left + right) / 2];
		i = left;
		j = right;
		
		do{
			
			while(items[i] < pivot) i++; //swap conditional to ascend (vice versa)
			while(items[j] > pivot) j--; //swap conditional to ascend
			
			if(i <=j){
				
				temp = items[i];
				items[i] = items[j];
				items[j] = temp;
				i++;
				j--;
			}
			
		}while(i <=1);
		
		quickSort(items, left, j);
		quickSort(items, i, right);
		
	}

}
