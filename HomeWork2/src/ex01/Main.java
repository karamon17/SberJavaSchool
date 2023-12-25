package ex01;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String[] words = {
                "One", "Two", "One", "Two", "Tree", "Four", "Five", "Six", "Six",
                "Seven", "Eight", "Nine", "Nine", "Nine", "Nine", "Ten", "Eleven",
                "Twelve", "Nine", "One"
        };
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        System.out.println("Unique Words:\n" + map.keySet() + "\n");
        System.out.println("Count of duplicates:\n" + map);
    }
}
