package com.movie.ex.Command;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.movie.ex.DTO.SingletonDTO;
import com.movie.ex.JPanel.MovieDTO;

public class MovieListener implements MouseListener{
	JFrame frame = new JFrame();
	
	public MovieListener(JFrame frame) {
		this.frame = frame;
	}
	
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
	
}
