package DataStructures.Stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyStackTest {

    @Test
    void should_push_element_to_stack() {
        var myStack = new MyStack(5);
        myStack.push(2);
        myStack.push(2);
        myStack.push(2);
        myStack.push(2);
        myStack.push(5);

        var expectedElement = myStack.size();

        Assertions.assertEquals(expectedElement, 5);
    }

    @Test
    void should_show_size_after_push_and_pop_in_stack() {
        var myStack = new MyStack(5);
        myStack.push(2);
        myStack.push(2);
        myStack.push(2);
        myStack.push(2);
        myStack.push(5);
        myStack.pop();

        var expectedElement = myStack.size();

        Assertions.assertEquals(expectedElement, 4);
    }

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

    @Test
    void check_size_after_pop_in_stack() {
        var myStack = new MyStack(5);
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.pop();

        var expectedElement = myStack.size();

        Assertions.assertEquals(expectedElement, 3);
    }

    @Test
    void check_size_after_peek_in_stack() {
        var myStack = new MyStack(5);
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.peek();

        var expectedElement = myStack.size();

        Assertions.assertEquals(expectedElement, 4);
    }
}