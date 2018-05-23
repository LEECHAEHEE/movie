package com.movie.ex;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MovieList {
	public ArrayList<MovieDTO> showList(){
		/*접속할 페이지 URL*/
		String url = "https://movie.naver.com/movie/running/current.nhn";
		
		/*MovieDTO객체를 담을 수 있는 ArrayList선언*/
		ArrayList<MovieDTO> list = new ArrayList<>();
		
		try {
			/*접속하여 HTML 정보 Document객체에 저장*/
			Document response = Jsoup.connect(url).get();

			
			/*각각의 정보 Elements 객체에 저장*/
			Elements elements = response.select("ul.lst_detail_t1 li");
			Elements title = elements.select("dl.lst_dsc dt.tit a");
			Elements star = elements.select("dd.star dl.info_star dd div.star_t1 a span.num");
			Elements outline = elements.select("dl.lst_dsc dd dl.info_txt1");
			
			/*1~100위 까지 영화 정보를 각각 MovieDTO 객체에 담아서 ArrayList에 담는다.*/
			for(int i=0;i<elements.size();i++) {
				/*MovieDTO 객체 생성*/
				MovieDTO dto = new MovieDTO();
				
				/*setter 이용해서 객체 정보 삽입*/
				dto.setTitle(title.get(i).text());
				dto.setRating(star.get(i).text());
				/*출연 정보가 없는 영화 정보와 있는 정보 구분해서 저장.*/
				if(outline.get(i).childNodeSize()==13) {
					dto.setOutline(outline.get(i).child(1).text());
					dto.setDirector(outline.get(i).child(3).text());
					dto.setActor(outline.get(i).child(5).text());
				}else {
					dto.setOutline(outline.get(i).child(1).text());
					dto.setDirector(outline.get(i).child(3).text());
					dto.setActor("-");
				}
				
				/*MovieDTO객체 ArrayList에 삽입*/
				list.add(dto);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
}


