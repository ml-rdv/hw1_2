/*
Написать метод (с тестами) который,
Принимает дату и время с часовым поясом
Метод должен сконвертировать дату и время к часовому поясу
Метод возвращает дату и время в новом часовом поясе
 */
package PracticeJavaMemoryModelLinksObject;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Task14 {

    public static String dateConverting(String dateInString, String timeZoneFrom, String timeZoneTo) {
        String DATE_FORMAT = "dd-M-yyyy hh:mm:ss a";
        LocalDateTime ldt = LocalDateTime.parse(dateInString, DateTimeFormatter.ofPattern(DATE_FORMAT));

        // Часовой пояс timeZoneFrom с его "правилами" в формате ZoneId
        ZoneId zoneIdFrom = ZoneId.of(timeZoneFrom);

        // Дата с часовым поясом timeZoneFrom и его "правилами"
        ZonedDateTime zonedDateTimeFrom = ldt.atZone(zoneIdFrom);

        // Часовой пояс timeZoneTo с его "правилами" в формате ZoneId
        ZoneId zoneIdTo = ZoneId.of(timeZoneTo);

        // Дата с часовым поясом timeZoneTo и его "правилами"
        ZonedDateTime dateTimeTo = zonedDateTimeFrom.withZoneSameInstant(zoneIdTo);

        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
        // Преобразованная дата с помощью DateTimeFormatter
        return "Date (" + timeZoneTo + ") : " + format.format(dateTimeTo);
    }
}