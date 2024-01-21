package Exceptions.dividingTwoDoubles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class dividingTwoDoublesTest {
    @Test
    void should_throw_exception_ArithmeticException_when_we_divide_by_zero() {
        Assertions.assertThrows(DividerIsNull.class, () -> dividingTwoDoubles.dividing(8.0, 0.0));
    }

    @Test
    void should_throw_exception_IllegalArgumentException_when_we_divide_by_null() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dividingTwoDoubles.dividing(8.0, null));
    }

    @Test
    void should_divide_two_doubles() throws DividerIsNull {
        var expectedElement = dividingTwoDoubles.dividing(8.0, 2.0);
        Assertions.assertEquals(expectedElement, 4.0);
    }
}
