package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ScalableThreadPool implements ThreadPool {

    public ScalableThreadPool(int minAmountOfThreads, int maxAmountOfThreads) {
        this.minAmountOfThreads = minAmountOfThreads;
        this.maxAmountOfThreads = maxAmountOfThreads;
        threads = new Thread[maxAmountOfThreads];
        tasks = new LinkedBlockingQueue<>();
        currentAmountOfThreads = new AtomicInteger(minAmountOfThreads);
    }

    private final int minAmountOfThreads;
    private final int maxAmountOfThreads;
    private final AtomicInteger currentAmountOfThreads;
    private final Thread[] threads;
    private final BlockingQueue<Runnable> tasks;
    @Override
    public void start() {
        for (int i = 0; i < minAmountOfThreads; i++) {
            threads[i] = new WorkerThread();
            threads[i].start();
        }

    }

    @Override
    public void execute(Runnable runnable) {
        tasks.offer(runnable);

        // Если нет свободных потоков и текущее количество меньше максимального, увеличиваем количество потоков
        if (tasks.size() > currentAmountOfThreads.get() && currentAmountOfThreads.get() < maxAmountOfThreads) {
            threads[currentAmountOfThreads.get()] = new WorkerThread();
            threads[currentAmountOfThreads.get()].start();
            currentAmountOfThreads.getAndIncrement();
        }

        // Если очередь пуста и текущее количество больше минимального, уменьшаем количество потоков
        if (tasks.isEmpty() && currentAmountOfThreads.get() > minAmountOfThreads) {
            tasks.offer(() -> {}); // Пустая задача для принудительного выхода из блокировки потока
            currentAmountOfThreads.getAndDecrement();
        }
    }

    private class WorkerThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Runnable task = tasks.take();
                    if (task == null) {
                        break; // Выход из потока при пустой задаче
                    }
                    task.run();
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    public void stop() {
        for (int i = 0; i < maxAmountOfThreads; i++) {
            threads[i].interrupt();
        }
    }

}
