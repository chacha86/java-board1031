package javaboard1031;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ArticleController {
	
	ArticleDao articleDao = new ArticleDao();
	Scanner sc = new Scanner(System.in);
	Member loginedMember = null;
	
	public ArticleController() {
		articleDao.init();
	}
	
	public void setLoginedMember(Member member) {
		this.loginedMember = member;
	}
	
	public void doCommand(String cmd) {
		if (cmd.equals("add")) {
			
			// 로그인 체크
			if(isLogin()) {
				addArticle();
			}
		}
		if (cmd.equals("list")) {
			printArticleList(articleDao.getArticles());
		}
		if (cmd.equals("update")) {
			updateArticle();
		}
		if (cmd.equals("delete")) {
			deleteArticle();
		}
		if (cmd.equals("read")) {
			readArticle();
		}
		if (cmd.equals("search")) {
			searchArticle();
		}
		if (cmd.equals("sort")) {
			sortArticle();
		}
	}
	
	public void sortArticle() {
		System.out.print("정렬 대상을 선택해주세요. (id : 번호, hit : 조회수) : ");
		String sortTarget = sc.nextLine();
		System.out.print("정렬 방법을 선택해주세요. (asc : 오름차순, desc : 내림차순) : ");
		String sortType = sc.nextLine();
		
		ArrayList<Article> articles = articleDao.getArticles();
		
		MyComp comp = new MyComp();
		comp.sortTarget = sortTarget;
		comp.sortType = sortType;
		
		
		Collections.sort(articles, comp);
		
		printArticleList(articles);
		
		
	}
	
	public boolean isMyArticle(Article article) {
		System.out.println("article : " + article);
		System.out.println("loginedMember : " + loginedMember);
		if(article.getWriterId().equals(loginedMember.getLoginId())) {
			return true;
		} else {
			System.out.println("자신의 게시물만 수정/삭제할 수 있습니다.");
			return false;
		}
	}
	
	public boolean isLogin() {
		if(loginedMember == null) {
			System.out.println("로그인이 필요한 기능입니다.");
			return false;
		} else {
			return true;
		}
	}
	
	public void searchArticle() {

		System.out.println("검색 항목을 선택해주세요 (1. 제목, 2. 내용, 3. 제목 + 내용, 4. 작성자) : ");
		int searchTarget = Integer.parseInt(sc.nextLine());

		System.out.print("검색 키워드를 입력해주세요 : ");
		String keyword = sc.nextLine();

		ArrayList<Article> articles = articleDao.getArticles();

		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			String targetStr = "";
			if (searchTarget == 1) {
				targetStr = article.getTitle();
			} else if (searchTarget == 2) {
				targetStr = article.getBody();
			} else if (searchTarget == 3) {
				targetStr = article.getTitle() + article.getBody();
			} else {
				targetStr = article.getWriter();
			}

			if (targetStr.contains(keyword)) {
				System.out.println("번호 : " + article.getId());
				System.out.println("제목 : " + article.getTitle());
				System.out.println("등록날짜 : " + article.getRegDate());
				System.out.println("조회수 : " + article.getHit());
				System.out.println("작성자 : " + article.getWriter());
				System.out.println("===================");
			}
		}
	}
	
	public void readProcess(Article article) {
		while (true) {
			System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) : ");
			int rCmdNo = Integer.parseInt(sc.nextLine());

			if (rCmdNo == 1) {
				
				if(isLogin()) {					
					System.out.print("댓글 내용을 입력해주세요 : ");
					String replyBody = sc.nextLine();
					Reply re = new Reply(article.getId(), replyBody, loginedMember.getNickname(), loginedMember.getLoginId());
					
					articleDao.addReply(re);
					System.out.println("댓글이 등록되었습니다.");
					
					printArticle(article);
				}

			} else if (rCmdNo == 2) {
				
				Like like = articleDao.getLikeByParentIdAndMemberLoginId(article.getId(), loginedMember.getLoginId());
				
				if(isLogin() && (like == null)) {
					
					like = new Like(article.getId(), loginedMember.getLoginId());
					articleDao.addLike(like);
					
					System.out.println("해당 게시물을 좋아합니다.");	
					printArticle(article);
				} else {
					articleDao.removeLike(like);
					System.out.println("해당 게시물의 좋아요를 해제합니다.");
					printArticle(article);
				}
			} else if (rCmdNo == 3) {
				if(isLogin() && isMyArticle(article)) {
					updateMyArticle(article.getId());					
				}
			} else if (rCmdNo == 4) {
				if(isLogin() && isMyArticle(article)) {					
					deleteMyArticle(article.getId());
				}
			} else if (rCmdNo == 5) {
				break;
			}
		}
	}
	
	public void deleteMyArticle(int targetId) {		
		boolean rst = articleDao.deleteArticle(targetId);
		if (!rst) {
			System.out.println("없는 게시물 번호입니다.");
		}
	}
	
	public void updateMyArticle(int targetId) {
		
		System.out.print("제목 : ");
		String title = sc.nextLine();
		System.out.print("내용 : ");
		String body = sc.nextLine();

		boolean rst = articleDao.updateArticle(targetId, title, body);

		if (!rst) {
			System.out.println("없는 게시물 번호입니다.");
		}
	}
	
	public void readArticle() {
		System.out.print("상세보기할 게시물 번호 : ");
		String aid = sc.nextLine();
		int targetId = Integer.parseInt(aid);

		Article article = articleDao.getArticleById(targetId);

		if (article == null) {
			System.out.println("없는 게시물입니다.");
		} else {

			int targetHit = article.getHit();
			article.setHit(targetHit + 1);

			printArticle(article);
			readProcess(article);
		}
	}
	
	public void deleteArticle() {
		System.out.print("삭제할 게시물 번호 : ");
		String aid = sc.nextLine();
		int targetId = Integer.parseInt(aid);
		boolean rst = articleDao.deleteArticle(targetId);
		if (!rst) {
			System.out.println("없는 게시물 번호입니다.");
		}
	}
	
	public void updateArticle() {
		System.out.print("수정할 게시물 번호 : ");
		String aid = sc.nextLine();
		int targetId = Integer.parseInt(aid);

		System.out.print("제목 : ");
		String title = sc.nextLine();
		System.out.print("내용 : ");
		String body = sc.nextLine();

		boolean rst = articleDao.updateArticle(targetId, title, body);

		if (!rst) {
			System.out.println("없는 게시물 번호입니다.");
		}

	}
	
	public void printArticleList(ArrayList<Article> articles) {
	
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
	
	public void addArticle() {
		System.out.print("게시물 제목을 입력해주세요 : ");
		String title = sc.nextLine();
		System.out.print("게시물 내용을 입력해주세요 : ");
		String body = sc.nextLine();
		System.out.println("게시물이 등록되었습니다.");

		Article article1 = new Article(title, body, 0, loginedMember.getNickname(), loginedMember.getLoginId());

		articleDao.addArticle(article1);
	}
	
	public void printArticle(Article article) {

		System.out.println("번호 : " + article.getId());
		System.out.println("제목 : " + article.getTitle());
		System.out.println("내용 : " + article.getBody());
		System.out.println("등록날짜 : " + article.getRegDate());
		System.out.println("조회수 : " + article.getHit());
		System.out.println("좋아요 : " + articleDao.getLikeCountByParentId(article.getId()));
		System.out.println("작성자 : " + article.getWriter());
		System.out.println("======================");
		System.out.println("--------- 댓글 --------");

		ArrayList<Reply> replies = articleDao.getReplies();
		for (int i = 0; i < replies.size(); i++) {
			Reply re = replies.get(i);
			if (re.getParentId() == article.getId()) {
				System.out.println("내용 : " + re.getBody());
			}
		}
	}
}

class MyComp implements Comparator<Article> {
	
	String sortTarget = "hit";
	String sortType = "asc";

	@Override
	public int compare(Article a1, Article a2) {
		if(sortTarget.equals("hit")) {
			if(sortType.equals("asc")) {
				if(a1.getHit() > a2.getHit()) {
					return 1;
				}
				return -1;							
			} else {
				if(a1.getHit() < a2.getHit()) {
					return 1;
				}
				return -1;
			}
		} else {
			if(sortType.equals("asc")) {
				if(a1.getId() > a2.getId()) {
					return 1;
				}
				return -1;							
			} else {
				if(a1.getId() < a2.getId()) {
					return 1;
				}
				return -1;
			}
		}
	}
	
}