package Algorithms;

// Отсортировать массив от меньшего к большему придумав свой алгоритм сортировки.
public class MySortingAlgorithm {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 524, 3, 1, 9, -1, 2345};
        mySortingFirstMethod(arr);
        mySortingSecondMethod(arr);
    }

    // Сложность лучшего, худшего и среднего случая = n^2
    public static void mySortingFirstMethod(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] > arr[i]) {
                    int k = arr[i];
                    arr[i] = arr[j];
                    arr[j] = k;
                }
            }
        }
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    // Сложность лучшего = n, худшего = n^2, среднего случая = n^2
    public static void mySortingSecondMethod(int[] arr) {
        boolean correct = false;
        while (!correct) {
            correct = true;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int k = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = k;
                    correct = false;
                }
            }
        }
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}