package DataStructures.Queue;

public class MyQueue {

    private final int capacity;
    private int size = 0;
    private int head = 0;
    private int tail = 0;
    private final int[] queue;

    public MyQueue(int capacity) {
        this.queue = new int[capacity];
        this.capacity = capacity;
    }

    public MyQueue() {
        int DEFAULT_CAPACITY = 25;
        this.queue = new int[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
    }

    public void enqueue(int element) {
        if (size != capacity) {
            queue[tail] = element;
            tail = (tail + 1) % capacity;
            size++;
        }
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty.");
        }
        int element = queue[head];
        head = (head + 1) % capacity;
        size--;
        return element;
    }

    public String toString() {
        if (isEmpty()) {
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
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty.");
        }
        return queue[head];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
