package module01;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class NumberGuess {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter your name: ");
        String name = scan.next();
        Random random = new Random();
        int count = random.nextInt(100);
        System.out.println("Let the game begin!");
        guess(name, count);
    }

    public static void guess(String name, int count) {
        int[] array = new int[100];
        int k = 0;
        while (true) {
            System.out.print("Enter number: ");
            int enterNumber = scan.nextInt();
            array[k++] = enterNumber;
            if (enterNumber < count) {
                System.out.println("Your number is too small. Please. try again...");
            } else if (enterNumber > count) {
                System.out.println("Your number is too big. Please, try again...");
            } else {
                int[] guessArray = Arrays.copyOf(array, k);
                Arrays.sort(guessArray);
                print(name, guessArray);
                break;
            }
        }
    }

    public static void print(String name, int[] guessArray) {
        System.out.print("Your numbers: ");
        for (int i : guessArray) {
            System.out.print(i + " ");
        }
        System.out.printf("\nCongratulations, %s!", name);
    }

}

