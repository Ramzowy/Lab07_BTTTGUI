import javax.swing.*;
import java.awt.*;

public class TicTacToeGame {
    private Player playerX;
    private Player playerO;
    private Player currentPlayer;
    private TTTBoard board;
    private JLabel statusLabel;

    public TicTacToeGame() {
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 350);

        playerX = new Player('X');
        playerO = new Player('O');
        currentPlayer = playerX;

        board = new TTTBoard(this);
        statusLabel = new JLabel("Player " + currentPlayer.getSymbol() + "'s turn");

        frame.add(board.getPanel(), BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
        statusLabel.setText("Player " + currentPlayer.getSymbol() + "'s turn");
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void displayWinMessage() {
        JOptionPane.showMessageDialog(null, "Player " + currentPlayer.getSymbol() + " wins!");
        board.reset();
    }

    public void displayDrawMessage() {
        JOptionPane.showMessageDialog(null, "It's a draw!");
        board.reset();
    }
}
