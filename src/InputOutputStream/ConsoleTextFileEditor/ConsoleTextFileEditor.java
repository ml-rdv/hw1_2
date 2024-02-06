package InputOutputStream.ConsoleTextFileEditor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
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
    private static final ConsoleTextFileEditor console = new ConsoleTextFileEditor();

    public static void main(String[] args) throws IOException, InterruptedException {
        console.start();
        Scanner in = new Scanner(System.in);
        String input;
        while (true) {
            input = in.nextLine();
            System.out.print("\033[H\033[J");
            System.out.flush();
            if (!input.equals("finish")) {
                console.continueWork(input);
            } else {
                in.close();
                break;
            }
        }
    }

    private void start() {
        openDirectory(currentDirectory.getPath());
    }

    private void continueWork(String input) {
        String[] splittedInput = input.split(" ");
        String command = splittedInput[0];
        switch (command) {
            case "open" -> {
                String path = currentDirectory.getPath() + "/" + splittedInput[1];
                File directory = new File(path);
                if (directory.isFile()) {
                    openFile(path);
                } else {
                    openDirectory(path);
                }
            }
            case "create" -> {
                if (splittedInput[1].equals("-f")) {
                    createFile(input);
                } else {
                    String path = currentDirectory.getPath() + "/" + splittedInput[1];
                    createDirectory(path);
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
                editFile(input);
            }
            case "delete" -> {
                String path = currentDirectory.getPath() + "/" + splittedInput[1];
                delete(path);
            }
            case "rename" -> {
                if (splittedInput.length == 3) {
                    String path = currentDirectory.getPath() + "/" + splittedInput[1];
                    renameTo(path, splittedInput[2]);
                } else {
                    System.out.println("Input 3 parameters");
                }
            }
            case "info" -> {
                String path = currentDirectory.getPath() + "/" + splittedInput[1];
                printInfo(path);
            }
            default -> System.out.println("Command is not correct. Try again.");
        }
        System.out.println("Current path: " + currentDirectory.getPath());
    }

    private void openFile(String filePath) {
        if (!currentDirectoryExists(filePath)) {
            return;
        }
        File fileDirectory = new File(filePath);
        String fileName = fileDirectory.getName();
        int dotIndex = fileName.lastIndexOf('.');
        String extension = (dotIndex == -1) ? "" : fileName.substring(dotIndex);
        if (extension.equals(".txt")) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileDirectory))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("The file format is incorrect.");
        }
        System.out.println();
    }

    private void openDirectory(String path) {
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

    private void createFile(String input) {
        String[] splittedInput = input.split(" ");
        String fileText = null;
        if (splittedInput.length > 3) {
            int dotIndex = input.lastIndexOf(splittedInput[3]);
            fileText = input.substring(dotIndex);
        }
        File fileDirectory = new File(currentDirectory.getPath() + "/" + splittedInput[2]);
        try {
            if (fileDirectory.createNewFile()) {
                System.out.println("File created: " + fileDirectory.getName());
                if (fileText != null) {
                    try {
                        fillOutFile(fileDirectory.getPath(), fileText, false);
                        System.out.println("File " + fileDirectory.getName()
                                + " has been successfully filled.");
                    } catch (RuntimeException e) {
                        System.out.println("File " + fileDirectory.getName()
                                + " has not been filled.");
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("File " + fileDirectory.getName()
                    + " has not been created.");
            e.printStackTrace();
        }
    }

    private void createDirectory(String path) {
        File directory = new File(path);
        if (directory.mkdir()) {
            System.out.println("Directory " + directory.getName() + " has been created.");
        } else {
            System.out.println("Directory " + directory.getName() + " exists.");
        }
    }

    private void editFile(String input) {
        String[] splittedInput = input.split(" ");
        String filePath = currentDirectory.getPath() + "/" + splittedInput[1];
        if (!currentDirectoryExists(filePath)) {
            return;
        }
        File fileDirectory = new File(filePath);
        if (splittedInput.length > 2) {
            int dotIndex = input.lastIndexOf(splittedInput[2]);
            String fileText = "\n" + input.substring(dotIndex);
            try {
                fillOutFile(fileDirectory.getPath(), fileText, true);
                System.out.println("File " + fileDirectory.getName() + " has been successfully edited.");
            } catch (RuntimeException e) {
                System.out.println("File " + fileDirectory.getName() + " has not been edited.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Input is empty.");
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

    private void renameTo(String path, String newName) {
        if (!currentDirectoryExists(path)) {
            return;
        }
        File oldNameDirectory = new File(path);
        File newNameDirectory = new File(oldNameDirectory.getParent() + "/" + newName);
        if (oldNameDirectory.renameTo(newNameDirectory)) {
            System.out.println("Directory or file " + oldNameDirectory.getName()
                    + " has been successfully renamed.");
        } else {
            System.out.println("Directory or file " + oldNameDirectory.getName()
                    + " has not been renamed.");
        }
    }

    private void printInfo(String path) {
        if (!currentDirectoryExists(path)) {
            return;
        }
        File directory = new File(path);
        Map<String, String> info = new HashMap<>();
        info.put("Absolute path: ", directory.getPath());
        info.put("Size: ", directory.length() + " bytes");
        Path file = Paths.get(directory.getPath());
        FileTime creationTime = null;
        try {
            creationTime = (FileTime) Files.getAttribute(file, "creationTime");
        } catch (IOException e) {
            e.printStackTrace();
        }
        info.put("Creation time: ", creationTime != null ? creationTime.toString() : "");
        if (directory.isDirectory()) {
            int numberNestedElements = countNestedElements(directory, -1);
            info.put("Number of nested elements: ", String.valueOf(numberNestedElements));
        }
        info.forEach((key, value) -> System.out.println(key + " " + value));
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

    private void delete(String path) {
        if (!currentDirectoryExists(path)) {
            return;
        }
        File directory = new File(path);
        File[] list = directory.isDirectory() ? directory.listFiles() : null;
        if (directory.isFile() || (list != null && list.length == 0)) {
            deleteDirectory(directory);
            System.out.println("Directory or file " + directory.getName() + " has been successfully deleted.");
            return;
        }
        System.out.println("Directory " + directory.getName() + " is not empty. Are you sure you want to delete it? Yes/No");
        Scanner in = new Scanner(System.in);
        String input;
        while (true) {
            input = in.nextLine();
            if (input.equals("Yes")) {
                deleteDirectory(directory);
                System.out.println("Directory " + directory.getName() + " has been successfully deleted.");
                return;
            } else if (input.equals("No")) {
                System.out.println("Directory " + directory.getName() + " has not been deleted.");
                return;
            } else {
                System.out.println("Incorrect command. Try again.");
            }
        }
    }

    private boolean currentDirectoryExists(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            System.out.println("Directory or file with this name does not exist.");
            return false;
        }
        return true;
    }
}
