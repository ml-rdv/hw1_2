package Multithreading.InfiniteRunning;

/**
 * Запустить код. Объяснить почему он бесконечно выполняется.
 * Исправить код, чтобы он корректно завершался без использования синхронизации.
 */

public class InfiniteRunning {
    //    private static boolean isRunning = true;
//    У каждого потока своя стековая память, поэтому изменения перемененной isRunning в первом потоке
//    не будут видны второму потоку.
//    Для того чтобы переменная была "общая" для всех потоков, нужно использовать ключевое слово volatile
//    Результат операции записи значения в volatile переменную одним потоком, становится виден всем
//    другим потокам, которые используют эту переменную для чтения из нее значения
    private static volatile boolean isRunning = true;

    public static void main(String[] args) throws InterruptedException {
        Thread setterThread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isRunning = false;
            System.out.println("isRunning установлен в false");
        });

        Thread checkerThread = new Thread(() -> {
            while (isRunning) {
            }
            System.out.println("isRunning теперь равен false");
        });

        setterThread.start();
        checkerThread.start();
    }
}
