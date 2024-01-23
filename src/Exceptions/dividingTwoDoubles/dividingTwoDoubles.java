package Exceptions.dividingTwoDoubles;

/**
 * Реализовать аналогичный метод деления, который принимает числа Doublе. Метод должен:
 * Проверить, что входящие числа не равны null, если равны, то выбрасывается исключение IllegalArgumentException
 * Проверить, если делитель равен 0, то выбросить собственное checked исключение
 * Вывести результат деления в консоль
 */
public class dividingTwoDoubles {
    public static double dividing(Double firstNum, Double secondNum) throws IllegalArgumentException, ArithmeticException, DividerIsNullException {
        if (firstNum == null || secondNum == null) {
            throw new IllegalArgumentException("Number is null");
        } else if (secondNum == 0) {
            throw new DividerIsNullException("Divider is null");
        }
        double result = firstNum / secondNum;
        System.out.println(result);
        return result;
    }
}
