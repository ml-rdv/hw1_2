package Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * Для HashMap написать тесты на методы
 * Добавления элементов
 * Замены элементов
 * Получение элементов
 * Удаление элементов
 * Определение признака, что элемент содержится в списке
 * Получение размера и признака, что список  пуст
 */
public class HashMapTest {

    HashMap<Integer, String> hashMap = new HashMap<>();

    @BeforeEach
    public void setup() {
        hashMap.put(1, "One");
        hashMap.put(2, "Two");
        hashMap.put(3, "Three");
        hashMap.put(4, "Four");
    }

    @Test
    void should_put_element_to_HashMap() {
        hashMap.put(5, "Five");
        var expectedElement = hashMap.get(5);
        Assertions.assertEquals(expectedElement, "Five");
    }

    @Test
    void should_change_element_in_HashMap() {
        hashMap.put(1, "OneOne");
        var expectedElement = hashMap.get(1);
        Assertions.assertEquals(expectedElement, "OneOne");
    }

    @Test
    void should_get_element_from_HashMap() {
        var expectedElement = hashMap.get(1);
        Assertions.assertEquals(expectedElement, "One");
    }

    @Test
    void should_return_null_from_HashMap() {
        var expectedElement = hashMap.get(10);
        Assertions.assertNull(expectedElement);
    }

    @Test
    void should_remove_element_from_HashMap() {
        hashMap.remove(3);
        var expectedElement = hashMap.size();
        Assertions.assertEquals(expectedElement, 3);
    }

    @Test
    void should_find_key_in_HashMap() {
        var expectedElement = hashMap.containsKey(1);
        Assertions.assertTrue(expectedElement);
    }

    @Test
    void should_find_value_in_HashMap() {
        var expectedElement = hashMap.containsValue("One");
        Assertions.assertTrue(expectedElement);
    }

    @Test
    void should_get_size_of_empty_HashMap() {
        HashMap<Integer, String> hashMap2 = new HashMap<>();
        var expectedElement = hashMap2.size();
        Assertions.assertEquals(expectedElement, 0);

        var expectedElement2 = hashMap2.isEmpty();
        Assertions.assertEquals(expectedElement2, true);
    }
}
