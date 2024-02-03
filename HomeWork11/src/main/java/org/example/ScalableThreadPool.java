package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ScalableThreadPool implements ThreadPool {

    public ScalableThreadPool(int MinAmountOfThreads, int MaxAmountOfThreads) {
        this.MinAmountOfThreads = MinAmountOfThreads;
        this.MaxAmountOfThreads = MaxAmountOfThreads;
        threads = new Thread[MaxAmountOfThreads];
        tasks = new LinkedBlockingQueue<>();
    }

    private final int MinAmountOfThreads;
    private final int MaxAmountOfThreads;
    private volatile int CurrentAmountOfThreads;
    private final Thread[] threads;
    private final BlockingQueue<Runnable> tasks;
    @Override
    public void start() {
        for (int i = 0; i < MinAmountOfThreads; i++) {
            threads[i] = new WorkerThread();
            threads[i].start();
        }
        CurrentAmountOfThreads = MinAmountOfThreads;
    }

    @Override
    public void execute(Runnable runnable) {
        tasks.offer(runnable);

        // Если нет свободных потоков и текущее количество меньше максимального, увеличиваем количество потоков
        if (tasks.size() > CurrentAmountOfThreads && CurrentAmountOfThreads < MaxAmountOfThreads) {
            synchronized (this) {
                threads[CurrentAmountOfThreads] = new WorkerThread();
                threads[CurrentAmountOfThreads].start();
                CurrentAmountOfThreads++;
            }
        }

        // Если очередь пуста и текущее количество больше минимального, уменьшаем количество потоков
        if (tasks.isEmpty() && CurrentAmountOfThreads > MinAmountOfThreads) {
            tasks.offer(() -> {}); // Пустая задача для принудительного выхода из блокировки потока
            synchronized (this) {
                CurrentAmountOfThreads--;
            }
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
        for (int i = 0; i < MaxAmountOfThreads; i++) {
            threads[i].interrupt();
        }
    }

}
