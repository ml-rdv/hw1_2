package PracticeJavaMemoryModelLinksObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;

public class Task4Test {
    @Test
    public void should_compare_objects_incorrectly() {
        Task4 check1 = new Task4(1, Date.valueOf("1988-09-29"), 200.3);
        Task4 check2 = new Task4(1, Date.valueOf("1988-09-29"), 200.3);
        Assertions.assertNotSame(check1, check2);
    }

    @Test
    public void should_compare_objects_by_equals() {
        Task4 check1 = new Task4(1, Date.valueOf("1988-09-29"), 200.3);
        Task4 check2 = new Task4(1, Date.valueOf("1988-09-29"), 200.3);
        Assertions.assertEquals(check1, check2);
    }

}
