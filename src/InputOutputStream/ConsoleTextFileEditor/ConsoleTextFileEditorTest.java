package InputOutputStream.ConsoleTextFileEditor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConsoleTextFileEditorTest {

    private ConsoleTextFileEditor consoleTextFileEditor;

    @Test
    public void directoryMustBeOpened() {
        consoleTextFileEditor = new ConsoleTextFileEditor();
        consoleTextFileEditor.openDirectory("src");
        File directory = new File("src");

        Assertions.assertEquals(directory, consoleTextFileEditor.getCurrentDirectory());
    }

    @Test
    public void fileMustBeOpened() throws IOException {
        consoleTextFileEditor = new ConsoleTextFileEditor();
        consoleTextFileEditor.openDirectory("src");
        consoleTextFileEditor.createFile("newFile.txt", "File with text");
        boolean result = consoleTextFileEditor.openFile("newFile.txt");

        Files.delete(Paths.get("src\\newFile.txt"));

        Assertions.assertTrue(result);
    }

    @Test
    public void fileMustBeCreated() throws IOException {
        consoleTextFileEditor = new ConsoleTextFileEditor();
        consoleTextFileEditor.openDirectory("src");
        String messageIsSuccess = consoleTextFileEditor.createFile("newFile", "File with text");

        Files.delete(Paths.get("src\\newFile"));

        Assertions.assertEquals(FileSystemManagement.MESSAGE_FORMAT_SUCCESS, messageIsSuccess);
    }

    @Test
    public void directoryMustBeCreated() throws IOException {
        consoleTextFileEditor = new ConsoleTextFileEditor();
        consoleTextFileEditor.openDirectory("src");
        String messageIsSuccess = consoleTextFileEditor.createDirectory("TestDirectory");

        Files.delete(Paths.get("src\\TestDirectory"));

        Assertions.assertEquals(FileSystemManagement.MESSAGE_FORMAT_SUCCESS, messageIsSuccess);
    }

    @Test
    public void directoryMustBeDeleted() throws IOException {
        consoleTextFileEditor = new ConsoleTextFileEditor();
        Files.createDirectory(Paths.get("src\\TestDirectory"));
        String messageIsSuccess = consoleTextFileEditor.delete("src\\TestDirectory");

        Assertions.assertEquals(FileSystemManagement.MESSAGE_FORMAT_SUCCESS, messageIsSuccess);
    }

    @Test
    public void fileMustBeDeleted() throws IOException {
        consoleTextFileEditor = new ConsoleTextFileEditor();
        Files.createFile(Paths.get("src\\newFile"));
        String messageIsSuccess = consoleTextFileEditor.delete("src\\newFile");

        Assertions.assertEquals(FileSystemManagement.MESSAGE_FORMAT_SUCCESS, messageIsSuccess);
    }

    @Test
    public void fileMustBeEdited() throws IOException {
        consoleTextFileEditor = new ConsoleTextFileEditor();
        consoleTextFileEditor.openDirectory("src");
        consoleTextFileEditor.createFile("newFile", "File with text");
        consoleTextFileEditor.createFile("newFile2", "File with text");
        consoleTextFileEditor.editFile("newFile", "next text");
        Path path1 = Paths.get("src\\newFile");
        Path path2 = Paths.get("src\\newFile2");
        boolean filesAreSame = isFilesTheSame(path1, path2);

        Files.delete(path1);
        Files.delete(path2);

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
    public void fileMustBeRenamed() throws IOException {
        consoleTextFileEditor = new ConsoleTextFileEditor();
        Files.createFile(Paths.get("src\\oldName.txt"));

        consoleTextFileEditor.openDirectory("src");
        String messageIsSuccess = consoleTextFileEditor.renameTo("oldName.txt", "newName.txt");

        Files.delete(Paths.get("src\\newName.txt"));

        Assertions.assertEquals(FileSystemManagement.MESSAGE_FORMAT_SUCCESS, messageIsSuccess);
    }

    @Test
    public void directoryMustBeRenamed() throws IOException {
        consoleTextFileEditor = new ConsoleTextFileEditor();
        Files.createDirectory(Paths.get("src\\directory"));

        consoleTextFileEditor.openDirectory("src");
        String messageIsSuccess = consoleTextFileEditor.renameTo("directory", "newDirectory");

        Files.delete(Paths.get("src\\newDirectory"));

        Assertions.assertEquals(FileSystemManagement.MESSAGE_FORMAT_SUCCESS, messageIsSuccess);
    }
}