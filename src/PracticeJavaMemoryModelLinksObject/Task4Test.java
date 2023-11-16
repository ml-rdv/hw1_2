package PracticeJavaMemoryModelLinksObject;

import InternetCafe.Check;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;

public class Task4Test {

    // Сравнение с помощью "=="
    @Test
    public void should_compare_objects_incorrectly() {
        Check check1 = new Check(1, Date.valueOf("1988-09-29"), 200.3);
        Check check2 = new Check(1, Date.valueOf("1988-09-29"), 200.3);
        Assertions.assertNotSame(check1, check2);
    }

    // Сравнение с помощью equals
    @Test
    public void should_compare_objects_by_equals() {
        Check check1 = new Check(1, Date.valueOf("1988-09-29"), 200.3);
        Check check2 = new Check(1, Date.valueOf("1988-09-29"), 200.3);
        Assertions.assertEquals(check1, check2);
    }

    // Сравнение с помощью equals
    @Test
    public void should_return_false_compare_objects_by_equals() {
        Check check1 = new Check(1, Date.valueOf("1979-09-29"), 200.3);
        Check check2 = new Check(1, Date.valueOf("1988-09-29"), 200.3);
        Assertions.assertNotEquals(check1, check2);
    }
}
