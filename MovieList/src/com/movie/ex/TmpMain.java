package com.movie.ex;

import java.util.ArrayList;

public class TmpMain {

	public static void main(String[] args) {
		MovieList list = new MovieList();
		ArrayList<MovieDTO> l = list.showList();
		for (MovieDTO dto : l) {
			System.out.println(dto.getTitle());
			System.out.println(dto.getRating());
			System.out.println(dto.getOutline());
			System.out.println(dto.getDirector());
			System.out.println(dto.getActor());
			System.out.println("=================================================================================================");
		}
	}
}
