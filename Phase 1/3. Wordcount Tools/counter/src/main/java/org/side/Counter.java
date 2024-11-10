package org.side;

import java.io.File;
import java.nio.file.*;
import java.util.*;

import static org.side.TUI.UI.*;

public class Counter {
    private static final List<String> Extensions = Collections.unmodifiableList(Arrays.asList(
            "TXT", "PDF", "WORD"
    ));

    private static final String DROP_ZONE = System.getProperty("user.home") + File.separator + "DropZone";
    private static final List<File> selectedFiles = new ArrayList<>();

    public static void main(String[] args) {
        clearScreen();
        heading();
        Rules();
        dragDropInstructions();
        initializeDropZone();

        while (true) {
            fileOperationMenu();
            int choice = getMenuChoice();

            switch (choice) {
                case 1:
                    String ext = Options();
                    if (ext != null) {
                        selectFiles(ext);
                    }
                    break;
                case 2:
                    viewSelectedFiles();
                    break;
                case 3:
                    dropFiles();
                    break;
                case 4:
                    wordCount();
                    break;
                case 5:
                    showStatus("Exiting program");
                    return;
                default:
                    showError("Invalid choice!");
            }
            showSeparator();
        }
    }

    private static void initializeDropZone() {
        File dropZoneDir = new File(DROP_ZONE);
        if (!dropZoneDir.exists()) {
            dropZoneDir.mkdirs();
            showDropZoneInfo(DROP_ZONE);
        }
    }

    private static int getMenuChoice() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static String Options() {
        return get_Input();
    }

    private static String get_Input() {
        try (Scanner scanner = new Scanner(System.in)) {
            selection_opt();
            Integer input = scanner.nextInt();
            String ext = validateInput(input);

            if (Extensions.contains(ext))
                return ext;
            else {
                showError("Invalid extension selected");
                return null;
            }
        }
    }

    private static String validateInput(Integer input) {
        if (1 <= input && input <= 3) {
            if (input == 1)
                return "TXT";
            else if (input == 2)
                return "PDF";
            else
                return "WORD";
        }
        return null;
    }

    private static void selectFiles(String extension) {
        String currentDir = System.getProperty("user.dir");
        File dir = new File(currentDir);
        File[] files = dir.listFiles((_, name) -> name.toUpperCase().endsWith("." + extension));

        if (files == null || files.length == 0) {
            showError("No " + extension + " files found in current directory.");
            return;
        }

        promptFileSelection();
        String[] fileNames = Arrays.stream(files).map(File::getName).toArray(String[]::new);
        showFileList(fileNames);

        System.out.print("\nEnter file number to select (0 to cancel): ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= files.length) {
            selectedFiles.add(files[choice - 1]);
            showSuccess("Selected: " + files[choice - 1].getName());
            showFileCount(selectedFiles.size());
        }
    }

    private static void viewSelectedFiles() {
        if (selectedFiles.isEmpty()) {
            showError("No files selected!");
            return;
        }

        String[] fileNames = selectedFiles.stream()
                .map(File::getName)
                .toArray(String[]::new);
        showFileList(fileNames);
        showFileCount(selectedFiles.size());
    }

    private static void dropFiles() {
        if (selectedFiles.isEmpty()) {
            showError("No files selected to drop!");
            return;
        }

        showDropZoneInfo(DROP_ZONE);
        confirmOperation("drop files");
        Scanner scanner = new Scanner(System.in);
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            for (File file : selectedFiles) {
                try {
                    Path source = file.toPath();
                    Path target = Paths.get(DROP_ZONE, file.getName());
                    Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
                    showProgress(file.getName(), "Dropped");
                } catch (Exception e) {
                    showError("Error dropping file " + file.getName() + ": " + e.getMessage());
                }
            }
            selectedFiles.clear();
            showSuccess("All files dropped successfully!");
        } else {
            showStatus("Drop operation cancelled");
        }
    }

    private static void wordCount() {
        if (selectedFiles.isEmpty()) {
            showError("No files selected for word count!");
            return;
        }

        for (File file : selectedFiles) {
            try {
                // This is a placeholder for actual word counting logic
                // You would implement the actual word counting here
                int wordCount = countWordsInFile(file);
                showWordCount(file.getName(), wordCount);
            } catch (Exception e) {
                showError("Error counting words in " + file.getName() + ": " + e.getMessage());
            }
        }
    }

    private static int countWordsInFile(File file) {
        // Placeholder method - implement actual word counting logic
        return 0;
    }
}