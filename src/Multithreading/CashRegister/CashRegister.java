package Multithreading.CashRegister;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Реализовать класс касса. Касса может обслуживать только 1 покупателя, когда покупатель подходит к кассе,
 * то он её занимает на какое-то время (1 сек) и затем освобождает для другого покупателя.
 * Каждый покупатель это отдельный поток,
 * взаимодействие которого с кассой должно быть реализовано через wait/notify.
 * Для наглядности можно добавить вывод в консоль отладочных сообщений по типу:
 * “Покупатель 1 занял кассу”, “Покупатель 1 освободил кассу”, “Касса занята, покупатель 2 ожидает”.
 */

public class CashRegister {
    static CashRegister buyer = new CashRegister();
    static final CashRegister monitor1 = new CashRegister();
    static final CashRegister monitor2 = new CashRegister();
    static AtomicBoolean isCashRegisterBusy = new AtomicBoolean(false);

    public static void main(String[] args) {
        for (int i = 1; i < 6; i++) {
            int finalI = i;
            // Создание покупателей в цикле
            new Thread(() -> {
                try {
                    buyer.payUsingWait(finalI);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }

    public void payUsingWait(int number) throws InterruptedException {
        if (isCashRegisterBusy.get()) {
            waiting(number);
        } else {
            paying(number);
        }
    }

    private void waiting(int number) throws InterruptedException {
        synchronized (monitor1) {
            System.out.printf("Касса занята, покупатель %d ожидает\n", number);
            wait();
        }
    }

    private void paying(int number) throws InterruptedException {
        synchronized (monitor2) {
            isCashRegisterBusy.getAndSet(true);
            System.out.printf("Покупатель %d занял кассу\n", number);
            Thread.sleep(1000);
            System.out.printf("Покупатель %d освободил кассу\n", number);
            isCashRegisterBusy.getAndSet(false);
            notifyAll();
        }
    }
}
