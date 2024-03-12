package org.example;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    public Calculator(Source sourceCache) {
        this.sourceCache = sourceCache;
    }

    private static Source sourceCache;

    /**
     * Check result in cache, if not present - calculate and save to cache
     * @param n - number of elements
     * @return - list of elements
     */
    @Cachable(PostgreSQLSource.class)
    public List<Integer> fibonachi(int n) {
        if (sourceCache.getData(Integer.toString(n)).isEmpty()) {
            List<Integer> result = calculateFibonachi(n);
            sourceCache.saveData(Integer.toString(n), result);
            return result;
        } else {
            return sourceCache.getData(Integer.toString(n));
        }
    }

    /**
     * Calculate fibonachi sequence
     * @param n - number of elements
     * @return - list of elements
     */
    public List<Integer> calculateFibonachi(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n should be positive");
        }
        if (n == 0) {
            return List.of();
        }
        if (n == 1) {
            return List.of(0);
        }
        if (n == 2) {
            return List.of(0, 1);
        }
        List<Integer> result = new ArrayList<>(calculateFibonachi(n - 1));
        result.add(result.getLast() + result.get(result.size() - 2));
        return result;
    }
}
