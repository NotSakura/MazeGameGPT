package main;

import java.util.Random;

/**
 * Represents the Maze Game logic and state.
 */
public class MazeGame {
    public static final char PLAYER = '@';
    public static final char EXIT = 'E';
    public static final char WALL = '#';
    public static final char PATH = ' ';

    private static final int MAZE_SIZE = 15;

    private char[][] maze;
    private int playerX;
    private int playerY;
    private boolean gameWon;

    private long startTimeMillis;
    private boolean isGameStarted;

    /**
     * Constructs a new instance of MazeGame.
     */
    public MazeGame() {
        maze = new char[MAZE_SIZE][MAZE_SIZE];
    }

    /**
     * Starts a new game by generating a maze and initializing game state.
     */
    public void startNewGame() {
        generateMaze();
        gameWon = false;
        startTimeMillis = System.currentTimeMillis();
        isGameStarted = true;

    }

    /**
     * Generates a new maze.
     */
    public void generateMaze() {
        for (int i = 0; i < MAZE_SIZE; i++) {
            for (int j = 0; j < MAZE_SIZE; j++) {
                maze[i][j] = WALL;
            }
        }

        Random random = new Random();
        int startX = random.nextInt(MAZE_SIZE);
        int startY = random.nextInt(MAZE_SIZE);
        maze[startX][startY] = PATH;
        playerX = startX;
        playerY = startY;

        generateMazeRecursive(startX, startY, random);

        int exitX, exitY;
        do {
            exitX = random.nextInt(MAZE_SIZE);
            exitY = random.nextInt(MAZE_SIZE);
        } while ((exitX == startX && exitY == startY) || maze[exitX][exitY] == WALL);
        maze[exitX][exitY] = EXIT;
        maze[playerX][playerY] = PLAYER; // Set player position after generating maze
    }

    /**
     * Generates a maze recursively using depth-first search.
     */
    private void generateMazeRecursive(int x, int y, Random random) {
        int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        for (int i = 0; i < 4; i++) {
            int nextX = x + directions[i][0] * 2;
            int nextY = y + directions[i][1] * 2;
            if (nextX >= 0 && nextX < MAZE_SIZE && nextY >= 0 && nextY < MAZE_SIZE && maze[nextX][nextY] == WALL) {
                maze[nextX][nextY] = PATH;
                maze[x + directions[i][0]][y + directions[i][1]] = PATH;
                generateMazeRecursive(nextX, nextY, random);
            }
        }
    }

    /**
     * Generates a maze recursively using depth-first search.
     */
    public char[][] getMaze() {
        return maze;
    }

    /**
     * Gets the current maze layout.
     *
     * @return The 2D char array representing the maze.
     */
    public int getPlayerX() {
        return playerX;
    }

    /**
     * Gets the current X-coordinate of the player.
     *
     * @return The X-coordinate of the player.
     */
    public int getPlayerY() {
        return playerY;
    }


    /**
     * Gets the current Y-coordinate of the player.
     *
     * @return The Y-coordinate of the player.
     */
    public boolean movePlayer(int dx, int dy) {
        int newX = playerX + dx;
        int newY = playerY + dy;

        if (isMoveValid(newX, newY)) {
            maze[playerX][playerY] = PATH;
            playerX = newX;
            playerY = newY;

            if (maze[playerX][playerY] == EXIT) {
                gameWon = true;
                // Implement game won logic here if needed
            }

            maze[playerX][playerY] = PLAYER;
            return true;
        }

        return false;
    }

    /**
     * Moves the player within the maze.
     *
     * @param dx The change in X-coordinate.
     * @param dy The change in Y-coordinate.
     * @return True if the player's move is valid, otherwise false.
     */
    private boolean isMoveValid(int x, int y) {
        return x >= 0 && x < MAZE_SIZE && y >= 0 && y < MAZE_SIZE && maze[x][y] != WALL;
    }

    /**
     * Checks if the game has been won.
     *
     * @return True if the player has reached the exit, otherwise false.
     */
    public boolean isGameWon() {
        return gameWon;
    }

    /**
     * Gets the elapsed time since the game started in seconds.
     *
     * @return The time in seconds.
     */
    public int getTimeInSeconds() {
        if (!isGameStarted) {
            return 0; // Game not started yet
        }
        long currentTimeMillis = System.currentTimeMillis();
        return (int) ((currentTimeMillis - startTimeMillis) / 1000);
    }
}