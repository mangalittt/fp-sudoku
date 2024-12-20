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
package TICTACTOE;

/**
 *
 * @author micle
 */
/**
 * Abstract superclass for all AI players with different strategies.
 * To construct an AI player:
 * 1. Construct an instance (of its subclass) with the game Board
 * 2. Call setSeed() to set the computer's seed
 * 3. Call move() which returns the next move in an int[2] array of {row, col}.
 *
 * The implementation subclasses need to override abstract method move().
 * They shall not modify Cell[][], i.e., no side effect expected.
 * Assume that next move is available, i.e., not game-over yet.
 */
public abstract class AIPlayer {
   protected int ROWS = Board.ROWS;  // number of rows
   protected int COLS = Board.COLS;  // number of columns
 
   protected Cell[][] cells; // the board's ROWS-by-COLS array of Cells
   protected Seed mySeed;    // computer's seed
   protected Seed oppSeed;   // opponent's seed
 
   /** Constructor with reference to game board */
   public AIPlayer(Board board) {
      cells = board.cells;
   }
 
   /** Set/change the seed used by computer and opponent */
   public void setSeed(Seed seed) {
      this.mySeed = seed;
      oppSeed = (mySeed == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
   }
 
   /** Abstract method to get next move. Return int[2] of {row, col} */
   abstract int[] move();  // to be implemented by subclasses
}
