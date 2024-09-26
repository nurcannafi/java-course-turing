package module01;

import java.util.Random;
import java.util.Scanner;

public class ShipBattle {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[][] arr = new String[5][5];
        start(arr);
        Random random = new Random();
        int targetRow = random.nextInt(5);
        int targetColumn = random.nextInt(5);
        System.out.println("All set. Get ready to rumble!");
        while (true) {
            int row = getValidInput(scan, "Enter row number: ", 1, 5) - 1;
            int column = getValidInput(scan, "Enter column number: ", 1, 5) - 1;
            if (row == targetRow && column == targetColumn) {
                System.out.println("You have won!");
                arr[targetRow][targetColumn] = "X";
                break;
            } else {
                arr[row][column] = "*";
            }
            print(arr);
        }
        print(arr);
    }

    public static void start(String[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = "-";
            }
        }
    }

    public static void print(String[][] arr) {
        System.out.print("0 |");
        for (int j = 0; j < arr[0].length; j++) {
            System.out.print(" " + (j + 1) + " |");
        }
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            System.out.print((i + 1) + " |");
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(" " + arr[i][j] + " |");
            }
            System.out.println();
        }
    }

    public static int getValidInput(Scanner scan, String message, int min, int max) {
        int number = -1;
        boolean flag = false;
        while (!flag) {
            System.out.print(message);
            String input = scan.next();
            try {
                number = Integer.parseInt(input);
                if (number >= min && number <= max) {
                    flag = true;
                } else {
                    System.out.println("Invalid input, please, enter a number between " + min + " and " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please, enter again.");
            }
        }
        return number;
    }

}
