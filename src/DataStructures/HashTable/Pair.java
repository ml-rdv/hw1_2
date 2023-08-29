package DataStructures.HashTable;

public class Pair {
    private final int key;
    private final int hashCode;
    private final int value;

    public Pair(int key, int value) {
        this.key = key;
        this.value = value;
        this.hashCode = key;
    }

    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public int getHashCode() {
        return hashCode;
    }

}
