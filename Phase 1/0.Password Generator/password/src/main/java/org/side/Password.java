package org.side;

import java.util.Scanner;

public class Password {
    public static void main(String[] args) {
        heading();
        int level = get_option();
        String password = processOption(level);
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
        System.out.println("""
                Level 1:
                 -> Base password with user determined length
                  
                Level 2:
                 -> Password with user determined length
                 -> 1 additional option of adding special chars or case sensitivity
                 
                Level 3:
                 -> Password with user determined length
                 -> Provide case sensitivity
                 -> Provide special chars
                """);

        Scanner scanner = new Scanner(System.in);
        try{
            System.out.print("Select a Level\n>");
            int level = scanner.nextInt();

            if (level < 1 || level > 3){
                throw new Exception();
            }else{
                return level;
            }

        }catch (Exception e){
            System.out.println("Invalid entry");
        }

        return 0;
    }

    private static String processOption(int Level){
        boolean special, case_sensitive = false;


        switch (Level){
            case 1:
                int length = get_length();

        }
        return "";
    }

    private static int get_length(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter length (Max length = 16):\n> ");
            int length = scanner.nextInt();

            if (length < 1 || length > 16)
                return 0;
            else
                return length;
        } catch (Exception e) {
            System.out.println("Invalid entry");
        }

        return 0;
    }
}