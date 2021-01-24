import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;



public class Music {
	
	Clip clip = null;
	long DurationOfMusic;
	FloatControl gainControl;
	
	
	//TODO write a second stop music method
	public void playMusicSingle(int index) {
		File soundFile;
		
		String musicArray1[] = new String[5];
		//win
		musicArray1[0] = "Sound/victory.wav";
		//lose
		musicArray1[1] = "Sound/hangman.wav";
		
		soundFile = new File(musicArray1[index]);
		
		//Clip clip = null;
				try {
					clip = AudioSystem.getClip();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				AudioInputStream ais = null;
				try {
					ais = AudioSystem.getAudioInputStream(soundFile);
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					clip.open(ais);
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DurationOfMusic = clip.getMicrosecondLength()/1000000;
				
				if(index == 1 ) {
					DurationOfMusic = DurationOfMusic - 4;
				}
				
				setVolume(80);
				clip.start();
		
				
			
				
				
	}
	
	public void playMusicLoop(int index) {
		File soundFile;
		
		String musicArray[] = new String[5];
		//in room
		musicArray[0] = "Sound/betrayel.wav";
		//in asking monk
		musicArray[1] = "Sound/witchsdom.wav";
		//type music
		musicArray[2] = "Sound/typewriter-1.wav";
		
		soundFile = new File(musicArray[index]);
		//Clip clip = null;
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AudioInputStream ais = null;
		try {
			ais = AudioSystem.getAudioInputStream(soundFile);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clip.open(ais);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setVolume(70);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
	}
	
	public void setVolume(int inputInt) {
		if(inputInt > 110 || inputInt < 0) {
			System.out.println("volume should be between 0 to 110");
		}else {
		int newInt = (int) (inputInt * 0.509) - 50;//map input to master gain range
		gainControl = 
			    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(newInt);
		}
	}
	
	public void stopMusic() {
		clip.close();
	}
	
	
}
