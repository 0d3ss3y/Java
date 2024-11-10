# Terminal File Management and Word Counter

A Java-based terminal application for managing files with drag-and-drop functionality and word counting capabilities across multiple file formats.

## Features

- File Selection and Management
    - Support for TXT, PDF, and Word documents
    - Intuitive terminal-based file selection
    - File status tracking and management
    - Custom drop zone configuration

- Drag and Drop Functionality
    - Terminal-based drag and drop interface
    - Automatic drop zone creation
    - File operation confirmation
    - Progress tracking for file operations

- Word Counter
    - Support for multiple file formats:
        - Plain text (.txt)
        - PDF documents (.pdf)
        - Word documents (.doc, .docx)
    - Accurate word counting
    - Progress tracking during counting
    - Batch processing capabilities

## Prerequisites

- Java JDK 17 or higher
- Maven for dependency management
- Required dependencies:
    - Apache PDFBox (for PDF processing)
    - Apache POI (for Word document processing)

## Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/terminal-file-manager.git
```

2. Navigate to the project directory:
```bash
cd terminal-file-manager
```

3. Add the following dependencies to your `pom.xml`:
```xml
<dependencies>
    <!-- PDF Box for PDF files -->
    <dependency>
        <groupId>org.apache.pdfbox</groupId>
        <artifactId>pdfbox</artifactId>
        <version>2.0.27</version>
    </dependency>
    
    <!-- Apache POI for Word files -->
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml</artifactId>
        <version>5.2.2</version>
    </dependency>
</dependencies>
```

4. Build the project:
```bash
mvn clean install
```

## Usage

1. Start the application:
```bash
java -jar terminal-file-manager.jar
```

2. Main Menu Options:
    - Select files (Option 1)
    - View selected files (Option 2)
    - Drop files (Option 3)
    - Count words (Option 4)
    - Exit (Option 5)

### File Selection
1. Choose the file extension (TXT, PDF, WORD)
2. Select files from the displayed list
3. Confirm your selection

### Drag and Drop
1. Select files you want to move
2. Choose the drop operation
3. Confirm the operation
4. Files will be copied to the DropZone

### Word Counting
1. Select files to count
2. Choose the word count option
3. View results for each file

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── org/
│   │       └── side/
│   │           ├── Counter.java
│   │           └── TUI/
│   │               └── UI.java
│   └── resources/
└── test/
    └── java/
```

## Configuration

The application creates a DropZone directory in the user's home folder:
```
${user.home}/DropZone/
```

## Error Handling

The application includes comprehensive error handling for:
- Invalid file selections
- File access issues
- Processing errors
- Unsupported file formats

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- Apache PDFBox for PDF processing
- Apache POI for Word document processing

## Support

For support, please open an issue in the repository or contact the maintainers.

## Future Enhancements

Planned features for future releases:
- Additional file format support
- Word frequency analysis
- Character and paragraph counting
- Custom drop zone locations
- Batch processing improvements
- GUI interface option

## Notes

- The application currently supports TXT, PDF, and WORD file formats
- All operations are performed in the terminal
- Files are copied, not moved, to preserve originals

# Issues:

- Still not 100% some issues