package javaboard1031;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {

		String str1 = "안녕하세요 반갑습니다.";

		String txt1 = "가나다라";
		String txt2 = "해당 내용은 테스트 입니다";
		String txt3 = "가격은 29,000원 입니다";

		// contains를 이용한 방법(true, false 반환)
		if (txt1.contains("나다12"))
			System.out.println("문자열 있음!");
		else
			System.out.println("문자열 없음!");

	}

}
