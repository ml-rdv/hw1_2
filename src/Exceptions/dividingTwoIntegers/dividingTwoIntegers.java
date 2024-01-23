package Exceptions.dividingTwoIntegers;

/**
 * Реализовать метод деления двух чисел типа Integer. Метод должен:
 * Выводить в консоль результат деления
 * Перехватывать исключение NullPointerException с выводом stacktrace’а в консоль
 * Перехватывать исключение ArithmeticException с выводом в консоль информации об ошибке, а затем прокидывать исключение дальше наверх
 */

public class dividingTwoIntegers {
    public static int dividing(Integer firstNum, Integer secondNum) throws ArithmeticException {
        int result = 1;
        try {
            result = firstNum / secondNum;
            System.out.println(result);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        } catch (ArithmeticException ex) {
            System.out.println(ex);
            throw ex;
        }
        return result;
    }
}

