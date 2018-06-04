package com.movie.ex.DTO;

import java.sql.Timestamp;

public class BoardDTO {
	private int no;
	private String title;
	private String content;
	private String writer;
	private Timestamp date;
	
	public BoardDTO() {
		// TODO Auto-generated constructor stub
	}
	public BoardDTO(int no, String title, String content, String writer, Timestamp date) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.date = date;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp timestamp) {
		this.date = timestamp;
	}
	
	
}
