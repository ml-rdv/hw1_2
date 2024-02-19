package InputOutputStream.ConsoleTextFileEditor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        // здесь дополнительно сносим из  вывода команду на очистку, чтобы не мешала нам
        var consoleOutput = byteArrayOutputStream.toString().replaceAll(CLEAR_CONSOLE_COMMAND, "");

        // если ожидаемый вывод получается очень большой на много строк, то можно
        // сделать папку например testConsoleOutput и там положить текстовые файлы с большим выводом,
        // а в тесте уже сравнивать то что лежит в файле с реальным выводом
        Assertions.assertEquals("-d dir\r\nCurrent path: work\\dir\r\n", consoleOutput);
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
                .replaceAll("Current path: work\r\n", "");

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
                .replaceAll("Current path: work\r\n", "");

        Files.delete(Paths.get("work\\newFile"));
        Assertions.assertEquals("File newFile has been successfully created.\n", consoleOutput);
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
                .replaceAll("Current path: work\r\n", "");

        Files.delete(Paths.get("work\\nestedDirectory"));
        Assertions.assertEquals("Directory nestedDirectory has been successfully created.\n", consoleOutput);
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
                .replaceAll("Current path: work\r\n", "")
                .replaceAll("-d nestedDirectory\r\n", "");

        Assertions.assertEquals("Directory or file nestedDirectory has been successfully deleted.\n", consoleOutput);
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
                .replaceAll("Current path: work\r\n", "")
                .replaceAll("-f newFile\r\n", "");

        Assertions.assertEquals("Directory or file newFile has been successfully deleted.\n", consoleOutput);
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
                .replaceAll("Current path: work\r\n", "")
                .replaceAll("-f newFile\r\n", "");

        Files.delete(filePath);
        Assertions.assertEquals("File newFile has been successfully edited.\n", consoleOutput);
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
                .replaceAll("Current path: work\r\n", "")
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
                .replaceAll("Current path: work\r\n", "")
                .replaceAll("-d tmp\r\n", "");

        Files.delete(Paths.get("work\\tmp2"));
        Assertions.assertEquals("Directory or file tmp has been successfully renamed.\n", consoleOutput);
    }
}