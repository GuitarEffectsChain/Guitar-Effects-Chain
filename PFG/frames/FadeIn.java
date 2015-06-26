package frames;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;




@SuppressWarnings("serial")

public class FadeIn extends JPanel implements ActionListener {

    Image imagem;
    Timer timer;
    
    private float alpha = 0f;
    
    public static JFrame frame;
  

    public FadeIn() {
        imagem = new ImageIcon("Images/Intro-img.jpg").getImage();
        timer = new Timer(100, this);
       timer.start();
    }
// here you define alpha 0f to 1f
    public FadeIn(float alpha) {
        imagem = new ImageIcon("Images/Intro-img.jpg").getImage();
        this.alpha = alpha;

    }
    
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                                                    alpha));
        g2d.drawImage(imagem, 0, 0, null);
       
        
        
    }

    public static void main(String[] args) {

    	
        frame = new JFrame();
        frame.add(new FadeIn());
        frame.setSize(680,680);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        
    }


    
    
    public void actionPerformed(ActionEvent e) {
        alpha += 0.05f;
        
        if (alpha >1) {
            alpha = 1;
            timer.stop();
            
            new Intro_frame();
            frame.dispose();
            
            
        }
        
        repaint();
    }
    
}