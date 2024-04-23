package Multithreading;

/**
 * Реализовать класс касса. Касса может обслуживать только 1 покупателя, когда покупатель подходит к кассе,
 * то он её занимает на какое-то время (1 сек) и затем освобождает для другого покупателя.
 * Каждый покупатель это отдельный поток,
 * взаимодействие которого с кассой должно быть реализовано через wait/notify.
 * Для наглядности можно добавить вывод в консоль отладочных сообщений по типу:
 * “Покупатель 1 занял кассу”, “Покупатель 1 освободил кассу”, “Касса занята, покупатель 2 ожидает”.
 */

public class CashRegister {
    static final Object monitor = new Object();
    static volatile boolean isCashRegisterBusy = false;

    public void payUsingWait(int number) throws InterruptedException {
        synchronized (monitor) {
            while (isCashRegisterBusy) {
                System.out.printf("Касса занята, покупатель %d ожидает\n", number);
                monitor.wait();
            }
            isCashRegisterBusy = true;
        }
        System.out.printf("Покупатель %d занял кассу\n", number);
        Thread.sleep(1000);
        synchronized (monitor) {
            System.out.printf("Покупатель %d освободил кассу\n", number);
            isCashRegisterBusy = false;
            monitor.notifyAll();
        }
    }
}

class Main {
    public static void main(String[] args) {
        CashRegister buyer = new CashRegister();

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

}
