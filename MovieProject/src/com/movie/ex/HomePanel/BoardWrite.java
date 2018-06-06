package com.movie.ex.HomePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.movie.ex.DAO.BoardDAO;
import com.movie.ex.DTO.BoardDTO;
import com.movie.ex.DTO.PagingDTO;

public class BoardWrite extends JPanel{
	BoardDAO dao = new BoardDAO();
	ArrayList<BoardDTO> dtos = new ArrayList<>();
	
	public BoardWrite(MovieAllBoard movieAllBoard) {
		setBounds(800,0,800,600);
//		setBackground(Color.white);
		setLayout(null);
		
	
		JLabel titleLabel = new JLabel("글 작성");
		titleLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		titleLabel.setBounds(40,20,100,30);
		
		/*뒤로 가기 버튼*/
		JButton backBtn = new JButton("뒤로가기");
		backBtn.setBounds(40,480,90,30);
		backBtn.setContentAreaFilled(false);
		
		
		/*제목 입력*/
		JTextField titleField = new JTextField();
		titleField.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		titleField.setBounds(40,70,710,30);
			
		/*내용 영역*/
		JTextArea contentArea = new JTextArea();
		contentArea.setBounds(40,110,710,350);
		contentArea.setBorder(new LineBorder(Color.black));
		contentArea.setLineWrap(true);
		
		/*입력 버튼*/
		JButton completeBtn = new JButton("입력");
		completeBtn.setBounds(660,480,90,30);
		completeBtn.setContentAreaFilled(false);
		
		/*뒤로가기 버튼 리스너 등록*/
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				titleField.setText("");
				contentArea.setText("");
				movieAllBoard.LtoRTimer.start();
			}
		});
		
		/*입력 버튼 리스너 등록*/
		completeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String title = titleField.getText().trim();
				String content = contentArea.getText().trim();
				
				/*입력 받은 값으로 DB저장*/
				dao.insert(title, content);
				
				/*입력 완료 했으므로 초기화*/
				titleField.setText("");
				contentArea.setText("");
				JOptionPane.showMessageDialog(null, "등록 되었습니다");
				
				/*입력된거 적용하기 위한 작업*/
				movieAllBoard.contentPanel.removeAll();
				movieAllBoard.Pdto.setCur_Page(1);
				movieAllBoard.dtos = dao.getBoardList();
				PagingDTO Pdto = new PagingDTO(movieAllBoard.dtos.size(), 1);
				
				movieAllBoard.contentPanel.removeAll();
				movieAllBoard.inputTopPanel(movieAllBoard.contentPanel);
				movieAllBoard.insertBoardElement(movieAllBoard.contentPanel,Pdto);
				movieAllBoard.contentPanel.repaint();
				movieAllBoard.contentPanel.revalidate();
				movieAllBoard.LtoRTimer.start();
			}
		});
		
		add(titleLabel);
		add(backBtn);
		add(titleField);
		add(contentArea);
		add(completeBtn);
	}
}
