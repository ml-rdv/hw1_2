package InputOutputStream.ConsoleTextFileEditor;

public class FileSystemResponse<T> {
    private T body;
    private String messageError;

    public FileSystemResponse(T body) {
        this.body = body;
    }

    public FileSystemResponse(String messageError) {
        this.messageError = messageError;
    }

    public T getBody() {
        return body;
    }

    public String getMessageError() {
        return messageError;
    }
}
