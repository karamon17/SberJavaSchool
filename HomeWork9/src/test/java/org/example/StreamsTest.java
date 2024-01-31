package org.example;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StreamsTest {

    @org.junit.jupiter.api.Test
    void testFilterAreThere_a_or_b_Expect_a_and_b() {
        List<String> list = List.of("a", "b", "c", "d", "e");
        Streams<String> streams = Streams.of(list);
        Streams<String> filtered = streams.filter(o -> o.equals("a") || o.equals("b"));
        assertEquals(List.of("a", "b"), filtered.toList());
    }

    @org.junit.jupiter.api.Test
    void testTransformConcatenateNewToEachStringExpectConcatenated() {
        List<String> list = List.of("a", "b", "c", "d", "e");
        Streams<String> streams = Streams.of(list);
        Streams<String> transformed = streams.transform(o -> o + "new");
        assertEquals(List.of("anew", "bnew", "cnew", "dnew", "enew"), transformed.toList());
    }

    @org.junit.jupiter.api.Test
    void testToMapCreatePairsStringAndLengthOfStringExpectMapWithCorrectPairs() {
        List<String> list = List.of("a", "b", "c", "d", "e");
        Streams<String> streams = Streams.of(list);
        Map<String, Integer> wordWithLength = streams.toMap(o -> o, String::length);
        assertEquals(Map.of("a", 1, "b", 1, "c", 1, "d", 1, "e", 1), wordWithLength);
    }
}