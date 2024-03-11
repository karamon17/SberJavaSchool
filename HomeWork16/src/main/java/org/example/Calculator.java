package org.example;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    @Cachable(PosgreSQLSource.class)
    public List<Integer> fibonachi(int n) {
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
        List<Integer> result = new ArrayList<>(fibonachi(n - 1));
        result.add(result.getLast() + result.get(result.size() - 2));
        return result;
    }
}
