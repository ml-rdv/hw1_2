package ProgrammingBasics;// Дан массив чисел, найти его сумму, ср. арифметическое, медиану и вывести всё.

import java.util.stream.IntStream;

public class SumArithmeticMeanMedian {
    public static void main(String[] args) {
        int arr[] = {1, 3, 5, 7};

        // Сумма

        // 1 способ
        // Знаю что можно с помощью Stream (и др. операции), но на этом всё)))
        System.out.printf("Sum = %d\n", IntStream.of(arr).sum());

        // 2 способ
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        System.out.printf("Sum = %d\n", sum);

        // Среднее арифметическое
        System.out.printf("Average = %d\n", sum / arr.length);

        // Медиана
        System.out.printf("Median = %d\n", (arr.length % 2 == 0 ?
                (arr[arr.length / 2] + arr[arr.length / 2 - 1]) / 2 :
                arr[arr.length / 2]));

    }
}
