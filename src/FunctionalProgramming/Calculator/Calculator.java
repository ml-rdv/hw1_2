package FunctionalProgramming.Calculator;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

/**
 * Реализовать калькулятор с операциями для 2х чисел в функциональном стиле
 * Каждая операция (сложение, вычитание, …) должна быть функцией. Для них нужно
 * создать отдельный функциональный интерфейс.
 * В калькуляторе должен быть метод execute, который принимает 2 числа и операцию.
 * Определение операции желательно сделать без условных операторов.
 */
public class Calculator {
    public static int execute(int x, int y, Operation operator) {
        return operator.calc(x, y);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input operator: ");
        char str = sc.nextLine().charAt(0);
        System.out.println("Input first number: ");
        int x = sc.nextInt();
        System.out.println("Input second number: ");
        int y = sc.nextInt();

        Map<Character, Operation> operations = Map.of(
                '+', Integer::sum,
                '-', (x1, y1) -> x1 - y1,
                '*', (x1, y1) -> x1 * y1,
                '/', (x1, y1) -> (x1 / y1)
        );

        Operation operation = Optional.ofNullable(operations.get(str))
                .orElseThrow(() -> {
                    System.out.println("Operator is not correct.");
                    return new RuntimeException();
                });

        System.out.println(execute(x, y, operation));
    }
}
