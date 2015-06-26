package frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import connection.GetIp;
import audio_processing.MainActivity;

public class Config_frame {

	public static JFrame frame = new JFrame("Audio Configuration");
	public JLabel title_label;
	public JButton save_button = new JButton("Save");
	public JButton cancel_button = new JButton("Cancel");
	private JLabel buffer_size_title = new JLabel("Buffer Size: ");
	private JLabel connection_port_title = new JLabel("Connection Port: ");
	public static JComboBox buffer_size_box = new JComboBox(new String[] {"32","64","128","256","512"});
	public static JComboBox connection_port_box = new JComboBox(new String[]{"5555"});
	
	public Font cpb;

	private boolean isPortInUse(String hostName, int portNumber) {
        boolean result;

        try {

            Socket s = new Socket(hostName, portNumber);
            s.close();
            result = true;

        }
        catch(Exception e) {
            result = false;
        }

        return(result);
	}
	
	
public Config_frame() throws SocketException{
	
	try {
		 cpb = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Cheap Potatoes Black Thin.ttf"));
		 cpb = cpb.deriveFont(12f);
		 
	} catch (Exception e) {
	}
	
	connection_port_box.removeAllItems();
	for(int i = 5555; i<5575; i++){
		
		if(!isPortInUse(GetIp.getLocalAddress().getHostAddress(),i)){
			
		connection_port_box.addItem(Integer.toString(i));	
			
		
		}
		
		System.out.println(isPortInUse(GetIp.getLocalAddress().getHostAddress(),i));
		
	}
	
	
	
	final Object buffer_ini = buffer_size_box.getSelectedItem();
	final Object port_ini = connection_port_box.getSelectedItem();
	
	frame.setContentPane(new JLabel(new ImageIcon("Images/guitar.jpg")));
	frame.setPreferredSize(new Dimension(550, 550));
	
	cancel_button.setBounds(430,460,100,50);
	cancel_button.setFont(cpb);
	cancel_button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
				
		frame.dispose();
		buffer_size_box.setSelectedItem(buffer_ini);
		connection_port_box.setSelectedItem(port_ini);
		}
	});
	
	save_button.setBounds(320,460,100,50);
	save_button.setFont(cpb);
	save_button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		
		if(Integer.parseInt(buffer_size_box.getSelectedItem().toString()) != MainActivity.ac.getBufferSize()){
			
			//Reiniciar Audio Context
			
		System.out.println(MainActivity.ac.getBufferSize());
			
		}	
		
		frame.dispose();
		
		}
	});
	
	buffer_size_title.setBounds(100,70,160,30);
	buffer_size_title.setFont(cpb);
	buffer_size_title.setForeground(Color.white);
	buffer_size_box.setBounds(260, 70, 170, 30);
	
	connection_port_title.setBounds(100,120,160,30);
	connection_port_title.setFont(cpb);
	connection_port_title.setForeground(Color.white);
	connection_port_box.setBounds(260, 120, 170, 30);
	
	frame.add(cancel_button);
	frame.add(save_button);
	frame.add(buffer_size_title);
	frame.add(buffer_size_box);
	frame.add(connection_port_title);
	frame.add(connection_port_box);
	
	frame.setUndecorated(true);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frame.setResizable(false);
	frame.pack();
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
	
}		
	
}
