package com.movie.ex.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.movie.ex.Listener.ButtonListener;
import com.movie.ex.Main.Main;

public class TopMenu extends JPanel {
	boolean isLogin=Main.isLogin;		//로그인 여부에 따라 topMenu 다르게 나타납니다.
	ButtonListener listener = new ButtonListener();
	
	public TopMenu(JFrame frame) {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(1100, 100));
		setBackground(Color.white);
		/*로고*/
		JLabel logo = new JLabel("    LEGO AREA");
		logo.setFont(new Font("맑은 고딕", Font.ITALIC, 30));
		add(logo, BorderLayout.WEST);
		
		/*로그인여부에 따라 다르게 나타나는 회원 메뉴*/
		JPanel btnWrap = new JPanel();
		btnWrap.setBackground(Color.white);
		btnWrap.setPreferredSize(new Dimension(300, 40));
		btnWrap.setLayout(new FlowLayout());
		
		JLabel greetLabel = new JLabel();
		greetLabel.setText((isLogin)?"USER":"GUEST");
		JButton registerBtn = new JButton("회원가입");
		JButton loginBtn = new JButton("로그인");
		JButton logoutBtn = new JButton("로그아웃");
		JButton memberInfoBtn = new JButton("회원정보");
		
		/*각각 버튼에 리스너 추가*/
		loginBtn.addActionListener(new ButtonListener(frame).loginBtnListener);
		registerBtn.addActionListener(new ButtonListener().registerBtnListener);
		logoutBtn.addActionListener(new ButtonListener(frame).logoutBtnListener);
		memberInfoBtn.addActionListener(new ButtonListener().memberInfoBtnListener);
		
		/*로그인 여부에 따라 달라지는 상단 메뉴*/
		if(isLogin) {
			btnWrap.add(greetLabel);
			btnWrap.add(memberInfoBtn);
			btnWrap.add(logoutBtn);
		}else {
			btnWrap.add(greetLabel);
			btnWrap.add(loginBtn);
			btnWrap.add(registerBtn);
		}
		
		add(btnWrap, BorderLayout.EAST);
		//setBounds(0,0,1000,40);
	}
}
