package pieces;

public class Rook extends Piece {
    public Rook(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean isValidMove(int destRow, int destCol, Piece[][] grid) {
        // Must move in a straight line (not same square)
        if (destRow != row && destCol != col) return false;
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
        return color.equals("White") ? "WR" : "BR";
    }
}