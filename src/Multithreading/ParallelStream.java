package Multithreading;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Не запуская код объяснить какой stream выполнится быстрее.
 * Затем запустить и проверить себя, если объяснение было неверно, то разобраться в чём ошибка.
 * <p>
 * Я считаю, что быстрее выполнится последовательный стрим, т.к. numbers является List-ом Integer-ов
 * Integer является объектов. Параллельный стрим медленно работает с не примитивами.
 * <p>
 * Из теории. Чем больше указателей в нашей структуре данных, тем больше нагрузки мы оказываем
 * на память для извлечения ссылочных объектов. Это может отрицательно повлиять на распараллеливание,
 * поскольку несколько ядер одновременно извлекают данные из памяти.
 * <p>
 * Однако, меня настораживает большой объем данных. Но учитывая тот факт, что не производится
 * никаких вычислений над элементами (n++ не изменяет элемент, т.к. используется постфиксный инкремент,
 * а не префиксный), могу сделать вывод, что последовательный стрим всё-таки выполнится быстрее.
 * + Параллельный стрим тратит много времени и ресурсов на распараллеливание и сборку конечного результата.
 */
public class ParallelStream {
    public static void main(String[] args) {
        var numbers = IntStream.range(0, 1_000_000)
                .boxed()
                .collect(Collectors.toList());

        var start = System.currentTimeMillis();

        numbers.stream()
                .map(n -> n++)
                .collect(Collectors.toList());

        System.out.println("stream: = " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();

        numbers.parallelStream()
                .map(n -> n++)
                .collect(Collectors.toList());

        System.out.println("parallel stream: = " + (System.currentTimeMillis() - start) + " ms");
    }
}
