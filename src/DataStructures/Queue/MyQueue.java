package DataStructures.Queue;

public class MyQueue {
    public int size = 0;
    public int[] queue;

    public MyQueue(int size) {
        this.queue = new int[size];
    }

    public void offer(int element) {
        extendArray();
        queue[size] = element;
        size++;
    }

    public void remove() {
        if (size > 0) {
            int[] queueCopy = new int[queue.length];
            for (int i = 0; i < size - 1; i++) {
                queueCopy[i] = queue[i + 1];
            }
            queue = queueCopy;
            size--;
        }
    }

    public int peek() {
        if (size > 0) {
            int element = queue[0];
            remove();
            return element;
        }
        return 0; // null
    }

    private void extendArray() {
        if (queue.length <= size) {
            int[] queueCopy = new int[size * 2 + 1];
            for (int i = 0; i < queue.length; i++) {
                queueCopy[i] = queue[i];
            }
            queue = queueCopy;
        }
    }
}
