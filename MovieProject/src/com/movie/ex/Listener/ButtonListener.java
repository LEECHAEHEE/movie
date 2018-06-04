package com.movie.ex.Listener;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.movie.ex.HomePanel.MovieSlide;
import com.movie.ex.HomePanel.TopMenu;
import com.movie.ex.Main.Main;

public class ButtonListener{
	JFrame frame;
	JPanel panel;
	JButton button;
	MovieSlide movieSlide;
	TopMenu topMenu;
	
	boolean flag=true;
	
	public ButtonListener() { }
	
	public ButtonListener(JFrame frame) { this.frame=frame; }
	
	public ButtonListener(JFrame frame, TopMenu topMenu) { 
		this.frame=frame; this.topMenu = topMenu; 
	}
	
	public ButtonListener(JButton button, MovieSlide movieSlide) {
		this.button = button;
		this.movieSlide = movieSlide;
	}
	
	/*회원가입 버튼*/
	public ActionListener registerBtnListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			//TODO 회원가입 클래스 이곳에
			System.out.println("회원가입");

//			Main.isLogin = true;
//			frame.setVisible(false);
//			new HomeScreen();
		}
	};
	
	/*로그인 버튼*/
	public ActionListener loginBtnListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			//TODO 로그인 클래스 이곳에
			Main.isLogin=true;
			
			/*로그인 성공*/
			if(Main.isLogin) {
				topMenu.btnWrap.removeAll();

				topMenu.greetLabel = new JLabel("User");
				topMenu.greetLabel.setPreferredSize(new Dimension(100, 40));
				topMenu.greetLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
				
				topMenu.btnWrap.add(topMenu.greetLabel);
				topMenu.btnWrap.add(topMenu.logoutBtn);
				topMenu.btnWrap.add(topMenu.memberInfoBtn);
				topMenu.btnWrap.add(topMenu.bookButton);
				
				topMenu.repaint();
				topMenu.revalidate();
				frame.revalidate();
			}
			/*로그인 실패*/
			else {
				
			}
		}
	};
	
	/*로그아웃 버튼*/
	public ActionListener logoutBtnListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Main.isLogin = false;
			topMenu.btnWrap.removeAll();
			
			topMenu.greetLabel = new JLabel("Guest");
			topMenu.greetLabel.setPreferredSize(new Dimension(100, 40));
			topMenu.greetLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
			
			topMenu.btnWrap.add(topMenu.greetLabel);
			topMenu.btnWrap.add(topMenu.loginBtn);
			topMenu.btnWrap.add(topMenu.registerBtn);
			topMenu.btnWrap.add(topMenu.bookButton);
			
			topMenu.repaint();
			topMenu.revalidate();
			frame.revalidate();
		}
	};
	
	/*회원정보 버튼*/
	public ActionListener memberInfoBtnListener = new ActionListener() {
		//TODO 회원정보 보기 클래스
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("회원정보");
		}
	};
	
	/*예매하기 버튼*/
	public ActionListener bookBtnListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("예매하기");

//			ReserveFrame window = new ReserveFrame();
//			Thread thread = new Thread(window);
//			thread.start();
		}
	};
	
	/*슬라이딩 컨트롤 버튼*/
	public ActionListener controlBtnListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			ImageIcon play = new ImageIcon("./images/play.png"); 
			ImageIcon stop = new ImageIcon("./images/stop.png"); 
			
			if(flag) {
				button.setIcon(play);
				movieSlide.timer.stop();
			}else {
				button.setIcon(stop);
				movieSlide.timer.restart();
			}
			flag=!flag;
		}
	};
}
