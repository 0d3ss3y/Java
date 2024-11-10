package org.side.TUI;

public interface UI {
    static void heading() {
        System.out.println("Word Counter");
    }

    static void Rules() {
        System.out.println("""
                [A] Select a extension
                [B] Upload a file
                [C] Return the word count
                [D] View selected files
                [E] Drop selected files
                """);
    }

    static void selection_opt() {
        System.out.println("""
                Available extension:
                [1] TXT file
                [2] PDF file
                [3] WORD file
                """);

        System.out.print("Select an extension [1-3] :\n>");
    }

    static void fileOperationMenu() {
        System.out.println("""
                File Operations:
                [1] Select files
                [2] View selected files
                [3] Drop files
                [4] Count words
                [5] Return to main menu
                """);
        System.out.print("Choose operation [1-5] :\n>");
    }

    static void dragDropInstructions() {
        System.out.println("""
                === Drag and Drop Instructions ===
                1. First select your files using option 1
                2. View your selection using option 2
                3. Drop files to destination using option 3
                4. Files will be copied to the DropZone folder
                ===============================""");
    }

    static void showProgress(String fileName, String operation) {
        System.out.println("[ " + operation + " ] " + fileName);
    }

    static void showError(String message) {
        System.out.println("[ ERROR ] " + message);
    }

    static void showSuccess(String message) {
        System.out.println("[ SUCCESS ] " + message);
    }

    static void promptFileSelection() {
        System.out.println("""
                Available files in current directory:
                ------------------------""");
    }

    static void promptDropLocation() {
        System.out.println("""
                Select drop location:
                [1] Default DropZone
                [2] Custom location
                """);
        System.out.print("Choose location [1-2] :\n>");
    }

    static void showDropZoneInfo(String path) {
        System.out.println("Drop Zone Location: " + path);
        System.out.println("------------------------");
    }

    static void showFileCount(int count) {
        System.out.println("Total files selected: " + count);
    }

    static void showWordCount(String fileName, int count) {
        System.out.printf("""
                ========================
                File: %s
                Word Count: %d
                ========================%n""", fileName, count);
    }

    static void showMainMenu() {
        System.out.println("""
                
                Main Menu:
                [1] File Operations
                [2] Word Count
                [3] Exit
                """);
        System.out.print("Select option [1-3] :\n>");
    }

    static void showFileList(String[] files) {
        if (files == null || files.length == 0) {
            System.out.println("No files available.");
            return;
        }

        System.out.println("\nAvailable files:");
        for (int i = 0; i < files.length; i++) {
            System.out.printf("[%d] %s%n", (i + 1), files[i]);
        }
    }

    static void confirmOperation(String operation) {
        System.out.print("Confirm " + operation + "? (y/n): ");
    }

    static void showSeparator() {
        System.out.println("================================");
    }

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void showStatus(String status) {
        System.out.println("Status: " + status);
        showSeparator();
    }

    static void showHelp() {
        System.out.println("""
                === Help Menu ===
                - Use number keys to select options
                - Follow on-screen instructions
                - Select files before dropping
                - Check word count after dropping
                - Press CTRL+C to exit anytime
                ================""");
    }
}