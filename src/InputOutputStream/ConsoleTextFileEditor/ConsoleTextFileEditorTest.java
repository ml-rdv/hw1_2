package InputOutputStream.ConsoleTextFileEditor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
        long res = filesCompareByByte(path1, path2);

        consoleTextFileEditor.delete(".\\src\\newFile");
        consoleTextFileEditor.delete(".\\src\\newFile2");

        Assertions.assertNotEquals(-1, res);
    }

    private long filesCompareByByte(Path path1, Path path2) throws IOException {
        try (BufferedInputStream fis1 = new BufferedInputStream(new FileInputStream(path1.toFile()));
             BufferedInputStream fis2 = new BufferedInputStream(new FileInputStream(path2.toFile()))) {

            int ch;
            long pos = 1;
            while ((ch = fis1.read()) != -1) {
                if (ch != fis2.read()) {
                    return pos;
                }
                pos++;
            }
            if (fis2.read() == -1) {
                // files are the same
                return -1;
            } else {
                // files are not the same
                return pos;
            }
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