package Multithreading.ShowThreadName;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Реализовать метод, который выводит название текущего потока в консоль,
 * если булево поле isShowThreadName == true. После вывода имени потока, поле isShowThreadName
 * должно стать false.
 * Метод должен запускаться как минимум в 2 потоках. Ожидается, что в консоль будет выводиться
 * название только 1 потока, но без синхронизации такой гарантии невозможно получить,
 * поэтому необходимо синхронизировать работу метода всеми известными способами.
 */

public class MyThread {
    public static void main(String[] args) {
        FirstThread firstThread = new FirstThread();
        Thread t1 = new Thread(firstThread);

        SecondThread secondThread = new SecondThread();
        Thread t2 = new Thread(secondThread);

        ThirdThread thirdThread = new ThirdThread();
        Thread t3 = new Thread(thirdThread);

        t1.start();
        t2.start();
        t3.start();
    }

    public static synchronized void showThreadNameUsingSynchronized(CommonThread thread) {
        if (thread.isShowThreadName) {
            System.out.println(thread.getClass().getName());
            thread.isShowThreadName = false;
        }
    }

    public static void showThreadNameUsingLock(CommonThread thread) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            if (thread.isShowThreadName) {
                System.out.println(thread.getClass().getName());
                thread.isShowThreadName = false;
            }
        } finally {
            lock.unlock();
        }
    }

    public static void showThreadNameUsingMonitor(CommonThread thread) {
        synchronized (thread) {
            if (thread.isShowThreadName) {
                System.out.println(thread.getClass().getName());
                thread.isShowThreadName = false;
            }
        }
    }
}

class CommonThread {
    boolean isShowThreadName = true;
}

class FirstThread extends CommonThread implements Runnable {
    @Override
    public void run() {
        MyThread.showThreadNameUsingLock(this);
        MyThread.showThreadNameUsingLock(this);
    }
}

class SecondThread extends CommonThread implements Runnable {
    @Override
    public void run() {
        MyThread.showThreadNameUsingLock(this);
        MyThread.showThreadNameUsingLock(this);
    }
}

class ThirdThread extends CommonThread implements Runnable {
    @Override
    public void run() {
        MyThread.showThreadNameUsingLock(this);
        MyThread.showThreadNameUsingLock(this);
    }
}
