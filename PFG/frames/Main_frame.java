package frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import audio_processing.MainActivity;

public class Main_frame {
	
	public static JFrame frame;

	public static JButton connect_button;
	public static JButton config_button;
	public static JButton import_preset_button;
	public static JButton export_preset_button;
	public static JLabel preset_name;
	public static String pr_name;
	
	@SuppressWarnings("serial")
	public JPanel amp_panel = new JPanel(null){
	      
		@Override
		  protected void paintComponent(Graphics g) {

		    super.paintComponent(g);
		        g.drawImage(new ImageIcon("Images/amp_pedal.png").getImage(), 0, 0, null);
		}
	};

	
	public Insets amp_insets = amp_panel.getInsets();
	public JLabel amp_drive_name_label;
	public static JLabel amp_drive_value_label;
	public JLabel amp_low_name_label;
	public static JLabel amp_low_value_label;
	public JLabel amp_mid_name_label;
	public static JLabel amp_mid_value_label;
	public JLabel amp_high_name_label;
	public static JLabel amp_high_value_label;
	public JLabel amp_volume_name_label;
	public static JLabel amp_volume_value_label;
	public JLabel amp_channel_name_label;
	public static JComboBox amp_channel;
	
	
	public static JSlider amp_drive_slider;
	public static JSlider amp_low_slider;
	public static JSlider amp_mid_slider;
	public static JSlider amp_high_slider;
	public static JSlider amp_volume_slider;
	
	@SuppressWarnings("serial")
	public JPanel reverb_panel = new JPanel(null){
		
		@Override
		  protected void paintComponent(Graphics g) {

		    super.paintComponent(g);
		        g.drawImage(new ImageIcon("Images/reverb_pedal.png").getImage(), 0, 0, null);
		}
	};
	
	public Insets reverb_insets = reverb_panel.getInsets();
	public JLabel reverb_roomsize_name_label;
	public static JLabel reverb_roomsize_value_label;
	public static JSlider reverb_roomsize_slider;
	public JLabel reverb_mix_name_label;
	public static JSlider reverb_mix_slider;
	public static JLabel reverb_mix_value_label;
	public static JToggleButton reverb_button;
	
	@SuppressWarnings("serial")
	public JPanel compressor_panel = new JPanel(null){
		@Override
		  protected void paintComponent(Graphics g) {

		    super.paintComponent(g);
		        g.drawImage(new ImageIcon("Images/comp_pedal.png").getImage(), 0, 0, null);
		}
	};
	
	public Insets compressor_insets = compressor_panel.getInsets();
	public JLabel compressor_attack_name_label;
	public static JLabel compressor_attack_value_label;
	public JLabel compressor_decay_name_label;
	public static JLabel compressor_decay_value_label;
	public JLabel compressor_ratio_name_label;
	public static JLabel compressor_ratio_value_label;
	public JLabel compressor_threshold_name_label;
	public static JLabel compressor_threshold_value_label;
	public static JSlider compressor_attack_slider;
	public static JSlider compressor_decay_slider;
	public static JSlider compressor_ratio_slider;
	public static JSlider compressor_threshold_slider;
	public static JToggleButton compressor_button;
	
	@SuppressWarnings("serial")
	public JPanel delay_panel = new JPanel(null){
		@Override
		  protected void paintComponent(Graphics g) {

		    super.paintComponent(g);
		        g.drawImage(new ImageIcon("Images/delay_pedal.png").getImage(), 0, 0, null);
		}
	};
	
	public Insets delay_insets = delay_panel.getInsets();
	public JLabel delay_time_name_label;
	public static JLabel delay_time_value_label;
	public static JSlider delay_time_slider;
	public JLabel delay_feedback_name_label;
	public static JLabel delay_feedback_value_label;
	public static JSlider delay_feedback_slider;
	public JLabel delay_mix_name_label;
	public static JLabel delay_mix_value_label;
	public static JSlider delay_mix_slider;
	public static JToggleButton delay_button;

	
	
	public Font cpb; 
	public Font cpb1; 
	public Font cpb2; 
	
	public static String params[];

	
	
	public Main_frame() {
	
		try {
			 cpb = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Cheap Potatoes Black Thin.ttf"));
			 cpb = cpb.deriveFont(14f);
			 cpb1 = cpb.deriveFont(12f);
			 cpb2 = cpb.deriveFont(10f);
			
		} catch (Exception e) {
		
		}	
		
		
	connect_button = new JButton("Connect with Android Device");	
	connect_button.setBounds(970,610,280,70);
	connect_button.setFont(cpb1);
	
	connect_button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			
	
				new Connect_frame();
			

		}
	});
	
	config_button = new JButton("Configuration");	
	config_button.setBounds(30,610,200,70);	
	config_button.setFont(cpb1);
	
	config_button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			
			
				try {
					new Config_frame();
				} catch (SocketException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
			
		}
	});
	
	import_preset_button = new JButton("Import Preset");
	import_preset_button.setBounds(250,610,120,70);
	import_preset_button.setFont(cpb2);
	
	import_preset_button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JFileChooser preset = new JFileChooser();
		    FileNameExtensionFilter f1 = new FileNameExtensionFilter("Text Files", "txt", "text");
			
			preset.setFileFilter(f1);
			int result = preset.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = preset.getSelectedFile();
				
					BufferedReader br;
					try {
						br = new BufferedReader(new FileReader(selectedFile));
						
						params = br.readLine().split(";");
						
							
						//TODO Cambiar valores ON/OFF 
						
						amp_drive_slider.setValue(Integer.parseInt(params[1]));
						amp_high_slider.setValue(Integer.parseInt(params[2]));
						amp_mid_slider.setValue(Integer.parseInt(params[3]));
						amp_low_slider.setValue(Integer.parseInt(params[4]));
						amp_volume_slider.setValue(Integer.parseInt(params[5]));
						amp_channel.setSelectedIndex(Integer.parseInt(params[6]));
						
						if(params[7] == "1"){compressor_button.setSelected(false);compressor_button.doClick();}
						else {compressor_button.setSelected(true);compressor_button.setText("Off");}

						compressor_attack_slider.setValue(Integer.parseInt(params[8]));
						compressor_decay_slider.setValue(Integer.parseInt(params[9]));
						compressor_ratio_slider.setValue(Integer.parseInt(params[10]));
						compressor_threshold_slider.setValue(Integer.parseInt(params[11]));
						
						if(params[12] == "1"){delay_button.setSelected(false);delay_button.doClick();}
						else {delay_button.setSelected(true);delay_button.setText("Off");}
					
						delay_time_slider.setValue(Integer.parseInt(params[13]));
						delay_feedback_slider.setValue(Integer.parseInt(params[14]));
						delay_mix_slider.setValue(Integer.parseInt(params[15]));
						
						if(params[16] == "1"){reverb_button.setSelected(false);reverb_button.doClick();}
						else {reverb_button.setSelected(true);reverb_button.setText("Off");}
						
						reverb_roomsize_slider.setValue(Integer.parseInt(params[17]));
						reverb_mix_slider.setValue(Integer.parseInt(params[18]));
						
						
						pr_name = params[0];
						preset_name.setText(params[0]);
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						
						JOptionPane.showMessageDialog(null,
						        "ÁArchivo No V‡lido!",
						           "Advertencia",JOptionPane.WARNING_MESSAGE);
					}

				
			}
			
		}
	});
	
	export_preset_button = new JButton("Export Preset");
	export_preset_button.setBounds(390,610,120,70);
	export_preset_button.setFont(cpb2);
	
	export_preset_button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try
			 {
			  JFileChooser file =new JFileChooser();
			  file.showSaveDialog(null);
			  File save_file =file.getSelectedFile();
			 
			  if(save_file !=null)
			  {
			    FileWriter  save_writer =new FileWriter(save_file);
			    int b1 = compressor_button.isSelected() ? 1 : 0;
			    int b2 = delay_button.isSelected() ? 1 : 0;
			    int b3 = reverb_button.isSelected() ? 1 : 0;
			    
			    save_writer.write(save_file.getName() + ";" + amp_drive_slider.getValue() + ";" + amp_low_slider.getValue() + ";"+ 
			    amp_mid_slider.getValue() + ";"+ amp_high_slider.getValue() + ";" + amp_volume_slider.getValue() + ";" + amp_channel.getSelectedIndex() + ";" 
			    + b1 + ";" + compressor_attack_slider.getValue() + ";" + compressor_decay_slider.getValue() + ";" + compressor_ratio_slider.getValue() 
			    + ";" + compressor_threshold_slider.getValue() + ";" + b2 + ";" + delay_time_slider.getValue() + ";" + delay_feedback_slider.getValue() + ";" 
			    + delay_mix_slider.getValue() + ";" + b3 + ";" + reverb_roomsize_slider.getValue() + ";" + reverb_mix_slider.getValue() + ";");
			    save_writer.close();
			    
			    if(!save_file.getAbsolutePath().endsWith(".txt")){
			    	
			    	File temp = new File(save_file+".txt");
			    	save_file.renameTo(temp);
			    	
			    }
			    
			    pr_name = save_file.getName();
			    preset_name.setText(save_file.getName());
			    
			    }
			 }
			  catch(IOException ex)
			  {
			   JOptionPane.showMessageDialog(null,
			        "ÁSu archivo no ha podido guardarse!",
			           "Advertencia",JOptionPane.WARNING_MESSAGE);
			  }
			 }	
			
		
	});
	
	preset_name = new JLabel("No Preset Selected");
	preset_name.setFont(cpb);
	preset_name.setBounds(535,610,250,70);
	preset_name.setForeground(Color.white);

	
	//Amp
	amp_drive_name_label = new JLabel("Drive");
	amp_drive_name_label.setForeground(Color.white);
	amp_drive_slider = new JSlider(0,100,50);
	amp_drive_value_label = new JLabel(amp_drive_slider.getValue() + " %");
	amp_drive_value_label.setForeground(Color.white);
	amp_low_name_label = new JLabel("Low");
	amp_low_name_label.setForeground(Color.white);
	amp_low_slider = new JSlider(0,100,50);
	amp_low_value_label = new JLabel(amp_low_slider.getValue() + " %");
	amp_low_value_label.setForeground(Color.white);
	amp_mid_name_label = new JLabel("Mid");
	amp_mid_name_label.setForeground(Color.white);
	amp_mid_slider = new JSlider(0,100,50);
	amp_mid_value_label = new JLabel(amp_mid_slider.getValue() + " %");
	amp_mid_value_label.setForeground(Color.white);
	amp_high_name_label = new JLabel("High");
	amp_high_name_label.setForeground(Color.white);
	amp_high_slider = new JSlider(0,100,50);
	amp_high_value_label = new JLabel(amp_high_slider.getValue() + " %");
	amp_high_value_label.setForeground(Color.white);
	amp_volume_name_label = new JLabel("Volume");
	amp_volume_name_label.setForeground(Color.white);
	amp_volume_slider = new JSlider(0,100,80);
	amp_volume_value_label = new JLabel(amp_volume_slider.getValue() + "%");
	amp_volume_value_label.setForeground(Color.white);
	amp_channel_name_label = new JLabel("Channel");
	amp_channel_name_label.setForeground(Color.white);
	amp_channel = new JComboBox(new String[] {"Clean","Overdrive", "Distortion"});
	
	amp_panel.setBounds(64,10,240,600);
	amp_panel.add(amp_drive_name_label);
	amp_panel.add(amp_drive_slider);
	amp_panel.add(amp_drive_value_label);
	amp_panel.add(amp_low_name_label);
	amp_panel.add(amp_low_slider);
	amp_panel.add(amp_low_value_label);
	amp_panel.add(amp_mid_name_label);
	amp_panel.add(amp_mid_slider);
	amp_panel.add(amp_mid_value_label);
	amp_panel.add(amp_high_name_label);
	amp_panel.add(amp_high_slider);
	amp_panel.add(amp_high_value_label);
	amp_panel.add(amp_volume_name_label);
	amp_panel.add(amp_volume_slider);
	amp_panel.add(amp_volume_value_label);
	amp_panel.add(amp_channel_name_label);
	amp_panel.add(amp_channel);
	amp_panel.setOpaque(false);
	
	amp_drive_name_label.setBounds(10 + amp_insets.left,430 + amp_insets.top,50,20);
	amp_drive_slider.setBounds(40 + amp_insets.left,430 + amp_insets.top,150,20);
	amp_drive_value_label.setBounds(190 + amp_insets.left,430 + amp_insets.top,40,20);
	amp_low_name_label.setBounds(10 + amp_insets.left,455 + amp_insets.top,30,20);
	amp_low_slider.setBounds(40 + amp_insets.left,455 + amp_insets.top,150,20);
	amp_low_value_label.setBounds(190 + amp_insets.left,455 + amp_insets.top,40,20);
	amp_mid_name_label.setBounds(10 + amp_insets.left,480 + amp_insets.top,30,20);
	amp_mid_slider.setBounds(40 + amp_insets.left,480 + amp_insets.top,150,20);
	amp_mid_value_label.setBounds(190 + amp_insets.left,480 + amp_insets.top,40,20);
	amp_high_name_label.setBounds(10 + amp_insets.left,505 + amp_insets.top,30,20);
	amp_high_slider.setBounds(40 + amp_insets.left,505 + amp_insets.top,150,20);
	amp_high_value_label.setBounds(190 + amp_insets.left,505 + amp_insets.top,40,20);
	amp_volume_name_label.setBounds(10 + amp_insets.left,530 + amp_insets.top,50,20);
	amp_volume_slider.setBounds(50 + amp_insets.left,530 + amp_insets.top,140,20);
	amp_volume_value_label.setBounds(190 + amp_insets.left,530 + amp_insets.top,40,20);
	amp_channel_name_label.setBounds(10 + amp_insets.left,555 + amp_insets.top,70,20);
	amp_channel.setBounds(70 + amp_insets.left,555 + amp_insets.top,155,20);
	
	//Amp Listeners
		amp_drive_slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				
				if(preset_name.getText() != "No Preset Selected"){preset_name.setText(pr_name + "*");}
				amp_drive_value_label.setText(amp_drive_slider.getValue() + " %");
				if(amp_channel.getSelectedItem() == "Clean"){
				MainActivity.mainGain.setGain(2f);
				} else if(amp_channel.getSelectedItem() == "Overdrive"){
					MainActivity.mainGain.setGain(0.5f);
					MainActivity.OD.setPreGain(amp_drive_slider.getValue()/25);
				} else if (amp_channel.getSelectedItem() == "Distortion"){
					MainActivity.mainGain.setGain(0.5f);
					MainActivity.OD.setPreGain(amp_drive_slider.getValue()/4);	
				}
				
			}
		});	
		
		amp_high_slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				
				if(preset_name.getText() != "No Preset Selected"){preset_name.setText(pr_name + "*");}
				amp_high_value_label.setText(amp_high_slider.getValue() + " %");
				MainActivity.filter_high.setFrequency(4000 + amp_high_slider.getValue()*10);
				MainActivity.filter_high2.setFrequency(5000 + amp_high_slider.getValue()*10);
			}
		});
		
		amp_mid_slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				
				if(preset_name.getText() != "No Preset Selected"){preset_name.setText(pr_name + "*");}
				amp_mid_value_label.setText(amp_mid_slider.getValue() + " %");
				MainActivity.filter_mid.setFrequency(3000 - amp_mid_slider.getValue()*10);
			
			}
		});
		
		amp_low_slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				
				if(preset_name.getText() != "No Preset Selected"){preset_name.setText(pr_name + "*");}
				amp_low_value_label.setText(amp_low_slider.getValue() + " %");
				MainActivity.filter_low.setFrequency(200 - amp_low_slider.getValue());
				
			}
		});
		
		amp_volume_slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				
				if(preset_name.getText() != "No Preset Selected"){preset_name.setText(pr_name + "*");}
				amp_volume_value_label.setText(amp_volume_slider.getValue() + " %");
				MainActivity.OD.setPostGain(amp_volume_slider.getValue()/4);
			}
		});
		
		
		amp_channel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(preset_name.getText() != "No Preset Selected"){preset_name.setText(pr_name + "*");}
				if(amp_channel.getSelectedItem() == "Clean"){
					MainActivity.OD.setWetMix(0f);
					MainActivity.OD.setPreGain(1f);
				} else if(amp_channel.getSelectedItem() == "Overdrive"){
					MainActivity.OD.setWetMix(0.5f);
					MainActivity.OD.setPreGain(amp_drive_slider.getValue()/25);
				} else if (amp_channel.getSelectedItem() == "Distortion"){
					MainActivity.OD.setWetMix(0.7f);
					MainActivity.OD.setPreGain(amp_drive_slider.getValue()/4);	
				}	
			}
		});

			
	
	
	//Compressor
	compressor_attack_name_label = new JLabel("Attack");
	compressor_attack_name_label.setForeground(Color.white);
	compressor_attack_slider = new JSlider(1,500,30);
	compressor_attack_value_label = new JLabel(compressor_attack_slider.getValue() + "ms");
	compressor_attack_value_label.setForeground(Color.white);
	compressor_decay_name_label = new JLabel("Decay");
	compressor_decay_name_label.setForeground(Color.white);
	compressor_decay_slider = new JSlider(1,500,200);
	compressor_decay_value_label = new JLabel(compressor_decay_slider.getValue() + "ms");
	compressor_decay_value_label.setForeground(Color.white);
	compressor_ratio_name_label = new JLabel("Ratio");
	compressor_ratio_name_label.setForeground(Color.white);
	compressor_ratio_slider = new JSlider(0,30,4);
	compressor_ratio_value_label = new JLabel(compressor_ratio_slider.getValue() + ":1");
	compressor_ratio_value_label.setForeground(Color.white);
	compressor_threshold_name_label = new JLabel("Threshold");
	compressor_threshold_name_label.setForeground(Color.white);
	compressor_threshold_slider = new JSlider(1,100,50);
	compressor_threshold_value_label = new JLabel(compressor_threshold_slider.getValue() + "%");
	compressor_threshold_value_label.setForeground(Color.white);
	compressor_button = new JToggleButton("Off");
	compressor_button.setFont(cpb2);
	
	compressor_panel.setBounds(368,10,240,600);
	compressor_panel.add(compressor_attack_name_label);
	compressor_panel.add(compressor_attack_slider);
	compressor_panel.add(compressor_attack_value_label);
	compressor_panel.add(compressor_decay_name_label);
	compressor_panel.add(compressor_decay_slider);
	compressor_panel.add(compressor_decay_value_label);
	compressor_panel.add(compressor_ratio_name_label);
	compressor_panel.add(compressor_ratio_slider);
	compressor_panel.add(compressor_ratio_value_label);
	compressor_panel.add(compressor_threshold_name_label);
	compressor_panel.add(compressor_threshold_slider);
	compressor_panel.add(compressor_threshold_value_label);
	compressor_panel.add(compressor_button);
	compressor_panel.setOpaque(false);
	
	compressor_attack_name_label.setBounds(10 + compressor_insets.left,430 + compressor_insets.top,100,20);
	compressor_attack_slider.setBounds(50 + compressor_insets.left,430 + compressor_insets.top,130,20);
	compressor_attack_value_label.setBounds(180 + compressor_insets.left,430 + compressor_insets.top,50,20);
	compressor_decay_name_label.setBounds(10 + compressor_insets.left,460 + compressor_insets.top,100,20);
	compressor_decay_slider.setBounds(50 + compressor_insets.left,460 + compressor_insets.top,130,20);
	compressor_decay_value_label.setBounds(180 + compressor_insets.left,460 + compressor_insets.top,50,20);
	compressor_ratio_name_label.setBounds(10 + compressor_insets.left,490 + compressor_insets.top,100,20);
	compressor_ratio_slider.setBounds(50 + compressor_insets.left,490 + compressor_insets.top,130,20);
	compressor_ratio_value_label.setBounds(180 + compressor_insets.left,490 + compressor_insets.top,50,20);
	compressor_threshold_name_label.setBounds(10 + compressor_insets.left,520 + compressor_insets.top,100,20);
	compressor_threshold_slider.setBounds(65 + compressor_insets.left,520 + compressor_insets.top,115,20);
	compressor_threshold_value_label.setBounds(180 + compressor_insets.left,520 + compressor_insets.top,50,20);
	compressor_button.setBounds(80 + compressor_insets.left,310 + compressor_insets.top,80,40);
	
	
	//Compressor Listeners
	
	compressor_button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(preset_name.getText() != "No Preset Selected"){preset_name.setText(pr_name + "*");}
			if(!compressor_button.isSelected()){
				
			MainActivity.ac.out.removeConnection(0,MainActivity.C ,0);	
			MainActivity.ac.out.removeConnection(1,MainActivity.C ,0);
			
			compressor_button.setText("Off");
			MainActivity.C.clearInputConnections();
		
			if(delay_button.isSelected()){
				
				MainActivity.delayIn.clearInputConnections();
				MainActivity.delayIn.addInput(MainActivity.filter_high2);	
			}
			
			if(reverb_button.isSelected() && !delay_button.isSelected()){
				
				MainActivity.reverbGain.clearInputConnections();
				MainActivity.reverbGain.addInput(MainActivity.filter_high2);
			}
			
			} else {
			
				MainActivity.C.addInput(MainActivity.filter_high2);
				MainActivity.ac.out.addInput(MainActivity.C);	
				compressor_button.setText("On");
			}
			
			
		}
	});
	

	
	compressor_attack_slider.addChangeListener(new ChangeListener() {
		
		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			
		if(preset_name.getText() != "No Preset Selected"){preset_name.setText(pr_name + "*");}
		MainActivity.C.setAttack(compressor_attack_slider.getValue());	
		compressor_attack_value_label.setText(compressor_attack_slider.getValue() + " ms");
		System.out.println("Compressor Attack = " + compressor_attack_slider.getValue() + " ms");
			
		}
	});
	
	compressor_decay_slider.addChangeListener(new ChangeListener() {
		
		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			
		if(preset_name.getText() != "No Preset Selected"){preset_name.setText(pr_name + "*");}	
		MainActivity.C.setDecay(compressor_decay_slider.getValue());	
		compressor_decay_value_label.setText(compressor_decay_slider.getValue() + " ms");
		System.out.println("Compressor Decay = " + compressor_decay_slider.getValue() + " ms");
			
		}
	});
		
	compressor_ratio_slider.addChangeListener(new ChangeListener() {
		
		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			
		if(preset_name.getText() != "No Preset Selected"){preset_name.setText(pr_name + "*");}
		MainActivity.C.setRatio(compressor_ratio_slider.getValue());	
		compressor_ratio_value_label.setText(compressor_ratio_slider.getValue() + ":1");
		System.out.println("Compressor Ratio = " + compressor_ratio_slider.getValue() + ":1");
			
		}
	});
	
	compressor_threshold_slider.addChangeListener(new ChangeListener() {
		
		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
		
		if(preset_name.getText() != "No Preset Selected"){preset_name.setText(pr_name + "*");}
		MainActivity.C.setThreshold(compressor_threshold_slider.getValue());	
		compressor_threshold_value_label.setText(compressor_threshold_slider.getValue() + " %");
		System.out.println("Compressor Threshold = " + compressor_threshold_slider.getValue() + " %");
			
		}
	});
	
	
	//Multi Tap Delay
	delay_time_name_label = new JLabel("Time");
	delay_time_name_label.setForeground(Color.white);
	delay_time_slider = new JSlider(0,2000,100);
	delay_time_value_label = new JLabel(delay_time_slider.getValue() + " ms");
	delay_time_value_label.setForeground(Color.white);
	delay_feedback_name_label = new JLabel("FeedBack");
	delay_feedback_name_label.setForeground(Color.white);
	delay_feedback_slider = new JSlider(0,100,50);
	delay_feedback_value_label = new JLabel(delay_feedback_slider.getValue() + " %");
	delay_feedback_value_label.setForeground(Color.white);
	delay_mix_name_label = new JLabel("Mix");
	delay_mix_name_label.setForeground(Color.white);
	delay_mix_slider = new JSlider(0,100,50);
	delay_mix_value_label = new JLabel(delay_mix_slider.getValue() + " %");
	delay_mix_value_label.setForeground(Color.white);
	delay_button = new JToggleButton("Off");
	delay_button.setFont(cpb2);
	
	delay_panel.setBounds(672,10,240,600);
	delay_panel.add(delay_time_name_label);
	delay_panel.add(delay_time_slider);
	delay_panel.add(delay_time_value_label);
	delay_panel.add(delay_feedback_name_label);
	delay_panel.add(delay_feedback_slider);
	delay_panel.add(delay_feedback_value_label);
	delay_panel.add(delay_mix_name_label);
	delay_panel.add(delay_mix_slider);
	delay_panel.add(delay_mix_value_label);
	delay_panel.add(delay_button);
	delay_panel.setOpaque(false);
	
	delay_time_name_label.setBounds(10 + delay_insets.left,430 + delay_insets.top,100,20);
	delay_time_slider.setBounds(50 + delay_insets.left,430 + delay_insets.top,130,20);
	delay_time_value_label.setBounds(180 + delay_insets.left,430 + delay_insets.top,60,20);
	delay_feedback_name_label.setBounds(10 + delay_insets.left,460 + delay_insets.top,100,20);
	delay_feedback_slider.setBounds(60 + delay_insets.left,460 + delay_insets.top,120,20);
	delay_feedback_value_label.setBounds(180 + delay_insets.left,460 + delay_insets.top,50,20);
	delay_mix_name_label.setBounds(10 + delay_insets.left,490 + delay_insets.top,100,20);
	delay_mix_slider.setBounds(50 + delay_insets.left,490 + delay_insets.top,130,20);
	delay_mix_value_label.setBounds(180 + delay_insets.left,490 + delay_insets.top,50,20);
	delay_button.setBounds(80 + delay_insets.left,310 + delay_insets.top,80,40);
	
	
	
	
	//Delay Listeners
	delay_button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			if(preset_name.getText() != "No Preset Selected"){preset_name.setText(pr_name + "*");}
			if(!delay_button.isSelected()){
				
			MainActivity.ac.out.removeConnection(0,MainActivity.delayGain ,0);	
			MainActivity.ac.out.removeConnection(1,MainActivity.delayGain ,0);
			delay_button.setText("Off");
			MainActivity.delayIn.clearInputConnections();
			
			if(reverb_button.isSelected()){
				
				MainActivity.reverbGain.clearInputConnections();
				if(compressor_button.isSelected()){MainActivity.reverbGain.addInput(MainActivity.C);}
				else{MainActivity.reverbGain.addInput(MainActivity.filter_high2);}
						
			}
		
			
			} else {
			
				MainActivity.delayIn.addInput(MainActivity.delayGain);
				if(compressor_button.isSelected()){MainActivity.delayIn.addInput(MainActivity.C);}
				else if(!compressor_button.isSelected()){MainActivity.delayIn.addInput(MainActivity.filter_high2);}	
				MainActivity.ac.out.addInput(MainActivity.delayGain);
				delay_button.setText("On");
			}	
			
		}
		
	});
	
	
	
		delay_time_slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
			
			if(preset_name.getText() != "No Preset Selected"){preset_name.setText(pr_name + "*");}
			MainActivity.delayOut.setDelay(delay_time_slider.getValue());
			delay_time_value_label.setText(delay_time_slider.getValue() + " ms");
			
			
			}
		});
		
		delay_feedback_slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub	
			
			if(preset_name.getText() != "No Preset Selected"){preset_name.setText(pr_name + "*");}
			MainActivity.delayGain.setGain((float)(delay_feedback_slider.getValue()/100.0f)*(delay_mix_slider.getValue()/100.0f));
			delay_feedback_value_label.setText(delay_feedback_slider.getValue() + "%");
			
			}
		});
		
		delay_mix_slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
			
			if(preset_name.getText() != "No Preset Selected"){preset_name.setText(pr_name + "*");}
			MainActivity.delayGain.setGain((float)(delay_feedback_slider.getValue()/100.0f)*(delay_mix_slider.getValue()/100.0f));
			delay_mix_value_label.setText(delay_mix_slider.getValue() + "%");

			}
		});	
	
		//Reverb
		reverb_roomsize_name_label = new JLabel("Size");
		reverb_roomsize_name_label.setForeground(Color.white);
		reverb_roomsize_slider = new JSlider(0,100,50);
		reverb_button = new JToggleButton("Off");
		reverb_button.setFont(cpb2);
		reverb_roomsize_value_label = new JLabel(reverb_roomsize_slider.getValue() + " %");
		reverb_roomsize_value_label.setForeground(Color.white);
		reverb_mix_name_label = new JLabel("Mix");
		reverb_mix_name_label.setForeground(Color.white);
		reverb_mix_slider = new JSlider(0,100,75);
		reverb_mix_value_label = new JLabel(reverb_mix_slider.getValue() + " %");
		reverb_mix_value_label.setForeground(Color.white);
		
		
		reverb_panel.setBounds(976,10,240,600);
		reverb_panel.add(reverb_roomsize_name_label);
		reverb_panel.add(reverb_roomsize_slider);
		reverb_panel.add(reverb_roomsize_value_label);
		reverb_panel.add(reverb_mix_name_label);
		reverb_panel.add(reverb_mix_slider);
		reverb_panel.add(reverb_mix_value_label);
		reverb_panel.add(reverb_button);
		reverb_panel.setOpaque(false);
		
		reverb_roomsize_name_label.setBounds(10 + reverb_insets.left,430 + reverb_insets.top,40,20);
		reverb_roomsize_slider.setBounds(40 + reverb_insets.left,430 + reverb_insets.top,150,20);
		reverb_roomsize_value_label.setBounds(190 + reverb_insets.left,430 + reverb_insets.top,40,20);
		reverb_mix_name_label.setBounds(10 + reverb_insets.left,460 + reverb_insets.top,40,20);
		reverb_mix_slider.setBounds(40 + reverb_insets.left,460 + reverb_insets.top,150,20);
		reverb_mix_value_label.setBounds(190 + reverb_insets.left,460 + reverb_insets.top,40,20);
		
		reverb_button.setBounds(80 + reverb_insets.left,310 + reverb_insets.top,80,40);
		
		//Reverb Listeners
		reverb_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(preset_name.getText() != "No Preset Selected"){preset_name.setText(pr_name + "*");}
				if(!reverb_button.isSelected()){
					
				MainActivity.ac.out.removeConnection(0,MainActivity.R ,0);	
				MainActivity.ac.out.removeConnection(1,MainActivity.R ,0);
				reverb_button.setText("Off");
				
				MainActivity.reverbGain.clearInputConnections();
				MainActivity.R.clearInputConnections();
				
			
				
				} else {
				
					if(delay_button.isSelected()){MainActivity.reverbGain.addInput(MainActivity.delayGain);}
					else if(compressor_button.isSelected() && !delay_button.isSelected()){MainActivity.reverbGain.addInput(MainActivity.C);}	
					else {MainActivity.reverbGain.addInput(MainActivity.filter_high2);};	
					
					MainActivity.R.addInput(MainActivity.reverbGain);
					MainActivity.ac.out.addInput(MainActivity.R);	
					reverb_button.setText("On");
				}
				
				
			}
		});
		
		
		reverb_roomsize_slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
			
			if(preset_name.getText() != "No Preset Selected"){preset_name.setText(pr_name + "*");}
			MainActivity.R.setSize((float) reverb_roomsize_slider.getValue()/100);
			reverb_roomsize_value_label.setText(reverb_roomsize_slider.getValue() + " %");
			}
		});	
		
		reverb_mix_slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
			
			if(preset_name.getText() != "No Preset Selected"){preset_name.setText(pr_name + "*");}
			reverb_mix_value_label.setText(reverb_mix_slider.getValue() + " %");
			MainActivity.reverbGain.setGain(reverb_mix_slider.getValue()/100f);
			}
		});	
	
	frame = new JFrame("Guitar Effects Chain");
	frame.setContentPane(new JLabel(new ImageIcon("Images/bg-texture.jpg")));
	
	frame.add(amp_panel);
	frame.add(reverb_panel);
	frame.add(compressor_panel);
	frame.add(delay_panel);
	frame.add(connect_button);
	frame.add(config_button);
	frame.add(import_preset_button);
	frame.add(export_preset_button);
	frame.add(preset_name);
		
	frame.setPreferredSize(new Dimension(1280, 720));
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(false);
	frame.pack();
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
	
	}

		
}


