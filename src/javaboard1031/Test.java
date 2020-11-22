package javaboard1031;

public class Test {

	static String test = "";
	
	public static void main(String[] args) {
		
		if(false && test()) {
			System.out.println("kkk");
		}
		
		System.out.println(test);
		
		
	}	
	
	public static boolean test() {
		test = "aa";
		return true;
	}
	

}
