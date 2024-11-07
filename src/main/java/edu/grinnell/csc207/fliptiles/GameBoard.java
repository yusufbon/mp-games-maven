package edu.grinnell.csc207.fliptiles;

import edu.grinnell.csc207.util.MatrixV0;

/**
 * Represents the game board for GAME
 * Manages tile flipping patterns and checks for game completion.
 */
public class GameBoard {
    private MatrixV0<Boolean> board;
    private int size;

    /**
     * Initializes the game board with all tiles set to false (off).
     * 
     * @param size The size of the board (3x3, 4x4, 5x5).
     */
    public GameBoard(int size) {
        this.size = size;
        board = new MatrixV0<>(size, size, false);
    }

    /**
     * Flips tiles based on the selected tile's position.
     * Implements flipping rules for the selected tile.
     * 
     * @param row The row of the tile to flip.
     * @param col The column of the tile to flip.
     */
    public void flipTiles(int row, int col) {
        flipTile(row, col); // Flip the selected tile

        // Flip neighboring tiles based on position
        if (row > 0) flipTile(row - 1, col);        // Above
        if (row < size - 1) flipTile(row + 1, col); // Below
        if (col > 0) flipTile(row, col - 1);        // Left
        if (col < size - 1) flipTile(row, col + 1); // Right

        // Flip corners or edges depending on rules
        if (size > 3) {
            if (row > 0 && col > 0) flipTile(row - 1, col - 1);             // Top-left corner
            if (row > 0 && col < size - 1) flipTile(row - 1, col + 1);     // Top-right corner
            if (row < size - 1 && col > 0) flipTile(row + 1, col - 1);     // Bottom-left corner
            if (row < size - 1 && col < size - 1) flipTile(row + 1, col + 1); // Bottom-right corner
        }
    }

    /**
     * Flips a tile at the specified position.
     * 
     * @param row The row of the tile to flip.
     * @param col The column of the tile to flip.
     */
    private void flipTile(int row, int col) {
        board.set(row, col, !board.get(row, col));
    }

    /**
     * Checks if all tiles on the board are flipped to true.
     * 
     * @return True if all tiles are flipped, otherwise false.
     */
    public boolean allTilesFlipped() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!board.get(i, j)) return false;
            }
        }
        return true;
    }

    /**
     * Displays the current board state in ASCII format.
     */
    public void display() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board.get(i, j) ? "1 " : "0 ");
            }
            System.out.println();
        }
    }

    /**
     * Checks if a position is valid on the board.
     * 
     * @param row The row index.
     * @param col The column index.
     * @return True if the position is valid, otherwise false.
     */
    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }
}
