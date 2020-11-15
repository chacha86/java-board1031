package javaboard1031;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static ArrayList<Member> members = new ArrayList<>();
	static ArticleDao articleDao = new ArticleDao();
	
	public static void printArticle(Article article) {
		
		System.out.println("번호 : " + article.getId());
		System.out.println("제목 : " + article.getTitle());
		System.out.println("내용 : " + article.getBody());
		System.out.println("등록날짜 : " + article.getRegDate());
		System.out.println("조회수 : " + article.getHit());
		System.out.println("작성자 : " + article.getWriter());
		System.out.println("======================");
		System.out.println("--------- 댓글 --------");
		
		ArrayList<Reply> replies = articleDao.getReplies();
		for(int i = 0; i < replies.size(); i++) {
			Reply re = replies.get(i);
			if(re.getParentId() == article.getId()) {
				System.out.println("내용 : " + re.getBody());				
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);		
		Member loginedMember = null;
		
		
		while (true) {
			if(loginedMember == null) {
				System.out.print("명령어를 입력해주세요 : ");				
			} else {
				System.out.print("명령어를 입력해주세요 [" + loginedMember.getLoginId() + "(" + loginedMember.getNickname() + ")] : ");				
			}
			String cmd = sc.nextLine();
			if (cmd.equals("exit")) {
				System.out.println("종료");
				break;
			}

			if (cmd.equals("add")) {

				System.out.print("게시물 제목을 입력해주세요 : ");
				String title = sc.nextLine();
				System.out.print("게시물 내용을 입력해주세요 : ");
				String body = sc.nextLine();
				System.out.println("게시물이 등록되었습니다.");

				Article article1 = new Article(title, body, 0,  "익명");
				
				articleDao.addArticle(article1);
				

			}
			if (cmd.equals("list")) {
				ArrayList<Article> articles = articleDao.getArticles();
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					System.out.println("번호 : " + article.getId());
					System.out.println("제목 : " + article.getTitle());
					System.out.println("등록날짜 : " + article.getRegDate());
					System.out.println("조회수 : " + article.getHit());
					System.out.println("작성자 : " + article.getWriter());
					System.out.println("===================");
				}
			}

			if (cmd.equals("update")) {
				System.out.print("수정할 게시물 번호 : ");
				String aid = sc.nextLine();
				int targetId = Integer.parseInt(aid);
				
				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String body = sc.nextLine();
				
				boolean rst = articleDao.updateArticle(targetId, title, body);
				
				if(!rst) {
					System.out.println("없는 게시물 번호입니다.");
				}

			}

			if (cmd.equals("delete")) {
				System.out.print("삭제할 게시물 번호 : ");
				String aid = sc.nextLine();
				int targetId = Integer.parseInt(aid);
				boolean rst = articleDao.deleteArticle(targetId);
				if(!rst) {
					System.out.println("없는 게시물 번호입니다.");
				}
			}
			if(cmd.equals("read")) {
				System.out.print("상세보기할 게시물 번호 : ");
				String aid = sc.nextLine();
				int targetId = Integer.parseInt(aid);
				
				Article article = articleDao.getArticleById(targetId);
				
				if(article == null) {
					System.out.println("없는 게시물입니다.");
				} else {
					
					int targetHit = article.getHit();
					article.setHit(targetHit + 1);
					
					printArticle(article);
					
					while(true) {
						System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) : ");
						int rCmdNo = Integer.parseInt(sc.nextLine());
						
						if(rCmdNo == 1) {
							
							System.out.print("댓글 내용을 입력해주세요 : ");
							String replyBody = sc.nextLine();
							Reply re = new Reply(article.getId(), replyBody, "익명");
							
							articleDao.addReply(re);
							System.out.println("댓글이 등록되었습니다.");
							
							printArticle(article);
							
						} else if(rCmdNo == 2) {
							System.out.println("[좋아요 기능]");
						} else if(rCmdNo == 3) {
							System.out.println("[수정 기능]");
						} else if(rCmdNo == 4) {
							System.out.println("[삭제 기능]");
						} else if(rCmdNo == 5) {
							break;
						}
					}
				}
			}
			
			if(cmd.equals("search")) {
				
				System.out.println("검색 항목을 선택해주세요 (1. 제목, 2. 내용, 3. 제목 + 내용, 4. 작성자) : ");
				int searchTarget = Integer.parseInt(sc.nextLine());
				
				System.out.print("검색 키워드를 입력해주세요 : ");
				String keyword = sc.nextLine();
				
				ArrayList<Article> articles = articleDao.getArticles();
				
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					String targetStr = "";
					if(searchTarget == 1) {
						targetStr = article.getTitle();
					} else if(searchTarget == 2) {
						targetStr = article.getBody();
					} else if(searchTarget == 3) {
						targetStr = article.getTitle() + article.getBody();
					} else {
						targetStr = article.getWriter();
					}
					
					if(targetStr.contains(keyword)) {
						System.out.println("번호 : " + article.getId());
						System.out.println("제목 : " + article.getTitle());
						System.out.println("등록날짜 : " + article.getRegDate());
						System.out.println("조회수 : " + article.getHit());
						System.out.println("작성자 : " + article.getWriter());
						System.out.println("===================");
					}
				}
			}
			if(cmd.equals("signup")) {
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
			if(cmd.equals("signin")) {
				System.out.print("아이디 : ");
				String loginId = sc.nextLine();
				System.out.print("비밀번호 : ");
				String loginPw = sc.nextLine();
				
				boolean loginFlag = false; // 로그인 성공 여부
				Member member = null;
				
				for(int i = 0; i < members.size(); i++) {
					member = members.get(i);
					String targetId = member.getLoginId();
					String targetPw = member.getLoginPw();
					
					if(loginId.equals(targetId) && loginPw.equals(targetPw)) {
						loginFlag = true;
						break;
					} 
				}
				// 로그인 처리
				if(loginFlag) {
					loginedMember = member;
					System.out.println(member.getNickname() + "님 반갑습니다.!");
				} else {
					System.out.println("비밀번호를 틀렸거나 잘못된 회원정보입니다.");
				}
				
			}
		}
	}
}
