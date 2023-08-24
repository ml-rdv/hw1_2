package DataStructures.DynamicArray;

// Реализовать динамический массив(список)

public class MyDynamicArray {
    private int size = 0;
    private int[] dynamicArr;

    public MyDynamicArray(int capacity) {
        this.dynamicArr = new int[capacity];
    }

    public MyDynamicArray() {
        int DEFAULT_CAPACITY = 25;
        this.dynamicArr = new int[DEFAULT_CAPACITY];
    }

    private int[] getDynamicArr() {
        return dynamicArr;
    }

    public void add(int element) {
        extendArray();
        dynamicArr[size] = element;
        size++;
    }

    public void add(int index, int element) {
        extendArray();
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
        extendArray(arr.length);
        int index = size;
        for (int i = 0; i < arr.length; i++, index++) {
            dynamicArr[index] = arr[i];
        }
        size += dynamicArray.size();
    }

    public void addAll(int index, MyDynamicArray dynamicArray) {
        int[] arr = dynamicArray.getDynamicArr();
        extendArray(arr.length);
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

    private void extendArray() {
        extendArray(size, size * 2 + 1);
    }

    private void extendArray(int arrayLength) {
        extendArray(size + arrayLength, size + arrayLength);
    }

    private void extendArray(int checkSize, int newSize) {
        if (dynamicArr.length <= checkSize) {
            int[] dynamicArrCopy = new int[newSize];
            for (int i = 0; i < dynamicArr.length; i++) {
                dynamicArrCopy[i] = dynamicArr[i];
            }
            dynamicArr = dynamicArrCopy;
        }
    }

    public void clear() {
        dynamicArr = new int[0];
        size = 0;
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
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of dynamic array.");
        }
        return dynamicArr[index];
    }

    public void remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of dynamic array.");
        }
        for (int i = index; i < size - 1; i++) {
            dynamicArr[i] = dynamicArr[i + 1];
        }
        size--;
    }

    public void set(int index, int element) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of dynamic array.");
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
