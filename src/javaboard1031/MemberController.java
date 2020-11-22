package javaboard1031;

import java.util.Scanner;

public class MemberController {
	
	MemberDao memberDao = new MemberDao();
	Scanner sc = new Scanner(System.in);
	Member loginedMember = null;
	
	public MemberController() {
		memberDao.init();
	}
	
	public Member getLoginedMember() {
		return this.loginedMember;
	}
	
	public void doCommand(String cmd) {
		if (cmd.equals("signup")) {
			memberSignUp();
		}
		if (cmd.equals("signin")) {
			memberSignin();
		} 
	}
	
	public void memberSignin() {
		System.out.print("아이디 : ");
		String loginId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String loginPw = sc.nextLine();

		Member member = memberDao.getLoginMember(loginId, loginPw);
		
		
		// 로그인 처리
		if (member != null) {
			loginedMember = member;
			System.out.println(member.getNickname() + "님 반갑습니다.!");
		} else {
			System.out.println("비밀번호를 틀렸거나 잘못된 회원정보입니다.");
		}

	}
	
	public void memberSignUp() {
		System.out.println("==== 회원 가입을 진행합니다 ====");
		System.out.print("아이디를 입력해주세요 : ");
		String loginId = sc.nextLine();
		System.out.print("비밀번호를 입력해주세요 : ");
		String loginPw = sc.nextLine();
		System.out.print("닉네임을 입력해주세요 : ");
		String nick = sc.nextLine();

		Member member = new Member(loginId, loginPw, nick);
		
		memberDao.addMember(member);
		
		System.out.println("==== 회원가입이 완료되었습니다. ====");
	}
}
