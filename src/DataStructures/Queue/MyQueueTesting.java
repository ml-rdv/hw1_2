package DataStructures.Queue;

public class MyQueueTesting {

    private static MyQueue buildMyQueue(int capacity) {
        return new MyQueue(capacity);
    }

    public static void main(String[] args) {
        MyQueue myQueue = buildMyQueue(5);
        myQueue.enqueue(2);
        myQueue.enqueue(5);
        myQueue.enqueue(-7);
        myQueue.enqueue(5);
        myQueue.enqueue(-7);

        System.out.println(myQueue.dequeue());
        myQueue.enqueue(457);
        System.out.println(myQueue.dequeue());
        System.out.println(myQueue);

        myQueue.enqueue(-1);
        myQueue.enqueue(-1);
        System.out.println(myQueue.peek());
        System.out.println(myQueue);
    }
}
