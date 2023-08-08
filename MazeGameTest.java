package test;

import main.Main;
import main.MazeGame;
import main.SwingMazeGameUI;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MazeGameTest {
    private MazeGame mazeGame;

    @BeforeEach
    public void setUp() {
        mazeGame = new MazeGame();
    }

    @Test
    public void testGenerateMaze() {
        mazeGame.generateMaze();
        char[][] maze = mazeGame.getMaze();
        assertNotNull(maze);
        assertTrue(maze.length > 0);
        assertTrue(maze[0].length > 0);
    }

    @Test
    public void testMovePlayerValidMove() {
        mazeGame.startNewGame();
        int initialX = mazeGame.getPlayerX();
        int initialY = mazeGame.getPlayerY();
        assertTrue(mazeGame.movePlayer(0, 1));
        assertEquals(initialX, mazeGame.getPlayerX());
        assertEquals(initialY + 1, mazeGame.getPlayerY());
    }

    @Test
    public void testMovePlayerInvalidMove() {
        mazeGame.startNewGame();
        int initialX = mazeGame.getPlayerX();
        int initialY = mazeGame.getPlayerY();
        assertFalse(mazeGame.movePlayer(-1, 0)); // Trying to move into a wall
        assertEquals(initialX, mazeGame.getPlayerX());
        assertEquals(initialY, mazeGame.getPlayerY());
    }

    @Test
    public void testTimeInSeconds() throws InterruptedException {
        mazeGame.startNewGame();
        Thread.sleep(1000); // Wait for 1 second
        assertTrue(mazeGame.getTimeInSeconds() >= 1);
        Thread.sleep(2000); // Wait for 2 more seconds
        assertTrue(mazeGame.getTimeInSeconds() >= 3);
    }
}
