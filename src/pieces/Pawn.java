package pieces;

public class Pawn extends Piece {
    public Pawn(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean isValidMove(int destRow, int destCol, Piece[][] grid) {
        // Basic logic: Pawns move forward 1. 
        // Note: Full legality (captures, double-step) goes here in later phases.
        int direction = color.equals("White") ? -1 : 1;
        return (destRow == row + direction && destCol == col);
    }

    @Override
    public String toString() {
        return color.equals("White") ? "WP" : "BP";
    }
}