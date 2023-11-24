package PracticeJavaMemoryModelLinksObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class Task13Test {
    @Test
    public void should_return_date_with_added_years() {
        LocalDateTime date = LocalDateTime.of(2023, Month.NOVEMBER, 15, 20, 6);
        String dateResult = Task13.changeDate("PLUS", ChronoUnit.YEARS, 100, date);
        Assertions.assertEquals(dateResult, "20 часов 06 минут, 15 ноября 2123");
    }

    @Test
    public void should_return_date_with_added_months() {
        LocalDateTime date = LocalDateTime.of(2023, Month.NOVEMBER, 15, 20, 6);
        String dateResult = Task13.changeDate("PLUS", ChronoUnit.MONTHS, 100, date);
        Assertions.assertEquals(dateResult, "20 часов 06 минут, 15 марта 2032");
    }

    @Test
    public void should_return_date_with_added_hours() {
        LocalDateTime date = LocalDateTime.of(2023, Month.NOVEMBER, 15, 20, 6);
        String dateResult = Task13.changeDate("PLUS", ChronoUnit.HOURS, 1000, date);
        Assertions.assertEquals(dateResult, "12 часов 06 минут, 27 декабря 2023");
    }

    @Test
    public void should_return_date_with_minus_years() {
        LocalDateTime date = LocalDateTime.of(2023, Month.NOVEMBER, 15, 20, 6);
        String dateResult = Task13.changeDate("MINUS", ChronoUnit.YEARS, 100, date);
        Assertions.assertEquals(dateResult, "20 часов 06 минут, 15 ноября 1923");
    }

    @Test
    public void should_return_date_with_minus_months() {
        LocalDateTime date = LocalDateTime.of(2023, Month.NOVEMBER, 15, 20, 6);
        String dateResult = Task13.changeDate("MINUS", ChronoUnit.MONTHS, 100, date);
        Assertions.assertEquals(dateResult, "20 часов 06 минут, 15 июля 2015");
    }

    @Test
    public void should_return_date_with_minus_hours() {
        LocalDateTime date = LocalDateTime.of(2023, Month.NOVEMBER, 15, 20, 6);
        String dateResult = Task13.changeDate("MINUS", ChronoUnit.HOURS, 100, date);
        Assertions.assertEquals(dateResult, "16 часов 06 минут, 11 ноября 2023");
    }

}
