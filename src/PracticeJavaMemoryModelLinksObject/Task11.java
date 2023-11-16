/*
Написать метод, который вычисляет выражение “1.1 - 0.9” и выводит результат в консоль.
Затем тоже самое выражение вычислить с помощью BigDecimal. Сравнить полученные результаты.
 */
package PracticeJavaMemoryModelLinksObject;

import java.math.BigDecimal;

public class Task11 {
    public static void subtractionWithoutBigDecimal() {
        System.out.println(1.1 - 0.9);
    }

    public static void subtractionWithBigDecimal() {
        BigDecimal firstNumber = new BigDecimal("1.1");
        BigDecimal secondNumber = new BigDecimal("0.9");

        System.out.println(firstNumber.subtract(secondNumber));
    }

    public static void main(String[] args) {
        subtractionWithoutBigDecimal();
        subtractionWithBigDecimal();
    }
}
