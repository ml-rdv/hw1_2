package FunctionalProgramming.Car;

public class Main {
    public static void main(String[] args) {
        Car car = new Car();

        // not lazy!!!
        car.start();
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
    }
}
