package Algorithms;

// Реализовать метод, который принимает отсортированный массив чисел и число. Нужно найти число в массиве и вывести кол-во шагов за которое оно было найдено.
// Решить задачу через алгоритм бинарного поиска.
// Сложность лучшего = 1, худшего и среднего случая = n * log(n)
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 8, 10, 11, 12};
        int numb = 2;
        int left = 0;
        int right = arr.length - 1;
        int position = (left + right) / 2;
        while (arr[position] != numb) {
            if (left == right) {
                System.out.println("Number is not found.");
                return;
            }
            if (numb > arr[position]) {
                left = position + 1;
            } else if (numb < arr[position]) {
                right = position - 1;
            }
            position = (left + right) / 2;
        }
        System.out.println("Position = " + position);
    }
}