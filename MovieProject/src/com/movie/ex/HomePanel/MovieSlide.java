package com.movie.ex.HomePanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import com.movie.ex.DTO.MovieDTO;
import com.movie.ex.DTO.SingletonDTO;
import com.movie.ex.Listener.SliderPosterClickListener;
import com.movie.ex.Parsing.WebParsing;

/*
 * Top 10 영화 슬라이더 
 */
public class MovieSlide extends JPanel {
	WebParsing parsing = new WebParsing();
	/*초기값*/
	final int x = 0;		//슬라이딩 위한 x축 변수
	final int delay = 20;	//슬라이딩 속도 millisecond
	final Font gradeFont = new Font("맑은 고딕", Font.PLAIN, 20);
	
	final Font reservationFont = new Font("맑은 고딕", Font.PLAIN, 15);
	public Timer timer;
	
	/*MovieDTO 객체 얻어온다.*/
	ArrayList<MovieDTO> dtos =  SingletonDTO.getInstance().dtos;
	
	Image image;
	
	public MovieSlide(JFrame frame) {
		setLayout(null);
		setPreferredSize(new Dimension(1100, 450));
		
		/*각각의 content JLabel, 5개씩 들어가므로 총 2개의 큰 JLable이 필요하다.*/
		/*1위부터 5위까지의 영화를 담은 JLable 을 담을 wrapLable*/
		JPanel wrapPanel1to5 = new JPanel();
		wrapPanel1to5.setBounds(x,0,1100,405);
		wrapPanel1to5.setLayout(new GridLayout(1, 5));
		
		/*6위부터 7위까지의 영화를 담은 JLable 을 담을 wrapLable*/
		JPanel wrapPanel6to10 = new JPanel();
		wrapPanel6to10.setBounds(x+1100,0,1100,405);
		wrapPanel6to10.setLayout(new GridLayout(1, 5));
		
		for(int i=0;i<10;i++) {
//			Random random = new Random();
//			int r = random.nextInt(255);
//			int g = random.nextInt(255);
//			int b = random.nextInt(255);
			
			try {
				image = ImageIO.read(dtos.get(i).getImgURL());
			} catch (IOException e) {e.printStackTrace();}
			
			/*영역에 맞게 포스터 편집*/
			ImageIcon imageIcon = new ImageIcon(parsing.getMovieInfo(dtos.get(i).getMovieNo()).getImgURL());
			Image image =imageIcon.getImage();
			Image newImg = image.getScaledInstance(200, 300, Image.SCALE_SMOOTH);
			
			/*영화 포스터를 담은 JLabel 객체 생성*/
			JLabel label = new JLabel(new ImageIcon(image));
			
			/*paading 값 부여*/
			label.setBorder(new EmptyBorder(20, 0, 0, 0));
			
			/*각 포스터 구분 위한 이름 지정*/
			label.setName(String.valueOf(i));
			
			/*각 포스터 담은 JLabel에 listener 등록*/
			label.addMouseListener(new SliderPosterClickListener(frame,this));
			
			JPanel topInnerPanel = new JPanel();
			topInnerPanel.setOpaque(true);
			topInnerPanel.setPreferredSize(new Dimension(220, 420));
			topInnerPanel.setBackground(new Color(41,41,41));
			
			/*순위 텍스트*/
			JLabel gradeLabel = new JLabel(i+1 + " 위", SwingConstants.CENTER);
			gradeLabel.setPreferredSize(new Dimension(220, 25));
			gradeLabel.setForeground(Color.white);
			gradeLabel.setFont(gradeFont);
			
			/*제목*/
			JLabel titleLabel = new JLabel(dtos.get(i).getTitle(), SwingConstants.CENTER);
			titleLabel.setPreferredSize(new Dimension(220, 20));
			titleLabel.setForeground(Color.white);
			titleLabel.setFont(reservationFont);
			
			/*예매율*/
			JLabel reservationLabel = new JLabel("예매율 " + dtos.get(i).getReservationRate() + "%", SwingConstants.CENTER);
			reservationLabel.setPreferredSize(new Dimension(220, 20));
			reservationLabel.setForeground(Color.white);
			reservationLabel.setFont(reservationFont);
			
			topInnerPanel.add(label);
			topInnerPanel.add(gradeLabel);
			topInnerPanel.add(titleLabel);
			topInnerPanel.add(reservationLabel);
			
			if(i<5) {
				wrapPanel1to5.add(topInnerPanel);
			}else {
				wrapPanel6to10.add(topInnerPanel);
			}
		}//	for(int i=0;i<10;i++) {
		
		/*슬라이더 메뉴 컨트롤 패널추가*/
		MovieSlideControl controlPanel = new MovieSlideControl(this);
		
		add(wrapPanel1to5);
		add(wrapPanel6to10);
		add(controlPanel);
		
		/*Timer이용한 영화 슬라이딩 구현*/
		ActionListener taskPerformer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if(wrapPanel6to10.getLocation().x==-1100){
					wrapPanel6to10.setLocation(1100,0);
				}
				else if(wrapPanel1to5.getLocation().x==-1100){
					wrapPanel1to5.setLocation(1100,0);
				}	
				else{
					wrapPanel1to5.setLocation(wrapPanel1to5.getLocation().x-1,0);
					wrapPanel6to10.setLocation(wrapPanel6to10.getLocation().x-1,0);
				}
			}
		};//ActionListener taskPerformer = new ActionListener() {
		
		timer = new Timer(delay, taskPerformer);
		timer.start();
	}
}
