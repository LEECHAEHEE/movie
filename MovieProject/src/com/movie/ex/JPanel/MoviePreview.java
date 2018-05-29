package com.movie.ex.JPanel;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;


public class MoviePreview extends JPanel{
	/*초기값*/
	final int x = 0;		//슬라이딩 위한 x축 변수
	final int delay = 1;		//슬라이딩 속도 
	public Timer Ltimer, Rtimer;
	
	public MoviePreview(JFrame frame) {
		setLayout(null);
		setPreferredSize(new Dimension(1100,300));

		JLabel previewLabel = new JLabel("영화 프리뷰",SwingConstants.CENTER);
		previewLabel.setFont(new Font("맑은 고딕",Font.PLAIN,30));
		previewLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
		previewLabel.setSize(1100,50);
		previewLabel.setOpaque(true);
		previewLabel.setBackground(Color.WHITE);
		
		/*
		 *	총 두개의 패널이 사용됩니다. 하나의 패널에는 총 4개의 영화 미리보기가 들어갑니다.
		 */
		JPanel firstPanel = new JPanel();
		firstPanel.setLayout(new GridLayout(1, 4));
		firstPanel.setBounds(x, 50, 1100, 250);
		
		JPanel secondPanel = new JPanel();
		secondPanel.setLayout(new GridLayout(1, 4));
		secondPanel.setBounds(x+1100,50,1100,250);
		
		
		/*총 8개의 미리보기가 들어간다.*/
		for(int i=0;i<8;i++) {
			Random random = new Random();
			int r = random.nextInt(255);
			int g = random.nextInt(255);
			int b = random.nextInt(255);
			
			/*각각의 사진과 영화제목 넣을 Panel*/
			JPanel contentPanel = new JPanel();
			contentPanel.setOpaque(true);
			contentPanel.setPreferredSize(new Dimension(275, 250));
			contentPanel.setBackground(new Color(r, g, b,100));
			
			/*포스터 들어갈 Lable*/
			JLabel posterLabel = new JLabel();
			posterLabel.setSize(275,150);
			/*padding 값 부여*/
			
			/*각 포스터 구분하기 위한 이름 지정*/
			posterLabel.setName(String.valueOf(i));
			
			/*각 포스터 담은 JLabel에 리스너 등록*/
//			posterLabel.addMouseListener(new PosterClickListener(frame));
			
			/*영화 제목 Label*/
			JLabel titleLabel = new JLabel("test");
			titleLabel.setPreferredSize(new Dimension(275,20));
			
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
				firstPanel.setLocation(firstPanel.getLocation().x-1, 50);
				secondPanel.setLocation(secondPanel.getLocation().x-1,50);
				if(firstPanel.getLocation().x%275==0) Ltimer.stop();
				
				if(secondPanel.getLocation().x<-1100) {
					secondPanel.setLocation(1100,50);
				}else if(firstPanel.getLocation().x<-1100) {
					firstPanel.setLocation(1100,50);
				}
			}
		};//ActionListener taskPerformed = new ActionListener() {
		
		/*Timer 이용한 슬라이딩 구현 - 오른쪽*/
		ActionListener rightTaskPerformed = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				firstPanel.setLocation(firstPanel.getLocation().x+1, 50);
				secondPanel.setLocation(secondPanel.getLocation().x+1,50);
				if(secondPanel.getLocation().x%275==0) Rtimer.stop();
				
				if(firstPanel.getLocation().x>1100) {
					firstPanel.setLocation(-1100,50);
				}else if(secondPanel.getLocation().x>1100) {
					secondPanel.setLocation(-1100,50);
				}
			}
		};//ActionListener taskPerformed = new ActionListener() {
		
		Ltimer = new Timer(delay, leftTaskPerformed);
		Rtimer = new Timer(delay, rightTaskPerformed);
	}
}


















