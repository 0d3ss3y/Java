package org.side;

import org.side.generatePassword.PasswordGenerator;
import java.util.Scanner;

public class Password {
    private static final int MIN_LENGTH = 4;
    private static final int MAX_LENGTH = 16;
    private static final int DEFAULT_LENGTH = 8;
    private static final int MIN_LEVEL = 1;
    private static final int MAX_LEVEL = 3;

    private final Scanner scanner;
    private final PasswordGenerator passwordGenerator;

    public Password() {
        this.scanner = new Scanner(System.in);
        this.passwordGenerator = new PasswordGenerator();
    }

    public void start() {
        try {
            displayHeader();
            int level = getSecurityLevel();
            String password = generatePasswordForLevel(level);
            displayGeneratedPassword(password);
        } finally {
            scanner.close();
        }
    }

    private void displayHeader() {
        System.out.println("\n=== Password Generator ===\n");
        displayRules();
    }

    private void displayRules() {
        System.out.println("Password Rules and Requirements:");
        System.out.println("""
            1. Password Characteristics:
               • Length: 4-16 characters
               • Optional uppercase letters
               • Optional special characters
               • Optional numbers
            
            2. Security Levels:
               Level 1: Basic (length only)
               Level 2: Intermediate (length + character types)
               Level 3: Strong (all character types)
            
            3. Password Security:
               • Cryptographically secure generation
               • Meets modern security standards
            """);
    }

    private int getSecurityLevel() {
        while (true) {
            try {
                System.out.printf("\nSelect security level (%d-%d) [default: %d]: ", MIN_LEVEL, MAX_LEVEL, MIN_LEVEL);
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    return MIN_LEVEL;
                }

                int level = Integer.parseInt(input);
                if (level >= MIN_LEVEL && level <= MAX_LEVEL) {
                    return level;
                }

                System.out.printf("Please enter a level between %d and %d\n", MIN_LEVEL, MAX_LEVEL);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }

    private int getPasswordLength() {
        while (true) {
            try {
                System.out.printf("\nEnter password length (%d-%d) [default: %d]: ",
                        MIN_LENGTH, MAX_LENGTH, DEFAULT_LENGTH);
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    return DEFAULT_LENGTH;
                }

                int length = Integer.parseInt(input);
                if (length >= MIN_LENGTH && length <= MAX_LENGTH) {
                    return length;
                }

                System.out.printf("Please enter a length between %d and %d\n", MIN_LENGTH, MAX_LENGTH);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }

    private PasswordOptions getPasswordOptions() {
        System.out.println("\nSelect character types to include:");
        System.out.println("[U]ppercase letters");
        System.out.println("[S]pecial characters");
        System.out.println("[N]umbers");
        System.out.print("Enter options (e.g., USN or press Enter for none): ");

        String input = scanner.nextLine().toUpperCase();

        return new PasswordOptions(
                input.contains("U"),
                input.contains("S"),
                input.contains("N")
        );
    }

    private String generatePasswordForLevel(int level) {
        int length = getPasswordLength();

        return switch (level) {
            case 1 -> passwordGenerator.generatePassword(length, false, false, false);
            case 2 -> {
                PasswordOptions options = getPasswordOptions();
                yield passwordGenerator.generatePassword(
                        length,
                        options.useSpecialChars(),
                        options.useCaseSensitive(),
                        options.useNumbers()
                );
            }
            case 3 -> passwordGenerator.generatePassword(length, true, true, true);
            default -> throw new IllegalArgumentException("Invalid security level: " + level);
        };
    }

    private void displayGeneratedPassword(String password) {
        System.out.println("\n=== Generated Password ===");
        System.out.println(password);
        System.out.println("=========================\n");
    }

    private record PasswordOptions(
            boolean useCaseSensitive,
            boolean useSpecialChars,
            boolean useNumbers
    ) {}

    public static void main(String[] args) {
        new Password().start();
    }
}