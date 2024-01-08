package ex01;

public class TerminalServer {
    private float balance = 0;

    public float getBalance() {
        return balance;
    }

    public void put(int amount) throws IllegalArgumentException {
        balance += amount;
        System.out.println("На счет зачислено: " + amount);
    }

    public void withdraw(int amount) throws IllegalArgumentException {
        if (balance < amount) {
            throw new IllegalArgumentException("Недостаточно средств на счете");
        }
        balance -= amount;
        System.out.println("Со счета снято: " + amount);
    }
}
