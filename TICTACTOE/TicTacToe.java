
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
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Tic-Tac-Toe: Two-player Graphic version with better OO design.
 * The Board and Cell classes are separated in their own classes.
 */


/**
 * Tic-Tac-Toe: Two-player Graphic version with better OO design.
 */
public class TicTacToe extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final String TITLE = "Tic Tac Toe";
    private static final Color COLOR_BG = Color.WHITE;
    private static final Color COLOR_BG_STATUS = new Color(216, 216, 216);
    private static final Font FONT_STATUS = new Font("OCR A Extended", Font.PLAIN, 14);

    private Board board;
    private State currentState;
    private Seed currentPlayer;
    private JLabel statusBar;
    private AIPlayerMinimax aiPlayer; // AI Player instance

    public TicTacToe() {

        SoundEffect.preload();

        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                int row = mouseY / Cell.SIZE;
                int col = mouseX / Cell.SIZE;

                if (currentState == State.PLAYING) {
                    if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS
                            && board.cells[row][col].content == Seed.NO_SEED) {
                        currentState = board.stepGame(currentPlayer, row, col);
                        currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;

                        if (currentState == State.PLAYING) {
                            SoundEffect.EAT_FOOD.play();
                        }

                        if (currentState == State.PLAYING && currentPlayer == aiPlayer.mySeed) {
                            int[] aiMove = aiPlayer.move();
                            board.stepGame(aiPlayer.mySeed, aiMove[0], aiMove[1]);
                            currentState = board.checkWinner(aiPlayer.mySeed, aiMove[0], aiMove[1]);
                            currentPlayer = Seed.CROSS;
                        }
                    }
                } else {
                    TicTacToe.this.showEndGameDialog();
                }
                repaint();
            }
            
        });
        statusBar = new JLabel();
        statusBar.setFont(FONT_STATUS);
        statusBar.setBackground(COLOR_BG_STATUS);
        statusBar.setOpaque(true);
        statusBar.setPreferredSize(new Dimension(300, 30));
        statusBar.setHorizontalAlignment(JLabel.LEFT);
        statusBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 12));

        super.setLayout(new BorderLayout());
        super.add(statusBar, BorderLayout.PAGE_END);
        super.setPreferredSize(new Dimension(Board.CANVAS_WIDTH, Board.CANVAS_HEIGHT + 30));
        super.setBorder(BorderFactory.createLineBorder(COLOR_BG_STATUS, 2, false));

        initGame();
        aiPlayer = new AIPlayerMinimax(board);
        aiPlayer.setSeed(Seed.NOUGHT); // Set AI as 'O'
        newGame();
    }

    public void initGame() {
        board = new Board();
    }

    public void newGame() {
        board.newGame();
        currentPlayer = Seed.CROSS; // X player starts
        currentState = State.PLAYING;
    }

    

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(COLOR_BG);

        board.paint(g);

        if (currentState == State.PLAYING) {
            statusBar.setForeground(Color.BLACK);
            statusBar.setText((currentPlayer == Seed.CROSS) ? "Intel's Turn" : "Ryzen's Turn");
        } else if (currentState == State.DRAW) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("NOBODY LOSE! LETS TRY AGAIN");
            SoundEffect.DRAW.play();
        } else if (currentState == State.CROSS_WON) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("'YOU WON! CONGRATULATIONS");
            SoundEffect.EAT_FOOD.play();
        } else if (currentState == State.NOUGHT_WON) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("it's too bad you lose let's try again");
            SoundEffect.DIE.play();
        }
    }

    private void showEndGameDialog(){
    int choice = JOptionPane.showOptionDialog(
        this,
        "Do you want to play again?",
        "Game Over",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        new String[] {"Play Again", "Exit"},
        "Play Again"
    );

    if (choice == JOptionPane.YES_OPTION) {
        newGame();
    } else {
        System.exit(0);
    }
}

    
public void play() {
    javax.swing.SwingUtilities.invokeLater(() -> {
        JFrame frame = new JFrame(TITLE); // Deklarasi frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel untuk layar awal
        JPanel startPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("FP SUDOKU", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.PLAIN, 16));

        startPanel.add(titleLabel, BorderLayout.CENTER);
        startPanel.add(startButton, BorderLayout.SOUTH);

        frame.setContentPane(startPanel);
        frame.setPreferredSize(new Dimension(400, 300)); // Ukuran default untuk frame
        frame.pack();
        frame.setLocationRelativeTo(null); // Posisikan di tengah layar
        frame.setVisible(true);

        // Event listener untuk tombol start game
        startButton.addActionListener(e -> {
            frame.setContentPane(new TicTacToe());
            frame.setSize(Board.CANVAS_WIDTH + 20, Board.CANVAS_HEIGHT + 70); // Sesuaikan ukuran untuk game
            frame.revalidate();
        });
    });
}
}
