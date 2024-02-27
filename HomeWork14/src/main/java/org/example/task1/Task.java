package org.example.task1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Task<T> {

    private final Callable<? extends T> callable;
    private volatile FutureTask<T> future;

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
    }

    public T get() {
        if (future == null) {
            synchronized (this) {
                if (future == null) {
                    future = new FutureTask<>(() -> {
                        try {
                            return callable.call();
                        } catch (Exception e) {
                            throw new TaskExecutionException("Exception during task execution", e);
                        }
                    });
                    future.run();
                }
            }
        }

        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new TaskExecutionException("Exception while getting the result", e);
        }
    }
}
