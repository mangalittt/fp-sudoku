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

import javax.swing.*;
import java.awt.*;

public class Sudoku extends JFrame {
    private static final long serialVersionUID = 1L;

    GameBoardPanel board = new GameBoardPanel();
    JButton btnNewGame = new JButton("New Game");
    JButton btnNextLevel = new JButton("Next Level");

    private int currentLevel = 1;

    public Sudoku() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(board, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnNewGame);
        buttonPanel.add(btnNextLevel);
        cp.add(buttonPanel, BorderLayout.SOUTH);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Game");
        JMenuItem newGameMenu = new JMenuItem("New Game");
        newGameMenu.addActionListener(e -> newGame(30));
        fileMenu.add(newGameMenu);

        JMenuItem resetMenu = new JMenuItem("Reset");
        resetMenu.addActionListener(e -> {
            board.resetInputs();
        });
        fileMenu.add(resetMenu);

        JMenuItem exitMenu = new JMenuItem("Exit");
        exitMenu.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenu);

        menuBar.add(fileMenu);

        JMenu optionsMenu = new JMenu("Difficulty");
        JMenuItem easyOption = new JMenuItem("Easy");
        easyOption.addActionListener(e -> setDifficulty(30));
        JMenuItem intermediateOption = new JMenuItem("Intermediate");
        intermediateOption.addActionListener(e -> setDifficulty(40));
        JMenuItem hardOption = new JMenuItem("Hard");
        hardOption.addActionListener(e -> setDifficulty(50));
        optionsMenu.add(easyOption);
        optionsMenu.add(intermediateOption);
        optionsMenu.add(hardOption);
        menuBar.add(optionsMenu);

        setJMenuBar(menuBar);

        btnNewGame.addActionListener(e -> {
            currentLevel = 1;
            setDifficulty(30);
        });

        btnNextLevel.addActionListener(e -> {
            currentLevel++;
            setDifficulty(currentLevel * 10);
        });

        setDifficulty(30);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sudoku");
        setVisible(true);
    }

    private void setDifficulty(int cellsToGuess) {
        board.newGame(cellsToGuess);
    }

    private void newGame(int cellsToGuess) {
        board.newGame(cellsToGuess);
    }
}
