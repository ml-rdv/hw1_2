package InputOutputStream.ConsoleTextFileEditor;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ConsoleTextFileEditor console;
        if (args.length == 0) {
            console = new ConsoleTextFileEditor();
        } else {
            console = new ConsoleTextFileEditor(args[0]);
        }
        console.start();
    }
}
