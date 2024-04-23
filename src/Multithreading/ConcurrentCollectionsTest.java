package Multithreading;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

/**
 * Запустить тесты. Объяснить почему падают тесты и починить используя потокобезопасные коллекции.
 */

public class ConcurrentCollectionsTest {

    @Execution(ExecutionMode.CONCURRENT)
    @RepeatedTest(100)
    void should_parallel_add_elements_to_list() throws InterruptedException {
//        var numbers = new ArrayList<>();
//        Проблема из-за состояния гонки. 2 потока одновременно добавляют элементы в 1 ячейку памяти.
//        Нужно использовать потокобезопасный класс CopyOnWriteArrayList, который использует синхронизацию
//        "под капотом" и не вызывает исключений при изменении структуры коллекции, потому что работает
//        с её клоном.
        var numbers = new CopyOnWriteArrayList<>();

        Thread thread = new Thread(() -> {
            IntStream.range(0, 1000).forEach(numbers::add);
        });

        Thread thread2 = new Thread(() -> {
            IntStream.range(0, 1000).forEach(numbers::add);
        });

        thread.start();
        thread2.start();

        thread.join();
        thread2.join();

        int expectedValue = 2000;
        Assertions.assertEquals(expectedValue, numbers.size());
    }

    @Test
    void should_remove_all_entry_from_map() {
//        var map = new HashMap<String, Integer>();
//        Мы не можем изменять структуру коллекции в цикле foreach, т.к. классический
//        класс HashMap основан на fail fast подходе.
//        Для этого нужно использовать итератор или класс ConcurrentHashMap, который основан на fail safe
//        подходе, то есть не вызывает исключений при изменении структуры коллекции,
//        потому что работает с её клоном
        var map = new ConcurrentHashMap<String, Integer>();
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);

        map.forEach((k, v) -> map.remove(k));

        int expectedSize = 0;
        Assertions.assertEquals(expectedSize, map.size());
    }
}
