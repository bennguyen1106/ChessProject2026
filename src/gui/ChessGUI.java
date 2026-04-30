package gui;

import javax.swing.*;
import java.awt.*;
import board.Board;
import pieces.Piece;

public class ChessGUI extends JFrame {
    private JButton[][] squares = new JButton[8][8];
    private Board gameBoard; 
    private JButton selectedSquare = null;
    private int startR, startC; 

    private Color currentLight = new Color(235, 235, 208);
    private Color currentDark = new Color(119, 148, 85);
    private String currentTurn = "White";
    private boolean gameOver = false;

    public ChessGUI() {
        gameBoard = new Board(); // [cite: 10]
        
        setTitle("Chess Project 2026 - Phase 3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLayout(new BorderLayout());

        setupBoard();
        
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setupBoard() {
        JPanel boardPanel = new JPanel(new GridLayout(8, 8));
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                squares[r][c] = new JButton();
                paintSquare(r, c);
                updateIcon(r, c);

                int row = r; int col = c;
                squares[r][c].addActionListener(e -> handleAction(row, col));
                boardPanel.add(squares[r][c]);
            }
        }
        add(boardPanel, BorderLayout.CENTER);
    }

    private void handleAction(int r, int c) {
        if (gameOver) return;

        Piece clicked = gameBoard.getGrid()[r][c];

        if (selectedSquare == null) {
            // Select a piece belonging to the current player
            if (clicked != null && clicked.getColor().equals(currentTurn)) {
                selectedSquare = squares[r][c];
                startR = r; startC = c;
                selectedSquare.setBackground(Color.YELLOW);
            }
        } else {
            // Re-select another own piece instead of attempting an illegal move
            if (clicked != null && clicked.getColor().equals(currentTurn)) {
                resetAllSquareColors();
                selectedSquare = squares[r][c];
                startR = r; startC = c;
                selectedSquare.setBackground(Color.YELLOW);
                return;
            }

            if (gameBoard.isValidMove(startR, startC, r, c)) {
                gameBoard.makeMove(startR, startC, r, c);
                refreshBoard();

                if (gameBoard.isCheckmate()) {
                    JOptionPane.showMessageDialog(this, currentTurn + " wins! Game Over.");
                    gameOver = true;
                }
                currentTurn = currentTurn.equals("White") ? "Black" : "White";
            } else {
                JOptionPane.showMessageDialog(this, "Illegal move!");
            }

            resetAllSquareColors();
            selectedSquare = null;
        }
    }

    private void refreshBoard() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                updateIcon(r, c);
            }
        }
    }

    private void updateIcon(int r, int c) {
        Piece p = gameBoard.getGrid()[r][c];
        if (p != null) {
            String name = p.getColor() + p.getClass().getSimpleName();
            squares[r][c].setIcon(loadIcon(name));
        } else {
            squares[r][c].setIcon(null);
        }
    }

    private ImageIcon loadIcon(String name) {
        String path = "resources/" + name + ".png";
        java.io.File file = new java.io.File(path);
        if (file.exists()) {
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }
        return null;
    }

    private void paintSquare(int r, int c) {
        if ((r + c) % 2 == 0) squares[r][c].setBackground(currentLight);
        else squares[r][c].setBackground(currentDark);
    }

    private void resetAllSquareColors() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) paintSquare(r, c);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChessGUI::new);
    }
}