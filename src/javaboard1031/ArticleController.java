package javaboard1031;

import java.util.ArrayList;
import java.util.Scanner;

public class ArticleController {
	
	ArticleDao articleDao = new ArticleDao();
	Scanner sc = new Scanner(System.in);
	
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

				System.out.print("댓글 내용을 입력해주세요 : ");
				String replyBody = sc.nextLine();
				Reply re = new Reply(article.getId(), replyBody, "익명");

				articleDao.addReply(re);
				System.out.println("댓글이 등록되었습니다.");

				printArticle(article);

			} else if (rCmdNo == 2) {
				System.out.println("[좋아요 기능]");
			} else if (rCmdNo == 3) {
				System.out.println("[수정 기능]");
			} else if (rCmdNo == 4) {
				System.out.println("[삭제 기능]");
			} else if (rCmdNo == 5) {
				break;
			}
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
	
	public void printArticleList() {
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
	
	public void addArticle() {
		System.out.print("게시물 제목을 입력해주세요 : ");
		String title = sc.nextLine();
		System.out.print("게시물 내용을 입력해주세요 : ");
		String body = sc.nextLine();
		System.out.println("게시물이 등록되었습니다.");

		Article article1 = new Article(title, body, 0, "익명");

		articleDao.addArticle(article1);
	}
	
	public void printArticle(Article article) {

		System.out.println("번호 : " + article.getId());
		System.out.println("제목 : " + article.getTitle());
		System.out.println("내용 : " + article.getBody());
		System.out.println("등록날짜 : " + article.getRegDate());
		System.out.println("조회수 : " + article.getHit());
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
