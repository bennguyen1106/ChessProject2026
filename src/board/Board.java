package board;

import pieces.*;

public class Board {
    private Piece[][] grid;

    public Board() {
        grid = new Piece[8][8];
        setup();
    }

    private void setup() {
        // Initialize Black Pieces
        grid[0][0] = new Rook("Black", 0, 0);
        grid[0][1] = new Knight("Black", 0, 1);
        // ... add other pieces ...
        for (int i = 0; i < 8; i++) grid[1][i] = new Pawn("Black", 1, i);

        // Initialize White Pieces
        for (int i = 0; i < 8; i++) grid[6][i] = new Pawn("White", 6, i);
        grid[7][0] = new Rook("White", 7, 0);
        // ... add other pieces ...
    }

    public void display() {
        System.out.println("  A  B  C  D  E  F  G  H");
        for (int r = 0; r < 8; r++) {
            System.out.print((8 - r) + " ");
            for (int c = 0; c < 8; c++) {
                if (grid[r][c] == null) {
                    System.out.print("-- ");
                } else {
                    System.out.print(grid[r][c] + " ");
                }
            }
            System.out.println(8 - r);
        }
        System.out.println("  A  B  C  D  E  F  G  H");
    }

    public Piece getPiece(int r, int c) { return grid[r][c]; }
}