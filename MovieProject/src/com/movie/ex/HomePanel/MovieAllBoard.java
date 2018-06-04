package com.movie.ex.HomePanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.MatteBorder;

import com.movie.ex.DTO.MovieDTO;

public class MovieAllBoard extends JFrame {
	/*슬라이딩 변수*/
	final int x = 20;									//슬라이딩 위한 x축 변수
	final int delay = 1;								//슬라이딩 소요 시간
	Timer RtoLTimer,LtoRTimer;							//타이머 객체 
	
	public MovieAllBoard() {
		setBounds((Toolkit.getDefaultToolkit().getScreenSize().width-800)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-600)/2, 800, 600);
		setTitle("Board");
		setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		setBackground(Color.white);
		getContentPane().setLayout(null);
		
		
		/*MovieDTO*/
		ArrayList<MovieDTO> dtos = new ArrayList<>();
		
		/*전체 패널 감싸는 패널*/
		JPanel wrapPanel = new JPanel();
		wrapPanel.setBounds(x,20,740,520);
		wrapPanel.setLayout(new BorderLayout());
		wrapPanel.setBackground(Color.black);
		
		/*게시판 타이틀*/
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(null);
		titlePanel.setPreferredSize(new Dimension(740, 40));
		titlePanel.setBorder(new MatteBorder(0, 0, 3, 0, Color.lightGray));
		
		/*"게시판"*/
		JLabel titleLabel = new JLabel("자유 게시판");
		titleLabel.setBounds(20,0,140,25);
		titleLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		titlePanel.add(titleLabel);
		
		/*내가 쓴 글 보기*/
		JLabel myWriting = new JLabel("내가 쓴 글 보기");
		myWriting.setBounds(140,11,150,15);
		myWriting.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		titlePanel.add(myWriting);
		
		/*내가 쓴 글 보기 리스너 등록*/
		//TODO register the listener into myWriting
		
		/*글쓰기 버튼*/
		JButton writeBtn = new JButton("글쓰기");
		writeBtn.setBounds(650,0,80,30);
		writeBtn.setContentAreaFilled(false);
		titlePanel.add(writeBtn);
		
		/*글쓰기 버튼 리스너*/
		writeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RtoLTimer.start();
			}
		});
		
		/*게시판 내용 한 페이지에 10개 글*/
		JPanel contentPanel = new JPanel();
		contentPanel.setPreferredSize(new Dimension(740, 440));
		contentPanel.setLayout(new GridLayout(11, 1));
		
		/*10개 글 등록*/
		for(int i=0;i<11;i++) {
			JPanel writingPanel = new JPanel();
			writingPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
			writingPanel.setPreferredSize(new Dimension(740, 40));
			if(i<10) writingPanel.setBorder(new MatteBorder(0,0,1,0,Color.LIGHT_GRAY));
			
			/*최상단 구분 레이블*/
			//TODO change with for phrase
			
			if(i==0)
			{
			JLabel noTab = new JLabel("글 번호");
			noTab.setPreferredSize(new Dimension(80, 40));
			noTab.setOpaque(true);
			noTab.setHorizontalAlignment(JLabel.CENTER);
			noTab.setVerticalAlignment(JLabel.CENTER);
			noTab.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			noTab.setBorder(new MatteBorder(0,0,1,1,Color.lightGray));
			
			JLabel titleTab = new JLabel("글 제목");
			titleTab.setPreferredSize(new Dimension(460, 40));
			titleTab.setOpaque(true);
			titleTab.setHorizontalAlignment(JLabel.CENTER);
			titleTab.setVerticalAlignment(JLabel.CENTER);
			titleTab.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			titleTab.setBorder(new MatteBorder(0,0,1,1,Color.lightGray));
			
			JLabel writerTab = new JLabel("글쓴이");
			writerTab.setPreferredSize(new Dimension(100, 40));
			writerTab.setOpaque(true);
			writerTab.setHorizontalAlignment(JLabel.CENTER);
			writerTab.setVerticalAlignment(JLabel.CENTER);
			writerTab.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			writerTab.setBorder(new MatteBorder(0,0,1,1,Color.lightGray));

			JLabel dateTab = new JLabel("작성일");
			dateTab.setPreferredSize(new Dimension(100, 40));
			dateTab.setOpaque(true);
			dateTab.setHorizontalAlignment(JLabel.CENTER);
			dateTab.setVerticalAlignment(JLabel.CENTER);
			dateTab.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			dateTab.setBorder(new MatteBorder(0,0,1,0,Color.lightGray));
			
			writingPanel.add(noTab);
			writingPanel.add(titleTab);
			writingPanel.add(writerTab);
			writingPanel.add(dateTab);
			}
			
			/*각각 내용*/
			JLabel no = new JLabel();
			no.setPreferredSize(new Dimension(80, 40));
			no.setBackground(Color.red);
			no.setOpaque(true);
			no.setHorizontalAlignment(JLabel.CENTER);
			no.setVerticalAlignment(JLabel.CENTER);
			no.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			no.setBorder(new MatteBorder(0,0,1,0,Color.lightGray));
			
			JLabel title = new JLabel();
			title.setPreferredSize(new Dimension(460, 40));
			title.setBackground(Color.blue);
			title.setOpaque(true);
			title.setHorizontalAlignment(JLabel.CENTER);
			title.setVerticalAlignment(JLabel.CENTER);
			title.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			title.setBorder(new MatteBorder(0,0,1,0,Color.lightGray));
			
			JLabel writer = new JLabel();
			writer.setPreferredSize(new Dimension(100, 40));
			writer.setBackground(Color.green);
			writer.setOpaque(true);
			writer.setHorizontalAlignment(JLabel.CENTER);
			writer.setVerticalAlignment(JLabel.CENTER);
			writer.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			writer.setBorder(new MatteBorder(0,0,1,0,Color.lightGray));
			
			JLabel date = new JLabel();
			date.setPreferredSize(new Dimension(100,40));
			date.setBackground(Color.magenta);
			date.setOpaque(true);
			date.setHorizontalAlignment(JLabel.CENTER);
			date.setVerticalAlignment(JLabel.CENTER);
			date.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			date.setBorder(new MatteBorder(0,0,1,0,Color.lightGray));
			
			writingPanel.add(no);
			writingPanel.add(title);
			writingPanel.add(writer);
			writingPanel.add(date);
			
			contentPanel.add(writingPanel);
		}//for(int i=0;i<11;i++) {
		
		/*페이징 패널*/
		JPanel pagingPanel = new JPanel();
		pagingPanel.setPreferredSize(new Dimension(740, 40));
		pagingPanel.setBorder(new MatteBorder(3,0,0,0,Color.lightGray));
	
		/*처음, 이전, 다음, 마지막 버튼, 3가지 속성(배경색 지움,테두리 지움, 클릭시 확인 박스 미적용)부여*/
		JButton firstBtn = new JButton(new ImageIcon("./images/first.png"));
		firstBtn.setContentAreaFilled(false);
		firstBtn.setBorderPainted(false);
		firstBtn.setFocusPainted(false);
		
		JButton prevBtn = new JButton(new ImageIcon("./images/prev.png"));
		prevBtn.setContentAreaFilled(false);
		prevBtn.setBorderPainted(false);
		prevBtn.setFocusPainted(false);
		
		JButton nextBtn = new JButton(new ImageIcon("./images/next.png"));
		nextBtn.setContentAreaFilled(false);
		nextBtn.setBorderPainted(false);
		nextBtn.setFocusPainted(false);
		
		JButton lastBtn = new JButton(new ImageIcon("./images/last.png"));
		lastBtn.setContentAreaFilled(false);
		lastBtn.setBorderPainted(false);
		lastBtn.setFocusPainted(false);
		
		pagingPanel.add(firstBtn);
		pagingPanel.add(prevBtn);
		
		/*페이지 번호 넣는 반복문*/
		for(int i=0;i<10;i++) {
			JButton pageBtn = new JButton(String.valueOf(1+i));
			pageBtn.setContentAreaFilled(false);
			pageBtn.setBorderPainted(false);
			pagingPanel.add(pageBtn);
		}//	for(int i=0;i<10;i++) {
		
		pagingPanel.add(nextBtn);
		pagingPanel.add(lastBtn);
		
		/*감싸는 패널에 넣기*/
		wrapPanel.add(titlePanel,BorderLayout.NORTH);
		wrapPanel.add(contentPanel,BorderLayout.CENTER);
		wrapPanel.add(pagingPanel,BorderLayout.SOUTH);
		
		JPanel writePanel = new WriteBoard(this);
	
		/*감싸는 패널 프레임에 넣기*/
		/*그리고 슬라이딩 패널 넣기*/
		add(wrapPanel);
		add(writePanel);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		/*패널 이동하기 위한 액션 리스너*/
		ActionListener RtoLTaskPerformed = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wrapPanel.setLocation(wrapPanel.getLocation().x-40, 20);
				writePanel.setLocation(writePanel.getLocation().x-40, 0);
				
				if(wrapPanel.getLocation().x==-780) {
					RtoLTimer.stop();
				}
				
			}
		};
		
		ActionListener LtoRTaskPerformed = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wrapPanel.setLocation(wrapPanel.getLocation().x+40, 20);
				writePanel.setLocation(writePanel.getLocation().x+40, 0);
				
				if(wrapPanel.getLocation().x==20) LtoRTimer.stop();
			}
		};
		RtoLTimer = new Timer(delay, RtoLTaskPerformed);
		LtoRTimer = new Timer(delay, LtoRTaskPerformed);
	}//public MovieAllBoard() {
	
}
