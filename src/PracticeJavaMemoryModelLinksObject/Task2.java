/*
Ошибка StackOverflowError происходит, т.к. память стека заполнена из-за бесконечного вызова метода.
 */
package PracticeJavaMemoryModelLinksObject;

public class Task2 {
    public static void main(String[] args) {
        doRecursive();
    }

    private static void doRecursive() {
        doRecursive();
    }
}
