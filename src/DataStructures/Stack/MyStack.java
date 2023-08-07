package DataStructures.Stack;

// Реализовать стек

public class MyStack {
    public int size = 0;
    public int[] stack;

    public MyStack(int capacity) {
        this.stack = new int[capacity];
    }

    public void push(int element) {
        extendArray();
        stack[size] = element;
        size++;
    }

    public int pop() {
        int element = stack[size - 1];
        stack[size - 1] = 0;
        size--;
        return element;
    }

    public int peek() {
        return stack[size - 1];
    }

    public boolean empty() {
        return size == 0;
    }

    public int search(int element) {
        for (int i = stack.length - 1, j = 0; i >= 0; i--, j++) {
            if (stack[i] == element) {
                return j;
            }
        }
        return -1;
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
}
