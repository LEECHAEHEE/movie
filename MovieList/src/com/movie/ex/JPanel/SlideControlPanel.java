package com.movie.ex.JPanel;

import java.awt.Dimension;
import java.awt.Transparency;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.movie.ex.Command.MovieListener;

public class SlideControlPanel extends JPanel{
	public SlideControlPanel() {
		setBounds(0,290,1000,30);
		JButton controlBtn = new JButton();
		controlBtn.setPreferredSize(new Dimension(20, 20));
		controlBtn.setIcon(new ImageIcon("./images/stop.png"));			//버튼 배경화면
		controlBtn.setBorderPainted(false);								//버튼 테두리 삭제
		controlBtn.setFocusPainted(false);								//버튼 선택 되었을 때 테두리 삭제
		controlBtn.setContentAreaFilled(false);							//버튼 내용영역 비우기
		controlBtn.addActionListener(new MovieListener(controlBtn).controlBtnListener);
		
		add(controlBtn);
	}
}
