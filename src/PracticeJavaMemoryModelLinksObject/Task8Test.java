package PracticeJavaMemoryModelLinksObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task8Test {

    @Test
    public void should_return_FullName(){
        // ????????
        String fullName = Task8.stringDefinition("Иванов Иван Иванович");
        Assertions.assertEquals(fullName, "FullName");
    }

    @Test
    public void should_return_Email(){
        String email1 = Task8.stringDefinition("test@test.com");
        Assertions.assertEquals(email1, "Email");

        String email2 = Task8.stringDefinition("name.surname111@test.ru");
        Assertions.assertEquals(email2, "Email");
    }

    @Test
    public void should_return_PhoneNumber(){
        String phoneNumber = Task8.stringDefinition("+7(999)9999999");
        Assertions.assertEquals(phoneNumber, "PhoneNumber");
    }

    @Test
    public void should_return_Number(){
        String number = Task8.stringDefinition("0123456");
        Assertions.assertEquals(number, "Number");
    }

    @Test
    public void should_return_Other() {
        String str = Task7.stringDefinition("");
        Assertions.assertEquals(str, "Other");

        String str2 = Task7.stringDefinition("Иванов 1Иван Иванович");
        Assertions.assertEquals(str2, "Other");

        String str3 = Task7.stringDefinition("test@@@test.ru");
        Assertions.assertEquals(str3, "Other");

        String str4 = Task7.stringDefinition("0123456sdf");
        Assertions.assertEquals(str4, "Other");
    }
}
