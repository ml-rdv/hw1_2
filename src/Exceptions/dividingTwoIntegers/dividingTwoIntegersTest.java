package Exceptions.dividingTwoIntegers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class dividingTwoIntegersTest {
    @Test
    void should_throw_exception_ArithmeticException_when_we_divide_by_zero() {
        Assertions.assertThrows(ArithmeticException.class, () -> dividingTwoIntegers.dividing(8, 0));
    }

    @Test
    void should_divide_two_integers() {
        var expectedElement = dividingTwoIntegers.dividing(8, 2);
        Assertions.assertEquals(expectedElement, 4);
    }
}
