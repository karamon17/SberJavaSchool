package org.example;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class Streams<T> {
    private final List<T> list;

    public Streams(List<T> list) {
        this.list = new ArrayList<>(list);
    }

    public static <T> Streams<T> of(List<T> list) {
        return new Streams<>(list);
    }

    public Streams<T> filter(Predicate<T> predicate) {
        list.removeIf(predicate.negate());
        return this;
    }

    public <R> Streams<T> transform(Function<T, R> transformFunction) {
        list.replaceAll(t -> (T) transformFunction.apply(t));
        return this;
    }

    public <K, V> Map<K, V> toMap(Function<T, K> keyFunction, Function<T, V> valueFunction) {
        Map<K, V> map = new HashMap<>();
        list.forEach(o -> map.put(keyFunction.apply(o), valueFunction.apply(o)));
        return map;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        Map<String, String> m = Streams.of(list)
                .filter(o -> o.equals("a") || o.equals("b"))
                .transform(o -> o + "new")
                .toMap(String::toUpperCase, String::toLowerCase);
        System.out.println(m);
    }
}
