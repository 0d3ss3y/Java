package org.side.palindrome;

public class Palindrome {

    public Boolean checkPalindrome(String input) {
        String reversed = new StringBuilder(input).reverse().toString();
        System.out.println("Original string: " + input);
        System.out.println("Reversed string: " + reversed);
        System.out.println("Are they equal?: " + input.equals(reversed));
        return input.equals(reversed);
    }
}
