package javaboard1031;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberController {
	
	ArrayList<Member> members = new ArrayList<>();
	Scanner sc = new Scanner(System.in);
	Member loginedMember = null;
	
	public void memberSignin() {
		System.out.print("아이디 : ");
		String loginId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String loginPw = sc.nextLine();

		boolean loginFlag = false; // 로그인 성공 여부
		Member member = null;

		for (int i = 0; i < members.size(); i++) {
			member = members.get(i);
			String targetId = member.getLoginId();
			String targetPw = member.getLoginPw();

			if (loginId.equals(targetId) && loginPw.equals(targetPw)) {
				loginFlag = true;
				break;
			}
		}
		// 로그인 처리
		if (loginFlag) {
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
		members.add(member);
		System.out.println("==== 회원가입이 완료되었습니다. ====");
	}
}
