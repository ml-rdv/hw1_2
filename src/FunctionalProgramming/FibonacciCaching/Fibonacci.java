package FunctionalProgramming.FibonacciCaching;

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input number: ");
        int num = sc.nextInt();
        System.out.println(fibonachchi(num - 1));
    }

    public static long fibonachchi(int num) {
        if (num == 0)
            return 0;
        if (num == 1)
            return 1;
        else
            return fibonachchi(num - 1) + fibonachchi(num - 2);
    }

    private void memorizing() {

    }
}
