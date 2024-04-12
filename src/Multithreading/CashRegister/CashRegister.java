package Multithreading.CashRegister;

/**
 * Реализовать класс касса. Касса может обслуживать только 1 покупателя, когда покупатель подходит к кассе,
 * то он её занимает на какое-то время (1 сек) и затем освобождает для другого покупателя.
 * Каждый покупатель это отдельный поток,
 * взаимодействие которого с кассой должно быть реализовано через wait/notify.
 * Для наглядности можно добавить вывод в консоль отладочных сообщений по типу:
 * “Покупатель 1 занял кассу”, “Покупатель 1 освободил кассу”, “Касса занята, покупатель 2 ожидает”.
 */

public class CashRegister {
    static CashRegister monitor = new CashRegister();

    public static void main(String[] args) {
        new Client1().start();
        new Client2().start();
        new Client3().start();
    }

    public static synchronized void payUsingSleep(int number) {
        try {
            System.out.printf("Покупатель %d занял кассу\n", number);
            Thread.sleep(1000);
            System.out.printf("Покупатель %d освободил кассу\n", number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /*
    Не понимаю, как реализовать через wait/notify
    wait усыпляет поток, предоставляя доступ другому потоку, но как это поможет
    в данном случае решить задачу?

    Это не работающий набросок

    public synchronized void payUsingWait(int number) {
        try {
            System.out.printf("Касса занята, покупатель %d ожидает\n", number);
            wait();
            System.out.printf("Покупатель %d занял кассу\n", number);
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
        }
        System.out.printf("Покупатель %d освободил кассу\n", number);
        notify();
    }
    */
}

class Client1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
//            CashRegister.monitor.payUsingWait(1);
            CashRegister.payUsingSleep(1);
        }
    }
}

class Client2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
//            CashRegister.monitor.payUsingWait(2);
            CashRegister.payUsingSleep(2);
        }
    }
}

class Client3 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
//            CashRegister.monitor.payUsingWait(3);
            CashRegister.payUsingSleep(3);
        }
    }
}