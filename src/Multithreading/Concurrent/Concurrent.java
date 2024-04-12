package Multithreading.Concurrent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Запустить тест. Нужно объяснить почему некоторые тесты падают и назвать проблему
 * (если тесты не упали при первом запуске, то перезапустить несколько раз).
 * Также нужно реализовать решение проблемы (предложить несколько вариантов).
 */

public class Concurrent {
    static class ConcurrentUsingAtomicInteger {
        /*
        private int value = 0;
        Проблема в данном случае - в состояние гонки. В тот момент, когда 1 поток взял старое зн-ние
        и хочет записать новое значение в переменную, другой потом берёт то же старое значение себе.
        Получается, что 2 потока записывают одинаковое зн-ние, вместо того,
        чтобы поочерёдно увеличивать на 1.

        Atomic в Java представляет собой классы в пакете java.util.concurrent.atomic,
        которые позволяют выполнять атомарные операции.
        Атомарная операция неделима. Как только операция начинает выполняться,
        она выполняется до завершения, не прерываясь другим потоком. Это помогает
        предотвратить состояние гонки.
        (Аналогично synchronized + volatile, но Atomic работает быстрее за счет CAS алгоритма.)
         */
        private final AtomicInteger value = new AtomicInteger(0);

        public void doTheWorkInParallel() throws InterruptedException {
            int repeatTime = 1000;
            int numberOfThreads = 100;

            Runnable runnable = () -> {
                for (int i = 0; i < repeatTime; i++) {
                    value.incrementAndGet();
                }
            };

            Thread[] workers = new Thread[numberOfThreads];
            for (int i = 0; i < numberOfThreads; i++) {
                workers[i] = new Thread(runnable, String.valueOf(i));
                workers[i].start();
            }
            for (int j = 0; j < numberOfThreads; j++) {
                workers[j].join();
            }

            System.out.printf("value = " + value.get() + " expected = %d", repeatTime * numberOfThreads);
        }

        public int getValue() {
            return value.get();
        }

        @Execution(ExecutionMode.CONCURRENT)
        @RepeatedTest(5000)
        void test() throws InterruptedException {
            var concurrent = new ConcurrentUsingAtomicInteger();
            concurrent.doTheWorkInParallel();

            int expectedValue = 100_000;
            Assertions.assertEquals(expectedValue, concurrent.getValue());
        }
    }

    static class ConcurrentUsingsynchronizedAndVolatile {
        // Чтобы избежать состояния гонки, использую synchronized + volatile
//        private int value = 0;

        /*
        Почему нельзя использовать только volatile?
        Операции над переменной помеченной volatile НЕ являются атомарными.
        Атомарная операция выглядит единой и неделимой командой процессора,
        которая может быть или уже выполненной, или ещё невыполненной.
        А volatile атомарность не обеспечивается, потому что это сначала выполняется чтение(1),
        потом изменение(2) в локальной памяти, а затем запись(3).
        Такая операция не является атомарной и в неё может вклиниться поток посередине.
         */
        private volatile int value = 0;

        public void doTheWorkInParallel() throws InterruptedException {
            int repeatTime = 1000;
            int numberOfThreads = 100;

            Runnable runnable = () -> {
                synchronized (this) {
                    for (int i = 0; i < repeatTime; i++) {
                        value++;
                    }
                }
            };

            Thread[] workers = new Thread[numberOfThreads];
            for (int i = 0; i < numberOfThreads; i++) {
                workers[i] = new Thread(runnable, String.valueOf(i));
                workers[i].start();
            }
            for (int j = 0; j < numberOfThreads; j++) {
                workers[j].join();
            }

            System.out.printf("value = %d expected = %d", value, repeatTime * numberOfThreads);
        }

        public int getValue() {
            return value;
        }

        @Execution(ExecutionMode.CONCURRENT)
        @RepeatedTest(5000)
        void test() throws InterruptedException {
            var concurrent = new ConcurrentUsingsynchronizedAndVolatile();
            concurrent.doTheWorkInParallel();

            int expectedValue = 100_000;
            Assertions.assertEquals(expectedValue, concurrent.getValue());
        }
    }
}