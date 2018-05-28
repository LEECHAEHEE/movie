package com.movie.ex.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.movie.ex.Listener.TopBtnListener;
import com.movie.ex.Main.Main;

public class TopMenu extends JPanel {
	boolean isLogin=Main.isLogin;		//로그인 여부에 따라 topMenu 다르게 나타납니다.
	TopBtnListener listener = new TopBtnListener();
	
	public TopMenu(JFrame frame) {
		setLayout(new BorderLayout());
		
		/*로고*/
		JLabel logo = new JLabel("영화 프로젝트");
		logo.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		add(logo, BorderLayout.WEST);
		
		/*로그인여부에 따라 다르게 나타나는 회원 메뉴*/
		JPanel btnWrap = new JPanel();
		btnWrap.setPreferredSize(new Dimension(300, 40));
		btnWrap.setLayout(new FlowLayout());
		
		JLabel greetLabel = new JLabel();
		greetLabel.setText((isLogin)?"USER":"GUEST");
		
		JButton loginBtn = new JButton("로그인");
		JButton logoutBtn = new JButton("로그아웃");
		JButton memberInfoBtn = new JButton("회원정보");
		
		/*각각 버튼에 리스너 추가*/
		loginBtn.addActionListener(new TopBtnListener(frame).loginBtnListener);
		logoutBtn.addActionListener(new TopBtnListener(frame).logoutBtnListener);
		memberInfoBtn.addActionListener(new TopBtnListener().memberInfoBtnListener);
		
		/*로그인 여부에 따라 달라지는 상단 메뉴*/
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
