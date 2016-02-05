
public class ClassA {


	private int a;
	private String b;
	private static int blah = 0;
	
	public ClassA(){
		a = 0;
		blah++;
	}
	
	public static int getBlah(){
		return blah;
	}

	public void SetInt(int Number){
		a = Number;
	}
	
	public void SetString(String name){
		b = name;
	}

	public int GetInt(){
		return a;
	}
	
	public String GetString(){
		return b;
	}

	public String toString(){
		return "yup";
	}
}
