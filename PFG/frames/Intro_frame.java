package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import audio_processing.MainActivity;

public class Intro_frame {

	public JFrame frame;
	public JButton startButton;
	public JLabel info_label;
	
	public Font cpb;
	
	public Intro_frame(){
		
		try {
			 cpb = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Cheap Potatoes Black Thin.ttf"));
			 cpb = cpb.deriveFont(16f);
			 
		} catch (Exception e) {
		}	
		
		 	frame = new JFrame();
		 	frame.setContentPane(new JLabel(new ImageIcon("Images/Intro-img.jpg")));
	        frame.setSize(680,680);
	        frame.setUndecorated(true);
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);
	        
	        
	        startButton = new JButton("Start");
	        startButton.setBounds(480,600,180,60);
	        startButton.setFont(cpb);
	        info_label = new JLabel("<html><strong>Set your Audio Interface at Settings -> Audio Preferences, and then click \"Start\"<strong><html>");
	        info_label.setBounds(20,610,400,60);
	        info_label.setForeground(Color.white);
	        frame.add(startButton);
	        frame.add(info_label);
	        
	        startButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					frame.dispose();
					new Main_frame();
					new MainActivity();
				}
			});
		
		
		
	}
}
