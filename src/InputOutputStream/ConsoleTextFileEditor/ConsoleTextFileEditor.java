package InputOutputStream.ConsoleTextFileEditor;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Реализовать консольный мини-редактор текстовых файлов. Редактор должен:
 * При запуске отображать списком файлы и папки текущей папки из которой запускается программа
 * (желательно визуально разделить файлы и папки)
 * При вводе в консоль команды open c названием папки (пример “open work”)
 * Должен произойти переход в данную папку. Визуально должен выводиться список её содержимого
 * Если папки нет, то вывести соответствующее сообщение
 * При вводе в консоль команды create c названием папки (пример “create documents”)
 * Должна создаться папка и должен произойти переход в неё
 * Если папка существует, то вывести соответствующее сообщение
 * При вводе в консоль команды back происходит возврат на родительскую папку
 * При вводе в консоль команды open c названием файла (пример “open notes”)
 * Если файл содержит расширение txt, то содержимое файла выводится в консоль
 * Если иное расширение, то выводится текст по типу “неверный формат файла”
 * При нажатие enter содержимое файла убирается и отображается содержимое текущей папки
 * При вводе в консоль команды create c названием файла (пример “create notes”) создается пустой файл
 * Если к команде добавить текст, то файл создается с текстом (Пример “create notes hello world”)
 * При вводе в консоль команды edit с названием файла (Пример “edit notes”)
 * Можно ввести любой текст и после нажатия enter он добавится в конец файла
 * При вводе в консоль команды delete и названия файла/папки
 * Происходит удаление выбранного
 * Если папка не пустая, то программа должна запросить разрешение на удаление содержимого
 * Если файла/папки нет, то вывести соответствующее сообщение
 * При вводе в консоль команды rename, старого и нового названия  файла/папки
 * (пример rename oldName newName) должно произойти переименование
 * При вводе в консоль команды info и названия файла/папки должна выводится мета информация
 * Полный(абсолютный) путь
 * Размер
 * Дата создания
 * Если папка, то выводить кол-во вложенных элементов
 * Желательно перед выполнением команд делать очистку консоли
 */
public class ConsoleTextFileEditor {
    private final FileSystemManagement manager;
    private boolean isActivated = true;

    public ConsoleTextFileEditor() {
        this.manager = new FileSystemManagement("");
    }

    public ConsoleTextFileEditor(String startingDir) {
        this.manager = new FileSystemManagement(startingDir);
    }

    public void start() {
        Scanner in = new Scanner(System.in);
        String startingPath = manager.getCurrentDirectory().getPath();
        openDirectory(startingPath);
        String command;
        while (isActivated) {
            command = in.nextLine();
            clearConsole();
            roadCommand(command);
        }
    }

    private void clearConsole() {
        System.out.print("\033[H\033[J");
        System.out.flush();
    }

    public void shutDown() {
        isActivated = false;
    }

    public void roadCommand(String input) {
        String[] splittedInput = input.split(" ");
        String command = splittedInput[0];
        switch (command) {
            case "open" -> beforeOpen(splittedInput);
            case "create" -> beforeCreate(input);
            case "back" -> backToParentDirectory();
            case "edit" -> beforeEdit(input);
            case "delete" -> beforeDelete(splittedInput);
            case "rename" -> beforeRename(splittedInput);
            case "info" -> printInfo(input);
            case "finish" -> {
                shutDown();
                return;
            }
            default -> System.out.println("Command is not correct. Try again.");
        }
        System.out.println("Current path: " + manager.getCurrentDirectory().getPath());
    }

    private void beforeRename(String[] splittedInput) {
        if (splittedInput.length == 3) {
            String oldName = splittedInput[1];
            String newName = splittedInput[2];
            String messageResult = renameTo(oldName, newName);
            System.out.printf(messageResult);
        } else {
            System.out.println("Input 3 parameters");
        }
    }

    private void beforeDelete(String[] splittedInput) {
        String name = splittedInput[1];
        String path = manager.getCurrentDirectory().getPath() + "\\" + name;
        String messageResult = delete(path);
        System.out.println(messageResult);
    }

    private void beforeEdit(String input) {
        String[] splittedInput = input.split(" ");
        if (splittedInput.length > 2) {
            int indexFileText = input.lastIndexOf(splittedInput[2]);
            String fileText = "\n" + input.substring(indexFileText);
            String nameFile = splittedInput[1];
            String messageResult = editFile(nameFile, fileText);
            System.out.println(messageResult);
        } else {
            System.out.println("Input all parameters.");
        }
    }

    private void beforeOpen(String[] splittedInput) {
        String name = splittedInput[1];
        String path = manager.getCurrentDirectory().getPath() + "\\" + name;
        File directory = new File(path);
        if (directory.isFile()) {
            openFile(name);
        } else {
            openDirectory(path);
        }
    }

    private void beforeCreate(String input) {
        String[] splittedInput = input.split(" ");
        boolean isFile = splittedInput[1].equals("-f");
        if (isFile) {
            String fileText = "";
            if (splittedInput.length > 3) {
                int indexFileText = input.lastIndexOf(splittedInput[3]);
                fileText = input.substring(indexFileText);
            }
            String nameFile = splittedInput[2];
            String messageResult = createFile(nameFile, fileText);
            System.out.println(messageResult);
        } else {
            String nameDirectory = splittedInput[1];
            String messageResult = createDirectory(nameDirectory);
            System.out.println(messageResult);
        }
    }

    private <T> boolean checkIsError(FileSystemResponse<T> fileSystemResponse) {
        if (fileSystemResponse.getMessageError() != null) {
            System.out.println(fileSystemResponse.getMessageError());
            return true;
        }
        return false;
    }

    private String getMessageResult(FileSystemResponse<String> fileSystemResponse) {
        checkIsError(fileSystemResponse);
        return fileSystemResponse.getBody();
    }

    private void backToParentDirectory() {
        FileSystemResponse<Boolean> fileSystemResponse = manager.backToParentDirectory();
        if (checkIsError(fileSystemResponse)) {
            return;
        }
        openDirectory(manager.getCurrentDirectory().getPath());
    }

    public void openFile(String name) {
        FileSystemResponse<String> fileSystemResponse = manager.getTextFileContents(name);
        if (checkIsError(fileSystemResponse)) {
            return;
        }
        System.out.println(fileSystemResponse.getBody());
    }

    public void openDirectory(String path) {
        FileSystemResponse<Boolean> fileSystemResponse = manager.setDirectory(path);
        if (checkIsError(fileSystemResponse)) {
            return;
        }
        FileSystemResponse<List<String>> fSR_list_files = manager.getListFiles();
        List<String> listFiles = fSR_list_files.getBody();
        listFiles.forEach(System.out::println);
    }

    public String createFile(String name, String fileText) {
        FileSystemResponse<String> fileSystemResponse = manager.createFile(name, fileText);
        return getMessageResult(fileSystemResponse);
    }

    public String createDirectory(String name) {
        FileSystemResponse<String> fileSystemResponse = manager.createDirectory(name);
        return getMessageResult(fileSystemResponse);
    }

    public String editFile(String nameFile, String text) {
        FileSystemResponse<String> fileSystemResponse = manager.editFile(nameFile, text);
        return getMessageResult(fileSystemResponse);
    }

    public String renameTo(String oldName, String newName) {
        FileSystemResponse<String> fileSystemResponse = manager.renameTo(oldName, newName);
        return getMessageResult(fileSystemResponse);
    }

    public void printInfo(String input) {
        String[] splittedInput = input.split(" ");
        String name = splittedInput[1];
        FileSystemResponse<Map<String, String>> fileSystemResponse = manager.getInfo(name);
        if (checkIsError(fileSystemResponse)) {
            return;
        }
        Map<String, String> info = fileSystemResponse.getBody();
        info.forEach((key, value) -> System.out.println(key + " " + value));
    }

    public String delete(String path) {
        FileSystemResponse<String> fileSystemResponse = manager.delete(path);
        if (fileSystemResponse.getMessageError() != null
                && fileSystemResponse.getMessageError().equals("Directory is not empty.")) {
            System.out.println("Directory is not empty. Are you sure you want to delete it? Yes/No/Cansel");
            Scanner in = new Scanner(System.in);
            String input;
            while (true) {
                input = in.nextLine();
                if (input.equalsIgnoreCase("Yes")) {
                    manager.deleteWithNestedDirectories(path);
                    return fileSystemResponse.getBody();
                } else if (input.equalsIgnoreCase("No") || input.equalsIgnoreCase("Cansel")) {
                    return fileSystemResponse.getBody();
                } else {
                    System.out.println("Incorrect command. Try again.");
                }
            }
        }
        checkIsError(fileSystemResponse);
        return fileSystemResponse.getBody();
    }
}
