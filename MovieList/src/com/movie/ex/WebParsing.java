package com.movie.ex;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MovieList {
	public ArrayList<MovieDTO> showList(){

		/*������ ������ URL*/
		String url = "https://movie.naver.com/movie/running/current.nhn";
		
		/*MovieDTO��ü�� ���� �� �ִ� ArrayList����*/
		ArrayList<MovieDTO> list = new ArrayList<>();
		
		try {
			/*�����Ͽ� HTML ���� Document��ü�� ����*/
			Document response = Jsoup.connect(url).get();
			
			/*������ ���� Elements ��ü�� ����*/
			Elements elements = response.select("ul.lst_detail_t1 li");
			
			Elements images = elements.select("div.thumb a img");
			Elements title = elements.select("dl.lst_dsc dt.tit a");
			Elements star = elements.select("dd.star dl.info_star dd div.star_t1 a span.num");
			Elements outline = elements.select("dl.lst_dsc dd dl.info_txt1");
			
			/*1~100�� ���� ��ȭ ������ ���� MovieDTO ��ü�� ��Ƽ� ArrayList�� ��´�.*/
			for(int i=0;i<elements.size();i++) {
				/*MovieDTO ��ü ����*/
				MovieDTO dto = new MovieDTO();
				
				/*setter �̿��ؼ� ��ü ���� ����*/
				dto.setImgURL(new URL(images.get(i).attr("src")));
				dto.setTitle(title.get(i).text());
				dto.setRating(star.get(i).text());
				/*�⿬ ������ ���� ��ȭ ������ �ִ� ���� �����ؼ� ����.*/
				if(outline.get(i).childNodeSize()==13) {
					dto.setOutline(outline.get(i).child(1).text());
					dto.setDirector(outline.get(i).child(3).text());
					dto.setActor(outline.get(i).child(5).text());
				}else {
					dto.setOutline(outline.get(i).child(1).text());
					dto.setDirector(outline.get(i).child(3).text());
					dto.setActor("-");
				}
				
				/*MovieDTO��ü ArrayList�� ����*/
				list.add(dto);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
}


