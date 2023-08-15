package DataStructures.DynamicArray;

// Реализовать динамический массив(список)

public class MyDynamicArray {
    public int size = 0;
    public int[] dynamicArr;

    public MyDynamicArray(int capacity) {
        this.dynamicArr = new int[capacity];
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

    private void extendArray() {
        if (dynamicArr.length <= size) {
            int[] dynamicArrCopy = new int[size * 2 + 1];
            for (int i = 0; i < dynamicArr.length; i++) {
                dynamicArrCopy[i] = dynamicArr[i];
            }
            dynamicArr = dynamicArrCopy;
        }
    }

    public void addAll(int[] arr) {
        extendArrayWithArray(arr.length);
        for (int i = 0; i < arr.length; i++, size++) {
            dynamicArr[size] = arr[i];
        }
    }

    private void extendArrayWithArray(int lengthOfNewArr) {
        if (dynamicArr.length <= size + lengthOfNewArr) {
            int[] dynamicArrCopy = new int[size + lengthOfNewArr];
            for (int i = 0; i < dynamicArr.length; i++) {
                dynamicArrCopy[i] = dynamicArr[i];
            }
            dynamicArr = dynamicArrCopy;
        }
    }

    public void addAll(int index, int[] arr) {
        extendArrayWithArray(arr.length);
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

    public void clear() {
        dynamicArr = new int[0];
        size = 0;
    }

    public int[] cloneArr() {
        int[] dynamicArrCopy = new int[dynamicArr.length];
        for (int i = 0; i < dynamicArr.length; i++) {
            dynamicArrCopy[i] = dynamicArr[i];
        }
        return dynamicArrCopy;
    }

    public boolean contains(int numb) {
        return indexOf(numb) != -1;
    }

    public int indexOf(int elem) {
        int positionOfSearch = -1;
        for (int i = 0; i < size; i++) {
            if (dynamicArr[i] == elem) {
                positionOfSearch = i;
                break;
            }
        }
        return positionOfSearch;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int lastIndexOf(int elem) {
        int positionOfSearch = -1;
        for (int i = dynamicArr.length - 1; i >= 0; i--) {
            if (dynamicArr[i] == elem) {
                positionOfSearch = i;
                break;
            }
        }
        return positionOfSearch;
    }

    public int get(int index) {
        if (index >= 0 && index < size) {
            return dynamicArr[index];
        }
        return -1; // null
    }

    public void remove(int index) {
        if (index >= 0 && index < size) {
            int[] dynamicArrCopy = new int[dynamicArr.length];
            for (int i = 0; i < index; i++) {
                dynamicArrCopy[i] = dynamicArr[i];
            }
            for (int i = index; i < size - 1; i++) {
                dynamicArrCopy[i] = dynamicArr[i + 1];
            }
            dynamicArr = dynamicArrCopy;
            size--;
        }
    }

    public void removeObject(int numb) {
        for (int i = 0; i < size; i++) {
            if (dynamicArr[i] == numb) {
                remove(i);
                i--;
            }
        }
    }

    public void removeAll(int[] arr) {
        for (int i : arr) {
            removeObject(i);
        }
    }

    public void set(int index, int element) {
        if (index >= 0 && index < size) {
            dynamicArr[index] = element;
        }
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
}
