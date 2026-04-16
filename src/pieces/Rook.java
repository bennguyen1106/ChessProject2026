package pieces;

public class Rook extends Piece {
    public Rook(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean isValidMove(int destRow, int destCol, Piece[][] grid) {
        // Horizontal or Vertical: one coordinate remains the same
        return (destRow == row || destCol == col);
    }

    @Override
    public String toString() {
        return color.equals("White") ? "WR" : "BR";
    }
}