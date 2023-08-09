package ProgrammingBasics;// Ввести 2 переменных(int) и поменять их местами.

import java.util.Scanner;

public class PermutationOfVariables {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input first number: ");
        int x = sc.nextInt();
        System.out.println("Input second number: ");
        int y = sc.nextInt();
        System.out.printf("x = %d, y = %d;\n", x, y);
        int z = x;
        x = y;
        y = z;
        System.out.println("After change:");
        System.out.printf("x = %d, y = %d;\n", x, y);
    }
}