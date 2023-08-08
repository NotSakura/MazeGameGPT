package main;

import javax.swing.*;
import java.awt.*;


/**
 * The entry point of the Swing Maze Game application.
 */
public class Main {
    /**
     * The main method that launches the Swing Maze Game application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }


    /**
     * Creates and displays the Swing Maze Game GUI.
     */
    private static void createAndShowGUI() {
        SwingMazeGameUI mazeGameUI = new SwingMazeGameUI();
        int mazeSize = SwingMazeGameUI.MAZE_SIZE * SwingMazeGameUI.CELL_SIZE;
        int padding = 30; // Adjust padding for visual balance
        int timerHeight = 30; // Adjust timer height for visual balance
        int frameSize = mazeSize + padding;

        JFrame frame = new JFrame("Swing Maze Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mazeGameUI);
        frame.setPreferredSize(new Dimension(frameSize, frameSize + timerHeight));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}