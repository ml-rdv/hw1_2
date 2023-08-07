package DataStructures.Stack;

import java.util.Arrays;

public class MyStackTesting {
    private static MyStack buildMyStack(int[] stack) {
        return new MyStack(stack);
    }
    public static void main(String[] args) {
        buildMyStack(new int[]{2, 5, -7, 548, 6});
        MyStack.pop();
        System.out.println(Arrays.toString(MyStack.stack));
    }
}
