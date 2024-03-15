import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public String[] board;
    public String turn;

    public TicTacToe() {
        board = new String[9];
        turn = "X";

        for (int i = 0; i < 9; i++) {
            board[i] = String.valueOf(i + 1);
        }
    }

    public String checkWinner() {
        String[] lines = {
                board[0] + board[1] + board[2], // rows
                board[3] + board[4] + board[5],
                board[6] + board[7] + board[8],
                board[0] + board[3] + board[6], // columns
                board[1] + board[4] + board[7],
                board[2] + board[5] + board[8],
                board[0] + board[4] + board[8], // diagonals
                board[2] + board[4] + board[6]
        };

        for (String line : lines) {
            if (line.equals("XXX")) {
                return "X";
            } else if (line.equals("OOO")) {
                return "O";
            }
        }

        for (String cell : board) {
            if (!cell.equals("X") && !cell.equals("O")) {
                return null; // game ongoing
            }
        }

        return "draw"; // game drawn
    }

    public void printBoard() {
        System.out.println("|---|---|---|");
        for (int i = 0; i < 9; i += 3) {
            System.out.println("| " + board[i] + " | " + board[i + 1] + " | " + board[i + 2] + " |");
            System.out.println("|---|---|---|");
        }
        System.out.println("Your next move:");
    }

    public void makeMove(int numInput) {
        if (board[numInput - 1].equals(String.valueOf(numInput))) {
            board[numInput - 1] = turn;
            turn = (turn.equals("X")) ? "O" : "X";
            printBoard();
        } else {
            System.out.println("Slot already taken; re-enter slot number:");
        }
    }

    public void makeAIMove() {
        List<Integer> emptySlots = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            if (board[i].equals(String.valueOf(i + 1))) {
                emptySlots.add(i + 1);
            }
        }

        if (!emptySlots.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(emptySlots.size());
            int aiMove = emptySlots.get(randomIndex);
            makeMove(aiMove);
        }
    }

    public void endGame(String winner, Scanner scanner) {
        if (winner.equalsIgnoreCase("draw")) {
            System.out.println("It's a draw! Thanks for playing.");
        } else {
            System.out.println("Congratulations! " + winner + " have won! Thanks for playing.");
        }
        scanner.close();
    }

}