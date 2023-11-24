/*
Написать метод (с тестами) который,
Принимает тип действия (прибавить, вычесть), единицу измерения времени (секунда, минута, час, месяц, год, …) и кол-во.
Метод в зависимости от действия прибавляет или вычитает из текущей даты и времени переданное кол-во времени.
Полученная дата и время выводится в формате строки “23 часов, 27 минут, 22 августа 2022”
Например:
Входящие данные: “PLUS”, “YEARS”, 100
Исходящая строка: “23 часов, 27 минут, 22 августа 2123” (если текущее время будет 2023-08-22 23:27)
 */
package PracticeJavaMemoryModelLinksObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Task13 {
    public static String changeDate(String action, ChronoUnit unit, int count, LocalDateTime... ldt) {
        LocalDateTime date;
        if (ldt.length > 0) {
            date = ldt[0];
        } else {
            date = LocalDateTime.now();
        }
        if (action.equals("PLUS")) {
            date = date.plus(count, unit);
        } else if (action.equals("MINUS")) {
            date = date.minus(count, unit);
        } else {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH часов mm минут, dd MMMM yyyy");
        return date.format(formatter);
    }
}
