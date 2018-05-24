package com.movie.ex;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ShowMovieList extends JFrame{
	MovieList list= new MovieList();
	ArrayList<MovieDTO> dtos = list.showList();
	Image image = null;
	
	/*images print*/
	public ShowMovieList() {
		super("movie list");

		/*set position & size of jframe*/
		setBounds(100,100,515,700);
		Container contentPane = this.getContentPane();
		contentPane.setLayout(null);
		
		/*top panel has top ranked movie 1st~3rd*/
		JPanel topPanel = new JPanel();
		topPanel.setBounds(0,0,500,220);
		topPanel.setLayout(new GridLayout(1, 3));
		
		/*top panel has 3 panels each have 1st~3rd movie info*/
		JPanel[] rankPanelList = new JPanel[3];
		
		/*topJpanel 객체 생성*/
		for(int i=0;i<3;i++) {
			rankPanelList[i] = new JPanel();
//			rankPanelList[i].setLayout(new GridLayout(5, 1));
		}
		
		/*1위부터 3위까지 정보 삽입*/
		for(int i=0;i<3;i++) {
			try {
				image = ImageIO.read(dtos.get(i).getImgURL());
				JLabel label = new JLabel(new ImageIcon(image));
//				JLabel title = new JLabel(dtos.get(i).getTitle());
				rankPanelList[i].add(label);
				topPanel.add(rankPanelList[i]);
//				topPanel.add(title);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/*3위 이후 영화담는 패널 중 가장 큰 패널, 이후 scrollpane 지정한다.*/
		JPanel rankPanel= new JPanel();
		rankPanel.setLayout(new GridLayout(dtos.size()-3,1));
		
		/*4~100영화 정보 panel 삽입*/
		for(int i=3;i<dtos.size();i++) {
			Random random = new Random();
			int r = random.nextInt(255);
			int g = random.nextInt(255);
			int b = random.nextInt(255);
			/*각각의 영화 정보 담는 wrap Panel*/
			JPanel rPanel = new JPanel();
			rPanel.setBorder(null);
			rPanel.setLayout(null);
			
			/*영화 포스터 담는 패널*/
			JPanel imgPanel = new JPanel();
			imgPanel.setBackground(new Color(r, g, b, 50));
			imgPanel.setBounds(0,0,130,150);
			try {
				image = ImageIO.read(dtos.get(i).getImgURL());
//				JButton button = new JButton().setBackground(new ImageIcon(image));
				JLabel label = new JLabel(new ImageIcon(image));
				imgPanel.add(label);
				topPanel.add(imgPanel);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*영화 정보 담는 패널*/
			JPanel infoPanel = new JPanel();
			infoPanel.setLayout(new GridLayout(5, 1));
			infoPanel.setBackground(new Color(r, g, b, 90));
			infoPanel.setBounds(130,0,360,150);
			JLabel title = new JLabel(dtos.get(i).getTitle());
			JLabel rating = new JLabel("평점 : " + dtos.get(i).getRating());
			JLabel outline = new JLabel("개요 : " + dtos.get(i).getOutline());
			JLabel director = new JLabel("감독 : " + dtos.get(i).getDirector());
			JLabel actor = new JLabel("출연 : " + "actor");
			infoPanel.add(title);
			infoPanel.add(rating);
			infoPanel.add(outline);
			infoPanel.add(director);
			infoPanel.add(actor);
			
			/*wrap Panel에 영화 포스터와 정보 삽입*/
			rPanel.add(imgPanel);
			rPanel.add(infoPanel);
			
			/*wrap Panel 크기 고정*/
			rPanel.setPreferredSize(new Dimension(500, 150));
			
			/*scroll panel에 영화 패널을 담는다.*/
			rankPanel.add(rPanel);
		}
		
		JScrollPane jScrollPane = new JScrollPane(rankPanel);
		jScrollPane.getVerticalScrollBar().setUnitIncrement(20);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane.setPreferredSize(new Dimension(500, 450));
		
		jScrollPane.setBounds(0,220,500,445);
		
		contentPane.add(topPanel);
		contentPane.add(jScrollPane);
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);	
	}

}
