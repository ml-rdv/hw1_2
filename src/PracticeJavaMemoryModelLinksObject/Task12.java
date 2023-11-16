/*
Написать метод (с тестами) который,
Принимает строку в формате "yyyy-MM-dd HH:mm" и преобразовывает её в формат даты и времени (использовать DateTimeFormatter.ofPattern())
Метод должен вернуть строку:
“Before”, если входящая дата до текущей даты и времени.
“After”, если входящая дата после текущей даты и времени.
“Equal”, если даты совпадают (Сравнить только по дате, время игнорировать)
 */
package PracticeJavaMemoryModelLinksObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;

public class Task12 {
    public static String dateCheck(String dateInString) {
        LocalDateTime nowWithTime = LocalDateTime.now();
        LocalDate nowWithoutTime = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime parsedDateTime = LocalDateTime.parse(dateInString, formatter);


        if (nowWithoutTime.isEqual(ChronoLocalDate.from(parsedDateTime))) {
            return "Equal";
        } else if (parsedDateTime.isBefore(nowWithTime)) {
            return "Before";
        } else if (parsedDateTime.isAfter(nowWithTime)) {
            return "After";
        }
        return "";
    }
}
