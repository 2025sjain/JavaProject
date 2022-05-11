/**
 * If the Coconut object is to be spawned, this class is run.
 * Creates the green coconut which adds one point to the user's score if it is collected.
 * Increases the speed at which the coconut is dropped if it is collected.
 * Checks if the Captain character is touching the coconut, to add to the user's score or end the game.
 * Keeps updating the frame or screen of the game by running the repaint method of Java graphics.
 * @author Soham Jain, Shaurya Jain, Archi Marrapu
 * @version 1.0
 * @since 5/5/22
 */

import javax.swing.*;
import java.io.IOException;

public class Coconut {

    /**
     * Returns the x-coordinate of the coconut if this method is run.
     * @return cocoX, or the x-value of the coconut.
     */
    public static int getCocoX() {
        return Captain.cocoX;
    }

    /**
     * Returns the y-coordinate of the coconut if this method is run.
     * @return cocoY, or the y-value of the coconut.
     */
    public static int getCocoY() {
        return Captain.cocoY;
    }

    /**
     * Sets the x-coordinate of the coconut if this method is run.
     * @param x the location where the new x-coordinate of the coconut will be.
     */
    public static void setCocoX(int x) {
        Captain.cocoX = x;
    }

    /**
     * Sets the y-coordinate of the coconut if this method is run.
     * @param y the location where the new y-coordinate of the coconut will be.
     */
    public static void setCocoY(int y) {
        Captain.cocoY = y;
    }

    /**
     * Shows the coconut animation once this method is run, beginning with a random x-value and the JFrame where the animation will be displayed.
     * Checks if the Captain touches the Coconut object, and adds one point to the user's score if true.
     * If the Captain does not touch the Coconut, the gameEnd method is run in the Captain class.
     * Updates the frame 10 times each second using the repaint method.
     * @param rand the random x-coordinate at which the coconut will be spawned.
     * @param frame the frame on which the coconut dropping animation and rest of the game will be displayed.
     * @throws IOException handles the IOException, which is thrown if the Thread object does not compile.
     */
    public static void moveCocos(int rand, JFrame frame) throws IOException {
        // updates the frame with the coconut
        Captain.cocoX = rand;
        frame.repaint();
        boolean touch = false;

        // shows the coconut dropping animation
        while (Captain.cocoY < Captain.capY) {
            Captain.cocoY += Captain.step;
            try {
                Thread.sleep(100);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            // checks if the Captain character touches the Coconut
            if ((Captain.cocoX < Captain.capX + 100) && (Captain.cocoX > Captain.capX - 100) && (Captain.cocoY < Captain.capY + 100) && (Captain.cocoY > Captain.capY - 100))
                touch = true;
            frame.repaint();

        }

        // if the captain collects the coconut, the score increases
        // otherwise, the gameEnd method is run
        if (touch)
            Captain.score++;
        else
            Captain.gameEnd();
        
        // speed at which the coconut drops increases
        Captain.step++;

        Captain.cocoX = 1600;
        frame.repaint();
    }

}
