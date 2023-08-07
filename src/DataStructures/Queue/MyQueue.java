package DataStructures.Queue;

public class MyQueue {
    public static int position = 0;
    public static int[] queue;

    public MyQueue(int[] queue) {
        MyQueue.queue = queue;
        position = queue.length;
    }

    public static boolean offer(int element) {
        if (position < queue.length) {
            queue[position] = element;
            position++;
            return true;
        }
        return false;
    }


    public static void remove() {
        if (queue.length > 0) {
            position--;
        }
        decreaseCapacity();
    }


    public static int peek() {
        if (queue.length > 0) {
            int element = queue[0];
            remove();
            return element;
        }
        return 0;
    }

    private static void decreaseCapacity() {
        int[] queueCopy = new int[queue.length];
        System.arraycopy(queue, 1, queueCopy, 0, queue.length - 1);
        queue = queueCopy;
    }

}
