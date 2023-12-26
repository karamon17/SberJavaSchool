package ex02;

import java.util.*;

public class PhoneBook {
    private final Map<String, Set<String>> map = new HashMap<>();

    public void add(String surname, String phone_number) {
        //Метод computeIfAbsent позволяет избежать проверки наличия ключа.
        // Если ключ отсутствует, он создаст новое значение с использованием предоставленной функции.
        map.computeIfAbsent(surname, k -> new HashSet<>()).add(phone_number);
    }

    public Set<String> get(String surname) {
        Set<String> res;
        res = map.containsKey(surname) ?  map.get(surname) : new HashSet<>();
        return res;
    }
}
