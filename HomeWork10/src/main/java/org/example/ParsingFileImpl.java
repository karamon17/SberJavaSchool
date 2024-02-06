package org.example;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.io.IOException;

public class ParsingFileImpl implements ParsingFile {
    @Override
    public String fileToString(String path) {
        StringBuilder content = new StringBuilder();

        try (FileChannel channel = FileChannel.open(Path.of(path), StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (channel.read(buffer) > 0) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    content.append((char) buffer.get());
                }
                buffer.clear();
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
