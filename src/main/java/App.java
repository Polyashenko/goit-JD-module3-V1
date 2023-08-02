import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        runGame();
    }

    private static void runGame() {
        Scanner scan = new Scanner(System.in);
        char[] box = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        boolean boxEmpty = false;
        byte winner = 0;

        System.out.println("Enter box number to select. Enjoy!\n");

        while (true) {
            printBoard(box);

            if (!boxEmpty) {
                initializeBoard(box);
                boxEmpty = true;
            }

            winner = playerTurn(scan, box);
            if (winner == 0) {
                winner = computerTurn(box);
            }

            if (winner != 0) {
                printResult(winner);
                break;
            }
        }
        scan.close();
    }

    private static void printBoard(char[] box) {
        System.out.println("\n\n " + box[0] + " | " + box[1] + " | " + box[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[3] + " | " + box[4] + " | " + box[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[6] + " | " + box[7] + " | " + box[8] + " \n");
    }

    private static void initializeBoard(char[] box) {
        for (int i = 0; i < 9; i++)
            box[i] = ' ';
    }

    private static void printResult(byte winner) {
        if (winner == 1) {
            System.out.println("You won the game!");
        } else if (winner == 2) {
            System.out.println("You lost the game!");
        } else if (winner == 3) {
            System.out.println("It's a draw!");
        }
        System.out.println("Created by Poliashenko Dmitry. Thanks for playing!");
    }

    private static byte playerTurn(Scanner scan, char[] box) {
        byte input;
        while (true) {
            input = scan.nextByte();
            if (input > 0 && input < 10) {
                if (box[input - 1] == 'X' || box[input - 1] == 'O') {
                    System.out.println("That one is already in use. Enter another.");
                } else {
                    box[input - 1] = 'X';
                    return checkWinner(box);
                }
            } else {
                System.out.println("Invalid input. Enter again.");
            }
        }
    }

    private static byte checkWinner(char[] box) {
        // Можливі способи перемоги для X та O
        char[][] winConditions = {
                {box[0], box[1], box[2]}, // Перша горизонталь
                {box[3], box[4], box[5]}, // Друга горизонталь
                {box[6], box[7], box[8]}, // Третя горизонталь
                {box[0], box[3], box[6]}, // Перший вертикаль
                {box[1], box[4], box[7]}, // Другий вертикаль
                {box[2], box[5], box[8]}, // Третій вертикаль
                {box[0], box[4], box[8]}, // Перша діагональ
                {box[2], box[4], box[6]}  // Друга діагональ
        };

        for (char[] condition : winConditions) {
            if (condition[0] == 'X' && condition[1] == 'X' && condition[2] == 'X') {
                return 1; // Виграв гравець X
            } else if (condition[0] == 'O' && condition[1] == 'O' && condition[2] == 'O') {
                return 2; // Виграв гравець O
            }
        }

        // Перевірка на нічию
        boolean boxAvailable = false;
        for (int i = 0; i < 9; i++) {
            if (box[i] != 'X' && box[i] != 'O') {
                boxAvailable = true;
                break;
            }
        }

        if (!boxAvailable) {
            return 3; // Нічия
        }

        return 0; // Гра ще триває
    }

    private static byte computerTurn(char[] box) {
        byte rand;
        boolean boxAvailable = false;
        for (int i = 0; i < 9; i++) {
            if (box[i] != 'X' && box[i] != 'O') {
                boxAvailable = true;
                break;
            }
        }

        if (!boxAvailable) {
            return 3;
        }

        while (true) {
            rand = (byte) (Math.random() * (9 - 1 + 1) + 1);
            if (box[rand - 1] != 'X' && box[rand - 1] != 'O') {
                box[rand - 1] = 'O';
                return checkWinner(box);
            }
        }
    }
}
