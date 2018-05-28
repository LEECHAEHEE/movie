package com.movie.ex.Command;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

import com.movie.ex.DTO.SingletonDTO;
import com.movie.ex.JPanel.MovieDTO;
import com.movie.ex.JPanel.SlidePanel;

public class MovieListener implements MouseListener{
	JFrame frame;
	JButton button;
	SlidePanel slidePanel;
	boolean flag=true;
	
	public MovieListener() {
		// TODO Auto-generated constructor stub
	}
	
	public MovieListener(JFrame frame) {this.frame = frame;}
	public MovieListener(JButton button,SlidePanel slidePanel) {this.button = button; this.slidePanel=slidePanel;}
	
	ArrayList<MovieDTO> dtos =  SingletonDTO.getInstance().dtos;
	
	@Override
	public void mouseReleased(MouseEvent e) {}
	
	@Override
	public void mousePressed(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e) {
		frame.setCursor(Cursor.DEFAULT_CURSOR);
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		frame.setCursor(Cursor.HAND_CURSOR);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
//		System.out.println(e.getComponent());
		int i = Integer.parseInt(e.getComponent().getName());
		new ShowMovieInfo(dtos.get(i).getMovieNo());
	}
	
	public ActionListener loginBtnListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		}
	};
	public ActionListener logoutBtnListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		}
	};
	public ActionListener memberInfoBtnListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		}
	};
	public ActionListener controlBtnListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			ImageIcon play = new ImageIcon("./images/play.png"); 
			ImageIcon stop = new ImageIcon("./images/stop.png"); 
			
			if(flag) {
				button.setIcon(play);
				slidePanel.timer.stop();
			}else {
				button.setIcon(stop);
				slidePanel.timer.restart();
			}
			flag=!flag;
		}
	};
}
