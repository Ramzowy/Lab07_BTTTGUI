import javax.swing.*;

public class Game {
    private JFrame frame;
    private Board board;
    private Player player1, player2;
    private Player currentPlayer;

    public Game() {
        player1 = new Player("Player 1", 'X');
        player2 = new Player("Player 2", 'O');
        currentPlayer = player1;

        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board = new Board(3, this); // Pass Game instance for interaction
        frame.add(board);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    public void processMove(TTTTile_Button tile) {
        if (!tile.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Tile already taken!");
            return;
        }

        tile.setSymbol(currentPlayer.getSymbol());

        if (board.checkWin(currentPlayer.getSymbol())) {
            JOptionPane.showMessageDialog(frame, currentPlayer.getName() + " wins!");
            resetGame();
        } else if (board.isFull()) {
            JOptionPane.showMessageDialog(frame, "It's a draw!");
            resetGame();
        } else {
            switchPlayer();
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    private void resetGame() {
        frame.getContentPane().remove(board);
        board = new Board(3, this); // New board
        frame.add(board);
        frame.revalidate();
        frame.repaint();
        currentPlayer = player1; // Reset to player1
    }
}
