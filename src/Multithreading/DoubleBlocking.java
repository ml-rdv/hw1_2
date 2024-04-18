package Multithreading;

/**
 * Какая проблема представлена в данном коде? Предложить и реализовать решение проблемы.
 */
public class DoubleBlocking {

    /*
    ??? Не уверена
    - В роли монитора следует использовать final объекты, объекты класса Account не являются final
    - Использование входных параметров в роли монитора опасно тем, что 2 потока могут выполнить
    метод в случае передачи в метод разных параметров
    Потенциальный DeadLock
     */

    static Object mutex = new Object();

    static void transferMoney(Account a1, Account a2, int summa) {
        if (a1.hashCode() == a2.hashCode()) {
            // уменьшаем производительность, увеличиваем безопасность
            synchronized (mutex) {
                synchronized (a1) {
                    synchronized (a2) {
                        a1.money = a1.money - summa;
                        a2.money = a2.money + summa;
                    }
                }
            }
            return;
        }
        // 1 из решений проблемы DeadLock = упорядочивание
        final Account first = (a1.hashCode() > a2.hashCode()) ? a2 : a1;
        final Account second = (a1.hashCode() > a2.hashCode()) ? a1 : a2;

        synchronized (first) {
            synchronized (second) {
                a1.money = a1.money - summa;
                a2.money = a2.money + summa;
            }
        }
    }

    static class Account {
        int id;
        int money;
    }

    public static void main(String[] args) {
        Account a1 = new Account();
        a1.money = 100;

        Account a2 = new Account();
        a2.money = 0;

        Account a3 = new Account();
        a3.money = 0;
        for (int i = 0; i < 100; i++) {
            new Thread(() -> DoubleBlocking.transferMoney(a1, a2, 1)).start();
            new Thread(() -> DoubleBlocking.transferMoney(a2, a1, 1)).start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("a1 = %d a2 = %d a3 = %d\n", a1.money, a2.money, a3.money);
    }
}
