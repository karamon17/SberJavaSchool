package org.example;
import static java.lang.Thread.sleep;

public class MainScalablePool {
    public static void main(String[] args) {
        ThreadPool threadPool = new ScalableThreadPool(2, 5);

        threadPool.start();

        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            threadPool.execute(() -> {
                System.out.println("Task " + taskId + " executed by thread " + Thread.currentThread().getName());
                try {
                    sleep(1000); // Имитация выполнения задачи
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ((ScalableThreadPool) threadPool).stop();
    }
}
