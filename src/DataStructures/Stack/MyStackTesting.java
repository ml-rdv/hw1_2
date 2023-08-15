package DataStructures.Stack;

import java.util.Arrays;

public class MyStackTesting {
    private static MyStack buildMyStack(int capacity) {
        return new MyStack(capacity);
    }

    public static void main(String[] args) {
        MyStack myStack = buildMyStack(5);
        myStack.push(2);
        myStack.push(5);
        myStack.push(-7);
        myStack.push(548);
        myStack.push(6);
        myStack.pop();
        System.out.println(myStack.search(-7));
        System.out.println(Arrays.toString(myStack.stack));
    }
}
