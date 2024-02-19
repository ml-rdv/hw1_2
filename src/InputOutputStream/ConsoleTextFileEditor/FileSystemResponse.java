package InputOutputStream.ConsoleTextFileEditor;

public class FileSystemResponse<T> {
    private T body;
    private String messageError;

    public static <T> FileSystemResponse<T> createErrorResponse(String messageError) {
        return new FileSystemResponse<>(messageError);
    }

    public FileSystemResponse(T body, String messageError) {
        this.body = body;
        this.messageError = messageError;
    }

    public FileSystemResponse(T body) {
        this.body = body;
    }

    private FileSystemResponse(String messageError) {
        this.messageError = messageError;
    }

    public T getBody() {
        return body;
    }

    public String getMessageError() {
        return messageError;
    }
}
