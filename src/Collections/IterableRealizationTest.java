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
        array.add(1);
        Iterator<Integer> iterator = array.iterator();
        while (iterator.hasNext()) {
            int num = iterator.next();
            System.out.print(num + " ");
        }
        // Цикл foreach доступен, т.к. класс MyDynamicArray реализует интерфейс Iterable
        System.out.println();
        for (int a : array) {
            System.out.print(a + " ");
        }
        // Аналогично тому, что выше
        array.forEach(System.out::println);
    }

}
