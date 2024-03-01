package org.example;

import java.util.ArrayList;

public class GarbageCollectionExample {
    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();

        for (int i = 0; i < 100000; i++) {
            list.add(new Object());
        }

        for (Object obj : list) {
            System.out.println(obj.toString().length());
        }

        // Освобождаем ссылку на список
        list = null;

        // Запускаем сборщик мусора (можно вызвать System.gc(), но это не гарантирует немедленное выполнение)
        System.gc();

        // Задержка для наблюдения результатов в VisualVM
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

