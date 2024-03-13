package org.example;

public interface Terminal {
    /**
     * Check balance
     */
    void checkBalance();
    /**
     * Put money
     * @param amount amount to put
     */
    void put(int amount);
    /**
     * Withdraw money
     * @param amount amount to withdraw
     */
    void withdraw(int amount);
}
