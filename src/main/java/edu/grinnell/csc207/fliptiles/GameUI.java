package edu.grinnell.csc207.fliptiles;

import java.util.Scanner;

/**
 * User interface for GAME (add here)
 * Manages game flow, user input, and displays game state.
 */
public class GameUI {
    private GameLogic game;
    private Scanner scanner;

    /**
     * Initializes the game UI with a new scanner.
     */
    public GameUI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Starts the game, allows player to select difficulty, 
     * and handles the main game loop.
     */
    public void startGame() {
        System.out.println("Welcome to GAMEE!");
        System.out.println("Select difficulty: 1 (3x3), 2 (4x4), or 3 (5x5)");
        
        int difficulty = scanner.nextInt();
        int size = (difficulty == 1) ? 3 : (difficulty == 2) ? 4 : 5;

        game = new GameLogic(size);

        System.out.println("Flip all the tiles to complete the game. Enter row and column to flip.");

        while (!game.isGameComplete()) {
            game.displayBoard();
            System.out.print("Enter row: ");
            int row = scanner.nextInt();
            System.out.print("Enter col: ");
            int col = scanner.nextInt();

            if (!game.makeMove(row, col)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }
        }

        System.out.println("Congratulations! You've completed the game.");
        System.out.println("Moves taken: " + game.getMoveCount());
        System.out.println("Time taken: " + game.getElapsedTimeInSeconds() + " seconds.");
    }
    
    /**
     * Main method to start the game.
     * 
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        GameUI ui = new GameUI();
        ui.startGame();
    }
}
