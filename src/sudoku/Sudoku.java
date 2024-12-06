package sudoku;

import java.awt.*;
import javax.swing.*;

public class Sudoku extends JFrame {
    private static final long serialVersionUID = 1L;

    // Private variables
    GameBoardPanel board = new GameBoardPanel();
    JButton btnNewGame = new JButton("New Game");
    JButton btnNextLevel = new JButton("Next Level");

    private int currentLevel = 1; // Menyimpan level permainan saat ini

    // Constructor
    public Sudoku() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(board, BorderLayout.CENTER);

        // Panel untuk tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnNewGame);
        buttonPanel.add(btnNextLevel);
        cp.add(buttonPanel, BorderLayout.SOUTH);

        // Tombol New Game
        btnNewGame.addActionListener(e -> {
            currentLevel = 1; // Reset level
            board.newGame(currentLevel * 2); // Kotak kosong awal (misalnya 5)
        });

        // Tombol Next Level
        btnNextLevel.addActionListener(e -> {
            currentLevel++; // Tingkatkan level
            board.newGame(currentLevel * 2); // Tambah jumlah kotak kosong per level
        });

        // Mulai permainan dengan level 1
        board.newGame(currentLevel * 2);

        pack(); // Pack the UI components
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Handle window-closing
        setTitle("Sudoku");
        setVisible(true);
    }
}

