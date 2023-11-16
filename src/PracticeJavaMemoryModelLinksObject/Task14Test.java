package PracticeJavaMemoryModelLinksObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

public class Task14Test {

    @Test
    public void should_convert_date() {
        String dateTimeToNewYork = Task14.dateConverting("22-1-2015 10:15:55 AM", "Asia/Singapore", "America/New_York");
        Assertions.assertEquals(dateTimeToNewYork, "Date (America/New_York) : 21-1-2015 09:15:55 PM");

        String dateTimeToWarsaw = Task14.dateConverting("22-11-2023 10:15:55 AM", "Asia/Singapore", "Europe/Warsaw");
        Assertions.assertEquals(dateTimeToWarsaw, "Date (Europe/Warsaw) : 22-11-2023 03:15:55 AM");

        String dateTimeToGibraltar = Task14.dateConverting("22-1-2015 10:15:55 AM", "Australia/Currie", "Europe/Gibraltar");
        Assertions.assertEquals(dateTimeToGibraltar, "Date (Europe/Gibraltar) : 22-1-2015 12:15:55 AM");
    }

    @Test
    public void should_throw_error() {
        Assertions.assertThrows(DateTimeParseException.class, () -> Task14.dateConverting("22-1-2015 10:15:55", "Asia/Singapore", "America/New_York"));
    }
}
