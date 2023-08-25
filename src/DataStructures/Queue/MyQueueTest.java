package DataStructures.Queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyQueueTest {

    @Test
    void should_enqueue_element_to_queue() {
        var myQueue = new MyQueue(5);
        myQueue.enqueue(2);
        myQueue.enqueue(5);
        myQueue.enqueue(-7);

        var expectedElement = myQueue.size();

        Assertions.assertEquals(expectedElement, 3);
    }

    @Test
    void should_dequeue_element_from_queue() {
        var myQueue = new MyQueue(5);
        myQueue.enqueue(2);
        myQueue.enqueue(5);
        myQueue.enqueue(-7);

        var expectedElement = myQueue.dequeue();
        Assertions.assertEquals(expectedElement, 2);

        var expectedElement2 = myQueue.size();
        Assertions.assertEquals(expectedElement2, 2);
    }

    @Test
    void should_throw_IndexOutOfBoundsException_when_dequeue() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            var myQueue = new MyQueue(5);
            myQueue.dequeue();
        });
    }

    @Test
    void should_peek_element_from_queue() {
        var myQueue = new MyQueue(5);
        myQueue.enqueue(2);
        myQueue.enqueue(5);
        myQueue.enqueue(-7);

        var expectedElement = myQueue.peek();
        Assertions.assertEquals(expectedElement, 2);

        var expectedElement2 = myQueue.size();
        Assertions.assertEquals(expectedElement2, 3);
    }

    @Test
    void should_throw_IndexOutOfBoundsException_when_peek() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            var myQueue = new MyQueue(5);
            myQueue.peek();
        });
    }
}
