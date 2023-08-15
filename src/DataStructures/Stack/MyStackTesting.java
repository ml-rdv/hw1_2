package DataStructures.Stack;

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
        System.out.println(myStack.pop());
        System.out.println(myStack.peek());
        System.out.println(myStack);
    }
}
