package FunctionalProgramming.Car;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Есть класс Car с методом start(), который заводит машину.
 * Машина начинает заводиться сразу, как только вызовем метод,
 * причём запуск машины может занимать продолжительное время и пока машина не заведется,
 * других дел мы выполнять не можем. Представим, что нам нужно завести машину по таймеру,
 * например за 30 минут до начала работы. Для этого нам понадобиться ленивый вызов метода start(),
 * чтобы машину запустить не сразу, а позже по необходимости.
 * Суть задачи: реализовать ленивый вызов метода start().
 */
public class Main {
    public static void main(String[] args) {
        Car car = new Car();

        // not lazy!!!
        car.start();

        // lazy
        Supplier<Long> supplier = car.startLazy();

        Long l = supplier.get();

        System.out.println(l);

        // lazy
        // 2 type
        Supplier<Long> start = car::start;

        Optional.of(2L).orElse(car.start()); // not lazy, because (car.start()) first,
                                                    // then orElse(...)
        Optional.of(2L).orElseGet(() -> car.start()); // lazy because of supplier.get()
    }

    private static class Car {
        public long start() {
            long timeMs = System.currentTimeMillis();

            try {
                // запуск машины
                Thread.sleep(5000L);
            } catch (InterruptedException ignored) {
            }

            long startTimeSec = (System.currentTimeMillis() - timeMs) / 1000L;
            System.out.println("Машина завелась за " + startTimeSec + " sec");

            return startTimeSec;
        }

        public Supplier<Long> startLazy(){
            return this::start;
        }
    }
}
