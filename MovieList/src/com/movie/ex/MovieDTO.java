package com.movie.ex;

import java.net.URL;

public class MovieDTO {
	URL imgURL;
	String title;		//��ȭ ����
	String rating;		//��ȭ ���� 
	String outline;		//��ȭ ����(�帣, �󿵽ð�, ������)
	String director;	//��ȭ ����
	String actor;		//�⿬ ���

	public MovieDTO() { }

	public MovieDTO(URL imgURL, String title, String rating, String outline, String director, String actor) {
		super();
		this.imgURL = imgURL;
		this.title = title;
		this.rating = rating;
		this.outline = outline;
		this.director = director;
		this.actor = actor;
	}


	public URL getImgURL() {
		return imgURL;
	}
	public void setImgURL(URL imgURL) {
		this.imgURL = imgURL;
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

