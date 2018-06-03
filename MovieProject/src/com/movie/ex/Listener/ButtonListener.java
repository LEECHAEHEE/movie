package com.movie.ex.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.movie.ex.HomePanel.HomeScreen;
import com.movie.ex.HomePanel.MovieSlide;
import com.movie.ex.Main.Main;

public class ButtonListener{
	JFrame frame;
	JPanel panel;
	JButton button;
	MovieSlide movieSlide;
	boolean flag=true;
	
	public ButtonListener() { }
	public ButtonListener(JFrame frame) { this.frame=frame; }
	public ButtonListener(JButton button, MovieSlide movieSlide) {
		this.button = button;
		this.movieSlide = movieSlide;
	}
	
	/*회원가입 버튼*/
	public ActionListener registerBtnListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			//TODO 회원가입 클래스 이곳에
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
			Main.isLogin = true;
			frame.setVisible(false);
			new HomeScreen();
		}
	};
	
	/*로그아웃 버튼*/
	public ActionListener logoutBtnListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Main.isLogin = false;
			frame.setVisible(false);
			new HomeScreen();
		}
	};
	
	/*회원정보 버튼*/
	public ActionListener memberInfoBtnListener = new ActionListener() {
		//TODO 회원정보 보기 클래스
		@Override
		public void actionPerformed(ActionEvent e) {
		}
	};
	
	/*예매하기 버튼*/
	public ActionListener bookBtnListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
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
