package InputOutputStream.ConsoleTextFileEditor;

public class FileSystemResponse<T> {
    private T body;
    private String messageInfo;

    public static <T> FileSystemResponse<T> createErrorResponse(String messageError) {
        return new FileSystemResponse<>(messageError);
    }

    public FileSystemResponse(T body, String messageInfo) {
        this.body = body;
        this.messageInfo = messageInfo;
    }

    public FileSystemResponse(T body) {
        this.body = body;
    }

    private FileSystemResponse(String messageInfo) {
        this.messageInfo = messageInfo;
    }

    public T getBody() {
        return body;
    }

    public String getMessageInfo() {
        return messageInfo;
    }
}
