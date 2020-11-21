package javaboard1031;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
	
	ArrayList<Member> members = new ArrayList<>();
	ArticleController ac = new ArticleController();
	MemberController mc = new MemberController();
	Scanner sc = new Scanner(System.in);
	
	
	public void start() {
		Scanner sc = new Scanner(System.in);	
		
		while (true) {
			
			Member loginedMember = mc.getLoginedMember();
			ac.setLoginedMember(loginedMember);
			
			if (loginedMember == null) {
				System.out.print("명령어를 입력해주세요 : ");
			} else {
				System.out.print(
						"명령어를 입력해주세요 [" + loginedMember.getLoginId() + "(" + loginedMember.getNickname() + ")] : ");
			}
			String cmd = sc.nextLine();
			
			if (cmd.equals("exit")) {
				System.out.println("종료");
				break;
			}
			
			String[] cmdBits = cmd.split(" ");
			
			if(cmdBits.length < 2) {
				System.out.println("잘못된 명령어입니다.");
				continue;
			}
			String module = cmdBits[0]; // article
		    String func = cmdBits[1]; // list add
		    
			if(module.equals("article")) {
				ac.doCommand(func);				
			} else if(module.equals("member")) {				
				mc.doCommand(func);
			}
		}
	}
}
