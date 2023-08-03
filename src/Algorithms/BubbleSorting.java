package Algorithms;

// Отсортировать массив пузырьковым алгоритмом.
// Сложность лучшего, худшего и среднего случая = n^2
public class BubbleSorting {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 524, 3, 1, 9, -1, 2345};
        for (int j = 0; j < arr.length; j++) {
            for (int i = 0; i < arr.length - (j + 1); i++) {
                if (arr[i] > arr[i + 1]) {
                    int k = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = k;
                }
            }
        }
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
