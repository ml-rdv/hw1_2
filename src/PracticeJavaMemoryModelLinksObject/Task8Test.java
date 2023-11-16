package PracticeJavaMemoryModelLinksObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class Task8Test {

    @Test
    public void should_return_FullName() {
        String fullName = Task8.stringDefinition("Иванов Иван Иванович");
        Assertions.assertEquals(fullName, "FullName");
    }

    @ParameterizedTest
    @ValueSource(strings = {"test@test.ru", "name.surname111@test.ru", "d@."})
    public void should_return_Email(String email) {
        String email1 = Task8.stringDefinition(email);
        Assertions.assertEquals(email1, "Email");
    }

    @Test
    public void should_return_PhoneNumber() {
        String phoneNumber = Task8.stringDefinition("+7(999)9999999");
        Assertions.assertEquals(phoneNumber, "PhoneNumber");
    }

    @Test
    public void should_return_Number() {
        String number = Task8.stringDefinition("0123456");
        Assertions.assertEquals(number, "Number");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "Иванов 1Иван Иванович", "test@@@test.ru", "0123456sdf"})
    public void should_return_Other(String other) {
        String other1 = Task8.stringDefinition(other);
        Assertions.assertEquals(other1, "Other");
    }
}
