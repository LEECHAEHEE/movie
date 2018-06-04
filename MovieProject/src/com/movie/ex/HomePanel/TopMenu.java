package com.movie.ex.HomePanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.movie.ex.Listener.ButtonListener;
import com.movie.ex.Main.Main;

public class TopMenu extends JPanel {
	boolean isLogin=Main.isLogin;		//로그인 여부에 따라 topMenu 다르게 나타납니다.
	ButtonListener listener = new ButtonListener();
	
	public JPanel btnWrap;
	public JLabel greetLabel;
	
	public JButton registerBtn 		= new JButton("회원가입");
	public JButton loginBtn 		= new JButton("로그인");	
	public JButton logoutBtn 		= new JButton("로그아웃");
	public JButton memberInfoBtn 	= new JButton("회원정보");
	public JButton bookButton		= new JButton("예매하기");
	
	public TopMenu(JFrame frame) {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(1100, 100));
		setBackground(Color.white);
		/*로고*/
		JLabel logo = new JLabel("    LEGO AREA");
		logo.setFont(new Font("맑은 고딕", Font.ITALIC, 30));
		add(logo, BorderLayout.WEST);
		
		/*로그인여부에 따라 다르게 나타나는 회원 메뉴*/
		btnWrap = new JPanel();
		btnWrap.setBorder(new EmptyBorder(30, 0, 0, 0));
		btnWrap.setBackground(Color.white);
		btnWrap.setPreferredSize(new Dimension(500, 40));
		btnWrap.setLayout(new FlowLayout());
		
		greetLabel = new JLabel();
		greetLabel.setPreferredSize(new Dimension(100, 40));
		greetLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		greetLabel.setText("GUEST");
		
		/*각각 버튼에 리스너 추가*/
		loginBtn.addActionListener(new ButtonListener(frame,this).loginBtnListener);
		registerBtn.addActionListener(new ButtonListener().registerBtnListener);
		logoutBtn.addActionListener(new ButtonListener(frame,this).logoutBtnListener);
		memberInfoBtn.addActionListener(new ButtonListener().memberInfoBtnListener);
		bookButton.addActionListener(new ButtonListener().bookBtnListener);
		
		/*버튼 꾸미기*/
		registerBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		registerBtn.setBorderPainted(false);
		registerBtn.setFocusPainted(false);
		registerBtn.setContentAreaFilled(false);
		
		loginBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		loginBtn.setPreferredSize(new Dimension(100, 30));
		loginBtn.setBorderPainted(false);
		loginBtn.setFocusPainted(false);
		loginBtn.setContentAreaFilled(false);
		
		logoutBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		logoutBtn.setPreferredSize(new Dimension(100, 30));
		logoutBtn.setBorderPainted(false);
		logoutBtn.setFocusPainted(false);
		logoutBtn.setContentAreaFilled(false);
		
		memberInfoBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		memberInfoBtn.setBorderPainted(false);
		memberInfoBtn.setFocusPainted(false);
		memberInfoBtn.setContentAreaFilled(false);
		
		bookButton.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		bookButton.setFocusPainted(false);
		bookButton.setContentAreaFilled(false);
		
		/*버튼 추가*/
		btnWrap.add(greetLabel);
		btnWrap.add(loginBtn);
		btnWrap.add(registerBtn);
		btnWrap.add(bookButton);
		
		add(btnWrap, BorderLayout.EAST);
	}
}
