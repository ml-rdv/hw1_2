package DataStructures.DynamicArray;

import java.util.Arrays;

public class MyDynamicArrayTesting {

    private static MyDynamicArray buildMyDynamicArray(int capacity) {
        return new MyDynamicArray(capacity);
    }

    public static void main(String[] args) {
        MyDynamicArray myDynArr = buildMyDynamicArray(3);
        myDynArr.add(2);
        myDynArr.add(524);
        myDynArr.add(3);
        myDynArr.add(1, 6);

        int[] arrayAdd = new int[]{2, 5, 7, 4, 1, 6};
        myDynArr.addAll(2, arrayAdd);
        myDynArr.sort();
        myDynArr.removeAll(arrayAdd);
        myDynArr.addAll(arrayAdd);
        myDynArr.set(1, -5);
        System.out.println(Arrays.toString(myDynArr.dynamicArr));
        System.out.println("element with index 6 = " + myDynArr.get(6));
        System.out.println("size = " + myDynArr.size());
        System.out.println(Arrays.toString(myDynArr.dynamicArr));
    }
}
