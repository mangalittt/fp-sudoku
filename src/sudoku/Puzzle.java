package sudoku;

import java.util.Random;

/**
 * The Sudoku number puzzle to be solved
 */
public class Puzzle {
    int[][] numbers = new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    boolean[][] isGiven = new boolean[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    private Random random = new Random();

    public Puzzle() {
        super();
    }

    public void newPuzzle(int cellsToGuess) {
        // Puzzle tetap sama untuk testing
        int[][] hardcodedNumbers =
                {{5, 3, 4, 6, 7, 8, 9, 1, 2},
                        {6, 7, 2, 1, 9, 5, 3, 4, 8},
                        {1, 9, 8, 3, 4, 2, 5, 6, 7},
                        {8, 5, 9, 7, 6, 1, 4, 2, 3},
                        {4, 2, 6, 8, 5, 3, 7, 9, 1},
                        {7, 1, 3, 9, 2, 4, 8, 5, 6},
                        {9, 6, 1, 5, 3, 7, 2, 8, 4},
                        {2, 8, 7, 4, 1, 9, 6, 3, 5},
                        {3, 4, 5, 2, 8, 6, 1, 7, 9}};

        // Salin angka ke puzzle
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                numbers[row][col] = hardcodedNumbers[row][col];
                isGiven[row][col] = true; // Default semua kotak diberikan
            }
        }

        // Buat kotak kosong secara acak
        int emptyCells = 0;
        while (emptyCells < cellsToGuess) {
            int row = (int) (Math.random() * SudokuConstants.GRID_SIZE);
            int col = (int) (Math.random() * SudokuConstants.GRID_SIZE);

            // Jika kotak ini belum kosong, kosongkan
            if (isGiven[row][col]) {
                isGiven[row][col] = false; // Tandai sebagai kotak kosong
                emptyCells++;
            }
        }
    }
}
