package ProgrammingBasics;// Ввести число и вывести признак чётности/нечётности

import java.util.Scanner;

public class ParityOfNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input first number: ");
        int x = sc.nextInt();
        System.out.println((x % 2 == 0 ? "Number is even" : "Number is uneven"));
    }
}
