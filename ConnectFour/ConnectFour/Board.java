/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group #9
 * 1 - 5026231131 - Davin Jonathan Tanus
 * 2 - 5026231115 - Komang Alit Pujangga
 * 3 - Student ID - Jeremy Danial Haposan Siregar
 */










/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConnectFour;

/**
 *
 * @author micle
 */
/**
 * The Board class models the TTT game-board of 3x3 cells.
 */
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

   
   public State stepGame(Seed player, int selectedRow, int selectedCol) {
      cells[selectedRow][selectedCol].content = player;
      if (hasWon(player, selectedRow, selectedCol)) {
         return (player == Seed.CROSS) ? State.CROSS_WON : State.NOUGHT_WON;
      } else {
         for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
               if (cells[row][col].content == Seed.NO_SEED) {
                  return State.PLAYING;
               }
            }
         }return State.DRAW;
      }
   }
   
   public boolean hasWon(Seed theSeed, int rowSelected, int colSelected) {
     
      int count = 0;
      for (int col = 0; col < COLS; ++col) {
         if (cells[rowSelected][col].content == theSeed) {
            ++count;
            if (count == 4) return true;
         } else {
            count = 0;
         }
      }

      // column
      count = 0;
      for (int row = 0; row < ROWS; ++row) {
         if (cells[row][colSelected].content == theSeed) {
            ++count;
            if (count == 4) return true;
         } else {
            count = 0;
         }
      }

      //diagonal (kiri atas to kanan bawh)
      count = 0;
      for (int i = -Math.min(rowSelected, colSelected); i < Math.min(ROWS - rowSelected, COLS - colSelected); i++) {
         if (rowSelected + i >= 0 && rowSelected + i < ROWS && colSelected + i >= 0 && colSelected + i < COLS
               && cells[rowSelected + i][colSelected + i].content == theSeed) {
            ++count;
            if (count == 4) return true;
         } else {
            count = 0;
         }
      }

      // Check diagonal (kiri bawah to kanan atas)
      count = 0;
      for (int i = -Math.min(rowSelected, COLS - 1 - colSelected); i < Math.min(ROWS - rowSelected, colSelected + 1); i++) {
         if (rowSelected + i >= 0 && rowSelected + i < ROWS && colSelected - i >= 0 && colSelected - i < COLS
               && cells[rowSelected + i][colSelected - i].content == theSeed) {
            ++count;
            if (count == 4) return true;
         } else {
            count = 0;
         }
      }

      return false;
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