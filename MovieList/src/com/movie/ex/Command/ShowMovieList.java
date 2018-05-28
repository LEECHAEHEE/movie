package com.movie.ex.Command;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.movie.ex.JPanel.MenuPanel;
import com.movie.ex.JPanel.ScrollPanel;
import com.movie.ex.JPanel.SlideControlPanel;
import com.movie.ex.JPanel.SlidePanel;

public class ShowMovieList extends JFrame{
	/*style*/
	final Color WhiteBackgroundColor = new Color(255, 255, 255);
	
	/*images print*/
	public ShowMovieList() {
		super("movie list");
		
		/*JFrame*/
		setBounds(300,100,1015,780);
		Container contentPane = this.getContentPane();
		setBackground(WhiteBackgroundColor);
		contentPane.setLayout(null);
		
		/*회원 이름, 로그인, 로그아웃 등 메뉴 탭*/
		JPanel menuPanel = new MenuPanel();
		
		/*slidePanel : 1위부터 10위까지 슬라이딩 메뉴 JLable 넣을 Panel
		 * 한 JLable 에는 다섯개의 content JLable이 들어간다.
		 * */
		JPanel slidePanel = new SlidePanel(this);
		
		
		/*scrollPanel : 11위부터 영화담는 패널 중 가장 큰 패널, 이후 scrollpane 지정한다.*/
		JPanel scrollPanel = new ScrollPanel(this);
		
		JScrollPane jScrollPane = new JScrollPane(scrollPanel);
		jScrollPane.getVerticalScrollBar().setUnitIncrement(20);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane.setBounds(0,320,1000,420);
		
		contentPane.add(menuPanel);
		contentPane.add(slidePanel);
		contentPane.add(jScrollPane);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);	
	}

}