package org.example.homework20.task2;

public class AccountIsLockedException extends Exception {
    private final long remainingLockTime;

    public AccountIsLockedException(long remainingLockTime) {
        this.remainingLockTime = remainingLockTime;
    }

    public long getRemainingLockTime() {
        return remainingLockTime;
    }
}