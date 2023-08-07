package DataStructures.Queue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyQueueTesting {

    private static MyQueue buildMyQueue(int capacity) {
        return new MyQueue(capacity);
    }

    public static void main(String[] args) {
        MyQueue myQueue = buildMyQueue(5);
        myQueue.offer(2);
        myQueue.offer(5);
        myQueue.offer(-7);
        myQueue.offer(548);
        myQueue.offer(6);
        System.out.println(myQueue.peek());
        myQueue.offer(-1);
        myQueue.offer(-1);
        System.out.println(Arrays.toString(myQueue.queue));

    }
}
