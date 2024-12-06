/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group #9
 * 1 - 5026231131 - Davin Jonathan Tanus
 * 2 - 5026231115 - Komang Alit Pujangga
 * 3 - Student ID - Student Name 3
 */

package sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Puzzle {
    int[][] numbers = new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    boolean[][] isGiven = new boolean[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    private Random random = new Random();

    public Puzzle() {
        super();
    }

    public void newPuzzle(int cellsToGuess) {
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                numbers[row][col] = 0;
                isGiven[row][col] = false;
            }
        }

        if (generatePuzzle()) {
            shuffleGrid();

            int emptyCells = 0;
            while (emptyCells < cellsToGuess) {
                int row = (int) (Math.random() * SudokuConstants.GRID_SIZE);
                int col = (int) (Math.random() * SudokuConstants.GRID_SIZE);

                if (isGiven[row][col]) {
                    isGiven[row][col] = false;
                    emptyCells++;
                }
            }
        }
    }

    private boolean generatePuzzle() {
        return fillGrid(0, 0);
    }

    private boolean fillGrid(int row, int col) {
        if (row == SudokuConstants.GRID_SIZE) {
            return true;
        }

        if (col == SudokuConstants.GRID_SIZE) {
            return fillGrid(row + 1, 0);
        }

        ArrayList<Integer> numbersList = new ArrayList<>();
        for (int num = 1; num <= 9; num++) {
            numbersList.add(num);
        }
        Collections.shuffle(numbersList);

        for (int num : numbersList) {
            if (isSafe(row, col, num)) {
                numbers[row][col] = num;
                isGiven[row][col] = true;

                if (fillGrid(row, col + 1)) {
                    return true;
                }

                numbers[row][col] = 0;
                isGiven[row][col] = false;
            }
        }

        return false;
    }

    private boolean isSafe(int row, int col, int num) {
        for (int i = 0; i < SudokuConstants.GRID_SIZE; i++) {
            if (numbers[row][i] == num || numbers[i][col] == num) {
                return false;
            }
        }

        int subGridRow = row / SudokuConstants.SUBGRID_SIZE;
        int subGridCol = col / SudokuConstants.SUBGRID_SIZE;
        for (int i = subGridRow * SudokuConstants.SUBGRID_SIZE; i < (subGridRow + 1) * SudokuConstants.SUBGRID_SIZE; i++) {
            for (int j = subGridCol * SudokuConstants.SUBGRID_SIZE; j < (subGridCol + 1) * SudokuConstants.SUBGRID_SIZE; j++) {
                if (numbers[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private void shuffleGrid() {
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            nums.add(i);
        }

        for (int row = 0; row < SudokuConstants.GRID_SIZE; row++) {
            Collections.shuffle(nums);
            for (int col = 0; col < SudokuConstants.GRID_SIZE; col++) {
                int randomNum = nums.get(col);
                if (isSafe(row, col, randomNum)) {
                    numbers[row][col] = randomNum;
                }
            }
        }
    }

    public void printPuzzle() {
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                System.out.print(numbers[row][col] + " ");
            }
            System.out.println();
        }
    }
}
