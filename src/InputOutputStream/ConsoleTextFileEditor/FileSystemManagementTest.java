package InputOutputStream.ConsoleTextFileEditor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class FileSystemManagementTest {

    private FileSystemManagement manager;

    @Test
    public void directoryMustNotBeReturnedAndOpenedBecauseDirectoryDoesNotExist() {
        manager = new FileSystemManagement();
        FileSystemResponse<List<String>> response = manager.getDirectory(".\\src\\src");
        String message = response.getMessageError();

        Assertions.assertEquals("Directory src does not exist.", message);
    }

    @Test
    public void fileMustBeOpenedAndRead() {
        manager = new FileSystemManagement();
        ConsoleTextFileEditor consoleTextFileEditor = new ConsoleTextFileEditor();
        consoleTextFileEditor.openDirectory(".\\src");
        consoleTextFileEditor.createFile("newFile.txt", "File with text");
        FileSystemResponse<StringBuilder> response = manager.getFile(".\\src\\newFile.txt");

        consoleTextFileEditor.delete(".\\src\\newFile.txt");

        Assertions.assertEquals("File with text\n", response.getBody().toString());
    }

    @Test
    public void fileMustNotBeOpenedBecauseOfExtension() {
        manager = new FileSystemManagement();
        manager.getDirectory(".\\src");
        manager.createFile("newFile", "File with text");
        FileSystemResponse<StringBuilder> response = manager.getFile("newFile");
        String message = response.getMessageError();

        manager.delete(".\\src\\newFile", false);

        Assertions.assertEquals("The file extension is incorrect.", message);
    }

    @Test
    public void fileMustNotBeCreatedBecauseFileWithThisNameAlreadyExists() {
        manager = new FileSystemManagement();
        manager.getDirectory(".\\src");
        manager.createFile("newFile", "File with text");
        FileSystemResponse<Boolean> response = manager.createFile("newFile", "File with text");
        String message = response.getMessageError();

        manager.delete(".\\src\\newFile", false);

        Assertions.assertEquals("File with this name already exists.", message);
    }

    @Test
    public void directoryMustNotBeCreatedBecauseDirectoryWithThisNameAlreadyExists() {
        manager = new FileSystemManagement();
        manager.createDirectory(".\\src\\TestDirectory");
        FileSystemResponse<Boolean> response = manager.createDirectory(".\\src\\TestDirectory");
        String message = response.getMessageError();

        manager.delete(".\\src\\TestDirectory", false);

        Assertions.assertEquals("Directory with this name already exists.", message);
    }

    @Test
    public void directoryMustNotBeDeletedBecauseDirectoryDoesNotExist() {
        manager = new FileSystemManagement();
        FileSystemResponse<Boolean> response = manager.delete(".\\src\\TestDirectory", false);
        String message = response.getMessageError();

        Assertions.assertEquals("Directory or file TestDirectory does not exist.", message);
    }

    @Test
    public void directoryMustNotBeDeletedBecauseDirectoryIsNotEmpty() {
        manager = new FileSystemManagement();
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
    public void fileMustNotBeDeletedBecauseFileDoesNotExist() {
        manager = new FileSystemManagement();
        FileSystemResponse<Boolean> response = manager.delete(".\\src\\newFile", false);
        String message = response.getMessageError();

        Assertions.assertEquals("Directory or file newFile does not exist.", message);
    }

    @Test
    public void fileMustNotBeEditedBecauseFileDoesNotExist() {
        manager = new FileSystemManagement();
        FileSystemResponse<Boolean> response = manager.editFile("newFile", "New text");
        String message = response.getMessageError();

        Assertions.assertEquals("File newFile does not exist.", message);
    }

    @Test
    public void fileMustNotBeRenamedBecauseFileWithThisNameAlreadyExists() {
        manager = new FileSystemManagement();
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
        manager = new FileSystemManagement();
        FileSystemResponse<Boolean> response = manager.renameTo("newFile", "newFile2");
        String message = response.getMessageError();

        Assertions.assertEquals("Directory or file newFile does not exist.", message);
    }

    @Test
    public void directoryMustNotBeRenamedBecauseDirectoryWithThisNameAlreadyExists() {
        manager = new FileSystemManagement();
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
        manager = new FileSystemManagement();
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
        manager = new FileSystemManagement();
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
        manager = new FileSystemManagement();
        FileSystemResponse<Map<String, String>> response = manager.getInfo("dir33");
        String message = response.getMessageError();

        Assertions.assertEquals("Directory or file dir33 does not exist.", message);
    }
}
