package PracticeJavaMemoryModelLinksObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task6Test {
    @Test
    public void should_work_correctly(){
        String str = Task6.method("  testString;abc123abc;!testXstring;foo;bar  ");
        Assertions.assertEquals(str, "result join = TESTSTRING|cba123cba|Xstring and array strings size = 5");
    }

    @Test
    public void should_return_empty_string(){
        String str = Task6.method("");
        Assertions.assertEquals(str, "");

        String str2 = null;
        Assertions.assertEquals(str, "");

        String str3 = " sfw;ajdgbasw  ";
        Assertions.assertEquals(str, "");
    }
}