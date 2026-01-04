/**
 * FileOperationsDemo.java
 *
 * A simple demonstration of basic text file operations in Java:
 *  1. Writing to a file
 *  2. Reading from a file
 *  3. Modifying file content (read -> change -> write back)
 *
 * Author: ChatGPT
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileOperationsDemo {

    public static void main(String[] args) {

        Path filePath = Path.of("sample.txt");

        try {
            // 1. WRITE OPERATION
            writeToFile(filePath);

            // 2. READ OPERATION
            readFromFile(filePath);

            // 3. MODIFY OPERATION
            modifyFile(filePath);

            // 4. READ AGAIN TO SHOW THE RESULT
            readFromFile(filePath);

        } catch (IOException e) {
            System.out.println("An error occurred during file operations: " + e.getMessage());
        }
    }

    /**
     * Writes initial content to a file.
     * Creates the file automatically if it does not exist.
     */
    private static void writeToFile(Path path) throws IOException {
        String content = """
                This is a sample text file.
                It contains multiple lines.
                File operations in Java are simple!
                """;

        Files.writeString(path, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        System.out.println("✔ File created and written: " + path.toAbsolutePath());
    }

    /**
     * Reads and prints the file's contents line by line.
     */
    private static void readFromFile(Path path) throws IOException {
        System.out.println("\n--- Reading File ---");
        List<String> lines = Files.readAllLines(path);

        for (String line : lines) {
            System.out.println(line);
        }
    }

    /**
     * Modifies the file by appending a message
     * and replacing a selected word.
     */
    private static void modifyFile(Path path) throws IOException {

        System.out.println("\n--- Modifying File ---");

        // Read all lines
        List<String> lines = Files.readAllLines(path);

        // Example modification: Replace the word "sample" with "modified"
        for (int i = 0; i < lines.size(); i++) {
            lines.set(i, lines.get(i).replace("sample", "modified"));
        }

        // Write modified content back
        Files.write(path, lines, StandardOpenOption.TRUNCATE_EXISTING);

        // Append an extra line
        Files.writeString(path,
                "\nThis line was appended during modification.",
                StandardOpenOption.APPEND);

        System.out.println("✔ File modified successfully.");
    }
}