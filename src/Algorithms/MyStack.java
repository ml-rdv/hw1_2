package Algorithms;

// Реализовать стек

import java.util.Arrays;

public class MyStack {
    public static int position = 0;
    public static int[] stack;

    public MyStack(int[] stack) {
        MyStack.stack = stack;
        position = stack.length;
    }

    public static void main(String[] args) {
        stack = new int[]{2, 5, -7, 548, 6};
        position = 5;
        pop();
        System.out.println(Arrays.toString(stack));
    }

    public static void push(int element) {
        checkCapacity();
        stack[position] = element;
        position++;
    }

    private static void checkCapacity() {
        if (stack.length <= position) {
            int[] stackCopy = new int[position + 1];
            System.arraycopy(stack, 0, stackCopy, 0, stack.length);
            stack = stackCopy;
        }
    }

    public static int pop() {
        int element = stack[position - 1];
        stack[position - 1] = 0;
        position--;
        decreaseCapacity();
        return element;
    }

    private static void decreaseCapacity() {
        int[] stackCopy = new int[stack.length - 1];
        System.arraycopy(stack, 0, stackCopy, 0, stack.length - 1);
        stack = stackCopy;
    }

    public static int peek() {
        return stack[position - 1];
    }

    public static boolean empty() {
        return stack.length == 0;
    }

    public static int search(int element) {
        for (int i = stack.length - 1, j = 1; i >= 0; i--) {
            if (stack[i] == element) {
                return j;
            }
            j++;
        }
        return -1;
    }
}
