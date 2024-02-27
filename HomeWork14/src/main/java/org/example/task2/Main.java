package org.example.task2;

public class Main {
    public static void main(String[] args) {
        ExecutionManager executionManager = new ExecutionManagerImpl(3);

        Runnable callback = () -> System.out.println("All tasks completed!");

        Runnable[] tasks = {
                () -> System.out.println("Task 1"),
                () -> {
                    System.out.println("Task 2");
                    throw new RuntimeException("Task 2 failed!");
                },
                () -> System.out.println("Task 3")
        };

        Context context = executionManager.execute(callback, tasks);

        try {
            Thread.sleep(2000);
            context.interrupt();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        while (!context.isFinished()) {
            // Waiting for execution to complete
        }

        System.out.println("Completed tasks: " + context.getCompletedTaskCount());
        System.out.println("Failed tasks: " + context.getFailedTaskCount());
        System.out.println("Interrupted tasks: " + context.getInterruptedTaskCount());
    }
}
