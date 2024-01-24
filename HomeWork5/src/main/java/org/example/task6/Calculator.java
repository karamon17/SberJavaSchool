package org.example.task6;

public interface Calculator {
    /**
     * Вычисляет факториал числа
     * Бросает исключение IllegalArgumentException, если число меньше 0
     * @param number число, для которого вычисляется факториал
     * @return факториал числа
     */
    @Metric
    int calc(int number);
}
