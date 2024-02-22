package InputOutputStream.ConsoleTextFileEditor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class FileSystemManagementTest {

    private FileSystemManagement manager;

    @BeforeEach
    void prepareWorkDirectory() throws IOException {
        var testPath = Path.of("work");
        var fileSystemManagement = new FileSystemManagement("");
        fileSystemManagement.deleteWithNestedDirectories(testPath.toString());

        Files.createDirectory(testPath);
    }

    @Test
    public void directoryMustNotBeReturnedAndOpenedBecauseDirectoryDoesNotExist() {
        manager = new FileSystemManagement();
        FileSystemResponse<Boolean> response = manager.setDirectory("work\\work");
        String message = response.getMessageError();

        Assertions.assertEquals("Directory work does not exist.", message);
        Assertions.assertTrue(Files.notExists(Path.of("work\\work")));
    }

    @Test
    public void fileMustBeOpenedAndRead() throws IOException {
        manager = new FileSystemManagement("work");
        manager.createFile("newFile.txt", "File with text");
        FileSystemResponse<String> response = manager.getTextFileContents("newFile.txt");

        Files.delete(Paths.get("work\\newFile.txt"));

        Assertions.assertEquals("File with text\n", response.getBody());
    }

    @Test
    public void fileMustNotBeOpenedBecauseOfExtension() throws IOException {
        manager = new FileSystemManagement("work");

        Path path = Paths.get("work\\newFile");
        Files.createFile(path);

        FileSystemResponse<String> response = manager.getTextFileContents("newFile");
        String message = response.getMessageError();

        Files.delete(path);

        Assertions.assertEquals("The file extension is incorrect.", message);
    }

    @Test
    public void fileMustNotBeCreatedBecauseFileWithThisNameAlreadyExists() throws IOException {
        manager = new FileSystemManagement("work");

        Path path = Paths.get("work\\newFile");
        Files.createFile(path);

        FileSystemResponse<String> response = manager.createFile("newFile", "File with text");

        String messageError = response.getMessageError();
        String messageBody = response.getBody();

        Files.delete(path);

        Assertions.assertEquals("File with this name already exists.", messageError);
        Assertions.assertEquals("File newFile has been not created.\n", messageBody);
    }

    @Test
    public void fileMustBeCreated() throws IOException {
        manager = new FileSystemManagement("work");

        FileSystemResponse<String> response = manager.createFile("newFile", "File with text");
        String messageBody = response.getBody();

        Path path = Path.of("work\\newFile");

        Assertions.assertEquals("File newFile has been successfully created.\n", messageBody);
        Assertions.assertTrue(Files.exists(path));

        Files.delete(path);
    }

    @Test
    public void directoryMustNotBeCreatedBecauseDirectoryWithThisNameAlreadyExists() throws IOException {
        manager = new FileSystemManagement("work");

        Path path = Paths.get("work\\TestDirectory");
        Files.createFile(path);

        FileSystemResponse<String> response = manager.createDirectory("TestDirectory");

        String messageError = response.getMessageError();
        String messageBody = response.getBody();

        Files.delete(path);

        Assertions.assertEquals("Directory with this name already exists.", messageError);
        Assertions.assertEquals("Directory TestDirectory has been not created.\n", messageBody);
    }

    @Test
    public void directoryMustBeCreated() throws IOException {
        manager = new FileSystemManagement("work");

        FileSystemResponse<String> response = manager.createDirectory("newDir");
        String messageBody = response.getBody();

        Path path = Path.of("work\\newDir");

        Assertions.assertEquals("Directory newDir has been successfully created.\n", messageBody);
        Assertions.assertTrue(Files.exists(path));

        Files.delete(path);
    }

    @Test
    public void directoryMustBeDeleted() throws IOException {
        manager = new FileSystemManagement();
        Path path = Paths.get("work\\newDir");
        Files.createDirectory(path);

        FileSystemResponse<String> response = manager.delete("work\\newDir");
        String messageBody = response.getBody();

        Assertions.assertEquals("Directory or file newDir has been successfully deleted.\n", messageBody);
        Assertions.assertTrue(Files.notExists(path));
    }

    @Test
    public void directoryMustNotBeDeletedBecauseDirectoryDoesNotExist() {
        manager = new FileSystemManagement();
        FileSystemResponse<String> response = manager.delete("work\\TestDirectory");

        String messageError = response.getMessageError();
        String messageBody = response.getBody();

        Assertions.assertEquals("Directory or file TestDirectory does not exist.", messageError);
        Assertions.assertEquals("Directory or file TestDirectory has been not deleted.\n", messageBody);
        Assertions.assertTrue(Files.notExists(Path.of("work\\TestDirectory")));
    }

    @Test
    public void directoryMustNotBeDeletedWithNestedDirectoriesBecauseDirectoryDoesNotExist() {
        manager = new FileSystemManagement();
        FileSystemResponse<String> response = manager.deleteWithNestedDirectories("work\\TestDirectory");

        String messageError = response.getMessageError();
        String messageBody = response.getBody();

        Assertions.assertEquals("Directory or file TestDirectory does not exist.", messageError);
        Assertions.assertEquals("Directory or file TestDirectory has been not deleted.\n", messageBody);
        Assertions.assertTrue(Files.notExists(Path.of("work\\TestDirectory")));
    }

    @Test
    public void directoryMustNotBeDeletedBecauseDirectoryIsNotEmpty() throws IOException {
        manager = new FileSystemManagement();

        Path newFilePath = Paths.get("work\\p1");
        Path newFilePath2 = Paths.get("work\\p1\\p2");

        Files.createDirectory(newFilePath);
        Files.createDirectory(newFilePath2);

        FileSystemResponse<String> response = manager.delete("work\\p1");

        String messageError = response.getMessageError();
        String messageBody = response.getBody();

        Assertions.assertEquals("Directory is not empty.", messageError);
        Assertions.assertEquals("Directory or file p1 has been not deleted.\n", messageBody);
        Assertions.assertTrue(Files.exists(Paths.get("work\\p1")));

        Files.delete(newFilePath2);
        Files.delete(newFilePath);
    }

    @Test
    public void fileMustBeDeleted() throws IOException {
        manager = new FileSystemManagement();
        Path path = Paths.get("work\\newFile");
        Files.createFile(path);

        FileSystemResponse<String> response = manager.delete("work\\newFile");
        String messageBody = response.getBody();

        Assertions.assertEquals("Directory or file newFile has been successfully deleted.\n", messageBody);
        Assertions.assertTrue(Files.notExists(path));
    }

    @Test
    public void fileMustNotBeDeletedBecauseFileDoesNotExist() {
        manager = new FileSystemManagement();
        FileSystemResponse<String> response = manager.delete("work\\newFile");

        String messageError = response.getMessageError();
        String messageBody = response.getBody();

        Assertions.assertEquals("Directory or file newFile does not exist.", messageError);
        Assertions.assertEquals("Directory or file newFile has been not deleted.\n", messageBody);
        Assertions.assertTrue(Files.notExists(Path.of("work\\newFile")));
    }

    @Test
    public void fileMustBeEdited() throws IOException {
        manager = new FileSystemManagement("work");

        Path path = Paths.get("work\\newFile");
        Files.createFile(path);

        File file = new File("work\\newFile");
        long creationTime = file.lastModified();

        FileSystemResponse<String> response = manager.editFile("newFile", "New text");
        String messageBody = response.getBody();

        long modificationTime = file.lastModified();

        Files.delete(path);

        Assertions.assertEquals("File newFile has been successfully edited.\n", messageBody);
        Assertions.assertNotEquals(creationTime, modificationTime);
    }

    @Test
    public void fileMustNotBeEditedBecauseFileDoesNotExist() {
        manager = new FileSystemManagement();
        FileSystemResponse<String> response = manager.editFile("newFile", "New text");

        String messageError = response.getMessageError();
        String messageBody = response.getBody();

        Assertions.assertEquals("File newFile does not exist.", messageError);
        Assertions.assertEquals("File newFile has been not edited.\n", messageBody);
        Assertions.assertTrue(Files.notExists(Path.of("work\\newFile")));
    }

    @Test
    public void fileMustBeRenamed() throws IOException {
        manager = new FileSystemManagement("work");

        Files.createFile(Paths.get("work\\newFile"));

        FileSystemResponse<String> response = manager.renameTo("newFile", "newFile2");
        String messageBody = response.getBody();

        Assertions.assertEquals("Directory or file newFile has been successfully renamed.\n", messageBody);
        Assertions.assertTrue(Files.notExists(Path.of("work\\newFile")));
        Assertions.assertTrue(Files.exists(Path.of("work\\newFile2")));

        Files.delete(Paths.get("work\\newFile2"));
    }

    @Test
    public void fileMustNotBeRenamedBecauseFileWithThisNameAlreadyExists() throws IOException {
        manager = new FileSystemManagement("work");

        Path path1 = Paths.get("work\\newFile");
        Path path2 = Paths.get("work\\newFile2");
        Files.createFile(path1);
        Files.createFile(path2);

        FileSystemResponse<String> response = manager.renameTo("newFile", "newFile2");
        String messageError = response.getMessageError();
        String messageBody = response.getBody();

        Files.delete(path1);
        Files.delete(path2);

        Assertions.assertEquals("Directory or file with this name already exists.", messageError);
        Assertions.assertEquals("Directory or file newFile has been not renamed.\n", messageBody);
    }

    @Test
    public void fileMustNotBeRenamedBecauseFileDoesNotExist() {
        manager = new FileSystemManagement();
        FileSystemResponse<String> response = manager.renameTo("newFile", "newFile2");

        String messageError = response.getMessageError();
        String messageBody = response.getBody();

        Assertions.assertEquals("Directory or file newFile does not exist.", messageError);
        Assertions.assertEquals("Directory or file newFile has been not renamed.\n", messageBody);
        Assertions.assertTrue(Files.notExists(Path.of("work\\newFile")));
    }

    @Test
    public void directoryMustBeRenamed() throws IOException {
        manager = new FileSystemManagement("work");

        Files.createDirectory(Paths.get("work\\newDir"));

        FileSystemResponse<String> response = manager.renameTo("newDir", "newDir2");
        String messageBody = response.getBody();

        Assertions.assertEquals("Directory or file newDir has been successfully renamed.\n", messageBody);
        Assertions.assertTrue(Files.notExists(Path.of("work\\newDir")));
        Assertions.assertTrue(Files.exists(Path.of("work\\newDir2")));

        Files.delete(Paths.get("work\\newDir2"));
    }

    @Test
    public void directoryMustNotBeRenamedBecauseDirectoryWithThisNameAlreadyExists() throws IOException {
        manager = new FileSystemManagement("work");

        Path path1 = Paths.get("work\\newDir");
        Path path2 = Paths.get("work\\newDir2");
        Files.createDirectory(path1);
        Files.createDirectory(path2);

        FileSystemResponse<String> response = manager.renameTo("newDir", "newDir2");

        String messageError = response.getMessageError();
        String messageBody = response.getBody();

        Files.delete(path1);
        Files.delete(path2);

        Assertions.assertEquals("Directory or file with this name already exists.", messageError);
        Assertions.assertEquals("Directory or file newDir has been not renamed.\n", messageBody);
    }

    @Test
    public void shouldReturnInfoAboutFile() throws IOException {
        manager = new FileSystemManagement("work");
        manager.createFile("newFile.txt", "new file");
        FileSystemResponse<Map<String, String>> response = manager.getInfo("newFile.txt");
        Map<String, String> map = response.getBody();

        String absolutePathExpected = Path.of("work/newFile.txt").toAbsolutePath().toString();

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        String currentDateTime = dateFormat.format(currentDate);

        Files.delete(Paths.get("work\\newFile.txt"));

        Assertions.assertEquals(absolutePathExpected, map.get("Absolute path: "));
        Assertions.assertEquals("8 bytes", map.get("Size: "));
        Assertions.assertEquals(currentDateTime, map.get("Creation time: "));
    }

    @Test
    public void shouldReturnInfoAboutDirectory() throws IOException {
        manager = new FileSystemManagement("work");

        Path path = Paths.get("work\\newDirectory");
        Files.createDirectory(path);
        Files.createDirectory(Paths.get("work\\newDirectory\\nestedDirectory1"));
        Files.createDirectory(Paths.get("work\\newDirectory\\nestedDirectory2"));
        Files.createFile(Paths.get("work\\newDirectory\\nestedDirectory1\\nestedFile.txt"));

        FileSystemResponse<Map<String, String>> response = manager.getInfo("newDirectory");
        Map<String, String> map = response.getBody();

        String absolutePathExpected = path.toAbsolutePath().toString();

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        String currentDateTime = dateFormat.format(currentDate);

        manager.deleteWithNestedDirectories("work\\newDirectory");

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
        Assertions.assertTrue(Files.notExists(Path.of("work\\dir33")));
    }
}
