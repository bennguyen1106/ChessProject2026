package pieces;

public class Knight extends Piece {
    public Knight(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean isValidMove(int destRow, int destCol, Piece[][] grid) {
        int rowDiff = Math.abs(destRow - row);
        int colDiff = Math.abs(destCol - col);
        // L-shape: (2,1) or (1,2)
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }

    @Override
    public String toString() {
        return color.equals("White") ? "WN" : "BN";
    }
}