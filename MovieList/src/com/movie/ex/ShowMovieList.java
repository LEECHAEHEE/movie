package com.movie.ex;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class ShowMovieList extends JFrame{
	/*style*/
	final MatteBorder bottomBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black);
	final Font gradeFont = new Font("맑은 고딕", Font.PLAIN, 20);
	final Font reservationFont = new Font("맑은 고딕", Font.PLAIN, 15);
	final Color WhiteBackgroundColor = new Color(255, 255, 255);
	
	WebParsing parsing= new WebParsing();
	ArrayList<MovieDTO> dtos = parsing.showList();
	Image image = null;
	
	/*images print*/
	public ShowMovieList() {
		super("movie list");
		
		/*set position & size of jframe*/
		setBounds(1000,100,515,700);
		Container contentPane = this.getContentPane();
		setBackground(WhiteBackgroundColor);
		contentPane.setLayout(null);
		
		/*top panel has top ranked movie 1st~3rd*/
		JPanel topPanel = new JPanel();
		topPanel.setBounds(0,0,500,250);
		topPanel.setLayout(new GridLayout(1, 3));
		
		/*top panel has 3 panels each have 1st~3rd movie info*/
		JPanel[] topInnerPanel = new JPanel[3];
		
		/*topJpanel 객체 생성*/
		for(int i=0;i<3;i++) {
			topInnerPanel[i] = new JPanel();
			topInnerPanel[i].setBackground(WhiteBackgroundColor);
		}

		/*
		 *  영화 포스터가 삽입 되는 JLable 에는 setName을 통해 아이디가 부여됩니다.
		 * 각각의 아이디는 포스터를 클릭 했을 때 해당 영화에 대한 정보창을 띄울때 
		 * 사용하게 됩니다. 포스터를 클릭하면 SHowMoveInfo 객체를 생성하며 이때 
		 * 해당 레이블의 번호를 매개변수로 넘깁니다. 
		 */
		
		/*3위 이후 영화담는 패널 중 가장 큰 패널, 이후 scrollpane 지정한다.*/
		JPanel rankPanel= new JPanel();
		rankPanel.setLayout(new GridLayout(dtos.size()-3,1));
		
		/*1~100영화 정보 panel 삽입*/
		for(int i=0;i<dtos.size();i++) {

			try {
				image = ImageIO.read(dtos.get(i).getImgURL());
			} catch (IOException e) {e.printStackTrace();}
			
			JLabel label = new JLabel(new ImageIcon(image));
			label.setName(String.valueOf(i));
			label.addMouseListener(listener);
			
			/*top3 까지는 topPanel에 삽입하고 그 이후에는 영화 포스터는 imgPanel,
			 * 영화 정보는 infoPanel에 넣고 두 개의 JPanel을 다시 rPanel에 넣어서
			 * rankPanel에 삽입.
			 */
			if(i<3) 
			{
				/*순위 텍스트*/
				JLabel gradeLabel = new JLabel(i+1 + " 위", SwingConstants.CENTER);
				gradeLabel.setPreferredSize(new Dimension(100, 25));
				gradeLabel.setFont(gradeFont);
				
				/*제목*/
				JLabel titleLabel = new JLabel(dtos.get(i).getTitle(), SwingConstants.CENTER);
				titleLabel.setPreferredSize(new Dimension(200, 20));
				titleLabel.setFont(reservationFont);
				
				/*예매율*/
				JLabel reservationLabel = new JLabel("예매율 " + dtos.get(i).getReservationRate() + "%", SwingConstants.CENTER);
				reservationLabel.setPreferredSize(new Dimension(100, 20));
				reservationLabel.setFont(reservationFont);
				
				/*포스터 들어갈 JLabel*/
				topInnerPanel[i].add(gradeLabel);
				topInnerPanel[i].add(label);
				topInnerPanel[i].add(titleLabel);
				topInnerPanel[i].add(reservationLabel);
				
				topPanel.add(topInnerPanel[i]);
			}
			else
			{
				
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
				infoPanel.setBounds(130,0,360,150);
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
				rPanel.setPreferredSize(new Dimension(500, 150));
				
				/*scroll panel에 영화 패널을 담는다.*/
				rankPanel.add(rPanel);
			}
		}
		
		JScrollPane jScrollPane = new JScrollPane(rankPanel);
		jScrollPane.getVerticalScrollBar().setUnitIncrement(20);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane.setPreferredSize(new Dimension(500, 450));
		
		jScrollPane.setBounds(0,250,500,445);
		
		contentPane.add(topPanel);
		contentPane.add(jScrollPane);
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);	
	}


		
	/*포스터 클릭 시 이벤트 발생.*/
	MouseListener listener = new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {}
		
		@Override
		public void mousePressed(MouseEvent e) {}
		
		@Override
		public void mouseExited(MouseEvent e) {
			setCursor(Cursor.DEFAULT_CURSOR);
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			setCursor(Cursor.HAND_CURSOR);
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
//			System.out.println(e.getComponent());
			int i = Integer.parseInt(e.getComponent().getName());
			new ShowMovieInfo(dtos.get(i).getMovieNo());
		}
	};
	
//	public static void setUIFont(javax.swing.plaf.FontUIResource f) {
//		java.util.Enumeration keys = UIManager.getDefaults().keys();
//		while(keys.hasMoreElements()) {
//			Object key = keys.nextElement();
//			Object value = UIManager.get(key);
//			if(value instanceof javax.swing.plaf.FontUIResource)
//				UIManager.put(key, f);
//		}
//	}

}