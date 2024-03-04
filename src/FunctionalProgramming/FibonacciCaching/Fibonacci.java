package FunctionalProgramming.FibonacciCaching;
/**
 * Для метода поиска числа Фибоначчи (рекурсивная реализация) нужно реализовать
 * метод кэширования(мемоизации) в функциональном стиле.
 * 1) Метод ничего не принимает
 * 2) Метод возвращает метод(функцию), которая принимает порядковый номер Фибоначчи
 * и возвращает само число Фибоначчи
 * 3) В методе можно использовать HashMap для кэширования вычисленных чисел
 * 4) Ожидаемое поведение: при первом запуске, например на числе 50, поиск числа
 * Фибоначчи займёт десятки секунд, когда как при втором запуске число найдётся за миллисекунды.
 */

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;

public class Fibonacci {

    public static void main(String[] args) {
        Function<Integer, Long> func = getFibonachchiFunc();
        Scanner sc = new Scanner(System.in);
        System.out.println("Input number: ");
        int num = sc.nextInt();
        long start1 = System.currentTimeMillis();
        Long fibonachchi = func.apply(num);
        long end1 = System.currentTimeMillis() - start1;
        System.out.println(fibonachchi);
        System.out.println(end1);

        long start2 = System.currentTimeMillis();
        Long fibonachchi2 = func.apply(num);
        long end2 = System.currentTimeMillis() - start2;
        System.out.println(fibonachchi2);
        System.out.println(end2);
    }

    private static Function<Integer, Long> getFibonachchiFunc() {
        HashMap<Integer, Long> hashMap = new HashMap<>();
        return num -> {
            if (hashMap.containsKey(num)) {
                return hashMap.get(num);
            }
            Long fibonachchi = fibonachchi(num - 1);
            hashMap.put(num, fibonachchi);
            return fibonachchi;
        };
    }

    public static long fibonachchi(int num) {
        if (num == 0)
            return 0;
        if (num == 1)
            return 1;
        else
            return fibonachchi(num - 1) + fibonachchi(num - 2);
    }

}
