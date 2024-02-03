package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool {

    public FixedThreadPool(int amountOfThreads) {
        this.amountOfThreads = amountOfThreads;
        threads = new Thread[amountOfThreads];
        tasks = new LinkedBlockingQueue<>();
    }

    private final int amountOfThreads;
    private final Thread[] threads;
    private final BlockingQueue<Runnable> tasks;

    @Override
    public void start() {
        for (int i = 0; i < amountOfThreads; i++) {
            threads[i] = new WorkerThread();
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            tasks.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private class WorkerThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Runnable task = tasks.take();
                    task.run();
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    public void stop() {
        for (int i = 0; i < amountOfThreads; i++) {
            threads[i].interrupt();
        }
    }
}
