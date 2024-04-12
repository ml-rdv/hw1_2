package Multithreading.TwoThreads;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Реализовать 2 потока через класс Thread.
 * a. Первый поток в цикле каждую 1 мс выводит целочисленное поле count в консоль. Условие выхода
 * из цикла является признак прерывания потока.
 * b. Второй поток в цикле инкрементирует поле count каждую 1 мс.
 * Цикл работает пока count != n (n можно взять =< 1000).
 * c. Программа должна корректно сама завершаться без зависаний и ожидания потоков.
 */

public class TwoThreads {
    public static void main(String[] args) {
        FirstThread firstThread = new FirstThread();
        SecondThread secondThread = new SecondThread();

//        Если устанавливаю флаг демон, потоки останавливают работу
//        вместе с программой
//        firstThread.setDaemon(true);
//        secondThread.setDaemon(true);

        firstThread.start();
        secondThread.start();

//        Чтобы потоки успели вывести хоть что-то
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        firstThread.interrupt();
        secondThread.interrupt();
    }
}

class FirstThread extends Thread {
    @Override
    public void run() {
        while (!isInterrupted()) {
            System.out.println(SecondThread.count.get());
            try {
                FirstThread.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                break;
            }
        }
    }
}

class SecondThread extends Thread {
    protected final static AtomicInteger count = new AtomicInteger();

    @Override
    public void run() {
        int n = 1000;
        while (count.get() != n) {
            count.incrementAndGet();
            try {
                SecondThread.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}