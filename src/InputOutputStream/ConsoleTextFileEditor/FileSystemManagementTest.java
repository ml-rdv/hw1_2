package InputOutputStream.ConsoleTextFileEditor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class FileSystemManagementTest {

    private FileSystemManagement manager;

    @Test
    public void directoryMustNotBeReturnedAndOpenedBecauseDirectoryDoesNotExist() {
        manager = new FileSystemManagement();
        FileSystemResponse<List<String>> response = manager.getDirectory("src\\src");
        String message = response.getMessageError();

        Assertions.assertEquals("Directory src does not exist.", message);
    }

    @Test
    public void fileMustBeOpenedAndRead() throws IOException {
        manager = new FileSystemManagement();
        manager.getDirectory("src");
        manager.createFile("newFile.txt", "File with text");
        FileSystemResponse<StringBuilder> response = manager.getTextFileContents("newFile.txt");

        Files.delete(Paths.get("src\\newFile.txt"));

        Assertions.assertEquals("File with text\n", response.getBody().toString());
    }

    @Test
    public void fileMustNotBeOpenedBecauseOfExtension() throws IOException {
        manager = new FileSystemManagement();
        manager.getDirectory("src");

        Path path = Paths.get("src\\newFile");
        Files.createFile(path);

        FileSystemResponse<StringBuilder> response = manager.getTextFileContents("newFile");
        String message = response.getMessageError();

        Files.delete(path);

        Assertions.assertEquals("The file extension is incorrect.", message);
    }

    @Test
    public void fileMustNotBeCreatedBecauseFileWithThisNameAlreadyExists() throws IOException {
        manager = new FileSystemManagement();
        manager.getDirectory("src");

        Path path = Paths.get("src\\newFile");
        Files.createFile(path);

        FileSystemResponse<Boolean> response = manager.createFile("newFile", "File with text");
        String message = response.getMessageError();

        Files.delete(path);

        Assertions.assertEquals("File with this name already exists.", message);
    }

    @Test
    public void directoryMustNotBeCreatedBecauseDirectoryWithThisNameAlreadyExists() throws IOException {
        manager = new FileSystemManagement();
        manager.getDirectory("src");

        Path path = Paths.get("src\\TestDirectory");
        Files.createFile(path);

        FileSystemResponse<Boolean> response = manager.createDirectory("TestDirectory");
        String message = response.getMessageError();

        Files.delete(path);

        Assertions.assertEquals("Directory with this name already exists.", message);
    }

    @Test
    public void directoryMustNotBeDeletedBecauseDirectoryDoesNotExist() {
        manager = new FileSystemManagement();
        FileSystemResponse<Boolean> response = manager.delete("src\\TestDirectory");
        String message = response.getMessageError();

        Assertions.assertEquals("Directory or file TestDirectory does not exist.", message);
    }

    @Test
    public void directoryMustNotBeDeletedWithNestedDirectoriesBecauseDirectoryDoesNotExist() {
        manager = new FileSystemManagement();
        FileSystemResponse<Boolean> response = manager.deleteWithNestedDirectories("src\\TestDirectory");
        String message = response.getMessageError();

        Assertions.assertEquals("Directory or file TestDirectory does not exist.", message);
    }

    @Test
    public void directoryMustNotBeDeletedBecauseDirectoryIsNotEmpty() throws IOException {
        manager = new FileSystemManagement();

        Path newFilePath = Paths.get("src\\p1");
        Path newFilePath2 = Paths.get("src\\p1\\p2");

        Files.createDirectory(newFilePath);
        Files.createDirectory(newFilePath2);

        FileSystemResponse<Boolean> response = manager.delete("src\\p1");

        Files.delete(newFilePath2);
        Files.delete(newFilePath);

        String message = response.getMessageError();

        Assertions.assertEquals("Directory is not empty.", message);
    }

    @Test
    public void fileMustNotBeDeletedBecauseFileDoesNotExist() {
        manager = new FileSystemManagement();
        FileSystemResponse<Boolean> response = manager.delete("src\\newFile");
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
    public void fileMustNotBeRenamedBecauseFileWithThisNameAlreadyExists() throws IOException {
        manager = new FileSystemManagement();
        manager.getDirectory("src");

        Path path1 = Paths.get("src\\newFile");
        Path path2 = Paths.get("src\\newFile2");
        Files.createFile(path1);
        Files.createFile(path2);

        FileSystemResponse<Boolean> response = manager.renameTo("newFile", "newFile2");
        String message = response.getMessageError();

        Files.delete(path1);
        Files.delete(path2);

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
    public void directoryMustNotBeRenamedBecauseDirectoryWithThisNameAlreadyExists() throws IOException {
        manager = new FileSystemManagement();
        manager.getDirectory("src");

        Path path1 = Paths.get("src\\newFile");
        Path path2 = Paths.get("src\\newFile2");
        Files.createDirectory(path1);
        Files.createDirectory(path2);

        FileSystemResponse<Boolean> response = manager.renameTo("newFile", "newFile2");
        String message = response.getMessageError();

        Files.delete(path1);
        Files.delete(path2);

        Assertions.assertEquals("Directory or file with this name already exists.", message);
    }

    @Test
    public void shouldReturnInfoAboutFile() throws IOException {
        manager = new FileSystemManagement();
        manager.getDirectory("src");
        manager.createFile("newFile.txt", "new file");
        FileSystemResponse<Map<String, String>> response = manager.getInfo("newFile.txt");
        Map<String, String> map = response.getBody();

        String absolutePathExpected = Path.of("src/newFile.txt").toAbsolutePath().toString();

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        String currentDateTime = dateFormat.format(currentDate);

        Files.delete(Paths.get("src\\newFile.txt"));

        Assertions.assertEquals(absolutePathExpected, map.get("Absolute path: "));
        Assertions.assertEquals("8 bytes", map.get("Size: "));
        Assertions.assertEquals(currentDateTime, map.get("Creation time: "));
    }

    @Test
    public void shouldReturnInfoAboutDirectory() throws IOException {
        manager = new FileSystemManagement();
        manager.getDirectory("src");

        Path path = Paths.get("src\\newDirectory");
        Files.createDirectory(path);
        Files.createDirectory(Paths.get("src\\newDirectory\\nestedDirectory1"));
        Files.createDirectory(Paths.get("src\\newDirectory\\nestedDirectory2"));
        Files.createFile(Paths.get("src\\newDirectory\\nestedDirectory1\\nestedFile.txt"));

        FileSystemResponse<Map<String, String>> response = manager.getInfo("newDirectory");
        Map<String, String> map = response.getBody();

        String absolutePathExpected = path.toAbsolutePath().toString();

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        String currentDateTime = dateFormat.format(currentDate);

        manager.deleteWithNestedDirectories("src\\newDirectory");

        Assertions.assertEquals(absolutePathExpected, map.get("Absolute path: "));
        Assertions.assertEquals("0 bytes", map.get("Size: "));
        Assertions.assertEquals("3", map.get("Number of all nested elements: "));
        Assertions.assertEquals("2", map.get("Number of elements: "));
        Assertions.assertEquals(currentDateTime, map.get("Creation time: "));
    }

    @Test
    public void shouldNotReturnInfoAboutDirectoryBecauseDirectoryDoesNotExist() {
        manager = new FileSystemManagement();
        FileSystemResponse<Map<String, String>> response = manager.getInfo("dir33");
        String message = response.getMessageError();

        Assertions.assertEquals("Directory or file dir33 does not exist.", message);
    }
}
