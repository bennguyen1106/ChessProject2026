package board;

import pieces.Piece;
import pieces.Pawn;
import pieces.Rook;
import pieces.Knight;
import pieces.Bishop;
import pieces.Queen;
import pieces.King;

public class Board {
    private Piece[][] grid;
    private boolean kingCaptured = false;

    public Board() {
        grid = new Piece[8][8];
        initialize();
    }

    private void initialize() {
        // White Pieces
        grid[7][0] = new Rook("White", 7, 0);
        grid[7][1] = new Knight("White", 7, 1);
        grid[7][2] = new Bishop("White", 7, 2);
        grid[7][3] = new Queen("White", 7, 3);
        grid[7][4] = new King("White", 7, 4);
        grid[7][5] = new Bishop("White", 7, 5);
        grid[7][6] = new Knight("White", 7, 6);
        grid[7][7] = new Rook("White", 7, 7);
        for (int i = 0; i < 8; i++) grid[6][i] = new Pawn("White", 6, i);

        // Black Pieces
        grid[0][0] = new Rook("Black", 0, 0);
        grid[0][1] = new Knight("Black", 0, 1);
        grid[0][2] = new Bishop("Black", 0, 2);
        grid[0][3] = new Queen("Black", 0, 3);
        grid[0][4] = new King("Black", 0, 4);
        grid[0][5] = new Bishop("Black", 0, 5);
        grid[0][6] = new Knight("Black", 0, 6);
        grid[0][7] = new Rook("Black", 0, 7);
        for (int i = 0; i < 8; i++) grid[1][i] = new Pawn("Black", 1, i);
    }

    public Piece[][] getGrid() { return grid; }

    public boolean isValidMove(int startR, int startC, int endR, int endC) {
        Piece p = grid[startR][startC];
        if (p == null) return false;

        // Prevent capturing own color
        Piece target = grid[endR][endC];
        if (target != null && target.getColor().equals(p.getColor())) return false;

        // FIXED: Passing endR, endC, and the current grid as required by your Piece class
        return p.isValidMove(endR, endC, grid);
    }

    public void makeMove(int startR, int startC, int endR, int endC) {
        Piece movingPiece = grid[startR][startC];
        Piece target = grid[endR][endC];
        
        if (target instanceof King) {
            kingCaptured = true;
        }

        grid[endR][endC] = movingPiece;
        grid[startR][startC] = null;
        
        movingPiece.setPosition(endR, endC);
    }

    public boolean isCheckmate() {
        return kingCaptured;
    }

    public boolean isCheck() { return false; }

    /** Prints the current board state to the console. */
    public void display() {
        System.out.println("  A  B  C  D  E  F  G  H");
        for (int r = 0; r < 8; r++) {
            System.out.print((8 - r) + " ");
            for (int c = 0; c < 8; c++) {
                if (grid[r][c] != null) System.out.print(grid[r][c] + " ");
                else System.out.print("## ");
            }
            System.out.println();
        }
    }
}