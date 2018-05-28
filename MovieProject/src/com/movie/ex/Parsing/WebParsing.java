package com.movie.ex.Parsing;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.movie.ex.DTO.MovieDTO;


public class WebParsing {
	public ArrayList<MovieDTO> getDtos(){

		String url = "https://movie.naver.com/movie/running/current.nhn";
		
		ArrayList<MovieDTO> list = new ArrayList<>();
		
		try {
			Document response = Jsoup.connect(url).get();
			
			Elements elements = response.select("ul.lst_detail_t1 li");
			
			Elements images = elements.select("div.thumb a img");
			Elements title = elements.select("dl.lst_dsc dt.tit a");
			Elements star = elements.select("dd.star dl.info_star dd div.star_t1 a span.num");
			Elements outline = elements.select("dl.lst_dsc dd dl.info_txt1");
			Elements href = elements.select("div.thumb a");
			Elements reservationRate = elements.select("div.star_t1.b_star span.num");
			
			for(int i=0;i<elements.size();i++) {
				/*MovieDTO 객체 생성*/
				MovieDTO dto = new MovieDTO();
				String Shref = href.get(i).attr("href");
				
				dto.setImgURL(new URL(images.get(i).attr("src")));
				dto.setTitle(title.get(i).text());
				dto.setRating(star.get(i).text());
				
				if(i<reservationRate.size()) dto.setReservationRate(reservationRate.get(i).text());
				else dto.setReservationRate("-");
				
				dto.setMovieNo(Shref.substring(Shref.indexOf("=")+1));
				
				if(outline.get(i).childNodeSize()==13) {
					dto.setOutline(outline.get(i).child(1).text());
					dto.setDirector(outline.get(i).child(3).text());
					dto.setActor(outline.get(i).child(5).text());
				}else {
					dto.setOutline(outline.get(i).child(1).text());
					dto.setDirector(outline.get(i).child(3).text());
					dto.setActor("-");
				}
				
				list.add(dto);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public MovieDTO getMovieInfo(String movieNo){
		String url = "https://movie.naver.com/movie/bi/mi/basic.nhn?code=" + movieNo;
		String viewURL = "https://movie.naver.com/movie/bi/mi/photoViewPopup.nhn?movieCode=" + movieNo;
		MovieDTO info = null;
		
		try {
			Document response = Jsoup.connect(url).get();
			Document response2 = Jsoup.connect(viewURL).get();
			
			String title = response.select("h3.h_movie a").first().text();
			String story = response.select("div.story_area h5.h_tx_story").text() + "\r\n"
							+ response.select("div.story_area p.con_tx").text();
			URL ImgURL = new URL(response2.select("img#targetImage").attr("src"));
			
			info = new MovieDTO(ImgURL, title, story);
		} catch (IOException e) {e.printStackTrace();}
		
		return info;
	}
}
