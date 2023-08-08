# Swing Maze Game

Welcome to Swing Maze Game! This is a simple maze game implemented in Java using Swing for the graphical user interface. The objective of the game is to navigate the player character through a randomly generated maze to reach the exit.

## Features

- Randomly generated mazes for a unique gameplay experience each time.
- Player movement using arrow keys to navigate the maze.
- Win condition: Reach the exit to win the game.
- Timer: The game tracks the time taken to complete the maze.
- Main menu: Start the game, read instructions, or exit the application.

## User Stories

1. As a player, I want to be able to start a new game with a randomly generated maze.
2. As a player, I want to move the character using arrow keys to navigate through the maze.
3. As a player, I want to see a timer that tracks the time taken to complete the maze.
4. As a player, I want to reach the exit of the maze to win the game.
5. As a player, I want to read instructions on how to play the game.

## Design Patterns

The Swing Maze Game project follows the Clean Architecture principles, separating the codebase into different layers:

1.Model-View-Controller (MVC) Pattern:
  The code follows the MVC pattern, which is a widely used design pattern for developing user interfaces. In this pattern:
  
  The MazeGame class acts as the model, representing the core logic and entities of the maze game.
  The SwingMazeGameUI class acts as the view, handling the user interface and visual representation of the maze game using Swing components.
  The Main class acts as the controller, gluing the model and view together to run the application.
  The separation of concerns achieved by following the MVC pattern makes the code more organized and maintainable.
  
2.Factory Method Pattern (Creational Pattern):
  Although not explicitly implemented as a separate class or interface, the MazeGame class can be seen as an example of the Factory Method pattern. The       generateMaze method in the MazeGame class acts as a factory method that encapsulates the creation of the maze. The factory method abstracts the maze generation process, allowing subclasses or variations to provide different implementations. This flexibility is demonstrated by the use of generateMazeRecursive for maze generation in the MazeGame class.
  
3. Observer Pattern (Behavioral Pattern):
  The SwingMazeGameUI class implements the Observer pattern through the use of event listeners. The KeyListener interface is used to listen for keyboard input, and the game state is updated accordingly based on the user's key presses. This decouples the user input from the maze game logic and allows for more flexibility in   handling different types of user interactions.

## Accessibility Features

- **Keyboard Navigation:** The game can be played entirely using the keyboard, making it accessible to users who may have difficulty using a mouse.
- **High Contrast:** The game uses high contrast colors to ensure visibility and readability, catering to users with visual impairments.
- **Perceptible Information:** The game includes an "Instructions" button that provides clear and concise instructions on how to play, ensuring that all users can understand the game mechanics.

## Getting Started

1. Open the project in your Java IDE.`
2. Compile and run the `Main` class to launch the game.
3. Use the arrow keys to navigate through the maze and reach the exit.
4. Press 'Q' to exit the game.

Used JUnit.jupiter testing (might need to download) and JDK 11 used. 


