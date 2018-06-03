package com.movie.ex.HomePanel;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.movie.ex.DTO.MovieDTO;
import com.movie.ex.DTO.SingletonDTO;
import com.movie.ex.Listener.PreviewPosterClickListener;
import com.movie.ex.Parsing.WebParsing;


public class MoviePreview extends JPanel{
	/*초기값*/
	final int x = 0;		//슬라이딩 위한 x축 변수
	final int delay = 1;		//슬라이딩 속도 
	public Timer Ltimer, Rtimer;
	
	ArrayList<MovieDTO> dtos =  SingletonDTO.getInstance().dtos;
	WebParsing parsing = new WebParsing();
	
	public MoviePreview(JFrame frame) {
		setLayout(null);
		setPreferredSize(new Dimension(1100,300));

		JLabel previewLabel = new JLabel("영화 예고편",SwingConstants.CENTER);
		previewLabel.setFont(new Font("맑은 고딕",Font.PLAIN,30));
		previewLabel.setBorder(new EmptyBorder(20, 0, 10, 0));
		previewLabel.setBorder(new MatteBorder(0,0,2,0,Color.LIGHT_GRAY));
		previewLabel.setSize(1100,50);
		previewLabel.setOpaque(true);
		previewLabel.setBackground(Color.WHITE);
		
		/*
		 *	두개의 패널 사용. 하나의 패널에는 총 4개의 영화 미리보기가 들어간다..
		 */
		JPanel firstPanel = new JPanel();
		firstPanel.setLayout(new GridLayout(1, 4));
		firstPanel.setBounds(x, 50, 1100, 250);
		
		JPanel secondPanel = new JPanel();
		secondPanel.setLayout(new GridLayout(1, 4));
		secondPanel.setBounds(x+1100,50,1100,250);
		
		
		/*총 8개의 미리보기가 들어간다.*/
		for(int i=0;i<8;i++) {
			String movieNum = dtos.get(i).getMovieNo();
			URL previewImg = parsing.getMovieInfo(dtos.get(i).getMovieNo()).getPreviewURL();

			Random random = new Random();
			int r = random.nextInt(255);
			int g = random.nextInt(255);
			int b = random.nextInt(255);
			
			/*각각의 사진과 영화제목 넣을 Panel*/
			JPanel contentPanel = new JPanel();
			contentPanel.setLayout(null); //&&&
			contentPanel.setOpaque(true);
			contentPanel.setPreferredSize(new Dimension(275, 210));
			contentPanel.setBackground(new Color(255,255,255));

			/*포스터에 들어갈 미리보기 image*/
			ImageIcon imageIcon = new ImageIcon(previewImg);
			Image image= imageIcon.getImage();
			Image newImage = image.getScaledInstance(230, 150, Image.SCALE_SMOOTH);
			
			/*포스터 들어갈 Lable*/
			JLabel posterLabel = new JLabel(new ImageIcon(newImage));
			posterLabel.setBounds(20, 20, 230, 150); //&&&
//			posterLabel.setSize(275,150);
			
			/*padding 값 부여*/
			posterLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
			
			/*각 포스터 구분하기 위한 이름 지정*/
			posterLabel.setName(String.valueOf(i));
			
			/*각 포스터 담은 JLabel에 리스너 등록*/
			posterLabel.addMouseListener(new PreviewPosterClickListener(frame).listener);
			
			/*영화 제목 Label*/
			JLabel titleLabel = new JLabel("<html>&nbsp&nbsp" + dtos.get(i).getTitle() + "<br/>" + "&nbsp&nbsp★&nbsp " + dtos.get(i).getRating() + "</html>");
//			titleLabel.setPreferredSize(new Dimension(230,20));
			titleLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
			titleLabel.setForeground(Color.black);
			titleLabel.setBounds(20,170,230,70);
			titleLabel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.lightGray));
//			titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 15, 0, 0));
			
			contentPanel.add(posterLabel);
			contentPanel.add(titleLabel);
			
			if(i<4) {
				firstPanel.add(contentPanel);
			}else {
				secondPanel.add(contentPanel);
			}
		}//for(int i=0;i<7;i++) {
		
		add(previewLabel);
		add(firstPanel);
		add(secondPanel);
		
		/*Timer 이용한 슬라이딩 구현 - 왼쪽*/
		ActionListener leftTaskPerformed = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println("firstPanel x : " + firstPanel.getLocation().x + "secondPanel x : " + secondPanel.getLocation().x);
				if(firstPanel.getLocation().x==-1100) firstPanel.setLocation(1100,50);
				else if(secondPanel.getLocation().x==-1100) secondPanel.setLocation(1100,50);
				
				firstPanel.setLocation(firstPanel.getLocation().x-5, 50);
				secondPanel.setLocation(secondPanel.getLocation().x-5,50);
				
				if(firstPanel.getLocation().x%275==0) Ltimer.stop(); 
				
				if(firstPanel.getLocation().x==-1100) firstPanel.setLocation(1100,50);
				else if(secondPanel.getLocation().x==-1100) secondPanel.setLocation(1100,50);
			}
		};//ActionListener taskPerformed = new ActionListener() {
		
		/*Timer 이용한 슬라이딩 구현 - 오른쪽*/
		ActionListener rightTaskPerformed = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(firstPanel.getLocation().x==1100) firstPanel.setLocation(-1100,50);
				else if(secondPanel.getLocation().x==1100) secondPanel.setLocation(-1100,50);
				
				firstPanel.setLocation(firstPanel.getLocation().x+5, 50);
				secondPanel.setLocation(secondPanel.getLocation().x+5,50);
				
				if(firstPanel.getLocation().x%275==0) Rtimer.stop(); 
				
				if(firstPanel.getLocation().x==1100) firstPanel.setLocation(-1100,50);
				else if(secondPanel.getLocation().x==1100) secondPanel.setLocation(-1100,50);
				
//				System.out.println("firstPanel x : " + firstPanel.getLocation().x + "secondPanel x : " + secondPanel.getLocation().x);
			}
		};//ActionListener taskPerformed = new ActionListener() {
		
		Ltimer = new Timer(delay, leftTaskPerformed);
		Rtimer = new Timer(delay, rightTaskPerformed);
	}
}