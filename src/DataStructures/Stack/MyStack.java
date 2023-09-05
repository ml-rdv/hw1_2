package DataStructures.Stack;

// Реализовать стек

public class MyStack {
    private int size = 0;
    private int[] stack;

    public MyStack(int capacity) {
        this.stack = new int[capacity];
    }

    public MyStack() {
        int DEFAULT_CAPACITY = 25;
        this.stack = new int[DEFAULT_CAPACITY];
    }

    public void push(int element) {
        extendArray();
        stack[size] = element;
        size++;
    }

    public int pop() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Stack is empty");
        }
        return stack[--size];
    }

    public int peek() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Stack is empty");
        }
        return stack[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void extendArray() {
        if (stack.length <= size) {
            int[] stackCopy = new int[size * 2 + 1];
            for (int i = 0; i < stack.length; i++) {
                stackCopy[i] = stack[i];
            }
            stack = stackCopy;
        }
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; i < size; i++) {
            b.append(stack[i]);
            if (i == size - 1)
                return b.append(']').toString();
            b.append(", ");
        }
        return "";
    }
}
