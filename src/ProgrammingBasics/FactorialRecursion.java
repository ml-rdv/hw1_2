package ProgrammingBasics;// Сделать рекурсивный метод по нахождению факториала.

import java.util.Scanner;

public class FactorialRecursion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input number: ");
        int num = sc.nextInt();
        System.out.println(factorial(num));
    }

    public static long factorial(int num) {
        if (num == 1) {
            return 1;
        }
        return num * factorial(num - 1);
    }
}
