package ex01;

public class TerminalServer {
    private float balance = 0;

    public float balance() {
        return balance;
    }

    public void put(float amount) throws IllegalArgumentException {
        if (amount % 100 == 0) {
            balance += amount;
            System.out.println("На счет зачислено: " + amount);
        }
        else {
            throw new IllegalArgumentException("Сумма должна быть кратна 100");
        }
    }

    public void withdraw(float amount) throws IllegalArgumentException {
        if (amount % 100 == 0) {
            if (balance < amount) {
                throw new IllegalArgumentException("Недостаточно средств на счете");
            }
            balance -= amount;
        }
        else {
            throw new IllegalArgumentException("Сумма должна быть кратна 100");
        }
    }
}
