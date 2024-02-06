package InputOutputStream.ConsoleTextFileEditor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class ConsoleTextFileEditorTest {

    ConsoleTextFileEditor consoleTextFileEditor;

    @BeforeEach
    public void setConsoleTextFileEditor() {
        consoleTextFileEditor = new ConsoleTextFileEditor();
    }

    @Test
    public void directoryMustBeOpened() {
        consoleTextFileEditor.openDirectory(".\\src");
        File directory = new File(".\\src");
        Assertions.assertEquals(directory, consoleTextFileEditor.getCurrentDirectory());
    }

    @Test
    public void fileMustBeOpened() {
        consoleTextFileEditor.openDirectory(".\\src");
        consoleTextFileEditor.createFile("create -f newFile.txt File with text");
        boolean result = consoleTextFileEditor.openFile(".\\src\\newFile.txt");
        Assertions.assertTrue(result);
        consoleTextFileEditor.delete(".\\src\\newFile.txt");
    }

    @Test
    public void fileMustNotBeOpenedBecauseOfExtension() {
        consoleTextFileEditor.openDirectory(".\\src");
        consoleTextFileEditor.createFile("create -f newFile File with text");
        boolean result = consoleTextFileEditor.openFile(".\\src\\newFile");
        Assertions.assertFalse(result);
        consoleTextFileEditor.delete(".\\src\\newFile");
    }

    @Test
    public void fileMustBeCreated() {
        consoleTextFileEditor.openDirectory(".\\src");
        boolean result = consoleTextFileEditor.createFile("create -f newFile File with text");
        Assertions.assertTrue(result);
        consoleTextFileEditor.delete(".\\src\\newFile");
    }

    @Test
    public void fileMustNotBeCreated() {
        consoleTextFileEditor.openDirectory(".\\src");
        consoleTextFileEditor.createFile("create -f newFile File with text");
        boolean result = consoleTextFileEditor.createFile("create -f newFile File with text");
        Assertions.assertFalse(result);
        consoleTextFileEditor.delete(".\\src\\newFile");
    }

    @Test
    public void directoryMustBeCreated() {
        boolean result = consoleTextFileEditor.createDirectory(".\\src\\TestDirectory");
        Assertions.assertTrue(result);
        consoleTextFileEditor.delete(".\\src\\TestDirectory");
    }

    @Test
    public void directoryMustNotBeCreated() {
        consoleTextFileEditor.createDirectory(".\\src\\TestDirectory");
        boolean result = consoleTextFileEditor.createDirectory(".\\src\\TestDirectory");
        Assertions.assertFalse(result);
        consoleTextFileEditor.delete(".\\src\\TestDirectory");
    }

    @Test
    public void directoryMustBeDeleted() {
        consoleTextFileEditor.createDirectory(".\\src\\TestDirectory");
        boolean result = consoleTextFileEditor.delete(".\\src\\TestDirectory");
        Assertions.assertTrue(result);
    }

    @Test
    public void directoryMustNotBeDeleted() {
        consoleTextFileEditor.delete(".\\src\\TestDirectory");
        boolean result = consoleTextFileEditor.delete(".\\src\\TestDirectory");
        Assertions.assertFalse(result);
    }

    @Test
    public void fileMustBeDeleted() {
        consoleTextFileEditor.openDirectory(".\\src");
        consoleTextFileEditor.createFile("create -f newFile File with text");
        boolean result = consoleTextFileEditor.delete(".\\src\\newFile");
        Assertions.assertTrue(result);
    }

    @Test
    public void fileMustNotBeDeleted() {
        consoleTextFileEditor.openDirectory(".\\src");
        boolean result = consoleTextFileEditor.delete(".\\src\\newFile");
        Assertions.assertFalse(result);
    }

    @Test
    public void fileMustBeEdited() throws IOException {
        consoleTextFileEditor.openDirectory(".\\src");
        consoleTextFileEditor.createFile("create -f newFile File with text");
        consoleTextFileEditor.createFile("create -f newFile2 File with text");
        consoleTextFileEditor.editFile("edit newFile nex text");
        Path path1 = Path.of(".\\src\\newFile");
        Path path2 = Path.of(".\\src\\newFile2");
        long res = filesCompareByByte(path1, path2);
        Assertions.assertNotEquals(-1, res);
        consoleTextFileEditor.delete(".\\src\\newFile");
        consoleTextFileEditor.delete(".\\src\\newFile2");
    }

    private long filesCompareByByte(Path path1, Path path2) throws IOException {
        try (BufferedInputStream fis1 = new BufferedInputStream(new FileInputStream(path1.toFile()));
             BufferedInputStream fis2 = new BufferedInputStream(new FileInputStream(path2.toFile()))) {

            int ch = 0;
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
        consoleTextFileEditor.openDirectory(".\\src");
        consoleTextFileEditor.createFile("create -f newFile.txt File with text");
        boolean result = consoleTextFileEditor.renameTo(".\\src\\newFile.txt", "newName.txt");
        Assertions.assertTrue(result);
        consoleTextFileEditor.delete(".\\src\\newName.txt");
    }

    @Test
    public void checkInfo() {
        consoleTextFileEditor.openDirectory(".\\src");
        consoleTextFileEditor.createFile("create -f newFile.txt File with text");
        Map<String, String> map = consoleTextFileEditor.getInfo(".\\src\\newFile.txt");

        boolean absolutePath = map.get("Absolute path: ").endsWith(".\\src\\newFile.txt");

        boolean size = map.get("Size: ").equals("14 bytes");

        String creationTime = map.get("Creation time: ");

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        String currentDateTime = dateFormat.format(currentDate);

        Assertions.assertTrue(absolutePath);
        Assertions.assertTrue(size);
        Assertions.assertEquals(currentDateTime, creationTime);

        consoleTextFileEditor.delete(".\\src\\newFile.txt");
    }
}