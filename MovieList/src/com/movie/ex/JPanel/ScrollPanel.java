package com.movie.ex.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import com.movie.ex.Command.MovieListener;
import com.movie.ex.Command.ShowMovieList;
import com.movie.ex.DTO.SingletonDTO;

public class ScrollPanel extends JPanel{
	/*style*/
	final MatteBorder bottomBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black);
	final Color WhiteBackgroundColor = new Color(255, 255, 255);
	
	ArrayList<MovieDTO> dtos =  SingletonDTO.getInstance().dtos;
	Image image = null;
	
	
	public ScrollPanel(ShowMovieList frame) {
		setLayout(new GridLayout(dtos.size()-10,1));
		
		for(int i=10;i<dtos.size();i++) {
			
			try {
				image = ImageIO.read(dtos.get(i).getImgURL());
			} catch (IOException e) {e.printStackTrace();}
			
			/*영화 포스터를 담은 JLabel 객체 생성*/
			JLabel label = new JLabel(new ImageIcon(image));
			
			label.setName(String.valueOf(i));
			label.addMouseListener(new MovieListener(frame));
			
			/*rPanel : 각각의 영화 정보 담는 wrap Panel*/
			JPanel rPanel = new JPanel();
			rPanel.setLayout(null);
			
			/*imgPanel : 영화 포스터 담는 패널*/
			JPanel imgPanel = new JPanel();
			imgPanel.setBackground(WhiteBackgroundColor);
			imgPanel.add(label);
			imgPanel.setBorder(bottomBorder);
			imgPanel.setBounds(0,0,130,150);
			
			/*infoPanel : 영화 정보 담는 패널*/
			JPanel infoPanel = new JPanel();
			infoPanel.setLayout(new GridLayout(5, 1));
			infoPanel.setBorder(bottomBorder);
			infoPanel.setBounds(130,0,870,150);
			infoPanel.setBackground(WhiteBackgroundColor);
			
			ArrayList<JLabel> info = new ArrayList<>();
			info.add(new JLabel(dtos.get(i).getTitle()));
			info.add(new JLabel("네티즌 " + dtos.get(i).getRating() + "          예매율 " + dtos.get(i).getReservationRate() + "%"));
			info.add(new JLabel("개요 : " + dtos.get(i).getOutline()));
			info.add(new JLabel("감독 : " + dtos.get(i).getDirector()));
			info.add(new JLabel("출연 : " + "actor"));
			
			for(int j=0;j<info.size();j++) {
				if(j==0) info.get(j).setFont(new Font("맑은 고딕", Font.BOLD, 13));
				else info.get(j).setFont(new Font("맑은 고딕", Font.PLAIN, 13));
				infoPanel.add(info.get(j));
			}
			
			/*rPanel에 영화 포스터와 정보 삽입*/
			rPanel.add(imgPanel);
			rPanel.add(infoPanel);
			
			/*wrap Panel 크기 고정*/
			rPanel.setPreferredSize(new Dimension(1000, 150));
			
			/*scroll panel에 영화 패널을 담는다.*/
			add(rPanel);
			
			
		}
	}
}
