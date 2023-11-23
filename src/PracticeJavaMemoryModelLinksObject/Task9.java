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
        Character ch = Character.MIN_VALUE;
        for (int i = 0; ; i++) {
            if (i % 32 == 0) {
                stringBuilder.append('\n');
            }
            stringBuilder.append(ch).append('\t');
            if (ch == Character.MAX_VALUE) {
                break;
            }
            ch = (char) (ch + 1);
        }
        System.out.println(stringBuilder);
    }

    public static void main(String[] args) {
        printChars();
    }
}
