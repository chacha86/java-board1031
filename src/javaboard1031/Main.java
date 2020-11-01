package javaboard1031;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String[] titles = new String[10];
		String[] bodies = new String[10];
		int[] ids = new int[10];
		
		int index = 0;
		int id = 1;
		
		while(true) {
			System.out.print("명령어를 입력해주세요 : ");
			String cmd = sc.nextLine();
			if(cmd.equals("exit")) {
				System.out.println("종료");
				break;
			}
			
			if(cmd.equals("add")) {
					
				System.out.print("게시물 제목을 입력해주세요 : ");
				titles[index] = sc.nextLine();
				System.out.print("게시물 내용을 입력해주세요 : ");
				bodies[index] = sc.nextLine();
				System.out.println("게시물이 등록되었습니다.");
				ids[index] = id;
				
				index++;
				id++;
				
			} 
			if(cmd.equals("list")) {
			
				for(int i = 0; i < index; i++) {
					System.out.println("번호 : " + ids[i]);
					System.out.println("제목 : " + titles[i]);
					System.out.println("===================");					
				}
				
			}
		}
	
	}

}
