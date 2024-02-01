package org.example;

import java.io.IOException;

public interface ParsingFile {

    /**
     * @param path - путь к файлу
     * @return - возвращает содержимое файла в виде строки
     * @throws IOException - если файл не найден
     */
    String fileToString(String path) throws IOException;

    /**
     * @param string - строка, которую нужно преобразовать в массив чисел
     * @return - возвращает массив чисел
     */
    Integer[] stringToIntegerArray(String string);
}
