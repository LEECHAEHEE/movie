package com.movie.ex.Listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.movie.ex.HomePanel.MoviePreview;

public class PreviewControlBtnListener implements MouseListener{
	MoviePreview moviePreview;
	JButton button;
	
	public PreviewControlBtnListener(MoviePreview moviePreview) {
		this.moviePreview = moviePreview;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent().getName().equals("right")) {
			if(moviePreview.Rtimer.isRunning()) {
				moviePreview.Rtimer.stop();
			}
			moviePreview.Ltimer.restart();
		}else if(e.getComponent().getName().equals("left")) {
			if(moviePreview.Ltimer.isRunning()) {
				moviePreview.Ltimer.stop();
			}
			moviePreview.Rtimer.restart();
		}else {
			System.out.println("ERROR");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		button = (JButton)e.getComponent();
		if(e.getComponent().getName().equals("left")) {
			button.setIcon(new ImageIcon("./images/selectedleftarw.png"));
		}else if(e.getComponent().getName().equals("right")) {
			button.setIcon(new ImageIcon("./images/selectedrightarw.png"));
		}else {
			System.out.println("ERROR");
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		button = (JButton)e.getComponent();
		if(e.getComponent().getName().equals("left")) {
			button.setIcon(new ImageIcon("./images/leftarw.png"));
		}else if(e.getComponent().getName().equals("right")) {
			button.setIcon(new ImageIcon("./images/rightarw.png"));
		}else {
			System.out.println("ERROR");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
