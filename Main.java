import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToe game = new TicTacToe();

        System.out.println("Welcome to 3x3 Tic Tac Toe.");
        game.printBoard();
        System.out.println("X will play first. Enter a slot number to place X in:");

        while (true) {
            int numInput;
            while (true) {
                if (game.turn.equals("X")) {
                    try {
                        numInput = scanner.nextInt();
                        if (numInput > 0 && numInput <= 9) {
                            break;
                        }
                        System.out.println("Invalid input; re-enter slot number:");
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input; re-enter slot number:");
                        scanner.next(); // clear invalid input
                    }
                } else {
                    game.makeAIMove();
                    String winner = game.checkWinner();
                    if (winner != null) {
                        game.endGame(winner, scanner);
                        return;
                    }
                }
            }
            game.makeMove(numInput);
            String winner = game.checkWinner();
            if (winner != null) {
                game.endGame(winner, scanner);
                return;
            }
        }

    }

    private void endGame(String winner, Scanner scanner) {
        if (winner.equalsIgnoreCase("draw")) {
            System.out.println("It's a draw! Thanks for playing.");
        } else {
            System.out.println("Congratulations! " + winner + " have won! Thanks for playing.");
        }
        scanner.close();
    }
}