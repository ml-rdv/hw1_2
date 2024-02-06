package InputOutputStream.ConsoleTextFileEditor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
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
    private static File currentDirectory = new File(".");

    public File getCurrentDirectory() {
        return currentDirectory;
    }

    public void start() {
        openDirectory(currentDirectory.getPath());
    }

    public void continueWork(String input) {
        String[] splittedInput = input.split(" ");
        String command = splittedInput[0];
        switch (command) {
            case "open" -> {
                String path = currentDirectory.getPath() + "\\" + splittedInput[1];
                File directory = new File(path);
                if (directory.isFile()) {
                    openFile(path);
                } else {
                    openDirectory(path);
                }
            }
            case "create" -> {
                if (splittedInput[1].equals("-f")) {
                    boolean success = createFile(input);
                    printWhetherTheCommandIsSuccessful(success, splittedInput[2], "created");
                } else {
                    String path = currentDirectory.getPath() + "\\" + splittedInput[1];
                    boolean success = createDirectory(path);
                    if (!success) {
                        System.out.println("Directory " + splittedInput[1] + " exists.");
                    }
                    printWhetherTheCommandIsSuccessful(success, splittedInput[1], "created");
                }
            }
            case "back" -> {
                if (currentDirectory.getPath().equals(".")) {
                    System.out.println("It is root directory.");
                    return;
                }
                openDirectory(currentDirectory.getParent());
            }
            case "edit" -> {
                if (splittedInput.length > 2) {
                    boolean success = editFile(input);
                    printWhetherTheCommandIsSuccessful(success, splittedInput[1], "edited");
                } else {
                    System.out.println("Input is empty.");
                }
            }
            case "delete" -> {
                String path = currentDirectory.getPath() + "\\" + splittedInput[1];
                boolean success = delete(path);
                printWhetherTheCommandIsSuccessful(success, splittedInput[1], "deleted");
            }
            case "rename" -> {
                if (splittedInput.length == 3) {
                    String path = currentDirectory.getPath() + "\\" + splittedInput[1];
                    boolean success = renameTo(path, splittedInput[2]);
                    printWhetherTheCommandIsSuccessful(success, splittedInput[1], "renamed");
                } else {
                    System.out.println("Input 3 parameters");
                }
            }
            case "info" -> {
                String path = currentDirectory.getPath() + "\\" + splittedInput[1];
                Map<String, String> info = getInfo(path);
                if (info != null) {
                    printInfo(info);
                }
            }
            default -> System.out.println("Command is not correct. Try again.");
        }
        System.out.println("Current path: " + currentDirectory.getPath());
    }

    private boolean extensionIsCorrect(File fileDirectory) {
        String fileName = fileDirectory.getName();
        int dotIndex = fileName.lastIndexOf('.');
        String extension = (dotIndex == -1) ? "" : fileName.substring(dotIndex);
        if (extension.equals(".txt")) {
            return true;
        } else {
            System.out.println("The file extension is incorrect.");
            return false;
        }
    }

    public boolean openFile(String filePath) {
        if (!currentDirectoryExists(filePath)) {
            return false;
        }
        File fileDirectory = new File(filePath);
        if (extensionIsCorrect(fileDirectory)) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileDirectory))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                System.out.println();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public void openDirectory(String path) {
        if (!currentDirectoryExists(path)) {
            return;
        }
        currentDirectory = new File(path);
        File[] list = currentDirectory.listFiles();
        for (File f : list != null ? list : new File[0]) {
            if (f.isFile()) {
                System.out.println("-f " + f.getName());
            } else {
                System.out.println("-d " + f.getName());
            }
        }
    }

    public boolean createFile(String input) {
        String[] splittedInput = input.split(" ");
        String fileText = null;
        if (splittedInput.length > 3) {
            int dotIndex = input.lastIndexOf(splittedInput[3]);
            fileText = input.substring(dotIndex);
        }
        File fileDirectory = new File(currentDirectory.getPath() + "\\" + splittedInput[2]);
        try {
            if (fileDirectory.createNewFile()) {
                if (fileText != null) {
                    try {
                        fillOutFile(fileDirectory.getPath(), fileText, false);
                        printWhetherTheCommandIsSuccessful(true, splittedInput[2], "filled");
                    } catch (RuntimeException e) {
                        printWhetherTheCommandIsSuccessful(false, splittedInput[2], "filled");
                        e.printStackTrace();
                    }
                }
                return true;
            } else {
                System.out.println("File already exists.");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean createDirectory(String path) {
        File directory = new File(path);
        return directory.mkdir();
    }

    public boolean editFile(String input) {
        String[] splittedInput = input.split(" ");
        String filePath = currentDirectory.getPath() + "\\" + splittedInput[1];
        if (!currentDirectoryExists(filePath)) {
            return false;
        }
        File fileDirectory = new File(filePath);
        int dotIndex = input.lastIndexOf(splittedInput[2]);
        String fileText = "\n" + input.substring(dotIndex);
        try {
            fillOutFile(fileDirectory.getPath(), fileText, true);
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void fillOutFile(String path, String fileText, boolean append) throws RuntimeException {
        File fileDirectory = new File(path);
        try (FileWriter output = new FileWriter(fileDirectory.getPath(), append)) {
            output.write(fileText);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean renameTo(String path, String newName) {
        if (!currentDirectoryExists(path)) {
            return false;
        }
        File oldNameDirectory = new File(path);
        File newNameDirectory = new File(oldNameDirectory.getParent() + "\\" + newName);
        return oldNameDirectory.renameTo(newNameDirectory);
    }

    public Map<String, String> getInfo(String path) {
        if (!currentDirectoryExists(path)) {
            return null;
        }
        File directory = new File(path);
        Map<String, String> info = new HashMap<>();
        info.put("Absolute path: ", directory.getAbsolutePath());
        info.put("Size: ", directory.length() + " bytes");
        Path file = Paths.get(directory.getPath());
        FileTime creationTimeFileTime = null;
        try {
            creationTimeFileTime = (FileTime) Files.getAttribute(file, "creationTime");
        } catch (IOException e) {
            e.printStackTrace();
        }
        LocalDateTime localDateTime = creationTimeFileTime != null ? creationTimeFileTime
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime() : null;
        String creationTime = localDateTime != null ? localDateTime.format(
                DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm")) : null;
        info.put("Creation time: ", creationTime);
        if (directory.isDirectory()) {
            int numberNestedElements = countNestedElements(directory, -1);
            info.put("Number of nested elements: ", String.valueOf(numberNestedElements));
        }
        return info;
    }

    private int countNestedElements(File directory, int count) {
        if (directory.isDirectory()) {
            count += 1;
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    count = countNestedElements(file, count);
                }
            }
            return count;
        }
        return count + 1;
    }

    private void deleteDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteDirectory(file);
                }
            }
        }
        directory.delete();
    }

    public boolean delete(String path) {
        if (!currentDirectoryExists(path)) {
            return false;
        }
        File directory = new File(path);
        File[] list = directory.isDirectory() ? directory.listFiles() : null;
        if (directory.isFile() || (list != null && list.length == 0)) {
            deleteDirectory(directory);
            return true;
        }
        System.out.println("Directory " + directory.getName() + " is not empty. Are you sure you want to delete it? Yes/No");
        Scanner in = new Scanner(System.in);
        String input;
        while (true) {
            input = in.nextLine();
            if (input.equals("Yes")) {
                deleteDirectory(directory);
                return true;
            } else if (input.equals("No")) {
                return false;
            } else {
                System.out.println("Incorrect command. Try again.");
            }
        }
    }

    private boolean currentDirectoryExists(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            System.out.println("Directory or file " + directory.getName() + " does not exist.");
            return false;
        }
        return true;
    }

    private void printInfo(Map<String, String> info) {
        info.forEach((key, value) -> System.out.println(key + " " + value));
    }

    private void printWhetherTheCommandIsSuccessful(boolean success, String name, String command) {
        if (success) {
            System.out.println("Directory or file " + name + " has been successfully " + command + ".");
        } else {
            System.out.println("Directory or file " + name + " has not been " + command + ".");
        }
    }
}
