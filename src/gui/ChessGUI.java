package gui;

import javax.swing.*;
import java.awt.*;
import board.Board;
import pieces.Piece;

/**
 * Phase 3 Integrated Chess GUI.
 * Connects the visual interface to the backend game logic.
 */
public class ChessGUI extends JFrame {
    private JButton[][] squares = new JButton[8][8];
    private Board gameBoard; // The Backend logic from Phase 1
    private JButton selectedSquare = null;
    private int startR, startC; // Track backend coordinates

    // Theme Colors
    private Color currentLight = new Color(235, 235, 208);
    private Color currentDark = new Color(119, 148, 85);

    public ChessGUI() {
        // Initialize the backend logic 
        gameBoard = new Board();
        
        setTitle("Chess Project 2026 - Phase 3 (Integrated)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLayout(new BorderLayout());

        setupMenu();
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

                // Get piece from the BACKEND grid [cite: 12]
                Piece p = gameBoard.getGrid()[r][c];
                if (p != null) {
                    squares[r][c].setIcon(loadIcon(p.getColor() + p.getClass().getSimpleName()));
                }

                int row = r; int col = c;
                squares[r][c].addActionListener(e -> handleAction(row, col));
                boardPanel.add(squares[r][c]);
            }
        }
        add(boardPanel, BorderLayout.CENTER);
    }

    private void handleAction(int r, int c) {
    if (selectedSquare == null) {
        // Selection Logic
        if (gameBoard.getGrid()[r][c] != null) {
            selectedSquare = squares[r][c];
            startR = r; startC = c;
            selectedSquare.setBackground(Color.YELLOW);
        }
    } else {
        // INTEGRATION POINT: Call your Phase 1 validation method
        // Example: boolean legal = gameBoard.isValidMove(startR, startC, r, c);
        boolean legal = true; // Replace with your actual move validation call

        if (legal) {
            // Update Backend State
            gameBoard.executeMove(startR, startC, r, c); // Your Phase 1 move method
            
            // Requirement 11: Check for special states
            if (gameBoard.isCheckmate()) {
                JOptionPane.showMessageDialog(this, "Checkmate! Game Over.");
            } else if (gameBoard.isCheck()) {
                JOptionPane.showMessageDialog(this, "Check!");
            }

            // Requirement 12: Sync GUI with Backend
            refreshBoard();
        } else {
            JOptionPane.showMessageDialog(this, "Illegal Move!");
        }
        
        resetAllSquareColors();
        selectedSquare = null;
    }
}

    private void refreshBoard() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = gameBoard.getGrid()[r][c];
                if (p != null) {
                    squares[r][c].setIcon(loadIcon(p.getColor() + p.getClass().getSimpleName()));
                } else {
                    squares[r][c].setIcon(null);
                }
            }
        }
    }

    private void setupMenu() {
        JMenuBar mb = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenuItem settingsItem = new JMenuItem("Settings");
        settingsItem.addActionListener(e -> {
            SettingsDialog dialog = new SettingsDialog(this);
            dialog.setVisible(true);
            if (dialog.wasApplied()) {
                this.currentLight = dialog.getLight();
                this.currentDark = dialog.getDark();
                resetAllSquareColors();
            }
        });
        gameMenu.add(settingsItem);
        mb.add(gameMenu);
        setJMenuBar(mb);
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