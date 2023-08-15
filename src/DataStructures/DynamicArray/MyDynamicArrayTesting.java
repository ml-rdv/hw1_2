package DataStructures.DynamicArray;

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
        System.out.println(myDynArr);

        MyDynamicArray myDynArr2 = buildMyDynamicArray(6);
        myDynArr2.add(2);
        myDynArr2.add(5);
        myDynArr2.add(7);
        myDynArr2.add(4);
        myDynArr2.add(1);
        myDynArr2.add(6);
        myDynArr.addAll(2, myDynArr2);
        System.out.println(myDynArr);
        myDynArr.sort();

        myDynArr.addAll(myDynArr2);
        myDynArr.set(1, -5);
        System.out.println(myDynArr);
        System.out.println("element with index 6 = " + myDynArr.get(6));
        System.out.println("size = " + myDynArr.size());
        System.out.println(myDynArr);
    }
}
