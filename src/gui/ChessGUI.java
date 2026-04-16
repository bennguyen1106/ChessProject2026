package gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Final Phase 2 GUI. 
 * Includes: Piece Movement, Capture Logic, King Capture Detection, 
 * New Game Reset, and Settings Integration.
 */
public class ChessGUI extends JFrame {
    private JButton[][] squares = new JButton[8][8];
    private JButton selectedSquare = null;
    private Icon heldIcon = null;
    private String heldPieceName = ""; // Track the name of the piece being moved
    
    private Color currentLight = new Color(235, 235, 208);
    private Color currentDark = new Color(119, 148, 85);

    public ChessGUI() {
        setTitle("Chess Project 2026 - Phase 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLayout(new BorderLayout());

        setupMenu();
        setupBoard();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setupMenu() {
        JMenuBar mb = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        
        JMenuItem newItem = new JMenuItem("New Game");
        newItem.addActionListener(e -> resetBoard());
        
        JMenuItem settingsItem = new JMenuItem("Settings");
        settingsItem.addActionListener(e -> {
            SettingsDialog dialog = new SettingsDialog(this);
            dialog.setVisible(true);
            if (dialog.wasApplied()) {
                this.currentLight = dialog.getLight();
                this.currentDark = dialog.getDark();
                refreshColors();
            }
        });

        gameMenu.add(newItem);
        gameMenu.addSeparator();
        gameMenu.add(settingsItem);
        mb.add(gameMenu);
        setJMenuBar(mb);
    }

    private void setupBoard() {
        JPanel boardPanel = new JPanel(new GridLayout(8, 8));
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                squares[r][c] = new JButton();
                paintSquare(r, c);

                String pieceName = getInitialPiece(r, c);
                if (pieceName != null) {
                    squares[r][c].setIcon(loadIcon(pieceName));
                    squares[r][c].setName(pieceName); // Store piece name in the button name
                }

                int row = r; int col = c;
                squares[r][c].addActionListener(e -> handleAction(row, col));
                boardPanel.add(squares[r][c]);
            }
        }
        add(boardPanel, BorderLayout.CENTER);
    }

    private void handleAction(int r, int c) {
        JButton clicked = squares[r][c];

        if (selectedSquare == null) {
            // First Click: Selection
            if (clicked.getIcon() != null) {
                selectedSquare = clicked;
                heldIcon = clicked.getIcon();
                heldPieceName = clicked.getName(); // Remember what we picked up
                clicked.setBackground(Color.YELLOW);
            }
        } else {
            // Second Click: Move or Capture
            if (clicked.getIcon() != null) {
                // Requirement 5: Check for King Capture using the stored Name
                String targetName = clicked.getName();
                if (targetName != null && targetName.toLowerCase().contains("king")) {
                    JOptionPane.showMessageDialog(this, "GAME OVER! The King has been captured.");
                    System.exit(0);
                }
            }

            // Move the piece
            clicked.setIcon(heldIcon);
            clicked.setName(heldPieceName); // Transfer the name to the new square
            
            selectedSquare.setIcon(null);
            selectedSquare.setName(null); // Clear the old square
            
            // Reset state
            refreshColors();
            selectedSquare = null;
            heldIcon = null;
            heldPieceName = "";
        }
    }

    private void resetBoard() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                squares[r][c].setIcon(null);
                squares[r][c].setName(null);
                String piece = getInitialPiece(r, c);
                if (piece != null) {
                    squares[r][c].setIcon(loadIcon(piece));
                    squares[r][c].setName(piece);
                }
            }
        }
        refreshColors();
    }

    private ImageIcon loadIcon(String name) {
        String[] possiblePaths = {"resources/" + name + ".png", "src/resources/" + name + ".png", name + ".png"};
        for (String p : possiblePaths) {
            File f = new File(p);
            if (f.exists()) {
                ImageIcon icon = new ImageIcon(p);
                Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                return new ImageIcon(img);
            }
        }
        return null;
    }

    private String getInitialPiece(int r, int c) {
        if (r == 1) return "BlackPawn";
        if (r == 6) return "WhitePawn";
        if (r == 0) {
            if (c == 0 || c == 7) return "BlackRook";
            if (c == 1 || c == 6) return "BlackKnight";
            if (c == 2 || c == 5) return "BlackBishop";
            if (c == 3) return "BlackQueen";
            if (c == 4) return "BlackKing";
        }
        if (r == 7) {
            if (c == 0 || c == 7) return "WhiteRook";
            if (c == 1 || c == 6) return "WhiteKnight";
            if (c == 2 || c == 5) return "WhiteBishop";
            if (c == 3) return "WhiteQueen";
            if (c == 4) return "WhiteKing";
        }
        return null;
    }

    private void paintSquare(int r, int c) {
        if ((r + c) % 2 == 0) squares[r][c].setBackground(currentLight);
        else squares[r][c].setBackground(currentDark);
    }

    private void refreshColors() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) paintSquare(r, c);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChessGUI::new);
    }
}