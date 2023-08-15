package DataStructures.DynamicArray;

// Реализовать динамический массив(список)

public class MyDynamicArray implements Cloneable {
    private int size = 0;
    private int[] dynamicArr;

    public MyDynamicArray(int capacity) {
        this.dynamicArr = new int[capacity];
    }

    public MyDynamicArray() {
        int DEFAULT_CAPACITY = 25;
        this.dynamicArr = new int[DEFAULT_CAPACITY];
    }

    public int[] getDynamicArr() {
        return dynamicArr;
    }

    public void add(int element) {
        checkArray();
        dynamicArr[size] = element;
        size++;
    }

    public void add(int index, int element) {
        checkArray();
        int[] dynamicArrCopy = new int[dynamicArr.length];
        for (int i = 0; i < index; i++) {
            dynamicArrCopy[i] = dynamicArr[i];
        }
        dynamicArrCopy[index] = element;
        for (int i = index; i < dynamicArr.length - 1; i++) {
            dynamicArrCopy[i + 1] = dynamicArr[i];
        }
        dynamicArr = dynamicArrCopy;
        size++;
    }

    public void addAll(MyDynamicArray dynamicArray) {
        int[] arr = dynamicArray.getDynamicArr();
        checkArrayWithArray(arr.length);
        int index = size;
        for (int i = 0; i < arr.length; i++, index++) {
            dynamicArr[index] = arr[i];
        }
        size += arr.length;
    }

    public void addAll(int index, MyDynamicArray dynamicArray) {
        int[] arr = dynamicArray.getDynamicArr();
        checkArrayWithArray(arr.length);
        int[] dynamicArrCopy = new int[dynamicArr.length];
        for (int i = 0; i < index; i++) {
            dynamicArrCopy[i] = dynamicArr[i];
        }
        for (int i = 0, indexCopy = index; i < arr.length; i++, indexCopy++) {
            dynamicArrCopy[indexCopy] = arr[i];
        }
        for (int i = index; i < size; i++) {
            dynamicArrCopy[i + arr.length] = dynamicArr[i];
        }
        dynamicArr = dynamicArrCopy;
        size += arr.length;
    }

    private void checkArray() {
        if (dynamicArr.length <= size) {
            int lengthOfNewArr = size * 2 + 1;
            extendArray(lengthOfNewArr);
        }
    }

    private void checkArrayWithArray(int lengthOfArr) {
        if (dynamicArr.length <= size + lengthOfArr) {
            int lengthOfNewArr = size + lengthOfArr;
            extendArray(lengthOfNewArr);
        }
    }

    private void extendArray(int lengthOfNewArr) {
        int[] dynamicArrCopy = new int[lengthOfNewArr];
        for (int i = 0; i < dynamicArr.length; i++) {
            dynamicArrCopy[i] = dynamicArr[i];
        }
        dynamicArr = dynamicArrCopy;
    }

    public void clear() {
        dynamicArr = new int[0];
        size = 0;
    }

    public MyDynamicArray cloneArr() throws CloneNotSupportedException {
        return (MyDynamicArray) this.clone();
    }

    public boolean contains(int numb) {
        return indexOf(numb) != -1;
    }

    public int indexOf(int elem) {
        for (int i = 0; i < size; i++) {
            if (dynamicArr[i] == elem) {
                return i;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int lastIndexOf(int elem) {
        for (int i = dynamicArr.length - 1; i >= 0; i--) {
            if (dynamicArr[i] == elem) {
                return i;
            }
        }
        return -1;
    }

    public int get(int index) throws IndexOutOfBoundsException {
        if (index < 0 && index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return dynamicArr[index];
    }

    public void remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 && index >= size) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = index; i < size - 1; i++) {
            dynamicArr[i] = dynamicArr[i + 1];
        }
        size--;
    }

    public void set(int index, int element) throws IndexOutOfBoundsException {
        if (index < 0 && index >= size) {
            throw new IndexOutOfBoundsException();
        }
        dynamicArr[index] = element;
    }

    public int size() {
        return size;
    }

    public void sort() {
        for (int i = 0; i < size; i++) {
            int x = dynamicArr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (dynamicArr[j] <= x) {
                    break;
                }
                int k = dynamicArr[j + 1];
                dynamicArr[j + 1] = dynamicArr[j];
                dynamicArr[j] = k;
            }
        }
    }

    public String toString() {
        if (dynamicArr == null)
            return "null";
        int iMax = dynamicArr.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(dynamicArr[i]);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }

}
