package main;

import java.util.Scanner;
import board.Board;

/**
 * Main entry point for the Chess game.
 * Handles the console interface loop and input parsing.
 */
public class ChessGame {
    public static void main(String[] args) {
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to Chess Phase 1!");

        while (running) {
            // 1. Display the board (Goal 5)
            board.display();

            // 2. Prompt for moves (Goal 6)
            System.out.print("Enter move (e.g., E2 E4) or 'exit': ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                running = false;
                continue;
            }

            // 3. Basic Validation (Goal 6)
            // Checks for: Letter (A-H), Number (1-8), Space, Letter (A-H), Number (1-8)
            if (input.toUpperCase().matches("^[A-H][1-8] [A-H][1-8]$")) {
                System.out.println("Move format accepted: " + input.toUpperCase());
                // In Phase 1, we just acknowledge the format. 
                // Actual move execution is for later phases.
            } else {
                System.out.println("Invalid format! Please use standard notation like 'E2 E4'.");
            }
        }

        System.out.println("Game terminated.");
        scanner.close();
    }
}