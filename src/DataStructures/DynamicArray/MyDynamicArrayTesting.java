package DataStructures.DynamicArray;

import DataStructures.DynamicArray.MyDynamicArray;

import java.util.Arrays;

public class MyDynamicArrayTesting {

    private static MyDynamicArray buildMyDynamicArray(int[] dynamicArr) {
        return new MyDynamicArray(dynamicArr);
    }
    public static void main(String[] args) {
        buildMyDynamicArray(new int[] {2, 524, 3});
        MyDynamicArray.add(6);
        MyDynamicArray.add(1, 6);
        System.out.println(Arrays.toString(MyDynamicArray.dynamicArr));

        int[] arrayAdd = new int[]{2, 5, 7, 4, 1, 6};
        MyDynamicArray.addAll(1, arrayAdd);

        MyDynamicArray.removeAll(arrayAdd);
        MyDynamicArray.addAll(arrayAdd);
        System.out.println(Arrays.toString(MyDynamicArray.dynamicArr));
        MyDynamicArray.set(7, -5);
        System.out.println(Arrays.toString(MyDynamicArray.dynamicArr));
    }
}
