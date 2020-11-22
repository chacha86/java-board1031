package javaboard1031;

public class Like {
	private int parentId; // 원게시글번호
	private String checkMemberId; // 체크한 멤버 아이디
	private String regDate; // 등록 날짜
	
	public Like(int parentId, String checkMemberId) {
		super();
		this.parentId = parentId;
		this.checkMemberId = checkMemberId;
	}
	
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getCheckMemberId() {
		return checkMemberId;
	}
	public void setCheckMemberId(String checkMemberId) {
		this.checkMemberId = checkMemberId;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
}
