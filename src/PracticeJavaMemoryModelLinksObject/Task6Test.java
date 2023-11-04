package PracticeJavaMemoryModelLinksObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task6Test {
    @Test
    public void should_work_correctly(){
        String firstString = Task6.method("  testString;abc123abc;!testXstring;foo;bar  ");
        Assertions.assertEquals(firstString, "result join = TESTSTRING|cba123cba|Xstring and array strings size = 5");

        String secondString = Task6.method("  testString ;abc123abc ;!testXstring; foo;bar  ");
        Assertions.assertEquals(secondString, "result join = TESTSTRING |cba123cba |Xstring and array strings size = 5");

        String thirdString = Task6.method(" sfw; aj; dg; b; asw  ");
        Assertions.assertEquals(thirdString, "result join = sfw| aj| dg and array strings size = 5");
    }

    @Test
    public void should_return_empty_string(){
        String emptyString = Task6.method("");
        Assertions.assertEquals(emptyString, "");

        String nullString = Task6.method(null);
        Assertions.assertEquals(nullString, "");

        String strHasTwoSubStrings = Task6.method(" sfw;ajdgbasw  ");
        Assertions.assertEquals(strHasTwoSubStrings, "");
    }
}