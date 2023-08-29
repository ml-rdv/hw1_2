package DataStructures.HashTable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyHashTableTest {

    @Test
    void should_insert_three_pairs() {
        var hashTable = new MyHashTable();
        hashTable.insert(5, 1);
        hashTable.insert(4, 1);
        hashTable.insert(75, 1);

        var expectedElement = hashTable.toString();

        Assertions.assertEquals(expectedElement, "[(5, 1), (4, 1), (75, 1)]");
    }

    @Test
    void should_not_insert_pair() {
        var hashTable = new MyHashTable();
        hashTable.insert(10000005, 1);

        var expectedElement = hashTable.toString();

        Assertions.assertEquals(expectedElement, "[]");
    }

    @Test
    void should_change_value() {
        var hashTable = new MyHashTable();
        hashTable.insert(5, 1);
        hashTable.insert(4, 1);
        hashTable.insert(75, 3);
        hashTable.insert(75, 1);

        var expectedElement = hashTable.toString();

        Assertions.assertEquals(expectedElement, "[(5, 1), (4, 1), (75, 1)]");
    }

    @Test
    void check_negative_key() {
        var hashTable = new MyHashTable();
        hashTable.insert(5, 1);
        hashTable.insert(4, 1);
        hashTable.insert(75, 1);
        hashTable.insert(-75, 1);
        hashTable.insert(-75, 1);
        hashTable.insert(-5, 1);

        var expectedElement = hashTable.toString();

        Assertions.assertEquals(expectedElement, "[(5, 1), (4, 1), (75, 1), (-75, 1), (-5, 1)]");
    }

    @Test
    void check_size() {
        var hashTable = new MyHashTable();
        hashTable.insert(5, 1);
        hashTable.insert(4, 1);
        hashTable.insert(75, 1);

        var expectedElement = hashTable.size();

        Assertions.assertEquals(expectedElement, 3);
    }

    @Test
    void check_size_second_variant() {
        var hashTable = new MyHashTable();
        hashTable.insert(5, 1);
        hashTable.insert(4, 1);
        hashTable.insert(75, 1);
        hashTable.insert(75, 1);
        hashTable.insert(75, 1);

        var expectedElement = hashTable.size();

        Assertions.assertEquals(expectedElement, 3);
    }

    @Test
    void should_remove_pair() {
        var hashTable = new MyHashTable();
        hashTable.insert(5, 1);
        hashTable.insert(4, 1);
        hashTable.insert(75, 1);
        hashTable.remove(75);

        var expectedElement = hashTable.toString();

        Assertions.assertEquals(expectedElement, "[(5, 1), (4, 1)]");
    }

    @Test
    void should_remove_all_pairs() {
        var hashTable = new MyHashTable();
        hashTable.insert(5, 1);
        hashTable.insert(4, 1);
        hashTable.insert(75, 1);
        hashTable.remove(75);
        hashTable.remove(5);
        hashTable.remove(4);

        var expectedElement = hashTable.toString();

        Assertions.assertEquals(expectedElement, "[]");
    }

    @Test
    void should_throw_exception_NullPointerException_when_we_remove() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            var hashTable = new MyHashTable();
            hashTable.remove(5);
        });
    }

    @Test
    void should_find_value() {
        var hashTable = new MyHashTable();
        hashTable.insert(5, 1);
        hashTable.insert(4, 1);
        hashTable.insert(75, 1);

        var expectedElement = hashTable.find(75);

        Assertions.assertEquals(expectedElement, 1);
    }

    @Test
    void should_not_find_value() {
        var hashTable = new MyHashTable();

        var expectedElement = hashTable.find(75);

        Assertions.assertEquals(expectedElement, -1);
    }

    @Test
    void should_clear_hashTable() {
        var hashTable = new MyHashTable();
        hashTable.insert(5, 1);
        hashTable.insert(4, 1);
        hashTable.insert(75, 1);
        hashTable.clear();

        var expectedElement = hashTable.toString();

        Assertions.assertEquals(expectedElement, "[]");
    }
}
