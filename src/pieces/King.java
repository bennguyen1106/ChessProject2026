package pieces;

public class King extends Piece {
    public King(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean isValidMove(int destRow, int destCol, Piece[][] grid) {
        // One square in any direction
        return Math.abs(destRow - row) <= 1 && Math.abs(destCol - col) <= 1;
    }

    @Override
    public String toString() {
        return color.equals("White") ? "WK" : "BK";
    }
}