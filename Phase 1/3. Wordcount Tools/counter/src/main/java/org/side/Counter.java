package org.side;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static org.side.TUI.UI.*;

public class Counter {
    private static final List<String> Extensions = Collections.unmodifiableList(Arrays.asList(
            "TXT","PDF","WORD"
    ));

    public static void main(String[] args) {
        heading();
        Rules();
        String opt = Options();
        System.out.println("Selected Option = " + opt);
    }

    private static String Options() {
        String input = get_Input();
        return input;
    }

    private static String get_Input() {

        try (Scanner scanner = new Scanner(System.in)) {
            selection_opt();
            Integer input = scanner.nextInt();
            String ext = validateInput(input);

            if (Extensions.contains(ext))
                return ext;
            else
                return null;
        }


    }

    private static String validateInput(Integer input) {
        if (1 <= input && input <= 3){
            if (input == 1)
                return "TXT";
            else if (input == 2)
                return "PDF";
            else
                return "WORD";
            }
        return null;
    }
}