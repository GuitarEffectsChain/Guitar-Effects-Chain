package audio_processing;

import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.core.UGen;
import net.beadsproject.beads.ugens.BiquadFilter;
import net.beadsproject.beads.ugens.Compressor;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.Reverb;
import net.beadsproject.beads.ugens.TapIn;
import net.beadsproject.beads.ugens.TapOut;
import net.beadsproject.beads.ugens.WaveShaper;

import frames.Config_frame;
import frames.Main_frame;


public class MainActivity {
	
	public static AudioContext ac;
	public static UGen microphoneIn;
	public static Gain reverbGain, delayGain, mainGain;
	public static WaveShaper OD;
	public static Reverb R;
	public static Compressor C;
	public static TapIn delayIn;
	public static TapOut delayOut;
	public static BiquadFilter filter_high, filter_low, filter_mid, filter_high2;
	public float[] WaveShape = {-1f, -0.75f, -0.5f, -0.25f, 0f, 0.25f, 0.5f, 0.75f, 1f};

	
	public MainActivity(){
  

	//Audio Context initialization
	ac = new AudioContext(Integer.parseInt(Config_frame.buffer_size_box.getSelectedItem().toString())); 
	microphoneIn = ac.getAudioInput();
	System.out.println(ac.getBufferSize());
	mainGain = new Gain(ac,1,1f);
	mainGain.addInput(microphoneIn);
	
	//Amp
	OD = new WaveShaper(ac, WaveShape);
	OD.addInput(mainGain);
	OD.setPreGain((float) Main_frame.amp_drive_slider.getValue()/50);
	OD.setPostGain((float) Main_frame.amp_volume_slider.getValue()/4);
	OD.setLimit(1f);
	OD.setWetMix(0f);
	
	
	filter_high = new BiquadFilter(ac, BiquadFilter.LP, 5000.0f, 0.7f);
	filter_low = new BiquadFilter(ac, BiquadFilter.HP, 80.0f, 0.7f);
	filter_mid = new BiquadFilter(ac, BiquadFilter.NOTCH, 2500.0f, 0.7f);
	filter_high2 = new BiquadFilter(ac, BiquadFilter.LP, 5000.0f, 0.7f);
	
	
	filter_low.addInput(OD);
	filter_mid.addInput(filter_low);
	filter_high.addInput(filter_mid);
	filter_high2.addInput(filter_high);
	

	//Compressor
	C = new Compressor(ac, 1);
	C.setAttack(30);
	C.setDecay(200);
	C.setRatio(4.0f);
	C.setThreshold(0.6f);
	
	
	//Delay
	delayIn = new TapIn(ac, 2000);
	delayOut = new TapOut(ac, delayIn,TapOut.ALLPASS, 100.0f);
	delayGain = new Gain(ac, 1, 0.5f);	
	delayGain.addInput(delayOut);
	
	//Reverb
	reverbGain = new Gain(ac, 1, 0.75f);
	R = new Reverb(ac);
	R.setSize((float) 0.5);

	//Outputs
	ac.out.addInput(filter_high2);
	ac.start(); 
	
	
	}
	
	
public static void main(String[] args) {

	SwingUtilities.invokeLater(new Runnable() {

        @Override
        public void run() {
        	

        }});
}}
