package Multithreading;

import java.util.concurrent.*;

/**
 * Есть логика по подсчёту кол-ва пользователей. Логика работает 6 секунд.
 * Необходимо добиться, чтобы логика выполнялась за 2 секунды.
 * Нужно решить задачу двумя способами:
 * а. Используя пул потоков через Executors
 * б. Используя только CompletableFuture
 */
public class CompletableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Standard method");
        var start = System.currentTimeMillis();

        var usersNumFromDB = getUsersNumberFromDatabase();
        var usersNumFromUserService = getUsersNumberFromUserService();
        var usersNumFromCache = countUsersNumberFromCache();

        var result = usersNumFromDB + usersNumFromUserService + usersNumFromCache;
        System.out.println("result = " + result);

        var end = System.currentTimeMillis() - start;
        System.out.println(end / 1000L + " sec");

        // Решение с использованием Executors
        System.out.println("Method using Executors");
        getUsersUsingExecutors();

        // Решение с использованием CompletableFuture
        System.out.println("Method using CompletableFuture");
        getUsersUsingCompletableFuture();
    }

    private static void getUsersUsingCompletableFuture() {
        var start = System.currentTimeMillis();

        CompletableFuture<Integer> usersNumFromDBCF = CompletableFuture.supplyAsync(CompletableFutureExample::getUsersNumberFromDatabase);
        CompletableFuture<Integer> usersNumFromUserServiceCF = CompletableFuture.supplyAsync(CompletableFutureExample::getUsersNumberFromUserService);
        CompletableFuture<Integer> usersNumFromCacheCF = CompletableFuture.supplyAsync(CompletableFutureExample::countUsersNumberFromCache);

        // Композиция CompletableFuture (в данном случае объединение результата)
        var result = usersNumFromDBCF
                .thenCombine(usersNumFromUserServiceCF, Integer::sum)
                .thenCombine(usersNumFromCacheCF, Integer::sum)
                .join();

        System.out.println("result = " + result);

        var end = System.currentTimeMillis() - start;
        System.out.println(end / 1000L + " sec");
    }

    private static void getUsersUsingExecutors() throws ExecutionException, InterruptedException {
        var start = System.currentTimeMillis();
        int result;

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        try {
            Future<Integer> usersNumFromDB = executorService.submit(CompletableFutureExample::getUsersNumberFromDatabase);
            Future<Integer> usersNumFromUserService = executorService.submit(CompletableFutureExample::getUsersNumberFromUserService);
            Future<Integer> usersNumFromCache = executorService.submit(CompletableFutureExample::countUsersNumberFromCache);
            result = usersNumFromDB.get() + usersNumFromUserService.get() + usersNumFromCache.get();
        } finally {
            executorService.shutdown();
        }
        System.out.println("result = " + result);

        var end = System.currentTimeMillis() - start;
        System.out.println(end / 1000L + " sec");
    }

    static int getUsersNumberFromDatabase() {
        try {
            // обращение к БД
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 10;
    }

    static int getUsersNumberFromUserService() {
        try {
            // http вызов другого сервиса
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 15;
    }

    static int countUsersNumberFromCache() {
        try {
            // алгоритм обхода и подсчёта по кэшам
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 5;
    }
}
