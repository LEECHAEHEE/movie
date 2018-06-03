package com.movie.ex.HomePanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class MovieRating extends JPanel{
	public MovieRating() {
		setLayout(null);
		setBounds(25,25,500,450);
		setBorder(new LineBorder(Color.black));
		setBackground(Color.white);
		
		/*전체 감싸는 영역*/
		JPanel wrapPanel = new JPanel();
		wrapPanel.setLayout(null);
		wrapPanel.setBounds(20,10,460,430);
		
		/*제목 영역*/
		JPanel topPanel = new JPanel();
		topPanel.setBounds(0,0,460,60);
		topPanel.setLayout(null);
		topPanel.setBorder(new MatteBorder(0,0,2,0,Color.gray));
		topPanel.setBackground(Color.white);
		
		/*타이틀*/
		JLabel titleLabel= new JLabel("최근 작성된 리뷰");
		titleLabel.setBounds(20,15,200,30);
		titleLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		/*더보기 버튼*/
		ImageIcon imageIcon = new ImageIcon("./images/plus.png");
		Image image = imageIcon.getImage();
		Image newImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		JButton moreIcon = new JButton(new ImageIcon(newImage));
		moreIcon.setBounds(410,15,30,30);
		moreIcon.setContentAreaFilled(false);

		/*제목 라벨, 더보기 버튼 추가*/
		topPanel.add(titleLabel);
		topPanel.add(moreIcon);
		
		/*내용 영역*/
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(5, 1));
		contentPanel.setBounds(0,60,460,370);
		contentPanel.setBackground(Color.red);
		
		for(int i=0;i<5;i++) {
			JPanel reviewPanel = new JPanel();
			reviewPanel.setPreferredSize(new Dimension(460, 80));
			reviewPanel.setBackground(Color.WHITE);
			if(i<4) reviewPanel.setBorder(new MatteBorder(0,0,1,0,Color.LIGHT_GRAY));
			contentPanel.add(reviewPanel);
		}
		
		wrapPanel.add(topPanel);
		wrapPanel.add(contentPanel);
		add(wrapPanel);
	}
}
