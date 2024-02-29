package FunctionalProgramming.Calculator;

public class Calculator {
    public static int execute(int x, int y, Adding<Integer> operator) {
        return operator.add(x, y);
    }

    public static int execute(int x, int y, Subtraction<Integer> operator) {
        return operator.subtract(x, y);
    }

    public static int execute(int x, int y, Division<Integer> operator) {
        return operator.divide(x, y);
    }

    public static int execute(int x, int y, Multiplication<Integer> operator) {
        return operator.multiply(x, y);
    }

    public static void main(String[] args) {
        int x = 10;
        int y = 5;

        Multiplication<Integer> multiply = (x1, y1) -> x1 * y1;
        System.out.println(execute(x, y, multiply));

        Adding<Integer> adding = Integer::sum;
        System.out.println(execute(x, y, adding));

        Subtraction<Integer> subtraction = (x1, y1) -> x1 - y1;
        System.out.println(execute(x, y, subtraction));

        Division<Integer> division = (x1, y1) -> (x1 / y1);
        System.out.println(execute(x, y, division));
    }
}
