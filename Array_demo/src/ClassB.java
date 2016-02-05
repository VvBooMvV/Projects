
public class ClassB {

	//declared variable in the field
	private int[] b;
	
	//constructor which also initializes my variable 'b'
	public ClassB(){
		
	}
	
	//Autator or setter
	public void SetArray(int[] Number){
		b = Number;
	}
	
	//Accessors or getters
	public int[] GetArray(){
		return b;
	}
	
	//self created method
	public void PrintArray(){
		
		System.out.println("PrintArray Method Was Called");
		for(int print : b){
			System.out.print(print + "  ");
		}
	}
}
