package PracticeJavaMemoryModelLinksObject;

/**
 * Написать метод (с тестами), который:
 * Принимает 2 строки и пытается их привести к целочисленным или вещественным числам.
 * Метод должен вернуть результат сложения 2 чисел типа Number.
 * Пример:
 * Входящая строки: “1,1” и “2”
 * Исходящие число: 3,1
 */

public class Task10 {
    public static Number stringConversion(String str1, String str2) {
        double num1 = Double.parseDouble(str1);
        double num2 = Double.parseDouble(str2);
        return num1 + num2;
    }
}
