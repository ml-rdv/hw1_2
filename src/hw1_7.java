import java.util.ArrayList;
import java.util.Collections;

// Реализовать динамический массив(список)

public class hw1_7 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(1);
        list.add(-1);
        list.add(-1);
        list.add(100);
        list.add(105);
        list.add(-1);
        list.add(100);
        list.add(107);
        list.add(25);
        list.add(15);

        // Найти второй минимальный элемент массива
        Collections.sort(list);
        ArrayList<Integer> list3 = new ArrayList<Integer>();
        for (int element : list) {
            if (!list3.contains(element)) {
                list3.add(element);
            }
        }
        System.out.println(list3.get(1));

        // Первые неповторяющиеся целые числа в массиве
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        int previous = list.get(0), count = 1;
        // Сложность = n
        for (int i = 1; i < list.size(); i++) {
            int element = list.get(i);
            if (previous == element) {
                count++;
            } else if (count == 0) {
                list2.add(previous);
            } else {
                count = 0;
            }
            if (i == list.size() - 1) {
                if (element != list.get(i - 1)) {
                    list2.add(list.get(i));
                }
            }
            previous = list.get(i);
        }
        System.out.println(list2);

        // Объединить два отсортированных массива
        list.addAll(list2);
        System.out.println(list);

        // Изменение порядка положительных и отрицательных значений в массиве
        // Реверс массива ???
        ArrayList<Integer> list4 = new ArrayList<Integer>();
        // Сложность = n
        for (int i = list.size() - 1; i >= 0; i--) {
            list4.add(list.get(i));
        }
        System.out.println(list4);
    }
}
