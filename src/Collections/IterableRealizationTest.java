package Collections;

import DataStructures.DynamicArray.MyDynamicArray;

import java.util.Iterator;

public class IterableRealizationTest {
    public static void main(String[] args) {
        MyDynamicArray array = new MyDynamicArray();
        array.add(5);
        array.add(1);
        array.add(4);
        array.add(6);
        array.add(7);
        Iterator<Integer> iterator = array.iterator();
        while (iterator.hasNext()) {
            int num = iterator.next();
            if (num == 1) {
                iterator.remove();
            }
        }
        System.out.println(array);
        // Цикл foreach доступен, т.к. класс MyDynamicArray реализует интерфейс Iterable
        for (int a : array) {
            System.out.print(a + " ");
        }
        System.out.println();
        // Аналогично тому, что выше
        array.forEach(System.out::print);
    }

}
