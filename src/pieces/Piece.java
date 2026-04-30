package pieces;

/**
 * Abstract class representing a Chess Piece.
 */
public abstract class Piece {
    protected String color; // "White" or "Black"
    protected int row;
    protected int col;

    public Piece(String color, int row, int col) {
        this.color = color;
        this.row = row;
        this.col = col;
    }

    public String getColor() { return color; }

    /** Updates the piece's position after a move. */
    public void setPosition(int row, int col) { this.row = row; this.col = col; }

    /**
     * Subclasses implement unique movement rules here.
     * Returns true if the move to (destRow, destCol) is valid for that piece type.
     */
    public abstract boolean isValidMove(int destRow, int destCol, Piece[][] grid);

    @Override
    public abstract String toString(); // For console display (e.g., "WP" for White Pawn)
}