package org.example.task1;

public interface Calculator{
    /**
     * Расчет факториала числа.
     * Бросает исключение IllegalArgumentException, если число меньше 0.
     * @param number
     */
    int calc (int number);
}