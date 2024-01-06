package ex01;

public class TerminalServer {
    private float balance = 0;

    public float getBalance() {
        return balance;
    }

    public void put(float amount) throws IllegalArgumentException {
        if (amount % 100 == 0 && amount > 0) {
            balance += amount;
            System.out.println("На счет зачислено: " + amount);
        }
        else if (amount < 0) {
            throw new IllegalArgumentException("Сумма должна быть положительной");
        }
        else {
            throw new IllegalArgumentException("Сумма должна быть кратна 100");
        }
    }

    public void withdraw(float amount) throws IllegalArgumentException {
        if (amount % 100 == 0 && amount > 0) {
            if (balance < amount) {
                throw new IllegalArgumentException("Недостаточно средств на счете");
            }
            balance -= amount;
            System.out.println("Со счета снято: " + amount);
        }
        else if (amount < 0) {
            throw new IllegalArgumentException("Сумма должна быть положительной");
        }
        else {
            throw new IllegalArgumentException("Сумма должна быть кратна 100");
        }
    }
}
