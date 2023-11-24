package PracticeJavaMemoryModelLinksObject;

/**
 * Написать метод, который выводит все символы типа char с использованием StringBuilder.
 * Каждый символ добавляется в StringBuilder.
 * Каждый символ разделяется символом табуляции.
 * Каждые 32 символа добавляется символ новой строки.
 * Результат StringBuilder выводится в консоль.
 */

public class Task9 {
    public static void printChars() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (char ch = Character.MIN_VALUE; ch <= Character.MAX_VALUE; ch++) {
            if (i % 32 == 0) {
                stringBuilder.append('\n');
            }
            stringBuilder.append(ch).append('\t');
            if (ch == Character.MAX_VALUE) {
                break;
            }
            i++;
        }
        System.out.println(stringBuilder);
    }

    public static void main(String[] args) {
        printChars();
    }
}
