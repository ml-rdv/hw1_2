package PracticeJavaMemoryModelLinksObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task13Test {
    @Test
    public void should_return_date_with_added_years() {
        String date = Task13.changeDate("PLUS", "YEARS", 100);
        Assertions.assertEquals(date, "20 часов 06 минут, 15 NOVEMBER 2123");
    }

    @Test
    public void should_return_date_with_added_months() {
        String date = Task13.changeDate("PLUS", "MONTHS", 100);
        Assertions.assertEquals(date, "20 часов 06 минут, 15 MARCH 2032");
    }

    @Test
    public void should_return_date_with_added_hours() {
        String date = Task13.changeDate("PLUS", "HOURS", 1000);
        Assertions.assertEquals(date, "12 часов 06 минут, 27 DECEMBER 2023");
    }

    @Test
    public void should_return_date_with_minus_years() {
        String date = Task13.changeDate("MINUS", "YEARS", 100);
        Assertions.assertEquals(date, "20 часов 06 минут, 15 NOVEMBER 1923");
    }

    @Test
    public void should_return_date_with_minus_months() {
        String date = Task13.changeDate("MINUS", "MONTHS", 100);
        Assertions.assertEquals(date, "20 часов 06 минут, 15 JULY 2015");
    }

    @Test
    public void should_return_date_with_minus_hours() {
        String date = Task13.changeDate("MINUS", "HOURS", 100);
        Assertions.assertEquals(date, "16 часов 06 минут, 11 NOVEMBER 2023");
    }

}
