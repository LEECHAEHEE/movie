package test;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;


public class Test {

    private final JFrame frame;

    private final EmbeddedMediaPlayerComponent component;
    private final EmbeddedMediaPlayer player;
    
    public static void main(String[] args) {
        new NativeDiscovery().discover();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Test("https://youtu.be/yKX2VrYpCp8");
            }
        });
    }

    public Test(String args) {
        frame = new JFrame("My First Media Player");
        frame.setBounds(500, 200, 800, 500);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	component.release();
            	System.exit(0);
            }
        });
        
        component = new EmbeddedMediaPlayerComponent();
        player = component.getMediaPlayer();
        player.addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
        	@Override
        	public void finished(MediaPlayer mediaPlayer) {
        		component.release();
        		frame.remove(component);
        	}
        });
        frame.setContentPane(component);
        frame.setVisible(true);
        
        player.setPlaySubItems(true);
        player.playMedia(args);
    }
}

//public class Test extends JPanel{
//	private EmbeddedMediaListPlayerComponent component;
//	private EmbeddedMediaPlayer player;
//	
//	public Test() {
//		boolean found = new NativeDiscovery().discover();
//		System.out.println(found);
//		System.out.println(LibVlc.INSTANCE.libvlc_get_version());
//		component = new EmbeddedMediaListPlayerComponent();
//		player = component.getMediaPlayer();
//		
//		setLayout(new BorderLayout());
//		setPreferredSize(new Dimension(640, 480));
//		setVisible(true);
//		add(component,BorderLayout.CENTER);
//		player.addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
//			@Override
//			public void finished(MediaPlayer mediaPlayer) {
//				endVideo();
//			}
//		});
//	}//Test()
//
//	private void runVideo(){
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				player.playMedia("");
//			}
//		}).start();
//	}
//	
//	private void endVideo() {
//		component.release();
//		remove(component);
//	}
//}

//public class Test {
//	 private final JFrame frame;
//
//	    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
//
//	    public static void main(final String[] args) {
//	        new NativeDiscovery().discover();
//	        SwingUtilities.invokeLater(new Runnable() {
//	            @Override
//	            public void run() {
//	                new Test("https://youtu.be/yKX2VrYpCp8");
//	            }
//	        });
//	    }
//
//	    public Test(String args) {
//	        frame = new JFrame("My First Media Player");
//	        frame.setBounds(100, 100, 1280, 720);
//	        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//	        frame.addWindowListener(new WindowAdapter() {
//	            @Override
//	            public void windowClosing(WindowEvent e) {
//	                mediaPlayerComponent.release();
//	                System.exit(0);
//	            }
//	        });
//	        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
//	        frame.setContentPane(mediaPlayerComponent);
//	        frame.setVisible(true);
//	        mediaPlayerComponent.getMediaPlayer().setPlaySubItems(true);
//	        mediaPlayerComponent.getMediaPlayer().playMedia(args);
//	    }
//}









