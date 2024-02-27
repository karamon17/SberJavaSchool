package org.example.task2;

public interface Context {
    /**
     * @return the number of tasks that have completed execution
     */
    int getCompletedTaskCount();

    /**
     * @return the number of tasks that have Exception during execution
     */
    int getFailedTaskCount();

    /**
     * @return the number of tasks that have been interrupted
     */
    int getInterruptedTaskCount();

    /**
     * Interrupts all the tasks that have been submitted but not started yet.
     */
    void interrupt();

    /**
     * @return true if all tasks have completed following normal termination or have been interrupted
     */
    boolean isFinished();
}
