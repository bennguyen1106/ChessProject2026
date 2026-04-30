package pieces;

public class Queen extends Piece {
    public Queen(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean isValidMove(int destRow, int destCol, Piece[][] grid) {
        boolean straight = (destRow == row || destCol == col);
        boolean diagonal = Math.abs(destRow - row) == Math.abs(destCol - col);
        if (!straight && !diagonal) return false;
        if (destRow == row && destCol == col) return false;

        // Check that no pieces are blocking the path
        int rowDir = Integer.signum(destRow - row);
        int colDir = Integer.signum(destCol - col);
        int r = row + rowDir, c = col + colDir;
        while (r != destRow || c != destCol) {
            if (grid[r][c] != null) return false;
            r += rowDir;
            c += colDir;
        }
        return true;
    }

    @Override
    public String toString() {
        return color.equals("White") ? "WQ" : "BQ";
    }
}