package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        Map<String, String> m = Streams.of(list)
                .filter(o -> o.equals("a") || o.equals("b"))
                .transform(o -> o + "new")
                .toMap(String::toUpperCase, String::toLowerCase);
        System.out.println(m);
    }
}
