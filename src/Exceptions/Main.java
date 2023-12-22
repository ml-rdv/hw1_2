package Exceptions;

/**
 * Просмотреть код и попробовать без запуска определить, что будет выведено в консоль.
 * Затем запустить код и проверить себя.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(getValue()); // 1
        System.out.println(getValue2()); // 2
        System.out.println(getValue3()); // 3
        System.out.println(getValue4()); // 3
        System.out.println(getValue5()); // RuntimeException
    }

    public static int getValue() {
        try {
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {
        }
    }

    public static int getValue2() {
        try {
            if (true) {
                throw new Exception("An error occurred");
            }
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {
        }
    }

    public static int getValue3() {
        try {
            if (true) {
                throw new Exception("An error occurred");
            }
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {
            return 3;
        }
    }

    public static int getValue4() {
        try {
            if (true) {
                throw new Exception("An error occurred");
            }
            return 1;
        } catch (Exception e) {
            throw new RuntimeException("new Exception", e);
        } finally {
            return 3;
        }
    }

    public static int getValue5() {
        try {
            if (true) {
                throw new Exception("An error occurred");
            }
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {
            if (true) {
                throw new RuntimeException();
            }
            return 3;
        }
    }
}
