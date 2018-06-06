package com.movie.ex.HomePanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.MatteBorder;

import com.movie.ex.DAO.BoardDAO;
import com.movie.ex.DTO.BoardDTO;
import com.movie.ex.DTO.PagingDTO;

public class MovieAllBoard extends JFrame {
	/*슬라이딩 변수*/
	final int x = 20, y=20;
	//슬라이딩 위한 x축 변수
	final int delay = 1;								//슬라이딩 소요 시간
	Timer RtoLTimer,LtoRTimer,TtoBTimer,BtoTTimer;		//타이머 객체 
	
	/*boardDTO 객체 불러오기*/
	ArrayList<BoardDTO> dtos = new ArrayList<>();
	BoardDAO dao = new BoardDAO();
	PagingDTO Pdto;
	
	JPanel contentPanel;		//게시글이 들어가는 패널
	JPanel infoPanel;			//게시글 클릭하면 보이는 글 내용 패널
	JButton firstBtn;
	JButton prevBtn;
	JButton nextBtn;
	JButton lastBtn;
	
	public MovieAllBoard(MovieBoard movieBoard) {
		setBounds((Toolkit.getDefaultToolkit().getScreenSize().width-800)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-600)/2, 800, 600);
		setTitle("Board");
		setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		setBackground(Color.white);
		getContentPane().setLayout(null);
		
		/*전체 패널 감싸는 패널*/
		JPanel wrapPanel = new JPanel();
		wrapPanel.setBounds(x,y,740,520);
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
		
		/*contentPanel : 게시판 내용, 한 페이지에 10개 글*/
		contentPanel = new JPanel();
		contentPanel.setPreferredSize(new Dimension(740, 440));
		contentPanel.setLayout(new GridLayout(11, 1));
		
		/*dto객체 리스트에 board 객체 삽입.*/
		dtos = dao.getBoardList();
		
		/*pagingDTO 객체 생성 - 초기값으로는 1페이지 가리키도록 한다
		 * 최초 한번만 생성되고 해당 객체 계속 사용한다. 즉, new 통해서
		 * 새로운 객체 생성하면 안됨 cur_Page 저장하고 있어야 하니까.*/
		Pdto = new PagingDTO(dtos.size(), 1);
		
		/*맨 처음 보는 페이지(1페이지)*/
		/*최상단 구분 레이블 글번호 | 글제목 | 글쓴이 | 작성일 */
		inputTopPanel(contentPanel);
		
		/*******************
		 * 게시글 넣는 부분
		 *******************/
		insertBoardElement(contentPanel,Pdto);
		
		/*페이징 패널*/
		JPanel pagingPanel = new JPanel();
		pagingPanel.setPreferredSize(new Dimension(740, 40));
		pagingPanel.setBorder(new MatteBorder(3,0,0,0,Color.lightGray));
	
		/*처음, 이전, 다음, 마지막 버튼, 3가지 속성(배경색 지움,테두리 지움, 클릭시 확인 박스 미적용)부여*/
		firstBtn = new JButton(new ImageIcon("./images/first.png"));
		firstBtn.setContentAreaFilled(false);
		firstBtn.setBorderPainted(false);
		firstBtn.setFocusPainted(false);
		firstBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pagingPanel.removeAll();
				Pdto.setCur_Page(1);
				insertPageNum(contentPanel, pagingPanel, Pdto);
				pagingPanel.repaint();
				pagingPanel.revalidate();
			}
		});
		
		prevBtn = new JButton(new ImageIcon("./images/prev.png"));
		prevBtn.setContentAreaFilled(false);
		prevBtn.setBorderPainted(false);
		prevBtn.setFocusPainted(false);
		/*이전페이지 버튼 리스너*/
		prevBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pagingPanel.removeAll();
				Pdto.setCur_Page(Pdto.getPage_Start()-1);
				insertPageNum(contentPanel, pagingPanel, Pdto);
				pagingPanel.repaint();
				pagingPanel.revalidate();
			}
		});
		
		nextBtn = new JButton(new ImageIcon("./images/next.png"));
		nextBtn.setContentAreaFilled(false);
		nextBtn.setBorderPainted(false);
		nextBtn.setFocusPainted(false);
		/*다음페이지 버튼 리스너*/
		nextBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pagingPanel.removeAll();
				Pdto.setCur_Page(Pdto.getPage_End()+1);
				insertPageNum(contentPanel, pagingPanel, Pdto);
				pagingPanel.repaint();
				pagingPanel.revalidate();
			}
		});
		
		lastBtn = new JButton(new ImageIcon("./images/last.png"));
		lastBtn.setContentAreaFilled(false);
		lastBtn.setBorderPainted(false);
		lastBtn.setFocusPainted(false);
		lastBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pagingPanel.removeAll();
				Pdto.setCur_Page(Pdto.getPage_Count());
				insertPageNum(contentPanel, pagingPanel, Pdto);
				pagingPanel.repaint();
				pagingPanel.revalidate();
			}
		});
		
		/*페이지 번호 버튼 넣는 반복문 - 초기값은 1페이지 띄워줌*/
		insertPageNum(contentPanel, pagingPanel, Pdto);
		
		/*감싸는 패널에 넣기*/
		wrapPanel.add(titlePanel,BorderLayout.NORTH);
		wrapPanel.add(contentPanel,BorderLayout.CENTER);
		wrapPanel.add(pagingPanel,BorderLayout.SOUTH);
		
		/**********************
		 * 글 쓰는 페이지 패널
		 **********************/
		JPanel writePanel = new BoardWrite(this);
	
		/**********************
		 * 글 내용 페이지 패널
		 **********************/
		infoPanel = new BoardContent(TtoBTimer,-1);
		
		/*감싸는 패널 프레임에 넣기*/
		/*그리고 슬라이딩 패널 넣기*/
		add(wrapPanel);
		add(writePanel);
		add(infoPanel);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		/*패널 이동하기 위한 액션 리스너*/
		ActionListener rTaskPerformed = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wrapPanel.setLocation(wrapPanel.getLocation().x-40, 20);
				writePanel.setLocation(writePanel.getLocation().x-40, 0);
				infoPanel.setLocation(infoPanel.getLocation().x-40,600);
				if(wrapPanel.getLocation().x==-780) {
					RtoLTimer.stop();
				}
			}
		};
		
		ActionListener lTaskPerformed = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wrapPanel.setLocation(wrapPanel.getLocation().x+40, 20);
				writePanel.setLocation(writePanel.getLocation().x+40, 0);
				infoPanel.setLocation(infoPanel.getLocation().x+40,600);
				if(wrapPanel.getLocation().x==20) LtoRTimer.stop();
			}
		};
		
		ActionListener bTaskPerformed = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wrapPanel.setLocation(20, wrapPanel.getLocation().y-40);
				writePanel.setLocation(800, writePanel.getLocation().y-40);
				infoPanel.setLocation(0, infoPanel.getLocation().y-40);
				if(wrapPanel.getLocation().y==-580) BtoTTimer.stop();
			}
		};
		ActionListener tTaskPerformed = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wrapPanel.setLocation(20, wrapPanel.getLocation().y+40);
				writePanel.setLocation(800, writePanel.getLocation().y+40);
				infoPanel.setLocation(0, infoPanel.getLocation().y+40);
				if(wrapPanel.getLocation().y==20) TtoBTimer.stop();
			}
		};
		
		RtoLTimer = new Timer(delay, rTaskPerformed);
		LtoRTimer = new Timer(delay, lTaskPerformed);
		TtoBTimer = new Timer(delay, tTaskPerformed);
		BtoTTimer = new Timer(delay, bTaskPerformed);

		/*게시판 창이 꺼질 때 홈화면의 top10개 게시글 갱신*/
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				movieBoard.insertUpperTen();
			}
		});
		
	}//public MovieAllBoard() {
	
	/*페이지 번호 넣기*/
	public void insertPageNum(JPanel contentPanel, JPanel pagingPanel, PagingDTO Pdto) {
		/*페이징 패널에 버튼 추가*/
		if(Pdto.isPre()) {
			pagingPanel.add(firstBtn);
			pagingPanel.add(prevBtn);
		}
		for(int i=Pdto.getPage_Start();i<=Pdto.getPage_End();i++) {
			JButton pageBtn = new JButton(String.valueOf(i));
			pageBtn.setContentAreaFilled(false);
			pageBtn.setBorderPainted(false);
			pagingPanel.add(pageBtn);
			
			pageBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
//					System.out.println(pageBtn.getText()); //각 숫자버튼 구분용.
					contentPanel.removeAll();
					inputTopPanel(contentPanel);
					insertBoardElement(contentPanel,new PagingDTO(dtos.size(), Integer.parseInt(pageBtn.getText())));
					contentPanel.repaint();
					contentPanel.revalidate();
				}
			});
		}//	for(int i=0;i<10;i++) {
		
		if(Pdto.isNext()) {
			pagingPanel.add(nextBtn);
			pagingPanel.add(lastBtn);
		}
	}
	
	/*TAB 삽입*/
	public void inputTopPanel(JPanel contentPanel) {
		JPanel topPanel = new JPanel(); 
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		topPanel.setPreferredSize(new Dimension(740, 40));
		topPanel.setBorder(new MatteBorder(0,0,1,0,Color.LIGHT_GRAY));
		
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
		
		topPanel.add(noTab);
		topPanel.add(titleTab);
		topPanel.add(writerTab);
		topPanel.add(dateTab);
		
		contentPanel.add(topPanel);
	}//public void inputTopPanel(JPanel contentPanel) {

	/*contentPanel에 게시글 삽입*/
	public void insertBoardElement(JPanel contentPanel, PagingDTO Pdto) {
		for(int i=Pdto.getWriting_Start()-1;i<Pdto.getWriting_End();i++) {
			JPanel writingPanel = new JPanel();
			writingPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
			writingPanel.setPreferredSize(new Dimension(740, 40));
			if(i<Pdto.getWriting_End()-1) writingPanel.setBorder(new MatteBorder(0,0,1,0,Color.LIGHT_GRAY));
			
			/*각각 내용*/
			if(i < dtos.size()) {
				JLabel no = new JLabel(String.valueOf(dtos.get(i).getNo()));
				no.setPreferredSize(new Dimension(80, 40));
				no.setOpaque(true);
				no.setHorizontalAlignment(JLabel.CENTER);
				no.setVerticalAlignment(JLabel.CENTER);
				no.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
				no.setBorder(new MatteBorder(0,0,1,0,Color.lightGray));
				
				JLabel title = new JLabel(dtos.get(i).getTitle());
				title.setPreferredSize(new Dimension(460, 40));
				title.setOpaque(true);
				title.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
				title.setBorder(new MatteBorder(0,0,1,0,Color.lightGray));
				title.setName(String.valueOf(dtos.get(i).getNo()));
				/**********************************************************
				 * 제목 영역 클릭 하면 내용으로 넘어가는 클릭 리스너 추가
				 **********************************************************/
				title.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						title.setForeground(Color.blue);
						setCursor(Cursor.HAND_CURSOR);
					}
					@Override
					public void mouseExited(MouseEvent e) {
						title.setForeground(Color.black);
						setCursor(Cursor.DEFAULT_CURSOR);
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						/*내용 바꾼 상태에서 타이머 실행 해야돼.*/
//						System.out.println(e.getComponent().getName());
						infoPanel.removeAll();
						infoPanel = new BoardContent(TtoBTimer,Integer.parseInt(e.getComponent().getName()));
						add(infoPanel);
						infoPanel.repaint();
						infoPanel.revalidate();
						BtoTTimer.start();
					}
				});
				JLabel writer = new JLabel(dtos.get(i).getWriter());
				writer.setPreferredSize(new Dimension(100, 40));
				writer.setOpaque(true);
				writer.setHorizontalAlignment(JLabel.CENTER);
				writer.setVerticalAlignment(JLabel.CENTER);
				writer.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
				writer.setBorder(new MatteBorder(0,0,1,0,Color.lightGray));
				
				JLabel date = new JLabel(new SimpleDateFormat("yy/MM/hh").format(dtos.get(i).getDate()));
				date.setPreferredSize(new Dimension(100,40));
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
			}
		}//for(int i=0;i<11;i++) {
	}//public void insertBoardElement(JPanel contentPanel, PagingDTO Pdto) {
}
