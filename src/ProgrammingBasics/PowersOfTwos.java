package ProgrammingBasics;// Создать двумерных массив содержий степени двоек и вывести. Размер грани вводим из консоли.

public class PowersOfTwos {
    public static void main(String[] args) {
        int arr[][] = new int[4][4];
        int count, newCount = 2;
        for (int i = 0; i < arr.length; i++) {
            count = newCount;
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = count;
                count = count << 1;
                if (j == 1)
                    newCount = arr[i][j];
            }
        }
        for (int[] row : arr) {
            for (int element : row)
                System.out.print(element + " ");
            System.out.println();
        }
    }
}
