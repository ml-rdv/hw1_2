package ProgrammingBasics;// Через цикл найти факториал числа

import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input number: ");
        int num = sc.nextInt();
        long factor = 1;
        for (int i = 1; i <= num; i++)
            factor *= i;
        System.out.println(factor);
    }
}
