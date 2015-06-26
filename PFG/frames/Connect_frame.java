package frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import connection.*;


public class Connect_frame {

	public static JFrame frame = new JFrame("Connect with Android Device");
	public JLabel connection_ip_title;
	public static JLabel status_label = new JLabel("Waiting.");
	public JLabel ip_value;
	public JButton cancel_button = new JButton("Cancel");
	public static Thread splashTimer;
	private JLabel connection_port_title = new JLabel("Connection Port: " + Integer.parseInt(Config_frame.connection_port_box.getSelectedItem().toString()));
	
	public Font cpb;
	public Font cpb1;
	public Font cpb2;
	
	
	
	
public Connect_frame(){
	
	try {
		 cpb = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Cheap Potatoes Black Thin.ttf"));
		 cpb = cpb.deriveFont(18f);
		 cpb1 = cpb.deriveFont(12f);
		 cpb2 = cpb.deriveFont(24f);
		
	} catch (Exception e) {
		
	}
	
	frame.setContentPane(new JLabel(new ImageIcon("Images/guitar.jpg")));
	frame.setPreferredSize(new Dimension(550, 550));
	
	
	
	try {
		
		connection_ip_title = new JLabel("");
		if(GetIp.getLocalAddress() != null){connection_ip_title.setText("Connection IP: "	+ GetIp.getLocalAddress().getHostAddress());}
		else{connection_ip_title.setText("ConnectionIP: Connect WIFI");}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	connection_ip_title.setForeground(Color.orange);
	connection_ip_title.setBounds(120,50,400,50);
	connection_ip_title.setFont(cpb);
	
	connection_port_title.setForeground(Color.orange);
	connection_port_title.setBounds(120,100,400,50);
	connection_port_title.setFont(cpb);
	
	status_label.setForeground(Color.white);
	status_label.setBounds(200,300,300,50);
	status_label.setFont(cpb2);
	
	if(!Server.connected){
		
		new Server(null);
	
    splashTimer = new Thread(){
        @SuppressWarnings("deprecation")
		public void run(){
            try{
                int splashTime = 0;
             
                while(Server.connected == false){

                    sleep(100);

                        if(splashTime < 2000){
                            status_label.setText("Waiting.");
                        }
                        else if(splashTime >= 2000 && splashTime < 4000 ){
                            status_label.setText("Waiting..");
                        }else if (splashTime >= 4000){
                            status_label.setText("Waiting..."); 
                            System.out.println(Server.connected);
                        }
                        
                        if(splashTime>=6000){splashTime = 0;}
                        else{splashTime = splashTime + 200;}
                        

                }
             
              if(Server.connected){status_label.setText("Connected!");
              sleep(1000);
              frame.dispose();
              Main_frame.connect_button.setEnabled(true);
              splashTimer.stop();
              
              
              }
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            
        }
    };
    splashTimer.start();    
 
	};
    
	cancel_button.setBounds(430,460,100,50);
	cancel_button.setFont(cpb1);
	cancel_button.addActionListener(new ActionListener() {
		
		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			
				try {
					Server.skServidor.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	
			
			Main_frame.connect_button.setEnabled(true);
			splashTimer.stop();
			frame.dispose();	
		}
	});
	
	Main_frame.connect_button.setEnabled(false);
	frame.add(cancel_button);
	frame.add(connection_ip_title);
	frame.add(status_label);
	frame.add(connection_port_title);
	
	frame.setUndecorated(true);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frame.setResizable(false);
	frame.pack();
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
	
}
	

}


