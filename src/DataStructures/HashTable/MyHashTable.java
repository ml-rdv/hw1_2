package DataStructures.HashTable;

public class MyHashTable {

    private Pair[] arr;
    private int size = 0;

    public MyHashTable() {
        int DEFAULT_CAPACITY = 15;
        arr = new Pair[DEFAULT_CAPACITY];
    }

    public void insert(int key, int value) {
        int position = hashCode(key);
        Pair pair = arr[position];
        Pair prevPair = null;
        while (pair != null && pair.getKey() != key) {
            prevPair = pair;
            pair = pair.getNext();
        }
        if (pair == null) {
            pair = new Pair(key, value);
            if (prevPair != null) {
                prevPair.setNext(pair);
            } else {
                arr[position] = pair;
            }
            size++;
            return;
        }
        pair.setValue(value);
    }

    private int hashCode(int key) {
        return Math.abs(key) % arr.length;
    }

    public void remove(int key) {
        int position = hashCode(key);
        Pair pair = arr[position];
        Pair prevPair = null;
        while (pair != null && pair.getKey() != key) {
            prevPair = pair;
            pair = pair.getNext();
        }
        if (pair == null) {
            throw new NullPointerException("Key " + key + " is absent.");
        }
        Pair newNextPair = pair.getNext();
        if (prevPair != null) {
            prevPair.setNext(newNextPair);
        } else {
            arr[position] = newNextPair;
        }
        size--;
    }

    public int find(int key) {
        int position = hashCode(key);
        Pair pair = arr[position];
        while (pair != null && pair.getKey() != key) {
            pair = pair.getNext();
        }
        if (pair == null) {
            throw new RuntimeException("Key " + key + " is absent.");
        }
        return pair.getValue();
    }

    public int size() {
        return size;
    }

    public void clear() {
        arr = new Pair[0];
        size = 0;
    }

    public String toString() {
        if (arr == null)
            return "null";
        if (size == 0)
            return "[]";

        StringBuilder b = new StringBuilder();
        for (Pair pair : arr) {
            while (pair != null) {
                int key = pair.getKey();
                int value = pair.getValue();
                b.append('(').append(key).append(", ").append(value).append(") ");
                pair = pair.getNext();
            }
        }
        return b.toString();
    }
}
