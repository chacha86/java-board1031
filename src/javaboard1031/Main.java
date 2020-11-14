package javaboard1031;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

	static ArrayList<Article> articles = new ArrayList<>();
	
	public static int getArticleIndexById(int aid) {

		int existFlag = 1; // 1 없다, 2 있다.
		int index = -1; // -1 없다.

		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			if (aid == article.getId()) {
				existFlag = 2;
				index = i;
			}
		}

		return index;

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int id = 4;
		
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy.MM.dd");
		Date time = new Date();		
		String time1 = format1.format(time);
		
		Article a1 = new Article(1, "안녕하세요", "내용1", time1, 0, "홍길동");
		Article a2 = new Article(2, "반갑습니다", "내용2", time1, 0, "홍길동");
		Article a3 = new Article(3, "안녕2", "내용3", time1, 0, "이순신");
		
		articles.add(a1);
		articles.add(a2);
		articles.add(a3);

		while (true) {
			System.out.print("명령어를 입력해주세요 : ");
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

				Article article1 = new Article(id, title, body, time1, 0,  "익명");
				articles.add(article1);
				id++;

			}
			if (cmd.equals("list")) {
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
				int index = getArticleIndexById(targetId);

				if (index == -1) {
					System.out.println("없는 게시물입니다.");
				} else {
					System.out.print("제목 : ");
					String title = sc.nextLine();
					System.out.print("내용 : ");
					String body = sc.nextLine();
					
					// 수정 필요 -> 
					Article article2 = articles.get(index);
					article2.setTitle(title);
					article2.setBody(body);

					articles.set(index, article2);
				}

			}

			if (cmd.equals("delete")) {
				System.out.print("삭제할 게시물 번호 : ");
				String aid = sc.nextLine();
				int targetId = Integer.parseInt(aid);
				int index = getArticleIndexById(targetId);
				if (index == -1) {
					System.out.println("없는 게시물입니다.");
				} else {
					articles.remove(index);
					System.out.println("삭제가 완료되었습니다.");
				}

			}
			if(cmd.equals("read")) {
				System.out.print("상세보기할 게시물 번호 : ");
				String aid = sc.nextLine();
				int targetId = Integer.parseInt(aid);
				int index = getArticleIndexById(targetId);
				
				if(index == -1) {
					System.out.println("없는 게시물입니다.");
				} else {
					
					Article article = articles.get(index);
					int targetHit = article.getHit();
					article.setHit(targetHit + 1);
					
					System.out.println("번호 : " + article.getId());
					System.out.println("제목 : " + article.getTitle());
					System.out.println("내용 : " + article.getBody());
					System.out.println("등록날짜 : " + article.getRegDate());
					System.out.println("조회수 : " + article.getHit());
					System.out.println("작성자 : " + article.getWriter());
					System.out.println("======================");
				}
			}			
		}
	}
}
