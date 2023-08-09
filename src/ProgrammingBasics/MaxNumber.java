package ProgrammingBasics;// Ввести 2 переменных(int) и вывести наибольшее (без Math)

import java.util.Scanner;

public class MaxNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input first number: ");
        int x = sc.nextInt();
        System.out.println("Input second number: ");
        int y = sc.nextInt();
        System.out.println("Max is: " + (x > y ? x : y));
    }
}