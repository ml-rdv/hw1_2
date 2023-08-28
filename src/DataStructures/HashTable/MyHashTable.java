package DataStructures.HashTable;

public class MyHashTable {

    private Pair[] arr;
    private int size = 0;
    private final boolean[] checkPositiveKeys = new boolean[10005];
    private final boolean[] checkNegativeKeys = new boolean[10005];

    public MyHashTable() {
        int DEFAULT_CAPACITY = 25;
        arr = new Pair[DEFAULT_CAPACITY];
    }

    public void insert(int key, int value) {
        if (keyExists(key)) {
            int position = findPosition(key);
            Pair newElement = new Pair(key, value);
            arr[position] = newElement;
            return;
        }
        extendArray();
        Pair newElement = new Pair(key, value);
        arr[size] = newElement;
        if (key >= 0) {
            checkPositiveKeys[key] = true;
        } else {
            int positionOfKey = key * -1;
            checkNegativeKeys[positionOfKey] = true;
        }
        size++;
    }

    private boolean keyExists(int key) {
        int positionOfKey = key * -1;
        if ((key >= 0 && checkPositiveKeys[key]) ||
                (key < 0 && checkNegativeKeys[positionOfKey])) {
            return true;
        }
        return false;
    }

    private void extendArray() {
        if (arr.length <= size) {
            Pair[] arrCopy = new Pair[size * 2 + 1];
            for (int i = 0; i < size; i++) {
                arrCopy[i] = arr[i];
            }
            arr = arrCopy;
        }
    }

    public void remove(int key) {
        int position = findPosition(key);
        if (position == -1) {
            throw new NullPointerException("Key " + key + " is absent.");
        }
        if (key >= 0) {
            checkPositiveKeys[key] = false;
        } else {
            int positionOfKey = key * -1;
            checkNegativeKeys[positionOfKey] = false;
        }
        for (int i = position; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
    }

    private int findPosition(int key) {
        Pair element;
        for (int i = 0; i < size; i++) {
            element = arr[i];
            int currentKey = element.getKey();
            if (currentKey == key) {
                return i;
            }
        }
        return -1;
    }

    public int find(int key) {
        int position = findPosition(key);
        if (position == -1) {
            System.out.println("Key " + key + " is absent");
            return -1;
        }
        Pair foundElement = arr[position];
        return foundElement.getValue();
    }

    public int size(){
        return  size;
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
        for (int i = 0; ; i++) {
            Pair element = arr[i];
            int key = element.getKey();
            int value = element.getValue();
            b.append('(').append(key).append(", ").append(value).append(')');
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }
}
