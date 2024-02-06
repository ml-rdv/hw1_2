package InputOutputStream.ConsoleTextFileEditor;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ConsoleTextFileEditor console = new ConsoleTextFileEditor();
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
}
