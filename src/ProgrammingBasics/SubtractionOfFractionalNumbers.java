package ProgrammingBasics;// Ввести 2 дробных числа, например 1.1 и 0.9. Вычесть одно из другого и посмотреть какой результат получиться.

import java.util.Scanner;

public class SubtractionOfFractionalNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input first number: ");
        double x = sc.nextDouble();
        System.out.println("Input second number: ");
        double y = sc.nextDouble();
        System.out.printf("x - y = %f\n", (x - y));
    }
}
