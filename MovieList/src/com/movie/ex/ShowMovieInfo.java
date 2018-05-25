package com.movie.ex;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class ShowMovieInfo extends JFrame{
	WebParsing parsing = new WebParsing();
	MovieDTO dto = new MovieDTO(); 
	Point mouseClickedLocation = new Point(0,0);
	
	public ShowMovieInfo() {}
	
	public ShowMovieInfo(String movieNo) {
		/*movieNo을 통해 웹 파싱 진행, 영화 정보 얻어온다.*/
		dto = parsing.getMovieInfo(movieNo);
		
		setBounds(1000,400,700,500);
		setLayout(null);
		setName("JFrame");
		/*imgPanel 포스터 들어갈 JPanel*/
		JPanel imgPanel = new JPanel();
		imgPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
		imgPanel.setBounds(0, 0, 300, 500);
		
		/*imgPanel에 들어갈 포스터 image*/
		ImageIcon imageIcon = new ImageIcon(dto.getImgURL());
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(250, 360, Image.SCALE_SMOOTH);
		
		JLabel imgLabel = new JLabel(new ImageIcon(newimg));
		imgLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		imgPanel.add(imgLabel);
		
		
		/*infoPanel 영화 정보 들어갈 JPanel*/
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BorderLayout());
		infoPanel.setBounds(300,0,300,500);
		
		String story = dto.getstory();
		JLabel storyTitle = new JLabel("줄거리", SwingConstants.LEFT);
		storyTitle.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		storyTitle.setPreferredSize(new Dimension(300, 100));
		storyTitle.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		
		JTextArea area = new JTextArea(story);
		area.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		area.setName("JTextArea");
		area.addMouseMotionListener(motionListener);
		area.addMouseListener(mouseAdapter);
		area.setOpaque(false);
		area.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.setSize(400,500);
		area.setEditable(false);
		
		infoPanel.add(storyTitle, BorderLayout.NORTH);
		infoPanel.add(area, BorderLayout.CENTER);
		
		add(imgPanel);
		add(infoPanel);
		
		setUndecorated(true);
		setVisible(true);
		
		
		/*마우스 드래그 시 Frame 이동이 가능하고 Frame 클릭시 창이 닫힌다.*/
		addMouseMotionListener(motionListener);
		addMouseListener(mouseAdapter);
	}
	
	MouseMotionListener motionListener = new MouseMotionListener() {
		@Override
		public void mouseMoved(MouseEvent e) {}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			setLocation(e.getXOnScreen()-mouseClickedLocation.x,e.getYOnScreen()-mouseClickedLocation.y);
		}
	};
	
	MouseAdapter mouseAdapter = new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getComponent().getName().equals("JFrame"))
			{
				mouseClickedLocation.x = e.getX();
				mouseClickedLocation.y = e.getY();
			}
			else if(e.getComponent().getName().equals("JTextArea"))
			{
				mouseClickedLocation.x = e.getX()+300;
				mouseClickedLocation.y = e.getY()+40;
			}
		}
		@Override
		public void mouseClicked(MouseEvent e) {
//			System.out.println("e.getXOnScreen() : " +e.getXOnScreen()); 
//			System.out.println("e.getYOnScreen() : " +e.getYOnScreen());
//			System.out.println("mouseClickedLocation.x : " + mouseClickedLocation.x);
//			System.out.println("mouseClickedLocation.y : "+  mouseClickedLocation.y);
			setVisible(false);
			dispose();
		}
	};
}
