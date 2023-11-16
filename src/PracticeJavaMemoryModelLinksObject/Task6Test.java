package PracticeJavaMemoryModelLinksObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task6Test {
    @Test
    public void should_work_correctly() {
        String firstString = Task6.method("  testString;abc123abc;!testXstring;foo;bar  ");
        Assertions.assertEquals(firstString, "result join = TESTSTRING|cba123cba|Xstring and array strings size = 5");

        String secondString = Task6.method("  testString ;abc123abc ;!testXstring; foo;bar  ");
        Assertions.assertEquals(secondString, "result join = TESTSTRING |cba123cba |Xstring and array strings size = 5");

        String thirdString = Task6.method(" sfw; aj; dg; b; asw  ");
        Assertions.assertEquals(thirdString, "result join = sfw| aj| dg and array strings size = 5");

        String noChangesString = Task6.method("firstSubstring;cba123cba;testString");
        Assertions.assertEquals(noChangesString, "result join = firstSubstring|cba123cba|testString and array strings size = 3");

        String noChangesStringWith7Substrings = Task6.method("firstSubstring;cba123cba;testString;foo;bar;foo;bar");
        Assertions.assertEquals(noChangesStringWith7Substrings, "result join = firstSubstring|cba123cba|testString and array strings size = 7");
    }

    @Test
    public void should_return_empty_string_when_input_string_is_empty() {
        String emptyString = Task6.method("");
        Assertions.assertEquals(emptyString, "");
    }

    @Test
    public void should_return_empty_string_when_input_string_is_null() {
        String nullString = Task6.method(null);
        Assertions.assertEquals(nullString, "");
    }

    @Test
    public void should_return_empty_string_when_input_string_has_two_subStrings() {
        String strHasTwoSubStrings = Task6.method(" sfw;ajdgbasw  ");
        Assertions.assertEquals(strHasTwoSubStrings, "");
    }
}