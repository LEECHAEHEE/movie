package com.movie.ex;

public class MovieDTO {
	String title;		//영화 제목
	String rating;		//영화 평점 
	String outline;		//영화 개요(장르, 상영시간, 개봉일)
	String director;	//영화 감독
	String actor;		//출연 배우

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

