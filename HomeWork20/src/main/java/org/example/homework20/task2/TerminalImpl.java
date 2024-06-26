package org.example.homework20.task2;

public class TerminalImpl implements Terminal{
    private final TerminalServer server;
    private final PinValidator pinValidator;
    private static final MessageDisplay display = new ConsoleDisplay();

    public TerminalImpl(String correctPin) {
        this.server = new TerminalServer();
        this.pinValidator = new PinValidator(correctPin);
    }

    public PinValidator getPinValidator() {
        return pinValidator;
    }

    public void inputPinAndValidate() {
        pinValidator.validatePin();
    }

    @Override
    public void checkBalance() {
        display.displayMessage("Баланс: " + server.getBalance());
    }

    @Override
    public void put(int amount) {
        try {
            checkAmount(amount);
            server.put(amount);
        } catch (IllegalArgumentException e) {
            display.displayMessage(e.getMessage());
        }
    }

    @Override
    public void withdraw(int amount) {
        try {
            checkAmount(amount);
            server.withdraw(amount);
        } catch (IllegalArgumentException e) {
            display.displayMessage(e.getMessage());
        }
    }

    public static void checkAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма должна быть положительной");
        }
        else if (amount % 100 != 0){
            throw new IllegalArgumentException("Сумма должна быть кратна 100");
        }
    }

    public Float getBalance() {
        return server.getBalance();
    }
}
