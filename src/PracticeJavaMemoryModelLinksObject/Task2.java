/*
Ошибка StackOverflowError происходит, т.к. память стека заполнена из-за бесконечного вызова метода.
- Чем конкретно заполняется стек?
Стек заполняется вызовами меода
- Как не допустить появление StackOverflowError в данном примере?
Добавить условие выхода из метода, которое гарантированно будет выполняться
- Данный пример гарантировано будет выкидывать StackOverflowError на всех реализациях JVM?
Думаю, да, но, возможно, зависит от конкретной JVM
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
