package InputOutputStream.ConsoleTextFileEditor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConsoleTextFileEditorTest {

    private ConsoleTextFileEditor consoleTextFileEditor;

    @Test
    public void directoryMustBeOpened() {
        consoleTextFileEditor = new ConsoleTextFileEditor();
        consoleTextFileEditor.openDirectory(".\\src");
        File directory = new File(".\\src");

        Assertions.assertEquals(directory, consoleTextFileEditor.getCurrentDirectory());
    }

    @Test
    public void fileMustBeOpened() {
        consoleTextFileEditor = new ConsoleTextFileEditor();
        consoleTextFileEditor.openDirectory(".\\src");
        consoleTextFileEditor.createFile("newFile.txt", "File with text");
        boolean result = consoleTextFileEditor.openFile("newFile.txt");

        consoleTextFileEditor.delete(".\\src\\newFile.txt");

        Assertions.assertTrue(result);
    }

    @Test
    public void fileMustBeCreated() {
        consoleTextFileEditor = new ConsoleTextFileEditor();
        consoleTextFileEditor.openDirectory(".\\src");
        String messageIsSuccess = consoleTextFileEditor.createFile("newFile", "File with text");
        consoleTextFileEditor.delete(".\\src\\newFile");

        Assertions.assertEquals(FileSystemManagement.MESSAGE_FORMAT_SUCCESS, messageIsSuccess);
    }

    @Test
    public void directoryMustBeCreated() {
        consoleTextFileEditor = new ConsoleTextFileEditor();
        String messageIsSuccess = consoleTextFileEditor.createDirectory(".\\src\\TestDirectory");

        consoleTextFileEditor.delete(".\\src\\TestDirectory");

        Assertions.assertEquals(FileSystemManagement.MESSAGE_FORMAT_SUCCESS, messageIsSuccess);
    }

    @Test
    public void directoryMustBeDeleted() {
        consoleTextFileEditor = new ConsoleTextFileEditor();
        consoleTextFileEditor.createDirectory(".\\src\\TestDirectory");
        String messageIsSuccess = consoleTextFileEditor.delete(".\\src\\TestDirectory");

        Assertions.assertEquals(FileSystemManagement.MESSAGE_FORMAT_SUCCESS, messageIsSuccess);
    }

    @Test
    public void fileMustBeDeleted() {
        consoleTextFileEditor = new ConsoleTextFileEditor();
        consoleTextFileEditor.openDirectory(".\\src");
        consoleTextFileEditor.createFile("newFile", "File with text");
        String messageIsSuccess = consoleTextFileEditor.delete(".\\src\\newFile");

        Assertions.assertEquals(FileSystemManagement.MESSAGE_FORMAT_SUCCESS, messageIsSuccess);
    }

    @Test
    public void fileMustBeEdited() throws IOException {
        consoleTextFileEditor = new ConsoleTextFileEditor();
        consoleTextFileEditor.openDirectory(".\\src");
        consoleTextFileEditor.createFile("newFile", "File with text");
        consoleTextFileEditor.createFile("newFile2", "File with text");
        consoleTextFileEditor.editFile("newFile", "next text");
        Path path1 = Path.of(".\\src\\newFile");
        Path path2 = Path.of(".\\src\\newFile2");
        boolean filesAreSame = isFilesTheSame(path1, path2);

        consoleTextFileEditor.delete(".\\src\\newFile");
        consoleTextFileEditor.delete(".\\src\\newFile2");

        Assertions.assertFalse(filesAreSame);
    }

    private boolean isFilesTheSame(Path path1, Path path2) throws IOException {
        try (BufferedReader bf1 = Files.newBufferedReader(path1);
             BufferedReader bf2 = Files.newBufferedReader(path2)) {

            String line1, line2;
            while ((line1 = bf1.readLine()) != null) {
                line2 = bf2.readLine();
                if (!line1.equals(line2)) {
                    return false;
                }
            }
            return bf2.readLine() == null;
        }
    }

    @Test
    public void fileMustBeRenamed() {
        consoleTextFileEditor = new ConsoleTextFileEditor();
        consoleTextFileEditor.openDirectory(".\\src");
        consoleTextFileEditor.createFile("newFile.txt", "File with text");
        String messageIsSuccess = consoleTextFileEditor.renameTo("newFile.txt", "newName.txt");

        consoleTextFileEditor.delete(".\\src\\newName.txt");

        Assertions.assertEquals(FileSystemManagement.MESSAGE_FORMAT_SUCCESS, messageIsSuccess);
    }

    @Test
    public void directoryMustBeRenamed() {
        consoleTextFileEditor = new ConsoleTextFileEditor();
        consoleTextFileEditor.openDirectory(".\\src");
        consoleTextFileEditor.createDirectory("directory");
        String messageIsSuccess = consoleTextFileEditor.renameTo("directory", "newDirectory");

        consoleTextFileEditor.delete(".\\src\\newDirectory.txt");

        Assertions.assertEquals(FileSystemManagement.MESSAGE_FORMAT_SUCCESS, messageIsSuccess);
    }
}