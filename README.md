# Chess Project 2026
**Author:** Ben Nguyen (djx16)

A two-player chess game built in Java, developed across three phases: console backend, Swing GUI, and full integration.

---

## How to Run

**Requirements:** JDK 17+

> On Windows, run these commands in **Git Bash** or any bash terminal (not CMD or PowerShell) so the `*.java` wildcards expand correctly.

### Quick Run (compiled classes already included)
```bash
java -cp bin gui.ChessGUI
```

### Recompile from source
```bash
# GUI (Phase 2 & 3)
javac -d bin -sourcepath src src/pieces/*.java src/board/Board.java src/gui/*.java
java -cp bin gui.ChessGUI

# Console / CLI (Phase 1)
javac -d bin -sourcepath src src/pieces/*.java src/board/Board.java src/main/ChessGame.java
java -cp bin main.ChessGame
```

---

## Project Structure

```
src/
  pieces/     # Piece abstract class + Pawn, Rook, Knight, Bishop, Queen, King
  board/      # Board (8x8 grid, move validation, display)
  gui/        # ChessGUI (Swing), SettingsDialog
  main/       # ChessGame (CLI entry point)
bin/          # Compiled .class files
resources/    # Piece PNG icons
```

---

## Implemented Features

### Phase 1 — Console Backend
- [x] 8x8 board with piece initialization and console display
- [x] All six piece types with correct movement rules (including path-blocking for Rook, Bishop, Queen)
- [x] Pawn: single step, double step from starting row, diagonal capture
- [x] Input parsing using standard chess notation (e.g., `E2 E4`)
- [x] Basic move format validation

### Phase 2 — Swing GUI
- [x] 8x8 visual chessboard with alternating square colors
- [x] Piece icons loaded from `resources/` folder
- [x] Click-to-move interaction (select piece, click destination)
- [x] Capture logic: enemy piece removed on move-to
- [x] King capture triggers winner popup and ends the game
- [x] Settings dialog for board color themes (Classic Wood, Modern Gray)

### Phase 3 — Integration
- [x] GUI fully connected to backend move validation
- [x] Turn enforcement: White moves first, then Black, alternating
- [x] Illegal moves blocked with error dialog
- [x] Re-selecting a different own piece switches selection cleanly
- [x] Board freezes after game over (no moves accepted post-win)

---

## How to Play

1. Launch the GUI using the command above.
2. **White moves first.** Click any white piece to select it (highlighted yellow).
3. Click a destination square to move. Illegal moves show an error — try again.
4. Players alternate turns. Capture the opponent's King to win.
5. A popup declares the winner and the board stops accepting input.
