
import javax.swing.JButton;

public class TTTTile_Button extends JButton {
    private int row;
    private int col;
    private char symbol; // To store X or O

    public TTTTile_Button(int row, int col) {
        this.row = row;
        this.col = col;
        this.symbol = ' '; // Empty by default
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public char getSymbol() { return symbol; }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
        setText(String.valueOf(symbol)); // Display symbol on button
    }

    public boolean isEmpty() {
        return symbol == ' ';
    }
}