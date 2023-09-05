package DataStructures.DynamicArray;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyDynamicArrayTest {

    @Test
    void check_size() {
        var list = new MyDynamicArray(3);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);

        var expectedElement = list.size();

        Assertions.assertEquals(expectedElement, 5);
    }

    @Test
    void should_set_element() {
        var list = new MyDynamicArray(3);
        list.add(2);
        list.add(2);
        list.set(0, 1);

        var expectedElement = list.get(0);

        Assertions.assertEquals(expectedElement, 1);
    }

    @Test
    void should_throw_exception_IndexOutOfBoundsException_when_we_set() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            var list = new MyDynamicArray(3);
            list.add(2);
            list.add(2);
            list.set(5, 1);
        });
    }

    @Test
    void should_get_element_from_list() {
        var list = new MyDynamicArray(3);
        list.add(2);

        var expectedElement = list.get(0);

        Assertions.assertEquals(expectedElement, 2);
    }

    @Test
    void should_throw_exception_IndexOutOfBoundsException_when_we_get() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            var list = new MyDynamicArray(3);
            list.get(0);
        });
    }

    @Test
    void should_remove_element_from_list() {
        var list = new MyDynamicArray(3);
        list.add(1);
        list.add(2);
        list.remove(0);

        var expectedElement = list.size();

        Assertions.assertEquals(expectedElement, 1);
    }

    @Test
    void should_throw_exception_IndexOutOfBoundsException_when_we_remove() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            var list = new MyDynamicArray(3);
            list.add(1);
            list.add(2);
            list.remove(5);
        });
    }

    @Test
    void should_add_list_to_list() {
        var list = new MyDynamicArray(3);
        list.add(2);

        var list2 = new MyDynamicArray(2);
        list2.add(2);
        list2.add(5);
        list.addAll(list2);

        var expectedElement = list.size();

        Assertions.assertEquals(expectedElement, 3);
    }
}
