/*
Написать метод, который выводит все символы типа char с использованием StringBuilder.
Каждый символ добавляется в StringBuilder.
Каждый символ разделяется символом табуляции.
Каждые 32 символа добавляется символ новой строки.
Результат StringBuilder выводится в консоль.
 */
package PracticeJavaMemoryModelLinksObject;

public class Task9 {
    public static void printChars() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <= 127; i++) {
            if (i % 32 == 0) {
                stringBuilder.append('\n');
            }
            stringBuilder.append((char) i).append('\t');
        }
        System.out.println(stringBuilder);
    }

    public static void main(String[] args) {
        printChars();
    }
}
