package com.movie.ex.DTO;

import java.util.ArrayList;

import com.movie.ex.Parsing.WebParsing;

/**********************************************************************
 * @author ChaeHee Lee
 * 	dtos 객체 singleton 으로 생성
 *	initialization on demand holder idiom 방법
 **********************************************************************/


public class SingletonDTO {
	WebParsing parsing = new WebParsing();
	public ArrayList<MovieDTO> dtos = parsing.getDtos();
	
	private SingletonDTO() {
//		System.out.println("singleton");
	}
	
	private static class Singleton {
		private static final SingletonDTO instance = new SingletonDTO();
	}
	
	public static SingletonDTO getInstance() {
		//System.out.println("singleton");
		return Singleton.instance;
	}
}
