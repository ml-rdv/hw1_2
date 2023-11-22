package PracticeJavaMemoryModelLinksObject;

import java.time.LocalDateTime;
import java.time.Month;

/**
 * Написать метод (с тестами) который,
 * Принимает тип действия (прибавить, вычесть), единицу измерения времени (секунда, минута, час, месяц, год, …) и кол-во.
 * Метод в зависимости от действия прибавляет или вычитает из текущей даты и времени переданное кол-во времени.
 * Полученная дата и время выводится в формате строки “23 часов, 27 минут, 22 августа 2022”
 * Например:
 * Входящие данные: “PLUS”, “YEARS”, 100
 * Исходящая строка: “23 часов, 27 минут, 22 августа 2123” (если текущее время будет 2023-08-22 23:27)
 */
public class Task13 {
    public static String changeDate(String action, String unit, int count) {
        LocalDateTime date = LocalDateTime.now();
        if (action.equals("PLUS")) {
            switch (unit) {
                case "SECONDS" -> date = date.plusSeconds(count);
                case "MINUTES" -> date = date.plusMinutes(count);
                case "HOURS" -> date = date.plusHours(count);
                case "DAYS" -> date = date.plusDays(count);
                case "MONTHS" -> date = date.plusMonths(count);
                case "YEARS" -> date = date.plusYears(count);
            }
        } else if (action.equals("MINUS")) {
            switch (unit) {
                case "SECONDS" -> date = date.minusSeconds(count);
                case "MINUTES" -> date = date.minusMinutes(count);
                case "HOURS" -> date = date.minusHours(count);
                case "DAYS" -> date = date.minusDays(count);
                case "MONTHS" -> date = date.minusMonths(count);
                case "YEARS" -> date = date.minusYears(count);
            }
        } else {
            return "";
        }
        int minutes = date.getMinute();
        int hours = date.getHour();
        int dayOfMonth = date.getDayOfMonth();
        int year = date.getYear();
        Month month = date.getMonth();
        String result = (hours < 10 ? "0" + hours : hours) + " часов " + (minutes < 10 ? "0" + minutes : minutes) + " минут, "
                + dayOfMonth + ' ' + month + ' ' + year;
        return result;
    }
}
