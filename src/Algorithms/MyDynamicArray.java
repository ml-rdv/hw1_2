package Algorithms;

import java.util.Arrays;

// Реализовать динамический массив(список)

public class MyDynamicArray {
    public static int position = 0;
    public static int[] dynamicArr;

    public static void main(String[] args) {
        // работа с динамическим массивом
        dynamicArr = new int[]{2, 524, 3};
        position = 3;
        add(6);
        add(1, 6);

        int[] arrayAdd = new int[]{2, 5, 7, 4, 1, 6};
        addAll(1, arrayAdd);

        removeAll(arrayAdd);
        addAll(arrayAdd);
        System.out.println(Arrays.toString(dynamicArr));
        set(7, -5);
        System.out.println(Arrays.toString(dynamicArr));
        System.out.println(contains(565));
        System.out.println(Arrays.toString(dynamicArr));
        sort();
        add(1);
        System.out.println(lastIndexOf(1));
        System.out.println(Arrays.toString(dynamicArr));
    }

    public static void add(int element) {
        checkCapacity();
        dynamicArr[position] = element;
        position++;
    }

    public static void add(int index, int element) {
        checkCapacity();
        int[] dynamicArrCopy = new int[dynamicArr.length];
        if (index >= 0)
            System.arraycopy(dynamicArr, 0, dynamicArrCopy, 0, index);
        dynamicArrCopy[index] = element;
        if (dynamicArr.length - index >= 0)
            System.arraycopy(dynamicArr, index, dynamicArrCopy, index + 1, dynamicArr.length - index - 1);
        dynamicArr = dynamicArrCopy;
        position++;
    }

    private static void checkCapacity() {
        if (dynamicArr.length <= position) {
            int[] dynamicArrCopy = new int[position * 2 + 1];
            System.arraycopy(dynamicArr, 0, dynamicArrCopy, 0, dynamicArr.length);
            dynamicArr = dynamicArrCopy;
        }
    }

    public static void addAll(int[] arr) {
        checkCapacityWithArray(arr.length);
        System.arraycopy(arr, 0, dynamicArr, position, arr.length);
        position += arr.length;
    }

    private static void checkCapacityWithArray(int lengthOfNewArr) {
        if (dynamicArr.length <= position + lengthOfNewArr) {
            int[] dynamicArrCopy = new int[position + lengthOfNewArr];
            System.arraycopy(dynamicArr, 0, dynamicArrCopy, 0, dynamicArr.length);
            dynamicArr = dynamicArrCopy;
        }
    }

    public static void addAll(int index, int[] arr) {
        checkCapacityWithArray(arr.length);
        int[] dynamicArrCopy = new int[dynamicArr.length + arr.length];
        System.arraycopy(dynamicArr, 0, dynamicArrCopy, 0, index);
        System.arraycopy(arr, 0, dynamicArrCopy, index, arr.length);
        System.arraycopy(dynamicArr, index, dynamicArrCopy, index + arr.length, dynamicArr.length - index);
        dynamicArr = dynamicArrCopy;
        position += arr.length;
    }

    public static void clear() {
        dynamicArr = new int[0];
        position = 0;
    }

    public static int[] cloneArr() {
        int[] dynamicArrCopy = new int[dynamicArr.length];
        System.arraycopy(dynamicArr, 0, dynamicArrCopy, 0, dynamicArr.length);
        return dynamicArrCopy;
    }

    public static boolean contains(int numb) {
        return indexOf(numb) != -1;
    }

    public static int indexOf(int elem) {
        int positionOfSearch = -1;
        for (int i = 0; i < dynamicArr.length; i++) {
            if (dynamicArr[i] == elem) {
                positionOfSearch = i;
                break;
            }
        }
        return positionOfSearch;
    }

    public static boolean isEmpty() {
        return dynamicArr.length == 0;
    }

    public static int lastIndexOf(int elem) {
        int positionOfSearch = -1;
        for (int i = dynamicArr.length - 1; i >= 0; i--) {
            if (dynamicArr[i] == elem) {
                positionOfSearch = i;
                break;
            }
        }
        return positionOfSearch;
    }

    public static void remove(int index) {
        if (index >= 0 && index < dynamicArr.length) {
            int[] dynamicArrCopy = new int[dynamicArr.length];
            System.arraycopy(dynamicArr, 0, dynamicArrCopy, 0, index);
            System.arraycopy(dynamicArr, index + 1, dynamicArrCopy, index, dynamicArr.length - index - 1);
            dynamicArr = dynamicArrCopy;
            position--;
        }
    }

    public static void removeObject(int numb) {
        for (int i = 0; i < dynamicArr.length; i++) {
            if (dynamicArr[i] == numb) {
                remove(i);
                i--;
            }
        }
    }

    public static void removeAll(int[] arr) {
        for (int i : arr) {
            removeObject(i);
        }
    }

    public static void set(int index, int element) {
        if (index >= 0 && index < dynamicArr.length) {
            dynamicArr[index] = element;
        }
    }

    public static int size() {
        return dynamicArr.length;
    }

    public static void sort() {
        for (int i = 0; i < dynamicArr.length; i++) {
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
        for (int i = dynamicArr.length - 1; i >= 0; i--) {
            if (dynamicArr[i] != 0) {
                position = i + 1;
                break;
            }
        }
    }

//    public static void workWithList() {
//        ArrayList<Integer> list = new ArrayList<Integer>();
//        list.add(1);
//        list.add(1);
//        list.add(-1);
//        list.add(-1);
//        list.add(100);
//        list.add(105);
//        list.add(-1);
//        list.add(100);
//        list.add(107);
//        list.add(25);
//        list.add(15);
//
//        // Найти второй минимальный элемент массива
//        Collections.sort(list);
//        ArrayList<Integer> list3 = new ArrayList<Integer>();
//        for (int element : list) {
//            if (!list3.contains(element)) {
//                list3.add(element);
//            }
//        }
//        System.out.println(list3.get(1));
//
//        // Первые неповторяющиеся целые числа в массиве
//        ArrayList<Integer> list2 = new ArrayList<Integer>();
//        int previous = list.get(0), count = 1;
//        // Сложность = n
//        for (int i = 1; i < list.size(); i++) {
//            int element = list.get(i);
//            if (previous == element) {
//                count++;
//            } else if (count == 0) {
//                list2.add(previous);
//            } else {
//                count = 0;
//            }
//            if (i == list.size() - 1) {
//                if (element != list.get(i - 1)) {
//                    list2.add(list.get(i));
//                }
//            }
//            previous = list.get(i);
//        }
//        System.out.println(list2);
//
//        // Объединить два отсортированных массива
//        list.addAll(list2);
//        System.out.println(list);
//
//        // Изменение порядка положительных и отрицательных значений в массиве
//        // Реверс массива ???
//        ArrayList<Integer> list4 = new ArrayList<Integer>();
//        // Сложность = n
//        for (int i = list.size() - 1; i >= 0; i--) {
//            list4.add(list.get(i));
//        }
//        System.out.println(list4);
//    }

}
