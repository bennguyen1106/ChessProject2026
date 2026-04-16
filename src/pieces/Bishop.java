package pieces;

public class Bishop extends Piece {
    public Bishop(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean isValidMove(int destRow, int destCol, Piece[][] grid) {
        // Diagonal: row difference must equal column difference
        return Math.abs(destRow - row) == Math.abs(destCol - col);
    }

    @Override
    public String toString() {
        return color.equals("White") ? "WB" : "BB";
    }
}