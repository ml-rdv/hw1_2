package PracticeJavaMemoryModelLinksObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class Task12Test {

    @Test
    public void should_return_Equal() {
        String today = Task12.dateCheck("2023-11-15 18:00");
        Assertions.assertEquals(today, "Equal");
    }

    @ParameterizedTest
    @ValueSource(strings = {"2023-11-14 18:00", "2020-04-15 17:45"})
    public void should_return_Before(String date) {
        String comparison = Task12.dateCheck(date);
        Assertions.assertEquals(comparison, "Before");
    }

    @ParameterizedTest
    @ValueSource(strings = {"2023-11-28 18:00", "2027-04-15 17:45"})
    public void should_return_After(String date) {
        String comparison = Task12.dateCheck(date);
        Assertions.assertEquals(comparison, "After");
    }
}
