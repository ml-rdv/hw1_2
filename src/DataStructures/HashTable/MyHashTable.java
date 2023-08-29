package DataStructures.HashTable;

import java.util.Iterator;
import java.util.LinkedList;

public class MyHashTable {

    private Pair[] arr;
    private int size = 0;
    private final boolean[] checkPositiveKeys = new boolean[1000005];
    private final boolean[] checkNegativeKeys = new boolean[1000005];
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
        if (keyExists(key)) {
            Pair newElement = new Pair(key, value);
            int position = newElement.getHashCode();
            if (position < 0) {
                position = arr.length + position;
            }
            arr[position] = newElement;
            return;
        }
        Pair newElement = new Pair(key, value);
        int position = newElement.getHashCode();
        if (position < 0) {
            position = arr.length + position;
        }
        arr[position] = newElement;
        if (key >= 0) {
            checkPositiveKeys[key] = true;
        } else {
            int positionOfKey = key * -1;
            checkNegativeKeys[positionOfKey] = true;
        }
        hashCodes.add(position);
        size++;
    }

    private boolean keyExists(int key) {
        int positionOfNegativeKey = key * -1;
        if ((key >= 0 && checkPositiveKeys[key]) ||
                (key < 0 && checkNegativeKeys[positionOfNegativeKey])) {
            return true;
        }
        return false;
    }

    private int hashCode(int key) {
        if (key >= 0) {
            return key;
        }
        return arr.length + key;
    }

    public void remove(int key) {
        if (!keyExists(key)) {
            throw new NullPointerException("Key " + key + " is absent.");
        }
        if (key >= 0) {
            checkPositiveKeys[key] = false;
        } else {
            int positionOfKey = key * -1;
            checkNegativeKeys[positionOfKey] = false;
        }
        int position = hashCode(key);
        hashCodes.remove((Object) position);
        size--;
    }

    public int find(int key) {
        if (!keyExists(key)) {
            System.out.println("Key " + key + " is absent");
            return -1;
        }
        int position = hashCode(key);
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
