//Реализовать метод, который принимает отсортированный массив чисел и число. Нужно найти число в массиве и вывести кол-во шагов за которое оно было найдено.
// Решить задачу как получится
// Сложность = n
public class hw1_5 {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 8, 10, 11};
        int numb = 80;
        int index = 0;
        while (index < arr.length && numb != arr[index]) {
            index++;
        }
        if (index == arr.length) {
            System.out.println("Number is not found.");
        } else {
            System.out.println("Position = " + index);
        }
    }
}
