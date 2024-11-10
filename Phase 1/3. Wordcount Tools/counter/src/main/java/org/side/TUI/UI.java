package org.side.TUI;

public interface UI {
    static void heading(){
        System.out.println("Word Counter");
    }

    static void Rules() {
        System.out.println("""
                [A] Select a extension
                [B] Upload a file
                [C] Return the word count
                """);
    }

    static void selection_opt(){
        System.out.println("""
                Available extension:
                [1] TXT file
                [2] PDF file
                [3] WORD file
                """);

        System.out.print("Select an extension [1-3] :\n>");
    }
}
