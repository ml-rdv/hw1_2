package ProgrammingBasics;// Дан массив чисел, найти наименьшее и вывести.

import java.util.Arrays;

public class MinNumberInArray {
    public static void main(String[] args) {
        int arr[] = {5, 8, 6, 78, -1, 7};
        method1(arr);
        method2(arr);
    }

    private static void method2(int[] arr) {
        int minValue = Integer.MAX_VALUE;
        for (int i : arr) {
            if (minValue > i)
                minValue = i;
        }
        System.out.println(minValue);
    }

    private static void method1(int[] arr) {
        Arrays.sort(arr);
        System.out.println(arr[0]);
    }
}
