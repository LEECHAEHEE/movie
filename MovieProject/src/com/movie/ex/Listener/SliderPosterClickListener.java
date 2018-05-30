package com.movie.ex.Listener;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.movie.ex.DTO.MovieDTO;
import com.movie.ex.DTO.SingletonDTO;
import com.movie.ex.JPanel.MovieInfoScreen;

public class PosterClickListener implements MouseListener{
	JFrame frame;
	ArrayList<MovieDTO> dtos =  SingletonDTO.getInstance().dtos;
	
	public PosterClickListener(JFrame frame) {this.frame = frame;}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int i = Integer.parseInt(e.getComponent().getName());
		new MovieInfoScreen(dtos.get(i).getMovieNo());
	}

	@Override
	public void mouseEntered(MouseEvent e) {frame.setCursor(Cursor.HAND_CURSOR);}

	@Override
	public void mouseExited(MouseEvent e) {frame.setCursor(Cursor.DEFAULT_CURSOR);}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
