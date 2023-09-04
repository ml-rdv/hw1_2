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
        Assertions.assertEquals(expectedElement, "(75, 1) (4, 1) (5, 1) ");

        var sizeAfterInsertion = hashTable.size();
        Assertions.assertEquals(sizeAfterInsertion, 3);
    }

    @Test
    void should_insert_forth_pairs() {
        var hashTable = new MyHashTable();
        hashTable.insert(5, 1);
        hashTable.insert(4, 1);
        hashTable.insert(75, 1);
        hashTable.insert(20, 2);

        var expectedElement = hashTable.toString();
        Assertions.assertEquals(expectedElement, "(75, 1) (4, 1) (5, 1) (20, 2) ");

        var sizeAfterInsertion = hashTable.size();
        Assertions.assertEquals(sizeAfterInsertion, 4);
    }

    @Test
    void should_change_value() {
        var hashTable = new MyHashTable();
        hashTable.insert(5, 1);
        hashTable.insert(4, 1);
        hashTable.insert(75, 3);
        hashTable.insert(75, 1);

        var expectedElement = hashTable.toString();
        Assertions.assertEquals(expectedElement, "(75, 1) (4, 1) (5, 1) ");

        var sizeAfterInsertion = hashTable.size();
        Assertions.assertEquals(sizeAfterInsertion, 3);
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
        Assertions.assertEquals(expectedElement, "(75, 1) (-75, 1) (4, 1) (5, 1) (-5, 1) ");

        var sizeAfterInsertion = hashTable.size();
        Assertions.assertEquals(sizeAfterInsertion, 5);
    }

    @Test
    void should_remove_pair() {
        var hashTable = new MyHashTable();
        hashTable.insert(5, 1);
        hashTable.insert(4, 1);
        hashTable.insert(75, 1);
        hashTable.insert(20, 1);
        hashTable.insert(35, 1);
        hashTable.remove(75);

        var expectedElement = hashTable.toString();
        Assertions.assertEquals(expectedElement, "(4, 1) (5, 1) (20, 1) (35, 1) ");

        var sizeAfterRemoving = hashTable.size();
        Assertions.assertEquals(sizeAfterRemoving, 4);
    }

    @Test
    void should_remove_pair_with_same_hashCodes_in_the_beginning() {
        var hashTable = new MyHashTable();
        hashTable.insert(5, 1);
        hashTable.insert(4, 1);
        hashTable.insert(75, 1);
        hashTable.insert(20, 1);
        hashTable.insert(35, 1);
        hashTable.remove(5);

        var expectedElement = hashTable.toString();
        Assertions.assertEquals(expectedElement, "(75, 1) (4, 1) (20, 1) (35, 1) ");

        var sizeAfterRemoving = hashTable.size();
        Assertions.assertEquals(sizeAfterRemoving, 4);
    }

    @Test
    void should_remove_pair_with_same_hashCodes_in_the_middle() {
        var hashTable = new MyHashTable();
        hashTable.insert(5, 1);
        hashTable.insert(4, 1);
        hashTable.insert(75, 1);
        hashTable.insert(20, 1);
        hashTable.insert(35, 1);
        hashTable.remove(20);

        var expectedElement = hashTable.toString();
        Assertions.assertEquals(expectedElement, "(75, 1) (4, 1) (5, 1) (35, 1) ");

        var sizeAfterRemoving = hashTable.size();
        Assertions.assertEquals(sizeAfterRemoving, 4);
    }

    @Test
    void should_remove_pair_with_same_hashCodes_in_the_end() {
        var hashTable = new MyHashTable();
        hashTable.insert(5, 1);
        hashTable.insert(4, 1);
        hashTable.insert(75, 1);
        hashTable.insert(20, 1);
        hashTable.insert(35, 1);
        hashTable.remove(35);

        var expectedElement = hashTable.toString();
        Assertions.assertEquals(expectedElement, "(75, 1) (4, 1) (5, 1) (20, 1) ");

        var sizeAfterRemoving = hashTable.size();
        Assertions.assertEquals(sizeAfterRemoving, 4);
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
    void should_throw_exception_NullPointerException_when_we_find() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            var hashTable = new MyHashTable();
            hashTable.find(5);
        });
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
