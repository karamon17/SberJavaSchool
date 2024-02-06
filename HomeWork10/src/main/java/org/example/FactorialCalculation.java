package org.example;

import java.math.BigInteger;

public class FactorialCalculation implements Runnable {

    private final int n;
    private final byte count;

    public FactorialCalculation(int n, byte count) {
        this.n = n;
        this.count = count;
    }

    /**
     * Выводит в консоль факториал числа n
     */
    @Override
    public void run() {
        System.out.println("Thread " + count + " started");
        System.out.println("Факториал числа " + n + ": " + calculateFactorial());
    }

    /**
     * @return - возвращает факториал числа n
     */
    public BigInteger calculateFactorial() {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
