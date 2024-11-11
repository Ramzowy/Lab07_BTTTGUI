import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TTTTileButton extends JButton implements ActionListener {
    private int row, col;
    private char symbol;
    private TTTBoard board;

    public TTTTileButton(int row, int col, TTTBoard board) {
        this.row = row;
        this.col = col;
        this.board = board;
        this.symbol = ' ';
        setFont(new Font("Arial", Font.PLAIN, 40));
        addActionListener(this);
    }

    public char getSymbol() {
        return symbol;
    }

    public void reset() {
        setText("");
        symbol = ' ';
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (symbol == ' ') {
            symbol = board.game.getCurrentPlayer().getSymbol();
            setText(String.valueOf(symbol));
            if (board.checkWin(row, col)) {
                board.game.displayWinMessage();
            } else if (board.isBoardFull()) {
                board.game.displayDrawMessage();
            } else {
                board.game.switchPlayer();
            }
        }
    }

}
