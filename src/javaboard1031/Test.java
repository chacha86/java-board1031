package javaboard1031;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		
		int id = 1;
		
		ArrayList<Article> articleList = new ArrayList<>();	
		
		Article a1 = new Article(id, "제목1", "내용1");
		articleList.add(a1);
		id++;
		
		Article a2 = new Article(id, "제목2", "내용2");
		articleList.add(a2);		
		id++;
		
		Article a3 = new Article(id, "제목3", "내용3");
		articleList.add(a3);
		id++;

		articleList.remove(1);
		
		for(int i = 0; i < articleList.size(); i++) {
			Article a = articleList.get(i);
			System.out.println(a.getId());
			System.out.println(a.getTitle());
			System.out.println(a.getBody());
			System.out.println("==========");
		}
		
	}

}

class Article {
	private int id;
	private String title;
	private String body;
	
	public Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}

	
	
}