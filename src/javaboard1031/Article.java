package javaboard1031;

public class Article {
	
	private int id;
	private String title;
	private String body;
	private String regDate;
	private int hit;
	private String writer;
	
	public Article(String title, String body, int hit, String writer) {
		this.title = title;
		this.body = body;
		this.hit = hit;
		this.writer = writer;
	}
	
	public Article(int id, String title, String body, String regDate, int hit, String writer) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.hit = hit;
		this.writer = writer;
	}
	
	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
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
