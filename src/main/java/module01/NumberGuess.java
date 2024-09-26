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
        int number = random.nextInt(100);
        System.out.println("Let the game begin!");
        guess(name, number);
    }

    public static void guess(String name, int number) {
        int[] array = new int[100];
        int count = 0;
        while (true) {
            System.out.print("Enter number: ");
            int enterNumber = scan.nextInt();
            array[count++] = enterNumber;
            if (enterNumber < number) {
                System.out.println("Your number is too small. Please. try again...");
            } else if (enterNumber > number) {
                System.out.println("Your number is too big. Please, try again...");
            } else {
                int[] guessArray = Arrays.copyOf(array, count);
                Arrays.sort(guessArray);
                print(name, guessArray);
                break;
            }
        }
    }

    public static void print(String name, int[] guessArray) {
        System.out.print("Your numbers: ");
        for (int i = guessArray.length - 1; i >= 0; i--) {
            System.out.print(guessArray[i] + " ");
        }
        System.out.printf("\nCongratulations, %s!", name);
    }

}

