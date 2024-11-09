package org.side;

import org.side.generatePassword.PasswordGenerator;

import java.util.Scanner;

public class Password {
    static boolean specialCases, caseSensitive,numberCases = false;

    public static void main(String[] args) {
        heading();
        int level = get_option();
        String password = processOption(level);
        System.out.println("\nYour password: " + password);
    }

    private static void heading() {
        System.out.println("Password Generator\n");
        rules();
    }

    private static void rules() {
        System.out.println("Password Rules And Regulations");
        System.out.println(
                """
                        (1) Generated psaswords will include the following:
                        - specified length
                        - uppercasing (optional)
                        - lowercasing (optional)
                        - special (optional)
                        
                        (2) Password Encryption:
                        - Passwords as encrypted using SHA-256 for secure storage
                        
                        (3) Password Validation:
                        - Depending on level of difficulty different validations will be checked:
                        
                        (4) Levels :
                        * Level 1.  -> Provide basic length validation
                        * Level 2.  -> Provide case sensitive and length validation
                        * Level 3.  -> Provide case sensitive, special char and length validation
                        
                        """

        );
    }

    private static int get_option() {
//      1

        try{
            Scanner scanner = new Scanner(System.in);
            System.out.print("Select a Level\nDefault is 1\n>");
            int level = scanner.nextInt();

            if (level < 1 || level > 3){
                throw new Exception();
            }else{
                return level;
            }

        }catch (Exception e){
            System.out.println("Invalid entry");
        }

        return 1;

    }

    private static String processOption(int Level) {
        int len = get_length();
        String password = "";

        if (Level == 1 || Level == 3) {
            if (Level == 1)
                password = new PasswordGenerator().generatePassword(len, specialCases, caseSensitive, numberCases);
            else
                password = new PasswordGenerator().generatePassword(len, true, true, true);
        }
        else {
            String caseUse = get_Case();
            if (caseUse.equalsIgnoreCase("c"))
                password = new PasswordGenerator().generatePassword(len, specialCases, true, numberCases);
            else if (caseUse.equalsIgnoreCase("s"))
                password = new PasswordGenerator().generatePassword(len, true, caseSensitive, numberCases);
            else if (caseUse.equalsIgnoreCase("n"))
                password = new PasswordGenerator().generatePassword(len, specialCases, caseSensitive, true);
            else
                password = new PasswordGenerator().generatePassword(len, specialCases, caseSensitive, numberCases);
        }

        return password;
    }

    private static String get_Case() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the following to use the minimum requirement:");
        System.out.println("""
                    [N] = numbers
                    [S] = special chars
                    [C] = caseSensitive
                    """);
        System.out.print("> ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("N"))
            return input;
        else if (input.equalsIgnoreCase("S"))
            return input;
        else if (input.equalsIgnoreCase("C"))
            return input;
        else
            return null;
    }

    private static int get_length() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter length (Max length = 16) (Min length = 4):\nDefault length is 4\n> ");
            int length = scanner.nextInt();

            if (length < 4 || length > 16)
                return 4;
            else
                return length;
        } catch (Exception e) {
            System.out.println("Invalid entry");
        }

        return 4;
    }
}