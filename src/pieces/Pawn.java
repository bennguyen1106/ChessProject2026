package pieces;

public class Pawn extends Piece {
    public Pawn(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean isValidMove(int destRow, int destCol, Piece[][] grid) {
        int direction = color.equals("White") ? -1 : 1;
        int startRow = color.equals("White") ? 6 : 1;

        // One step forward to empty square
        if (destCol == col && destRow == row + direction && grid[destRow][destCol] == null)
            return true;

        // Two steps forward from starting row (path must be clear)
        if (destCol == col && row == startRow && destRow == row + 2 * direction
                && grid[row + direction][col] == null && grid[destRow][destCol] == null)
            return true;

        // Diagonal capture (Board already blocks same-color, so any piece here is enemy)
        if (Math.abs(destCol - col) == 1 && destRow == row + direction && grid[destRow][destCol] != null)
            return true;

        return false;
    }

    @Override
    public String toString() {
        return color.equals("White") ? "WP" : "BP";
    }
}