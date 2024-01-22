package Collections;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetWithReverseSort {

    public static void main(String[] args) {
        // Вариант 2 без создания отдельного класса, с использованием лямбда-выражения
        // Comparator<Integer> comp = (Integer o1, Integer o2) -> (o2 - o1);

        final TreeSetComparator comparator = new TreeSetComparator();
        Set<Integer> ts = new TreeSet<>(comparator);
        ts.add(50);
        ts.add(1);
        ts.add(3);
        ts.add(421);
        ts.add(9);
        System.out.println(ts);
    }
}
