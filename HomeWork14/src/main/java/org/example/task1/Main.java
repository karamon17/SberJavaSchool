package org.example.task1;

import java.util.concurrent.Callable;

public class Main {
    public static void main(String[] args) {
        Callable<String> callable = () -> {
            Thread.sleep(3000);
            return "Task result";
        };

        Task<String> task = new Task<>(callable);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                String result = task.get();
                System.out.println("Thread: " + Thread.currentThread().getId() + ", Result: " + result);
            }).start();
        }
    }
}
