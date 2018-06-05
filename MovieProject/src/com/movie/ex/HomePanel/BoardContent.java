package com.movie.ex.HomePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import com.movie.ex.DAO.BoardDAO;
import com.movie.ex.DTO.BoardDTO;

public class BoardContent extends JPanel{
	int no;
	BoardDTO dto = new BoardDTO();
	BoardDAO dao = new BoardDAO();
	Color defaultColor;
	
	public BoardContent(Timer timer, int no) {
		setBounds(0,600,800,600);
		setLayout(null);
		
		if(no!=-1) {
			dto = dao.getBoardInfo(no);
			
			JPanel wrapPanel = new JPanel();
			wrapPanel.setBounds(20,20,760,560);
			wrapPanel.setLayout(null);
			
			/*글 제목*/
			JPanel titlePanel = new JPanel();
			titlePanel.setLayout(null);
	//		titlePanel.setBackground(Color.gray);
			titlePanel.setBounds(0,0,740,50);
			titlePanel.setBorder(new MatteBorder(0,0,3,0,Color.LIGHT_GRAY));
			
			JLabel titleLabel = new JLabel(dto.getTitle());
			titleLabel.setBounds(20,5,740,50);
			titleLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			titlePanel.add(titleLabel);
			
			/*글 정보 - 글 번호,글쓴이,작성일*/
			JPanel infoPanel = new JPanel();
			infoPanel.setLayout(null);
	//		infoPanel.setBackground(Color.lightGray);
			infoPanel.setBounds(0,50,760,30);
			
			JLabel infoLabel = new JLabel(dto.getNo() + " ｜ " + dto.getWriter() + " ｜ " + dto.getDate());
			infoLabel.setBounds(20,0,760,30);
			infoLabel.setForeground(Color.blue);
			infoLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			infoPanel.add(infoLabel);
					
			/*글 정보 - 글 내용*/
			JPanel contentPanel = new JPanel();
			contentPanel.setLayout(null);
			contentPanel.setBounds(0,80,740,380);
			contentPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
			defaultColor = contentPanel.getBackground();
			
			JTextArea contentArea = new JTextArea(dto.getContent());
			contentArea.setBounds(20,10,700,360);
			contentArea.setBackground(defaultColor);
			contentArea.setEditable(false);
			contentArea.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
			contentPanel.add(contentArea);
			
			/*목록으로 버튼*/
			JButton backBtn= new JButton("목록으로");
			backBtn.setBounds(40,500,90,30);
			backBtn.setContentAreaFilled(false);
			/*목록으로 버튼 리스너 추가*/
			backBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					timer.start();
				}
			});
			
			wrapPanel.add(titlePanel);
			wrapPanel.add(infoPanel);
			wrapPanel.add(contentPanel);
			add(backBtn);
			
			add(wrapPanel);
		}
	}
}
