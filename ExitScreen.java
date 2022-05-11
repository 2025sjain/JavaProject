/**
 * Once the game is over, this class is run.
 * The ExitScreen class creates a new frame that displays the user's score.
 * There are two buttons which are visible on this screen, one to show the leaderboard and one to quit the game.
 * If the button to quit the game is pressed, the System exit command is run.
 * However, if the user chooses to display the leaderboard, the frame refreshes and displays the leaderbaord using the data in leaderbaord.txt.
 * The leaderboard screen also displays what rank the user placed based on other scores that are stored in the leaderboard.txt file.
 * From this screen, the user can press another button to quit the game.
 * This is the final class in the Captain Coconut game.
 * @author Soham Jain, Shaurya Jain, Archi Marrapu
 * @version 1.0
 * @since 5/5/22
 */

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;

public class ExitScreen {

    // the new frame on which the exit screen will be displayed
    public static JFrame newFrame = new JFrame("Exit Screen");

    /**
     * Creates a frame which displays the user's score on the center of the screen.
     * Creates two buttons, which can be pressed to display the leaderboard or exit the game.
     * Writes the user's score into the leaderboard.txt file before displaying it.
     * Therefore, the user can see where they placed compared to other scores on the leaderboard.
     * This method implements JLabels and JPanels to display text onto the new JFrame.
     * @param frame the frame which will be disposed in the beginning of this method.
     * @throws IOException handles the IOException, which is thrown if the leaderboard.txt file is not found.
     */
    public static void showScore(JFrame frame) throws IOException {
        // disposes of the old frame, and creates a new one to display the score
        frame.dispose();
        newFrame.setSize(1600, 800);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel textLabel;

        // writes the user's final score in the center of the screen
        if (Captain.score == 1) {
            textLabel = new JLabel("<html><br><br><br><br><br>Your final score was...<br><br>&emsp;&emsp;&emsp;" + Captain.score + " point!<br><br></html>", SwingConstants.CENTER);
        } else {
            textLabel = new JLabel("<html><br><br><br><br><br>Your final score was... <br><br>&emsp;&emsp;&emsp;" + Captain.score + " points!<br><br></html>", SwingConstants.CENTER);
        }

        textLabel.setFont(new Font("Montserrat", Font.BOLD, 45));
        JPanel exitPanel = new JPanel();
        exitPanel.setSize(1600, 300);
        exitPanel.setBackground(new Color(231, 215, 190));
        exitPanel.add(textLabel);

        exitPanel.setLocation(0, 300);
        newFrame.getContentPane().add(exitPanel, BorderLayout.CENTER);

        // writes the user's score into the leaderboard.txt filee
        try {
            Files.write(Paths.get("leaderboard.txt"), ("" + Captain.score).getBytes(), StandardOpenOption.APPEND);
            Files.write(Paths.get("leaderboard.txt"), "\n".getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.exit(0);
        }

        // creates the two buttons to show the leaderboard or quit the game
        // uses two ActionListeners, which are defined later in the program
        JButton end = new JButton("Click here to quit");
        JButton lb = new JButton("Click Here To Display Leaderboard");
        lb.setFont(new FontUIResource("Times_Roman", Font.BOLD, 20));
        lb.setPreferredSize(new Dimension(450, 225));
        lb.addActionListener(new Listener2());

        end.setFont(new FontUIResource("Times_Roman", Font.BOLD, 20));
        end.setPreferredSize(new Dimension(450, 225));
        end.addActionListener(new Listener1());
        newFrame.getContentPane().add(end, BorderLayout.EAST);
        newFrame.getContentPane().add(lb, BorderLayout.WEST);

        // sets the frame to be visible
        newFrame.setVisible(true);
    }

    /**
     * The first listener class is used to quit the game.
     * This class implements the ActionListener class, and performs the System exit command when run.
     * Listener1 is used in the "quit" buttons of the project.
     */
    private static class Listener1 implements ActionListener {
        /**
         * This method runs the action that is performed when the Listener1 class is run.
         * Exits the System and completes the game.
         * @param e the ActionEvent of when the "quit" button is pressed.
         */
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    /**
     * The second listener class is used to display the leaderbaord.
     * Uses the data in leaderboard.txt to show the top scores.
     * Also displays the rank at which the user places compared to other scores.
     * Sorts the scores in leaderboard.txt using arrays.
     * Displays another button which uses Listener1 to quit the game.
     */
    private static class Listener2 implements ActionListener {

        /**
         * The action that is performed if the Listener2 class is run.
         * Displays the leaderboard with the scores in order.
         * Also displays the rank at which the user has placed, compared to other scores on the leaderboard.
         * The top scores is updated with the user's score beforehand.
         * Displays a button which can be pressed to quit the game.
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            // removes everything from the previous frame
            newFrame.getContentPane().removeAll();
            newFrame.repaint();
            JPanel leaderboard = new JPanel();

            // reads values from the leaderbaord.txt file
            BufferedReader bf = null;
            try {
                bf = new BufferedReader (new FileReader("leaderboard.txt"));
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }

            // finds the size of the file
            int size = 0;
            try {
                String line = bf.readLine();
                while (line != null) {
                    size++;
                    line = bf.readLine();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            // creates an integer array using the size that was previously found
            int[] scores = new int[size];

            // uses Scanner infile to read data from the file, which contains the user's score
            Scanner infile = null;
            try {
                infile = new Scanner(new File("leaderboard.txt"));
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            for (int k = 0; k < size; k++) {
                scores[k] = Integer.parseInt(infile.next());
            }

            // sorts the array containing the scores in the leaderbaord.txt file
            // however, the scores are from least to greatest, so they need to be reversed
            Arrays.sort(scores);

            // reverses the array
            int[] sortedScores = new int[size];
            for (int i = 0; i < size; i++) {
                sortedScores[i] = scores[size - i - 1];
            }

            // determines the user's rank based on previous scores in the leaderboard
            int place = 0;
            for (int i = 0; i < size; i++) {
                if (sortedScores[i] == Captain.score) {
                    place = i + 1;
                }
            }

            // creates a string to output the leaderboard and rank
            String str = "<html>";
            for (int i = 0; i < sortedScores.length; i++) {
                str += "<br>#" + (i + 1) + ": "+ sortedScores[i] + " points";
            }
            str += "<br><br>You placed " + place + " out of " + size + "! </html>";

            JLabel l = new JLabel("<html><br>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;LEADERBOARD<br>----------------------------------<br>" + str + " </html>", SwingConstants.CENTER);
            l.setSize(1600, 800);
            l.setFont(new Font("Montserrat", Font.BOLD, 30));
           
            leaderboard.setLocation(0,0);
            leaderboard.setBackground(Color.yellow);
            leaderboard.add(l);
            leaderboard.setSize(1400, 800);
            newFrame.getContentPane().add(leaderboard, BorderLayout.CENTER);

            // creates a button to quit the game if pressed
            JButton quit = new JButton("Click here to quit");
            quit.setFont(new FontUIResource("Times_Roman", Font.BOLD, 20));
            quit.setSize(200, 800);
            quit.addActionListener(new Listener1());
            newFrame.getContentPane().add(quit, BorderLayout.EAST);

            // updates the frame
            newFrame.repaint();

        }
    }

}
