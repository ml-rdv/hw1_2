package PracticeJavaMemoryModelLinksObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task10Test {

    @Test
    public void should_return_Number(){
        Number number = Task10.stringConversion("2.1", "1");
        Assertions.assertEquals(number, 3.1);
    }

    @Test
    public void should_return_Error(){
        Assertions.assertThrows(NumberFormatException.class, () -> {
            var number = Task10.stringConversion("aa2.1", "1");
        });
    }
}
