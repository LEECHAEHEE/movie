package com.movie.ex.HomePanel;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdPanel extends JPanel{
	Image image;
	JLabel imgLabel = new JLabel();
	
	public AdPanel(Image image) {
		this.image = image;
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(this.image, 0, 0, this);
	}
}
