package ex02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    private final Map<String, List<String>> map = new HashMap<>();

    public void add(String surname, String phone_number) {
        //Метод computeIfAbsent позволяет избежать проверки наличия ключа.
        // Если ключ отсутствует, он создаст новое значение с использованием предоставленной функции.
        map.computeIfAbsent(surname, k -> new ArrayList<>()).add(phone_number);
    }

    public List<String> get(String surname) {
        return map.get(surname);
    }
}
