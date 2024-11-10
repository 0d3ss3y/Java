package org.side;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.side.TUI.UI;

import java.io.*;
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
                    dropFile();
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

    private static void dropFile() {
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
                    UI.showProgress(file.getName(), "Dropped");
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
                String extension = getFileExtension(file.getName()).toUpperCase();
                int wordCount;

                switch (extension) {
                    case "TXT":
                        wordCount = countWordsInTextFile(file);
                        break;
                    case "PDF":
                        wordCount = countWordsInPDFFile(file);
                        break;
                    case "DOCX":
                    case "DOC":
                        wordCount = countWordsInWordFile(file);
                        break;
                    default:
                        showError("Unsupported file type: " + extension);
                        continue;
                }

                showWordCount(file.getName(), wordCount);
            } catch (Exception e) {
                showError("Error counting words in " + file.getName() + ": " + e.getMessage());
            }
        }
    }

    private static String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return fileName.substring(lastDotIndex + 1);
        }
        return "";
    }

    private static int countWordsInTextFile(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int wordCount = 0;

            while ((line = reader.readLine()) != null) {
                // Remove leading and trailing whitespace
                line = line.trim();

                // Skip empty lines
                if (line.isEmpty()) {
                    continue;
                }

                // Split the line into words and count them
                String[] words = line.split("\\s+");
                wordCount += words.length;
            }

            return wordCount;
        }
    }

    private static int countWordsInPDFFile(File file) throws IOException {
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

            // Remove leading and trailing whitespace
            text = text.trim();

            // Split into words and count
            if (text.isEmpty()) {
                return 0;
            }

            String[] words = text.split("\\s+");
            return words.length;
        }
    }

    private static int countWordsInWordFile(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             XWPFDocument document = new XWPFDocument(fis);
             XWPFWordExtractor extractor = new XWPFWordExtractor(document)) {

            String text = extractor.getText().trim();

            if (text.isEmpty()) {
                return 0;
            }

            String[] words = text.split("\\s+");
            return words.length;
        }
    }

    // Helper method to show word count progress
    private static void showProgress(String fileName, int currentCount, int totalFiles) {
        System.out.printf("Processing file %s (%d of %d)%n",
                fileName, currentCount, totalFiles);
    }

    // Modified dropFiles() method to include word count option
    private static void dropFiles() {
        if (selectedFiles.isEmpty()) {
            showError("No files selected to drop!");
            return;
        }

        showDropZoneInfo(DROP_ZONE);
        System.out.println("Would you like to count words before dropping? (y/n)");
        Scanner scanner = new Scanner(System.in);
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            wordCount();
        }

        confirmOperation("drop files");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            for (File file : selectedFiles) {
                try {
                    Path source = file.toPath();
                    Path target = Paths.get(DROP_ZONE, file.getName());
                    Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
                    UI.showProgress(file.getName(), "Dropped");
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

}