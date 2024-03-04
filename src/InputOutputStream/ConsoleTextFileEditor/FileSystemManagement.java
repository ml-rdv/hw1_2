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
            return FileSystemResponse.createInfoResponse("File " + file.getName() + " does not exist.");
        }
        if (!file.getPath().endsWith(".txt")) {
            return FileSystemResponse.createInfoResponse("The file extension is incorrect.");
        }
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
            return new FileSystemResponse<>(sb.toString());
        } catch (IOException e) {
            return FileSystemResponse.createInfoResponse(e.toString());
        }
    }

    public FileSystemResponse<Boolean> setDirectory(String path) {
        File directory = new File(path);
        if (Files.notExists(Path.of(path))) {
            return FileSystemResponse.createInfoResponse("Directory " + directory.getName() + " does not exist.");
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

    public FileSystemResponse<String> createFile(String name, String fileText) {
        File path = new File(currentDirectory.getPath() + "\\" + name);
        try {
            if (!path.createNewFile()) {
                return new FileSystemResponse<>(String.format("File %s has been not created.\n", name),
                        "File with this name already exists.");
            }
            if (fileText != null && !fileText.isEmpty()) {
                try (FileWriter output = new FileWriter(path.getPath(), false)) {
                    output.write(fileText);
                }
            }
            return new FileSystemResponse<>(String.format("File %s has been successfully created.\n", name));
        } catch (Exception e) {
            return new FileSystemResponse<>(String.format("File %s has been not created.\n", name), e.toString());
        }
    }

    public FileSystemResponse<String> createDirectory(String name) {
        String path = currentDirectory.getPath() + "\\" + name;
        File directory = new File(path);
        if (Files.exists(Path.of(path))) {
            return new FileSystemResponse<>(String.format("Directory %s has been not created.\n", name),
                    "Directory with this name already exists.");
        }
        if (directory.mkdir()) {
            return new FileSystemResponse<>(String.format("Directory %s has been successfully created.\n", name));
        } else {
            return FileSystemResponse.createInfoResponse(String.format("Directory %s has been not created.\n", name));
        }
    }

    public FileSystemResponse<Boolean> backToParentDirectory() {
        if (currentDirectory.getParent() == null) {
            return FileSystemResponse.createInfoResponse("It is a root directory.");
        }
        currentDirectory = new File(currentDirectory.getParent());
        return new FileSystemResponse<>(true);
    }

    public FileSystemResponse<String> editFile(String nameFile, String text) {
        String path = currentDirectory.getPath() + "\\" + nameFile;
        File file = new File(path);
        if (Files.notExists(Path.of(path))) {
            return new FileSystemResponse<>(String.format("File %s has been not edited.\n", nameFile),
                    String.format("File %s does not exist.", file.getName()));
        }
        try (FileWriter output = new FileWriter(file.getPath(), true)) {
            output.write(text);
            return new FileSystemResponse<>(String.format("File %s has been successfully edited.\n", nameFile));
        } catch (Exception e) {
            return new FileSystemResponse<>(String.format("File %s has been not edited.\n", nameFile),
                    e.toString());
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

    public FileSystemResponse<DeleteResponse> delete(String path) {
        File directory = new File(path);
        if (Files.notExists(Path.of(path))) {
            DeleteResponse deleteResponse = new DeleteResponse(false, false, true);
            String message = String.format("Directory or file %s has been not deleted.\n", directory.getName());
            return new FileSystemResponse<>(deleteResponse, message);
        }

        File[] list = directory.listFiles();
        if (list == null || list.length == 0) {
            directory.delete();
            DeleteResponse deleteResponse = new DeleteResponse(true);
            String message = String.format("Directory or file %s has been successfully deleted.\n", directory.getName());
            return new FileSystemResponse<>(deleteResponse, message);
        }

        DeleteResponse deleteResponse = new DeleteResponse(false, true, false);
        String message = String.format("Directory or file %s has been not deleted.\n", directory.getName());
        return new FileSystemResponse<>(deleteResponse, message);
    }

    public FileSystemResponse<DeleteResponse> deleteWithNestedDirectories(String path) {
        File directory = new File(path);
        if (Files.notExists(Path.of(path))) {
            return new FileSystemResponse<>(new DeleteResponse(false, false, true),
                    String.format("Directory or file %s has been not deleted.\n", directory.getName()));
        }
        deleteDirectory(directory);
        return new FileSystemResponse<>(new DeleteResponse(true),
                String.format("Directory or file %s has been successfully deleted.\n", directory.getName()));
    }

    public FileSystemResponse<String> renameTo(String oldName, String newName) {
        File oldNameDirectory = new File(currentDirectory.getPath() + "\\" + oldName);
        String path = currentDirectory.getPath() + "\\" + oldName;
        if (Files.notExists(Path.of(path))) {
            return new FileSystemResponse<>(String.format("Directory or file %s has been not renamed.\n", oldName),
                    String.format("Directory or file %s does not exist.", oldName));
        }
        File newNameDirectory = new File(currentDirectory.getPath() + "\\" + newName);
        if (oldNameDirectory.renameTo(newNameDirectory)) {
            return new FileSystemResponse<>(String.format("Directory or file %s has been successfully renamed.\n", oldName));
        } else {
            return new FileSystemResponse<>(String.format("Directory or file %s has been not renamed.\n", oldName),
                    "Directory or file with this name already exists.");
        }
    }

    public FileSystemResponse<Map<String, String>> getInfo(String name) {
        String path = currentDirectory.getPath() + "\\" + name;
        File directory = new File(path);
        if (Files.notExists(Path.of(path))) {
            return FileSystemResponse.createInfoResponse("Directory or file " + directory.getName() + " does not exist.");
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
