package javaboard1031;

public class Test {

	public static void main(String[] args) {
		사람 a사람 = new 사람();
		a사람.이름 = "홍길동";
		a사람.나이 = 20;
		
		사람 b사람 = new 사람();
		b사람.이름 = "홍길순";
		b사람.나이 = 22;
		
		a사람.자기소개();
		
		b사람.나이 = 30;
		
		수학 a수학 = new 수학();
		System.out.println(a수학.PI);
		
		a수학.PI = 3.15;
		
		수학 b수학 = new 수학();
		System.out.println(b수학.PI);
		
	}	

}
class 사람 {
	String 이름;
	int 나이;
	
	static public void 자기소개() {
		System.out.println("저는 " + 이름 + "입니다.");
	}
}

class 수학 {
	static double PI = 3.14;
}