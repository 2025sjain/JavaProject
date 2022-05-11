/**
 * If the LuckyCoconut object is to be spawned, this class is run.
 * Creates the blue coconut, which adds 3 points to the user's score if it is collected.
 * This object does not need to be collected, unlike the Coconut object, and nothing occurs if the user avoids this object.
 * Checks if the Captain character is touching the lucky coconut, to possibly add 3 points to the score.
 * Keeps updating the frame or screen of the game by running the repaint method of Java graphics.
 * @author Soham Jain, Shaurya Jain, Archi Marrapu
 * @version 1.0
 * @since 5/5/22
 */

import javax.swing.*;

public class LuckyCoconut {

    /**
     * Returns the x-coordinate of the lucky coconut if this method is run.
     * @return luckyX, or the x-value of the lucky coconut.
     */
    public static int getLuckyX() {
        return Captain.luckyX;
    }

    /**
     * Returns the y-coordinate of the lucky coconut if this method is run.
     * @return luckyY, or the y-value of the lucky coconut.
     */
    public static int getLuckyY() {
        return Captain.luckyY;
    }

    /**
     * Sets the x-coordinate of the lucky coconut if this method is run.
     * @param x the location where the new x-coordinate of the lucky coconut will be.
     */
    public static void setLuckyX(int x) {
        Captain.luckyX = x;
    }

    /**
     * Sets the y-coordinate of the lucky coconut if this method is run.
     * @param y the location where the new y-coordinate of the lucky coconut will be.
     */
    public static void setLuckyY(int y) {
        Captain.luckyY = y;
    }

    /**
     * Shows the lucky coconut animation once this method is run, beginning with a random x-value and the JFrame where the animation will be displayed.
     * Checks if the Captain touches the LuckyCoconut object, and adds three points to the user's score if true.
     * If the Captain does not touch the LuckyCoconut, the game continues.
     * Hence, nothing occurs if the user avoids this object.
     * Updates the frame 10 times each second using the repaint method.
     * @param rand the random x-coordinate at which the lucky coconut will be spawned.
     * @param frame the frame on which the lucky coconut dropping animation and rest of the game will be displayed.
     * @throws IOException handles the IOException, which is thrown if the Thread object does not compile.
     */
    public static void moveLucky(int rand, JFrame frame) {
        // updates the frame with the lucky coconut
        Captain.luckyX = rand;
        frame.repaint();
        boolean touch = false;

        // shows the lucky coconut dropping animation
        while (Captain.luckyY < Captain.capY) {
            Captain.luckyY += 45;           
            try {
                Thread.sleep(100);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            // checks if the Captain character touches the lucky coconut
            if ((Captain.luckyX < Captain.capX + 100) && (Captain.luckyX > Captain.capX - 100) && (Captain.luckyY < Captain.capY + 100) && (Captain.luckyY > Captain.capY - 100))
                touch = true;
            frame.repaint();
        }

        // if the captain collects the lucky coconut, the score increases by three
        // nothing happens if the user does not collect this coconut
        if (touch)
            Captain.score += 3;

        Captain.luckyX = 1600;
        frame.repaint();
    }

}
