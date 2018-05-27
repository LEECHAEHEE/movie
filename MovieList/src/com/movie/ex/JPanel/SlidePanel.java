package com.movie.ex.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import com.movie.ex.Command.MovieListener;
import com.movie.ex.Command.ShowMovieList;
import com.movie.ex.DTO.SingletonDTO;

public class SlidePanel extends JPanel{
	/*슬라이딩을 위한 x 좌표, 움직이는 속도*/
	int x = 0;
	int delay = 20; //millseconds
	final Font gradeFont = new Font("맑은 고딕", Font.PLAIN, 20);
	final Font reservationFont = new Font("맑은 고딕", Font.PLAIN, 15);
	public Timer slide;
	
	ArrayList<MovieDTO> dtos =  SingletonDTO.getInstance().dtos;
	
	Image image = null;
	public SlidePanel() { }
	
	public SlidePanel(ShowMovieList frame) {
		setLayout(null);
		setBounds(0,40,1000,250);
		
		/*각각의 content JLabel, 5개씩 들어가므로 총 2개의 큰 JLable이 필요하다.*/
		/*1위부터 5위까지의 영화를 담은 JLable 을 담을 wrapLable*/
		JPanel wrapPanel1to5 = new JPanel();
		wrapPanel1to5.setPreferredSize(new Dimension(1000, 250));
		wrapPanel1to5.setBounds(x,0,1000,250);
		wrapPanel1to5.setLayout(new GridLayout(1, 5));
		
		/*6위부터 7위까지의 영화를 담은 JLable 을 담을 wrapLable*/
		JPanel wrapPanel6to10 = new JPanel();
		wrapPanel6to10.setPreferredSize(new Dimension(1000, 250));
		wrapPanel6to10.setBounds(x+1000,0,1000,250);
		wrapPanel6to10.setLayout(new GridLayout(1, 5));
		
		for(int i=0;i<10;i++) {
			Random random = new Random();
			int r = random.nextInt(255);
			int g = random.nextInt(255);
			int b = random.nextInt(255);
			
			try {
				image = ImageIO.read(dtos.get(i).getImgURL());
			} catch (IOException e) {e.printStackTrace();}
			
			/*영화 포스터를 담은 JLabel 객체 생성*/
			JLabel label = new JLabel(new ImageIcon(image));
			
			label.setName(String.valueOf(i));
			label.addMouseListener(new MovieListener(frame));
			
			JPanel topInnerPanel = new JPanel();
			topInnerPanel.setOpaque(true);
			topInnerPanel.setPreferredSize(new Dimension(200, 250));
			topInnerPanel.setBackground(new Color(r, g, b, 50));
			
			/*순위 텍스트*/
			JLabel gradeLabel = new JLabel(i+1 + " 위", SwingConstants.CENTER);
			gradeLabel.setPreferredSize(new Dimension(200, 25));
			gradeLabel.setFont(gradeFont);
			
			/*제목*/
			JLabel titleLabel = new JLabel(dtos.get(i).getTitle(), SwingConstants.CENTER);
			titleLabel.setPreferredSize(new Dimension(200, 20));
			titleLabel.setFont(reservationFont);
			
			/*예매율*/
			JLabel reservationLabel = new JLabel("예매율 " + dtos.get(i).getReservationRate() + "%", SwingConstants.CENTER);
			reservationLabel.setPreferredSize(new Dimension(200, 20));
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
		
		add(wrapPanel1to5);
		add(wrapPanel6to10);
		
		ActionListener taskPerformer = new ActionListener() {
			int count = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				
//				/*2초 term 두고 슬라이딩*/
//				if(count!=0 && count%200==0)
//				{
//					try 
//					{
//						
//					}
//					catch(InterruptedException e2)
//					{
//						e2.printStackTrace();
//					}
//				}
				
				if(wrapPanel6to10.getLocation().x==-1000)
				{
					wrapPanel6to10.setLocation(1000,0);
				}
				else if(wrapPanel1to5.getLocation().x==-1000)
				{
					wrapPanel1to5.setLocation(1000,0);
				}	
				else 
				{
//					System.out.println(wrapLabel1to5.getLocation());
					wrapPanel1to5.setLocation(wrapPanel1to5.getLocation().x-1,0);
					wrapPanel6to10.setLocation(wrapPanel6to10.getLocation().x-1,0);
				}
				count++;
			}
		};//ActionListener taskPerformer = new ActionListener() {
		
		slide = new Timer(delay, taskPerformer);
		slide.start();
		
	}//public SlidePanel(ShowMovieList frame) {
}
