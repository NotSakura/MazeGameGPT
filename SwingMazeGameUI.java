package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The user interface for the Maze Game.
 */
public class SwingMazeGameUI extends JPanel implements KeyListener {
    public static final int MAZE_SIZE = 15;
    public static final int CELL_SIZE = 30;

    private MazeGame mazeGame;
    private boolean isGameStarted;

    /**
     * Constructs the SwingMazeGameUI.
     */
    public SwingMazeGameUI() {
        mazeGame = new MazeGame();
        isGameStarted = false;
        addKeyListener(this);
        setFocusable(true);
        showMainMenu();
    }

    /**
     * Displays the main menu with buttons.
     */
    private void showMainMenu() {
        removeAll();
        setLayout(new GridLayout(3, 1));

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        add(startButton);

        JButton instructionsButton = new JButton("Instructions");
        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInstructions();
            }
        });
        add(instructionsButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(exitButton);

        revalidate();
        repaint();
    }

    /**
     * Starts a new game when the "Start Game" button is clicked.
     */
    private void startGame() {
        isGameStarted = true;
        mazeGame.startNewGame(); // Start a new game
        requestFocusInWindow();
        removeAll(); // Clear the UI
        revalidate();
        repaint();
    }

    /**
     * Displays the game instructions when the "Instructions" button is clicked.
     */
    private void showInstructions() {
        // Display game instructions
        String instructions = "Instructions:\n\n" +
                "Use Arrow Keys to move the player (@) in the maze.\n" +
                "Reach the green 'E' block to win the game.\n" +
                "Press 'Q' to exit the game.\n" +
                "Have fun!";
        JOptionPane.showMessageDialog(this, instructions, "Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Draws a single cell of the maze at the specified coordinates with the provided cell type.
     * Sets the appropriate color based on the cell type and draws a filled rectangle representing the cell.
     * Additionally, draws a black border around the rectangle.
     *
     * @param g    the Graphics object to draw on
     * @param x    the x-coordinate of the cell's top-left corner
     * @param y    the y-coordinate of the cell's top-left corner
     * @param cell the type of cell to be drawn (WALL, PLAYER, EXIT, or PATH)
     */
    private void drawCell(Graphics g, int x, int y, char cell) {
        Color color;
        switch (cell) {
            case MazeGame.WALL:
                color = Color.BLACK;
                break;
            case MazeGame.PLAYER:
                color = Color.RED;
                break;
            case MazeGame.EXIT:
                color = Color.GREEN;
                break;
            default:
                color = Color.WHITE;
                break;
        }

        g.setColor(color);
        g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
    }

    /**
     * Draws the maze cells and the timer display.
     *
     * @param g the Graphics object to draw on
     */
    private void drawMaze(Graphics g) {
        if (!isGameStarted) {
            return;
        }

        char[][] maze = mazeGame.getMaze();
        for (int i = 0; i < MAZE_SIZE; i++) {
            for (int j = 0; j < MAZE_SIZE; j++) {
                char cell = maze[i][j];
                int x = j * CELL_SIZE;
                int y = i * CELL_SIZE;
                drawCell(g, x, y, cell);
            }
        }

        // Draw the timer
        g.setColor(Color.BLACK);
        int timerY = MAZE_SIZE * CELL_SIZE + 20;
        g.drawString("Time: " + mazeGame.getTimeInSeconds() + " seconds", 10, timerY);
    }



    /**
     * Overrides the paintComponent method to draw the maze and other UI elements.
     *
     * @param g The Graphics context to draw on.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMaze(g);
    }

    /**
     * Handles key press events when keys are pressed during the game.
     *
     * @param e The KeyEvent that represents the key press.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (isGameStarted) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    mazeGame.movePlayer(-1, 0);
                    break;
                case KeyEvent.VK_DOWN:
                    mazeGame.movePlayer(1, 0);
                    break;
                case KeyEvent.VK_LEFT:
                    mazeGame.movePlayer(0, -1);
                    break;
                case KeyEvent.VK_RIGHT:
                    mazeGame.movePlayer(0, 1);
                    break;
                case KeyEvent.VK_Q:
                    System.exit(0);
                    break;
            }

            if (mazeGame.isGameWon()) {
                mazeGame.startNewGame();
            }

            repaint();
        }
    }

    /**
     * Handles key typed events (not used in this implementation).
     *
     * @param e The KeyEvent that represents the key typed.
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Handles key release events (not used in this implementation).
     *
     * @param e The KeyEvent that represents the key release.
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }
}