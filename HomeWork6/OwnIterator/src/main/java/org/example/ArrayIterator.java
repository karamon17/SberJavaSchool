package org.example;

import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T> {

    private final T[] array;
    private int currentIndex;

    public ArrayIterator(T[] array) {
        this.array = array;
        this.currentIndex = 0;
    }

    /**
     * Возвращает true, если в массиве есть следующий элемент, иначе false.
     */
    @Override
    public boolean hasNext() {
        return currentIndex < array.length;
    }

    /**
     * Возвращает следующий элемент массива.
     * Если следующего элемента нет, бросает исключение NoSuchElementException.
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }
        return array[currentIndex++];
    }

    /**
     * Возвращает индекс текущего элемента массива.
     */
    public int getCurrentIndex() {
        return currentIndex;
    }
}
