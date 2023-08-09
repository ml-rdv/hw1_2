package ProgrammingBasics;// Дан массив чисел, найти наибольшее и вывести.

import java.util.Arrays;

public class MaxNumberInArray {
    public static void main(String[] args) {
        int arr[] = {5, 8, 6, 78, -1, 7};
        // 1 способ
        Arrays.sort(arr);
        System.out.println(arr[arr.length - 1]);
        // 2 способ
        int maxValue = Integer.MIN_VALUE;
        for(int i : arr){
            if(maxValue < i)
                maxValue = i;
        }
        System.out.println(maxValue);
    }
}
