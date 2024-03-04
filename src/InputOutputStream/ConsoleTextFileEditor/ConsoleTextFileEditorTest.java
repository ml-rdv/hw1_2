package InputOutputStream.ConsoleTextFileEditor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ConsoleTextFileEditorTest {

    private ConsoleTextFileEditor consoleTextFileEditor;
    private static final String CLEAR_CONSOLE_COMMAND = "\033\\[H\033\\[J";

    @BeforeEach
    void prepareWorkDirectory() throws IOException {
        var testPath = Path.of("work");
        var fileSystemManagement = new FileSystemManagement("");
        fileSystemManagement.deleteWithNestedDirectories(testPath.toString());

        Files.createDirectory(testPath);
    }

    @Test
    public void directoryMustBeOpened() throws IOException {
        // папку work пришлось руками в коде установить как директорию по умолчанию
        Files.createDirectories(Path.of("work\\dir"));

        var commands = List.of("open dir", "finish");
        var commandBytes = String.join("\n", commands).getBytes();

        // в данном месте берём контроль над потоками ввода/выводы,
        // чтобы мы могли сымитировать ввод нужной команды и вычитать результат System.out.println
        System.setIn(new ByteArrayInputStream(commandBytes));
        var byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        consoleTextFileEditor = new ConsoleTextFileEditor("work");
        consoleTextFileEditor.start();

        // здесь дополнительно сносим из вывода команду на очистку, чтобы не мешала нам
        var consoleOutput = byteArrayOutputStream.toString()
                .replaceAll(CLEAR_CONSOLE_COMMAND, "");

        // если ожидаемый вывод получается очень большой на много строк, то можно
        // сделать папку например testConsoleOutput и там положить текстовые файлы с большим выводом,
        // а в тесте уже сравнивать то, что лежит в файле с реальным выводом
        Assertions.assertEquals("-d dir\r\nCurrent path: C:\\Users\\MILA\\IdeaProjects\\hw1_2\\work\\dir\r\n", consoleOutput);
    }

    @Test
    public void fileMustBeOpened() throws IOException {
        Path filePath = Paths.get("work\\newFile.txt");
        Files.createFile(filePath);
        Files.write(filePath, List.of("File with text"), StandardCharsets.UTF_8);

        var commands = List.of("open newFile.txt", "finish");
        var commandBytes = String.join("\n", commands).getBytes();

        System.setIn(new ByteArrayInputStream(commandBytes));
        var byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        consoleTextFileEditor = new ConsoleTextFileEditor("work");
        consoleTextFileEditor.start();
        var consoleOutput = byteArrayOutputStream.toString().replaceAll(CLEAR_CONSOLE_COMMAND, "")
                .replaceAll("-f newFile.txt\r\n", "")
                .replaceAll("Current path: C:\\\\Users\\\\MILA\\\\IdeaProjects\\\\hw1_2\\\\work\r\n", "");

        Files.delete(filePath);
        Assertions.assertEquals("File with text\n\r\n", consoleOutput);
    }

    @Test
    public void fileMustBeCreated() throws IOException {
        var commands = List.of("create -f newFile File with text", "finish");
        var commandBytes = String.join("\n", commands).getBytes();

        System.setIn(new ByteArrayInputStream(commandBytes));
        var byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        consoleTextFileEditor = new ConsoleTextFileEditor("work");
        consoleTextFileEditor.start();
        var consoleOutput = byteArrayOutputStream.toString().replaceAll(CLEAR_CONSOLE_COMMAND, "")
                .replaceAll("Current path: C:\\\\Users\\\\MILA\\\\IdeaProjects\\\\hw1_2\\\\work\r\n", "");

        Files.delete(Paths.get("work\\newFile"));
        Assertions.assertEquals("File newFile has been successfully created.\n\r\n", consoleOutput);
    }

    @Test
    public void directoryMustBeCreated() throws Exception {
        var commands = List.of("create nestedDirectory", "finish");
        var commandBytes = String.join("\n", commands).getBytes();

        System.setIn(new ByteArrayInputStream(commandBytes));
        var byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        consoleTextFileEditor = new ConsoleTextFileEditor("work");
        consoleTextFileEditor.start();
        var consoleOutput = byteArrayOutputStream.toString().replaceAll(CLEAR_CONSOLE_COMMAND, "")
                .replaceAll("Current path: C:\\\\Users\\\\MILA\\\\IdeaProjects\\\\hw1_2\\\\work\r\n", "");

        Files.delete(Paths.get("work\\nestedDirectory"));
        Assertions.assertEquals("Directory nestedDirectory has been successfully created.\n\r\n", consoleOutput);
    }

    @Test
    public void directoryMustBeDeleted() throws IOException {
        Files.createDirectory(Path.of("work\\nestedDirectory"));
        var commands = List.of("delete nestedDirectory", "finish");
        var commandBytes = String.join("\n", commands).getBytes();

        System.setIn(new ByteArrayInputStream(commandBytes));
        var byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        consoleTextFileEditor = new ConsoleTextFileEditor("work");
        consoleTextFileEditor.start();
        var consoleOutput = byteArrayOutputStream.toString().replaceAll(CLEAR_CONSOLE_COMMAND, "")
                .replaceAll("Current path: C:\\\\Users\\\\MILA\\\\IdeaProjects\\\\hw1_2\\\\work\r\n", "")
                .replaceAll("-d nestedDirectory\r\n", "");

        Assertions.assertEquals("Directory or file nestedDirectory has been successfully deleted.\n\r\n", consoleOutput);
    }

    @Test
    public void directoryMustNotBeDeletedBecauseDirectoryDoesNotExist() {
        var commands = List.of("delete nestedDirectory", "finish");
        var commandBytes = String.join("\n", commands).getBytes();

        System.setIn(new ByteArrayInputStream(commandBytes));
        var byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        consoleTextFileEditor = new ConsoleTextFileEditor("work");
        consoleTextFileEditor.start();
        var consoleOutput = byteArrayOutputStream.toString().replaceAll(CLEAR_CONSOLE_COMMAND, "")
                .replaceAll("Current path: C:\\\\Users\\\\MILA\\\\IdeaProjects\\\\hw1_2\\\\work\r\n", "");

        Assertions.assertEquals("""
                Directory or file does not exist.\r
                Directory or file nestedDirectory has been not deleted.
                \r
                """, consoleOutput);
    }

    @Test
    public void fileMustBeDeleted() throws IOException {
        Files.createFile(Path.of("work\\newFile"));
        var commands = List.of("delete newFile", "finish");
        var commandBytes = String.join("\n", commands).getBytes();

        System.setIn(new ByteArrayInputStream(commandBytes));
        var byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        consoleTextFileEditor = new ConsoleTextFileEditor("work");
        consoleTextFileEditor.start();
        var consoleOutput = byteArrayOutputStream.toString().replaceAll(CLEAR_CONSOLE_COMMAND, "")
                .replaceAll("Current path: C:\\\\Users\\\\MILA\\\\IdeaProjects\\\\hw1_2\\\\work\r\n", "")
                .replaceAll("-f newFile\r\n", "");

        Assertions.assertEquals("Directory or file newFile has been successfully deleted.\n\r\n", consoleOutput);
    }

    @Test
    public void fileMustBeEdited() throws IOException {
        Path filePath = Path.of("work\\newFile");
        Files.createFile(filePath);
        var commands = List.of("edit newFile New text", "finish");
        var commandBytes = String.join("\n", commands).getBytes();

        System.setIn(new ByteArrayInputStream(commandBytes));
        var byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        consoleTextFileEditor = new ConsoleTextFileEditor("work");
        consoleTextFileEditor.start();
        var consoleOutput = byteArrayOutputStream.toString().replaceAll(CLEAR_CONSOLE_COMMAND, "")
                .replaceAll("Current path: C:\\\\Users\\\\MILA\\\\IdeaProjects\\\\hw1_2\\\\work\r\n", "")
                .replaceAll("-f newFile\r\n", "");

        Files.delete(filePath);
        Assertions.assertEquals("File newFile has been successfully edited.\n\r\n", consoleOutput);
    }

    @Test
    public void fileMustBeRenamed() throws IOException {
        Files.createFile(Path.of("work\\oldNameFile"));
        var commands = List.of("rename oldNameFile newFile", "finish");
        var commandBytes = String.join("\n", commands).getBytes();

        System.setIn(new ByteArrayInputStream(commandBytes));
        var byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        consoleTextFileEditor = new ConsoleTextFileEditor("work");
        consoleTextFileEditor.start();
        var consoleOutput = byteArrayOutputStream.toString().replaceAll(CLEAR_CONSOLE_COMMAND, "")
                .replaceAll("Current path: C:\\\\Users\\\\MILA\\\\IdeaProjects\\\\hw1_2\\\\work\r\n", "")
                .replaceAll("-f oldNameFile\r\n", "");

        Files.delete(Paths.get("work\\newFile"));
        Assertions.assertEquals("Directory or file oldNameFile has been successfully renamed.\n", consoleOutput);
    }

    @Test
    public void directoryMustBeRenamed() throws IOException {
        Files.createDirectory(Path.of("work\\tmp"));
        var commands = List.of("rename tmp tmp2", "finish");
        var commandBytes = String.join("\n", commands).getBytes();

        System.setIn(new ByteArrayInputStream(commandBytes));
        var byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        consoleTextFileEditor = new ConsoleTextFileEditor("work");
        consoleTextFileEditor.start();
        var consoleOutput = byteArrayOutputStream.toString().replaceAll(CLEAR_CONSOLE_COMMAND, "")
                .replaceAll("Current path: C:\\\\Users\\\\MILA\\\\IdeaProjects\\\\hw1_2\\\\work\r\n", "")
                .replaceAll("-d tmp\r\n", "");

        Files.delete(Paths.get("work\\tmp2"));
        Assertions.assertEquals("Directory or file tmp has been successfully renamed.\n", consoleOutput);
    }

    @Test
    public void shouldPrintInfoAboutDirectory() throws IOException {
        Path path = Paths.get("work\\tmp");
        Files.createDirectory(path);
        Path path1 = Path.of("work\\tmp\\d1");
        Files.createDirectory(path1);
        Path path2 = Path.of("work\\tmp\\d2");
        Files.createDirectory(path2);
        Path path3 = Path.of("work\\tmp\\d2\\f1");
        Files.createFile(path3);

        var commands = List.of("info tmp", "finish");
        var commandBytes = String.join("\n", commands).getBytes();

        System.setIn(new ByteArrayInputStream(commandBytes));
        var byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        consoleTextFileEditor = new ConsoleTextFileEditor("work");
        consoleTextFileEditor.start();
        var consoleOutput = byteArrayOutputStream.toString().replaceAll(CLEAR_CONSOLE_COMMAND, "")
                .replaceAll("Current path: C:\\\\Users\\\\MILA\\\\IdeaProjects\\\\hw1_2\\\\work\r\n", "")
                .replaceAll("-d tmp\r\n", "");

        Files.delete(path3);
        Files.delete(path2);
        Files.delete(path1);
        Files.delete(path);

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        String currentDateTime = dateFormat.format(currentDate);

        String absolutePathExpected = path.toAbsolutePath().toString();

        Assertions.assertEquals("Number of elements:  2\r\n" +
                "Size:  0 bytes\r\n" +
                "Creation time:  " + currentDateTime + "\r\n" +
                "Absolute path:  " + absolutePathExpected + "\r\n" +
                "Number of all nested elements:  3\r\n", consoleOutput);
    }

    @Test
    public void shouldPrintCommandIsNotCorrect() {
        var commands = List.of("printSomething", "finish");
        var commandBytes = String.join("\n", commands).getBytes();

        System.setIn(new ByteArrayInputStream(commandBytes));
        var byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        consoleTextFileEditor = new ConsoleTextFileEditor("work");
        consoleTextFileEditor.start();
        var consoleOutput = byteArrayOutputStream.toString().replaceAll(CLEAR_CONSOLE_COMMAND, "")
                .replaceAll("Current path: C:\\\\Users\\\\MILA\\\\IdeaProjects\\\\hw1_2\\\\work\r\n", "");

        Assertions.assertEquals("Command is not correct. Try again.\r\n", consoleOutput);
    }
}