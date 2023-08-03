package Algorithms;// Отсортировать массив вставками.
// Сложность лучшего = n, худшего = n^2, среднего случая = n^2

public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 524, 3, 1, 9, -1, 2345, 2, 524, 3, 1, 9, -1, 2345};
        for (int i = 0; i < arr.length; i++) {
            int x = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] <= x) {
                    break;
                }
                int k = arr[j + 1];
                arr[j + 1] = arr[j];
                arr[j] = k;
            }
        }
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
