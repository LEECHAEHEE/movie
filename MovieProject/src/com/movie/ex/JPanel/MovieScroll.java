package com.movie.ex.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;

import com.movie.ex.DTO.MovieDTO;
import com.movie.ex.DTO.SingletonDTO;
import com.movie.ex.Listener.SliderPosterClickListener;


public class MovieScroll extends JScrollPane {
	MatteBorder bottomBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black);
	
	ArrayList<MovieDTO> dtos =  SingletonDTO.getInstance().dtos;
	Image image;
	
	public MovieScroll(HomeScreen frame) {
		setBounds(0,320,1000,420);
		getVerticalScrollBar().setUnitIncrement(20);							//스크롤 속도
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);		//수직 스크롤 항상 존재
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);	//수평 스크롤은 없음
		
		/*scrollPanel : 11위 부터 100위 까지 영화 정보 담은 JPanel*/
		JPanel scrollPanel = new JPanel();
		scrollPanel.setLayout(new GridLayout(dtos.size()-10,1));	//gridlayout 이며 11~100위 까지 담아야 한다.
		scrollPanel.setBounds(0,320,1000,420);
		
		for(int i=10;i<dtos.size();i++) {
			
			try {
				image = ImageIO.read(dtos.get(i).getImgURL());
			} catch (IOException e) {e.printStackTrace();}
			
			/*영화 포스터를 담은 JLabel 객체 생성*/
			JLabel label = new JLabel(new ImageIcon(image));
			
			label.setName(String.valueOf(i));
			label.addMouseListener(new SliderPosterClickListener(frame));
			
			/*rPanel : 각각의 영화 정보 담는 wrap Panel*/
			JPanel rPanel = new JPanel();
			rPanel.setPreferredSize(new Dimension(1000, 150));
			rPanel.setBorder(bottomBorder);
			rPanel.setLayout(null);
			
			/*imgPanel : 영화 포스터 담는 패널*/
			JPanel imgPanel = new JPanel();
			imgPanel.setBackground(Color.white);
			imgPanel.add(label);
			imgPanel.setBounds(0,0,130,149);
			
			/*infoPanel : 영화 정보 담는 패널*/
			JPanel infoPanel = new JPanel();
			infoPanel.setLayout(new GridLayout(5, 1));
			infoPanel.setBounds(130,0,870,149);
			infoPanel.setBackground(Color.white);
			
			ArrayList<JLabel> info = new ArrayList<>();
			info.add(new JLabel(dtos.get(i).getTitle()));
			info.add(new JLabel("네티즌 " + dtos.get(i).getRating() + "          예매율 " + dtos.get(i).getReservationRate() + "%"));
			info.add(new JLabel("개요 : " + dtos.get(i).getOutline()));
			info.add(new JLabel("감독 : " + dtos.get(i).getDirector()));
			info.add(new JLabel("출연 : " + "actor"));
			
			for(int j=0;j<info.size();j++) {
				if(j==0) info.get(j).setFont(new Font("맑은 고딕", Font.BOLD, 13));
				else info.get(j).setFont(new Font("맑은 고딕", Font.PLAIN, 13));
				infoPanel.add(info.get(j));
			}
			
			/*rPanel에 영화 포스터와 정보 삽입*/
			rPanel.add(imgPanel);
			rPanel.add(infoPanel);
			
			/*scroll panel에 영화 패널을 담는다.*/
			scrollPanel.add(rPanel);
			
		}//for(int i=10;i<dtos.size();i++) {
		
		/*JScrollPane의 Viewport에 scrollPane 넣는다.*/
		getViewport().add(scrollPanel);
	}
}
