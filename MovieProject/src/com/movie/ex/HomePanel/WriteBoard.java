package com.movie.ex.HomePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.movie.ex.DAO.BoardDAO;

public class WriteBoard extends JPanel{
	public WriteBoard(MovieAllBoard movieAllBoard) {
		BoardDAO dao = new BoardDAO();
		
		setBounds(800,0,800,600);
		setBackground(Color.white);
		setLayout(null);
	
		JLabel titleLabel = new JLabel("글 작성");
		titleLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		titleLabel.setBounds(40,20,100,30);
		
		/*뒤로 가기 버튼*/
		JButton backBtn = new JButton("뒤로가기");
		backBtn.setBounds(40,480,90,30);
		backBtn.setContentAreaFilled(false);
		
		/*뒤로가기 버튼 리스너 등록*/
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				movieAllBoard.LtoRTimer.start();
			}
		});
		
		/*제목 입력*/
		JTextField titleField = new JTextField();
		titleField.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		titleField.setBounds(40,70,710,30);
			
		/*내용 영역*/
		JTextArea contentArea = new JTextArea();
		contentArea.setBounds(40,110,710,350);
		contentArea.setBorder(new LineBorder(Color.black));
		
		/*입력 버튼*/
		JButton completeBtn = new JButton("입력");
		completeBtn.setBounds(660,480,90,30);
		completeBtn.setContentAreaFilled(false);
		
		/*입력 버튼 리스너 등록*/
		completeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String title = titleField.getText().trim();
				String content = contentArea.getText().trim();
				dao.insert(title, content);
			}
		});
		
		add(titleLabel);
		add(backBtn);
		add(titleField);
		add(contentArea);
		add(completeBtn);
	}
}
