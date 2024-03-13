package org.example;

public class TerminalServer {
    private float balance = 0;

    public TerminalServer(MessageDisplay display) {
        this.display = display;
    }

    private MessageDisplay display;

    public float getBalance() {
        return balance;
    }

    public void put(int amount) throws IllegalArgumentException {
        balance += amount;
        display.displayMessage("На счет зачислено: " + amount);
    }

    public void withdraw(int amount) throws IllegalArgumentException {
        if (balance < amount) {
            throw new IllegalArgumentException("Недостаточно средств на счете");
        }
        balance -= amount;
        display.displayMessage("Со счета снято: " + amount);
    }
}
