package com.movie.ex.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/*
 * 홈 width:1500 height:1000
 */
public class HomeScreen extends JFrame{
	public HomeScreen() {
		super("영화 프로젝트");
		
		/*초기 홈 화면 설정*/
//		HomeScreenInit homeScreen = new HomeScreenInit(this);
		setBackground(Color.WHITE);
		
		JPanel homeScreenPanel = new JPanel();
		GridBagLayout gridbag = new GridBagLayout();
		homeScreenPanel.setLayout(gridbag);
		
		GridBagConstraints wrapConstraint = new GridBagConstraints();
		wrapConstraint.fill=GridBagConstraints.BOTH;				//panel을 세로,가로축으로 다 채운다
		wrapConstraint.weightx=1.0;									//x
		wrapConstraint.weighty=1.0;									//
		wrapConstraint.gridwidth= GridBagConstraints.REMAINDER;
		wrapConstraint.gridheight = 1;
		
		/*topMenu*/
		JPanel topWrapPanel = new JPanel();
		topWrapPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		topWrapPanel.setBackground(new Color(255, 102, 102));
		topWrapPanel.setPreferredSize(new Dimension(getWidth(),100));
		gridbag.setConstraints(topWrapPanel, wrapConstraint);
		
		
		JPanel topInnerPanel = new TopMenu(this);
		topWrapPanel.add(topInnerPanel);
		
		/*Slider*/
		JPanel sliderWrapPanel = new JPanel();
		sliderWrapPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		sliderWrapPanel.setBackground(new Color(255, 140, 102));
		sliderWrapPanel.setPreferredSize(new Dimension(getWidth(),450));
		gridbag.setConstraints(sliderWrapPanel, wrapConstraint);
		
		JPanel sliderInnerPanel = new MovieSlide(this);
		sliderWrapPanel.add(sliderInnerPanel);
		
		/*preView*/
		JPanel preViewWrapPanel = new JPanel();
		preViewWrapPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		preViewWrapPanel.setBackground(new Color(255, 217, 102));
		preViewWrapPanel.setPreferredSize(new Dimension(getWidth(), 300));
		gridbag.setConstraints(preViewWrapPanel, wrapConstraint);
		
		JPanel preViewInnerPanel = new MoviePreview(this);
		/*버튼을 통해 preVIewInnerPannel을 컨트롤 할 수 있게 객체를 전달해 줘야 함.*/
		JPanel leftArw = new MoviePreviewControl("left",(MoviePreview) preViewInnerPanel);
		JPanel rightArw = new MoviePreviewControl("right",(MoviePreview) preViewInnerPanel);
		
		preViewWrapPanel.add(leftArw);
		preViewWrapPanel.add(preViewInnerPanel);
		preViewWrapPanel.add(rightArw);
		
		/*content*/
		JPanel contentWrapPanel = new JPanel();
		contentWrapPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		contentWrapPanel.setBackground(new Color(255, 179, 102));
		contentWrapPanel.setPreferredSize(new Dimension(1100, 500));
		gridbag.setConstraints(contentWrapPanel, wrapConstraint);
		
		JPanel contentInnerPanel = new JPanel();
		contentInnerPanel.setBackground(new Color(115, 92, 138,20));
		contentInnerPanel.setPreferredSize(new Dimension(1100,500));
		contentWrapPanel.add(contentInnerPanel);
		
		homeScreenPanel.add(topWrapPanel);
		homeScreenPanel.add(sliderWrapPanel);
		homeScreenPanel.add(preViewWrapPanel);
		homeScreenPanel.add(contentWrapPanel);

		JScrollPane scrollPane = new JScrollPane(homeScreenPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);							//스크롤 속도
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);		//수직 스크롤 항상 존재
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);	//수평 스크롤은 없음
		add(scrollPane);
		setSize(1500,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
