package com.javalec.ex;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Animation extends JFrame{
	static int x=0;
	static int y=0;
	int delay = 20; //millseconds
	
	public Animation() {
		setSize(515,500);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0,0,500,100);
//		panel.setLayout(new GridLayout(1,5));
		
		/*******************************************************************
		 * 이후 wrapLabel에 스레드 걸어줘야 합니다. 
		 ******************************************************************/
		JLabel wrapLabel1to5 = new JLabel();
		wrapLabel1to5.setPreferredSize(new Dimension(500, 100));
		wrapLabel1to5.setBounds(x,0,500,100);
		wrapLabel1to5.setLayout(new GridLayout(1,5));
		
		JLabel wrapLabel6to10 = new JLabel();
		wrapLabel6to10.setPreferredSize(new Dimension(500, 100));
		wrapLabel6to10.setBounds(x+500,0,500,100);
		wrapLabel6to10.setLayout(new GridLayout(1,5));
		
		JLabel [] labels = new JLabel[10];
		for(int i=0;i<labels.length;i++) {
			Random random = new Random();
			int r = random.nextInt(255);
			int g = random.nextInt(255);
			int b = random.nextInt(255);
			
			labels[i] = new JLabel("jlabel" + (i+1), SwingConstants.CENTER);
			labels[i].setOpaque(true);
			labels[i].setPreferredSize(new Dimension(100, 100));
			labels[i].setBackground(new Color(r, g, b, 50));
			
			if(i<5)
			{
				wrapLabel1to5.add(labels[i]);
			}
			else
			{
				wrapLabel6to10.add(labels[i]);
			}	
		}
		panel.add(wrapLabel1to5);
		panel.add(wrapLabel6to10);
		add(panel);
	
		ActionListener taskPerformer = new ActionListener() {
			int count = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				
				/*2초 term 두고 슬라이딩*/
				if(count!=0 && count%100==0)
				{
					try 
					{
						Thread.sleep(2000);
					}
					catch(InterruptedException e2)
					{
						e2.printStackTrace();
					}
				}
				
				if(wrapLabel6to10.getLocation().x==-500)
				{
					wrapLabel6to10.setLocation(500,0);
				}
				else if(wrapLabel1to5.getLocation().x==-500)
				{
					wrapLabel1to5.setLocation(500,0);
				}	
				else 
				{
//					System.out.println(wrapLabel1to5.getLocation());
					wrapLabel1to5.setLocation(wrapLabel1to5.getLocation().x-1,0);
					wrapLabel6to10.setLocation(wrapLabel6to10.getLocation().x-1,0);
				}
				count++;
			}
		};
		new Timer(delay, taskPerformer).start();
		
		
		JButton btn = new JButton(new ImageIcon("C:\\git\\movie\\MovieList\\images\\play.png"));
		add(btn);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		Animation ani = new Animation();
	}

}
