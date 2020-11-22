package javaboard1031;

import java.util.ArrayList;

public class MemberDao {
	ArrayList<Member> members = new ArrayList<>();

	public void init() {

		Member m1 = new Member("hong123", "h1234", "홍길동");
		members.add(m1);
	}

	public void addMember(Member member) {
		members.add(member);
	}

	public Member getLoginMember(String loginId, String loginPw) {
		Member member = null;

		for (int i = 0; i < members.size(); i++) {
			member = members.get(i);
			String targetId = member.getLoginId();
			String targetPw = member.getLoginPw();

			if (loginId.equals(targetId) && loginPw.equals(targetPw)) {
				return member;
			}
		}

		return null;
	}
}
