package InputOutputStream.ConsoleTextFileEditor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FileSystemManagement {

    private File currentDirectory = new File("");
    static String MESSAGE_FORMAT_SUCCESS = "%s %s has been successfully %s.\n";
    static String MESSAGE_FORMAT_NOT_SUCCESS = "%s %s has not been %s.\n";

    public void setCurrentDirectory(File currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    public FileSystemResponse<StringBuilder> getTextFileContents(String name) {
        String path = currentDirectory.getPath() + "\\" + name;
        File file = new File(path);
        if (isDirectoryNotExists(path)) {
            return new FileSystemResponse<>("File " + file.getName() + " does not exist.");
        }
        StringBuilder sb = new StringBuilder();
        if (extensionIsCorrect(file)) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
                return new FileSystemResponse<>(sb);
            } catch (IOException e) {
                e.printStackTrace();
                return new FileSystemResponse<>(e.toString());
            }
        }
        return new FileSystemResponse<>("The file extension is incorrect.");
    }

    private boolean extensionIsCorrect(File fileDirectory) {
        String fileName = fileDirectory.getName();
        int dotIndex = fileName.lastIndexOf('.');
        String extension = (dotIndex == -1) ? "" : fileName.substring(dotIndex);
        return extension.equals(".txt");
    }

    public FileSystemResponse<List<String>> getDirectory(String path) {
        File directory = new File(path);
        if (isDirectoryNotExists(path)) {
            return new FileSystemResponse<>("Directory " + directory.getName() + " does not exist.");
        }
        currentDirectory = directory;
        File[] listFiles = currentDirectory.listFiles();

        if (listFiles == null) {
            return new FileSystemResponse<>(List.of());
        }
        List<String> list = new ArrayList<>();
        for (File f : listFiles) {
            if (f.isFile()) {
                list.add("-f " + f.getName());
            } else if (f.isDirectory()) {
                list.add("-d " + f.getName());
            } else {
                list.add(f.getName());
            }
        }
        return new FileSystemResponse<>(list);
    }

    public FileSystemResponse<Boolean> createFile(String name, String fileText) {
        File path = new File(currentDirectory.getPath() + "\\" + name);
        try {
            if (path.createNewFile()) {
                if (fileText != null) {
                    try {
                        fillOutFile(path.getPath(), fileText, false);
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }
                    return new FileSystemResponse<>(true);
                }
            } else {
                return new FileSystemResponse<>("File with this name already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new FileSystemResponse<>(e.toString());
        }
        return new FileSystemResponse<>("Unknown error");
    }

    private void fillOutFile(String path, String fileText, boolean append) throws RuntimeException {
        File fileDirectory = new File(path);
        try (FileWriter output = new FileWriter(fileDirectory.getPath(), append)) {
            output.write(fileText);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public FileSystemResponse<Boolean> createDirectory(String name) {
        String path = currentDirectory.getPath() + "\\" + name;
        File directory = new File(path);
        if (!isDirectoryNotExists(path)) {
            return new FileSystemResponse<>("Directory with this name already exists.");
        }
        return new FileSystemResponse<>(directory.mkdir());
    }

    public FileSystemResponse<Boolean> back() {
        if (currentDirectory.getPath().equals(".")) {
            return new FileSystemResponse<>("It is a root directory.");
        }
        currentDirectory = new File(currentDirectory.getParent());
        return new FileSystemResponse<>(true);
    }

    public FileSystemResponse<Boolean> editFile(String nameFile, String text) {
        String path = currentDirectory.getPath() + "\\" + nameFile;
        File file = new File(path);
        if (isDirectoryNotExists(path)) {
            return new FileSystemResponse<>("File " + file.getName() + " does not exist.");
        }
        try {
            fillOutFile(file.getPath(), text, true);
            return new FileSystemResponse<>(true);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new FileSystemResponse<>(e.toString());
        }
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

    public FileSystemResponse<Boolean> delete(String path, boolean deleteWithNestedDirectories) {
        File directory = new File(path);
        if (isDirectoryNotExists(path)) {
            return new FileSystemResponse<>("Directory or file " + directory.getName() + " does not exist.");
        }
        File[] list = directory.isDirectory() ? directory.listFiles() : null;
        if (directory.isFile() || (list != null && list.length == 0)) {
            deleteDirectory(directory);
            return new FileSystemResponse<>(true);
        }
        if (!deleteWithNestedDirectories) {
            return new FileSystemResponse<>("Directory is not empty.");
        } else {
            deleteDirectory(directory);
            return new FileSystemResponse<>(true);
        }
    }

    public FileSystemResponse<Boolean> renameTo(String oldName, String newName) {
        File oldNameDirectory = new File(currentDirectory.getPath() + "\\" + oldName);
        if (isDirectoryNotExists(currentDirectory.getPath() + "\\" + oldName)) {
            return new FileSystemResponse<>("Directory or file " + oldNameDirectory.getName() + " does not exist.");
        }
        File newNameDirectory = new File(currentDirectory.getPath() + "\\" + newName);
        FileSystemResponse<Boolean> result = new FileSystemResponse<>(oldNameDirectory.renameTo(newNameDirectory));
        if (result.getBody()) {
            return result;
        } else {
            return new FileSystemResponse<>("Directory or file with this name already exists.");
        }
    }

    public FileSystemResponse<Map<String, String>> getInfo(String name) {
        String path = currentDirectory.getPath() + "\\" + name;
        File directory = new File(path);
        if (isDirectoryNotExists(path)) {
            return new FileSystemResponse<>("Directory or file " + directory.getName() + " does not exist.");
        }
        Map<String, String> info = new HashMap<>();
        info.put("Absolute path: ", directory.getAbsolutePath());
        info.put("Size: ", directory.length() + " bytes");
        Path file = Paths.get(directory.getPath());
        FileTime creationTimeFileTime;
        try {
            creationTimeFileTime = (FileTime) Files.getAttribute(file, "creationTime");
        } catch (IOException e) {
            e.printStackTrace();
            return new FileSystemResponse<>(e.toString());
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
        return new FileSystemResponse<>(info);
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

    private boolean isDirectoryNotExists(String path) {
        File directory = new File(path);
        return !directory.exists();
    }

    public File getCurrentDirectory() {
        return currentDirectory;
    }
}
