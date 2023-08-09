package ProgrammingBasics;// Вывести n-число последовательности Фибоначчи(через Цикл)

import java.util.Scanner;

public class FibonacciSequencesLoop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input number: ");
        int n = sc.nextInt();
        int lastNum = 0, currNum = 1, nextNum = 1;
        for (int i = 3; i <= n; i++) {
            nextNum = lastNum + currNum;
            lastNum = currNum;
            currNum = nextNum;
        }
        System.out.println((n != 0) ? nextNum : lastNum);
    }
}
