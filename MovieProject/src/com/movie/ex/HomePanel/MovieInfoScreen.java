package com.movie.ex.HomePanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.movie.ex.DTO.MovieDTO;
import com.movie.ex.Listener.MoveWindowListener;
import com.movie.ex.Parsing.WebParsing;


public class MovieInfoScreen extends JFrame{
	WebParsing parsing = new WebParsing();
	MoveWindowListener listener = new MoveWindowListener(this);
	
	MovieDTO dto = new MovieDTO(); 
	Point mouseClickedLocation = new Point(0,0);
	
	public MovieInfoScreen(String movieNo) {
		/*movieNo을 통해 웹 파싱 진행, 영화 정보 얻어온다.*/
		dto = parsing.getMovieInfo(movieNo);
//		System.out.println(dto.getPreviewURL());
		
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
//		Image newimg = image.getScaledInstance(250, 360, Image.SCALE_SMOOTH);
		
		JLabel imgLabel = new JLabel(new ImageIcon(image));
		imgLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		imgPanel.add(imgLabel);
		
		/*infoPanel 영화 정보 들어갈 JPanel*/
		JPanel infoPanel = new JPanel();
//		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.setBounds(300,0,400,500);
		
		String story = dto.getstory().trim();
		JLabel storyTitle = new JLabel("줄거리",SwingConstants.LEFT);
		storyTitle.setPreferredSize(new Dimension(400, 60));
		storyTitle.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
		storyTitle.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		
		JTextArea area = new JTextArea(story);
		area.setBorder(BorderFactory.createEmptyBorder(20, 25, 0, 50));
		area.setName("JTextArea");
		area.addMouseMotionListener(listener.mouseAdapter);
		area.addMouseListener(listener.mouseAdapter);
		area.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		area.setLineWrap(true);
		area.setOpaque(false);
		area.setWrapStyleWord(true);
		area.setPreferredSize(new Dimension(400, 500));
		area.setEditable(false);
		
		infoPanel.add(storyTitle);
		infoPanel.add(area);
		
		add(imgPanel);
		add(infoPanel);
		
		setUndecorated(true);
		setVisible(true);
		
		/*마우스 드래그 시 Frame 이동이 가능하고 Frame 클릭시 창이 닫힌다.*/
		addMouseMotionListener(listener.mouseAdapter);
		addMouseListener(listener.mouseAdapter);
	}
}
