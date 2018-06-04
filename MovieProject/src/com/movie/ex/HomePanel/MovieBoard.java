package com.movie.ex.HomePanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import com.movie.ex.DAO.BoardDAO;
import com.movie.ex.DTO.BoardDTO;

public class MovieBoard extends JPanel{
	public MovieBoard() {
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardDTO> dtos = new ArrayList<>();
		
		setLayout(null);
		setBounds(575,25,500,450);
		setBorder(new LineBorder(Color.black));
		setBackground(Color.white);
		
		/*전체 감싸는 영역*/
		JPanel wrapPanel = new JPanel();
		wrapPanel.setLayout(null);
		wrapPanel.setBounds(20,10,460,430);
		
		/*제목 영역*/
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		topPanel.setBorder(new MatteBorder(0,0,2,0,Color.gray));
		topPanel.setBackground(Color.white);
		topPanel.setBounds(0,0,460,60);
		
		/*타이틀*/
		JLabel titleLabel= new JLabel("자유 게시판");
		titleLabel.setBounds(20,15,200,30);
		titleLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		/*더보기 버튼*/
		ImageIcon imageIcon = new ImageIcon("./images/plus.png");
		Image image = imageIcon.getImage();
		Image newImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		JButton moreIcon = new JButton(new ImageIcon(newImage));
		moreIcon.setBounds(410,15,30,30);
		moreIcon.setContentAreaFilled(false);
		
		moreIcon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MovieAllBoard();
			}
		});

		/*제목 라벨, 더보기 버튼 추가*/
		topPanel.add(titleLabel);
		topPanel.add(moreIcon);
		
		/*내용 영역*/
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(5, 1));
		contentPanel.setBounds(0,60,460,370);
		contentPanel.setBackground(Color.red);
		
		/*상위 5개 글 갖고온다*/
		dtos = dao.getUpperTen();
		
		for(int i=0;i<5;i++) {
			JPanel reviewPanel = new JPanel();
			reviewPanel.setPreferredSize(new Dimension(460, 80));
			reviewPanel.setBackground(Color.WHITE);
			if(i<4) reviewPanel.setBorder(new MatteBorder(0,0,1,0,Color.LIGHT_GRAY));
			
			
			if(i<dtos.size()) {
				JLabel no = new JLabel(String.valueOf(dtos.get(i).getNo()));
				JLabel title = new JLabel(dtos.get(i).getTitle());
				JLabel writer = new JLabel(dtos.get(i).getWriter());
				JLabel date = new JLabel(new SimpleDateFormat("yy/MM/dd").format(dtos.get(i).getDate()));
			}
			contentPanel.add(reviewPanel);
		}
		
		wrapPanel.add(topPanel);
		wrapPanel.add(contentPanel);
		add(wrapPanel);
	}
}
