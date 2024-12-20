/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group #9
 * 1 - 5026231131 - Davin Jonathan Tanus
 * 2 - 5026231115 - Komang Alit Pujangga
 * 3 - Student ID - Jeremy Danial Haposan Siregar
 */



package connectFour;

import java.awt.*;
/**
 * The Board class models the ROWS-by-COLS game board.
 */
public class Board {
    // Define named constants
    public static final int ROWS = 6;  // ROWS x COLS cells
    public static final int COLS = 7;
    // Define named constants for drawing
    public static final int CANVAS_WIDTH = Cell.SIZE * COLS;  // the drawing canvas
    public static final int CANVAS_HEIGHT = Cell.SIZE * ROWS;
    public static final int GRID_WIDTH = 8;  // Grid-line's width
    public static final int GRID_WIDTH_HALF = GRID_WIDTH / 2; // Grid-line's half-width
    public static final Color COLOR_GRID = Color.LIGHT_GRAY;  // grid lines
    public static final int Y_OFFSET = 1;  // Fine tune for better display

    // Define properties (package-visible)
    /** Composes of 2D array of ROWS-by-COLS Cell instances */
    Cell[][] cells;

    /** Constructor to initialize the game board */
    public Board() {
        initGame();
    }

    /** Initialize the game objects (run once) */
    public void initGame() {
        cells = new Cell[ROWS][COLS]; // allocate the array
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                // Allocate element of the array
                cells[row][col] = new Cell(row, col);
                // Cells are initialized in the constructor
            }
        }
    }

    /** Reset the game board, ready for new game */
    public void newGame() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col].newGame(); // clear the cell content
            }
        }
    }

    /**
     *  The given player makes a move on (selectedRow, selectedCol).
     *  Update cells[selectedRow][selectedCol]. Compute and return the
     *  new game state (PLAYING, DRAW, CROSS_WON, NOUGHT_WON).
     */
    public State stepGame(Seed player, int row, int col) {
        cells[row][col].content = player;
        if (hasWon(player, row, col)) {
            return (player == Seed.CROSS) ? State.CROSS_WON : State.NOUGHT_WON;
        } else if (isDraw()) {
            return State.DRAW;
        } else {
            return State.PLAYING;
        }
    }

    public boolean hasWon(Seed theSeed, int row, int col) { // MODIFIED: Cek kemenangan
        int count = 0;
        for (int c = 0; c < COLS; ++c) {
            if (cells[row][c].content == theSeed) {
                ++count;
                if (count == 4) return true;
            } else {
                count = 0;
            }
        }
        count = 0;
        for (int r = 0; r < ROWS; ++r) {
            if (cells[r][col].content == theSeed) {
                ++count;
                if (count == 4) return true;
            } else {
                count = 0;
            }
        }
        count = 0;
        for (int d = -3; d <= 3; ++d) {
            int r = row + d, c = col + d;
            if (r >= 0 && r < ROWS && c >= 0 && c < COLS && cells[r][c].content == theSeed) {
                ++count;
                if (count == 4) return true;
            } else {
                count = 0;
            }
        }
        count = 0;
        for (int d = -3; d <= 3; ++d) {
            int r = row + d, c = col - d;
            if (r >= 0 && r < ROWS && c >= 0 && c < COLS && cells[r][c].content == theSeed) {
                ++count;
                if (count == 4) return true;
            } else {
                count = 0;
            }
        }
        return false;
    }

    public boolean isDraw() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (cells[row][col].content == Seed.NO_SEED) {
                    return false;
                }
            }
        }
        return true;
    }


    /** Paint itself on the graphics canvas, given the Graphics context */
    public void paint(Graphics g) {
        // Draw the grid-lines
        g.setColor(COLOR_GRID);
        for (int row = 1; row < ROWS; ++row) {
            g.fillRoundRect(0, Cell.SIZE * row - GRID_WIDTH_HALF,
                    CANVAS_WIDTH - 1, GRID_WIDTH,
                    GRID_WIDTH, GRID_WIDTH);
        }
        for (int col = 1; col < COLS; ++col) {
            g.fillRoundRect(Cell.SIZE * col - GRID_WIDTH_HALF, 0 + Y_OFFSET,
                    GRID_WIDTH, CANVAS_HEIGHT - 1,
                    GRID_WIDTH, GRID_WIDTH);
        }

        // Draw all the cells
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col].paint(g);  // ask the cell to paint itself
            }
        }
    }
}
