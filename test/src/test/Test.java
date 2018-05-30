package test;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.MediaPlayer;


//public class Test {
//
//    private final JFrame frame;
//
//    private final EmbeddedMediaPlayerComponent component;
//    private final EmbeddedMediaPlayer player;
//    
//    public static void main(String[] args) {
//        new NativeDiscovery().discover();
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new Test("https://youtu.be/QKWlXvt9x7c");
//            }
//        });
//    }
//
//    public Test(String args) {
//        frame = new JFrame("My First Media Player");
//        frame.setBounds(500, 200, 800, 500);
//        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//        frame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//            	component.release();
//            	System.exit(0);
//            }
//        });
//        
//        component = new EmbeddedMediaPlayerComponent();
//        player = component.getMediaPlayer();
//        player.addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
//        	@Override
//        	public void finished(MediaPlayer mediaPlayer) {
//        		component.release();
//        		frame.remove(component);
//        	}
//        });
//        frame.setContentPane(component);
//        frame.setVisible(true);
//        
//        player.setPlaySubItems(true);
//        player.playMedia(args);
//    }
//}


public class Test extends JFrame{
	boolean flag=true;
	
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
    
    public static void main(final String[] args) {
        new NativeDiscovery().discover();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Test("https://www.youtube.com/watch?v=zU5hM4tvkTA");
            }
        });
    }

    public Test(String args) {
    	super("Preview");
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width-1280)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-720)/2, 1280, 720);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayerComponent.release();
                System.exit(0);
            }
        });
        
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent() {
        	@Override
        	public void finished(MediaPlayer mediaPlayer) {
        		if(flag) {
        			flag=!flag;
        		}else {
        			dispose();
        			mediaPlayerComponent.release();
        			System.out.println("finished");
        		}
        	}
        };
        
        setContentPane(mediaPlayerComponent);
        setVisible(true);
        mediaPlayerComponent.getMediaPlayer().setPlaySubItems(true);
        mediaPlayerComponent.getMediaPlayer().playMedia(args);
    }
}









