package gui;

import javax.swing.*;
import java.awt.*;

/**
 * A dialog window for customizing the chessboard appearance.
 */
public class SettingsDialog extends JDialog {
    private Color lightColor = new Color(235, 235, 208); // Default Cream
    private Color darkColor = new Color(119, 148, 85);  // Default Green
    private boolean applied = false;

    public SettingsDialog(JFrame parent) {
        super(parent, "Board Settings", true);
        setSize(300, 200);
        setLayout(new GridLayout(3, 1, 10, 10));
        setLocationRelativeTo(parent);

        JButton woodBtn = new JButton("Classic Wood (Brown/Tan)");
        JButton grayBtn = new JButton("Modern (Gray/White)");
        JButton applyBtn = new JButton("Apply Changes");

        woodBtn.addActionListener(e -> {
            lightColor = new Color(240, 217, 181);
            darkColor = new Color(181, 136, 99);
        });

        grayBtn.addActionListener(e -> {
            lightColor = new Color(240, 240, 240);
            darkColor = new Color(120, 120, 120);
        });

        applyBtn.addActionListener(e -> {
            applied = true;
            dispose();
        });

        add(woodBtn);
        add(grayBtn);
        add(applyBtn);
    }

    public boolean wasApplied() { return applied; }
    public Color getLight() { return lightColor; }
    public Color getDark() { return darkColor; }
}