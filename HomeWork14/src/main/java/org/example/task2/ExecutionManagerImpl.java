package org.example.task2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutionManagerImpl implements ExecutionManager {
    private final ExecutorService executorService;

    public ExecutionManagerImpl(int threadsCount) {
        executorService = Executors.newFixedThreadPool(threadsCount);
    }

    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        ExecutionContext context = new ExecutionContext(tasks.length, callback);

        for (Runnable task : tasks) {
            executorService.submit(() -> {
                try {
                    task.run();
                    context.onTaskCompleted();
                } catch (Exception e) {
                    context.onTaskFailed();
                }
            });
        }

        return context;
    }

    private class ExecutionContext implements Context {
        private final int amountOfTasks;
        private final Runnable callback;
        private final AtomicInteger completedTaskCount = new AtomicInteger(0);
        private final AtomicInteger failedTaskCount = new AtomicInteger(0);
        private final AtomicInteger interruptedTaskCount = new AtomicInteger(0);
        private volatile boolean isFinished = false;

        public ExecutionContext(int amountOfTasks, Runnable callback) {
            this.amountOfTasks = amountOfTasks;
            this.callback = callback;
        }

        public void onTaskCompleted() {
            completedTaskCount.incrementAndGet();
            checkIsFinished();
        }

        public void onTaskFailed() {
            failedTaskCount.incrementAndGet();
            checkIsFinished();
        }

        private synchronized void checkIsFinished() {
            boolean isAllTasksCompleted = (completedTaskCount.get() + failedTaskCount.get()) == amountOfTasks;
            if (isAllTasksCompleted && !isFinished) {
                isFinished = true;
                callback.run();
                notifyAll();
            }
        }

        @Override
        public int getCompletedTaskCount() {
            return completedTaskCount.get();
        }

        @Override
        public int getFailedTaskCount() {
            return failedTaskCount.get();
        }

        @Override
        public int getInterruptedTaskCount() {
            return interruptedTaskCount.get();
        }

        @Override
        public void interrupt() {
            executorService.shutdownNow();
            interruptedTaskCount.set(amountOfTasks - completedTaskCount.get() - failedTaskCount.get());
            checkIsFinished();
        }

        @Override
        public boolean isFinished() {
            return isFinished;
        }
    }
}
