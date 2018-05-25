package com.movie.ex;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
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
	
	/*슬라이딩을 위한 x 좌표, 움직이는 속도*/
	int x = 0;
	int delay = 20; //millseconds
	
	/*images print*/
	public ShowMovieList() {
		super("movie list");
		
		/*JFrame*/
		setBounds(300,100,1015,780);
		Container contentPane = this.getContentPane();
		setBackground(WhiteBackgroundColor);
		contentPane.setLayout(null);
		
		JPanel menuPanel = new MenuPanel();
		
		/*slidePanel : 1위부터 10위까지 슬라이딩 메뉴 JLable 넣을 Panel
		 * 한 JLable 에는 다섯개의 content JLable이 들어간다.
		 * */
		JPanel slidePanel = new JPanel();
		slidePanel.setLayout(null);
		slidePanel.setBounds(0,40,1000,250);
		
		/*rankPanel : 11위부터 영화담는 패널 중 가장 큰 패널, 이후 scrollpane 지정한다.*/
		JPanel rankPanel= new JPanel();
		rankPanel.setLayout(new GridLayout(dtos.size()-10,1));
		
		/*각각의 content JLabel, 5개씩 들어가므로 총 2개의 큰 JLable이 필요하다.*/
		/*1위부터 5위까지의 영화를 담은 JLable 을 담을 wrapLable*/
		JPanel wrapPanel1to5 = new JPanel();
		wrapPanel1to5.setPreferredSize(new Dimension(1000, 250));
		wrapPanel1to5.setBounds(x,0,1000,250);
		wrapPanel1to5.setLayout(new GridLayout(1, 5));
		
		/*6위부터 7위까지의 영화를 담은 JLable 을 담을 wrapLable*/
		JPanel wrapPanel6to10 = new JPanel();
		wrapPanel6to10.setPreferredSize(new Dimension(1000, 250));
		wrapPanel6to10.setBounds(x+1000,0,1000,250);
		wrapPanel6to10.setLayout(new GridLayout(1, 5));

		/*
		 *  영화 포스터가 삽입 되는 JLable 에는 setName을 통해 아이디가 부여됩니다.
		 * 각각의 아이디는 포스터를 클릭 했을 때 해당 영화에 대한 정보창을 띄울때 
		 * 사용하게 됩니다. 포스터를 클릭하면 SHowMoveInfo 객체를 생성하며 이때 
		 * 해당 레이블의 번호를 매개변수로 넘깁니다. 
		 */
		
		
		/*1~100영화 정보 삽입*/
		for(int i=0;i<dtos.size();i++) {
			Random random = new Random();
			int r = random.nextInt(255);
			int g = random.nextInt(255);
			int b = random.nextInt(255);
			
			try {
				image = ImageIO.read(dtos.get(i).getImgURL());
			} catch (IOException e) {e.printStackTrace();}
			
			/*영화 포스터를 담은 JLabel 객체 생성*/
			JLabel label = new JLabel(new ImageIcon(image));
			
			label.setName(String.valueOf(i));
			label.addMouseListener(listener);
			
			/*top10 까지는 slidePanel에 삽입하고 그 이후에는 영화 포스터는 imgPanel,
			 * 영화 정보는 infoPanel에 넣고 두 개의 JPanel을 다시 rPanel에 넣어서
			 * rankPanel에 삽입.
			 */
			if(i<10) 
			{
				JPanel topInnerPanel = new JPanel();
				topInnerPanel.setOpaque(true);
				topInnerPanel.setPreferredSize(new Dimension(200, 250));
				topInnerPanel.setBackground(new Color(r, g, b, 50));
				
				/*순위 텍스트*/
				JLabel gradeLabel = new JLabel(i+1 + " 위", SwingConstants.CENTER);
				gradeLabel.setPreferredSize(new Dimension(200, 25));
				gradeLabel.setFont(gradeFont);
				
				/*제목*/
				JLabel titleLabel = new JLabel(dtos.get(i).getTitle(), SwingConstants.CENTER);
				titleLabel.setPreferredSize(new Dimension(200, 20));
				titleLabel.setFont(reservationFont);
				
				/*예매율*/
				JLabel reservationLabel = new JLabel("예매율 " + dtos.get(i).getReservationRate() + "%", SwingConstants.CENTER);
				reservationLabel.setPreferredSize(new Dimension(200, 20));
				reservationLabel.setFont(reservationFont);
				
				topInnerPanel.add(label);
				topInnerPanel.add(gradeLabel);
				topInnerPanel.add(titleLabel);
				topInnerPanel.add(reservationLabel);
				
				if(i<5) {
					wrapPanel1to5.add(topInnerPanel);
				}else {
					wrapPanel6to10.add(topInnerPanel);
				}
			}
			else
			{
//				label.setPreferredSize(new Dimension(100, 150));
				
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
				infoPanel.setBounds(130,0,870,150);
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
				rPanel.setPreferredSize(new Dimension(1000, 150));
				
				/*scroll panel에 영화 패널을 담는다.*/
				rankPanel.add(rPanel);
			}
			slidePanel.add(wrapPanel1to5);
			slidePanel.add(wrapPanel6to10);
		}
		
		JScrollPane jScrollPane = new JScrollPane(rankPanel);
		jScrollPane.getVerticalScrollBar().setUnitIncrement(20);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane.setBounds(0,300,1000,420);
		
		
		contentPane.add(menuPanel);
		contentPane.add(slidePanel);
		contentPane.add(jScrollPane);
				
		
		ActionListener taskPerformer = new ActionListener() {
			int count = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				
				/*2초 term 두고 슬라이딩*/
				if(count!=0 && count%200==0)
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
				
				if(wrapPanel6to10.getLocation().x==-1000)
				{
					wrapPanel6to10.setLocation(1000,0);
				}
				else if(wrapPanel1to5.getLocation().x==-1000)
				{
					wrapPanel1to5.setLocation(1000,0);
				}	
				else 
				{
//					System.out.println(wrapLabel1to5.getLocation());
					wrapPanel1to5.setLocation(wrapPanel1to5.getLocation().x-1,0);
					wrapPanel6to10.setLocation(wrapPanel6to10.getLocation().x-1,0);
				}
				count++;
			}
		};
		new Timer(delay, taskPerformer).start();
		
		
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
}