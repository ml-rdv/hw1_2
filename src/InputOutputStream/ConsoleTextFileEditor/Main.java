package InputOutputStream.ConsoleTextFileEditor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Runtime.getRuntime;

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
public class Main {
    private static String currentPath = ".";
    static File currentDirectory = new File(currentPath);

    public static void main(String[] args) throws IOException, InterruptedException {
        start();
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

    private static void start() {
        openDirectory();
    }

    private static void continueWork(String input) {
        String[] splittedInput = input.split(" ");
        String command = splittedInput[0];
        switch (command) {
            case "open" -> {
                updateCurrentDirectory(currentPath + "/" + splittedInput[1]);
                if (currentDirectory.isFile()) {
                    openFile();
                } else {
                    openDirectory();
                }
            }
            case "create" -> {
                if (splittedInput[1].equals("-d")) {
                    updateCurrentDirectory(currentPath + "/" + splittedInput[2]);
                    createFile(input);
                } else {
                    updateCurrentDirectory(currentDirectory + "/" + splittedInput[1]);
                    createDirectory();
                }
            }
            case "back" -> {
                if (currentPath.equals(".")) {
                    System.out.println("It is root directory.");
                    return;
                }
                updateCurrentDirectory(currentDirectory.getParent());
                openDirectory();
            }
            case "edit" -> {
                updateCurrentDirectory(currentDirectory + "/" + splittedInput[1]);
                editFile(input);
            }
            case "delete" -> {
                updateCurrentDirectory(currentPath + "/" + splittedInput[1]);
                delete();
            }
            case "rename" -> {
                if (splittedInput.length == 3) {
                    updateCurrentDirectory(currentPath + "/" + splittedInput[1]);
                    renameTo(splittedInput[2]);
                } else {
                    System.out.println("Input 3 parameters");
                }
            }
            case "info" -> {
                updateCurrentDirectory(currentPath + "/" + splittedInput[1]);
                printInfo();
            }
            default -> System.out.println("Command is not correct. Try again.");
        }
        System.out.println("Current path: " + currentPath);
    }

    private static void openFile() {
        if (!currentDirectoryExists()) {
            return;
        }
        String fileName = currentDirectory.getName();
        int dotIndex = fileName.lastIndexOf('.');
        String extension = (dotIndex == -1) ? "" : fileName.substring(dotIndex);
        if (extension.equals(".txt")) {
            try (BufferedReader reader = new BufferedReader(new FileReader(currentDirectory))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                updateCurrentDirectory(currentDirectory.getParent());
                e.printStackTrace();
            }
        } else {
            System.out.println("The file format is incorrect.");
        }
        System.out.println();
    }

    private static void openDirectory() {
        if (!currentDirectoryExists()) {
            return;
        }
        File[] list = currentDirectory.listFiles();
        for (File f : list != null ? list : new File[0]) {
            if (f.isFile()) {
                System.out.println("-f " + f.getName());
            } else {
                System.out.println("-d " + f.getName());
            }
        }
    }

    private static void createFile(String input) {
        String[] splittedInput = input.split(" ");
        String fileText = null;
        if (splittedInput.length > 3) {
            int dotIndex = input.lastIndexOf(splittedInput[3]);
            fileText = input.substring(dotIndex);
        }
        try {
            if (currentDirectory.createNewFile()) {
                System.out.println("File created: " + currentDirectory.getName());
                if (fileText != null) {
                    try {
                        fillOutFile(fileText, false);
                        System.out.println("File " + currentDirectory.getName()
                                + " has been successfully filled.");
                    } catch (RuntimeException e) {
                        System.out.println("File " + currentDirectory.getName()
                                + " has not been filled.");
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("File already exists.");
                updateCurrentDirectory(currentDirectory.getParent());
            }
        } catch (IOException e) {
            System.out.println("File " + currentDirectory.getName()
                    + " has not been created.");
            updateCurrentDirectory(currentDirectory.getParent());
            e.printStackTrace();
        }
    }

    private static void createDirectory() {
        if (currentDirectory.mkdir()) {
            System.out.println("Directory " + currentDirectory.getName() + " has been created.");
        } else {
            System.out.println("Directory " + currentDirectory.getName() + " exists.");
            updateCurrentDirectory(currentDirectory.getParent());
        }
    }

    private static void editFile(String input) {
        if (!currentDirectoryExists()) {
            return;
        }
        String[] splittedInput = input.split(" ");
        if (splittedInput.length > 2) {
            int dotIndex = input.lastIndexOf(splittedInput[2]);
            String fileText = "\n" + input.substring(dotIndex);
            try {
                fillOutFile(fileText, true);
                System.out.println("File " + currentDirectory.getName() + " has been successfully edited.");
            } catch (RuntimeException e) {
                System.out.println("File " + currentDirectory.getName() + " has not been edited.");
                updateCurrentDirectory(currentDirectory.getParent());
                e.printStackTrace();
            }
        } else {
            System.out.println("Input is empty.");
            updateCurrentDirectory(currentDirectory.getParent());
        }
    }

    private static void fillOutFile(String fileText, boolean append) throws RuntimeException {
        try (FileWriter output = new FileWriter(currentPath, append)) {
            output.write(fileText);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void renameTo(String newName) {
        if (!currentDirectoryExists()) {
            return;
        }
        File newDir = new File(currentDirectory.getParent() + "/" + newName);
        if (currentDirectory.renameTo(newDir)) {
            System.out.println("Directory of file" + currentDirectory.getName()
                    + " has been successfully renamed.");
        } else {
            System.out.println("Directory of file" + currentDirectory.getName()
                    + " has not been renamed.");
        }
        updateCurrentDirectory(currentDirectory.getParent());
    }

    private static void printInfo() {
        if (!currentDirectoryExists()) {
            return;
        }
        Map<String, String> info = new HashMap<>();
        info.put("Absolute path: ", currentDirectory.getAbsolutePath());
        info.put("Size: ", currentDirectory.length() + " bytes");
        Path file = Paths.get(currentPath);
        FileTime creationTime = null;
        try {
            creationTime = (FileTime) Files.getAttribute(file, "creationTime");
        } catch (IOException e) {
            e.printStackTrace();
        }
        info.put("Creation time: ", creationTime != null ? creationTime.toString() : "");
        if (currentDirectory.isDirectory()) {
            int numberNestedElements = countNestedElements(currentDirectory, -1);
            info.put("Number of nested elements: ", String.valueOf(numberNestedElements));
        }
        info.forEach((key, value) -> System.out.println(key + " " + value));
    }

    public static int countNestedElements(File directory, int count) {
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

    public static void deleteDirectory(File directory) {
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

    private static void delete() {
        if (!currentDirectoryExists()) {
            return;
        }
        File[] list = currentDirectory.isDirectory() ? currentDirectory.listFiles() : null;
        if (currentDirectory.isFile() || (list != null && list.length == 0)) {
            deleteDirectory(currentDirectory);
            System.out.println("Directory or file " + currentDirectory.getName() + " has been successfully deleted.");
            updateCurrentDirectory(currentDirectory.getParent());
            return;
        }
        System.out.println("Directory " + currentDirectory.getName() + " is not empty. Are you sure you want to delete it? Yes/No");
        Scanner in = new Scanner(System.in);
        String input;
        while (true) {
            input = in.nextLine();
            if (input.equals("Yes")) {
                deleteDirectory(currentDirectory);
                System.out.println("Directory " + currentDirectory.getName() + " has been successfully deleted.");
                updateCurrentDirectory(currentDirectory.getParent());
                return;
            } else if (input.equals("No")) {
                System.out.println("Directory " + currentDirectory.getName() + " has not been deleted.");
                updateCurrentDirectory(currentDirectory.getParent());
                return;
            } else {
                System.out.println("Incorrect command. Try again.");
            }
        }
    }

    private static void updateCurrentDirectory(String newCurrentPath) {
        currentPath = newCurrentPath;
        currentDirectory = new File(currentPath);
    }

    private static boolean currentDirectoryExists() {
        if (!currentDirectory.exists()) {
            System.out.println("Directory of file with this name does not exist.");
            updateCurrentDirectory(currentDirectory.getParent());
            return false;
        }
        return true;
    }
}
