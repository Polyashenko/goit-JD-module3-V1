import java.util.Scanner;

public class TicTacToeGame {
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static final char EMPTY_BOX = ' ';
    private static final int BOARD_SIZE = 9;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean gameOver = false;

        while (!gameOver) {
            char[] board = new char[BOARD_SIZE];
            initBoard(board);

            System.out.println("Enter the box number to select. Enjoy!\n");

            boolean playerTurn = true;

            while (true) {
                printBoard(board);
                if (playerTurn) {
                    int playerMove = getPlayerMove(scan, board);
                    makeMove(board, playerMove, PLAYER_X);
                    if (checkWinner(board, PLAYER_X)) {
                        printBoard(board);
                        System.out.println("You won the game!\n");
                        break;
                    } else if (isBoardFull(board)) {
                        printBoard(board);
                        System.out.println("It's a draw!\n");
                        break;
                    }
                } else {
                    int computerMove = getComputerMove(board);
                    makeMove(board, computerMove, PLAYER_O);
                    if (checkWinner(board, PLAYER_O)) {
                        printBoard(board);
                        System.out.println("You lost the game!\n");
                        break;
                    }
                }

                playerTurn = !playerTurn;
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scan.next();
            gameOver = !playAgain.equalsIgnoreCase("yes");
        }

        System.out.println("Thanks for playing!");
    }

    private static void initBoard(char[] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = (char) (i + '1');
        }
    }

    private static void printBoard(char[] board) {
        System.out.println("\n " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " \n");
    }

    private static int getPlayerMove(Scanner scan, char[] board) {
        int move;
        while (true) {
            System.out.print("Enter your move (1-9): ");
            move = scan.nextInt();
            if (move >= 1 && move <= BOARD_SIZE && board[move - 1] == EMPTY_BOX) {
                break;
            } else {
                System.out.println("Invalid input or the box is already taken. Try again.");
            }
        }
        return move;
    }

    private static boolean checkWinner(char[] board, char player) {
        return (board[0] == player && board[1] == player && board[2] == player) ||
                (board[3] == player && board[4] == player && board[5] == player) ||
                (board[6] == player && board[7] == player && board[8] == player) ||
                (board[0] == player && board[3] == player && board[6] == player) ||
                (board[1] == player && board[4] == player && board[7] == player) ||
                (board[2] == player && board[5] == player && board[8] == player) ||
                (board[0] == player && board[4] == player && board[8] == player) ||
                (board[2] == player && board[4] == player && board[6] == player);
    }

    private static boolean isBoardFull(char[] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i] == EMPTY_BOX) {
                return false;
            }
        }
        return true;
    }

    private static int getComputerMove(char[] board) {
        int move;
        while (true) {
            move = (int) (Math.random() * BOARD_SIZE) + 1;
            if (board[move - 1] == EMPTY_BOX) {
                break;
            }
        }
        return move;
    }

    private static void makeMove(char[] board, int move, char player) {
        board[move - 1] = player;
    }
}
