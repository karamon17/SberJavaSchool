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

    /**
     * @param predicate - condition for filtering
     * @return new Streams object with filtered list
     */
    public Streams<T> filter(Predicate<? super T> predicate) {
        list.removeIf(predicate.negate());
        return this;
    }

    /**
     * @param transformFunction - function for transforming
     * @param <R>               - type of transformed object
     * @return new Streams object with transformed list
     */
    public <R> Streams<T> transform(Function<? super T, ? extends R> transformFunction) {
        list.replaceAll(t -> (T) transformFunction.apply(t));
        return this;
    }

    /**
     * @param keyFunction   - function for getting key
     * @param valueFunction - function for getting value
     * @param <K>           - type of key
     * @param <V>           - type of value
     * @return map with key and value
     */
    public <K, V> Map<K, V> toMap(Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends V> valueFunction) {
        Map<K, V> map = new HashMap<>();
        list.forEach(o -> map.put(keyFunction.apply(o), valueFunction.apply(o)));
        return map;
    }

    public List<T> toList() {
        return list;
    }
}
