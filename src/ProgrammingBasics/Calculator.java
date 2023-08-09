package ProgrammingBasics;// Написать калькулятор, в консоль вводим по отдельности первое число, второе число
// и арифметический оператор, программа должна произвести арифметическое действие и вывести результат.
// (Калькулятор должен уметь работать с дробными числами)

import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input operator: ");
        char str = sc.nextLine().charAt(0);
        System.out.println("Input first number: ");
        double x = sc.nextDouble();
        System.out.println("Input second number: ");
        double y = sc.nextDouble();
        double result = 0;
        switch (str) {
            case '+':
                result = x + y;
                break;
            case '-':
                result = x - y;
                break;
            case '*':
                result = x * y;
                break;
            case '/':
                if (y != 0) {
                    result = x / y;
                    break;
                } else {
                    System.out.println("It is impossible.");
                    return;
                }
            default:
                System.out.println("Operator is not correct.");
                return;
        }
        if (result % 1 == 0) {
            System.out.println((int) result);
        } else
            System.out.println(result);
    }
}
