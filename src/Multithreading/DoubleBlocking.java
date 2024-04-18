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
     */
    static void transferMoney(Account a1, Account a2, int summa) {
        synchronized (a1) {
            synchronized (a2) {
                a1.money = a1.money - summa;
                a2.money = a2.money + summa;
            }
        }
    }

    /*
    Сомневаюсь, что это правильное решение, т.к. обычная синхронизация на методе
    довольная ограничивающая.
    Позволяет только 1 потому в момент времени выполнять метод
     */
    static synchronized void transferMoney2(Account a1, Account a2, int summa) {
        a1.money = a1.money - summa;
        a2.money = a2.money + summa;
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
            new Thread(() -> DoubleBlocking.transferMoney2(a1, a2, 1)).start();
            new Thread(() -> DoubleBlocking.transferMoney2(a2, a3, 1)).start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("a1 = %d a2 = %d a3 = %d\n", a1.money, a2.money, a3.money);
    }
}
