package com.movie.ex.HomePanel;

import java.awt.Dimension;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.movie.ex.Listener.PreviewControlBtnListener;

public class MoviePreviewControl extends JPanel{
	
	public MoviePreviewControl(String path,MoviePreview moviePreview) {
		MouseListener listener = new PreviewControlBtnListener(moviePreview);
		
		setPreferredSize(new Dimension(80, 300));
		setOpaque(false);
		setBorder(new EmptyBorder(145, 0, 0, 0));
		
		/*컨트롤 버튼*/
		JButton arwBtn = new JButton();
		arwBtn.setSize(37,60);
		arwBtn.setBorderPainted(false);								//버튼 테두리 삭제
		arwBtn.setFocusPainted(false);								//버튼 선택 되었을 때 테두리 삭제
		arwBtn.setContentAreaFilled(false);							//배경색 삭제
		
		/*리스너 추가*/
		arwBtn.addMouseListener(listener);
		
		if(path.equals("left")) {
			arwBtn.setIcon(new ImageIcon("./images/leftarw.png"));
			arwBtn.setName("left");
		}else if(path.equals("right")) {
			arwBtn.setIcon(new ImageIcon("./images/rightarw.png"));
			arwBtn.setName("right");
		}else {
			System.out.println("ERROR");
		}
		
		add(arwBtn);
	}
}
