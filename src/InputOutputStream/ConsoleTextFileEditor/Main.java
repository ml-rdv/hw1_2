package InputOutputStream.ConsoleTextFileEditor;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ConsoleTextFileEditor console = new ConsoleTextFileEditor(args[0]);
        console.start();
    }
}
