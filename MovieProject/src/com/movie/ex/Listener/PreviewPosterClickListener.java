package com.movie.ex.Listener;

import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.MediaPlayer;

public class PreviewPosterClickListener extends JFrame{
	boolean flag = true;
	JFrame frame;
	
	private EmbeddedMediaPlayerComponent mediaPlayerComponent;
	
	/*기본 생성자*/
	public PreviewPosterClickListener() {}

	/*생성자*/
	public PreviewPosterClickListener(JFrame frame) {
		this.frame = frame;
	}

	/*url을 통해 동영상 재생*/
	public void showPreview(String url) {
		setTitle("preview");
		setBounds((Toolkit.getDefaultToolkit().getScreenSize().width-1280)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-720)/2, 1280, 720);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				mediaPlayerComponent.release();
			}
		});
		
		/*
		 * 이유 모름. finished 함수가 동영상 시작할때도 호출 돼서 두번 째 나오는 finished 메소드를
		 * 통해 dispose();
		 */
		mediaPlayerComponent= new EmbeddedMediaPlayerComponent() {
			@Override
			public void finished(MediaPlayer mediaPlayer) {
				if(flag) {
					flag=!flag;
				}else {
					dispose();
					mediaPlayerComponent.release();
				}
			}
		};
		
		setContentPane(mediaPlayerComponent);
		setVisible(true);
		mediaPlayerComponent.getMediaPlayer().setPlaySubItems(true);
		mediaPlayerComponent.getMediaPlayer().playMedia(url);
	}
	
	/*
	 * 포스터에 마우스 올리면 손모양으로 바뀌고 클릭하면 동영상 재생 하기 위한
	 * 쓰레드 실행
	 */
	public MouseListener listener = new MouseAdapter() {
		public void mouseEntered(MouseEvent e) {
			frame.setCursor(Cursor.HAND_CURSOR);
		};
		public void mouseExited(MouseEvent e) {
			frame.setCursor(Cursor.DEFAULT_CURSOR);
		};
		public void mouseClicked(MouseEvent e) {
			new NativeDiscovery().discover();
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					showPreview("https://www.youtube.com/watch?v=zU5hM4tvkTA"); 
				}
			});
		};
	};
}
