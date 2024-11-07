package edu.grinnell.csc207.fliptiles;

/**
 * Game logic for give some name ! GAME
 * Controls game mechanics such as flipping tiles (for now) and checking for game completion.
 */
public class GameLogic {
    private GameBoard board;
    private int moveCount;
    private long startTime;

    /**
     * Initializes the game logic with the specified board size.
     * 
     * @param size The size of the game board (3x3, 4x4, 5x5).
     */
    public GameLogic(int size) {
        board = new GameBoard(size);
        moveCount = 0;
        startTime = System.currentTimeMillis();
    }

    /**
     * Makes a move at the specified row and column by flipping tiles.
     * 
     * @param row The row to flip.
     * @param col The column to flip.
     * @return True if the move is valid, otherwise false.
     */
    public boolean makeMove(int row, int col) {
        if (!board.isValidPosition(row, col)) {
            return false;
        }
        board.flipTiles(row, col);
        moveCount++;
        return true;
    }

    /**
     * Displays the current game board in ASCII format.
     */
    public void displayBoard() {
        board.display();
    }

    /**
     * Checks if all tiles have been flipped to the target state, completing the game.
     * 
     * @return True if the game is complete, otherwise false.
     */
    public boolean isGameComplete() {
        return board.allTilesFlipped();
    }

    /**
     * Gets the number of moves taken by the player.
     * 
     * @return The move count.
     */
    public int getMoveCount() {
        return moveCount;
    }

    /**
     * Gets the elapsed time since the start of the game in seconds.
     * 
     * @return The elapsed time in seconds.
     */
    public long getElapsedTimeInSeconds() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }
}
