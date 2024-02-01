package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import static java.lang.System.exit;

public class ParsingFileImpl implements ParsingFile {
    @Override
    public String fileToString(String path) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Файл не найден " + e.getMessage());
        }

        return content.toString();

    }

    @Override
    public Integer[] stringToIntegerArray(String string) {
        if (string.isEmpty()) {
            throw new RuntimeException("Файл пуст");
        }
        String[] stringArray = string.split("\n");
        Integer[] integerArray = new Integer[stringArray.length];

        for (int i = 0; i < stringArray.length; i++) {
            try {
                integerArray[i] = Integer.parseInt(stringArray[i]);
            }
            catch (NumberFormatException e) {
                throw new IllegalArgumentException("В файле должны быть только числа, разделенные переносом строки " + e.getMessage());
            }
            if (integerArray[i] < 1 || integerArray[i] > 50) {
                throw new IllegalArgumentException("Каждое число должно быть больше 0 и меньше 51." +
                        " Найдено число: " + integerArray[i] + " в строке " + (i + 1));
            }
        }
        return integerArray;
    }
}
