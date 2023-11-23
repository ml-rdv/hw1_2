package PracticeJavaMemoryModelLinksObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task13Test {
    @Test
    public void should_return_date_with_added_years() {
        String dateResult = Task13.changeDate("PLUS", "YEARS", 100);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH часов mm минут, dd MMMM yyyy");
        LocalDateTime date = LocalDateTime.now();
        date = date.plusYears(100);
        String formattedDateTime = date.format(formatter);
        Assertions.assertEquals(dateResult, formattedDateTime);
    }

    @Test
    public void should_return_date_with_added_months() {
        String dateResult = Task13.changeDate("PLUS", "MONTHS", 100);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH часов mm минут, dd MMMM yyyy");
        LocalDateTime date = LocalDateTime.now();
        date = date.plusMonths(100);
        String formattedDateTime = date.format(formatter);
        Assertions.assertEquals(dateResult, formattedDateTime);
    }

    @Test
    public void should_return_date_with_added_hours() {
        String dateResult = Task13.changeDate("PLUS", "HOURS", 1000);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH часов mm минут, dd MMMM yyyy");
        LocalDateTime date = LocalDateTime.now();
        date = date.plusHours(1000);
        String formattedDateTime = date.format(formatter);
        Assertions.assertEquals(dateResult, formattedDateTime);
    }

    @Test
    public void should_return_date_with_minus_years() {
        String dateResult = Task13.changeDate("MINUS", "YEARS", 100);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH часов mm минут, dd MMMM yyyy");
        LocalDateTime date = LocalDateTime.now();
        date = date.minusYears(100);
        String formattedDateTime = date.format(formatter);
        Assertions.assertEquals(dateResult, formattedDateTime);
    }

    @Test
    public void should_return_date_with_minus_months() {
        String dateResult = Task13.changeDate("MINUS", "MONTHS", 100);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH часов mm минут, dd MMMM yyyy");
        LocalDateTime date = LocalDateTime.now();
        date = date.minusMonths(100);
        String formattedDateTime = date.format(formatter);
        Assertions.assertEquals(dateResult, formattedDateTime);
    }

    @Test
    public void should_return_date_with_minus_hours() {
        String dateResult = Task13.changeDate("MINUS", "HOURS", 100);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH часов mm минут, dd MMMM yyyy");
        LocalDateTime date = LocalDateTime.now();
        date = date.minusHours(100);
        String formattedDateTime = date.format(formatter);
        Assertions.assertEquals(dateResult, formattedDateTime);
    }

}
