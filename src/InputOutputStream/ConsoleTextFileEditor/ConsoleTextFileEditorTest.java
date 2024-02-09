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
import java.util.List;
import java.util.Map;

public class ConsoleTextFileEditorTest {

    ConsoleTextFileEditor consoleTextFileEditor;
    FileSystemManagement manager;

    @BeforeEach
    public void setEditorAndManager() {
        consoleTextFileEditor = new ConsoleTextFileEditor();
        manager = new FileSystemManagement();
    }

    @Test
    public void directoryMustBeOpened() {
        consoleTextFileEditor.openDirectory(".\\src");
        File directory = new File(".\\src");

        Assertions.assertEquals(directory, consoleTextFileEditor.getCurrentDirectory());
    }

    @Test
    public void directoryMustNotBeReturnedAndOpenedBecauseDirectoryDoesNotExist() {
        FileSystemResponse<List<String>> response = manager.getDirectory(".\\src\\src");
        String message = response.getMessageError();

        Assertions.assertEquals("Directory src does not exist.", message);
    }

    @Test
    public void fileMustBeOpened() {
        consoleTextFileEditor.openDirectory(".\\src");
        consoleTextFileEditor.createFile("newFile.txt", "File with text");
        boolean result = consoleTextFileEditor.openFile("newFile.txt");

        consoleTextFileEditor.delete(".\\src\\newFile.txt");

        Assertions.assertTrue(result);
    }

    @Test
    public void fileMustNotBeOpenedBecauseOfExtension() {
        manager.getDirectory(".\\src");
        manager.createFile("newFile", "File with text");
        FileSystemResponse<StringBuilder> response = manager.getFile("newFile");
        String message = response.getMessageError();

        manager.delete(".\\src\\newFile", false);

        Assertions.assertEquals("The file extension is incorrect.", message);
    }

    @Test
    public void fileMustBeOpenedAndRead() {
        consoleTextFileEditor.openDirectory(".\\src");
        consoleTextFileEditor.createFile("newFile.txt", "File with text");
        FileSystemResponse<StringBuilder> response = manager.getFile(".\\src\\newFile.txt");

        consoleTextFileEditor.delete(".\\src\\newFile.txt");

        Assertions.assertEquals("File with text\n", response.getBody().toString());
    }

    @Test
    public void fileMustBeCreated() {
        consoleTextFileEditor.openDirectory(".\\src");
        boolean result = consoleTextFileEditor.createFile("newFile", "File with text");

        consoleTextFileEditor.delete(".\\src\\newFile");

        Assertions.assertTrue(result);
    }

    @Test
    public void fileMustNotBeCreatedBecauseFileWithThisNameAlreadyExists() {
        manager.getDirectory(".\\src");
        manager.createFile("newFile", "File with text");
        FileSystemResponse<Boolean> response = manager.createFile("newFile", "File with text");
        String message = response.getMessageError();

        manager.delete(".\\src\\newFile", false);

        Assertions.assertEquals("File with this name already exists.", message);
    }

    @Test
    public void directoryMustBeCreated() {
        boolean result = consoleTextFileEditor.createDirectory(".\\src\\TestDirectory");

        consoleTextFileEditor.delete(".\\src\\TestDirectory");

        Assertions.assertTrue(result);
    }

    @Test
    public void directoryMustNotBeCreatedBecauseDirectoryWithThisNameAlreadyExists() {
        manager.createDirectory(".\\src\\TestDirectory");
        FileSystemResponse<Boolean> response = manager.createDirectory(".\\src\\TestDirectory");
        String message = response.getMessageError();

        manager.delete(".\\src\\TestDirectory", false);

        Assertions.assertEquals("Directory with this name already exists.", message);
    }

    @Test
    public void directoryMustBeDeleted() {
        consoleTextFileEditor.createDirectory(".\\src\\TestDirectory");
        boolean result = consoleTextFileEditor.delete(".\\src\\TestDirectory");

        Assertions.assertTrue(result);
    }

    @Test
    public void directoryMustNotBeDeletedBecauseDirectoryDoesNotExist() {
        FileSystemResponse<Boolean> response = manager.delete(".\\src\\TestDirectory", false);
        String message = response.getMessageError();

        Assertions.assertEquals("Directory or file TestDirectory does not exist.", message);
    }

    @Test
    public void directoryMustNotBeDeletedBecauseDirectoryIsNotEmpty() {
        manager.getDirectory(".\\src");
        manager.createDirectory("p1");
        manager.getDirectory(".\\src\\p1");
        manager.createDirectory("p2");
        FileSystemResponse<Boolean> response = manager.delete(".\\src\\p1", false);
        String message = response.getMessageError();

        manager.delete(".\\src\\p1", true);

        Assertions.assertEquals("Directory is not empty.", message);
    }

    @Test
    public void fileMustBeDeleted() {
        consoleTextFileEditor.openDirectory(".\\src");
        consoleTextFileEditor.createFile("newFile", "File with text");
        boolean result = consoleTextFileEditor.delete(".\\src\\newFile");

        Assertions.assertTrue(result);
    }

    @Test
    public void fileMustNotBeDeletedBecauseFileDoesNotExist() {
        FileSystemResponse<Boolean> response = manager.delete(".\\src\\newFile", false);
        String message = response.getMessageError();

        Assertions.assertEquals("Directory or file newFile does not exist.", message);
    }

    @Test
    public void fileMustBeEdited() throws IOException {
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

    @Test
    public void fileMustNotBeEditedBecauseFileDoesNotExist() {
        FileSystemResponse<Boolean> response = manager.editFile("newFile", "New text");
        String message = response.getMessageError();

        Assertions.assertEquals("File newFile does not exist.", message);
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
        consoleTextFileEditor.openDirectory(".\\src");
        consoleTextFileEditor.createFile("newFile.txt", "File with text");
        boolean result = consoleTextFileEditor.renameTo("newFile.txt", "newName.txt");

        consoleTextFileEditor.delete(".\\src\\newName.txt");

        Assertions.assertTrue(result);
    }

    @Test
    public void fileMustNotBeRenamedBecauseFileWithThisNameAlreadyExists() {
        manager.getDirectory(".\\src");
        manager.createFile("newFile", "File with text");
        manager.createFile("newFile2", "File with text");
        FileSystemResponse<Boolean> response = manager.renameTo("newFile", "newFile2");
        String message = response.getMessageError();

        manager.delete(".\\src\\newFile", false);
        manager.delete(".\\src\\newFile2", false);

        Assertions.assertEquals("Directory or file with this name already exists.", message);
    }

    @Test
    public void fileMustNotBeRenamedBecauseFileDoesNotExist() {
        FileSystemResponse<Boolean> response = manager.renameTo("newFile", "newFile2");
        String message = response.getMessageError();

        Assertions.assertEquals("Directory or file newFile does not exist.", message);
    }

    @Test
    public void directoryMustBeRenamed() {
        consoleTextFileEditor.openDirectory(".\\src");
        consoleTextFileEditor.createDirectory("directory");
        boolean result = consoleTextFileEditor.renameTo("directory", "newDirectory");

        consoleTextFileEditor.delete(".\\src\\newDirectory.txt");

        Assertions.assertTrue(result);
    }

    @Test
    public void directoryMustNotBeRenamedBecauseDirectoryWithThisNameAlreadyExists() {
        manager.getDirectory(".\\src");
        manager.createDirectory("newFile");
        manager.createDirectory("newFile2");
        FileSystemResponse<Boolean> response = manager.renameTo("newFile", "newFile2");
        String message = response.getMessageError();

        manager.delete(".\\src\\newFile", false);
        manager.delete(".\\src\\newFile2", false);

        Assertions.assertEquals("Directory or file with this name already exists.", message);
    }

    @Test
    public void shouldReturnInfoAboutFile() {
        manager.getDirectory(".\\src");
        manager.createFile("newFile.txt", "new file");
        FileSystemResponse<Map<String, String>> response = manager.getInfo("newFile.txt");
        Map<String, String> map = response.getBody();

        boolean absolutePath = map.get("Absolute path: ").endsWith(".\\src\\newFile.txt");

        boolean size = map.get("Size: ").equals("8 bytes");
        String creationTime = map.get("Creation time: ");

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        String currentDateTime = dateFormat.format(currentDate);

        manager.delete(".\\src\\newFile.txt", false);

        Assertions.assertTrue(absolutePath);
        Assertions.assertTrue(size);
        Assertions.assertEquals(currentDateTime, creationTime);
    }

    @Test
    public void shouldReturnInfoAboutDirectory() {
        manager.getDirectory(".\\src");
        manager.createDirectory("newDirectory");
        FileSystemResponse<Map<String, String>> response = manager.getInfo("newDirectory");
        Map<String, String> map = response.getBody();

        boolean absolutePath = map.get("Absolute path: ").endsWith(".\\src\\newDirectory");

        boolean size = map.get("Size: ").equals("0 bytes");

        String creationTime = map.get("Creation time: ");

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        String currentDateTime = dateFormat.format(currentDate);

        manager.delete(".\\src\\newDirectory", false);

        Assertions.assertTrue(absolutePath);
        Assertions.assertTrue(size);
        Assertions.assertEquals(currentDateTime, creationTime);
    }

    @Test
    public void shouldNotReturnInfoAboutDirectoryBecauseDirectoryDoesNotExist() {
        FileSystemResponse<Map<String, String>> response = manager.getInfo("dir33");
        String message = response.getMessageError();

        Assertions.assertEquals("Directory or file dir33 does not exist.", message);
    }
}