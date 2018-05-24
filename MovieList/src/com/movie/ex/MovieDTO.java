package com.movie.ex;

import java.net.URL;

public class MovieDTO {
	URL imgURL;
	String movieNo; 	//고유 번호
	String title;		//제목
	String rating;		//평점
	String outline;		//개요(장르, 상영시간, 예매율)
	String director;	//감독
	String actor;		//출연
	String story;
	
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
	
	
	public MovieDTO(URL imgURL, String title, String story) {
		super();
		this.imgURL = imgURL;
		this.title = title;
		this.story = story;
	}

	public String getstory() {
		return story;
	}
	public void setstory(String story) {
		this.story = story;
	}
	public String getMovieNo() {
		return movieNo;
	}
	public void setMovieNo(String movieNo) {
		this.movieNo = movieNo;
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

