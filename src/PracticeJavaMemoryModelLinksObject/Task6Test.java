package PracticeJavaMemoryModelLinksObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Task6Test {

    @ParameterizedTest
    @CsvSource({"  testString;abc123abc;!testXstring;foo;bar  ," +
                    "result join = TESTSTRING|cba123cba|Xstring and array strings size = 5",
            "  testString ;abc123abc ;!testXstring; foo;bar  ," +
                    "result join = TESTSTRING |cba123cba |Xstring and array strings size = 5",
            " sfw; aj; dg; b; asw  ," +
                    "result join = sfw| aj| dg and array strings size = 5",
            "firstSubstring;cba123cba;testString," +
                    "result join = firstSubstring|cba123cba|testString and array strings size = 3",
            "firstSubstring;cba123cba;testString;foo;bar;foo;bar," +
                    "result join = firstSubstring|cba123cba|testString and array strings size = 7"})
    void should_work_correctly(String input, String expected) {
        String actualValue = Task6.method(input);
        Assertions.assertEquals(expected, actualValue);
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