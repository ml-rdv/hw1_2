package DataStructures.Stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyStackTesting {

    @Test
    void should_pop_element_from_stack() {
        var myStack = new MyStack(5);
        myStack.push(2);
        myStack.push(5);

        var expectedElement = myStack.pop();

        Assertions.assertEquals(expectedElement, 5);
    }

    @Test
    void should_throw_IndexOutOfBoundsException_when_pop() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            var myStack = new MyStack(5);
            myStack.pop();
        });
    }

    @Test
    void should_peek_element_from_stack() {
        var myStack = new MyStack(5);
        myStack.push(2);
        myStack.push(5);

        var expectedElement = myStack.peek();

        Assertions.assertEquals(expectedElement, 5);
    }

    @Test
    void should_throw_IndexOutOfBoundsException_when_peek() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            var myStack = new MyStack(5);
            myStack.peek();
        });
    }
}