import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel {
    private TTTTile_Button[][] tiles;
    private int size;
    private Game game;

    public Board(int size, Game game) {
        this.size = size;
        this.game = game;
        setLayout(new GridLayout(size, size));
        tiles = new TTTTile_Button[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                TTTTile_Button tile = new TTTTile_Button(row, col);
                tile.addActionListener(new TileClickListener());
                tiles[row][col] = tile;
                add(tile);
            }
        }
    }

    public boolean checkWin(char symbol) {
        for (int i = 0; i < size; i++) {
            if (checkRow(i, symbol) || checkColumn(i, symbol)) return true;
        }
        return checkDiagonals(symbol);
    }

    private boolean checkRow(int row, char symbol) {
        for (int col = 0; col < size; col++) {
            if (tiles[row][col].getSymbol() != symbol) return false;
        }
        return true;
    }

    private boolean checkColumn(int col, char symbol) {
        for (int row = 0; row < size; row++) {
            if (tiles[row][col].getSymbol() != symbol) return false;
        }
        return true;
    }

    private boolean checkDiagonals(char symbol) {
        boolean diag1 = true, diag2 = true;
        for (int i = 0; i < size; i++) {
            diag1 &= (tiles[i][i].getSymbol() == symbol);
            diag2 &= (tiles[i][size - 1 - i].getSymbol() == symbol);
        }
        return diag1 || diag2;
    }

    public boolean isFull() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (tiles[row][col].isEmpty()) return false;
            }
        }
        return true;
    }

    private class TileClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            game.processMove((TTTTile_Button) e.getSource());
        }
    }
}