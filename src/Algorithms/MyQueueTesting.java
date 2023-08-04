package Algorithms;

import java.util.Arrays;

public class MyQueueTesting {

    private static MyQueue buildMyQueue(int[] queue) {
        return new MyQueue(queue);
    }

    public static void main(String[] args) {
        buildMyQueue(new int[]{2, 5, -7, 548, 6});
        System.out.println(MyQueue.peek());
        MyQueue.offer(-1);
        System.out.println(Arrays.toString(MyQueue.queue));
        MyQueue.offer(-1);
        MyQueue.offer(-1);
        MyQueue.offer(-1);

    }
}
