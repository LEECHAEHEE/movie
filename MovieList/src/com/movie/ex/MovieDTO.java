package com.movie.ex;

public class MovieDTO {
	String title;		//��ȭ ����
	String rating;		//��ȭ ���� 
	String outline;		//��ȭ ����(�帣, �󿵽ð�, ������)
	String director;	//��ȭ ����
	String actor;		//�⿬ ���

	public MovieDTO() { }
	
	public MovieDTO(String title, String outline, String rating, String director, String actor) {
		super();
		this.title = title;
		this.outline = outline;
		this.rating = rating;
		this.director = director;
		this.actor = actor;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOutline() {
		return outline;
	}
	public void setOutline(String outline) {
		this.outline = outline;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	
	
}

