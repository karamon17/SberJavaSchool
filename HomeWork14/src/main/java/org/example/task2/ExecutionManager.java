package org.example.task2;

public interface ExecutionManager {
    /**
     * @param callback the callback to be executed after all tasks have completed execution
     * @param tasks    the tasks to execute
     * @return the context that allows to control the execution of tasks
     */
    Context execute(Runnable callback, Runnable... tasks);
}
