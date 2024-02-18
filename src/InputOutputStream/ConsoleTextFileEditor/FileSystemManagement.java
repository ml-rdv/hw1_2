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

    private File currentDirectory;
    private final String DEFAULT_DIR = "";
    static String MESSAGE_FORMAT_SUCCESS = "%s %s has been successfully %s.\n";
    static String MESSAGE_FORMAT_NOT_SUCCESS = "%s %s has not been %s.\n";

    public FileSystemManagement() {
        this.currentDirectory = new File(DEFAULT_DIR);
    }

    public FileSystemManagement(String directory) {
        if (Files.exists(Path.of(directory))) {
            this.currentDirectory = new File(directory);
        } else {
            this.currentDirectory = new File(DEFAULT_DIR);
        }
    }

    public FileSystemResponse<String> getTextFileContents(String name) {
        String path = currentDirectory.getPath() + "\\" + name;
        File file = new File(path);
        if (Files.notExists(Path.of(path))) {
            return FileSystemResponse.createErrorResponse("File " + file.getName() + " does not exist.");
        }
        StringBuilder sb = new StringBuilder();
        if (file.getPath().endsWith(".txt")) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
                return new FileSystemResponse<>(sb.toString());
            } catch (IOException e) {
                return FileSystemResponse.createErrorResponse(e.toString());
            }
        } else {
            return FileSystemResponse.createErrorResponse("The file extension is incorrect.");
        }
    }

    public FileSystemResponse<Boolean> setDirectory(String path) {
        File directory = new File(path);
        if (Files.notExists(Path.of(path))) {
            return FileSystemResponse.createErrorResponse("Directory " + directory.getName() + " does not exist.");
        }
        currentDirectory = directory;
        return new FileSystemResponse<>(true);
    }

    public FileSystemResponse<List<String>> getListFiles() {
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
            if (!path.createNewFile()) {
                return FileSystemResponse.createErrorResponse("File with this name already exists.");
            }
            if (fileText != null && !fileText.isEmpty()) {
                try (FileWriter output = new FileWriter(path.getPath(), false)) {
                    output.write(fileText);
                }
            }
            return new FileSystemResponse<>(true);
        } catch (Exception e) {
            return FileSystemResponse.createErrorResponse(e.toString());
        }
    }

    public FileSystemResponse<Boolean> createDirectory(String name) {
        String path = currentDirectory.getPath() + "\\" + name;
        File directory = new File(path);
        if (Files.exists(Path.of(path))) {
            return FileSystemResponse.createErrorResponse("Directory with this name already exists.");
        }
        return new FileSystemResponse<>(directory.mkdir());
    }

    public FileSystemResponse<Boolean> backToParentDirectory() {
        if (currentDirectory.getParent() == null) {
            return FileSystemResponse.createErrorResponse("It is a root directory.");
        }
        currentDirectory = new File(currentDirectory.getParent());
        return new FileSystemResponse<>(true);
    }

    public FileSystemResponse<Boolean> editFile(String nameFile, String text) {
        String path = currentDirectory.getPath() + "\\" + nameFile;
        File file = new File(path);
        if (Files.notExists(Path.of(path))) {
            return FileSystemResponse.createErrorResponse("File " + file.getName() + " does not exist.");
        }
        try (FileWriter output = new FileWriter(file.getPath(), true)) {
            output.write(text);
            return new FileSystemResponse<>(true);
        } catch (Exception e) {
            return FileSystemResponse.createErrorResponse(e.toString());
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

    public FileSystemResponse<Boolean> delete(String path) {
        File directory = new File(path);
        if (Files.notExists(Path.of(path))) {
            return FileSystemResponse.createErrorResponse("Directory or file " + directory.getName() + " does not exist.");
        }

        File[] list = directory.listFiles();
        if (list == null || list.length == 0) {
            directory.delete();
            return new FileSystemResponse<>(true);
        }
        return FileSystemResponse.createErrorResponse("Directory is not empty.");
    }

    public FileSystemResponse<Boolean> deleteWithNestedDirectories(String path) {
        File directory = new File(path);
        if (Files.notExists(Path.of(path))) {
            return FileSystemResponse.createErrorResponse("Directory or file " + directory.getName() + " does not exist.");
        }

        deleteDirectory(directory);
        return new FileSystemResponse<>(true);
    }

    public FileSystemResponse<Boolean> renameTo(String oldName, String newName) {
        File oldNameDirectory = new File(currentDirectory.getPath() + "\\" + oldName);
        String path = currentDirectory.getPath() + "\\" + oldName;
        if (Files.notExists(Path.of(path))) {
            return FileSystemResponse.createErrorResponse("Directory or file " + oldNameDirectory.getName() + " does not exist.");
        }
        File newNameDirectory = new File(currentDirectory.getPath() + "\\" + newName);
        if (oldNameDirectory.renameTo(newNameDirectory)) {
            return new FileSystemResponse<>(true);
        } else {
            return FileSystemResponse.createErrorResponse("Directory or file with this name already exists.");
        }
    }

    public FileSystemResponse<Map<String, String>> getInfo(String name) {
        String path = currentDirectory.getPath() + "\\" + name;
        File directory = new File(path);
        if (Files.notExists(Path.of(path))) {
            return FileSystemResponse.createErrorResponse("Directory or file " + directory.getName() + " does not exist.");
        }
        Map<String, String> info = new HashMap<>();
        info.put("Absolute path: ", directory.getAbsolutePath());
        info.put("Size: ", directory.length() + " bytes");
        Path file = Paths.get(directory.getPath());
        FileTime creationTimeFileTime;
        try {
            creationTimeFileTime = (FileTime) Files.getAttribute(file, "creationTime");
            LocalDateTime localDateTime = creationTimeFileTime
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            String creationTime = localDateTime.format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));
            info.put("Creation time: ", creationTime);
        } catch (IOException e) {
            info.put("Creation time: ", "Could not determine creation time");
        }
        if (directory.isDirectory()) {
            int numberNestedElements = countNestedElements(directory);
            info.put("Number of all nested elements: ", String.valueOf(numberNestedElements));

            File[] arrFiles = directory.listFiles();
            int directoryLength = arrFiles.length;
            info.put("Number of elements: ", String.valueOf(directoryLength));
        }
        return new FileSystemResponse<>(info);
    }

    int countNestedElements(File directory) {
        File[] files = directory.listFiles();
        int count = files != null ? files.length : 0;
        if (files != null) {
            for (File file : files) {
                count += countNestedElements(file);
            }
        }
        return count;
    }

    public File getCurrentDirectory() {
        return currentDirectory;
    }
}
