package DataStructures.HashTable;

public class Pair {
    private final int key;
    private int value;
    private Pair next;

    public Pair(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public Pair getNext() {
        return next;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setNext(Pair next) {
        this.next = next;
    }
}
