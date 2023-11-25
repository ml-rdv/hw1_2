package PracticeJavaMemoryModelLinksObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Ошибка OutOfMemoryError происходит, т.к. память кучи heap заполнена из-за бесконечного создания объектов.
 * - Как не допустить появление OutOfMemoryError в данном примере?
 * Добавить условие выхода из цикла с помощью оператора break или добавить list.clear() в конец цикла
 */

public class Task1 {
    private static List<Object> list = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            list.add(new Object());
            list.add(new Object());
            list.add(new Object());
            list.add(new Object());
            list.add(new Object());
        }
    }
}
