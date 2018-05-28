package com.movie.ex.JPanel;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/*
 * 홈  
 */
public class HomeScreen extends JFrame{
	public HomeScreen() {
		super("영화 프로젝트");
		
		/*프레임 화면 설정*/
		setBounds(300,100,1015,780);
		Container contentPane = this.getContentPane();
		setBackground(Color.WHITE);
		contentPane.setLayout(null);
		/*최상단 메뉴, 로고, 회원 이름, 로그인 or 로그아웃 회원정보 버튼*/
		JPanel topMenu = new TopMenu(this);
		
		/*Top10 영화 슬라이더*/
		JPanel movieSlide = new MovieSlide(this);
		
		/*11~100위 영화 스크롤 형태*/
		JScrollPane movieScroll = new MovieScroll(this);
		
		contentPane.add(topMenu);
		contentPane.add(movieSlide);
		contentPane.add(movieScroll);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
