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
    private final FileSystemManagement manager = new FileSystemManagement();

    public File getCurrentDirectory() {
        return manager.getCurrentDirectory();
    }

    public void start() {
        openDirectory(".");
        Scanner in = new Scanner(System.in);
        String input;
        while (true) {
            input = in.nextLine();
            System.out.print("\033[H\033[J");
            System.out.flush();
            if (!input.equals("finish")) {
                continueWork(input);
            } else {
                in.close();
                break;
            }
        }
    }

    public void continueWork(String input) {
        String[] splittedInput = input.split(" ");
        String command = splittedInput[0];
        String MESSAGE_FORMAT_SUCCESS = "%s %s has been successfully %s.\n";
        String MESSAGE_FORMAT_NOT_SUCCESS = "%s %s has not been %s.\n";
        switch (command) {
            case "open" -> {
                String path = manager.getCurrentDirectory().getPath() + "\\" + splittedInput[1];
                File directory = new File(path);
                if (directory.isFile()) {
                    openFile(splittedInput[1]);
                } else {
                    openDirectory(path);
                }
            }
            case "create" -> {
                boolean success;
                if (splittedInput[1].equals("-f")) {
                    String fileText = "";
                    if (splittedInput.length > 3) {
                        int dotIndex = input.lastIndexOf(splittedInput[3]);
                        fileText = input.substring(dotIndex);
                    }
                    success = createFile(splittedInput[2], fileText);
                    System.out.printf((success ? MESSAGE_FORMAT_SUCCESS : MESSAGE_FORMAT_NOT_SUCCESS),
                            "File", splittedInput[2], "created");
                } else {
                    success = createDirectory(splittedInput[1]);
                    System.out.printf((success ? MESSAGE_FORMAT_SUCCESS : MESSAGE_FORMAT_NOT_SUCCESS),
                            "Directory", splittedInput[1], "created");
                }
            }
            case "back" -> {
                back();
            }
            case "edit" -> {
                if (splittedInput.length > 2) {
                    int dotIndex = input.lastIndexOf(splittedInput[2]);
                    String fileText = "\n" + input.substring(dotIndex);
                    boolean success = editFile(splittedInput[1], fileText);
                    System.out.printf((success ? MESSAGE_FORMAT_SUCCESS : MESSAGE_FORMAT_NOT_SUCCESS),
                            "File", splittedInput[1], "edited");
                } else {
                    System.out.println("Input all parameters.");
                }
            }
            case "delete" -> {
                String path = manager.getCurrentDirectory().getPath() + "\\" + splittedInput[1];
                boolean success = delete(path);
                System.out.printf((success ? MESSAGE_FORMAT_SUCCESS : MESSAGE_FORMAT_NOT_SUCCESS),
                        "Directory of file", splittedInput[1], "deleted");
            }
            case "rename" -> {
                if (splittedInput.length == 3) {
                    boolean success = renameTo(splittedInput[1], splittedInput[2]);
                    System.out.printf((success ? MESSAGE_FORMAT_SUCCESS : MESSAGE_FORMAT_NOT_SUCCESS),
                            "Directory of file", splittedInput[1], "renamed");
                } else {
                    System.out.println("Input 3 parameters");
                }
            }
            case "info" -> {
                printInfo(splittedInput[1]);
            }
            default -> System.out.println("Command is not correct. Try again.");
        }
        System.out.println("Current path: " + manager.getCurrentDirectory().getPath());
    }

    private void back() {
        FileSystemResponse<Boolean> fileSystemResponse = manager.back();
        if (fileSystemResponse.getMessageError() != null) {
            System.out.println(fileSystemResponse.getMessageError());
            return;
        }
        openDirectory(manager.getCurrentDirectory().getPath());
    }

    public boolean openFile(String name) {
        FileSystemResponse<StringBuilder> fileSystemResponse = manager.getFile(name);
        if (fileSystemResponse.getMessageError() != null) {
            System.out.println(fileSystemResponse.getMessageError());
            return false;
        }
        System.out.println(fileSystemResponse.getBody());
        return true;
    }

    public void openDirectory(String path) {
        FileSystemResponse<List<String>> fileSystemResponse = manager.getDirectory(path);
        if (fileSystemResponse.getMessageError() != null) {
            System.out.println(fileSystemResponse.getMessageError());
            return;
        }
        List<String> listFiles = fileSystemResponse.getBody();
        listFiles.forEach(System.out::println);
    }

    public boolean createFile(String name, String fileText) {
        FileSystemResponse<Boolean> fileSystemResponse = manager.createFile(name, fileText);
        if (fileSystemResponse.getMessageError() != null) {
            System.out.println(fileSystemResponse.getMessageError());
            return false;
        }
        return fileSystemResponse.getBody();
    }

    public boolean createDirectory(String name) {
        FileSystemResponse<Boolean> fileSystemResponse = manager.createDirectory(name);
        if (fileSystemResponse.getMessageError() != null) {
            System.out.println(fileSystemResponse.getMessageError());
            return false;
        }
        return fileSystemResponse.getBody();
    }

    public boolean editFile(String nameFile, String text) {
        FileSystemResponse<Boolean> fileSystemResponse = manager.editFile(nameFile, text);
        if (fileSystemResponse.getMessageError() != null) {
            System.out.println(fileSystemResponse.getMessageError());
            return false;
        }
        return fileSystemResponse.getBody();
    }

    public boolean renameTo(String oldName, String newName) {
        FileSystemResponse<Boolean> fileSystemResponse = manager.renameTo(oldName, newName);
        if (fileSystemResponse.getMessageError() != null) {
            System.out.println(fileSystemResponse.getMessageError());
            return false;
        }
        return fileSystemResponse.getBody();
    }

    public void printInfo(String name) {
        FileSystemResponse<Map<String, String>> fileSystemResponse = manager.getInfo(name);
        if (fileSystemResponse.getMessageError() != null) {
            System.out.println(fileSystemResponse.getMessageError());
            return;
        }
        Map<String, String> info = fileSystemResponse.getBody();
        info.forEach((key, value) -> System.out.println(key + " " + value));
    }

    public boolean delete(String path) {
        FileSystemResponse<Boolean> fileSystemResponse = manager.delete(path, false);
        if (fileSystemResponse.getMessageError() != null ||
                (fileSystemResponse.getMessageError() != null && !fileSystemResponse.getMessageError().equals("Directory is not empty."))) {
            System.out.println(fileSystemResponse.getMessageError());
            return false;
        }
        if (fileSystemResponse.getBody()) {
            return fileSystemResponse.getBody();
        }
        System.out.println("Directory is not empty. Are you sure you want to delete it? Yes/No/Cansel");
        Scanner in = new Scanner(System.in);
        String input;
        while (true) {
            input = in.nextLine();
            if (input.equals("Yes")) {
                manager.delete(path, true);
                return true;
            } else if (input.equals("No") || input.equals("Cansel")) {
                return false;
            } else {
                System.out.println("Incorrect command. Try again.");
            }
        }
    }
}
