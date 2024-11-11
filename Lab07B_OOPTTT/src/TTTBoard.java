import javax.swing.*;
import java.awt.*;

class TTTBoard {
    private static final int BOARD_SIZE = 3;
    private TTTTileButton[][] buttons;
    private TicTacToeGame game;
    private JPanel panel;

    public TTTBoard(TicTacToeGame game) {
        this.game = game;
        panel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        buttons = new TTTTileButton[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                buttons[i][j] = new TTTTileButton(i, j, this);
                panel.add(buttons[i][j]);
            }
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (buttons[i][j].getSymbol() == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWin(int row, int col) {
        char symbol = buttons[row][col].getSymbol();
        return (checkRow(row, symbol) || checkColumn(col, symbol) || checkDiagonals(symbol));
    }

    private boolean checkRow(int row, char symbol) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (buttons[row][i].getSymbol() != symbol) return false;
        }
        return true;
    }

    private boolean checkColumn(int col, char symbol) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (buttons[i][col].getSymbol() != symbol) return false;
        }
        return true;
    }

    private boolean checkDiagonals(char symbol) {
        boolean mainDiag = true, antiDiag = true;
        for (int i = 0; i < BOARD_SIZE; i++) {
            mainDiag &= (buttons[i][i].getSymbol() == symbol);
            antiDiag &= (buttons[i][BOARD_SIZE - i - 1].getSymbol() == symbol);
        }
        return mainDiag || antiDiag;
    }

    public void reset() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                buttons[i][j].reset();
            }
        }
    }
}
