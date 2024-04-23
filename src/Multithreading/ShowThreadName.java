package Multithreading;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Реализовать метод, который выводит название текущего потока в консоль,
 * если булево поле isShowThreadName == true. После вывода имени потока, поле isShowThreadName
 * должно стать false.
 * Метод должен запускаться как минимум в 2 потоках. Ожидается, что в консоль будет выводиться
 * название только 1 потока, но без синхронизации такой гарантии невозможно получить,
 * поэтому необходимо синхронизировать работу метода всеми известными способами.
 */

public class ShowThreadName {
    static boolean isShowThreadName1 = true;
    static boolean isShowThreadName2 = true;
    static boolean isShowThreadName3 = true;

    public static void main(String[] args) {
        Thread t1 = new Thread(ShowThreadName::showThreadNameUsingSynchronized);
        Thread t2 = new Thread(ShowThreadName::showThreadNameUsingSynchronized);

        t1.start();
        t2.start();

        Thread t3 = new Thread(ShowThreadName::showThreadNameUsingLock);
        Thread t4 = new Thread(ShowThreadName::showThreadNameUsingLock);

        t3.start();
        t4.start();

        ShowThreadName myThread = new ShowThreadName();

        Thread t5 = new Thread(myThread::showThreadNameUsingMonitor);
        Thread t6 = new Thread(myThread::showThreadNameUsingMonitor);

        t5.start();
        t6.start();
    }

    public static synchronized void showThreadNameUsingSynchronized() {
        if (isShowThreadName1) {
            System.out.println(Thread.currentThread().getName());
            isShowThreadName1 = false;
        }
    }

    static ReentrantLock lock = new ReentrantLock();

    public static void showThreadNameUsingLock() {
        lock.lock();
        try {
            if (isShowThreadName2) {
                System.out.println(Thread.currentThread().getName());
                isShowThreadName2 = false;
            }
        } finally {
            lock.unlock();
        }
    }

    public void showThreadNameUsingMonitor() {
        synchronized (this) {
            if (isShowThreadName3) {
                System.out.println(Thread.currentThread().getName());
                isShowThreadName3 = false;
            }
        }
    }
}
