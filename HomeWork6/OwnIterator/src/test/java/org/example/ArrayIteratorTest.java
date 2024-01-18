package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayIteratorTest {
    String[] strings = {"Hello", "World", "It,s", "new", "day"};

    @Test
    void testHasNextAfterCreatingNewIteratorWith5LengthOfArrayExpectTrue() {
        ArrayIterator<String> iterator = new ArrayIterator<>(strings);
        assertTrue(iterator.hasNext());
    }

    @Test
    void testHasNextAfterMovingToFinalElementExpectFalse() {
        ArrayIterator<String> iterator = new ArrayIterator<>(strings);
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    void testNextAfterCreatingNewIteratorWithFirstElementHelloExpectStringHello() {
        ArrayIterator<String> iterator = new ArrayIterator<>(strings);
        assertEquals("Hello", iterator.next());
    }

    @Test
    void testNextAfterMovingToFinalElementExpectNoSuchElementException() {
        ArrayIterator<String> iterator = new ArrayIterator<>(strings);
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        assertThrows(java.util.NoSuchElementException.class, () -> {
            iterator.next();
        });
    }

    @Test
    void testGetCurrentIndexAfterCreatingNewIteratorExpectZero() {
        ArrayIterator<String> iterator = new ArrayIterator<>(strings);
        assertEquals(0, iterator.getCurrentIndex());
    }

    @Test
    void testGetCurrentIndexAfterMovingToFinalElementExpect5() {
        ArrayIterator<String> iterator = new ArrayIterator<>(strings);
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        assertEquals(5, iterator.getCurrentIndex());
    }
}