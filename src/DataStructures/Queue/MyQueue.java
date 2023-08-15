package DataStructures.Queue;

public class MyQueue {

    private final int n;
    private int size = 0;
    private int head = 0;
    private int tail = 0;
    public int[] queue;

    public MyQueue(int capacity) {
        this.queue = new int[capacity];
        n = capacity;
    }

    public void enqueue(int element) {
        if (size != n) {
            queue[tail] = element;
            tail = (tail + 1) % n;
            size++;
        }
    }

    public int dequeue() {
        if (empty()) {
            return 0;
        }
        int element = queue[head];
        head = (head + 1) % n;
        size--;
        return element;
    }

    public String toString() {
        if (empty()) {
            return "[]";
        }

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = head, j = 0; j < size; i++, j++) {
            if (i == queue.length && tail <= head) {
                i = 0;
            }
            b.append(queue[i]);
            if (i == tail - 1) {
                return b.append(']').toString();
            }
            b.append(", ");
        }
        return "";
    }

    public int peek() {
        return !empty() ? queue[head] : 0;
    }

    public int size() {
        return size;
    }

    public boolean empty() {
        return size == 0;
    }

}
