package DataStructures.HashTable;

import java.util.Iterator;
import java.util.LinkedList;

public class MyHashTable {

    private Pair[] arr;
    private int size = 0;
    private final boolean[] checkHashCodes = new boolean[1000005];
    LinkedList<Integer> hashCodes = new LinkedList<>();

    public MyHashTable() {
        int DEFAULT_CAPACITY = 1000005;
        arr = new Pair[DEFAULT_CAPACITY];
    }

    public void insert(int key, int value) {
        if (key >= arr.length) {
            System.out.println("Key is too big.");
            return;
        }
        Pair newElement = new Pair(key, value);
        int position = newElement.getHashCode();
        if (position < 0) {
            position = arr.length + position;
        }
        arr[position] = newElement;
        if (hashCodeExists(position)) {
            return;
        }
        checkHashCodes[position] = true;
        hashCodes.add(position);
        size++;
    }

    private boolean hashCodeExists(int hashCode) {
        if (hashCode >= 0) {
            return checkHashCodes[hashCode];
        }
        int positionOfNegativeHashCode = arr.length + hashCode;
        return checkHashCodes[positionOfNegativeHashCode];
    }

    private int hashCode(int key) {
        return key;
    }

    public void remove(int key) {
        if (!hashCodeExists(key)) {
            throw new NullPointerException("Key " + key + " is absent.");
        }
        int hashCode = hashCode(key);
        hashCodes.remove((Object) hashCode);
        int position = hashCode;
        if (position < 0) {
            position = arr.length + hashCode;
        }
        checkHashCodes[position] = false;
        size--;
    }

    public int find(int key) {
        if (!hashCodeExists(key)) {
            System.out.println("Key " + key + " is absent");
            return -1;
        }
        int position = hashCode(key);
        if (position < 0) {
            position = arr.length + key;
        }
        return arr[position].getValue();
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
        int iMax = size - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        Iterator<Integer> itr = hashCodes.iterator();
        while (itr.hasNext()) {
            Pair element = arr[itr.next()];
            int key = element.getKey();
            int value = element.getValue();
            b.append('(').append(key).append(", ").append(value).append(')');
            if (!itr.hasNext())
                return b.append(']').toString();
            b.append(", ");
        }
        return b.toString();
    }
}
