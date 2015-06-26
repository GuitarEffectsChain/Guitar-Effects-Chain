package audio_processing;

/* SERVIDOR   
 * Creado por Sebastian Cipolat
 * */

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.SwingUtilities;

import frames.Connect_frame;
import frames.Main_frame;

public class Server {
	public static Socket skCliente;
	public static ServerSocket skServidor;
	public static boolean connected = false;
	String datareceived, substring1, substring2;
	final int PUERTO = 5555;// Puerto que utilizara el servidor utilizar este
							// mismo en el cliente
	String IP_client;
	String mdata;
	ObjectOutputStream oos = null;
	String TimeStamp;
	BufferedReader stdIn;
	
	public static boolean carga = false;
	
	public Server(Connect_frame connect_frame) {

		try {
			
			System.out.println("************ SERVER ****************");
			// creamos server socket
			System.out.println(GetIp.getLocalAddress().getHostAddress());
			System.out.println("Escuchando el puerto " + PUERTO);
			System.out.println("En Espera....");

			TimeStamp = new java.util.Date().toString();
			
		      Runnable serverTask = new Runnable() {
		          @Override
		          public void run() {

		              try {
		                  skServidor = new ServerSocket(PUERTO);
		                  skCliente = skServidor.accept();
		                  connected = true;
		                  IP_client = skCliente.getInetAddress().toString();
		    				System.out.println("[" + TimeStamp + "] Conectado al cliente "
		    						+ "IP:" + IP_client);
		                  while (true) {		      				
		                		DataInputStream DIS = new DataInputStream(skCliente.getInputStream());
								mdata = DIS.readUTF();
								System.out.println("MENSAJE: " + mdata);

								String[] msg_dcd = getMSG(mdata);
								
			
					        	
								if(msg_dcd.length <= 5){
									determinarEfecto(msg_dcd);
								}
								else{
									//carga = true;
									Main_frame.preset_name.setText(msg_dcd[0]);
									setAllSlidersAmp(msg_dcd);
									setAllSlidersComp(msg_dcd);
									setAllSlidersDelay(msg_dcd);
									setAllSlidersReverb(msg_dcd);
									//carga = false;
									}
								}
		                  }catch (IOException e) {
		                  System.err.println("Accept failed.");
		                  connected = false;
		                  Connect_frame.splashTimer.start();
		              }

		          }
		      };
		      
		      Thread serverThread = new Thread(serverTask);
		      serverThread.start();
		      System.out.println("thread server: " + serverThread.getName());
		   } catch (Exception e) {
			e.printStackTrace();
			System.out.println("[" + TimeStamp + "] Error ");
		}
	}

	public String[] getMSG(String msg){
		String[] res;
		res = msg.split(";");
		return res;
	}
	
	private void determinarEfecto(String[] effect) {
		// TODO Auto-generated method stub
		int efecto = Integer.parseInt(effect[0]);
		switch(efecto){
		case 1:
			setSliderAmpParam(effect);
			break;
		case 2:
			setSliderCompParam(effect);
			break;
		case 3:
			setSliderDelayParam(effect);
			break;
		case 4:
			setSliderReverbParam(effect);
			break;
		default :
			break;
						
		}
	}
	
	private void setAllSlidersAmp(final String[] data) {
		
	
		
		if (SwingUtilities.isEventDispatchThread()) {
			Main_frame.amp_drive_slider.setValue(Integer.parseInt(data[1]));

		} else {
		    SwingUtilities.invokeLater(new Runnable () {
		        public void run () {

		        	Main_frame.amp_drive_slider.setValue(Integer.parseInt(data[1]));
		        }
		    });
		}
		
		if (SwingUtilities.isEventDispatchThread()) {
			Main_frame.amp_mid_slider.setValue(Integer.parseInt(data[3]));
		} else {
		    SwingUtilities.invokeLater(new Runnable () {
		        public void run () {
		        	Main_frame.amp_mid_slider.setValue(Integer.parseInt(data[3]));		        }
		    });
		}
		
		if (SwingUtilities.isEventDispatchThread()) {
			Main_frame.amp_low_slider.setValue(Integer.parseInt(data[2]));
		} else {
		    SwingUtilities.invokeLater(new Runnable () {
		        public void run () {
		        	Main_frame.amp_low_slider.setValue(Integer.parseInt(data[2]));		        }
		    });
		}

		if (SwingUtilities.isEventDispatchThread()) {
			Main_frame.amp_high_slider.setValue(Integer.parseInt(data[4]));
		} else {
		    SwingUtilities.invokeLater(new Runnable () {
		        public void run () {
		        	Main_frame.amp_high_slider.setValue(Integer.parseInt(data[4]));
		        	}
		    });
		}


		if (SwingUtilities.isEventDispatchThread()) {
			Main_frame.amp_channel.setSelectedIndex(Integer.parseInt(data[6]));		
			} else {
		    SwingUtilities.invokeLater(new Runnable () {
		        public void run () {
		        	Main_frame.amp_channel.setSelectedIndex(Integer.parseInt(data[6]));		        	
		        	}
		    });
		}
				
		
		//Main_frame.amp_volume_slider.setValue(Integer.parseInt(data[5]));
		
		
	}
	private void setAllSlidersComp(final String[] data) {
		

		if (SwingUtilities.isEventDispatchThread()) {
			Main_frame.compressor_attack_slider.setValue(Integer.parseInt(data[8]));
			} else {
		    SwingUtilities.invokeLater(new Runnable () {
		        public void run () {
		        	Main_frame.compressor_attack_slider.setValue(Integer.parseInt(data[8]));
		        	}
		    });
		}
		
		if (SwingUtilities.isEventDispatchThread()) {
			Main_frame.compressor_decay_slider.setValue(Integer.parseInt(data[9]));		
			} else {
		    SwingUtilities.invokeLater(new Runnable () {
		        public void run () {
		        	Main_frame.compressor_decay_slider.setValue(Integer.parseInt(data[9]));
		        	}
		    });
		}
		if (SwingUtilities.isEventDispatchThread()) {
			Main_frame.compressor_ratio_slider.setValue(Integer.parseInt(data[10]));
			} else {
		    SwingUtilities.invokeLater(new Runnable () {
		        public void run () {
		        	Main_frame.compressor_ratio_slider.setValue(Integer.parseInt(data[10]));
		        	}
		    });
		}
		
		if (SwingUtilities.isEventDispatchThread()) {
			Main_frame.compressor_threshold_slider.setValue(Integer.parseInt(data[11]));
			} else {
		    SwingUtilities.invokeLater(new Runnable () {
		        public void run () {
		        	Main_frame.compressor_threshold_slider.setValue(Integer.parseInt(data[11]));
		        	}
		    });
		}
		
		if (SwingUtilities.isEventDispatchThread()) {
			Main_frame.compressor_button.setSelected(Integer.parseInt(data[7]) == 1 ? true : false);
			Main_frame.compressor_button.setText(Integer.parseInt(data[7]) == 1 ? "ON" : "OFF");
						} else {
		    SwingUtilities.invokeLater(new Runnable () {
		        public void run () {
					Main_frame.compressor_button.setSelected(Integer.parseInt(data[7]) == 1 ? true : false);
					Main_frame.compressor_button.setText(Integer.parseInt(data[7]) == 1 ? "ON" : "OFF");		        	}
		    });
		}
	}

	private void setAllSlidersReverb(final String[] data) {
		if (SwingUtilities.isEventDispatchThread()) {
			Main_frame.reverb_roomsize_slider.setValue(Integer.parseInt(data[17]));
			} else {
		    SwingUtilities.invokeLater(new Runnable () {
		        public void run () {
		        	Main_frame.reverb_roomsize_slider.setValue(Integer.parseInt(data[17]));
		        	}
		    });
		}
		
		if (SwingUtilities.isEventDispatchThread()) {
			Main_frame.reverb_mix_slider.setValue(Integer.parseInt(data[18]));
			} else {
		    SwingUtilities.invokeLater(new Runnable () {
		        public void run () {
		        	Main_frame.reverb_mix_slider.setValue(Integer.parseInt(data[18]));
		        	}
		    });
		}
		

		if (SwingUtilities.isEventDispatchThread()) {
			Main_frame.reverb_button.setSelected(Integer.parseInt(data[16]) == 1 ? true : false);
			Main_frame.reverb_button.setText(Integer.parseInt(data[16]) == 1 ? "ON" : "OFF");
			} else {
		    SwingUtilities.invokeLater(new Runnable () {
		        public void run () {
					Main_frame.reverb_button.setSelected(Integer.parseInt(data[16]) == 1 ? true : false);
					Main_frame.reverb_button.setText(Integer.parseInt(data[16]) == 1 ? "ON" : "OFF");		        	}
		    });
		}
	}
	
	private void setAllSlidersDelay(final String[] data) {
		
		if (SwingUtilities.isEventDispatchThread()) {
			Main_frame.delay_time_slider.setValue(Integer.parseInt(data[13]));
			} else {
		    SwingUtilities.invokeLater(new Runnable () {
		        public void run () {
		        	Main_frame.delay_time_slider.setValue(Integer.parseInt(data[13]));
		        	}
		    });
		}
		
		if (SwingUtilities.isEventDispatchThread()) {
			Main_frame.delay_feedback_slider.setValue(Integer.parseInt(data[14]));
			} else {
		    SwingUtilities.invokeLater(new Runnable () {
		        public void run () {
		        	Main_frame.delay_feedback_slider.setValue(Integer.parseInt(data[14]));
		        	}
		    });
		}
		
		if (SwingUtilities.isEventDispatchThread()) {
			Main_frame.delay_mix_slider.setValue(Integer.parseInt(data[15]));
			} else {
		    SwingUtilities.invokeLater(new Runnable () {
		        public void run () {
		        	Main_frame.delay_mix_slider.setValue(Integer.parseInt(data[15]));
		        	}
		    });
		}
		
		if (SwingUtilities.isEventDispatchThread()) {
			Main_frame.delay_button.setSelected(Integer.parseInt(data[12]) == 1 ? true : false);
			Main_frame.delay_button.setText(Integer.parseInt(data[12]) == 1 ? "ON" : "OFF");
						} else {
		    SwingUtilities.invokeLater(new Runnable () {
		        public void run () {
					Main_frame.delay_button.setSelected(Integer.parseInt(data[12]) == 1 ? true : false);
					Main_frame.delay_button.setText(Integer.parseInt(data[12]) == 1 ? "ON" : "OFF");		        	}
		    });
		}
	}

	private void setSliderReverbParam(String[] param) {
		// TODO Auto-generated method stub
		int parametro = Integer.parseInt(param[1]);
		switch(parametro){
		case 1:
			Main_frame.reverb_roomsize_slider.setValue(Integer.parseInt(param[2]));
			break;
		case 2:
			Main_frame.reverb_mix_slider.setValue(Integer.parseInt(param[2]));
			break;
		case 3:
			Main_frame.reverb_button.setSelected(Integer.parseInt(param[2]) == 1 ? true : false);
			Main_frame.reverb_button.setText(Integer.parseInt(param[2]) == 1 ? "ON" : "OFF");
			break;
		}
		
	}

	private void setSliderCompParam(String[] param) {
		// TODO Auto-generated method stub
		int parametro = Integer.parseInt(param[1]);
		switch(parametro){
		case 1:
			Main_frame.compressor_attack_slider.setValue(Integer.parseInt(param[2]));
			break;
		case 2:
			Main_frame.compressor_decay_slider.setValue(Integer.parseInt(param[2]));
			break;
		case 3:
			Main_frame.compressor_ratio_slider.setValue(Integer.parseInt(param[2]));
			break;
		case 4:
			Main_frame.compressor_threshold_slider.setValue(Integer.parseInt(param[2]));
			break;
		case 5:
			Main_frame.compressor_button.setSelected(Integer.parseInt(param[2]) == 1 ? true : false);
			Main_frame.compressor_button.setText(Integer.parseInt(param[2]) == 1 ? "ON" : "OFF");
			break;
		}
		
	}

	private void setSliderDelayParam(String[] param) {
		// TODO Auto-generated method stub
		int parametro = Integer.parseInt(param[1]);
		switch(parametro){
		case 1:
			Main_frame.delay_time_slider.setValue(Integer.parseInt(param[2]));
			break;
		case 2:
			Main_frame.delay_feedback_slider.setValue(Integer.parseInt(param[2]));
			break;
		case 3:
			Main_frame.delay_mix_slider.setValue(Integer.parseInt(param[2]));
			break;
		case 4:
			Main_frame.delay_button.setSelected(Integer.parseInt(param[2]) == 1 ? true : false);
			Main_frame.delay_button.setText(Integer.parseInt(param[2]) == 1 ? "ON" : "OFF");
			break;
		}
		
	}

	private void setSliderAmpParam(String[] param) {
		// TODO Auto-generated method stub
		int parametro = Integer.parseInt(param[1]);
		switch(parametro){
		case 1:
			Main_frame.amp_drive_slider.setValue(Integer.parseInt(param[2]));
			break;		
		case 2:
			Main_frame.amp_low_slider.setValue(Integer.parseInt(param[2]));
			break;		
		case 3:
			Main_frame.amp_mid_slider.setValue(Integer.parseInt(param[2]));
			break;		
		case 4:
			Main_frame.amp_high_slider.setValue(Integer.parseInt(param[2]));
			break;		
		case 5:
			break;
		case 6:
			Main_frame.amp_channel.setSelectedIndex(Integer.parseInt(param[2]));
			break;
		}
	}
	
	

}	
	
	
