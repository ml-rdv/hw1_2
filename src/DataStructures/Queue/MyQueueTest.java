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
    }

    @Test
    void should_throw_IndexOutOfBoundsException_when_peek() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            var myQueue = new MyQueue(5);
            myQueue.peek();
        });
    }

    @Test
    void check_size_after_pop_in_stack() {
        var myQueue = new MyQueue(5);
        myQueue.enqueue(1);
        myQueue.enqueue(2);
        myQueue.enqueue(3);
        myQueue.enqueue(4);
        myQueue.dequeue();

        var expectedElement = myQueue.size();

        Assertions.assertEquals(expectedElement, 3);
    }

    @Test
    void check_size_after_peek_in_stack() {
        var myQueue = new MyQueue(5);
        myQueue.enqueue(1);
        myQueue.enqueue(2);
        myQueue.enqueue(3);
        myQueue.enqueue(4);
        myQueue.peek();

        var expectedElement = myQueue.size();

        Assertions.assertEquals(expectedElement, 4);
    }

}
