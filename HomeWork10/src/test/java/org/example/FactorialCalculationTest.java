package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class FactorialCalculationTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 6})
    void testCalculateFactorial(int input) {
        FactorialCalculation factorialCalculation = new FactorialCalculation(input, (byte) 1);
        BigInteger result = factorialCalculation.calculateFactorial();

        switch (input) {
            case 0:
            case 1:
                assertEquals(BigInteger.valueOf(1), result);
                break;
            case 2:
                assertEquals(BigInteger.valueOf(2), result);
                break;
            case 3:
                assertEquals(BigInteger.valueOf(6), result);
                break;
            case 4:
                assertEquals(BigInteger.valueOf(24), result);
                break;
            case 6:
                assertEquals(BigInteger.valueOf(720), result);
                break;
        }
    }
}