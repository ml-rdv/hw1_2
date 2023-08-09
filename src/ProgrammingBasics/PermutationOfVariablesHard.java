package ProgrammingBasics;// Ввести 2 переменных(int) и поменять их местами без использования третьей переменной. *

import java.util.Scanner;

public class PermutationOfVariablesHard {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input first number: ");
        int x = sc.nextInt();
        System.out.println("Input second number: ");
        int y = sc.nextInt();
        System.out.printf("x = %d, y = %d;\n", x, y);
        x = x + y;
        y = x - y;
        x = x - y;
        System.out.println("After change:");
        System.out.printf("x = %d, y = %d;\n", x, y);
    }
}
