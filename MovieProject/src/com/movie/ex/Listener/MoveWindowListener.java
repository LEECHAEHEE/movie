package com.movie.ex.Listener;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class MoveWindowListener {
	JFrame frame;
	Point mouseClickedLocation = new Point(0,0);
	
	public MoveWindowListener(JFrame frame) {
		this.frame = frame;
	}
	
	public MouseAdapter mouseAdapter = new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getComponent().getName().equals("JFrame"))
			{
				mouseClickedLocation.x = e.getX();
				mouseClickedLocation.y = e.getY();
			}
			else if(e.getComponent().getName().equals("JTextArea"))
			{
				mouseClickedLocation.x = e.getX()+300;
				mouseClickedLocation.y = e.getY()+70;
			}
		}
		@Override
		public void mouseClicked(MouseEvent e) {
//			System.out.println("e.getXOnScreen() : " +e.getXOnScreen()); 
//			System.out.println("e.getYOnScreen() : " +e.getYOnScreen());
//			System.out.println("mouseClickedLocation.x : " + mouseClickedLocation.x);
//			System.out.println("mouseClickedLocation.y : "+  mouseClickedLocation.y);
			frame.setVisible(false);
			frame.dispose();
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			frame.setLocation(e.getXOnScreen()-mouseClickedLocation.x,e.getYOnScreen()-mouseClickedLocation.y);
		};
	};
}
