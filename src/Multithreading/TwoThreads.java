package Multithreading;

/**
 * Реализовать 2 потока через класс Thread.
 * a. Первый поток в цикле каждую 1 мс выводит целочисленное поле count в консоль. Условие выхода
 * из цикла является признак прерывания потока.
 * b. Второй поток в цикле инкрементирует поле count каждую 1 мс.
 * Цикл работает пока count != n (n можно взять =< 1000).
 * c. Программа должна корректно сама завершаться без зависаний и ожидания потоков.
 */

public class TwoThreads {
    static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread firstThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(count);
                try {
                    Thread.sleep(1L);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                    break;
                }
            }
        });
        Thread secondThread = new Thread(() -> {
            int n = 1000;
            while (count != n) {
                count++;
                try {
                    Thread.sleep(1L);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        firstThread.start();
        secondThread.start();

        // ждём завершения второго потока
        secondThread.join();

        firstThread.interrupt();
    }
}