/**
 * The Driver class runs the background music that is played throughout the game.
 * Once the music begins playing, the main method of the Captain class is run.
 * Running this class begins the Captain Coconut game.
 * @author Soham Jain, Shaurya Jain, and Archi Marrapu
 * @version 1.0
 * @since 5/5/22
 */
 
import java.io.*;
import javax.sound.sampled.*;

public class Driver {

   /**
    * The main method of Driver plays the background music of the game.
    * From here, the Captain class is called and the game begins.
    * This is the first method that is run to begin the Captain Coconut game.
    * @param args main method parameter; not used in the main method.
    * @throws IOException handles the IOException, which may result if the song file is not found
    * @throws UnsupportedAudioFileException handles the UnsupportedAudioFileException, which is thrown if the data in the song file was unrecognizable
    * @throws LineUnavailableException handles the LineUnavailableException, which indicates that a line cannot be opened because it is currently in use by another application
    */
   public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
      // Runs the background music
      AudioInputStream audioIn = AudioSystem.getAudioInputStream(Driver.class.getResource("Coconut-Song.wav"));
      Clip clip = AudioSystem.getClip();
      clip.open(audioIn);
      clip.start();

      // Runs the main method in the Captain class
      Captain.main(new String[0]);
   }

}
