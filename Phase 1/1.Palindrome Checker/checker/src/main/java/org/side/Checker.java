package org.side;

import org.side.palindrome.Palindrome;
import java.util.Scanner;

public class Checker {
    public static void main(String[] args) {
        displayHeader();
        rules();
        processWithFeedback();
    }

    private static void displayHeader() {
        System.out.println("Palindrome Checker\n");
    }

    private static void rules() {
        System.out.println("""
                Rules:
                [1]. Only allowed to enter a singular word.
                [2]. Input cannot be empty.
                [3]. Special characters and numbers are not allowed.
                """);
    }

    private static void processWithFeedback() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Enter a word (or 'exit' to quit): ");
                String word = scanner.nextLine().trim();

                if (word.equalsIgnoreCase("exit")) {
                    System.out.println("Thank you for using Palindrome Checker!");
                    break;
                }

                if (isValidInput(word)) {
                    boolean isPalindrome = new Palindrome().checkPalindrome(word);
                    displayResult(word, isPalindrome);
                } else {
                    System.out.println("Invalid input! Please enter a single word with only letters.");
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private static boolean isValidInput(String word) {
        return word != null &&
                !word.isEmpty() &&
                !word.contains(" ") &&
                word.matches("[a-zA-Z]+");
    }

    private static void displayResult(String word, boolean isPalindrome) {
        if (isPalindrome) {
            System.out.printf("'%s' is a palindrome!%n", word);
        } else {
            System.out.printf("'%s' is not a palindrome.%n", word);
        }
    }
}