
import java.util.Scanner;

public class Main {
    private static final String [][] board = new String[3][3];
    private static final Boolean[][] winCell = new Boolean[3][3];
    private static final Scanner scanner = new Scanner(System.in);

    private static void insertDefault(){
        int n= 1;
        for(int i = 0; i<board.length; i++) {
            for(int j = 0; j<board[i].length; j++) {
                board[i][j] = String.format("%d", n++);
                winCell[i][j] = false;
            }
        }
    }

    private static void view() {
        System.out.println("=".repeat(10)+"| Welcome to Tac Tac Toe | "+"=".repeat(10));
        System.out.print("|"+"-".repeat(11)+"|\n");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[i].length; j++) {
                if (winCell[i][j]) {
                    System.out.print("\u001B[33m" + board[i][j] + "\u001B[0m" + " | ");
                }else if(board[i][j].equals("X")) {
                    System.out.print("\u001B[31m" + board[i][j] + "\u001B[0m" + " | ");
                } else if (board[i][j].equals("O")) {
                    System.out.print("\u001B[34m" + board[i][j] + "\u001B[0m" + " | ");
                } else {
                    System.out.print(board[i][j] + " | ");
                }
            }
            System.out.print("\n|" + "-".repeat(11) + "|\n");
        }
    }

    private static void placeMark(String mark) {
        outer:
        while (true){
            System.out.print("Choose "+mark+" to place: ");
            String op = scanner.nextLine();
            for(int i = 0; i<board.length; i++) {
                for(int j = 0; j<board[i].length; j++) {
                    if(board[i][j].equals(op)) {
                        board[i][j] = mark;
                        break outer;
                    }
                }
            }

            System.out.println("Invalid Input");
        }
    }

    private static boolean check(String mark) {
        for(int i = 0; i<board.length; i++) {
            if(board[i][0].equals(mark) && board[i][1].equals(mark) && board[i][2].equals(mark)) {
                winCell[i][0] = true;
                winCell[i][1] = true;
                winCell[i][2] = true;
                return true;
            }
            if (board[0][i].equals(mark) && board[1][i].equals(mark) && board[2][i].equals(mark)){
                winCell[0][i] = true;
                winCell[1][i] = true;
                winCell[2][i] = true;
                return true;
            }
        }
        if (board[0][0].equals(mark) && board[1][1].equals(mark) && board[2][2].equals(mark)){
            winCell[0][0] = true;
            winCell[1][1] = true;
            winCell[2][2] = true;
            return true;
        }
        if (board[0][2].equals(mark) && board[1][1].equals(mark) && board[2][0].equals(mark)){
            winCell[0][2] = true;
            winCell[1][1] = true;
            winCell[2][0] = true;
            return true;
        }
        return false;
    }

    private static boolean isFull() {
        for (String[] strings : board) {
            for (String string : strings) {
                if (!string.equals("X") && !string.equals("O")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        insertDefault();
        String currentPlayer = "X";

        while (true) {
            view();
            placeMark(currentPlayer);

            if (check(currentPlayer)) {
                view();
                String color = currentPlayer.equals("X") ? "\u001B[33m" : "\u001B[34m";
                System.out.println(color + currentPlayer + " wins!" + "\u001B[0m");
                break;
            }
            if(isFull()) {
                view();
                do{
                    System.out.print("It's a tie! Would you like to play again? (Y/N): ");
                    String input = scanner.nextLine();
                    if (input.equalsIgnoreCase("y")) {
                        insertDefault();
                        currentPlayer = "X";
                        break;
                    } else if (input.equalsIgnoreCase("n")) {
                        System.out.println("Thanks for playing!");
                        System.exit(0);
                    }else{
                        System.out.println("Invalid Input !");
                    }
                }while (true);

            }

            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        }

    }
}
