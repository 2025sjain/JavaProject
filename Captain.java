/**
 * The Captain class creates the Java graphics for the Captain Coconut game.
 * When this class is run from the main method of Driver, the beach background and character is spawned.
 * The graphics components that are displayed on the frame are repainted when this class is run.
 * This class takes in user input, and moves the character left or right if the buttons 'A' or 'D' are pressed.
 * While listening to keyboard input, this class also runs Coconut, LuckyCoconut, and RottenCoconut and keeps track of the user's score.
 * @author Soham Jain, Shaurya Jain, and Archi Marrapu
 * @version 1.0
 * @since 5/5/22
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Captain extends JPanel {

   // boolean which checks if the game is over
   public static boolean gameOver = false;

   // frame which displays the Captain Coconut game
   public static JFrame frame = new JFrame("Captain Coconut");

   // contains the key which the user has pressed
   public static String key = "";

   // beach background image for the game
   public static ImageIcon background = new ImageIcon("ezgif.com-gif-maker.jpg");

   // image that displays the main character
   public static ImageIcon capt = new ImageIcon("smolcaptain.png");

   // image that displays the regular, green coconut
   public static ImageIcon coco = new ImageIcon("goodcoco_75x75-removebg-preview.png");

   // image that displays the blue, lucky coconut
   public static ImageIcon luckyCoco = new ImageIcon("imgonline-com-ua-ReplaceColor-0Cm4wL73XI-removebg-preview.png");

   // image that displays the brown, rotten coconut
   public static ImageIcon rottenCoco = new ImageIcon("ezgif.com-gif-maker__1_-removebg-preview.png");

   // integer that contains the user's score
   public static int score = 0;

   // x-coordinate and y-coordinate for the Captain object
   public static int capX = 400;
   public static int capY = 550;

   // x-coordinate and y-coordinate for the Coconut object
   public static int cocoX = 1600;
   public static int cocoY = 800;

   // x-coordinate and y-coordinate for the RottenCoconut object
   public static int rottenX = 1600;
   public static int rottenY = 800;

   // x-coordinate and y-coordinate for the LuckyCoconut object
   public static int luckyX = 1600;
   public static int luckyY = 800;

   // contains the step which the coconut object's y-coordinate increases by
   // this value increases as the game goes on
   public static int step = 20;

   // used to display the "Ready, Set, Go!" message at the beginning of the game
   public static int val = 0;

   /**
    * Graphics method that overrides the method it inherits from JComponent.
    * Paints the graphics components onto the Captain Coconut frame each time this method is run.
    * Updates the background, as well as the captain and coconut locations using their X and Y coordinates.
    * Displays the user's score at the top of the screen using the score variable.
    * At the beginning of the game, the "Ready, Set, Go!" text is displayed through this method.
    * @param g Graphics painting tool that updates the JFrame by drawing images and text on the screen.
    */
   public void paintComponent(Graphics g) {
      // updates the frame with the image of the background and coconuts
      g.drawImage(background.getImage(), 0, 0, null);
      g.drawImage(capt.getImage(), capX, capY, null);
      g.drawImage(coco.getImage(), cocoX, cocoY, null);
      g.drawImage(rottenCoco.getImage(), rottenX, rottenY, null);
      g.drawImage(luckyCoco.getImage(), luckyX, luckyY, null);

      // sets the font for the score that is displayed
      g.setFont(new Font("Montserrat", Font.BOLD, 25));

      // displays the "Ready, Set, Go!" message at the beginning of the game
      if (val == 0) {
         g.setColor(new Color(255, 75, 75));
         g.fillRect(720, 90, 150, 100);
         g.setColor(Color.black);
         g.drawString("Ready?", 750, 150);
      } else if (val == 1) {
         g.setColor(Color.yellow);
         g.fillRect(690, 65, 210, 135);
         g.setColor(Color.black);
         g.setFont(new Font("Montserrat", Font.BOLD, 55));
         g.drawString("Set", 750, 150);
      } else if (val == 2) {
         g.setColor(Color.green);
         g.fillRect(670, 35, 270, 170);
         g.setColor(Color.black);
         g.setFont(new Font("Montserrat", Font.BOLD, 85));
         g.drawString("Go!", 730, 150);
      } else {
         g.setColor(Color.white);
         g.fillRect(710, 65, 260, 125);
         g.setColor(Color.black);
         g.setFont(new Font("Montserrat", Font.BOLD, 40));
         g.drawString("Score: " + score, 750, 150);
      }
   }

   /**
    * Main method of the Captain class that is run from the Driver main method.
    * Creates the frame, while setting the size and location, and making it visible.
    * Adds a KeyListener method to the frame, which inputs the keys that are pressed by the user.
    * Uses the Graphics repaint method to update the frame each time it is run.
    * Checks if the game is over, which is true if an object of type Coconut touches the ground.
    * Spawns a Coconut with a 70% chance, LuckyCoconut with a 15% chance, and RottenCoconut with a 15% chance.
    * @param args main method parameter; not used in this method.
    * @throws IOException handles the IOException, which is thrown if there is an error with the Thread
    */
   public static void main(String[] args) throws IOException {
      // sets the size and content of the frame, then displays it and sets a close operation
      frame.setSize(1600, 800);
      Captain cap = new Captain();
      frame.getContentPane().add(cap);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);

      // adds a KeyListener for user input, which moves the Captain object
      frame.addKeyListener(
              new KeyListener() {
                 @Override
                 public void keyTyped(KeyEvent e) {
                    key = "" + e.getKeyChar();
                    doAction(key);
                 }

                 @Override
                 public void keyPressed(KeyEvent e) {}

                 @Override
                 public void keyReleased(KeyEvent e) {}
              });

      // used to display the "Ready, Set, Go!" message at the beginning of the game
      for (int i = 0; i < 3; i++) {
         try {
            Thread.sleep(1500);
            val++;
            frame.repaint();
         } catch(InterruptedException e) {
            e.printStackTrace();
         }
      }

      // runs as long as the game is not over, and spawns each coconut on the frame
      while (gameOver == false) {
         int rand = (int)(Math.random()*1000 + 200);

         cocoY = 0;
         luckyY = 0;
         rottenY = 0;
         double r = Math.random();

         // normal coconut: 70%
         // lucky coconut: 15%
         // rotten coconut: 15%
         if (r < 0.70)
            Coconut.moveCocos(rand, frame);
         else if (r < 0.85)
            LuckyCoconut.moveLucky(rand, frame);
         else
            RottenCoconut.moveRotten(rand, frame);
      }

      cocoX = 1600;
      cocoY = 800;
      frame.repaint();
   }

   /**
    * This is run from the Coconut class's main method.
    * Uses the key String to determine which action to take based on user input.
    * If the 'A' key is pressed, the moveLeft method is run.
    * However, if the 'D' key is pressed, the moveRight method is run.
    * Pressing any other key does not result in any action taken by this method.
    * @param key String containing the key that the user presses, which is used to determine what action the game will take.
    */
   public static void doAction(String key) {
      // checks if the 'A' or 'D' key is pressed
      switch (key) {
         case "a":
            moveLeft(frame);
            break;
         case "d":
            moveRight(frame);
            break;
         default:
            break;
      }
   }

   /**
    * This method is run when the game is over, which results if an object of type Coconut touches the ground without being collected.
    * Sets the gameOver boolean to true, so the frame will no longer be updated.
    * Runs a two second delay using Thread, before the exit screen is displayed.
    * Once the two second delay is over, the showScore method is from the ExitScreen class.
    * @throws IOException handles the IOException, which is thrown if there is an error in the Thread object
    */
   public static void gameEnd() throws IOException {
      gameOver = true;
      try {
         Thread.sleep(2000);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }

      // displays the exit screen after the game is over
      ExitScreen.showScore(frame);
   }

   /**
    * This method runs if the doAction method determines that the user presses the 'D' key.
    * Checks if the Captain object is visible on the screen by using its x-coordinate.
    * Increases the x-value of the Captain object and repaints the frame.
    * Updates the screen with the increased x-value, hence moving the object in the east direction.
    * @param frame The frame that is displayed as the screen for the Captain Coconut game.
    */
   private static void moveRight(JFrame frame) {
      // increases the x-coordinate of the Captain
      if (capX <= 1300) {
         capX += 60;
      }

      frame.repaint();
   }

   /**
    * This method runs if the doAction method determines that the user presses the 'A' key.
    * Checks if the Captain object is visible on the screen by using its x-coordinate.
    * Decreases the x-value of the Captain object and repaints the frame.
    * Updates the screen with the decreased x-value, hence moving the object in the west direction.
    * @param frame The frame that is displayed as the screen for the Captain Coconut game.
    */
   private static void moveLeft(JFrame frame) {
      // decreases the x-coordinate of the Captain
      if (capX >= 100) {
         capX -= 60;
      }

      frame.repaint();
   }

}
