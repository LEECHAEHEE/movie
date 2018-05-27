package com.movie.ex.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.movie.ex.Command.MovieListener;
import com.movie.ex.Command.TmpMain;

public class MenuPanel extends JPanel{
	boolean isLogin;
	MovieListener listener = new MovieListener();
	
	public MenuPanel() {
		/*로그인 여부*/
		if(TmpMain.isLogin) {
			isLogin = true;
		}
		
		setLayout(new BorderLayout());
		
		JLabel logo = new JLabel("영화 프로그램");
		logo.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		add(logo, BorderLayout.WEST);
		
		JPanel btnWrap = new JPanel();
		btnWrap.setPreferredSize(new Dimension(300, 40));
		btnWrap.setLayout(new FlowLayout());
		JLabel greetLabel = new JLabel();
		greetLabel.setText((isLogin)?"USER":"GUEST");
		JButton loginBtn = new JButton("로그인");
		JButton logoutBtn = new JButton("로그아웃");
		JButton memberInfoBtn = new JButton("회원정보");
		
		loginBtn.addActionListener(listener.loginBtnListener);
		logoutBtn.addActionListener(listener.logoutBtnListener);
		memberInfoBtn.addActionListener(listener.memberInfoBtnListener);
		
		if(isLogin) {
			btnWrap.add(greetLabel);
			btnWrap.add(memberInfoBtn);
			btnWrap.add(logoutBtn);
		}else {
			btnWrap.add(greetLabel);
			btnWrap.add(loginBtn);
		}
		
		add(btnWrap, BorderLayout.EAST);
		setBounds(0,0,1000,40);
	}
}
