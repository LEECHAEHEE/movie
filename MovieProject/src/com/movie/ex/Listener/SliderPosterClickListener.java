package com.movie.ex.Listener;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.movie.ex.DTO.MovieDTO;
import com.movie.ex.DTO.SingletonDTO;
import com.movie.ex.HomePanel.MovieInfoScreen;
import com.movie.ex.HomePanel.MovieSlide;

public class SliderPosterClickListener implements MouseListener{
	JFrame frame;
	MovieSlide movieSlide;
	ArrayList<MovieDTO> dtos =  SingletonDTO.getInstance().dtos;
	public SliderPosterClickListener(JFrame frame) {
		this.frame = frame;
	}
	
	public SliderPosterClickListener(JFrame frame,MovieSlide movieSlide) {
		this.frame = frame;
		this.movieSlide = movieSlide;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		e.getComponent().getName();
		int i = Integer.parseInt(e.getComponent().getName());
		new MovieInfoScreen(dtos.get(i).getMovieNo());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		frame.setCursor(Cursor.HAND_CURSOR);
		movieSlide.timer.stop();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		frame.setCursor(Cursor.DEFAULT_CURSOR);
		movieSlide.timer.restart();
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
