package org.example.task5;

public interface DataService {
    /**
     * Возвращает данные по ключу
     * @param key ключ
     * @return данные в виде строки
     */
    String fetchData(String key);
}
