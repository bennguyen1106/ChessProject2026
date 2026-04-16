package board;

import pieces.*;

/**
 * The Board class maintains an 8x8 matrix representing the chessboard.
 * It handles piece initialization, rendering, and basic move execution.
 */
public class Board {
    private Piece[][] grid;

    /**
     * Constructs a new Board and initializes pieces in their starting positions.
     */
    public Board() {
        this.grid = new Piece[8][8];
        initialize();
    }

    /**
     * Sets up the board with all pieces for a standard game of chess.
     */
    private void initialize() {
        // Black Back Row (Row 0)
        grid[0][0] = new Rook("Black", 0, 0);
        grid[0][1] = new Knight("Black", 0, 1);
        grid[0][2] = new Bishop("Black", 0, 2);
        grid[0][3] = new Queen("Black", 0, 3);
        grid[0][4] = new King("Black", 0, 4);
        grid[0][5] = new Bishop("Black", 0, 5);
        grid[0][6] = new Knight("Black", 0, 6);
        grid[0][7] = new Rook("Black", 0, 7);

        // Black Pawns (Row 1)
        for (int i = 0; i < 8; i++) {
            grid[1][i] = new Pawn("Black", 1, i);
        }

        // White Pawns (Row 6)
        for (int i = 0; i < 8; i++) {
            grid[6][i] = new Pawn("White", 6, i);
        }

        // White Back Row (Row 7)
        grid[7][0] = new Rook("White", 7, 0);
        grid[7][1] = new Knight("White", 7, 1);
        grid[7][2] = new Bishop("White", 7, 2);
        grid[7][3] = new Queen("White", 7, 3);
        grid[7][4] = new King("White", 7, 4);
        grid[7][5] = new Bishop("White", 7, 5);
        grid[7][6] = new Knight("White", 7, 6);
        grid[7][7] = new Rook("White", 7, 7);
    }

    /**
     * Displays the current state of the board in the console.
     */
/**
     * Displays the current state of the board in the console.
     */
    public void display() {
        System.out.println("\n    A  B  C  D  E  F  G  H");
        System.out.println("  +-------------------------+");
        for (int r = 0; r < 8; r++) {
            System.out.print((8 - r) + " | "); 
            for (int c = 0; c < 8; c++) {
                if (grid[r][c] == null) {
                    System.out.print("-- ");
                } else {
                    System.out.print(grid[r][c].toString() + " ");
                }
            }
            System.out.println("| " + (8 - r));
        }
        System.out.println("  +-------------------------+");
        System.out.println("    A  B  C  D  E  F  G  H");
    }

    public Piece[][] getGrid() {
        return grid;
    }
}