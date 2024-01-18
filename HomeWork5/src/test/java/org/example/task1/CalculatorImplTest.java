package org.example.task1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorImplTest {
    Calculator calculator = new CalculatorImpl();

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 6})
    void testCalcFactorial(int input) {

        int result = calculator.calc(input);

        switch (input) {
            case 0:
            case 1:
                assertEquals(1, result);
                break;
            case 2:
                assertEquals(2, result);
                break;
            case 3:
                assertEquals(6, result);
                break;
            case 4:
                assertEquals(24, result);
                break;
            case 6:
                assertEquals(720, result);
                break;
        }
    }

    @Test
    void testCalcFactorialIfNegativeInputThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calc(-5);
        });
    }
}