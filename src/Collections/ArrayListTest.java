package Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Для ArrayList написать тесты на методы (обратить внимание на перегрузки
 * и различные вариации по типу add/addAll или remove/removeIf)
 * Добавления элементов
 * Замены элементов
 * Получение элементов
 * Удаление элементов
 * Определение признака, что элемент содержится в списке
 * Получение размера и признака, что список пуст
 */
public class ArrayListTest {
    ArrayList<Integer> list = new ArrayList<>();

    @BeforeEach
    public void setup() {
        list.add(2);
        list.add(5);
        list.add(-1);
        list.add(7);
    }

    @Test
    void should_add_element_to_ArrayList() {
        list.add(-7);
        var expectedElement = list.get(4);
        Assertions.assertEquals(expectedElement, -7);
    }

    @Test
    void should_add_all_elements_to_ArrayList() {
        List<Integer> list2 = List.of(-1, -2, -3);
        list.addAll(2, list2);
        var expectedElement = list.size();
        Assertions.assertEquals(expectedElement, 7);
    }

    @Test
    void should_change_element_in_ArrayList() {
        list.set(0, 100);
        var expectedElement = list.get(0);
        Assertions.assertEquals(expectedElement, 100);
    }

    @Test
    void should_not_change_element_in_ArrayList() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.set(10, 100));
    }

    @Test
    void should_get_element_from_ArrayList() {
        var expectedElement = list.get(0);
        Assertions.assertEquals(expectedElement, 2);
    }

    @Test
    void should_not_get_element_from_ArrayList() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.get(10));
    }

    @Test
    void should_remove_element_from_ArrayList() {
        list.remove((Integer) 7);
        var expectedElement = list.size();
        Assertions.assertEquals(expectedElement, 3);
    }

    @Test
    void should_not_remove_element_from_ArrayList() {
        list.remove((Integer) 10000);
        var expectedElement = list.size();
        Assertions.assertEquals(expectedElement, 4);
    }

    @Test
    void should_remove_element_from_ArrayList_by_condition() {
        list.removeIf(n -> (n > 0));
        var expectedElement = list.size();
        Assertions.assertEquals(expectedElement, 1);
    }

    @Test
    void should_find_element_in_ArrayList() {
        var expectedElement = list.contains(7);
        Assertions.assertTrue(expectedElement);
    }

    @Test
    void should_not_find_element_in_ArrayList() {
        var expectedElement = list.contains(10000);
        Assertions.assertFalse(expectedElement);
    }

    @Test
    void should_get_size_of_empty_ArrayList() {
        ArrayList<Integer> list2 = new ArrayList<>();
        var expectedElement = list2.size();
        Assertions.assertEquals(expectedElement, 0);

        var expectedElement2 = list2.isEmpty();
        Assertions.assertEquals(expectedElement2, true);

    }
}
