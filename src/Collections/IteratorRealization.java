package Collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class IteratorRealization {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(2);
        list1.add(5);
        list1.add(-1);
        list1.add(7);
        list1.add(2);
        list1.add(5);
        list1.add(-1);
        list1.add(7);

        List<Integer> list2 = new ArrayList<>(list1);
        List<Integer> list3 = new ArrayList<>(list1);

        // Iterator
        Iterator<Integer> iterator = list1.iterator();
        while (iterator.hasNext()) {
            int num = iterator.next();
            if (num == 7) {
                iterator.remove();
            }
        }
        // Аналогично тому, что выше
        list1.removeIf(num -> num == 7);

        // Выкидывает ошибку ConcurrentModificationException
//        int index = 0;
//        for (int i : list2) {
//            if (i == 7) {
//                list2.set(index, 8); // проблем нет
//                list2.remove((Object) i); // ConcurrentModificationException
//                list2.add(8); // ConcurrentModificationException
//            }
//            index++;
//        }

        // Проблем нет в цикле for, т.к. значение list.size() пересчитывается каждый проход цикла
        for (int i = 0; i < list3.size(); i++) {
            if (list3.get(i) == 7) {
                list3.remove((Object) 7); // success
                list3.add(i, 8); // success
                list3.add(i, 9); // success
            }
        }
        System.out.println(list1);
        System.out.println(list2); // без изменений
        System.out.println(list3);
    }
}
