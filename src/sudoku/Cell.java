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

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

public class Cell extends JTextField {
    private static final long serialVersionUID = 1L;

    public static final Color BG_GIVEN = new Color(240, 240, 240);
    public static final Color FG_GIVEN = Color.BLACK;
    public static final Color FG_NOT_GIVEN = Color.GRAY;
    public static final Color BG_TO_GUESS  = Color.YELLOW;
    public static final Color BG_CORRECT_GUESS = new Color(0, 216, 0);
    public static final Color BG_WRONG_GUESS   = new Color(216, 0, 0);
    public static final Font FONT_NUMBERS = new Font("OCR A Extended", Font.PLAIN, 28);

    int row, col;
    int number;
    CellStatus status;

    public Cell(int row, int col) {
        super();
        this.row = row;
        this.col = col;
        super.setHorizontalAlignment(JTextField.CENTER);
        super.setFont(FONT_NUMBERS);
    }

    public void newGame(int number, boolean isGiven) {
        this.number = number;
        status = isGiven ? CellStatus.GIVEN : CellStatus.TO_GUESS;
        paint();
    }

    public void paint() {
        if (status == CellStatus.GIVEN) {
            super.setText(number + "");
            super.setEditable(false);
            super.setBackground(BG_GIVEN);
            super.setForeground(FG_GIVEN);
        } else if (status == CellStatus.TO_GUESS) {
            super.setText("");
            super.setEditable(true);
            super.setBackground(BG_TO_GUESS);
            super.setForeground(FG_NOT_GIVEN);
        } else if (status == CellStatus.CORRECT_GUESS) {
            super.setBackground(BG_CORRECT_GUESS);
        } else if (status == CellStatus.WRONG_GUESS) {
            super.setBackground(BG_WRONG_GUESS);
        }
    }

    public void resetInput() {
        if (status != CellStatus.GIVEN) {
            super.setText("");
            this.status = CellStatus.TO_GUESS;
            paint();
        }
    }

    public boolean isGiven() {
        return status == CellStatus.GIVEN;
    }

    public void resetStatus() {
        if (status != CellStatus.GIVEN) {
            status = CellStatus.TO_GUESS;
            super.setText("");
            paint();
        }
    }
}
