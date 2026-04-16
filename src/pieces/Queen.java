package pieces;

public class Queen extends Piece {
    public Queen(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean isValidMove(int destRow, int destCol, Piece[][] grid) {
        // Combination of Rook and Bishop logic
        return (destRow == row || destCol == col) || 
               (Math.abs(destRow - row) == Math.abs(destCol - col));
    }

    @Override
    public String toString() {
        return color.equals("White") ? "WQ" : "BQ";
    }
}