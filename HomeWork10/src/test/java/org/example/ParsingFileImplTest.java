package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParsingFileImplTest {

    @Test
    void testFileToStringIfUseCorrectPathExpectNotNull() throws IOException {
        ParsingFile parsingFile = new ParsingFileImpl();
        String path = System.getProperty("user.dir") + "/src/main/resources/numbers.txt";
        String result = parsingFile.fileToString(path);
        assertNotNull(result);
    }

    @Test
    void testFileToStringIfUseIncorrectPathExpectRuntimeException() {
        ParsingFile parsingFile = new ParsingFileImpl();
        String path = System.getProperty("user.dir") + "/src/main/resources/numbes.txt";
        assertThrows(RuntimeException.class, () -> {
            parsingFile.fileToString(path);
        });
    }

    @Test
    void testStringToIntegerArrayIfUseCorrectString() {
        ParsingFile parsingFile = new ParsingFileImpl();
        String string = "1\n2\n3\n4\n5\n6\n7\n8\n9\n10";
        Integer[] result = parsingFile.stringToIntegerArray(string);
        Integer[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertArrayEquals(expected, result);
    }

    @Test
    void testStringToIntegerArrayIfUseSpaceInsteadNewLineExpectIllegalArgumentException() {
        ParsingFile parsingFile = new ParsingFileImpl();
        String string = "1 2 3 4 5 6 7 8 9 10";
        assertThrows(IllegalArgumentException.class, () -> {
            parsingFile.stringToIntegerArray(string);
        });
    }

    @Test
    void testStringToIntegerArrayIfUseCharExpectIllegalArgumentException() {
        ParsingFile parsingFile = new ParsingFileImpl();
        String string = "a\nb\nc";
        assertThrows(IllegalArgumentException.class, () -> {
            parsingFile.stringToIntegerArray(string);
        });
    }

    @Test
    void testStringToIntegerArrayIfUseNegativeNumberExpectIllegalArgumentException() {
        ParsingFile parsingFile = new ParsingFileImpl();
        String string = "1\n-2\n3";
        assertThrows(IllegalArgumentException.class, () -> {
            parsingFile.stringToIntegerArray(string);
        });
    }

    @Test
    void testStringToIntegerArrayIfUseBiggerNumberThen50ExpectIllegalArgumentException() {
        ParsingFile parsingFile = new ParsingFileImpl();
        String string = "1\n2\n3\n51\n5\n6\n7\n8\n9\n10";
        assertThrows(IllegalArgumentException.class, () -> {
            parsingFile.stringToIntegerArray(string);
        });
    }

    @Test
    void testStringToIntegerArrayIfUseZeroExpectIllegalArgumentException() {
        ParsingFile parsingFile = new ParsingFileImpl();
        String string = "1\n2\n3\n0\n5\n6\n7\n8\n9\n10";
        assertThrows(IllegalArgumentException.class, () -> {
            parsingFile.stringToIntegerArray(string);
        });
    }

    @Test
    void testStringToIntegerArrayIfEmptyFileExpectRuntimeException() {
        ParsingFile parsingFile = new ParsingFileImpl();
        String string = "";
        assertThrows(RuntimeException.class, () -> {
            parsingFile.stringToIntegerArray(string);
        });
    }
}