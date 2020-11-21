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
		Member loginedMember = null;

		while (true) {
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
			if (cmd.equals("add")) {
				ac.addArticle();
			}
			if (cmd.equals("list")) {
				ac.printArticleList();
			}
			if (cmd.equals("update")) {
				ac.updateArticle();
			}
			if (cmd.equals("delete")) {
				ac.deleteArticle();
			}
			if (cmd.equals("read")) {
				ac.readArticle();
			}
			if (cmd.equals("search")) {
				ac.searchArticle();
			}
			if (cmd.equals("signup")) {
				mc.memberSignUp();
			}
			if (cmd.equals("signin")) {
				mc.memberSignin();
			} 
		}
	}
}
