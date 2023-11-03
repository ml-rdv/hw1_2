/*
Ошибка OutOfMemoryError происходит, т.к. память кучи heap заполнена из-за бесконечного создания объектов.
*/

package PracticeJavaMemoryModelLinksObject;

import java.util.ArrayList;
import java.util.List;

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
