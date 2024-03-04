package InputOutputStream.ConsoleTextFileEditor;

public record DeleteResponse(boolean isDeleted, boolean isNotEmpty, boolean notExist) {
    public DeleteResponse(boolean isDeleted) {
        this(isDeleted, false, false);
    }
}
