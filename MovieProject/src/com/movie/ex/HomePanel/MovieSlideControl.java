package com.movie.ex.HomePanel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.movie.ex.Listener.ButtonListener;


public class MovieSlideControl extends JPanel {
	public MovieSlideControl() {
	}
	
	public MovieSlideControl(MovieSlide movieSlide) {
		setBounds(0,405,1100,45);
		setBackground(new Color(41,41,41));
		JButton controlBtn = new JButton();
		controlBtn.setBorder(new EmptyBorder(0, 0, 20, 0));
		controlBtn.setPreferredSize(new Dimension(45, 50));
		controlBtn.setIcon(new ImageIcon("./images/stop.png"));			//버튼 배경화면
		controlBtn.setBorderPainted(false);								//버튼 테두리 삭제
		controlBtn.setFocusPainted(false);								//버튼 선택 되었을 때 테두리 삭제
		controlBtn.setContentAreaFilled(false);							//버튼 내용영역 비우기
		controlBtn.addActionListener(new ButtonListener(controlBtn,movieSlide).controlBtnListener);
		
		add(controlBtn);
	}
}
