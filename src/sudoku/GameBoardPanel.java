/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group #9
 * 1 - 5026231131 - Davin Jonathan Tanus
 * 2 - 5026231115 - Komang Alit Pujangga
 * 3 - Student ID - Jeremy Danial Haposan Siregar
 */
package sudoku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameBoardPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public static final int CELL_SIZE = 60;
    public static final int BOARD_WIDTH  = CELL_SIZE * SudokuConstants.GRID_SIZE;
    public static final int BOARD_HEIGHT = CELL_SIZE * SudokuConstants.GRID_SIZE;

    private Cell[][] cells = new Cell[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    private Puzzle puzzle = new Puzzle();
    private JLabel remainingLabel;

    public GameBoardPanel() {
        super.setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(SudokuConstants.GRID_SIZE, SudokuConstants.GRID_SIZE));

        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                cells[row][col] = new Cell(row, col);
                boardPanel.add(cells[row][col]);
            }
        }

        remainingLabel = new JLabel("Remaining: 81", JLabel.CENTER);
        remainingLabel.setFont(new Font("Arial", Font.BOLD, 16));
        remainingLabel.setPreferredSize(new Dimension(BOARD_WIDTH, 30));

        super.add(remainingLabel, BorderLayout.NORTH);
        super.add(boardPanel, BorderLayout.CENTER);

        CellInputListener listener = new CellInputListener();
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                if (cells[row][col].isEditable()) {
                    cells[row][col].addActionListener(listener);
                }
            }
        }

        super.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT + 30));
    }

    public void newGame(int cellsToGuess) {
        puzzle.newPuzzle(cellsToGuess);

        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                cells[row][col].newGame(puzzle.numbers[row][col], puzzle.isGiven[row][col]);
            }
        }
        updateRemainingCells();
    }

    public void resetInputs() {
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                if (!cells[row][col].isGiven()) {
                    cells[row][col].setText("");
                    cells[row][col].resetStatus();
                }
            }
        }
        updateRemainingCells();
    }

    public boolean isSolved() {
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                if (cells[row][col].status == CellStatus.TO_GUESS || cells[row][col].status == CellStatus.WRONG_GUESS) {
                    return false;
                }
            }
        }
        return true;
    }

    private void updateRemainingCells() {
        int remainingCells = 0;
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                if (cells[row][col].status == CellStatus.TO_GUESS) {
                    remainingCells++;
                }
            }
        }
        remainingLabel.setText("Remaining: " + remainingCells);
    }

    private class CellInputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Cell sourceCell = (Cell) e.getSource();
            int numberIn = Integer.parseInt(sourceCell.getText());

            if (numberIn == sourceCell.number) {
                sourceCell.status = CellStatus.CORRECT_GUESS;
                updateRemainingCells();
            } else {
                sourceCell.status = CellStatus.WRONG_GUESS;
            }
            sourceCell.paint();

            if (isSolved()) {
                JOptionPane.showMessageDialog(null, "Congratulations, You Solved the Puzzle!");
            }
        }
    }

    public void setPuzzle(Puzzle newPuzzle) {
        this.puzzle = newPuzzle;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }
}
