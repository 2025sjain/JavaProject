/**
 * If the RottenCoconut object is to be spawned, this class is run.
 * Creates the brown coconut, which subtracts 3 points from the user's score if it is collected.
 * This object does not need to be collected, unlike the Coconut object, and can be avoided by the user.
 * Checks if the Captain character is touching the coconut, to possibly subtract points from the score.
 * Keeps updating the frame or screen of the game by running the repaint method of Java graphics.
 * @author Soham Jain, Shaurya Jain, Archi Marrapu
 * @version 1.0
 * @since 5/5/22
 */

import javax.swing.*;

public class RottenCoconut {

    /**
     * Returns the x-coordinate of the rotten coconut if this method is run.
     * @return rottenX, or the x-value of the rotten coconut.
     */
    public static int getRottenX() {
        return Captain.rottenX;
    }

    /**
     * Returns the y-coordinate of the rotten coconut if this method is run.
     * @return rottenY, or the y-value of the rotten coconut.
     */
    public static int getRottenY() {
        return Captain.rottenY;
    }

    /**
     * Sets the x-coordinate of the rotten coconut if this method is run.
     * @param x the location where the new x-coordinate of the rotten coconut will be.
     */
    public static void setRottenX(int x) {
        Captain.rottenX = x;
    }

    /**
     * Sets the y-coordinate of the rotten coconut if this method is run.
     * @param y the location where the new y-coordinate of the rotten coconut will be.
     */
    public static void setRottenY(int y) {
        Captain.rottenY = y;
    }

    /**
     * Shows the rotten coconut animation once this method is run, beginning with a random x-value and the JFrame where the animation will be displayed.
     * Checks if the Captain touches the RottenCoconut object, and subtracts three points from the user's score if true.
     * If the Captain does not touch the RottenCoconut, the game continues.
     * Hence, this object can be avoided by the user.
     * Updates the frame 10 times each second using the repaint method.
     * @param rand the random x-coordinate at which the rotten coconut will be spawned.
     * @param frame the frame on which the rotten coconut dropping animation and rest of the game will be displayed.
     * @throws IOException handles the IOException, which is thrown if the Thread object does not compile.
     */
    public static void moveRotten(int rand, JFrame frame) {
        // updates the frame with the rotten coconut
        Captain.rottenX = rand;
        frame.repaint();
        boolean touch = false;

        // shows the rotten coconut dropping animation
        while (Captain.rottenY < Captain.capY) {
            Captain.rottenY += Captain.step;
            try {
                Thread.sleep(100);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            // checks if the Captain character touches the rotten coconut
            if ((Captain.rottenX < Captain.capX + 100) && (Captain.rottenX > Captain.capX - 100) && (Captain.rottenY < Captain.capY + 100) && (Captain.rottenY > Captain.capY - 100))
                touch = true;
            frame.repaint();
        }

        // if the captain collects the rotten coconut, the score decreases by three
        // nothing happens if the user does not collect this coconut
        if (touch)
            Captain.score -= 3;

        Captain.rottenX = 1600;
        frame.repaint();
    }

}
